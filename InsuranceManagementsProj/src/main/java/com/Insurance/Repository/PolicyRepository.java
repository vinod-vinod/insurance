package com.Insurance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Insurance.Model.Policy;
import com.Insurance.Model.User;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
//	 User save(User entity);

}
