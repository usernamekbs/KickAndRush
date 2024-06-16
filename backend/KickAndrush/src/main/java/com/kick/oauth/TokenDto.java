package com.kick.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {

	@JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("error") 
    private String error;

    @JsonProperty("error_description") 
    private String error_description;
    
    public TokenDto(String accessToken, String refreshToken, String expiresIn, String tokenType) {
    	this.accessToken = accessToken;
    	this.refreshToken = refreshToken;
    	this.expiresIn = expiresIn;
    	this.tokenType = tokenType;

    }
}
