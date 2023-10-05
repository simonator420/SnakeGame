package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;

public class Food {
    private static int ROWS = Graphics.getROWS();
    private static int COLUMNS = Graphics.getCOLUMNS();
    private static int SQUARE_SIZE = Graphics.getSquareSize();
    private static int foodX;
    private static int foodY;
    Snake snake = new Snake();
    List<Point> snakeBody = snake.getSnakeBody();

    // done method
    public void generateFood(){ // generating random coordinates for the food
        begin:
        while (true){
            foodX = (int)(Math.random() * ROWS); // generates random integer
            foodY = (int)(Math.random() * COLUMNS); // generates random integer
            System.out.println(foodX);
            System.out.println(foodY);

            for(Point snake: snakeBody){
                if(snake.getX() == foodX && snake.getY() == foodY){ // if it collides with the snake body
                    continue begin; // then the process is restarted to found a new place for the food
                }
            }
            break; // if the food is not on snake's body, the loop is ended
        }
    }

    public void drawFood(GraphicsContext gc, Image foodImage) { // rendering food on the canvas
        // determining the food position and drawing it on canvas
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
}
