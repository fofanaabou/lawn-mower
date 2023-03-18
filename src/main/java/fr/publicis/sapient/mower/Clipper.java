package fr.publicis.sapient.mower;

import fr.publicis.sapient.location.Coordinates;
import fr.publicis.sapient.location.Orientation;
import fr.publicis.sapient.location.Position;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Author: Abou FOFANA
 * At: 16/03/2023
 **/
public class Clipper {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Position position;

    public Clipper(Position position) {
        this.position = position;
        LOGGER.setLevel(Level.INFO);
    }

    /**
     * this function allow to turn the mower either right or left
     *
     * @param command: parameter to indicate what direction to turn the mower
     */
    public void pivot(Character command) {
        if (command.equals(Command.RIGHT.getShortName())) {
            turnRight();
        } else if (command.equals(Command.LEFT.getShortName())) {
            turnLeft();
        }
        LOGGER.info(() -> Constants.INFO_COLOR + " The mower is now look at the direction -> " +
                position.getOrientation() + Constants.TEXT_BACKGROUND_COLOR);
    }

    /**
     * this function allow to forward the mower in its actual direction without modify its orientation
     *
     * @param coordinates: the location of the mower in lawn
     * @param lawn:        the lawn where mowers are deployed
     */
    public void move(Coordinates coordinates, Lawn lawn) {

        if (coordinates.getX() > lawn.width() || coordinates.getY() > lawn.height()) {
            LOGGER.info(() -> Constants.ERROR_COLOR + " The coordinates is out of lawn -> " +
                    position.getOrientation() + Constants.TEXT_BACKGROUND_COLOR);
            return;
        }

        position.setPosition(coordinates);
    }

    private void turnLeft() {
        var orientation = position.getOrientation();
        switch (orientation) {
            case NORTH -> position.setOrientation(Orientation.WEST);
            case EAST -> position.setOrientation(Orientation.NORTH);
            case SOUTH -> position.setOrientation(Orientation.EAST);
            case WEST -> position.setOrientation(Orientation.SOUTH);
        }
    }

    private void turnRight() {
        var orientation = position.getOrientation();
        switch (orientation) {
            case NORTH -> position.setOrientation(Orientation.EAST);
            case EAST -> position.setOrientation(Orientation.SOUTH);
            case SOUTH -> position.setOrientation(Orientation.WEST);
            case WEST -> position.setOrientation(Orientation.NORTH);
        }
    }

    @Override
    public String toString() {
        return position.toString();
    }
}