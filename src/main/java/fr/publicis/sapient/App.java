package fr.publicis.sapient;

import fr.publicis.sapient.models.DataContainer;
import fr.publicis.sapient.repository.DataRepository;
import fr.publicis.sapient.models.Coordinates;
import fr.publicis.sapient.enums.Orientation;
import fr.publicis.sapient.models.Position;
import fr.publicis.sapient.mower.Clipper;
import fr.publicis.sapient.enums.Command;
import fr.publicis.sapient.models.LawnDimension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static fr.publicis.sapient.constant.Constants.INFO_COLOR;
import static fr.publicis.sapient.constant.Constants.HEAD_TEXT_COLOR;

/**
 * Lawn mower application
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String p = "src/main/resources/data/mowers-info.txt";
    private static final DataRepository dataRepository = new DataRepository();



    public static void main(String[] args) throws IOException {


        DataContainer dataContainer = dataRepository.readData(p);
        LawnDimension lawnDimension = dataContainer.getLawnDimension();

        Map<Clipper, List<Character>> clippers = dataContainer.getClipperMap();

        List<Clipper> cl = clippers.keySet().stream().sorted(Comparator.comparingInt(Clipper::getId)).toList();

        List<Position> positions = new ArrayList<>();

        for(Clipper clipper: cl) {
            start(clipper, clippers.get(clipper));

            positions.add(clipper.getPosition());
        }

        for(Position position: positions) {
            System.out.println("position: " + position);
        }


    }

    private static void start(Clipper clipper, List<Character> commands) {

        for(char command: commands) {
            switch (command) {
                case 'A' -> clipper.move();
                case 'D', 'G' -> clipper.pivot(command);
                default -> throw new IllegalArgumentException("invalid command");
            }
        }
    }
}
