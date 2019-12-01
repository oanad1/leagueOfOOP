package input;

import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

public final class PlayersFactory {

    private PlayersFactory() { }

    public static Player getPlayer(final char type, final int rowPos,
                                   final int columnPos, final int id) {

        switch (type) {
            case 'W':
                return new Wizard(rowPos, columnPos, id);

            case 'P':
                return new Pyromancer(rowPos, columnPos, id);

            case 'K':
                return new Knight(rowPos, columnPos, id);

            case 'R':
                return new Rogue(rowPos, columnPos, id);

            default:
                return null;
        }
    }
}
