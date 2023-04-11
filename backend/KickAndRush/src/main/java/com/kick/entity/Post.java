package com.kick.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor
@Table(name="post")
public class Post {
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

 	@Column(nullable = true, name="title")
 	private String title;
 
    @Column(columnDefinition = "TEXT", nullable = false, name="content")
    private String content;
    
    @Column
    private String writer;
     
    @Column
    private int view;
     
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent")
//    private Post parent;
//
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<Post> children = new ArrayList<>();
     
    public static Post createPost(Long pno,String title,String content, String writer) {
    	Post post = new Post();
    	post.pno = pno;
    	post.title = title;
    	post.content = content;
    	post.writer = writer;   	
    	return post;
    }
 
    public void addView() {
        this.view++;
    }

	public void update(String title, String content,String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
    
}
