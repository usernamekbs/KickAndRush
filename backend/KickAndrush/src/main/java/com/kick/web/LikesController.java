package com.kick.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.service.LikesService;
import com.kick.web.dto.likes.LikesDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikesController {
	
	private final LikesService likesService;
	
	@PostMapping("/create")
	public int likeSave(@RequestBody LikesDto like) {
		return likesService.likeSave(like.getPostId(),like.getUserId());
	}   
	
}
