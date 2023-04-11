package com.kick.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Post post;
    
    @Column(nullable = false)
    private String content;

    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();
    
    public static Comment createComment(Post post, String content, String writer, Comment parent) {
        Comment comment = new Comment();
        comment.post    = post;
        comment.content = content;
        comment.writer  = writer;
        comment.parent  = parent;
        return comment;
    }
    
    public void update(String content) {
    	this.content = content;
    }

}