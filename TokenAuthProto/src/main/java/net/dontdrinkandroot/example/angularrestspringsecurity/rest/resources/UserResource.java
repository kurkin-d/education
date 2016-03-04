package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;

import java.util.HashMap;
import java.util.Map;

import net.dontdrinkandroot.example.angularrestspringsecurity.rest.StomPathTokenUtils;
import net.dontdrinkandroot.example.angularrestspringsecurity.transfer.TokenTransfer;
import net.dontdrinkandroot.example.angularrestspringsecurity.transfer.UserTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
//@Path("/user")
@RequestMapping("/rest/user")
public class UserResource
{

	@Autowired
	private UserDetailsService userService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;


	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public UserTransfer getUser() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			throw new Exception("ошибка анонимный пользователь "+401);
		}
		UserDetails userDetails = (UserDetails) principal;
		return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
	}


	public static class LoginFormParams{
		String username;
		String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 *
	 * @return A transfer containing the authentication token.
	 */
	//@Path("authenticate")
	//@POST
	//@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	@ResponseBody
	public TokenTransfer authenticate(@RequestBody LoginFormParams loginForm)
	{
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = this.userService.loadUserByUsername(loginForm.getUsername());

		return new TokenTransfer(StomPathTokenUtils.createToken(userDetails));
	}

	@RequestMapping(value = "authenticate", method = RequestMethod.GET)
	@ResponseBody
	public String getHello(){
		return "hello";
	}


	private Map<String, Boolean> createRoleMap(UserDetails userDetails)
	{
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}

}