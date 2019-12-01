package input;

import players.Player;

import java.util.ArrayList;

public final class GameInfo {
    private final ArrayList<Player> players;
    private final ArrayList<String> moves;
    private final int nrRounds;

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
