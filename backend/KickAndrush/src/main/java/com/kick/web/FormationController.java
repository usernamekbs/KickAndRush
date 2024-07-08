package com.kick.web;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.entity.Player;
import com.kick.service.FormationService;
import com.kick.web.dto.formation.FormationRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/formation")
@RestController
public class FormationController {

	private final FormationService formationService;
	
	@PostMapping("/create")
	public void formationSave(
			@RequestBody FormationRequest formationRequest
			) {
		formationService.formationSave(formationRequest);
	}
}
