package com.nerus.pong;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PongTest {

    @Test
    public void testDefaultPongCreation() {
        Pong pong = new Pong();
        assertAll("Area Properties",
            () -> assertEquals(Pong.DEFAULT_HEIGHT, pong.getHeight()),
            () -> assertEquals(Pong.DEFAULT_WIDTH, pong.getWidth()));
    }

    @Test
    public void testCustomPongCreation() {
        int height = 456;
        int width = 789;
        Pong pong = new Pong(height, width);
        assertAll("Area Properties",
            () -> assertEquals(height, pong.getHeight()),
            () -> assertEquals(width, pong.getWidth()));
    }

    @Test
    public void testTooSmallPongHeightThrowsException() {
        int smallHeight = Pong.MIN_HEIGHT - 1;
        int okWidth = Pong.MIN_WIDTH;
        assertThrows(IllegalArgumentException.class, () -> new Pong(smallHeight, okWidth), Pong.HEIGHT_MINIMUM_EXCEPTION);
    }

    @Test
    public void testTooSmallPongWidthThrowsException() {
        int okHeight = Pong.MIN_HEIGHT;
        int smallWidth = Pong.MIN_WIDTH - 1;
        assertThrows(IllegalArgumentException.class, () -> new Pong(okHeight, smallWidth), Pong.WIDTH_MINIMUM_EXCEPTION);
    }

    @Test
    public void testDefaultBallPosition() {
        Pong pong = new Pong();
        assertAll("Ball Properties",
            () -> assertEquals(Pong.DEFAULT_HEIGHT / 2, pong.getBallYPosition()),
            () -> assertEquals(Pong.DEFAULT_WIDTH / 2, pong.getBallXPosition()));
    }

    @Test
    public void testDefaultBallVelocity() {
        Pong pong = new Pong();
        assertEquals(Pong.DEFAULT_VELOCITY | -Pong.DEFAULT_VELOCITY, pong.getBallXVelocity());
        assertThat([Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY], contains(pong.getBallXVelocity()));
    }

}
