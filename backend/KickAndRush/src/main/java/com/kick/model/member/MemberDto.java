package com.kick.model.member;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kick.entity.Member;
import com.kick.common.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto implements UserDetails{
	
 	private String member_id;
 	private String member_password;
 	private String member_nick;
 	private String member_email;
 	private Role member_role;   
	private String message  ="";
	
	public MemberDto(Member member ,String message) {
		this.member_id = member.getMemberid();
		this.member_password = member.getMember_password();
		this.member_nick = member.getMember_nick();
		this.member_email = member.getMember_email();
		this.member_role = member.getMember_role();
		this.message = message;
		
	}
	 
	public MemberDto(String message) {
		this.message = message;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
 
        collectors.add(() -> "ROLE_"+member_role);
        return collectors;
    }

 	@Override 
	public String getPassword() {
		return this.member_password;
	}

	@Override
	public String getUsername() {
		return this.member_id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public static MemberDto duplicatePassMemberToDto(boolean exists_password) {
		
		return exists_password == true ? new MemberDto("패스워드가 틀립니다.") : new MemberDto("비밀번호가 정확합니다.");
	}

	public static MemberDto duplicateIdMemberToDto2(boolean exists_memberId) {
		
		return exists_memberId == true ? new MemberDto("아이디가 중복됩니다.") : new MemberDto("사용가능한 아이디입니다.");
	}

	public static MemberDto convertMemberToDto(Member member, String message) {
		return new MemberDto(member.getMemberid(),member.getMember_password(),member.getMember_nick(),member.getMember_email(), member.getMember_role(),"회원 가입이 완료되었습니다.");
	}	

}
