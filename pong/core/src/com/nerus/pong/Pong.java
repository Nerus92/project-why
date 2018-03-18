package com.nerus.pong;

import java.awt.*;

public class Pong {

    protected static final int MIN_HEIGHT = 256;
    public static final int DEFAULT_HEIGHT = MIN_HEIGHT;
    public static final String HEIGHT_MINIMUM_EXCEPTION = "%s creation failed, height should be equals or superior to %d";
    public static final String INVALID_Y_POSITION = "Ball position setting failed, Y should be within [0, %d]";

    protected static final int MIN_WIDTH = 512;
    public static final int DEFAULT_WIDTH = MIN_WIDTH;
    public static final String WIDTH_MINIMUM_EXCEPTION = "%s creation failed, width should be equal or superior to %d";
    public static final String INVALID_X_POSITION = "Ball position setting failed, X should be within [0, %d]";

    protected static final int BALL_VERTICAL_SIZE = 5;
    protected static final int BALL_HORIZONTAL_SIZE = 6;
    protected static final int DEFAULT_VELOCITY = 7;

    private int height = DEFAULT_HEIGHT;
    private int width = DEFAULT_WIDTH;

    protected class Ball extends Point {

        public int dx, dy;

        public Ball(int x, int y) {
            super(x, y);
            dx = DEFAULT_VELOCITY;
            dy = DEFAULT_VELOCITY;
        }

        public void update() {
            x += dx;
            y += dy;

            if (y >= (height - BALL_VERTICAL_SIZE)) {
                dy = -dy;
                y -= 2 * (y - (height - BALL_VERTICAL_SIZE) + 1);
            } else if (y < 0) {
                dy = -dy;
                y += -2 * y;
            }
            if (x >= width) {
                dx = -dx;
                x -= 2 * (x - width + 1);
            } else if (x < 0) {
                dx = -dx;
                x += -2 * x;
            }
        }

    }

    private Ball ball;

    public Pong() {
        ball = new Ball(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);
    }

    public Pong(int height, int width) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(String.format(HEIGHT_MINIMUM_EXCEPTION, Pong.class, height));
        } else if (width < MIN_WIDTH) {
            throw new IllegalArgumentException(String.format(WIDTH_MINIMUM_EXCEPTION, Pong.class, width));
        }
        this.height = height;
        this.width = width;
        ball = new Ball(width / 2, height / 2);
    }

    public void update() {
        ball.update();
    }

    public void setBallPosition(int x, int y) {
        if (x < 0 || x >= width) {
            throw new IllegalArgumentException(String.format(INVALID_X_POSITION, width));
        } else if (y < 0 || y >= height) {
            throw new IllegalArgumentException(String.format(INVALID_Y_POSITION, height));
        }
        ball.x = x;
        ball.y = y;
    }

    public void setBallVelocity(int dx, int dy) {
        ball.dx = dx;
        ball.dy = dy;
    }

    public int getBallYPosition() {
        return ball.y;
    }

    public int getBallXPosition() {
        return ball.x;
    }

    public int getBallXVelocity() {
        return ball.dx;
    }

    public int getBallYVelocity() {
        return ball.dy;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
