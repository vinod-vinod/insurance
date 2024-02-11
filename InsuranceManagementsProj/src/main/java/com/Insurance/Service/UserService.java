package com.Insurance.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Insurance.Dto.UserDto;
import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Exception.UseralreadyExists;
import com.Insurance.Model.User;
import com.Insurance.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;
	
	@Autowired 
	private PasswordEncoder encoder;
	boolean flag;
	public User saveUser(User user) throws UseralreadyExists
	{
//		System.out.println();
		List<User> userlist = userrepository.findAll();
		if(null != userlist) {
			for(User u:userlist) {
				if(u.getUsername().equals(user.getUsername())){
					flag=true;
				}
				else {
					flag=false;
				}
			}
		}
		if(flag)
			throw  new UseralreadyExists("User Already Exists with this username");
		else {
			user.setPassword(encoder.encode(user.getPassword()));
			return userrepository.save(user);	
		}
	}
	public List<UserDto> gellAllUsersList() {		
		List<User> dtolist= userrepository.findAll();
	 return dtolist.stream().map(dto->UserDto.convertToDtowithPolicies(dto)).collect(Collectors.toList());
	}
	
	public String delete(int id) throws EntityNotFoundException 
	{
		System.out.println("id "+id);
		User founduser=getUserId(id);
		System.out.println(founduser.getFirstname());
		if(founduser!=null)
		{
			userrepository.deleteById(id);
				return "User deleted with Id: "+id;
		}
		return "";
	}
	
	public User getUserId(int id) throws EntityNotFoundException {		
		return userrepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
	}
	public User updateUserData(User user, int id) throws EntityNotFoundException {
		System.out.println("this is from updateUserData "+user);
		System.out.println("this is id from updateUserData"+id);
		User found =getUserId(id);
		System.out.println(found);
		found.setFirstname(user.getFirstname());
		found.setLastname(user.getLastname());
		found.setEmail(user.getEmail());
		found.setRole(user.getRole());
		User updateuser=userrepository.saveAndFlush(found);
		System.out.println(updateuser);
		return updateuser;		
	}
	}
