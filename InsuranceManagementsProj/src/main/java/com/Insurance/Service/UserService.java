package com.Insurance.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Model.User;
import com.Insurance.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;
	
	public User saveUser(User user)
	{
//		System.out.println();
		return userrepository.save(user);	
	}
	public List<User> gellAllUsersList() {		
	return userrepository.findAll();
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
