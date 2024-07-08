package com.shop.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shop.Coupang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoupangFlatDto {
	
	private Long id;
	
	private String sellerProductName;
	
	private String vendorId;

	private String saleStartedAt;

	private String saleEndedAt;

	private String displayProductName;
	
	private String deliveryMethod;
	//O
	private String deliveryCompanyCode;

	private String deliveryChargeType;
	
	private int deliveryCharge;
	
	private int freeShipOverAmount;
	
	private int deliveryChargeOnReturn;

	private String remoteAreaDeliverable;
	
	private String unionDeliveryType;
	
	private String returnCenterCode;
	
	private String returnChargeName;

	private String companyContactNumber;

	private String returnZipCode;

	private String returnAddress;

	private String returnAddressDetail;

	private int returnCharge;
	
	private int outboundShippingPlaceCode;

	private String vendorUserId;
	
	private Boolean requested;
	
	private List<CoupangItemsDto> items =new ArrayList<>();
	

	public CoupangFlatDto(Coupang coupang) {
		this.id					= coupang.getId();
		this.sellerProductName = coupang.getSellerProductName();
		this.vendorId = coupang.getVendorId();
		this.saleStartedAt = coupang.getSaleStartedAt();
		this.saleEndedAt = coupang.getSaleEndedAt();
		this.displayProductName = coupang.getDisplayProductName();
		this.deliveryMethod = coupang.getDeliveryMethod();
		this.deliveryCompanyCode = coupang.getDeliveryCompanyCode();
		this.deliveryChargeType = coupang.getDeliveryChargeType();
		this.deliveryCharge = coupang.getDeliveryCharge();
		this.freeShipOverAmount = coupang.getFreeShipOverAmount();
		this.deliveryChargeOnReturn = coupang.getDeliveryChargeOnReturn();
		this.remoteAreaDeliverable = coupang.getRemoteAreaDeliverable();
		this.unionDeliveryType = coupang.getUnionDeliveryType();
		this.returnCenterCode = coupang.getReturnCenterCode();
		this.returnChargeName = coupang.getReturnChargeName();
		this.companyContactNumber = coupang.getCompanyContactNumber();
		this.returnZipCode = coupang.getReturnZipCode();
		this.returnAddress = coupang.getReturnAddress();
		this.returnAddressDetail = coupang.getReturnAddressDetail();
		this.returnCharge = coupang.getReturnCharge();
		this.outboundShippingPlaceCode = coupang.getOutboundShippingPlaceCode();
		this.vendorUserId = coupang.getVendorUserId();
		this.requested = coupang.getRequested();
		this.items		= coupang.getItems().stream().map(CoupangItemsDto::new).collect(Collectors.toList());
//		this.items = coupang.getItems().stream().map(CoupangItemsDto::new).collect(Collectors.toList());
	}
}
