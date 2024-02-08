package com.Insurance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Model.User;
import com.Insurance.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> registerUser(@RequestBody User user)
	{
		User savedUser=userService.saveUser(user);
	    return	new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	@GetMapping("/allusers")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User> getAllUsers()
	{
		return userService.gellAllUsersList();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) throws EntityNotFoundException
	{
		return userService.getUserId(id);
		
	}
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id ) throws EntityNotFoundException {
		return userService.delete(id);		
	}
	
	@PutMapping("/update/{id}")
	public User updateUser(@RequestBody User user,@PathVariable int id) throws EntityNotFoundException
	{
	return userService.updateUserData(user,id)	;
	}

}
