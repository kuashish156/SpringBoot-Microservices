package com.ashish.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credential")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer credentialId;

	@Column(unique = true) // user name must be unique so avoid dublicate user name
	private String username;
	private String password;

	// By default, JPA stores enum values as ORDINAL (0, 1, 2, ...).
	// Using EnumType.STRING stores the enum constant name in the database,
	// for example: ROLE_USER or ROLE_ADMIN, instead of its index.
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleBasedAuthority roleBasedAuthority;

	// @JsonIgnore tells the Jackson library to exclude this field
	// from JSON serialization, preventing the user object from appearing
	// in the JSON response and helping avoid infinite recursion
	// in bidirectional entity relationships.
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY) // Defines a one-to-one relationship between Credential and User.
	@JoinColumn(name = "user_id", unique = true, nullable = true)
	private User user;

}
