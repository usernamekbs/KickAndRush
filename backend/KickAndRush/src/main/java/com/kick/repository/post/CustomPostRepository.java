package com.kick.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kick.model.post.PostDto;
import com.kick.model.post.PostRequestDto;

public interface CustomPostRepository {
	
	Page<PostDto> findByPost(String keyword,String option,PostRequestDto postDto,Pageable pageable );
}