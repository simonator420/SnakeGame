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
            foodX = (int) (Math.random() * 20); // generates random integer
            foodY = (int) (Math.random() * 20); // generates random integer

            for (Point snake : snake.getSnakeBody()) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue begin;
                }
            }

            // int randomFoodIndex = (int) (Math.random() * FOODS_IMAGE.length);
            // foodImage = new Image(getClass().getResourceAsStream(FOODS_IMAGE[randomFoodIndex]));

            break;
        }
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
