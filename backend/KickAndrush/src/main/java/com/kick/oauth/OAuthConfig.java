package com.kick.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class OAuthConfig {
	
	@Value("${oauth.naver.client-id}")
    private String clientId;

    @Value("${oauth.naver.client-secret}")
    private String clientSecret;

    @Value("${oauth.naver.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.naver.url}")
    private String url;
    
    @Value("${oauth.naver.token}")
    private String tokenUrl;

    @Value("${oauth.naver.user-detail}")
    private String userDetail;
    
    public ResponseEntity<String> getNaverOauthLoginRedirect() {
        String redirectUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .build().toUriString();
    		
    	return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
    }

	public ResponseEntity<TokenDto> getNaverOauthLoginCallback(String code,String state) throws JsonMappingException, JsonProcessingException {
		 		RestTemplate restTemplate = new RestTemplate();
			    HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			    MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
			    map.add("client_id", clientId);
			    map.add("client_secret", clientSecret);
			    map.add("redirect_uri", redirectUri);
			    map.add("code", code);
			    map.add("state", state);
			    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			    ResponseEntity<String> token = restTemplate.postForEntity(tokenUrl, request, String.class);
			    String responseBody = token.getBody();

			    ObjectMapper objectMapper = new ObjectMapper();
			    TokenDto naverToken = objectMapper.readValue(responseBody,TokenDto.class);
		    return ResponseEntity.ok(naverToken);
	}

	public ResponseEntity<UserResponseDto> getNaverOauthUserDetail(String accessToken) throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
        		userDetail,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody();
            UserResponseDto naverUserDetail = objectMapper.readValue(responseBody, UserResponseDto.class);
            
            return ResponseEntity.ok(naverUserDetail);
        }
        
        return null;
	}
	
}