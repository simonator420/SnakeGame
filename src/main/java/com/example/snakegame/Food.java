package com.example.snakegame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public class Food {
    private Image foodImage;
    private int foodX;
    private int foodY;
    public Food(){}
    Snake snake = new Snake(new Point(5, 10), new ArrayList<>());
    public void generateFood() {
        begin:
        while (true) {
            foodX = (int) (Math.random() * Graphics.getCOLUMNS()); // generates random integer within checkerboard columns
            foodY = (int) (Math.random() * Graphics.getROWS());    // generates random integer within checkerboard rows

            for (Point snakePart : snake.getSnakeBody()) {
                if (snakePart.getX() == foodX && snakePart.getY() == foodY) {
                    continue begin; // if food is generated on the snake's body, regenerate
                }
            }
            break;
        }
        System.out.println(foodX + " "+ foodY);
    }

    public void drawFood(GraphicsContext gc, Image foodImage) {
        gc.drawImage(foodImage, (double)(foodX * 40), (double)(foodY * 40), 40.0, 40.0);
    }

    public Image getFoodImage() {
        return foodImage;
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }
}
