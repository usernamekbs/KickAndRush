package com.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.CoupangDto;
import com.shop.dto.CoupangFlatDto;
import com.shop.service.CoupangService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/coupang")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class CoupangController {
	
	private final CoupangService service;
	
//	@GetMapping("/detail")
//	public CoupangFlatDto coupangDetail(){
//		return service.coupangDetail();
//	}

	@GetMapping("/list")
	public Page<CoupangDto> coupangList(){
		return service.getCoupangDto();
	}

}

 