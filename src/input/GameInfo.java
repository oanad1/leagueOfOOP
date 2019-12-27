package input;

import angels.AngelVisitor;
import players.Player;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Helper class which stores information about the game:
 * the initial array of players, an array containing each
 * round's moves and the total number of rounds.
 */
public final class GameInfo {
    private final ArrayList<Player> players;                     //The array of players in their initial input order
    private final ArrayList<String> moves;                       //The array containing all moves in the game
    private final int nrRounds;                                  //The number of rounds in the current game
    private final HashMap<Integer, ArrayList<Angel>> angels;

    public GameInfo(final ArrayList<Player> players, final ArrayList<String> moves) {
        this.players = players;
        this.moves = moves;
        this.nrRounds = moves.size();
        this.angels = new HashMap<>();
    }

    public void addAngelsRound(int roundNr, ArrayList<Angel> roundAngels) {
         angels.put(roundNr,roundAngels);
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


    public static class Angel {
        private int xCoord;
        private int yCoord;
        private AngelVisitor angelType;

        public Angel(int xCoord, int yCoord, AngelVisitor angelType) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.angelType = angelType;
        }

        public int getxCoord() {
            return xCoord;
        }

        public void setxCoord(int xCoord) {
            this.xCoord = xCoord;
        }

        public int getyCoord() {
            return yCoord;
        }

        public void setyCoord(int yCoord) {
            this.yCoord = yCoord;
        }

        public AngelVisitor getAngelType() {
            return angelType;
        }

        public void setAngelType(AngelVisitor angelType) {
            this.angelType = angelType;
        }

        @Override
        public String toString() {
            return "Angel{" +
                    "xCoord=" + xCoord +
                    ", yCoord=" + yCoord +
                    ", angelType=" + angelType +
                    '}';
        }
    }
}