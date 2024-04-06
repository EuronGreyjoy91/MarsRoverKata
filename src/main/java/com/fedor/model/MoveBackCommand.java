package com.fedor.model;

public class MoveBackCommand implements Command {
    private final Rover rover;

    public MoveBackCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        switch (rover.getOrientation()) {
            case NORTH -> rover.moveAdvancingInY();
            case EAST -> rover.moveBackwardsInX();
            case SOUTH -> rover.moveBackwardsInY();
            case WEST -> rover.moveAdvancingInX();
        }
    }

}
