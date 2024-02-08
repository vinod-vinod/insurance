package com.Insurance.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Insurance.Dto.UserDetailsDto;
import com.Insurance.Model.User;
import com.Insurance.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

public class UserDetailServiceRepo implements UserDetailsService{

	@Autowired
	private UserRepository userepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userinfo= userepo.findByUsername(username);
		
		return userinfo.map(UserDetailsDto::new).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
	}


}
