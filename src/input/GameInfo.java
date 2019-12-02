package input;

import players.Player;
import java.util.ArrayList;

/**
 * Helper class which stores information about the game:
 * the initial array of players, an array containing each
 * round's moves and the total number of rounds.
 */
public final class GameInfo {
    private final ArrayList<Player> players;  //The array of players in their initial input order
    private final ArrayList<String> moves;    //The array containing all moves in the game
    private final int nrRounds;               //The number of rounds in the current game

    public GameInfo(final ArrayList<Player> players, final ArrayList<String> moves) {
        this.players = players;
        this.moves = moves;
        this.nrRounds = moves.size();
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
}
