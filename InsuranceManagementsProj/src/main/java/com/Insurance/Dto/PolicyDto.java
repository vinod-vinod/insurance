package com.Insurance.Dto;

import java.time.LocalDate;

import com.Insurance.Model.Policy;
import com.Insurance.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
    private Long policyNumber;
    private String coverageType;
    private double premium;
    private LocalDate startDate;
    private LocalDate endDate;
    private String insuranceName;
    private UserDto user;  // Include UserDto in PolicyDto

    public static PolicyDto convertToPolicyDto(Policy policy) {
        PolicyDto policyDto = new PolicyDto();
        policyDto.setPolicyNumber(policy.getPolicyNumber());
        policyDto.setCoverageType(policy.getCoverageType());
        policyDto.setPremium(policy.getPremium());
        policyDto.setStartDate(policy.getStartDate());
        policyDto.setEndDate(policy.getEndDate());
        policyDto.setInsuranceName(policy.getInsuranceName());

        // Include user details in the DTO
        User user = policy.getUser();
        if (user != null) {
            policyDto.setUser(UserDto.convertToDto(user));
        }

        return policyDto;
    }

   
}
