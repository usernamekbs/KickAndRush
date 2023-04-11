package com.kick.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.kick.entity.Category;
import com.kick.entity.Post;
import com.kick.model.category.CategoryDto;
import com.kick.model.category.CategoryRequestDto;
import com.kick.model.post.PostViewDto;
import com.kick.repository.category.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public List<Category> findByCategory() {
		return categoryRepository.findByCategory();
	}

	@Transactional
	public CategoryDto create(CategoryRequestDto requestDto) {
		Category category = categoryRepository.save(
                Category.createCategory(
                		requestDto.getTitle(),
                		requestDto.getUrl()
        		)
        );
	
		return CategoryDto.convertCategoryToDto(category);
	
	}

	@Transactional
	public CategoryDto delete(Long ctg_cno) {
		CategoryDto dto = new CategoryDto();
		categoryRepository.deleteById(ctg_cno);
		return dto;
	}

	@Transactional
	public CategoryDto update(CategoryRequestDto categoryRequestDto) {
		Category category  = categoryRepository.findById(categoryRequestDto.getCtg_cno())
				.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. ctg_cno =" +categoryRequestDto.getCtg_cno()));
		
		 category.update(categoryRequestDto.getTitle(),categoryRequestDto.getUrl());
		 return CategoryDto.convertCategoryToDto(category);
	}


}
