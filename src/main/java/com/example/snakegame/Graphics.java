package com.example.snakegame;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public class Graphics {
    private static final int WIDTH = 700;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH/COLUMNS;
    private Scene menuScene, mainScene;
    private Font gameAlternativeFont = Font.loadFont(getClass().getResourceAsStream("/com/example/snakegame/snake_font_alternative.otf"), 90);
    private Canvas backgroundCanvas = new Canvas(WIDTH, HEIGHT);
    private GraphicsContext backgroundGC = backgroundCanvas.getGraphicsContext2D();

    public void drawMainMenu(Stage primaryStage){
        drawCheckerboard(backgroundGC);
        // Game name
        Label gameName = new Label("Ultimate Snake");
        gameName.setFont(gameAlternativeFont);
        gameName.setTextFill(Color.RED);
        gameName.setTranslateY(-60);

        // Start button
        Button startButton = new Button("START");
        startButton.setOnAction(e -> primaryStage.setScene(mainScene));
        startButton.setStyle("-fx-background-color: RED; -fx-text-fill: white; -fx-font-size: 18px;");
        startButton.setTranslateY(30);

        // Exit button
        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setStyle("-fx-background-color: RED; -fx-text-fill: white; -fx-font-size: 18px;");
        exitButton.setTranslateY(80);

        // Adding elements from start menu
        StackPane menuLayout = new StackPane();
        menuLayout.getChildren().addAll(backgroundCanvas,gameName, startButton, exitButton);
        menuScene = new Scene(menuLayout, WIDTH, HEIGHT);
    }

    public void drawGameScene(Stage primaryStage){
        // Main stage settings
        Button button2 = new Button("Necum");
        button2.setOnAction(e -> primaryStage.setScene(menuScene));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        mainScene = new Scene(layout2, WIDTH, HEIGHT);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Ultimate Snake");
        primaryStage.show();
    }

    private void drawCheckerboard(GraphicsContext gc) {
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
}
