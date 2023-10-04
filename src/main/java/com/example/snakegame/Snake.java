package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead = new Point();
    private static int ROWS = Graphics.getROWS();
    private static int SQUARE_SIZE = Graphics.getSquareSize();

    public void initializeSnake(){
        for (int i = 0; i < 3; i++){ // adds three initial segments to the body
            snakeBody.add(new Point(5, ROWS / 2)); // hardcode for snake's initial position
        }
        snakeHead = snakeBody.get(0);
    }

    public void drawSnake(GraphicsContext gc){ // rendering snake on the canvas
        gc.setFill(Color.web("4674E9")); // fill the snake's body with color
        // draws the snake's head as rounded rectangle
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++){ // starts from 1 because head is already there
            // determining the position of each segment of body and drawing it as a round rectangle
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 20, 20);
        }
    }

    public void moveBody(){
        for(int i = snakeBody.size() - 1; i >= 1; i--){ // iterates through the body from the tail and moves it on the position in front of it
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
    }

    public void moveRight(){
        snakeHead.x++;
    }

    public void moveLeft(){
        snakeHead.x--;
    }

    public void moveUp(){
        snakeHead.y--;
    }

    public void moveDown(){
        snakeHead.y++;
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }

    public Point getSnakeHead() {
        return snakeHead;
    }

    public int getSnakeHeadX(){
        return snakeHead.x;
    }

    public int getSnakeHeadY(){
        return snakeHead.y;
    }
}
