package com.kick.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.Formation;
import com.kick.entity.Player;
import com.kick.repository.formation.FormationRepository;
import com.kick.repository.player.PlayerRepository;
import com.kick.web.dto.formation.FormationRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FormationService {
	
	private final FormationRepository formationRepository;
	private final PlayerRepository playerRepository;
	
	public void formationSave(FormationRequest formationRequest) {
		Formation formation = Formation.builder()
                .teamName(formationRequest.getTeamName())
                .headCoach(formationRequest.getHeadCoach())
                .build();
		
		formationRepository.save(formation);
		
		List<Player> players = formationRequest.getPlayers()
									.stream()
									.map(playerRequest-> 
											Player.builder()
											.playerLeft(playerRequest.getPlayerLeft())
											.playerTop(playerRequest.getPlayerTop())
											.formation(playerRequest.getFormation())
									.build())
									.collect(Collectors.toList());
		
		playerRepository.saveAll(players);
		
	}
	

}
