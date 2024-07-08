package com.shop.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.CoupangImages;
import com.shop.CoupangItems;
import com.shop.dto.CoupangImagesRequestDto;

public interface CoupangImagesRepository extends JpaRepository<CoupangImages, Long>{

	@Query("SELECT i FROM CoupangImages i where i.id=:id")
	List<CoupangImages> findAll(@Param("id")long id);

}
