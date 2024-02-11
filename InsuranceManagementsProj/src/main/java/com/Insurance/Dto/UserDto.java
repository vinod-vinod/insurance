package com.Insurance.Dto;

import java.util.List;

import com.Insurance.Model.Policy;
import com.Insurance.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDto {    
    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private List<PolicyDto> policy;
    
    public static UserDto convertToDto(User user) {
    	  
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
//        userDto.setPolicy(user.getPolicies());
        return userDto;

}
    public static UserDto convertToDtowithPolicies(User user) {
  	  
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setPolicy(PolicyDto.convertToPOlicyDtoList(user.getPolicies()));

        return userDto;

}

}
