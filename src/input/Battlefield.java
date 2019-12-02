package input;

import players.Player;

import java.util.ArrayList;

/**
 * A class used to store the structure of the game's map, as
 * well as information about each player's current position;
 * Its methods are used to update the map after every round,
 * adding and removing players, as well as returning information
 * about their placement.
 */
public final class Battlefield {
    private Lot[][] battlefieldMat;       //A 2D arrray which stores information about each map cell
    private int nrRows;                   //The number of rows in the matrix
    private int nrCols;                   //The number of columns in the matrix

    private static Battlefield instance = null;

    private Battlefield() { }
    public static Battlefield getInstance() {
        if (instance == null) {
            instance = new Battlefield();
        }
        return instance;
    }


    /**
     * Method used to initialize a battlefield class from within the package.
     * @param rows number of rows for the map matrix
     * @param columns number of columns for the map matrix
     */
    protected void createBattlefield(final int rows, final int columns) {
        this.battlefieldMat = new Lot[rows][columns];
        this.nrRows = rows;
        this.nrCols = columns;
    }


    /**
     * Method used to initialize a row in the battlefield.
     * @param fileString a string containing all land types for the current row cells
     * @param rowNumber the current row number
     */
    protected void setBattlefieldRow(final String fileString, final int rowNumber) {

        for (int i = 0; i < nrCols; i++) {
            battlefieldMat[rowNumber][i] = new Lot(fileString.charAt(i));
        }
    }


    /**
     * Method used to remove a player from the battlefield.
     * @param player player to remove
     */
    public void removePlayer(final Player player) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        battlefieldMat[rowPos][columnPos].removeOccupant(player);
    }


    /**
     * Method used to add a player to the battlefield.
     * @param player player to add
     */
    public void addPlayer(final Player player) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        battlefieldMat[rowPos][columnPos].addOccupant(player);
    }


    /**
     * Method used to return a player's lot on the battlefield.
     * @param player player to search for
     * @return a Lot class where the player is stored in the occupants array.
     */
    public Lot getLot(final Player player) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();
        return battlefieldMat[rowPos][columnPos];
    }


    /**
     * Method which returns the opponent of a player.
     * @param player player who needs to know its opponent
     * @return the other occupant of the battlefield cell, or null if there isn't any.
     */
    public Player getOpponent(final Player player) {

        Lot lot = getLot(player);
        Player opponent = null;

        for (Player p: lot.getOccupants()) {
            if (!p.equals(player)) {
                opponent = p;
            }
        }
        return opponent;
    }


    public Lot[][] getBattlefieldMat() {
        return battlefieldMat;
    }

    public int getNrRows() {
        return nrRows;
    }

    public int getNrCols() {
        return nrCols;
    }

    /**
     * Internal class representing a cell in the battlefield matrix;
     * It stores information about the type of land and references to
     * the players which are currently placed on it.
     */
    public final class Lot {
        private final char landType;                 //Type of land inside the cell
        private final ArrayList<Player> occupants;   //An array of players with this position

        public Lot(final char landType) {
            this.landType = landType;
            occupants = new ArrayList<>();
        }

        /**
         * Method used to remove a player from the lot.
         * @param player player to remove
         */
        public void removeOccupant(final Player player) {
            occupants.remove(player);
        }

        /**
         * Method used to add a player to the lot.
         * @param player player to add
         */
        public void addOccupant(final Player player) {
            occupants.add(player);
        }

        public char getLandType() {
            return landType;
        }

        public ArrayList<Player> getOccupants() {
            return occupants;
        }
    }

}
