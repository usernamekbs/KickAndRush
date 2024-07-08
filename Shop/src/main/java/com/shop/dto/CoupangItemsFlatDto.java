package com.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoupangItemsFlatDto {
	private String itemName;
	private int originalPrice;
	private int salePrice;
	private int maximumBuyCount;
	private int maximumBuyForPerson;
	private int maximumBuyForPersonPeriod;
	private int outboundShippingTimeDay;
	private int unitCount;
	private String adultOnly;
	private String taxType;
	private String parallelImported;
	private String overseasPurchased;
	private Boolean pccNeeded;
}
