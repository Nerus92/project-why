package com.nerus.pong;

public class GameArea {

    public static final int MIN_HEIGHT = 256;
    public static final int DEFAULT_HEIGHT = MIN_HEIGHT;
    public static final String HEIGHT_MINIMUM_EXCEPTION = "GameArea creation failed, height should be equals or superior to 256";

    public static final int MIN_WIDTH = 512;
    public static final int DEFAULT_WIDTH = MIN_WIDTH;
    public static final String WIDTH_MINIMUM_EXCEPTION = "GameArea creation failed, width should be equal or superior to 512";

    private int height = DEFAULT_HEIGHT;
    private int width = DEFAULT_WIDTH;

    public GameArea() {}

    public GameArea(int height, int width) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(HEIGHT_MINIMUM_EXCEPTION);
        } else if (width < MIN_WIDTH) {
            throw new IllegalArgumentException(WIDTH_MINIMUM_EXCEPTION);
        }
        this.height = height;
        this.width = width;
    }

    public int getBallYPosition() {
        return DEFAULT_HEIGHT / 2;
    }

    public int getBallXPosition() {
        return DEFAULT_WIDTH / 2;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
