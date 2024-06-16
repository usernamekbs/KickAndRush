package com.kick.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

import com.kick.common.BaseTimeEntity;
import com.kick.oauth.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@NoArgsConstructor
@Entity
@Table(name = "posts")
@AllArgsConstructor
public class Post extends BaseTimeEntity{
	 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private Long id; 
	 
	private String title;
	
	private String content;  
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	private User user; 
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>(); 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categories_id")
	private Category categories;
	 
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL) 
	private List<Image> images = new ArrayList<>();
	
	@Column
	private int views;
	 
	@ColumnDefault("0")
	private Long commentCnt;
	
	@ColumnDefault("0")
	private Long imageCnt;

	@Formula("(select count(*) from likes l where l.post_id = post_id)")
    private int likeCnt;

	//조회수 증가 
	public void updateViews() {
		this.views++;
	}
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		//this.writer = writer;,String writer
	}

	public Post(String title, String content,User user, Category category) {
		this.title 		= title;
		this.user		= user;
		this.content 	= content;
		this.categories = category;
	}

	
}