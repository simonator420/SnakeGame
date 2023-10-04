package com.example.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.List;

public class Graphics {
    private static int WIDTH = 700;
    private static int HEIGHT = WIDTH;
    private static int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static int SQUARE_SIZE = WIDTH/COLUMNS;
    private Font gameAlternativeFont = Font.loadFont(getClass().getResourceAsStream("/com/example/snakegame/snake_font_alternative.otf"), 90);
    Snake snake = new Snake();

    // done function
    public Button getExitGameButton(){
        javafx.scene.control.Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setStyle("-fx-background-color: RED; -fx-text-fill: white; -fx-font-size: 18px;");
        exitButton.setTranslateY(80);
        return exitButton;
    }

    // done function
    public Button getStartGameButton(){
        Button startButton = new Button("START");
        startButton.setStyle("-fx-background-color: RED; -fx-text-fill: white; -fx-font-size: 18px;");
        startButton.setTranslateY(30);
        return startButton;
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
        }
    }

    public static int getROWS() {
        return ROWS;
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
