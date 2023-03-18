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

        for(Map.Entry entry: clippers.entrySet()) {
            System.out.println(entry.getKey());
            start((Clipper) entry.getKey(), (List<Character>) entry.getValue(), lawnDimension);
        }


    }

    private static void start(Clipper clipper, List<Character> commands, LawnDimension lawnDimension) {


        int i = 0;
        int j = 1;
        for(char command: commands) {
            Coordinates coordinates = new Coordinates(i, j);
            switch (command) {
                case 'A' -> clipper.move(coordinates, lawnDimension);
                case 'D', 'G' -> {
                    clipper.pivot(command);
                    System.out.println(clipper);
                }
                default -> throw new IllegalArgumentException("invalid command");
            }

            i++;
            j++;
        }

    }
}
