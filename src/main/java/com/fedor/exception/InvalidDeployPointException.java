package com.fedor.exception;

public class InvalidDeployPointException extends IllegalArgumentException {
    public InvalidDeployPointException() {
        super("Invalid point for deploy");
    }
}
