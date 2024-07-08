package com.kick.web.dto.likes;

import com.kick.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLikesDto {
	private Post post;
}
