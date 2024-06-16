package com.kick.dto.gallery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestGalleryDto {
	private Long id;
	private String title;
	private String content;
	private Long userId;
	private Long categoryId;
	private int views;
}
