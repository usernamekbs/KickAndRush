package com.shop.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shop.Coupang;
import com.shop.CoupangItems;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CoupangItemsRequestDto {
	
	//O
	private String itemName;
	//O
	private int originalPrice;
	//O
	private int salePrice;
	//O
	private int maximumBuyCount;
	//O
	private int maximumBuyForPerson;
	//O
	private int maximumBuyForPersonPeriod;
	//O
	private int outboundShippingTimeDay;
	//O
	private int unitCount;
	//O
	private String adultOnly;
	//O
	private String taxType;
	//O
	private String parallelImported;
	//O
	private String overseasPurchased;
	//O
	private Boolean pccNeeded;
	
	private List<String> searchTags = new ArrayList<>();
	
	private Coupang coupang;

	@Builder
	public CoupangItemsRequestDto(String itemName,int originalPrice,int salePrice,int maximumBuyCount,int maximumBuyForPerson,
			int maximumBuyForPersonPeriod,int outboundShippingTimeDay,int unitCount,String adultOnly,
			String taxType,String parallelImported,String overseasPurchased,boolean pccNeeded,List<String>searchTags
			,Coupang coupang
			) { 
//		this.id							= item.getId();
		this.itemName 					= itemName;
		this.originalPrice				= originalPrice;
		this.salePrice					= salePrice;
		this.maximumBuyCount			= maximumBuyCount;
		this.maximumBuyForPerson		= maximumBuyForPerson;
		this.maximumBuyForPersonPeriod	= maximumBuyForPersonPeriod;
		this.outboundShippingTimeDay 	= outboundShippingTimeDay;
		this.unitCount					= unitCount;
		this.adultOnly					= adultOnly;
		this.taxType					= taxType;
		this.parallelImported			= parallelImported;
		this.overseasPurchased			= overseasPurchased;
		this.pccNeeded					= pccNeeded;
		this.coupang					= coupang;
		
	}

	public CoupangItems toEntity() {
        return CoupangItems.builder()
    			.itemName(itemName)
    			.originalPrice(originalPrice)
    			.salePrice(salePrice)
    			.maximumBuyCount(maximumBuyCount)
    			.maximumBuyForPerson(maximumBuyForPerson)
    			.maximumBuyForPersonPeriod(maximumBuyForPersonPeriod)
    			.outboundShippingTimeDay(outboundShippingTimeDay)
    			.unitCount(unitCount)
    			.adultOnly(adultOnly)
    			.taxType(taxType)
    			.parallelImported(parallelImported)
    			.overseasPurchased(overseasPurchased)
    			.pccNeeded(pccNeeded)
    			.coupang(coupang)
    			.build();
    }
	
}
