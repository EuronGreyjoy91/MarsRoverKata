package com.fedor.model;

public class RotateRightCommand implements Command{
    private final Rover rover;

    public RotateRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        switch (rover.getOrientation()) {
            case NORTH -> rover.setOrientation(Orientation.EAST);
            case EAST -> rover.setOrientation(Orientation.SOUTH);
            case SOUTH -> rover.setOrientation(Orientation.WEST);
            case WEST -> rover.setOrientation(Orientation.NORTH);
        }
    }
}
