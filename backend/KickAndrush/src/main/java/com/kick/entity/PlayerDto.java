package com.kick.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
	
	private Long id;
	private String playerName;//선수이름
	private int playerNumber;//등번호
	private String teamName;
//	private String team;//보류 이거 하려면 선수 정보를 다읽어와야함.. 나는 직접입력할꺼라 굳이 필요없을거같음..
	private Formation formation;
	private int left;
	private int top;
	
	public PlayerDto(Player player) {
		this.id = player.getId();
//		this.playerName = player.getPlayerName();
//		this.playerNumber = player.getPlayerNumber();
//		this.teamName = player.getTeamName();
//		this.formation = player.getFormation();
		this.left = player.getPlayerLeft();
		this.top = player.getPlayerTop();
	}
	
}
