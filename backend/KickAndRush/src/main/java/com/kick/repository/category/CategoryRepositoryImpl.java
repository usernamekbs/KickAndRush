package com.kick.repository.category;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kick.entity.Category;
import lombok.RequiredArgsConstructor;
import static com.kick.entity.QCategory.category;
 
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CustomCategoryRepository{

	private final JPAQueryFactory queryFactory;


	@Override
	public List<Category> findByCategory() {
		List<Category> results=queryFactory.selectFrom(category)
				.orderBy(category.ctg_cno.asc())
				.fetch();
		return results;
	}  

} 
 