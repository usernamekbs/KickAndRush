package com.kick.model.comment;

import java.util.ArrayList;
import java.util.List;

import com.kick.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto{
	private Long cno;
	private Long pno;
	private Long parent;
    private String content;
    private String writer;
    private List<CommentDto> children = new ArrayList<>();
    private String message;
    
    public CommentDto(Long cno,Long pno, String content, String writer, Long parent,String message) {
        this.cno = cno;
    	this.pno = pno;
        this.content = content;
        this.writer = writer;
        this.parent = parent;
        this.message = message;
    }
    
    public static CommentDto convertCommentToDto(Comment comment,String message) {
        return comment.getParent() != null ?
        		new CommentDto(comment.getCno(), comment.getPost().getPno(), comment.getContent(), comment.getWriter(),comment.getParent().getCno(),message):
                new CommentDto(comment.getCno(), comment.getPost().getPno(), comment.getContent(), comment.getWriter(),null,message);
    }
}

