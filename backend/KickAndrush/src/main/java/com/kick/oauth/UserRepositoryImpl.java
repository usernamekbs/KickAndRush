package com.kick.oauth;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.kick.entity.QPost.post;
import static com.kick.oauth.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements CustomUserRepository{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<UserDto> findAllList(Pageable pageable,String searchText,String searchType) {
			List<UserDto> result = queryFactory
							.select(Projections.fields(UserDto.class,
			                        user.naverId.as("id"),
			                        user.id.as("naverId"),
			                        user.name.as("name"),
			                        user.email.as("email"),
			                        user.role.as("role")
			                ))
							.from(user)
							.where(
								(searchType.equals("EMAIL") 	? emailEq(searchText) : null),
								(searchType.equals("NAME") 		? nameEq(searchText) : null))
							.offset(pageable.getOffset())
							.limit(pageable.getPageSize())
							.orderBy(user.id.desc())
							
							.fetch();
			
			Long count = queryFactory
					.select(user.count())
					.from(user)
					.where(
					)
					.fetchOne();
		return new PageImpl<>(result, pageable, count);
	}

	private BooleanExpression emailEq(String emailCond) {
		return emailCond != null ? user.email.contains(emailCond) : null;
	}

	private BooleanExpression nameEq(String nameCond) {
		return nameCond != null ? user.name.contains(nameCond) : null;
	}
	
}
