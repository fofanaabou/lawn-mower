package fr.publicis.sapient.mower;

import fr.publicis.sapient.location.Coordinates;
import fr.publicis.sapient.location.Orientation;
import fr.publicis.sapient.location.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Abou FOFANA
 * At: 18/03/2023
 **/
class ClipperTest {

    LawnDimension lawnDimension;
    Clipper clipper;

    @BeforeEach
    void setUp() {
        lawnDimension = new LawnDimension(9, 8);
        Coordinates coordinates = new Coordinates(0, 0);
        Position position = new Position(coordinates, Orientation.NORTH);
        clipper = new Clipper(position);

    }

    @Test
    void pivot() {
        clipper.pivot(Command.LEFT.getShortName());
        clipper.pivot(Command.LEFT.getShortName());
        assertEquals(Orientation.SOUTH, clipper.position.getOrientation());

        clipper.pivot(Command.RIGHT.getShortName());
        assertEquals(Orientation.WEST, clipper.position.getOrientation());

    }

    @Test
    void move() {

        Coordinates c1 = new Coordinates(10, 7);
        Coordinates c2 = new Coordinates(6, 11);
        Coordinates c3 = new Coordinates(5, 8);


        assertAll(
                () -> {
                    clipper.move(c1, lawnDimension);
                    assertNotEquals(new Position(c1, Orientation.NORTH), clipper.getPosition());
                },
                () -> {
                    clipper.move(c2, lawnDimension);
                    assertNotEquals(new Position(c2, Orientation.NORTH), clipper.getPosition());
                },
                ()-> {
                    clipper.move(c3, lawnDimension);
                    assertEquals(new Position(c3, Orientation.NORTH), clipper.getPosition());
                });

    }
}