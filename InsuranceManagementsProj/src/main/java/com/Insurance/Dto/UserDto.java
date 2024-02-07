package com.Insurance.Dto;

import java.util.List;

import com.Insurance.Model.Policy;
import com.Insurance.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {    
    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
//    private PolicyDto policy;
    
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
}
