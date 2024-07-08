package com.kick.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kick.common.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Player extends BaseTimeEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerName; // 선수이름
    private int playerNumber; // 등번호
    private int playerLeft;
    private int playerTop;
//양방향으로 설정하고 싶으면 해당 변수 추가하면된다
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id")
    private Formation formation;
    
	public void update (int playerLeft,int playerTop) {
		this.playerLeft = playerLeft;
		this.playerTop = playerTop;
	}
}
