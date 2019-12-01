package input;

import players.Player;

import java.util.ArrayList;

public final class Battlefield {
    private Lot[][] battlefieldMat;
    private int nrRows;
    private int nrCols;

    private static Battlefield instance = null;

    private Battlefield() { }
    public static Battlefield getInstance() {
        if (instance == null) {
            instance = new Battlefield();
        }
        return instance;
    }

    protected void createBattlefield(final int rows, final int columns) {
        this.battlefieldMat = new Lot[rows][columns];
        this.nrRows = rows;
        this.nrCols = columns;
    }

    protected void setBattlefieldRow(final String fileString, final int rowNumber) {

        for (int i = 0; i < nrCols; i++) {
            battlefieldMat[rowNumber][i] = new Lot(fileString.charAt(i));
        }
    }

    public void removePlayer(final Player player) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        battlefieldMat[rowPos][columnPos].removeOccupant(player);
    }

    public void addPlayer(final Player player) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        battlefieldMat[rowPos][columnPos].addOccupant(player);
    }

    public Lot getLot(final Player player) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();
        return battlefieldMat[rowPos][columnPos];
    }

    public Player getOpponent(final Player assailant) {

        Lot lot = getLot(assailant);
        Player opponent = null;

        for (Player p: lot.getOccupants()) {
            if (!p.equals(assailant)) {
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

    public final class Lot {
        private final char landType;
        private final ArrayList<Player> occupants;

        public Lot(final char landType) {
            this.landType = landType;
            occupants = new ArrayList<>();
        }

        public void removeOccupant(final Player player) {
            occupants.remove(player);
        }

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
