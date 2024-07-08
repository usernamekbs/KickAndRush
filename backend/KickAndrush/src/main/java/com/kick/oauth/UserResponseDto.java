package com.kick.oauth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
	private String resultcode;
    private String message;
    private UserDto response;
}
