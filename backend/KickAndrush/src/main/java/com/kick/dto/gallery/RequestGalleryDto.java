package com.kick.dto.gallery;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestGalleryDto {
	private Long id;
	@NotNull
	@Size(min=5,message="제목에 5글자 이상 입력해주세요")
	private String title;
	
	private String content;
	private Long userId;
	private Long categoryId;
	private int views;
}
