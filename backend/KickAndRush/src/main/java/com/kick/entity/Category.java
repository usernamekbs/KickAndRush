package com.kick.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ctg_cno;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String url;

	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ctg_cno", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Category> categorys;
	
	public static Category createCategory(String title,String url) {
		Category category = new Category();
		category.title = title;
		category.url = url;
		
		return category;
	}
	
	public void update(String title,String url) {
		this.title = title;
		this.url = url;
		
	}
}
 