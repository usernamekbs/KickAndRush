package com.shop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@DynamicUpdate
@DynamicInsert
public class CoupangItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
	@SequenceGenerator(name = "itemSeq", sequenceName = "coupang_items_id_seq", allocationSize = 1)
	private Long id; 

	private String itemName;

	private int originalPrice;

	private int salePrice;

	private int maximumBuyCount;

	private int maximumBuyForPerson;

	private int maximumBuyForPersonPeriod;

	private int outboundShippingTimeDay;

	private int unitCount;

	private String adultOnly;

	private String taxType;

	private String parallelImported;

	private String overseasPurchased;

	private Boolean pccNeeded;
	
	private List<String> searchTags = new ArrayList<>();

	@ColumnDefault("'N'")
	private String sendYn;
	
	@BatchSize(size = 1000)
    @OneToMany(mappedBy="coupangItems", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CoupangImages> images = new HashSet<>();

    @BatchSize(size = 1000)
    @OneToMany(mappedBy="coupangItems", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CoupangAttributes> attributes = new HashSet<>();

    @BatchSize(size = 1000)
    @OneToMany(mappedBy="coupangItems", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CoupangContents> contents = new HashSet<>();
     
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="coupang_id")
	private Coupang coupang;

	public void addImage(CoupangImages image) {
		this.images.add(image);
	}
	
	public void addAttribute(CoupangAttributes attribute) {
		this.attributes.add(attribute);
	}

	public void addContent(CoupangContents content) {
		this.contents.add(content);
	}

	public void addSearchTag(String tag) {
		this.searchTags.add(tag);
	}
	
	public void updateSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	
}
