package com.kk.secutiry.SpringSecutiry;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if("kk".equals(userName) && "pass".equals(password)) {
		return new UsernamePasswordAuthenticationToken(userName, password, Arrays.asList());
		} else {
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
	
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	
	}

}
