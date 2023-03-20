package fr.publicis.sapient.models;

import fr.publicis.sapient.constant.Constants;
import fr.publicis.sapient.enums.Command;
import fr.publicis.sapient.enums.Orientation;

import java.util.function.BiPredicate;
import java.util.logging.Logger;


/**
 * Author: Abou FOFANA
 * At: 16/03/2023
 **/
public class Clipper {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Integer id;
    private final Position position;
    private final Lawn lawn;

    public Clipper(Position position, Lawn lawn) {
        this.position = position;
        this.lawn = lawn;
    }

    public Position getPosition() {
        return position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        LOGGER.info(() -> Constants.INFO_COLOR + "clipper@" + id + " actual direction is -> " +
                position.getOrientation() + Constants.HEAD_TEXT_COLOR);
    }

    /**
     * this function allow to forward the mower in its actual direction without modify its orientation
     */
    public void move() {

        Coordinates coordinate = position.getCoordinates();
        int x = coordinate.getX();
        int y = coordinate.getY();
        Orientation orientation = position.getOrientation();
        if (orientation.equals(Orientation.WEST) || orientation.equals(Orientation.EAST)) {
            x = orientation.equals(Orientation.WEST) ? x - 1 : x + 1;
        } else {
            y = orientation.equals(Orientation.NORTH) ? y + 1 : y - 1;
        }

        Coordinates coordinates = new Coordinates(x, y);
        if (cannotMove(x, y, coordinates)) return;

        position.setCoordinates(coordinates);
        lawn.addLocation(id, coordinates);
    }

    /**
     * @param x           axis
     * @param y           ordinate
     * @param coordinates coordinates of mower
     * @return a boolean that indicate if the mower can move at that coordinate
     */
    private boolean cannotMove(int x, int y, Coordinates coordinates) {
        BiPredicate<Integer, Integer> predicate = (a, b) -> (a > lawn.getWidth() || b > lawn.getLength())
                || (a < 0 || b < 0);

        if (predicate.test(x, y)) {
            LOGGER.info(() -> Constants.ERROR_COLOR + " The coordinates (" + coordinates + ") is out of lawn"
                    + Constants.HEAD_TEXT_COLOR);
            return true;
        }

        var map = lawn.getPreviousLocation();
        if (map.containsValue(coordinates)) {
            LOGGER.info(() -> Constants.ERROR_COLOR + " This position with the coordinates (" + coordinates + ") is busy"
                    + Constants.HEAD_TEXT_COLOR);
            return true;
        }

        return false;
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