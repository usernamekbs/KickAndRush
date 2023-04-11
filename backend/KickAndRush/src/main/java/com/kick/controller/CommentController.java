package com.kick.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.model.comment.CommentDto;
import com.kick.model.comment.CommentRequestDto;
import com.kick.service.CommentService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@ApiOperation(value = "댓글 리스트", notes = "게시글 번호를 통해 댓글 리스트를 조회 한다.")
	@GetMapping("/list/{pno}")
	public List<CommentDto> list(@PathVariable Long pno) {
		return commentService.findByComment(pno);
	}

	
	@ApiOperation( value = "댓글 등록" , notes = "댓글을 등록 한다.")
	@PostMapping("/create") 
	public CommentDto create(@RequestBody CommentRequestDto requestDto) {
		return commentService.create(requestDto); 
	}
	

	@ApiOperation(value = "댓글 수정", notes = "게시글 번호를 통해 댓글을 수정 한다.")
	@PutMapping("/update")
	public CommentDto update(@RequestBody CommentRequestDto requestDto) {
		return commentService.update(requestDto);
	}

	@ApiOperation(value = "댓글 삭제", notes = "게시글 번호를 통해 댓글을 삭제 한다.")
	@DeleteMapping("/delete/{cno}/{pno}")
	public CommentDto delete(@PathVariable("cno") Long cno, @PathVariable("pno") Long pno) {
		return commentService.delete(cno, pno);
	}
}