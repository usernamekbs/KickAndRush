package com.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoupangContentsRequestDto {

	private String contentsType;

	private CoupangItemsRequestDto items;

	@Builder
	public CoupangContentsRequestDto(String contentsType, CoupangItemsRequestDto items
			) { 
		this.contentsType 				= contentsType;
		this.items						= items;
	}

//	public CoupangContents toEntity() {
//        return CoupangContents.builder()
//    			.contentsType(contentsType)
//    			.coupangItems(items)
//    			.build();
//    }
}
