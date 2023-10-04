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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    Graphics graphics = new Graphics();
    Snake snake = new Snake();
    Canvas backgroundCanvas = new Canvas(700, 700);
    private static final int WIDTH = 700;
    private static final int HEIGHT = WIDTH;
    private static int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static int SQUARE_SIZE = WIDTH/COLUMNS;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private Scene menuScene, mainScene;
    private Font gameAlternativeFont = Font.loadFont(getClass().getResourceAsStream("/com/example/snakegame/snake_font_alternative.otf"), 90);
    private int currentDirection;
    List<Point> snakeBody = snake.getSnakeBody();
    Point snakeHead = snake.getSnakeHead();

    public void startGame(Stage primaryStage){
        // Creating the checkerboard
        Canvas menuCanvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext menuGC = menuCanvas.getGraphicsContext2D();
        graphics.drawCheckerboard(menuGC);

        // Game name
        javafx.scene.control.Label gameName = new javafx.scene.control.Label("Ultimate Snake");
        gameName.setFont(gameAlternativeFont);
        gameName.setTextFill(Color.RED);
        gameName.setTranslateY(-60);


        Button exitButton = graphics.getExitGameButton();

        // Adding elements to main game scene

        Canvas mainCanvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext mainGC = mainCanvas.getGraphicsContext2D();
        graphics.drawCheckerboard(mainGC);

        Button startButton = startGame(primaryStage, mainGC);

        // Adding elements to start menu
        StackPane menuLayout = new StackPane();
        menuLayout.getChildren().addAll(menuCanvas,gameName, startButton, exitButton);
        menuScene = new Scene(menuLayout, WIDTH, HEIGHT);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Ultimate Snake");
        primaryStage.show();

        // drawGameScene

        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(mainCanvas);
        mainScene = new Scene(mainLayout, WIDTH, HEIGHT);

        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() { // mapping the key presses
            @Override
            public void handle(KeyEvent keyEvent) {
                handleKeyPress(keyEvent);
            }
        });

        snake.initializeSnake();
    }

    public void run(GraphicsContext backgroundGC) { // loop that updates each frame
        snake.drawSnake(backgroundGC);

        snake.moveBody();

        switch (currentDirection){ // get currentDirection which stores the current direction and calls one of the methods
            case RIGHT:
                snake.moveRight();
                break;
            case LEFT:
                snake.moveLeft();
                break;
            case UP:
                snake.moveUp();
                break;
            case DOWN:
                snake.moveDown();
                break;
        }
    }

    private void handleKeyPress(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            if (currentDirection != LEFT) {
                currentDirection = RIGHT;
            }
        } else if (code == KeyCode.LEFT || code == KeyCode.A) {
            if (currentDirection != RIGHT) {
                currentDirection = LEFT;
            }
        } else if (code == KeyCode.UP || code == KeyCode.W) {
            if (currentDirection != DOWN) {
                currentDirection = UP;
            }
        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
            if (currentDirection != UP) {
                currentDirection = DOWN;
            }
        }
    }

    private Button startGame(Stage primaryStage, GraphicsContext mainGC) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(215), e->run(mainGC))); // sets a timeline that defines a keyframe with a duration, the action is run method and passes the GraphicsContext as an argument
        timeline.setCycleCount(Animation.INDEFINITE); // calling the run function indefinite times

        // Start button
        Button startButton = graphics.getStartGameButton();
        startButton.setOnAction(e -> { primaryStage.setScene(mainScene); timeline.play();});

        return startButton;
    }
}
