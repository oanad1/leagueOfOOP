package main;

import input.InputReader;
import input.GameInfo;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws IOException {

        //Read input from file and load it into the Battlefield and GameInfo classes
        InputReader inputReader = new InputReader();
        GameInfo gameInfo = inputReader.inputLoad(args[0]);

        //Create a class for the game structure
        GameSystem gameSystem = new GameSystem();

        //Play the game
        gameSystem.playGame(gameInfo);

        //Write the results to the output file using a Visitor object
        FileWriter fileWriter = new FileWriter(args[1]);
        ScoreOutput scoreOutput = new ScoreOutput(fileWriter);

        for (Player player: gameInfo.getPlayers()) {
            player.accept(scoreOutput);
        }
        fileWriter.close();
    }
}
