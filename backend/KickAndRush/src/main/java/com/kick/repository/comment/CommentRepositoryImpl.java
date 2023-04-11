package com.kick.repository.comment;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kick.entity.Comment;
import lombok.RequiredArgsConstructor;
import static com.kick.entity.QComment.comment;
 
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CustomCommentRepository{

	private final JPAQueryFactory queryFactory;

	@Override  
	public List<Comment> findByComment(long pno) {
		List<Comment> results=queryFactory.selectFrom(comment) 
				.leftJoin(comment)
				.fetchJoin() 
				.where(
						comment.post.pno.eq(pno) 
				)
				.orderBy(comment.cno.asc().nullsFirst())
				.fetch();
		return results;
	}  

} 
