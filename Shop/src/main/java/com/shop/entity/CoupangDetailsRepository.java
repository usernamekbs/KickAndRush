package com.shop.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.CoupangDetails;
import com.shop.dto.CoupangDetailsRequestDto;

public interface CoupangDetailsRepository extends JpaRepository<CoupangDetails, Long>{

}
