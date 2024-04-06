package com.fedor.model;

import com.fedor.exception.InvalidDeployPointException;
import com.fedor.exception.ObstacleHitException;

public class Rover {
    private Point position;
    private final Grid grid;
    private Orientation orientation;

    public Rover(
            Point initialPosition,
            Grid grid,
            Orientation orientation
    ) {
        if (grid.pointHitsObstacles(initialPosition))
            throw new InvalidDeployPointException();

        this.position = initialPosition;
        this.grid = grid;
        this.orientation = orientation;
    }

    public String getLocation() {
        return String.format(
                "%s:%s:%s",
                position.getX(),
                position.getY(),
                orientation.getCode()
        );
    }

    public String getLocationWhenObstacleHit() {
        return String.format(
                "O:%s:%s:%s",
                position.getX(),
                position.getY(),
                orientation.getCode()
        );
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void moveAdvancingInX() {
        this.position = grid.getNewPositionIncrementingInX(this.position);

        if (grid.pointHitsObstacles(this.position)) {
            this.position = grid.getNewPositionDecrementingInX(this.position);
            throw new ObstacleHitException();
        }
    }

    public void moveBackwardsInX() {
        this.position = grid.getNewPositionDecrementingInX(this.position);

        if (grid.pointHitsObstacles(this.position)) {
            this.position = grid.getNewPositionIncrementingInX(this.position);
            throw new ObstacleHitException();
        }
    }

    public void moveAdvancingInY() {
        this.position = grid.getNewPositionIncrementingInY(this.position);

        if (grid.pointHitsObstacles(this.position)) {
            this.position = grid.getNewPositionDecrementingInY(this.position);
            throw new ObstacleHitException();
        }
    }

    public void moveBackwardsInY() {
        this.position = grid.getNewPositionDecrementingInY(this.position);

        if (grid.pointHitsObstacles(this.position)) {
            this.position = grid.getNewPositionIncrementingInY(this.position);
            throw new ObstacleHitException();
        }
    }
}
