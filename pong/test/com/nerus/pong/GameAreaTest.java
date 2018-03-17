package com.nerus.pong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameAreaTest {

    @Test
    public void testDefaultGameAreaCreation() {
        GameArea gameArea = new GameArea();
        assertEquals(GameArea.DEFAULT_HEIGHT, gameArea.getHeight());
        assertEquals(GameArea.DEFAULT_WIDTH, gameArea.getWidth());
    }

    @Test
    public void testCustomGameAreaCreation() {
        int height = 456;
        int width = 789;
        GameArea gameArea = new GameArea(height, width);
        assertEquals(height, gameArea.getHeight());
        assertEquals(width, gameArea.getWidth());
    }

    @Test
    public void testTooSmallGameAreaHeightThrowsException() {
        int smallHeight = GameArea.MIN_HEIGHT - 1;
        int okWidth = GameArea.MIN_WIDTH;
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new GameArea(smallHeight, okWidth);
            }
        }, GameArea.HEIGHT_MINIMUM_EXCEPTION);
    }

    @Test
    public void testTooSmallGameAreaWidthThrowsException() {
        int okHeight = GameArea.MIN_HEIGHT;
        int smallWidth = GameArea.MIN_WIDTH - 1;
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new GameArea(okHeight, smallWidth);
            }
        }, GameArea.WIDTH_MINIMUM_EXCEPTION);
    }

    @Test
    public void testDefaultBallPosition() {
        GameArea gameArea = new GameArea();
        assertEquals(GameArea.DEFAULT_HEIGHT / 2, gameArea.getBallYPosition());
        assertEquals(GameArea.DEFAULT_WIDTH / 2, gameArea.getBallXPosition());
    }

}
