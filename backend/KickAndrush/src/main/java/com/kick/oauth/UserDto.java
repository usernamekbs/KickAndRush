package com.kick.oauth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
	private Long naverId;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	private ERole role;
	
	public UserDto(User naverUserDetail) {
		this.naverId = naverUserDetail.getId();
		this.id		 = naverUserDetail.getNaverId();
		this.name    = naverUserDetail.getName();
		this.email   = naverUserDetail.getEmail();
		this.role 	 = naverUserDetail.getRole();
	}
}
