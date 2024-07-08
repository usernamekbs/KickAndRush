package com.shop.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.CoupangContents;
import com.shop.dto.CoupangContentsRequestDto;

public interface CoupangContentsRepository extends JpaRepository<CoupangContents, Long>{

}
