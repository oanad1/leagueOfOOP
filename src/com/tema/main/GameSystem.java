package com.tema.main;

import com.tema.input.GameInfo;
import com.tema.players.Player;

import java.util.ArrayList;

public class GameSystem {

    public void playGame(GameInfo gameInfo){
        for(int i=0; i < gameInfo.getNrRounds(); i++){
            String roundMoves = gameInfo.getMoves().get(i);
            playRound(gameInfo.getPlayers(),roundMoves);
        }
    }

    public void playRound(ArrayList<Player> players, String moves){
        for(Player player: players){
            PlayerAction.Move(player,moves.charAt(player.getId()));
        }
        for(Player player: players){
            PlayerAction.Fight(player);
        }
    }
}
