package com.kick.common;

import lombok.Getter;

@Getter
public enum Role {
	MEMBER("MEMBER"),
	ADMIN("ADMIN"); 

	private final String content;
	
	private Role(String content) {
		this.content = content;
	}
}
