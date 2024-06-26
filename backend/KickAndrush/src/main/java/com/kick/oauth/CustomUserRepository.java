package com.kick.oauth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {
	
	Page<UserDto> findAllList(Pageable pageable, String searchText, String searchType);

	
}
