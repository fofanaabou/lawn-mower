package fr.publicis.sapient;

import fr.publicis.sapient.models.Clipper;
import fr.publicis.sapient.repository.DataRepository;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import static fr.publicis.sapient.constant.Constants.HEAD_TEXT_COLOR;

/**
 * Lawn mower application
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String TABLE_LINE = "+-----------------+------+%n";

    private static final DataRepository dataRepository = new DataRepository();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Enter the file's path (Example -> /user/home/data/info.txt):");
        boolean isValidPath = true;

        while (isValidPath) {
            String path = scanner.nextLine();
            Map<Clipper, List<Character>> clipperMap = new HashMap<>();

            try {
                clipperMap = dataRepository.readData(path);

                List<Clipper> clippers = clipperMap.keySet()
                        .stream()
                        .toList();

                for (Clipper clipper : clippers) {
                    start(clipper, clipperMap.get(clipper));
                }

                displayPositions(clippers);

            } catch (IOException e) {
                LOGGER.info(HEAD_TEXT_COLOR + "File not found please enter a valid path:" + HEAD_TEXT_COLOR);
            }

            isValidPath = clipperMap.isEmpty();
        }
    }

    private static void displayPositions(List<Clipper> clippers) {

        LOGGER.info("=================Clippers info==============");
        String leftAlignFormat = "| %-15s | %-4s |%n";

        System.out.format(TABLE_LINE);
        System.out.format("| Last position   | ID   |%n");
        System.out.format(TABLE_LINE);
        for (Clipper clipper : clippers) {
            System.out.format(leftAlignFormat, clipper.getPosition(), clipper.getId());
        }
        System.out.format(TABLE_LINE);
    }

    private static void start(Clipper clipper, List<Character> commands) {

        for (var command : commands) {
            switch (command) {
                case  'A' -> clipper.move();
                case 'D', 'G' -> clipper.pivot(command);
                default -> throw new IllegalArgumentException("invalid command");
            }
        }
    }
}
