package main;

import abilities.PlayerVisitor;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Visitor class which outputs the corresponding final result for each player
 * **/
public final class ScoreOutput implements PlayerVisitor {
    private final FileWriter fileWriter;

    public ScoreOutput(final FileWriter fileWriter) throws IOException {
        this.fileWriter = fileWriter;
    }

    /**
     * Writes data common to all players
     * @param player the player whose information is written
     * @throws IOException
     */
    public void printPlayerDetails(final Player player) throws IOException {
        if (player.getLevel() == -1) {
            fileWriter.write("dead\n");
        } else {
            fileWriter.write(player.getLevel() + " "
                    + player.getCurrentXP() + " " + player.getCurrentHP() + " "
                    + player.getrowPos() + " " + player.getcolumnPos() + '\n');
        }
    }

    /**
     * Visitor pattern method for outputting the results of a pyromancer player
     * @param pyromancer visitable object
     */
    public void visit(final Pyromancer pyromancer) {
        try {

            fileWriter.write("P ");
            printPlayerDetails(pyromancer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visitor pattern method for outputting the results of a rogue player
     * @param rogue visitable object
     */
    public void visit(final Rogue rogue) {
        try {

            fileWriter.write("R ");
            printPlayerDetails(rogue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visitor pattern method for outputting the results of a wizard player
     * @param wizard visitable object
     */
    public void visit(final Wizard wizard) {
        try {

            fileWriter.write("W ");
            printPlayerDetails(wizard);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visitor pattern method for outputting the results of a knight player
     * @param knight visitable object
     */
    public void visit(final Knight knight) {
        try {

            fileWriter.write("K ");
            printPlayerDetails(knight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
