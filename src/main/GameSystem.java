package main;

import abilities.FightMode;
import input.Battlefield;
import input.GameInfo;
import players.Player;
import players.Wizard;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameSystem {

    public void playGame(GameInfo gameInfo, ScoreOutput scoreOutput, FileWriter fileWriter){
        for(int i=0; i < gameInfo.getNrRounds(); i++){
            String roundMoves = gameInfo.getMoves().get(i);
            FightMode fightMode = FightMode.getInstance();
            fightMode.setRoundNr(i);
          /*   try {
                fileWriter.write("ROUND " + i + "\n\n"); */
                playRound(gameInfo.getPlayers(),roundMoves, scoreOutput);
        /*        fileWriter.write("ROUND END "+ "\n\n");
             } catch (IOException e) {
                e.printStackTrace();
            } */
        }
    }

    public void playRound(ArrayList<Player> players, String moves,ScoreOutput scoreOutput){
        for(Player player: players){
            if(player.isAlive()) {
                player.setRoundDamage(0);
                PlayerAction.Move(player, moves.charAt(player.getId()));
            }
        }

        for(Player player: players) {
            if(player.isAlive() && player.getOvertimeDamage() != 0) {
                Battlefield battlefield = Battlefield.getInstance();
                int damage = player.getCurrentHP() - player.getOvertimeDamage();
                player.setCurrentHP(damage);
                player.setOvertimeRounds(player.getOvertimeRounds()-1);

                if(player.getOvertimeRounds()==0) {
                    player.setOvertimeDamage(0);
                }

                if (player.getCurrentHP() <= 0) {
                    battlefield.RemovePlayer(player);
                    player.setLevel(-1);
                }
            }
        }

        for(Player player: players){
            if(player.isAlive() && player.getPriority()) {
                PlayerAction.Fight(player);
            }
        }

        for(Player player: players){
            if(player.isAlive() && !player.getPriority()) {
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
             //   player.accept(scoreOutput);
        }
    }
}
