package com.khanhdew.homealone.view;

import com.khanhdew.homealone.config.GameConfiguration;
import javafx.scene.layout.BorderPane;
public class MainPane extends BorderPane {
    private final GameConfiguration configuration = GameConfiguration.getInstance();
    public MainPane(){
        setPrefSize(configuration.getWINDOW_WIDTH(), configuration.getWINDOW_HEIGHT());
    }


}
