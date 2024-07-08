package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shop.CoupangAttributes;
import com.shop.CoupangItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CoupangAttributesDto {
	
	private String attributeTypeName;
	
	private String attributeValueName;
	
	@JsonBackReference //데이터가 많은 nested 연관관계 참조시에 references 에러 뜸  
	private CoupangItems coupangItems;
	
	public CoupangAttributesDto(CoupangAttributes attributes) {
		this.attributeTypeName 	= attributes.getAttributeTypeName();
		this.attributeValueName	= attributes.getAttributeValueName();
		this.coupangItems		= attributes.getCoupangItems();
	}

}
