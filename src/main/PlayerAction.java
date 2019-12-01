package main;

import abilities.FightMode;
import input.Battlefield;
import players.Player;

public class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    public static void Move(Player player, char move){
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        if(player.getImmobilized()!= 0) {
            int imRounds = player.getImmobilized()-1;
            player.setImmobilized(imRounds);
            return;
        }

        battlefield.RemovePlayer(player);
        switch (move){
            case'U':
                player.setrowPos(rowPos-1);
                break;
            case 'D':
                player.setrowPos(rowPos+1);
                break;
            case 'L':
                player.setcolumnPos(columnPos-1);
                break;
            case 'R':
                player.setrowPos(columnPos+1);
                break;
            case '_':
                break;
        }
        battlefield.AddPlayer(player);
    }

    public static void Fight(Player player){
         Battlefield.Lot lot = battlefield.getLot(player);

         if(lot.getOccupants().size() == 2){
             player.accept(FightMode.getInstance());
         }
    }

    public static void SufferDamage(Player player){

        player.setCurrentHP(player.getCurrentHP() - player.getRoundDamage());
    }

    public static void CheckDeath(Player player){
        if(player.getCurrentHP() <=0 && player.getLevel() > -1){
            Battlefield.Lot lot = battlefield.getLot(player);

            if(lot.getOccupants().size() > 1 ) {
                Player opponent = battlefield.GetOpponent(player);
                opponent.LevelUp(player.getLevel());
            }

          //  opponent.resetHP();

            player.setLevel(-1);
            battlefield.RemovePlayer(player);
        }
    }
}
