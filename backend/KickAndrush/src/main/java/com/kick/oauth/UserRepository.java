package com.kick.oauth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long>,CustomUserRepository{
	
	Page<UserDto> findAllList(Pageable pageable, String searchText, String searchType);

	User findByEmail(String email);
}
