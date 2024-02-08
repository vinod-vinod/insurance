package com.Insurance.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Insurance.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
}
