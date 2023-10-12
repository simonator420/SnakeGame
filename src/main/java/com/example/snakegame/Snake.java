package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;

public class Snake {
    private Point snakeHead;
    private List<Point> snakeBody;
    private Image snakeHeadImage;
    private Image snakeImago;
    private ImageView snakeHeadImageView;
    private int score = 0;
    public Snake(Point initialHead, List<Point> initialBody) {
        this.snakeHead = initialHead;
        this.snakeBody = initialBody;
        this.snakeImago = new Image("file:src/img/snake_skin.png");
        this.snakeHeadImageView = new ImageView(new Image("file:src/img/snake_head2.png"));
    }

    public Point getSnakeHead() {
        return snakeHead;
    }

    public int getScore() {
        return score;
    }

    public void setSnakeHead(Point snakeHead) {
        this.snakeHead = snakeHead;
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }
    public void setSnakeBody(List<Point> snakeBody) {
        this.snakeBody = snakeBody;
    }

    public void drawSnake(GraphicsContext gc) {
        // Draw the rotated snake head
        snakeHeadImage = snakeHeadImageView.snapshot(null, null);
        gc.drawImage(snakeHeadImage, snakeHead.getX() * 40.0, snakeHead.getY() * 40.0, 39.0, 39.0);

        // Draw the snake body
        for(int i = 1; i < snakeBody.size(); ++i) {
            gc.drawImage(snakeImago, snakeBody.get(i).getX() * 40.0, snakeBody.get(i).getY() * 40.0, 39.0, 39.0);
        }
    }

    public void moveBody(){
        for(int i = snakeBody.size() - 1; i >= 1; --i) {
            ((Point)snakeBody.get(i)).x = ((Point)snakeBody.get(i - 1)).x;
            ((Point)snakeBody.get(i)).y = ((Point)snakeBody.get(i - 1)).y;
        }
    }

    public void initializeSnake(){
        for(int i = 0; i < 3; ++i) {
            snakeBody.add(new Point(5, 10));
        }
        setSnakeHead((Point)snakeBody.get(0));
    }

    public void eatFood(Food food) {
        if (snakeHead.getX() == (double) food.getFoodX() && snakeHead.getY() == (double) food.getFoodY()) {
            snakeBody.add(new Point(-1, -1));
            food.generateFood();
            this.score += 1;
        }
        System.out.println(score);
    }

    public void moveRight() {
        ++snakeHead.x;
        snakeHeadImageView.setRotate(270.0);

    }

    public void moveLeft() {
        --snakeHead.x;
        snakeHeadImageView.setRotate(90.0);
    }

    public void moveUp() {
        --snakeHead.y;
        snakeHeadImageView.setRotate(180.0);
    }

    public void moveDown() {
        ++snakeHead.y;
        snakeHeadImageView.setRotate(0.0);
    }
}


