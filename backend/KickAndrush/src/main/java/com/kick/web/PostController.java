package com.kick.web;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kick.service.PostService;
import com.kick.web.dto.post.PostDto;
import com.kick.web.dto.post.PostViewDto;
import com.kick.web.dto.post.RequestPostDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@GetMapping("/list") 
	public Page<PostDto> postList(Pageable pageable,
			@RequestParam(value="searchText", required=false) String searchText,
			@RequestParam(value="searchType", required=false) String searchType
			){ 
		return postService.postList(pageable,searchText,searchType);
	} 

	@GetMapping("/view/{id}")
	public PostViewDto postView(@PathVariable Long id){
		return postService.postView(id);
	}
	
	@PostMapping("/create")
	public PostDto postSave(@RequestPart("requestPostDto") @Valid  RequestPostDto requestPostDto,
							@RequestParam(value = "files", required = false) List<MultipartFile> files) throws IOException{
		return postService.postSave(requestPostDto,files);
	} 
	
	@PutMapping("/update/{id}")
	public PostDto postUpdate(@RequestBody RequestPostDto requestPostDto,@PathVariable Long id)
	{
		return postService.postUpdate(requestPostDto,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void postDelete(@PathVariable Long id) {
		postService.postDelete(id);
	}
}