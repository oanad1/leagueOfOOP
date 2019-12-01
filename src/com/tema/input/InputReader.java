package com.tema.input;

import com.tema.players.*;
import fileio.implementations.FileReader;

import java.io.IOException;
import java.util.ArrayList;

public final class InputReader {

    public GameInfo InputLoad(final String inputFile) throws IOException {

        String fileString;
        FileReader fileReader = new FileReader(inputFile);

        int nrRows = fileReader.nextInt();
        int nrCols = fileReader.nextInt();

        Battlefield battlefield = Battlefield.getInstance();
        battlefield.CreateBattlefield(nrRows,nrCols);

        for(int i=0; i<nrRows; i++) {
             fileString = fileReader.nextWord();
             battlefield.SetBattlefieldRow(fileString,i);
        }


        int nrPlayers = fileReader.nextInt();
        ArrayList<Player> players = new ArrayList<>();

        for(int i=0; i<nrPlayers; i++) {
            fileString = fileReader.nextWord();
            int rowPos = fileReader.nextInt();
            int columnPos = fileReader.nextInt();

            players.add(PlayersFactory.getPlayer(fileString.charAt(0),rowPos,columnPos,i));
        }

        int nrRounds = fileReader.nextInt();
        ArrayList<String> moves = new ArrayList<>();

        for(int i=0; i<nrRounds; i++){
            moves.add(fileReader.nextWord());
        }
        fileReader.close();
        GameInfo gameInfo = new GameInfo(players,moves);
        return gameInfo;
    }
}
