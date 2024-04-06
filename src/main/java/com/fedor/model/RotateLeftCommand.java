package com.fedor.model;


public class RotateLeftCommand implements Command {
    private final Rover rover;

    public RotateLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        switch (rover.getOrientation()) {
            case NORTH -> rover.setOrientation(Orientation.WEST);
            case EAST -> rover.setOrientation(Orientation.NORTH);
            case SOUTH -> rover.setOrientation(Orientation.EAST);
            case WEST -> rover.setOrientation(Orientation.SOUTH);
        }
    }
}
