package com.tema.input;

import com.tema.players.*;
import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;

public final class FileReader {
    private final String inputFile;
    private final String outputFile;

    public FileReader(final String inputFile, final String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public GameInfo FileLoad() throws IOException {

        String fileString;
        FileSystem fileSystem = new FileSystem(inputFile, outputFile);

        int nrRows = fileSystem.nextInt();
        int nrCols = fileSystem.nextInt();

        Battlefield battlefield = Battlefield.getInstance();
        battlefield.CreateBattlefield(nrRows,nrCols);

        for(int i=0; i<nrRows; i++) {
             fileString = fileSystem.nextWord();
             battlefield.SetBattlefieldRow(fileString,i);
        }


        int nrPlayers = fileSystem.nextInt();
        ArrayList<Player> players = new ArrayList<>();

        for(int i=0; i<nrPlayers; i++) {
            fileString = fileSystem.nextWord();
            int rowPos = fileSystem.nextInt();
            int columnPos = fileSystem.nextInt();

            players.add(PlayersFactory.getPlayer(fileString.charAt(0),rowPos,columnPos,i));
        }

        int nrRounds = fileSystem.nextInt();
        ArrayList<String> moves = new ArrayList<>();

        for(int i=0; i<nrRounds; i++){
            moves.add(fileSystem.nextWord());
        }
        GameInfo gameInfo = new GameInfo(players,moves);
        return gameInfo;
    }
}
