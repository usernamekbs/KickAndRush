package com.kick.web.dto.player;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

	private Long id;
	private String playerName;//선수이름
	private int playerNumber;//등번호
	private String teamName;
//	private String team;//보류 이거 하려면 선수 정보를 다읽어와야함.. 나는 직접입력할꺼라 굳이 필요없을거같음..
//	private Formation formation;
	
	
}
