package com.Insurance.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.Insurance.Service.UserDetailServiceRepo;

@Configuration
@EnableWebSecurity
public class InMemorySecurity {

	@Bean
	public UserDetailsService userDetailsService(/*PasswordEncoder encoder*/)
	{
//		Commented because we are getting details from database
//		UserDetails admin =User.withUsername("vinod").password(encoder.encode("vinod")).roles("ADMIN").build();
//		UserDetails user =User.withUsername("user").password(encoder.encode("user")).roles("USER").build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		
		return new UserDetailServiceRepo();
	}
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		
		return httpSecurity.csrf().disable().authorizeHttpRequests()
	               .requestMatchers("/users/saveUser").permitAll().and()
               .authorizeHttpRequests(). 
               requestMatchers("/policies/getAllPolicies","/users/allusers").authenticated()
           .and()
           .formLogin().and().build();
	}

}
