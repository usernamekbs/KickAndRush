package com.kick.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OauthViewDto {
	
	private Long id;
	private String name;
	private String email;
	private ERole role;
	
	public OauthViewDto(User naverUserDetail) {
		this.id = naverUserDetail.getId();
		this.name = naverUserDetail.getName();
		this.email = naverUserDetail.getEmail();
		this.role = naverUserDetail.getRole();
	}
}
