package com.tema.main;

import com.tema.input.Battlefield;
import com.tema.players.Player;

public class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    public static void Move(Player player, char move){
        int xCoord = player.getrowPos();
        int yCoord = player.getcolumnPos();

        if(player.isImobilized()) {
            return;
        }
        battlefield.RemovePlayer(player);
        switch (move){
            case'U':
                player.setcolumnPos(yCoord-1);
                break;
            case 'D':
                yCoord++;
        }
        battlefield.AddPlayer(player);
    }

    public static void Fight(Player player){

    }
}
