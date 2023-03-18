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

    public DataContainer readData(String pathName) throws IOException {
        Path path = Paths.get(pathName);
        List<String> lines = Files.readAllLines(path.toAbsolutePath());

        DataContainer dataContainer = new DataContainer();
        LawnDimension lawnDimension = createLawnDimensionFromFileData(lines);
        dataContainer.setLawnDimension(lawnDimension);

        Map<Clipper, List<Character>> clippers = getClippers(lines);
        dataContainer.setClipperMap(clippers);

        System.out.println(lawnDimension);

        return dataContainer;
    }

    private LawnDimension createLawnDimensionFromFileData(List<String> lines) {

        List<Integer> dimensions = Arrays.stream(lines.get(0).split(" "))
                .map(Integer::valueOf)
                .toList();

        return new LawnDimension(dimensions.get(0), dimensions.get(1));
    }

    private Map<Clipper, List<Character>> getClippers(List<String> lines) {
        Map<Clipper, List<Character>> clippers = new HashMap<>();
        for (int i = 1; i < lines.size(); i += 2) {

            String[] locations = lines.get(i).split(" ");
            Orientation orientation = getOrientation(locations[2].charAt(0));
            Coordinates coordinates = new Coordinates(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]));
            Position position = new Position(coordinates, orientation);
            Clipper clipper = new Clipper(position);

            List<Character> commands = new ArrayList<>();
            String commandString = lines.get(i + 1);
            for (int k = 0; k < commandString.length(); k++) {
                commands.add(commandString.charAt(k));
            }
            System.out.println(commands);
            clippers.put(clipper, commands);
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
