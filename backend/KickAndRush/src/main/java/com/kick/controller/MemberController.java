package com.kick.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.model.member.MemberDto;
import com.kick.model.member.MemberRequestDto;
import com.kick.service.MemberService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService; 
	
	@GetMapping("/login/{username}")  
    public UserDetails login(@PathVariable String username) {
		
		return memberService.loadUserByUsername(username);  
    }
	
	@PostMapping("/create")  
    public MemberDto create(@RequestBody MemberRequestDto requestDto) {
		
		return memberService.create(requestDto);   
    }
	
	@GetMapping("/exists/{username}")   
    public MemberDto existsByMemberid(@PathVariable String username) {
		return memberService.existsByMemberid(username);  
    }
}
