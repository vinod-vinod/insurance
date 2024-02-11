package com.Insurance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.Insurance.Dto.PolicyDto;
import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Model.Policy;
import com.Insurance.Service.PolicyService;
import com.Insurance.Service.UserService;

@RestController
@RequestMapping("/policies")
public class PolicyController {
	@Autowired
	private PolicyService policyService;
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/getAllPolicies")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<PolicyDto> getAllPolicies()
	{
		System.out.println("get all policies");
		return policyService.getAllPolicies();
		
	}
	@GetMapping("/{policyNumber}")
	public PolicyDto getPolicyById(@PathVariable("policyNumber") Long policyNumber) throws EntityNotFoundException
	{
		System.out.println("This is policy by id");
		return policyService.getPolicyId(policyNumber);
		
	}
	@PostMapping("/createPolicy/{id}")
	public ResponseEntity<PolicyDto> createPolicy(@RequestBody Policy policy,@PathVariable int id) throws EntityNotFoundException
	{
		PolicyDto createdPolicy = policyService.createPolicy(policy,id);
		return new ResponseEntity<>(createdPolicy,HttpStatus.CREATED);
		
	}
	@PutMapping("/updatePolicy/{policynumber}")
	public ResponseEntity<PolicyDto> updatePolicy(@RequestBody Policy policy,@PathVariable Long policynumber) throws EntityNotFoundException
	{
		PolicyDto updatePolicy = policyService.updatePolicy(policy,policynumber);
		return new ResponseEntity<>(updatePolicy,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	  public ResponseEntity<String> deletePolicyById(@PathVariable Long id) {
	        try {
	            String result = policyService.deletePolicyById(id);
	            return ResponseEntity.ok(result);
	        }
	        catch (EntityNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	  

}
