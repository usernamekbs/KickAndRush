package com.kick.oauth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Service
@Transactional
public class OauthService {
	 private static final Logger logger = LoggerFactory.getLogger(OauthService.class);
	private final OAuthConfig naverOAuthConfig;
	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;  
	
	public ResponseEntity<LoginDto> getOAuthNaverLogin(String code, String state) throws JsonMappingException, JsonProcessingException {
		
		try {
	        ResponseEntity<TokenDto> naverToken = naverOAuthConfig.getNaverOauthLoginCallback(code,state);
	        
	        if (naverToken.getStatusCode() == HttpStatus.OK) {
	            Token token= new Token(naverToken.getBody().getAccessToken(),naverToken.getBody().getRefreshToken(),naverToken.getBody().getTokenType(),naverToken.getBody().getExpiresIn());
	            tokenRepository.save(token);
	            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	            ResponseEntity<UserResponseDto> naverUserDetails = naverOAuthConfig.getNaverOauthUserDetail(token.getAccessToken());
	            logger.info("Naver user details retrieved: {}", naverUserDetails);
	            User detail= new User(naverUserDetails.getBody().getResponse(),ERole.USER);
	            String email = detail.getEmail();
	            System.out.println(email);
	            User existingUser = userRepository.findByEmail(email);
	            System.out.println(naverUserDetails.getBody().getResponse());
	            if (existingUser != null) {
	            	LoginDto loginDto = new LoginDto(
	            			existingUser.getId(),
	            			naverUserDetails.getBody().getResponse().getId(),
		                    naverUserDetails.getBody().getResponse().getName(),
		                    naverUserDetails.getBody().getResponse().getEmail(),
		                    naverToken.getBody().getAccessToken(),
		                    naverToken.getBody().getExpiresIn(),
		                    existingUser.getRole()
                    );
	                return ResponseEntity.ok(loginDto);
	            } else {
	            	userRepository.save(detail);
	                existingUser = userRepository.findByEmail(email);
	                userRepository.save(detail);
                    existingUser = userRepository.findByEmail(email);
                    LoginDto loginDto = new LoginDto(
	                		existingUser.getId(),
	            			naverUserDetails.getBody().getResponse().getId(),
		                    naverUserDetails.getBody().getResponse().getName(),
		                    naverUserDetails.getBody().getResponse().getEmail(),
		                    naverToken.getBody().getAccessToken(),
		                    naverToken.getBody().getExpiresIn(),
		                    existingUser.getRole()
                    );
                    return ResponseEntity.ok(loginDto);
	            }

	           
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	@Transactional(readOnly=true)
	public OauthViewDto userView(Long id) {
		User naverViewDto= userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다 =" +id));
		return new OauthViewDto(naverViewDto.getId(),naverViewDto.getName(),naverViewDto.getEmail(),naverViewDto.getRole());
	}
	
	@Transactional(readOnly=true)
	public Page<UserDto> userList(Pageable pageable,
			String searchText,String searchType) {
		return userRepository.findAllList(pageable,searchText,searchType);
	
	}
	
	public void userDelete(Long id) {
		userRepository.deleteById(id);
	}
	
}
