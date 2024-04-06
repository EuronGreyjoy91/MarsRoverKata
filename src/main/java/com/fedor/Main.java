package com.fedor;

import com.fedor.exception.ObstacleHitException;
import com.fedor.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> obstacles = List.of(
                new Point(4, 3),
                new Point(8, 5)
        );
        Grid grid = new Grid(obstacles);

        Point initialPosition = new Point(5, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.NORTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new RotateLeftCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));

        try {
            invoker.executeCommands();
            System.out.println(rover.getLocation());
        } catch (ObstacleHitException e) {
            System.out.println(rover.getLocationWhenObstacleHit());
        }
    }
}