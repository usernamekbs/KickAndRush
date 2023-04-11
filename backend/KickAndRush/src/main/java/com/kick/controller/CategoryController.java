package com.kick.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.entity.Category;
import com.kick.model.category.CategoryDto;
import com.kick.model.category.CategoryRequestDto;
import com.kick.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("/list") 
	public List<Category> list() {
		return categoryService.findByCategory();
	} 

	@PostMapping("/create")
	public CategoryDto cretae(@RequestBody CategoryRequestDto requestDto) {
		return categoryService.create(requestDto);
	}
	
	@DeleteMapping("/delete/{ctg_cno}")
	public CategoryDto delete(@PathVariable Long ctg_cno) {
		return categoryService.delete(ctg_cno);
	}

	@PatchMapping("/update")
	public CategoryDto update(@RequestBody CategoryRequestDto requestDto) {
		return categoryService.update(requestDto);
	}

}