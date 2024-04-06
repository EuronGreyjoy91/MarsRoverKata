package com.fedor.model;

import com.fedor.exception.InvalidCoordinateValueException;

public class Point {
    private static final int MINIMUM_VALUE = 0;
    private static final int MAXIMUM_VALUE = 9;

    private final int x;
    private final int y;

    public Point(int x, int y) {
        if (coordinateIsNotValid(x))
            throw new InvalidCoordinateValueException("x");

        if (coordinateIsNotValid(y))
            throw new InvalidCoordinateValueException("y");

        this.x = x;
        this.y = y;
    }

    private boolean coordinateIsNotValid(int coordinate) {
        return MINIMUM_VALUE > coordinate || MAXIMUM_VALUE < coordinate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
