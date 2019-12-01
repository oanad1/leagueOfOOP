package com.tema.main;

import com.tema.abilities.FightMode;
import com.tema.input.Battlefield;
import com.tema.players.Player;

public class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    public static void Move(Player player, char move){
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        if(player.getImmobilized()!= 0) {
            int imRounds = player.getImmobilized()-1;
            if(imRounds == 0) {
                player.setOvertimeDamage(0);
            }
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
        player.setCurrentHP((int) (player.getCurrentHP() - player.getRoundDamage()));
        if(player.getCurrentHP() <= 0){
            Player opponent = battlefield.GetOpponent(player);
            opponent.LevelUp(player.getLevel());
        }
    }

    public static void CheckDeath(Player player){
        if(player.getCurrentHP() <=0 && player.getLevel() > -1){

            Player opponent = battlefield.GetOpponent(player);
            opponent.LevelUp(player.getLevel());
            opponent.resetHP();

            player.setLevel(-1);
            battlefield.RemovePlayer(player);
        }
    }
}
