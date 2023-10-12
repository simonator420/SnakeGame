package com.example.snakegame;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

public class Graphics {
    private static int WIDTH = 960;
    private static int HEIGHT = 720;
    private static int ROWS = 18;
    private static int COLUMNS = 18;
    private static int SQUARE_SIZE = 40;
    private Font gameAlternativeFont = Font.loadFont(getClass().getResourceAsStream("file:src/font/snake_font.otf"), 90);


    // done function
    public javafx.scene.control.Button getExitGameButton(){
        javafx.scene.control.Button exitButton = new javafx.scene.control.Button("EXIT");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
        exitButton.setTextFill(Color.web("WHITE"));
        exitButton.setStyle("-fx-background-color: \"FF474C\";");
        exitButton.setTranslateY(80);
        return exitButton;
    }

    // done function
    public Button getStartGameButton(){
        javafx.scene.control.Button startButton = new Button("START");
        startButton.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
        startButton.setTextFill(Color.web("WHITE"));
        startButton.setStyle("-fx-background-color: \"FF474C\";");
        startButton.setTranslateY(30);
        return startButton;
    }

    public Button getRestartButton(){
        Button restartButton = new Button("RESTART");
        restartButton.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
        restartButton.setTextFill(Color.web("WHITE"));
        restartButton.setStyle("-fx-background-color: \"FF474C\";");
        restartButton.setTranslateY(30);
        return restartButton;
    }

    public Label getGameName(){
        Label gameName = new Label("Ultimate Snake");
        gameName.setFont(Font.loadFont ("file:src/font/snake_font.otf", 95));
        gameName.setTextFill(Color.web("FF474C"));
        gameName.setTranslateY(-60);
        return gameName;
    }

    // done method
    public void drawCheckerboard(GraphicsContext gc) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if ((row + col) % 2 == 0) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.web("#f2f2f2"));
                }
                double x = col * SQUARE_SIZE;
                double y = row * SQUARE_SIZE;
                gc.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
        gc.setFill(Color.web("#f2f2f2"));
        gc.fillRect(COLUMNS * SQUARE_SIZE, 0, WIDTH - COLUMNS * SQUARE_SIZE, HEIGHT);
    }

    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    public static int getSquareSize() {
        return SQUARE_SIZE;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
