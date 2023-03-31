package fr.publicis.sapient.repository;

import fr.publicis.sapient.enums.Orientation;
import fr.publicis.sapient.models.Clipper;
import fr.publicis.sapient.models.Coordinates;
import fr.publicis.sapient.models.Lawn;
import fr.publicis.sapient.models.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Author: Abou FOFANA
 * At: 18/03/2023
 **/
public class DataRepository {

    private static final int INDEX_OF_FIRST_LINE = 0;
    private static final int ORIENTATION_INDEX = 2;
    private static final int COORDINATE_FIRST_ELEMENT_INDEX = 0;
    private static final int COORDINATE_SECOND_ELEMENT_INDEX = 1;

    public Map<Clipper, List<Character>> readData(String pathName) throws IOException {

        Path path = Paths.get(pathName);
        List<String> lines;

        try (Stream<String> s = Files.lines(path)) {
            lines = new ArrayList<>(s.toList());
        }

        Lawn lawn = getLawnDimension(lines);

        return getClippersAndCommands(lines, lawn);
    }

    /**
     * this function extract the dimension of lawn. The first line in file content this information
     * this line has two element, so we use 0 and 1 as index to retrieve their value from create list
     *
     * @param lines list of data from file
     * @return the dimension of lawn
     */
    private Lawn getLawnDimension(List<String> lines) {

        List<Integer> dimensions = Arrays.stream(lines.get(INDEX_OF_FIRST_LINE).split(" "))
                .map(Integer::valueOf)
                .toList();

        return new Lawn(dimensions.get(0), dimensions.get(1));
    }

    /**
     * this function retrieve create clipper and set its information, then create list of commands that clipper
     * will execute.
     *
     * @param lines list of data from file
     * @param lawn  lawn dimension
     * @return a map in which the key is clipper and the value is list of commands
     */
    private Map<Clipper, List<Character>> getClippersAndCommands(List<String> lines, Lawn lawn) {

        Map<Clipper, List<Character>> clippers = new TreeMap<>(Comparator.comparingInt(Clipper::getId));
        int id = 1;

        for (int i = 1; i < lines.size(); i += 2) {

            String[] locations = lines.get(i).split(" ");

            Orientation orientation = getOrientation(locations[ORIENTATION_INDEX].charAt(0));
            Coordinates coordinates = new Coordinates(Integer.parseInt(locations[COORDINATE_FIRST_ELEMENT_INDEX]),
                    Integer.parseInt(locations[COORDINATE_SECOND_ELEMENT_INDEX]));
            Position position = new Position(coordinates, orientation);
            Clipper clipper = new Clipper(position, lawn);

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
        return switch (character) {
            case 'N' -> Orientation.NORTH;

            case 'W' -> Orientation.WEST;

            case 'S' -> Orientation.SOUTH;

            case 'E' -> Orientation.EAST;

            default -> Orientation.valueOf(null);
        };
    }
}
