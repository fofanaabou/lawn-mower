package fr.publicis.sapient;

import fr.publicis.sapient.location.Coordinates;
import fr.publicis.sapient.location.Orientation;
import fr.publicis.sapient.location.Position;
import fr.publicis.sapient.mower.Clipper;
import fr.publicis.sapient.mower.Command;
import fr.publicis.sapient.mower.Lawn;

import java.util.logging.Logger;

import static fr.publicis.sapient.mower.Constants.INFO_COLOR;
import static fr.publicis.sapient.mower.Constants.TEXT_BACKGROUND_COLOR;

/**
 * Hello world!
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {
        Coordinates coordinates = new Coordinates(1,2);
        Position position = new Position(coordinates, Orientation.NORTH);


        LOGGER.info(() ->  INFO_COLOR + position + TEXT_BACKGROUND_COLOR);

        Clipper clipper = new Clipper(position);
        Lawn lawn = new Lawn(5,4);

        clipper.pivot(Command.LEFT.getShortName());
        clipper.pivot(Command.LEFT.getShortName());
        clipper.pivot(Command.RIGHT.getShortName());
        clipper.move(new Coordinates(8,4), lawn);
        LOGGER.info(() -> String.format("%s %s %s", INFO_COLOR, clipper, TEXT_BACKGROUND_COLOR));
    }
}
