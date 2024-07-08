package com.shop.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.CoupangAttributes;
import com.shop.dto.CoupangAttributesDto;
import com.shop.dto.CoupangAttributesRequestDto;

public interface CoupangAttributesRepository extends JpaRepository<CoupangAttributes, Long>{

}
