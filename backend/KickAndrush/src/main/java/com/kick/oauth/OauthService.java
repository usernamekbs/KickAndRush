package com.kick.oauth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class OauthService {
	
	private final OAuthConfig naverOAuthConfig;
	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;  
	
	public ResponseEntity<LoginDto> getOAuthNaverLogin(String code, String state) throws JsonMappingException, JsonProcessingException {
		
		try {
	        ResponseEntity<TokenDto> naverToken = naverOAuthConfig.getNaverOauthLoginCallback(code,state);
	        
	        // 인증 토큰이 유효한지 확인
	        if (naverToken.getStatusCode() == HttpStatus.OK) {
	            Token token= new Token(naverToken.getBody().getAccessToken(),naverToken.getBody().getRefreshToken(),naverToken.getBody().getTokenType(),naverToken.getBody().getExpiresIn());
	            tokenRepository.save(token);
	            
	            ResponseEntity<UserResponseDto> naverUserDetails = naverOAuthConfig.getNaverOauthUserDetail(token.getAccessToken());
	            User detail= new User(naverUserDetails.getBody().getResponse(),ERole.USER);
	            String email = detail.getEmail();
	            User existingUser = userRepository.findByEmail(email);

	            // 사용자가 이미 존재하는지 확인 후 처리
	            if (existingUser != null) {
	                // 이미 존재하는 사용자이므로 로그인
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
	public List<UserDto> userList() {
		List<User> categories = userRepository.findAll();
		return categories.stream().map(UserDto::new).collect(Collectors.toList());
	
	}
	
	public void userDelete(Long id) {
		userRepository.deleteById(id);
	}
	
}
