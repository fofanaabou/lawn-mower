package fr.publicis.sapient.mower;

import fr.publicis.sapient.enums.Command;
import fr.publicis.sapient.models.Coordinates;
import fr.publicis.sapient.enums.Orientation;
import fr.publicis.sapient.models.LawnDimension;
import fr.publicis.sapient.models.Position;
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
        lawnDimension = new LawnDimension(3, 2);
        Coordinates coordinates = new Coordinates(0, 0);
        Position position = new Position(coordinates, Orientation.NORTH);
        clipper = new Clipper(position, lawnDimension);
    }

    @Test
    void pivot() {
        clipper.pivot(Command.LEFT.getShortName());
        clipper.pivot(Command.LEFT.getShortName());
        assertEquals(Orientation.SOUTH, clipper.getPosition().getOrientation());

        clipper.pivot(Command.RIGHT.getShortName());
        assertEquals(Orientation.WEST, clipper.getPosition().getOrientation());

    }

    @Test
    void move() {

        Coordinates c1 = new Coordinates(1, 1);
        Coordinates c2 = new Coordinates(2, 2);
        Coordinates c3 = new Coordinates(3, 2);

        assertAll(
                () -> {
                    clipper.move();
                    clipper.pivot('D');
                    clipper.move();
                    assertEquals(new Position(c1, Orientation.EAST), clipper.getPosition());
                },
                () -> {
                    clipper.move();
                    clipper.pivot('G');
                    clipper.move();
                    assertEquals(new Position(c2, Orientation.NORTH), clipper.getPosition());
                },
                ()-> {
                    clipper.move();
                    clipper.move();
                    clipper.pivot('D');
                    clipper.move();
                    assertEquals(new Position(c3, Orientation.EAST), clipper.getPosition());
                });
    }
}