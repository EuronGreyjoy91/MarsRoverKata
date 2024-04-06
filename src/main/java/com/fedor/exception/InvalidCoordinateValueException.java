package com.fedor.exception;

public class InvalidCoordinateValueException extends IllegalArgumentException {
    public InvalidCoordinateValueException(String coordinate) {
        super(String.format("Invalid value for coordinate %s", coordinate));
    }
}
