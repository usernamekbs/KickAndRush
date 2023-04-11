package com.kick.model.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
	private Long cno;
    private Long pno;
	private String content;
    private String writer;
    private Long parent;
	private String message;
}