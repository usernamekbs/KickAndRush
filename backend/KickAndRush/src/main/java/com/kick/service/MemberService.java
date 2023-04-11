package com.kick.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.Member;
import com.kick.model.member.MemberDto;
import com.kick.model.member.MemberRequestDto;
import com.kick.repository.member.MemberRepository;
import com.kick.common.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
//implements UserDetailsService 
public class MemberService {
 
	private final MemberRepository memberRepository;

	private final PasswordEncoder encoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberid(username);
		
		if(member == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new MemberDto(member, "로그인 되었습니다.");
	}
	
	@Transactional
	public MemberDto create(MemberRequestDto requestDto) {
		boolean exists_password = isDuplecateMemberPass(requestDto.getMember_password(),requestDto.getMember_chkpassword());
		
		if(exists_password == false) {
			Member member = memberRepository.save(
				Member.createMember(
					requestDto.getMember_id(), 
					encoder.encode(requestDto.getMember_password()), 
					requestDto.getMember_nick(),
					requestDto.getMember_email(),
					requestDto.getMember_role()
				)
			); 
			return MemberDto.convertMemberToDto(member,"회원 가입이 완료되었습니다.");
		}else{  
			return MemberDto.duplicatePassMemberToDto(exists_password);
		} 
	} 
	
	private boolean isDuplecateMemberPass(String password,String chkpassword) {
		if(!password.equals(chkpassword)) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public MemberDto existsByMemberid(String username) {
		boolean exists_memberId = memberRepository.existsByMemberid(username);
		
		if(exists_memberId==true) {
			return MemberDto.duplicateIdMemberToDto2(exists_memberId);
		}else {
			return MemberDto.duplicateIdMemberToDto2(exists_memberId);
		}
	}


}
