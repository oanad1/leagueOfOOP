package input;

import fileio.implementations.FileReader;
import players.Player;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to read input from the input file and load it
 * into GameInfo and Battlefield.
 */
public final class InputReader {

    public GameInfo inputLoad(final String inputFile) throws IOException {

        String fileString;
        FileReader fileReader = new FileReader(inputFile);

        //Read the number of rows and columns for the map
        int nrRows = fileReader.nextInt();
        int nrCols = fileReader.nextInt();

        //Create a new object in which to store the map and its associated methods
        Battlefield battlefield = Battlefield.getInstance();
        battlefield.createBattlefield(nrRows, nrCols);

        //Load a string containing the land type of each cell for a row in the map
        for (int i = 0; i < nrRows; i++) {
             fileString = fileReader.nextWord();
             battlefield.setBattlefieldRow(fileString, i);
        }

        //Read the number of players in the game an create an array list to store them
        int nrPlayers = fileReader.nextInt();
        ArrayList<Player> players = new ArrayList<>();

        //Use a Factory class to create an object of the same race as the player
        for (int i = 0; i < nrPlayers; i++) {
            fileString = fileReader.nextWord();
            int rowPos = fileReader.nextInt();
            int columnPos = fileReader.nextInt();

            //Add all players to the player array
            players.add(PlayersFactory.getPlayer(fileString.charAt(0), rowPos, columnPos, i));
        }

        //Read the number of rounds in the game
        int nrRounds = fileReader.nextInt();
        ArrayList<String> moves = new ArrayList<>();

        //For each round i, load a string with each player's moves
        for (int i = 0; i < nrRounds; i++) {
            moves.add(fileReader.nextWord());
        }

        fileReader.close();

        //Create an object which stores the players and moves arrays
        GameInfo gameInfo = new GameInfo(players, moves);
        return gameInfo;
    }
}
