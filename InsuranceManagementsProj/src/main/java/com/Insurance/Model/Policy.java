package com.Insurance.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="policies")
public class Policy {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long policyNumber;
	    private String coverageType;
	    private double premium;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private String insuranceName;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "userId", referencedColumnName = "userId")
	    private User user;

	    // Constructors, getters, setters
	}



