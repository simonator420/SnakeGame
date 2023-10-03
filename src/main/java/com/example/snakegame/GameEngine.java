package com.example.snakegame;

import javafx.stage.Stage;

public class GameEngine {
    Graphics graphics = new Graphics();

    public void startGame(Stage primaryStage){
        graphics.drawMainMenu(primaryStage);
        graphics.drawGameScene(primaryStage);
    }
}
