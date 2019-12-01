package main;

import abilities.FightMode;
import input.Battlefield;
import players.Player;

public final class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    private PlayerAction() { }

    public static void move(final Player player, final char move) {
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        if (player.getImmobilized() != 0) {
            int imRounds = player.getImmobilized() - 1;
            player.setImmobilized(imRounds);
            return;
        }

        battlefield.removePlayer(player);
        switch (move) {
            case'U':
                player.setrowPos(rowPos - 1);
                break;
            case 'D':
                player.setrowPos(rowPos + 1);
                break;
            case 'L':
                player.setcolumnPos(columnPos - 1);
                break;
            case 'R':
                player.setcolumnPos(columnPos + 1);
                break;
            case '_':
                break;
            default:
                break;
        }
        battlefield.addPlayer(player);
    }

    public static void fight(final Player player) {
         Battlefield.Lot lot = battlefield.getLot(player);

         if (lot.getOccupants().size() == 2) {
             player.accept(FightMode.getInstance());
         }
    }

    public static void sufferDamage(final Player player) {

        player.setCurrentHP(player.getCurrentHP() - player.getRoundDamage());
    }

    public static void checkDeath(final Player player) {

        if (player.getCurrentHP() <= 0 && player.getLevel() > -1) {
            Battlefield.Lot lot = battlefield.getLot(player);

            if (lot.getOccupants().size() > 1) {
                Player opponent = battlefield.getOpponent(player);
                opponent.gainXP(player.getLevel());
            }
            player.setLevel(-1);
            battlefield.removePlayer(player);
        }
    }
}
