package com.kick.web.dto.player;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestPlayerDto {
	
	private Long id;
//	@NotNull(message="해당 선수에 이름을 입력해주세요")
//	@Size(min=1,message="이름에 1글자 이상 입력해주세요")
	private String playerName;//선수이름
//	@NotNull(message="해당 선수에 이름을 입력해주세요")
//	@Size(min=1,message="등번호에 1이상 입력해주세요")
	private int playerNumber;//등번호
	private String teamName;
	//좌표값 선수 위치 좌표값임 건드리지마셈 
//	@NotNull(message="좌표값이 없습니다.")
	private int playerLeft;
//	@NotNull(message="좌표값이 없습니다.")
	private int playerTop;
	
//	private String team;//보류 이거 하려면 선수 정보를 다읽어와야함.. 나는 직접입력할꺼라 굳이 필요없을거같음..
//	private Formation formation;
	
	@JsonCreator
    public RequestPlayerDto(@JsonProperty("id")Long id,
    						@JsonProperty("playerName") String playerName,
                            @JsonProperty("playerNumber") int playerNumber,
                            @JsonProperty("teamName") String teamName,
                            @JsonProperty("playerLeft") int playerLeft,
                            @JsonProperty("playerTop") int playerTop) {
		this.id = id;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.teamName = teamName;
        this.playerLeft = playerLeft;
        this.playerTop = playerTop;
    }
}
