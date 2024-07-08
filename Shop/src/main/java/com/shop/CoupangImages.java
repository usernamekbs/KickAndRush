package com.shop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CoupangImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSeq")
	@SequenceGenerator(name = "imageSeq", sequenceName = "coupang_images_id_seq", allocationSize = 1)
	private Long id;  
	
	private int imageOrder;
	
	private String imageType;
	
	private String cdnPath;
	
	private String vendorPath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="items_id")
	private CoupangItems coupangItems;
	
}
