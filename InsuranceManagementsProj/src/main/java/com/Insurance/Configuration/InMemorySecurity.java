package com.Insurance.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class InMemorySecurity {

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder)
	{
		UserDetails admin =User.withUsername("vinod").password(encoder.encode("vinod")).roles("ADMIN").build();
		UserDetails user =User.withUsername("user").password(encoder.encode("user")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		
		return httpSecurity.csrf().disable()
           .authorizeHttpRequests()
               .requestMatchers("/users/allusers").permitAll().and().authorizeHttpRequests(). // Public endpoints
               requestMatchers("/policies/getAllPolicies").authenticated()
           .and()
           .formLogin().and().build();
	}

}
