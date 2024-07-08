package com.shop.dto;

import com.shop.CoupangImages;
import com.shop.CoupangItems;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoupangImagesRequestDto {
	
	private int imageOrder;
	
	private String imageType;
	
//	private String cdnPath;
	
	private String vendorPath;
	
	private CoupangItemsRequestDto items;
	
	@Builder
	public CoupangImagesRequestDto(int imageOrder,
			String imageType,String vendorPath,CoupangItemsRequestDto items) {
		this.imageOrder 				= imageOrder;
		this.imageType  				= imageType;
		this.vendorPath 				= vendorPath;
		this.items						= items;
	}
	
//	public CoupangImages toEntity() {
//        return CoupangImages.builder()
//    			.imageOrder(imageOrder)
//    			.imageType(imageType)
//    			.vendorPath(vendorPath)
//    			.coupangItems(items)
//    			.build();
//    }
}
