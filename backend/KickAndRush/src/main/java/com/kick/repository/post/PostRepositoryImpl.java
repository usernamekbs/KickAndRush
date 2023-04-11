package com.kick.repository.post;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.ObjectUtils;

import com.kick.model.post.PostDto;
import com.kick.model.post.PostRequestDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

import lombok.RequiredArgsConstructor;
 
import static com.kick.entity.QComment.comment;
import static com.kick.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements CustomPostRepository{

	private final JPAQueryFactory  queryFactory;
	
	@Override 
	public Page<PostDto> findByPost(String keyword,String option,PostRequestDto postDto,Pageable pageable) {

		List<PostDto> content = queryFactory.select(
				Projections.fields(PostDto.class,
                        post.pno,
                        post.title,
                        post.content,
                        post.writer,
                        post.view,
                        ExpressionUtils.as(
                            JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.post.pno.eq(post.pno))
                                , "comment_cnt")
				))
                .from(post)
                .where(
                		option.equals("Title") ? null : titleEq(keyword),
                		option.equals("Content") ? null : contentEq(keyword),
                		option.equals("Writer") ? null : writerEq(keyword)
                		)
                .orderBy(post.pno.desc())
				.offset(pageable.getOffset())
	        	.limit(pageable.getPageSize())
                .fetch();
		
		JPAQuery<PostDto> countQuery = queryFactory.select(
				Projections.fields(PostDto.class,
                        post.pno,
                        post.title,
                        post.content,
                        post.writer,
                        post.view,
                        ExpressionUtils.as(
                                JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(post.pno.eq(comment.post.pno))
                                , "commnet_cnt")	
				))
				.from(post)
                .where(
                		option.equals("Title") ? null : titleEq(keyword),
                		option.equals("Content") ? null : contentEq(keyword),
                		option.equals("Writer") ? null : writerEq(keyword)
                		);
		 
	  return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
		
	}

	
	
	private BooleanExpression titleEq(String title) { //제목 검색
		return ObjectUtils.isEmpty(title) ? null : post.title.contains(title);
	}

	private BooleanExpression contentEq(String content) { //내용 검색
		return ObjectUtils.isEmpty(content) ? null : post.content.contains(content);
	}

	private BooleanExpression writerEq(String writer) { //작성자 검색
		return ObjectUtils.isEmpty(writer) ? null : post.writer.contains(writer);
	}
}
