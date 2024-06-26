package com.kick.oauth;

import javax.validation.constraints.NotEmpty;

import org.checkerframework.common.value.qual.MinLen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private Long userId;
	private String id;
	private String name;
	private String email;
	private String accessToken;
	private String expiresIn;
	private ERole role;
}

