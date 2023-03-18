package fr.publicis.sapient.location;

import java.util.Objects;

public class Position {
    private Coordinates coordinates;
    private Orientation orientation;

    public Position(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return coordinates.equals(position.coordinates) && orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, orientation);
    }

    @Override
    public String toString() {
        return  coordinates + " " + orientation.getShorName();
    }
}
