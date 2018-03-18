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

class PongTest {

    @Test
    void testDefaultPongCreation() {
        Pong pong = new Pong();
        assertAll("Area Properties",
                () -> assertEquals(Pong.DEFAULT_HEIGHT, pong.getHeight()),
                () -> assertEquals(Pong.DEFAULT_WIDTH, pong.getWidth()));
    }

    @Test
    void testCustomPongCreation() {
        int height = 456;
        int width = 789;
        Pong pong = new Pong(height, width);
        assertAll("Area Properties",
                () -> assertEquals(height, pong.getHeight()),
                () -> assertEquals(width, pong.getWidth()));
    }

    @Test
    void testTooSmallPongHeightThrowsException() {
        int smallHeight = Pong.MIN_HEIGHT - 1;
        int okWidth = Pong.MIN_WIDTH;
        assertThrows(IllegalArgumentException.class, () -> new Pong(smallHeight, okWidth));
    }

    @Test
    void testTooSmallPongWidthThrowsException() {
        int okHeight = Pong.MIN_HEIGHT;
        int smallWidth = Pong.MIN_WIDTH - 1;
        assertThrows(IllegalArgumentException.class, () -> new Pong(okHeight, smallWidth));
    }

    @Test
    void testDefaultBallPosition() {
        Pong pong = new Pong();
        assertAll("Ball Position",
                () -> assertEquals(Pong.DEFAULT_HEIGHT / 2, pong.getBallYPosition()),
                () -> assertEquals(Pong.DEFAULT_WIDTH / 2, pong.getBallXPosition()));
    }

    @Test
    void testInitialBallPositionInCustomPong() {
        int height = 456;
        int width = 789;
        Pong pong = new Pong(height, width);
        assertAll("Ball Position",
                () -> assertEquals(height / 2, pong.getBallYPosition()),
                () -> assertEquals(width / 2, pong.getBallXPosition()));
    }

    @Test
    void testDefaultBallVelocity() {
        Pong pong = new Pong();
        assertAll("Ball Velocity",
                () -> assertThat(Arrays.asList(Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY), hasItem(pong.getBallXVelocity())),
                () -> assertThat(Arrays.asList(Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY), hasItem(pong.getBallYVelocity())));
    }

    @Test
    void testBallCanBeSetInPosition() {
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
    void testBallCannotBeSetInInvalidPosition() {
        Pong pong = new Pong();
        assertAll("Ball Invalid Position",
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(-1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(0, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(Pong.DEFAULT_WIDTH, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> pong.setBallPosition(0, Pong.DEFAULT_HEIGHT)));
    }

    @Test
    void testBallVelocityCanBeSet() {
        Pong pong = new Pong();
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY - 1, Pong.DEFAULT_VELOCITY - 1);
        assertAll("Ball Velocity",
                () -> assertEquals(Pong.DEFAULT_VELOCITY - 1, pong.getBallXVelocity()),
                () -> assertEquals(Pong.DEFAULT_VELOCITY - 1, pong.getBallYVelocity()));
    }

    @ParameterizedTest
    @MethodSource(names = {"ballVelocitiesProvider"})
    void testBallEvolves(int dx, int dy) {
        Pong pong = new Pong();
        pong.setBallPosition(Pong.DEFAULT_WIDTH / 2, Pong.DEFAULT_HEIGHT / 2);
        pong.setBallVelocity(dx, dy);
        pong.update();
        assertAll("Ball Position",
                () -> assertEquals((Pong.DEFAULT_HEIGHT / 2) + dy, pong.getBallYPosition()),
                () -> assertEquals((Pong.DEFAULT_WIDTH / 2) + dx, pong.getBallXPosition()));
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

    @Test
    void testBallBouncesAtTopWhenTouching() {
        Pong pong = new Pong();
        pong.setBallPosition(Pong.DEFAULT_WIDTH / 2, Pong.DEFAULT_HEIGHT - Pong.BALL_VERTICAL_SIZE);
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY, Pong.DEFAULT_VELOCITY);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals(Pong.DEFAULT_HEIGHT - Pong.BALL_VERTICAL_SIZE - Pong.DEFAULT_VELOCITY, pong.getBallYPosition()),
                        () -> assertEquals((Pong.DEFAULT_WIDTH / 2) + Pong.DEFAULT_VELOCITY, pong.getBallXPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(-Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @Test
    void testBallBouncesAtBottomWhenTouching() {
        Pong pong = new Pong();
        pong.setBallPosition(Pong.DEFAULT_WIDTH / 2, 0);
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYPosition()),
                        () -> assertEquals((Pong.DEFAULT_WIDTH / 2) + Pong.DEFAULT_VELOCITY, pong.getBallXPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @Test
    void testBallBouncesAtRightWhenTouching() {
        Pong pong = new Pong();
        pong.setBallPosition(Pong.DEFAULT_WIDTH - Pong.BALL_HORIZONTAL_SIZE, Pong.DEFAULT_HEIGHT / 2);
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY, Pong.DEFAULT_VELOCITY);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals((Pong.DEFAULT_HEIGHT / 2) + Pong.DEFAULT_VELOCITY, pong.getBallYPosition()),
                        () -> assertEquals(Pong.DEFAULT_WIDTH - Pong.BALL_HORIZONTAL_SIZE - Pong.DEFAULT_VELOCITY, pong.getBallXPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(-Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @Test
    void testBallBouncesAtLeftWhenTouching() {
        Pong pong = new Pong();
        pong.setBallPosition(0, Pong.DEFAULT_HEIGHT / 2);
        pong.setBallVelocity(-Pong.DEFAULT_VELOCITY, Pong.DEFAULT_VELOCITY);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals((Pong.DEFAULT_HEIGHT / 2) + Pong.DEFAULT_VELOCITY, pong.getBallYPosition()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @ParameterizedTest
    @MethodSource(names = {"ballOffsetProvider"})
    void testBallBouncesAtTopWhenOffset(int offset) {
        Pong pong = new Pong();
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY, Pong.DEFAULT_VELOCITY);
        pong.setBallPosition(Pong.DEFAULT_WIDTH / 2, Pong.DEFAULT_HEIGHT - Pong.BALL_VERTICAL_SIZE - offset);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals(Pong.DEFAULT_HEIGHT - Pong.BALL_VERTICAL_SIZE - Pong.DEFAULT_VELOCITY + offset, pong.getBallYPosition()),
                        () -> assertEquals((Pong.DEFAULT_WIDTH / 2) + Pong.DEFAULT_VELOCITY, pong.getBallXPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(-Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @ParameterizedTest
    @MethodSource(names = {"ballOffsetProvider"})
    void testBallBouncesAtBottomWhenOffset(int offset) {
        Pong pong = new Pong();
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY, -Pong.DEFAULT_VELOCITY);
        pong.setBallPosition(Pong.DEFAULT_WIDTH / 2, offset);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY - offset, pong.getBallYPosition()),
                        () -> assertEquals((Pong.DEFAULT_WIDTH / 2) + Pong.DEFAULT_VELOCITY, pong.getBallXPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @ParameterizedTest
    @MethodSource(names = {"ballOffsetProvider"})
    void testBallBouncesAtRightWhenOffset(int offset) {
        Pong pong = new Pong();
        pong.setBallVelocity(Pong.DEFAULT_VELOCITY, Pong.DEFAULT_VELOCITY);
        pong.setBallPosition(Pong.DEFAULT_WIDTH - Pong.BALL_HORIZONTAL_SIZE - offset, Pong.DEFAULT_HEIGHT / 2);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals(Pong.DEFAULT_WIDTH - Pong.BALL_HORIZONTAL_SIZE - Pong.DEFAULT_VELOCITY + offset, pong.getBallXPosition()),
                        () -> assertEquals((Pong.DEFAULT_HEIGHT / 2) + Pong.DEFAULT_VELOCITY, pong.getBallYPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(-Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    @ParameterizedTest
    @MethodSource(names = {"ballOffsetProvider"})
    void testBallBouncesAtLeftWhenOffset(int offset) {
        Pong pong = new Pong();
        pong.setBallVelocity(-Pong.DEFAULT_VELOCITY, Pong.DEFAULT_VELOCITY);
        pong.setBallPosition(offset, Pong.DEFAULT_HEIGHT / 2);
        pong.update();
        assertAll("Ball Properties",
                () -> assertAll("Ball Position",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY - offset, pong.getBallXPosition()),
                        () -> assertEquals((Pong.DEFAULT_HEIGHT / 2) + Pong.DEFAULT_VELOCITY, pong.getBallYPosition())),
                () -> assertAll("Ball Velocity",
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallYVelocity()),
                        () -> assertEquals(Pong.DEFAULT_VELOCITY, pong.getBallXVelocity())));
    }

    static Stream<Arguments> ballOffsetProvider() {
        Stream stream = Stream.empty();
        for (int i = 0; i < Pong.DEFAULT_VELOCITY; i++) {
            stream = Stream.concat(stream, Stream.of(ObjectArrayArguments.create(i)));
        }
        return stream;
    }

}
