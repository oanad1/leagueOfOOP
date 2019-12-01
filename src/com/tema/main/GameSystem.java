package com.tema.main;

import com.tema.abilities.FightMode;
import com.tema.input.Battlefield;
import com.tema.input.GameInfo;
import com.tema.players.Player;

import java.util.ArrayList;

public class GameSystem {

    public void playGame(GameInfo gameInfo){
        for(int i=0; i < gameInfo.getNrRounds(); i++){
            String roundMoves = gameInfo.getMoves().get(i);
            FightMode fightMode = FightMode.getInstance();
            fightMode.setRoundNr(i);
            playRound(gameInfo.getPlayers(),roundMoves);
        }
    }

    public void playRound(ArrayList<Player> players, String moves){
        for(Player player: players){
            if(player.isAlive()) {
                PlayerAction.Move(player, moves.charAt(player.getId()));
            }
        }

        for(Player player: players) {
            if(player.isAlive()) {
                Battlefield battlefield = Battlefield.getInstance();
                int damage = player.getCurrentHP() - player.getOvertimeDamage();
                player.setCurrentHP(damage);

                if (player.getCurrentHP() <= 0) {
                    battlefield.RemovePlayer(player);
                    player.setLevel(-1);
                }
            }
        }

        for(Player player: players){
            if(player.isAlive()) {
                PlayerAction.Fight(player);
            }
        }

        for(Player player: players){
            if(player.isAlive()) {
                PlayerAction.SufferDamage(player);
            }
        }

        for(Player player: players) {
                PlayerAction.CheckDeath(player);
        }
    }
}
