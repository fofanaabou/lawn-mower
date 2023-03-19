package fr.publicis.sapient.repository;

import fr.publicis.sapient.enums.Orientation;
import fr.publicis.sapient.models.Coordinates;
import fr.publicis.sapient.models.DataContainer;
import fr.publicis.sapient.models.LawnDimension;
import fr.publicis.sapient.models.Position;
import fr.publicis.sapient.mower.Clipper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Author: Abou FOFANA
 * At: 18/03/2023
 **/
public class DataRepository {

    private static final int INDEX_OF_FIRST_LINE = 0;
    private static final int ORIENTATION_INDEX  = 2;
    private static final int COORDINATE_FIRST_ELEMENT_INDEX = 0;
    private static final int COORDINATE_SECOND_ELEMENT_INDEX = 1;

    public DataContainer readData(String pathName) throws IOException {

        Path path = Paths.get(pathName);
        List<String> lines = Files.readAllLines(path.toAbsolutePath());

        DataContainer dataContainer = new DataContainer();

        LawnDimension lawnDimension = getLawnDimension(lines);
        dataContainer.setLawnDimension(lawnDimension);

        Map<Clipper, List<Character>> clippers = getClippersAndCommands(lines, lawnDimension);
        dataContainer.setClipperMap(clippers);

        return dataContainer;
    }

    /**
     * this function extract the dimension of lawn. The first line in file content this information
     * this line has two element, so we use 0 and 1 as index to retrieve their value from create list
     * @param lines list of data from file
     * @return the dimension of lawn
     */
    private LawnDimension getLawnDimension(List<String> lines) {

        List<Integer> dimensions = Arrays.stream(lines.get(INDEX_OF_FIRST_LINE).split(" "))
                .map(Integer::valueOf)
                .toList();

        return new LawnDimension(dimensions.get(0), dimensions.get(1));
    }

    /**
     * this function retrieve create clipper and set its information, then create list of commands that clipper
     * will execute.
     * @param lines list of data from file
     * @param lawnDimension lawn dimension
     * @return a map in which the key is clipper and the value is list of commands
     */
    private Map<Clipper, List<Character>> getClippersAndCommands(List<String> lines, LawnDimension lawnDimension) {

        Map<Clipper, List<Character>> clippers = new HashMap<>();
        int id = 1;

        for (int i = 1; i < lines.size(); i += 2) {

            String[] locations = lines.get(i).split(" ");

            Orientation orientation = getOrientation(locations[ORIENTATION_INDEX].charAt(0));
            Coordinates coordinates = new Coordinates(Integer.parseInt(locations[COORDINATE_FIRST_ELEMENT_INDEX]),
                    Integer.parseInt(locations[COORDINATE_SECOND_ELEMENT_INDEX]));
            Position position = new Position(coordinates, orientation);
            Clipper clipper = new Clipper(position, lawnDimension);

            List<Character> commands = new ArrayList<>();
            String commandString = lines.get(i + 1);
            for (int k = 0; k < commandString.length(); k++) {
                commands.add(commandString.charAt(k));
            }

            clipper.setId(id);
            clippers.put(clipper, commands);
            id++;
        }

        return clippers;
    }

    private Orientation getOrientation(Character character) {
        switch (character) {
            case 'N' -> {
                return Orientation.NORTH;
            }
            case 'W' -> {
                return Orientation.WEST;
            }
            case 'S' -> {
                return Orientation.SOUTH;
            }
            case 'E' -> {
                return Orientation.EAST;
            }

            default -> {
                return null;
            }
        }
    }
}
