package fr.publicis.sapient.location;

/**
 * Author: Abou FOFANA
 * At: 16/03/2023
 * this is enumeration for cardinal point that indicate the mower direction
 **/
public enum Orientation {
    NORTH {
        @Override
        public Character getShorName() {
            return 'N';
        }
    },
    EAST {
        @Override
        public Character getShorName() {
            return 'E';
        }
    },
    WEST {
        @Override
        public Character getShorName() {
            return 'W';
        }
    },
    SOUTH {
        @Override
        public Character getShorName() {
            return 'S';
        }
    };

    /**
     * The function return the abbreviation of the cardinal point
     * @return the first of the cardinal point
     */
    public abstract Character getShorName();
}
