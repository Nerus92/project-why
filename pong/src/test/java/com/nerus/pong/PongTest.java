package com.nerus.pong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(IllegalArgumentException.class, () -> new Pong(smallHeight, okWidth));
    }

    @Test
    public void testTooSmallPongWidthThrowsException() {
        int okHeight = Pong.MIN_HEIGHT;
        int smallWidth = Pong.MIN_WIDTH - 1;
        assertThrows(IllegalArgumentException.class, () -> new Pong(okHeight, smallWidth));
    }

    @Test
    public void testDefaultBallPosition() {
        Pong pong = new Pong();
        assertAll("Ball Position",
                () -> assertEquals(Pong.DEFAULT_HEIGHT / 2, pong.getBallYPosition()),
                () -> assertEquals(Pong.DEFAULT_WIDTH / 2, pong.getBallXPosition()));
    }

    @Test
    public void testInitialBallPositionInCustomPong() {
        int height = 456;
        int width = 789;
        Pong pong = new Pong(height, width);
        assertAll("Ball Position",
                () -> assertEquals(height / 2, pong.getBallYPosition()),
                () -> assertEquals(width / 2, pong.getBallXPosition()));
    }

    @Test
    public void testDefaultBallVelocity() {
        Pong pong = new Pong();
        assertAll("Ball Velocity",
                () -> assertThat(Arrays.asList(Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY), hasItem(pong.getBallXVelocity())),
                () -> assertThat(Arrays.asList(Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY), hasItem(pong.getBallYVelocity())));
    }

    @Test
    public void testBallCanBeSetInPosition() {
        Random random = new Random();
        Pong pong = new Pong();
        int x = random.nextInt(Pong.DEFAULT_WIDTH);
        int y = random.nextInt(Pong.DEFAULT_HEIGHT);
        pong.setBallPosition(x, y);
        assertAll("Ball Position",
                () -> assertEquals(y, pong.getBallYPosition()),
                () -> assertEquals(x, pong.getBallXPosition()));
    }

    @Test
    public void testBallCannotBeSetInInvalidPosition() {
        Pong pong = new Pong();
        assertAll("Ball Invalid Position",
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(-1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(0, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(Pong.MIN_WIDTH, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(0, Pong.MIN_HEIGHT)));
    }

    @Test
    public void testBallVelocityCanBeSet() {
        Pong pong = new Pong();
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY - 1, Pong.DEFAULT_VELOCITY - 1);
        assertAll("Ball Velocity",
                () -> assertEquals(Pong.DEFAULT_VELOCITY - 1, pong.getBallXVelocity()),
                () -> assertEquals(Pong.DEFAULT_VELOCITY - 1, pong.getBallYVelocity()));
    }

    @ParameterizedTest
    @MethodSource(names = {"ballVelocitiesProvider"})
    public void testBallEvolves(int dx, int dy) {
        Pong pong = new Pong();
        pong.setBallPosition(Pong.MIN_WIDTH / 2, Pong.MIN_HEIGHT / 2);
        pong.setBallVelocity(dx, dy);
        pong.update();
        assertAll("Ball Position",
                () -> assertEquals((Pong.MIN_HEIGHT / 2) + dy, pong.getBallYPosition()),
                () -> assertEquals((Pong.MIN_WIDTH / 2) + dx, pong.getBallXPosition()));
    }

    static Stream<Arguments> ballVelocitiesProvider() {
        Stream stream = Stream.empty();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                stream = Stream.concat(stream, Stream.of(ObjectArrayArguments.create(i * Pong.DEFAULT_VELOCITY, j * Pong.DEFAULT_VELOCITY)));
            }
        }
        return stream;
    }

}
