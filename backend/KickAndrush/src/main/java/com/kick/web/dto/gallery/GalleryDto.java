package com.kick.web.dto.gallery;

import com.kick.entity.Gallery;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GalleryDto {
	
	private Long id;
	private String title;
	private String content;
	private String filePath;
	private String origName;
	private int views;
	private String email;
	private String name;
	
	public GalleryDto(Gallery gallery) {
		this.id 			= gallery.getId();
		this.title 			= gallery.getTitle();
		this.content 		= gallery.getContent();
		this.filePath		= gallery.getFilePath();
		this.origName		= gallery.getOrigName();
		this.views 			= gallery.getViews();
		this.email			= gallery.getUser().getEmail();
		this.name			= gallery.getCategories().getName();
	}
}
