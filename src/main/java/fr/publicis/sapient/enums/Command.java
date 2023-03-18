package fr.publicis.sapient.enums;

/**
 * Author: Abou FOFANA
 * At: 16/03/2023
 * this enumeration list the command to control the mower
 **/
public enum Command {
    MOVE_FORWARD {
        @Override
        public Character getShortName() {
            return 'A';
        }
    },
    RIGHT {
        @Override
        public Character getShortName() {
            return 'D';
        }
    },
    LEFT {
        @Override
        public Character getShortName() {
            return 'G';
        }
    };

    /**
     *
     * @return the abbreviation of command
     */
    public abstract Character getShortName();
}