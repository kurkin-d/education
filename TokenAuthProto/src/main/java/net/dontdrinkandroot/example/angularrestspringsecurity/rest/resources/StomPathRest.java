package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;

/**
 */
@Controller
@RequestMapping
public class StomPathRest {

	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<String> login(HttpServletResponse response){
		Key key = MacProvider.generateKey();
		String s = Jwts.builder().setSubject("admin").setId("adsdsfufu").signWith(SignatureAlgorithm.HS512, key).compact();
		response.addCookie(new Cookie("access_token", s));
		return new ResponseEntity(HttpStatus.OK);
	}
}
