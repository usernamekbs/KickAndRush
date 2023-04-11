package com.kick.model.post;

import com.kick.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostViewDto {
	private Long pno;
	private String title;
    private String content;
    private String writer;
    private int view;
    
	public static PostViewDto convertPostToDto(Post post) {
		return new PostViewDto(post.getPno(),post.getTitle(), post.getContent(), post.getWriter(),post.getView());
	}
	
}
