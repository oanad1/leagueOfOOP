package input;

import angels.AngelVisitor;
import players.Player;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Helper class which stores information about the game:
 * the initial array of players, an array containing each
 * round's moves, the total number of rounds, a hashmap
 * containing the angels for each round.
 */
public final class GameInfo {
    private final ArrayList<Player> players;              //The array of players in order
    private final ArrayList<String> moves;                //The array with all the moves
    private final int nrRounds;                           //The number of rounds in the game
    private final HashMap<Integer, ArrayList<Angel>> angels;   //The angels for each round

    public GameInfo(final ArrayList<Player> players, final ArrayList<String> moves) {
        this.players = players;
        this.moves = moves;
        this.nrRounds = moves.size();
        this.angels = new HashMap<>();
    }


    /**
     * Adds angels in the hashmap based on a given round.
     * @param roundNr the current round number
     * @param roundAngels an array of angels corresponding to the current round
     */
    public void addAngelsRound(final int roundNr, final ArrayList<Angel> roundAngels) {
        angels.put(roundNr, roundAngels);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public int getNrRounds() {
        return nrRounds;
    }

    public HashMap<Integer, ArrayList<Angel>> getAngels() {
        return angels;
    }

    /**
     * Internal class used to retain an angel's position, name and
     * ability(an AngelVisitor object).
     */
    public static final class Angel {
        private int xCoord;
        private int yCoord;
        private AngelVisitor angelType;
        private String name;

        public Angel(final int xCoord, final int yCoord,
                     final AngelVisitor angelType, final String name) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.angelType = angelType;
            this.name = name;
        }

        public int getxCoord() {
            return xCoord;
        }

        public int getyCoord() {
            return yCoord;
        }

        public AngelVisitor getAngelType() {
            return angelType;
        }

        public String getName() {
            return name;
        }
    }
}
