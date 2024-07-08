package com.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
public class ProductAttributes {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attributes_id")
	private Long id;
	
	private String optionType; //옵션
	
	private String optionName; //옵션명
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="products_id")
	private Product product;
	
}
