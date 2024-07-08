package com.kick.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.entity.Player;
import com.kick.entity.PlayerDto;
import com.kick.service.PlayerService;
import com.kick.web.dto.player.RequestPlayerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class PlayerController {

	private final PlayerService playerService;
	
//	@PostMapping("/create") 
//	public List<Player> playerSave(@Valid @RequestBody List<RequestPlayerDto> requestPlayerDto){
//		return playerService.playerSave(requestPlayerDto);
//	}
}
