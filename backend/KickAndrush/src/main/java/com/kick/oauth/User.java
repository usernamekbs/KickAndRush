package com.kick.oauth;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naverId;
	private String name;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private ERole role;
	
	public User(UserDto naverUserDetailDto, ERole user) {
		this.naverId = naverUserDetailDto.getId();
		this.name = naverUserDetailDto.getName();
		this.email = naverUserDetailDto.getEmail();
		this.role  = user;
    }
}
