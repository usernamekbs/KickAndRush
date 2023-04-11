package com.kick.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchRequestDto {
	
	private String keyword;
	private String option;
	
    public SearchRequestDto(String keyword,String option) {
    	this.keyword = keyword;
    	this.option = option;
    }
}
