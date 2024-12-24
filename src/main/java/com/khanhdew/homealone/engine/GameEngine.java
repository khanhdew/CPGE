package com.khanhdew.homealone.engine;

import com.khanhdew.homealone.entity.BaseEntity;
import com.khanhdew.homealone.entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class GameEngine {
    private List<BaseEntity> entities;
    private GameState state;
    private Player player;

    public GameEngine(){
        entities = new ArrayList<>();
        state = new GameState();
        player = new Player(100,100,50,50);
    }

    public void update(){
        for(BaseEntity entity: entities){
            entity.update();
        }
        player.update();
    }
}
