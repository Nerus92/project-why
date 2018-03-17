package com.nerus.pong;

import java.awt.*;

public class Pong {

    public static final int MIN_HEIGHT = 256;
    public static final int DEFAULT_HEIGHT = MIN_HEIGHT;
    public static final String HEIGHT_MINIMUM_EXCEPTION = String.format("%s creation failed, height should be equals or superior to %d", Pong.class, MIN_HEIGHT);

    public static final int MIN_WIDTH = 512;
    public static final int DEFAULT_WIDTH = MIN_WIDTH;
    public static final String WIDTH_MINIMUM_EXCEPTION = String.format("%s creation failed, width should be equal or superior to %d", Pong.class, MIN_WIDTH);

    public static final int DEFAULT_VELOCITY = 7;

    private int height = DEFAULT_HEIGHT;
    private int width = DEFAULT_WIDTH;

    private Point ball;

    public Pong() {
        ball = new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);
    }

    public Pong(int height, int width) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(HEIGHT_MINIMUM_EXCEPTION);
        } else if (width < MIN_WIDTH) {
            throw new IllegalArgumentException(WIDTH_MINIMUM_EXCEPTION);
        }
        this.height = height;
        this.width = width;
        ball = new Point(width / 2, height / 2);
    }

    public void setBallPosition(int x, int y) {
        ball.x = x;
        ball.y = y;
    }

    public int getBallYPosition() {
        return ball.y;
    }

    public int getBallXPosition() {
        return ball.x;
    }

    public int getBallXVelocity() {
        return DEFAULT_VELOCITY;
    }

    public int getBallYVelocity() { return DEFAULT_VELOCITY; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
