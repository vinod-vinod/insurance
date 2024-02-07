package com.Insurance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Insurance.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
