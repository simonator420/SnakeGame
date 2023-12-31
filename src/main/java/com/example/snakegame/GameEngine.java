package com.example.snakegame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.Point;
import java.util.ArrayList;

public class GameEngine {
    private static final int WIDTH = 960;
    private static final int HEIGHT = 720;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private static final Color GAME_OVER_COLOR = Color.RED;
    private static final String GAME_TITLE = "Snake";
    private static final String GAME_OVER_TEXT = "Game Over";
    javafx.scene.image.Image berryImage = new Image("file:src/img/food.png");
    private Image staticBackground = new Image("file:src/img/dirt.jpg");
    private GraphicsContext gc;
    private Snake snake = new Snake(new Point(5, 10), new ArrayList<>());
    private Food food = new Food();
    private boolean gameOver;
    private int currentDirection;
    private int highScore = 0;
    private Graphics graphics = new Graphics();
    private Scene gameOverScene; // Make this an instance variable
    private Stage mainStage;

    public void startGame(Stage primaryStage) {
        Group root = new Group();
        this.mainStage = primaryStage;

        Canvas menuCanvas = new Canvas(WIDTH, HEIGHT);
        gc = menuCanvas.getGraphicsContext2D();

        Canvas mainCanvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(mainCanvas);

        Scene mainScene = new Scene(root);
        StackPane menuLayout = new StackPane();

        Graphics graphics = new Graphics();
        Label gameName = graphics.getGameName();
        Button exitButton = graphics.getExitGameButton();
        Button startButton = graphics.getStartGameButton();
        menuLayout.getChildren().addAll(menuCanvas, gameName, startButton, exitButton);
        Scene menuScene = new Scene(menuLayout, WIDTH, HEIGHT);

        StackPane gameOverLayout = new StackPane();
        Label gameOverText = new Label("GAME OVER");
        gameOverText.setFont(Font.loadFont ("file:src/font/snake_font.otf", 95));
        gameOverText.setTextFill(Color.web("FF474C"));
        gameOverText.setTranslateY(-35);
        Button restartButton = graphics.getRestartButton();
        restartButton.setOnAction(e -> {
            resetGame();
            mainStage.setScene(mainScene);
        });
        gameOverLayout.getChildren().addAll(gameOverText, restartButton, exitButton);
        gameOverScene = new Scene(gameOverLayout, WIDTH, HEIGHT);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle(GAME_TITLE);
        primaryStage.show();

        this.gc = mainCanvas.getGraphicsContext2D();
        mainScene.setOnKeyPressed(keyEvent -> handleKeyPress(keyEvent));

        snake.initializeSnake();
        snake.setSnakeHead((Point) this.snake.getSnakeBody().get(0));
        food.generateFood();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis((90.0)), e -> run(gc)));
        timeline.setCycleCount(-1);
        startButton.setOnAction(e -> { primaryStage.setScene(mainScene); timeline.play(); });

        gc.setStroke(javafx.scene.paint.Color.RED);
        gc.setLineWidth(5); // Adjust the width of the border as needed
        gc.strokeRect(0, 0, WIDTH, HEIGHT);
    }

    private void run(GraphicsContext gc) {
        if (gameOver) {
            if (snake.getScore() > highScore) {
                highScore = snake.getScore();
            }
            mainStage.setScene(gameOverScene);
            return;
        }
        graphics.drawCheckerboard(gc);

        food.drawFood(gc, berryImage);
        snake.drawSnake(gc);
        drawScore();

        snake.moveBody();

        switch (currentDirection) {
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

        gameOver();
        snake.eatFood(food);
    }

    private void handleKeyPress(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();

        switch (code) {
            case RIGHT:
            case D:
                if (currentDirection != LEFT) {
                    currentDirection = RIGHT;
                }
                break;
            case LEFT:
            case A:
                if (currentDirection != RIGHT) {
                    currentDirection = LEFT;
                }
                break;
            case UP:
            case W:
                if (currentDirection != DOWN) {
                    currentDirection = UP;
                }
                break;
            case DOWN:
            case S:
                if (currentDirection != UP) {
                    currentDirection = DOWN;
                }
                break;
        }
    }

    private void gameOver() {
        if (snake.getSnakeHead().x < 0 || snake.getSnakeHead().y < 0 ||
                snake.getSnakeHead().x * Graphics.getSquareSize() >= Graphics.getHEIGHT() ||
                snake.getSnakeHead().y * Graphics.getSquareSize() >= Graphics.getHEIGHT()) {
//            gc.setFill(GAME_OVER_COLOR);
//            gc.setFont(new Font("Digital-7", 70.0));
//            drawText(GAME_OVER_TEXT, 228.57142857142858, 400.0);
            gameOver = true;
        }

        for (int i = 1; i < snake.getSnakeBody().size(); ++i) {
            if (snake.getSnakeHead().equals(snake.getSnakeBody().get(i))) {
                gc.setFill(GAME_OVER_COLOR);
                gc.setFont(new Font("Digital-7", 70.0));
                drawText(GAME_OVER_TEXT, 228.57142857142858, 400.0);
                gameOver = true;
                break;
            }
        }
    }

    private void resetGame() {
        snake = new Snake(new Point(5, 10), new ArrayList<>());
        food = new Food();
        gameOver = false;
        currentDirection = RIGHT; // or any default direction you want
        snake.initializeSnake();
        snake.setSnakeHead((Point) this.snake.getSnakeBody().get(0));
        food.generateFood();
    }

    private void drawText(String text, double x, double y) {
        gc.fillText(text, x, y);
    }

    private void drawScore() {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Digital-7", 35.0));
        drawText("Score: " + snake.getScore(), 780.0, 40.0);
        drawText("High Score: " + highScore, 735, 100);
    }
}
