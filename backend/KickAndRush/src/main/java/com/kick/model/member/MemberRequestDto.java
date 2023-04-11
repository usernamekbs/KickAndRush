package com.kick.model.member;

import com.kick.common.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
	
 	private String member_id;
 	private String member_password;
 	private String member_nick;
 	private String member_email;
 	private String member_chkpassword;
 	private Role member_role;   
 	
 	 public MemberRequestDto(String member_id, String member_password,String member_chkpassword,String member_nick,String member_email,Role member_role) {
     	this.member_id 			= member_id;
     	this.member_password 	= member_password;
     	this.member_chkpassword = member_chkpassword;
     	this.member_email 		= member_email;
     	this.member_nick 		= member_nick;
     	this.member_role		= member_role;
     }

}
