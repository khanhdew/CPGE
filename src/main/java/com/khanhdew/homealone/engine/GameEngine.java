package com.khanhdew.homealone.engine;

import com.khanhdew.homealone.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class GameEngine {
    private List<BaseEntity> entities;
    private GameState state;

    public GameEngine(){
        entities = new ArrayList<>();
        state = new GameState();
    }

    public void update(){
        for(BaseEntity entity: entities){
            entity.update();
        }
    }
}
