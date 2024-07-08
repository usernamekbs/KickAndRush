package com.kick.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Formation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private String headCoach;

    //단방향으로 잡을때는 mappedBy를 설정하지않음. mappedBy="formation" 양방향으로 잡을때 
    @BatchSize(size=11)
    @OneToMany(mappedBy="formation",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

//    public void removePlayer(Player player) {
//        players.remove(player);
//    }
    
    
    
}
