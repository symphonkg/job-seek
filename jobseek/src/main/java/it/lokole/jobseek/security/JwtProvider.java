package it.lokole.jobseek.security;

import java.security.Key;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	private Key key;
	
	@PostConstruct
	public void init(){
		key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	public String generateToken(Authentication authentication) {
		User mainUser = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(mainUser.getUsername())
				.signWith(key)
				.compact();

	}
	
	
	public boolean validateToken(String token) {
		Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		return true;
	}

	public String getUserNameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
		
	}
}
