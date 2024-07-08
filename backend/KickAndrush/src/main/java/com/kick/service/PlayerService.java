package com.kick.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.kick.dto.gallery.RequestGalleryDto;
import com.kick.entity.Player;
import com.kick.entity.PlayerDto;
import com.kick.repository.player.PlayerRepository;
import com.kick.web.dto.player.RequestPlayerDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerService {

	private final PlayerRepository playerRepository;

//	public List<Player> playerSave(@Valid List<RequestPlayerDto> requestPlayerDtos) {
//				List<Player> players = requestPlayerDtos.stream()
//	                .map(requestPlayerDto -> {
//	                return Player.builder()
//	                        .playerName(requestPlayerDto.getPlayerName())
//	                        .playerNumber(requestPlayerDto.getPlayerNumber())
//	                        .teamName(requestPlayerDto.getTeamName())
//	                        .formation(Formation.FORMATION14)
//	                        .playerLeft(requestPlayerDto.getPlayerLeft())
//	                        .playerTop(requestPlayerDto.getPlayerTop())
//	                        .build();
//	                })
//	                .collect(Collectors.toList());
//
//	        List<Player> savedPlayers = playerRepository.saveAll(players);
//		return savedPlayers;
//	}
	
	
//	public List<PlayerDto> playerList(){
//		playerRepository.findById(1);
//		return null;
//		
//	}
	
	
}
