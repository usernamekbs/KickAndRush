package com.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoupangAttributesRequestDto {

	private String attributeTypeName;
	
	private String attributeValueName;
	
	private CoupangItemsRequestDto items;
	
	@Builder
	public CoupangAttributesRequestDto(String attributeTypeName,String attributeValueName,CoupangItemsRequestDto items) {
		this.attributeTypeName 	= attributeTypeName;
		this.attributeValueName	= attributeValueName;
		this.items		= items;
	}
	
}
