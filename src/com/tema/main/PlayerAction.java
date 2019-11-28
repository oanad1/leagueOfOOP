package com.tema.main;

import com.tema.abilities.FightMode;
import com.tema.input.Battlefield;
import com.tema.players.Player;

public class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    public static void Move(Player player, char move){
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        if(player.isImmobilized()) {
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
}
