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
        InputReader inputReader = new InputReader();
        GameInfo gameInfo = inputReader.inputLoad(args[0]);

        FileWriter fileWriter = new FileWriter(args[1]);
        ScoreOutput scoreOutput = new ScoreOutput(fileWriter);

        GameSystem gameSystem = new GameSystem();
        gameSystem.playGame(gameInfo);

        for (Player player: gameInfo.getPlayers()) {
            player.accept(scoreOutput);
        }
        fileWriter.close();
    }
}
