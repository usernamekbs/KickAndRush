package com.shop.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.boot.model.source.spi.PluralAttributeElementSource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shop.CoupangAttributes;
import com.shop.CoupangContents;
import com.shop.CoupangDetails;
import com.shop.CoupangItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CoupangItemsDto {
//	private Long id;
	
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
	
	private List<CoupangImagesDto> images = new ArrayList<>();

	//infinite recursion 해결 방법
	private List<CoupangAttributesDto> attributes= new ArrayList<>();
	
	private List<CoupangContentsDto> contents = new ArrayList<>();

	private List<String> searchTags = new ArrayList<>();
	
	public CoupangItemsDto(CoupangItems item) { 
//		this.id							= item.getId();
		this.itemName 					= item.getItemName();
		this.originalPrice				= item.getOriginalPrice();
		this.salePrice					= item.getSalePrice();
		this.maximumBuyCount			= item.getMaximumBuyCount();
		this.maximumBuyForPerson		= item.getMaximumBuyForPerson();
		this.maximumBuyForPersonPeriod	= item.getMaximumBuyForPersonPeriod();
		this.outboundShippingTimeDay 	= item.getOutboundShippingTimeDay();
		this.unitCount					= item.getUnitCount();
		this.adultOnly					= item.getAdultOnly();
		this.taxType					= item.getTaxType();
		this.parallelImported			= item.getParallelImported();
		this.overseasPurchased			= item.getOverseasPurchased();
		this.pccNeeded					= item.getPccNeeded();
		this.images						= item.getImages().stream().map(CoupangImagesDto::new).collect(Collectors.toList());
		this.attributes 				= item.getAttributes().stream().map(CoupangAttributesDto::new).collect(Collectors.toList());
		this.contents					= item.getContents().stream().map(CoupangContentsDto::new).collect(Collectors.toList());
		searchTags.add("커버핏");
		searchTags.add("트라이코지");
		searchTags.add("갤럭시");
		searchTags.add("아이폰");
		searchTags.add("케이스");
		this.searchTags					= searchTags.stream().map(String::new).collect(Collectors.toList());
	}

	
}
