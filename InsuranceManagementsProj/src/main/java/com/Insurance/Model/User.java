package com.Insurance.Model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
@Id
@GeneratedValue(strategy= GenerationType.SEQUENCE)
private int userId;
private String firstname;
private String lastname;
private String email;
private String role;
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonIgnoreProperties("user")
private List<Policy> policies = new ArrayList<>();
public User(int userId, String firstname, String lastname, String email, String role) {
	super();
	this.userId = userId;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.role = role;
}



}
