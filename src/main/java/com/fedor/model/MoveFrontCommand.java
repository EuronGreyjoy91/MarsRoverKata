package com.fedor.model;

public class MoveFrontCommand implements Command {
    private final Rover rover;

    public MoveFrontCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        switch (rover.getOrientation()) {
            case NORTH -> rover.moveBackwardsInY();
            case EAST -> rover.moveAdvancingInX();
            case SOUTH -> rover.moveAdvancingInY();
            case WEST -> rover.moveBackwardsInX();
        }
    }
}
