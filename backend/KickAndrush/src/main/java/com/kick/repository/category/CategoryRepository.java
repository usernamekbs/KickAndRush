package com.kick.repository.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kick.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
