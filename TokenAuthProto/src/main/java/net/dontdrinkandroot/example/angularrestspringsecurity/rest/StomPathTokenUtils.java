package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 *
 */
public class StomPathTokenUtils {

	private static Key key = MacProvider.generateKey();

	public static String createToken(UserDetails userDetails) {
		Date expires = new Date(System.currentTimeMillis() + 1000L * 60 * 60);
		String s = Jwts.builder().setSubject(userDetails.getUsername()).setId(UUID.randomUUID().toString()).setExpiration(expires)
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return s;
	}

	public static String getUserNameFromToken(String authToken) {
		if (null == authToken) {
			return null;
		}
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
		String user = claimsJws.getBody().getSubject();
		return user;
	}

	public static boolean validateToken(String authToken, UserDetails userDetails) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
			if(!claimsJws.getBody().containsKey("exp") || claimsJws.getBody().getExpiration()==null){
				return false;
			}
			String id = claimsJws.getBody().getId();
			Date expiration = claimsJws.getBody().getExpiration();
			Date current = new Date(System.currentTimeMillis());
			return expiration.after(current);
		} catch (SignatureException e) {
			//don't trust the JWT!
			return false;
		}
	}
}
