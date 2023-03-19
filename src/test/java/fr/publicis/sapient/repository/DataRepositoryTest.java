package fr.publicis.sapient.repository;

import fr.publicis.sapient.enums.Orientation;
import fr.publicis.sapient.models.Coordinates;
import fr.publicis.sapient.models.DataContainer;
import fr.publicis.sapient.models.Position;
import fr.publicis.sapient.mower.Clipper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: Abou FOFANA
 * At: 19/03/2023
 **/
class DataRepositoryTest {

    private static final String FILE_PATH = "src/main/resources/data/mowers-info.txt";
    DataRepository dataRepository;
    DataContainer dataContainer;

    @BeforeEach
    void setUp() throws IOException {
        dataRepository = new DataRepository();
        dataContainer = dataRepository.readData(FILE_PATH);

    }

    @Test
    void readData() {

        Map<Clipper, List<Character>> clipperMap =  dataContainer.getClipperMap();
        List<Clipper> clippers = clipperMap.keySet()
                .stream()
                .sorted(Comparator.comparingInt(Clipper::getId))
                .toList();

        assertAll(
                () -> {
                    Clipper clipper = clippers.get(0);
                    Position position = clipper.getPosition();
                    assertEquals(Orientation.NORTH, position.getOrientation());
                    assertEquals(new Coordinates(1,2), position.getCoordinates());
                },
                () -> {
                    Clipper clipper = clippers.get(1);
                    Position position = clipper.getPosition();
                    assertEquals(Orientation.EAST, position.getOrientation());
                    assertEquals(new Coordinates(3,3), position.getCoordinates());
                }
        );
    }
}