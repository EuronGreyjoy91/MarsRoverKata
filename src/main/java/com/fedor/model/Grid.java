package com.fedor.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Grid {
    private static final int MINIMUM_VALUE = 0;
    private static final int MAXIMUM_VALUE = 9;

    private final List<Point> obstacles;

    public Grid(List<Point> obstacles) {
        this.obstacles = Optional.ofNullable(obstacles).orElse(Collections.emptyList());
    }

    public boolean pointHitsObstacles(Point point) {
        return obstacles
                .stream()
                .anyMatch(obstacle -> obstacle.getX() == point.getX() && obstacle.getY() == point.getY());
    }

    public Point getNewPositionIncrementingInY(Point currentPosition) {
        if (currentPosition.getY() + 1 > MAXIMUM_VALUE)
            return new Point(currentPosition.getX(), MINIMUM_VALUE);

        return new Point(currentPosition.getX(), currentPosition.getY() + 1);
    }

    public Point getNewPositionIncrementingInX(Point currentPosition) {
        if (currentPosition.getX() + 1 > MAXIMUM_VALUE)
            return new Point(MINIMUM_VALUE, currentPosition.getY());

        return new Point(currentPosition.getX() + 1, currentPosition.getY());
    }

    public Point getNewPositionDecrementingInY(Point currentPosition) {
        if (currentPosition.getY() - 1 < MINIMUM_VALUE)
            return new Point(currentPosition.getX(), MAXIMUM_VALUE);

        return new Point(currentPosition.getX(), currentPosition.getY() - 1);
    }

    public Point getNewPositionDecrementingInX(Point currentPosition) {
        if (currentPosition.getX() - 1 < MINIMUM_VALUE)
            return new Point(MAXIMUM_VALUE, currentPosition.getY());

        return new Point(currentPosition.getX() - 1, currentPosition.getY());
    }
}
