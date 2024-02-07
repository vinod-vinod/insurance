package com.Insurance.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Insurance.Dto.PolicyDto;
import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Model.Policy;
import com.Insurance.Model.User;
import com.Insurance.Repository.PolicyRepository;
@Service
public class PolicyService {
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
    private UserService userService;

	public List<PolicyDto> getAllPolicies() {
		
		 List<Policy> policylist = policyRepository.findAll() ;
		 return policylist.stream()
				 .map(p->PolicyDto.convertToPolicyDto(p)).collect(Collectors.toList());
	}

	public PolicyDto getPolicyId(Long policyNumber) throws EntityNotFoundException {
		Policy foundpolicy=policyRepository.findById(policyNumber)
				.orElseThrow(() -> new EntityNotFoundException("User with id " + policyNumber + " not found"));
		PolicyDto policygetbyid= PolicyDto.convertToPolicyDto(foundpolicy);
		
		return policygetbyid;
	}
	
	public PolicyDto createPolicy(Policy policy, int id) throws EntityNotFoundException {
		User user=userService.getUserId(id);
		policy.setUser(user);
        policyRepository.save(policy);
       return PolicyDto.convertToPolicyDto(policy);
	}

	public String deletePolicyById(Long policyNumber) throws EntityNotFoundException {
		PolicyDto found = getPolicyId(policyNumber);
		if(found!=null)
		{
			policyRepository.deleteById(policyNumber);
			return"Policy with ID " + policyNumber + " deleted successfully.";
		}
		return "Policy with ID " + policyNumber+"not found";
				
		
	}

	public PolicyDto updatePolicy(Policy policy, Long policynumber) throws EntityNotFoundException {
		Policy found=policyRepository.findById(policynumber)
				.orElseThrow(() -> new EntityNotFoundException("User with id " + policynumber + " not found"));
		if(found!=null)
		{
		found.setCoverageType(policy.getCoverageType());
		found.setPremium(policy.getPremium());
		found.setStartDate(policy.getStartDate());
		found.setEndDate(policy.getEndDate());
		found.setInsuranceName(policy.getInsuranceName());
		found.setUser(found.getUser());
		policyRepository.save(found);
		return PolicyDto.convertToPolicyDto(found);
		}
		 throw new EntityNotFoundException("Policy with id " + policynumber + " not found");
	}
}
