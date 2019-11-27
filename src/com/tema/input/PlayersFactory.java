package com.tema.input;

import com.tema.players.*;

public final class PlayersFactory {

    public static Player getPlayer(char type, int rowPos, int columnPos, int id){

        switch (type){
            case 'W':
                return new Wizard(rowPos,columnPos, id);

            case 'P':
                return new Pyromancer(rowPos,columnPos, id);

            case 'K':
                return new Knight(rowPos,columnPos, id);

            case 'R':
                return new Rogue(rowPos,columnPos, id);
        }
        return null;
    }
}
