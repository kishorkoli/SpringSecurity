package com.kk.secutiry.SpringSecutiry.customfilters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.kk.secutiry.SpringSecutiry.MyAuthenticationProvider;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	//This commented method is used when we dont use custom authentication provider.
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //If
	 * BCryptPasswordEncoder is not used as a bean
	 * 
	 * InMemoryUserDetailsManager userDetailsService = new
	 * InMemoryUserDetailsManager();
	 * 
	 * UserDetails user =
	 * User.withUsername("kk").password(passwordEncoder.encode("pass")).authorities(
	 * "read").build(); userDetailsService.createUser(user);
	 * auth.userDetailsService(userDetailsService) .passwordEncoder(passwordEncoder)
	 * ;
	 * 
	 * }
	 */

	@Autowired
	MyAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic();
		//http.authorizeRequests().anyRequest().authenticated(); //For all requests
		http.authorizeRequests().antMatchers("/hello").authenticated();
		http.addFilterBefore(new MySecutiryFIlter(), BasicAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
