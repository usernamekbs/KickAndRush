package com.kick.repository.player;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kick.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

}
