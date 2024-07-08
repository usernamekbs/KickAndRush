package com.shop;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class CoupangDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detailSeq")
	@SequenceGenerator(name = "detailSeq", sequenceName = "coupang_details_id_seq", allocationSize = 1)
	private Long id;  
	//O
	private String content;
	
	/*
	 * IMAGE	이미지
	 *  TEXT	텍스트
	 */
	//O
	private String detailType;
	
	@BatchSize(size=1000)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="coupang_contents_id")
	private CoupangContents coupangContents;
	
}
