import com.fedor.exception.InvalidCoordinateValueException;
import com.fedor.exception.ObstacleHitException;
import com.fedor.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTest {

    private final Grid grid = new Grid(Collections.emptyList());

    @Test
    @DisplayName("Se crea un punto sin problemas")
    void createPointOk() {
        assertDoesNotThrow(() -> new Point(5, 5));
    }

    @Test
    @DisplayName("Rover se mueve correctamente en entorno sin obstaculos")
    void roverMovesOkWithoutObstacles() {
        Point initialPosition = new Point(3, 2);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.WEST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new RotateLeftCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("4:3:N", finalLocation);
    }

    @Test
    @DisplayName("Rover completa circuito con obstaculos sin chocarlos")
    void roverCompletesCircuitWithoutHits() {
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

        String location;

        try {
            invoker.executeCommands();
            location = rover.getLocation();
        } catch (ObstacleHitException e) {
            location = rover.getLocationWhenObstacleHit();
        }

        assertEquals("5:4:N", location);
    }

    @Test
    @DisplayName("Rover se frena si encuentra un obstaculo")
    void roverStopsWhenObstacleHit() {
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
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));

        String location;

        try {
            invoker.executeCommands();
            location = rover.getLocation();
        } catch (ObstacleHitException e) {
            location = rover.getLocationWhenObstacleHit();
        }

        assertEquals("O:7:5:E", location);
    }

    @Test
    @DisplayName("No se puede crear un punto con un X menor a 0")
    void cannotCreatePointWithXLowerThanZero() {
        Exception exception = assertThrows(InvalidCoordinateValueException.class, () -> new Point(-5, 5));

        String expectedMessage = "Invalid value for coordinate x";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("No se puede crear un punto con un X mayor a 9")
    void cannotCreatePointWithXGreaterThanNine() {
        Exception exception = assertThrows(InvalidCoordinateValueException.class, () -> new Point(10, 5));

        String expectedMessage = "Invalid value for coordinate x";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("No se puede crear un punto con un Y menor a 0")
    void cannotCreatePointWithYLowerThanZero() {
        Exception exception = assertThrows(InvalidCoordinateValueException.class, () -> new Point(5, -5));

        String expectedMessage = "Invalid value for coordinate y";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("No se puede crear un punto con un X mayor a 9")
    void cannotCreatePointWithYGreaterThanNine() {
        Exception exception = assertThrows(InvalidCoordinateValueException.class, () -> new Point(5, 10));

        String expectedMessage = "Invalid value for coordinate y";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Rover se mueve correctamente mirando al Norte")
    void roverMovesRightLookingNorth() {
        Point initialPosition = new Point(6, 7);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.NORTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("6:6:N", finalLocation);
    }

    @Test
    @DisplayName("Rover se mueve correctamente mirando al Este")
    void roverMovesRightLookingEast() {
        Point initialPosition = new Point(6, 7);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.EAST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("7:7:E", finalLocation);
    }

    @Test
    @DisplayName("Rover se mueve correctamente mirando al Sur")
    void roverMovesRightLookingSouth() {
        Point initialPosition = new Point(6, 7);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.SOUTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("6:8:S", finalLocation);
    }

    @Test
    @DisplayName("Rover se mueve correctamente mirando al Oeste")
    void roverMovesRightLookingWest() {
        Point initialPosition = new Point(6, 7);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.WEST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.addCommand(new MoveBackCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:7:W", finalLocation);
    }

    @Test
    @DisplayName("Rover en eje Y sale por el Sur si sobrepasa limite Norte")
    void roverInYAxisAppearsSouthWhenBreakingNorthLimit() {
        Point initialPosition = new Point(5, 0);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.NORTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:9:N", finalLocation);
    }

    @Test
    @DisplayName("Rover en eje X sale por el Oeste si sobrepasa limite Este")
    void roverInXAxisAppearsWestWhenBreakingEastLimit() {
        Point initialPosition = new Point(9, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.EAST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("0:5:E", finalLocation);
    }

    @Test
    @DisplayName("Rover en eje Y sale por el Norte si sobrepasa limite Sur")
    void roverInXAxisAppearsNorthWhenBreakingSouthLimit() {
        Point initialPosition = new Point(5, 9);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.SOUTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:0:S", finalLocation);
    }

    @Test
    @DisplayName("Rover en eje X sale por el Este si sobrepasa limite Oeste")
    void roverInXAxisAppearsEastWhenBreakingWestLimit() {
        Point initialPosition = new Point(0, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.WEST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new MoveFrontCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("9:5:W", finalLocation);
    }

    @Test
    @DisplayName("Rover mirando al Norte gira correctamente en ambas direcciones")
    void roverLookingNorthRotatesOkInBothDirections() {
        Point initialPosition = new Point(5, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.NORTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateLeftCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:5:E", finalLocation);
    }

    @Test
    @DisplayName("Rover mirando al Este gira correctamente en ambas direcciones")
    void roverLookingEastRotatesOkInBothDirections() {
        Point initialPosition = new Point(5, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.EAST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateLeftCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:5:S", finalLocation);
    }

    @Test
    @DisplayName("Rover mirando al Sur gira correctamente en ambas direcciones")
    void roverLookingSouthRotatesOkInBothDirections() {
        Point initialPosition = new Point(5, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.SOUTH
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateLeftCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:5:W", finalLocation);
    }

    @Test
    @DisplayName("Rover mirando al Oeste gira correctamente en ambas direcciones")
    void roverLookingWestRotatesOkInBothDirections() {
        Point initialPosition = new Point(5, 5);

        Rover rover = new Rover(
                initialPosition,
                grid,
                Orientation.WEST
        );

        Invoker invoker = new Invoker();
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateRightCommand(rover));
        invoker.addCommand(new RotateLeftCommand(rover));
        invoker.executeCommands();

        String finalLocation = rover.getLocation();

        assertEquals("5:5:N", finalLocation);
    }
}
