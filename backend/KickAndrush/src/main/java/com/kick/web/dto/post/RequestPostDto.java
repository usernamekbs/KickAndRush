package com.kick.web.dto.post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kick.entity.Category;
import com.kick.entity.Post;
import com.kick.oauth.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostDto {
	
	@NotNull(message="해당 게시글에 제목을 입력해주세요")
	@Size(min=5,message="제목에 5글자 이상 입력해주세요")
	private String title;
	
	private String content;
	
	@NotNull(message="유저 로그인을 해주세요")
	private Long userId;
	
	@NotNull(message="해당 게시글에 카테고리 번호가 없습니다")
	private Long categoryId;
	
	public Post toEntity(User user,Category category) {
		return new Post(title,content,user,category);
	}
}
