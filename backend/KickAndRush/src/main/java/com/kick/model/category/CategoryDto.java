package com.kick.model.category;

import com.kick.entity.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	private Long ctg_cno;
	private String title;
	private String url;
	
	public CategoryDto(Long ctg_cno,String title,String url) {
		this.ctg_cno = ctg_cno;
		this.title = title;
		this.url = url;
	}
	
	public static CategoryDto convertCategoryToDto(Category category) {
		return new CategoryDto(category.getCtg_cno(),category.getTitle(),category.getUrl());
	}

}


