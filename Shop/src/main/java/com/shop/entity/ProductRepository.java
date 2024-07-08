package com.shop.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>{

//	@Query("SELECT count(title) FROM product p where p.title=:title")
//	boolean existsByTitle(String title);
}
