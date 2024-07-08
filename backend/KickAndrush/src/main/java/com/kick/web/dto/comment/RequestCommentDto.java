package com.kick.web.dto.comment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class RequestCommentDto {
	@NotNull(message="해당 댓글에 내용을 입력해주세요")
	@Size(min=1,message="1글자 이상 입력 해주세요")
	private String content;
	@NotNull(message="로그인을 진행해 주세요")
	private Long userId;
	@NotNull(message="해당 게시물을 찾을수 없습니다.")
	private Long postId;
	private Long parent;
	
}
