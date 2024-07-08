package com.kick.web.dto.formation;

import java.util.ArrayList;
import java.util.List;

import com.kick.entity.Player;

import lombok.Getter;

@Getter
public class FormationRequest {
	private String teamName;
	private String headCoach;
	private List<Player> players =new ArrayList<>();
}
