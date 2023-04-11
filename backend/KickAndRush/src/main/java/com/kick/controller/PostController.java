package com.kick.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.common.PageRequest;
import com.kick.model.post.PostRequestDto;
import com.kick.model.post.PostViewDto;
import com.kick.service.PostService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@PostMapping("/create") 
    public PostViewDto create(@RequestBody PostRequestDto requestDto) {
        return postService.create(requestDto);
    }
	
	@GetMapping("/view/{pno}")
    public PostViewDto findById(@PathVariable Long pno) {
        return postService.findById(pno);
    }
	 
	@GetMapping("/list/{page}/{size}")  
    public Page<Object> list(String keyword,String option,@PathVariable int page,@PathVariable int size ,final PageRequest pageable,PostRequestDto postDto) {
		return postService.findAllDesc(keyword,option,postDto,pageable.of(page, size));
    }
	
	@DeleteMapping("/delete/{pno}") 
    public PostViewDto delete(@PathVariable Long pno) {
		return postService.delete(pno);
    }
	
	@PatchMapping("/update/{pno}") 
    public PostViewDto update(@RequestBody PostRequestDto requestDto) {
		return postService.update(requestDto);
    }
}
 