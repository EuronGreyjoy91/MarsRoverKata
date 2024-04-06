package com.fedor.model;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String code;

    Orientation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
