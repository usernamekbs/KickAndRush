package com.kick.oauth;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class OauthController {
	
	private final OauthService naverOAuthService;
	private final OAuthConfig naverOauthConfig;
	
    @GetMapping("/login")
    public ResponseEntity<String> getOAuthNaverLogin() {
    	return naverOauthConfig.getNaverOauthLoginRedirect();
	}

    @GetMapping("/callback")
    public ResponseEntity<LoginDto> callback(@RequestParam("code") String code, @RequestParam("state") String state) throws JsonMappingException, JsonProcessingException {
    	ResponseEntity<LoginDto> naverUser= naverOAuthService.getOAuthNaverLogin(code,state);
    	return ResponseEntity.ok(naverUser.getBody());
    }
    
    @GetMapping("/view/{id}")
	public OauthViewDto userView(@PathVariable Long id){
		return naverOAuthService.userView(id);
	}
    
    @GetMapping("/list")
	public Page<UserDto> userList(Pageable pageable,
			@RequestParam(value="searchText", required=false) String searchText,
			@RequestParam(value="searchType", required=false) String searchType
			){
		return naverOAuthService.userList(pageable,searchText,searchType);
	}
    
    @DeleteMapping("/delete/{id}")
    public void userDelete(@PathVariable Long id) {
    	naverOAuthService.userDelete(id);
	}
    
    @PutMapping("/update/{id}")
    public void userUpdate(@PathVariable Long id) {
//    	naverOAuthService.userDelete(id);
	}
}
