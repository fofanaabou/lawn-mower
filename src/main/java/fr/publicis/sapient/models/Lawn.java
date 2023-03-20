package fr.publicis.sapient.models;

import java.util.*;

/**
 * Author: Abou FOFANA
 * At: 16/03/2023
 **/
public class Lawn {
    private final int width;
    private final int length;

    // this map store the last position's coordinate of the previous mowers
    private final Map<Integer, Coordinates> previousLocation = new HashMap<>();

    public Lawn(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public Map<Integer, Coordinates> getPreviousLocation() {
        return previousLocation;
    }

    /**
     * add to the map the last position of clipper
     * @param id of the clipper
     * @param coordinates of clipper position
     */
    public void addPreviousLocation(Integer id, Coordinates coordinates) {
        previousLocation.put(id, coordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lawn lawn = (Lawn) o;
        return width == lawn.width && length == lawn.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length);
    }

    @Override
    public String toString() {
        return "Lawn{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
