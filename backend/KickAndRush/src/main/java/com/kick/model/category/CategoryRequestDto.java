package com.kick.model.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryRequestDto {
	private Long ctg_cno;
    private String title;
	private String url;
}