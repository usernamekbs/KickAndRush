package com.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoupangDetailsRequestDto {
	
	private String content;
	
	/*
	 * IMAGE	이미지
	 *  TEXT	텍스트
	 */
	//O
	private String detailType;
	
	private CoupangContentsRequestDto contents;
	
	@Builder
	public CoupangDetailsRequestDto(String content,String detailType,CoupangContentsRequestDto contents){
		this.content 			= content;
		this.detailType			= detailType;
		this.contents			= contents;
	}
}
