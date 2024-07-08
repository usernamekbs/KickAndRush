package com.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Coupang;
import com.shop.CoupangItems;
import com.shop.common.ECoupangSend;
import com.shop.common.HmacProductCreation;
import com.shop.common.JsonUtil;
import com.shop.dto.CoupangDto;
import com.shop.dto.CoupangFlatDto;
import com.shop.dto.CoupangItemsDto;
import com.shop.entity.CoupangAttributesRepository;
import com.shop.entity.CoupangImagesRepository;
import com.shop.entity.CoupangItemsRepository;
import com.shop.entity.CoupangRepository;

@Transactional
@Service
public class CoupangService {
	//쿠팡
	@Autowired CoupangRepository coupangRepository; 
	@Autowired CoupangItemsRepository coupangItemsRepository;
	@Autowired CoupangImagesRepository coupangImagesRepository;
	@Autowired CoupangAttributesRepository coupangAttributesRepository;
	@Autowired HmacProductCreation hmacProductCreation;
	
	
	//필없음 테스트 끝
//	@Transactional(readOnly = true)
//	public CoupangFlatDto coupangDetail(){
//		//408 시작 
//		//옵션을 기준으로 데이터를 생성했고 MANYTOONE LAZY를 사용하면 N+1이 생성 됨 해당 문제를 
//		//해결하려면 FETCH JOIN 사용 + BATCH SIZE 사용
//		//하지만 fetch join을 사용할수없음 페이징을 사용시에 setfirst..maxsize.. memory 에러가 나옴 
//		//해당 문제 때문에 fetchjoin을 사용하지않고 모든 entity를 조인하고 pagerequest를 사용하기로함
//		//쿠팡은 옵션을 기준으로 상세이미지가 생성이 되기 때문에 만약 첫번째 옵션이 100개고 두번째 옵션이 2개라면 총 200개의 옵션이 들어감
//		//쿠팡 json파일을 보면 옵션이 최대 200개 까지만 허용을 하는데 200개 기준으로 모두 꽉채워넣음
//		CoupangFlatDto coupang=coupangRepository.findByCoupang((long)408); 
//		
////		List<CoupangItems> coupangItems= coupangItemsRepository.findAllByCoupang(coupang.getId(),PageRequest.of(0,200));   
//		return coupang; 
//	}

//	firstResult/maxResults specified with collection fetch; applying in memory 해결
	@Transactional
	public Page<CoupangDto>  getCoupangDto(){
		Pageable pageable = PageRequest.of(13, 10);
        
		Page<Coupang> coupangPage = coupangRepository.findAll(pageable);
        
        // ENTITYGRAPH안써도 MEMORY문제 해결가능함 아래처럼 해결함 난 
        //map은 결과를 반환하는데 있어서 foreach를 사용함
        //무조건 ITEMS를 기준으로 UPDATE를 쳐라 COUPANG으로 치지마
		//영속성 컨텍스트 사용중에 update 하지마셈
        Pageable itemLimit = PageRequest.of(0, 200);
        List<Long> coupangIds = new ArrayList<>();
        List<Long> coupangItemIds = new ArrayList<>();
        List<CoupangDto> dtoList = coupangPage.getContent().stream()
							        .map(coupang -> {
							        	if(coupang.getItems() != null && !coupang.getItems().isEmpty()) {
							                List<CoupangItems> coupangItems = coupangItemsRepository.findAllByCoupangId(coupang.getId(), itemLimit);
							                
							                coupangIds.add(coupang.getId());
							                for(CoupangItems ci : coupangItems) {
							                    coupangItemIds.add(ci.getId());
							                }
							                
							                return new CoupangDto(coupang, coupangItems);
							            }
							            return new CoupangDto(coupang, Collections.emptyList());
							        })
							        .collect(Collectors.toList());
        	
        //아니 버크update 하려면 이런식으로 해야됨 ; 
        //왜냐면 조회중에 update를 하면 영속성컨텍스트 때문에 조회됐을때 영속성 컨텍스트에 있는 데이터들이 날라감 
        // 그래서 id값을 빼서 bulkupdate를 해야함.
        //그리고 clearAutomatically = true 영속성 컨텍스트 초기화 끝. 
    	for(Long id : coupangIds) {
    		
    		for(Long itemId : coupangItemIds) {
    			if(coupangItemIds!=null && itemId != 0)
    			coupangItemsRepository.bulkUpdate(id, itemId);
    		}
    	}
    	
	    for (CoupangDto dto : dtoList) {
	    	if (dto.getItems() != null && !dto.getItems().isEmpty()) {
	            String json = JsonUtil.convertListToJson(dto);
	            hmacProductCreation.sendDataToCoupang(json);
	            try {
	                TimeUnit.SECONDS.sleep(10); 
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }
	    }
	    System.out.println("끝");
        return new PageImpl<>(dtoList, pageable, coupangPage.getTotalElements());

	}

}
 