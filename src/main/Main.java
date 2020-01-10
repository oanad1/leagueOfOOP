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

        //Create an object to write the game output
        FileWriter fileWriter = new FileWriter(args[1]);
        ScoreOutput scoreOutput = new ScoreOutput(fileWriter);

        //Play the game
        gameSystem.playGame(gameInfo, fileWriter);

        //Write the final score
        fileWriter.write("~~ Results ~~" + '\n');

        for (Player player: gameInfo.getPlayers()) {
            player.accept(scoreOutput);
        }
        fileWriter.close();
    }
}
