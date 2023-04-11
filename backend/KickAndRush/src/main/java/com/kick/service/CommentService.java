package com.kick.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.Comment;
import com.kick.model.comment.CommentDto;
import com.kick.model.comment.CommentRequestDto;
import com.kick.repository.comment.CommentRepository;
import com.kick.repository.post.PostRepository;
import com.kick.common.CommentMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

		private final CommentRepository commentRepository;
		private final PostRepository postRepository;
		
		@Transactional(readOnly = true)
		public List<CommentDto> findByComment(Long pno) {
			return convertNestedStructure(commentRepository.findByComment(pno));
		}
		
		@Transactional
		public CommentDto update(CommentRequestDto requestDto) {
			Comment comment  = commentRepository.findById(requestDto.getCno())
				.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. cno =" +requestDto.getCno()));
			comment.update(requestDto.getContent());
			
			return CommentDto.convertCommentToDto(comment,CommentMessage.UPD_COMMENT.getContent());
		}

		@Transactional
		public CommentDto create(CommentRequestDto requestDto) {
			 Comment comment = commentRepository.save(
		                Comment.createComment(
		                        postRepository.findById(requestDto.getPno()).orElse(null),
		                        requestDto.getContent(),
		                		requestDto.getWriter(), 
		                        requestDto.getParent() != null ? commentRepository.findById(requestDto.getParent())
		                				.orElseThrow(() -> new IllegalArgumentException("해당 댓글의 부모가 없습니다. parent =" +requestDto.getParent())) : null)
		        );
			
			return CommentDto.convertCommentToDto(comment,CommentMessage.SAVE_COMMENT.getContent());
		}
		
		private List<CommentDto> convertNestedStructure(List<Comment> comments) {
			
		    List<CommentDto> result = new ArrayList<>();
	        Map<Long, CommentDto> map = new HashMap<>();
	        comments.stream().forEach(c -> {
	            CommentDto dto = CommentDto.convertCommentToDto(c,CommentMessage.LIST_COMMENT.getContent());
	            map.put(dto.getCno(), dto);
	            if(c.getParent() != null) { //자식 댓글
	            	map.get(c.getParent().getCno()).getChildren().add(dto);
	            }else { //부모 댓글
	            	result.add(dto);
	            };
	        });
	        
	        return result;
		}

		@Transactional
		public CommentDto delete(Long cno,Long pno) {
			Comment comment  = commentRepository.findById(cno)
					.orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. cno =" +cno));
			
			if(comment.getParent() == null) {
				commentRepository.deleteById(cno);
				return CommentDto.convertCommentToDto(comment,CommentMessage.DEL_COMMENT.getContent());
			}else {
				List<Comment> result = new ArrayList<>();
				result = commentRepository.findByComment(pno);

				result.stream().forEach(c->{
					if(c.getCno()==cno) {
						commentRepository.deleteById(cno);
					}
				});
				
				return CommentDto.convertCommentToDto(comment,CommentMessage.DEL_COMMENT.getContent());
			} 
		}
}
