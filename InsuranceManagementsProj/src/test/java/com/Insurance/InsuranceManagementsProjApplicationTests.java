package com.Insurance;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Exception.UseralreadyExists;
import com.Insurance.Model.User;
import com.Insurance.Repository.UserRepository;
import com.Insurance.Service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class InsuranceManagementsProjApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void gellAllUsersListTest()
	{
		when(userRepository.findAll())
	    .thenReturn(Stream.of(new User(1,"soujbh","soujanya", "vadde","soujanya@123", "soujanyavadde@gmail.com", "Admin", new ArrayList<>()))
	                      .collect(Collectors.toList()));
		assertEquals(1, userService.gellAllUsersList().size());
	}
	
	@Test
	public void saveUserTest() throws UseralreadyExists
	{
//		    User user = new User(1, "soujabh", "soujanya", "vadde", "soujanyavadde@gmail.com", "Admin");
//		    when(userRepository.save(user)).thenThrow(new UseralreadyExists("User already exists"));
//
//		    assertThrows(UseralreadyExists.class, () -> userService.saveUser(user));
////		}

		User user= new User(1,"soujabh","soujanya", "vadde","soujanya@123", "soujanyavadde@gmail.com", "Admin");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.saveUser(user));
	}
	@Test
	public void deleteTest() throws EntityNotFoundException
	{
		User user= new User(1,"soum","soumya", "bhoomandla","soumya@123", "soumya@gmail.com", "Admin");
	    when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		assertEquals("User deleted with Id: 1",userService.delete(user.getUserId()) );
	}
	@Test
	public void updateUserDataTest() throws EntityNotFoundException
	{
		 User user= new User(1,"soum","soumya", "bhoomandla","soumya@123", "soumya@gmail.com", "Admin");
		 User updatedUserData = new User(1,"soujabh", "soujanya", "vadde","soujanya@123", "soujanyavadde@gmail.com", "Admin");
		 when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		 
		 
		 when(userRepository.saveAndFlush(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

		 User updatedUser = userService.updateUserData(updatedUserData, user.getUserId());
		 assertNotNull(updatedUser);

		 assertEquals("soujanya", updatedUser.getFirstname());
		 
		 
		 
	}
}
