package com.kick.web;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.service.CommentService;
import com.kick.web.dto.comment.CommentDto;
import com.kick.web.dto.comment.RequestCommentDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@PostMapping("/create")
	public CommentDto commentSave(@RequestBody @Valid RequestCommentDto requestCommentDto) {
		return commentService.commentSave(requestCommentDto);
	} 
	 
	@PutMapping("/update/{id}") 
	public CommentDto commentUpdate(@PathVariable("id") Long id,@RequestBody RequestCommentDto requestCommentDto) {
		return commentService.commentUpdate(id,requestCommentDto);
	}
	
	@DeleteMapping("/delete/{id}") 
	public String commentDelete(@PathVariable("id") Long id) {
		commentService.commentDelete(id);
		return "댓글이 삭제되었습니다";
	} 
}
