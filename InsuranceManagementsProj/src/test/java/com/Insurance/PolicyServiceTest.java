package com.Insurance;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Insurance.Dto.PolicyDto;
import com.Insurance.Dto.UserDto;
import com.Insurance.Exception.EntityNotFoundException;
import com.Insurance.Model.Policy;
import com.Insurance.Model.User;
import com.Insurance.Repository.PolicyRepository;
import com.Insurance.Service.PolicyService;
import com.Insurance.Service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyServiceTest {
	
	@MockBean
	private PolicyRepository policyrepo;
	
	@Mock
	private UserService userservice;
	
	@Autowired
	private PolicyService policyservice;
	
	

	@Test
	public void getAllPoliciesTest() {
		List<Policy> mockPolicyList = Arrays.asList(
				 new Policy(1L, "Health", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Insurance A", new User()),
	                new Policy(2L, "Life", 1000.0, LocalDate.of(2022, 2, 1), LocalDate.of(2022, 11, 30), "Insurance B", new User()));

        when(policyrepo.findAll()).thenReturn(mockPolicyList);

        // Call the service method
        List<PolicyDto> result = policyservice.getAllPolicies();

        // Assertions
        assertEquals(2, result.size());
	}
	
	@Test
	public void getPolicyById() throws EntityNotFoundException {
		Long policyNumber= 1L;
		Policy mockpolicy = new Policy(1L, "Health", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Insurance A", new User());
		when(policyrepo.findById(policyNumber)).thenReturn(Optional.of(mockpolicy));
		PolicyDto result = policyservice.getPolicyId(policyNumber);
		assertEquals(policyNumber, result.getPolicyNumber());
	}
	
	@Test
	public void createPolicyTest() throws EntityNotFoundException {
		int userid = 1;
		User mockuser= new User(1,"soujabh","soujanya", "vadde","soujanya@123", "soujanyavadde@gmail.com", "Admin");
		when(userservice.getUserId(userid)).thenReturn(mockuser);
		Policy mockpolicysaved = new Policy(1L, "Health", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Insurance A", mockuser);
		when(policyrepo.save(mockpolicysaved)).thenReturn(mockpolicysaved);
		assertEquals(mockpolicysaved.getCoverageType(),policyservice.createPolicy(mockpolicysaved, userid).getCoverageType());	
	}
	
	@Test
	public void deletePolicyTest() throws EntityNotFoundException  {
		long policynumber = 1L;
		Policy mockpolicy = new Policy(1L, "Health", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Insurance A", new User());
		PolicyDto mockpolicydto = new PolicyDto(1L, "Health", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Insurance A", new UserDto());
//		when(policyservice.getPolicyId(policynumber)).thenReturn(mockpolicydto);
		when(policyrepo.findById(policynumber)).thenReturn(Optional.of( mockpolicy));
		String result = policyservice.deletePolicyById(policynumber);
	    assertEquals("Policy with ID 1 deleted successfully.", result);
	}
	
	@Test
	public void updatePolicyTest() throws EntityNotFoundException {
		long policynumber =1L;
		Policy mockpolicy = new Policy(1L, "Health", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Insurance A", new User());
		when(policyrepo.findById(policynumber)).thenReturn(Optional.of( mockpolicy));
		Policy updatedpolicy = new Policy(1L, "Life", 500.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), "Life Insurance", new User());
		 when(policyrepo.saveAndFlush(any(Policy.class))).then(AdditionalAnswers.returnsFirstArg());
		 PolicyDto result = policyservice.updatePolicy(updatedpolicy, policynumber);
		 assertNotNull(result);
		 assertEquals("Life Insurance", result.getInsuranceName());
	}
	
}