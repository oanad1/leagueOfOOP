package main;

import abilities.PlayerVisitor;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;
import java.io.FileWriter;
import java.io.IOException;

public final class ScoreOutput implements PlayerVisitor {
    private final FileWriter fileWriter;

    public ScoreOutput(final FileWriter fileWriter) throws IOException {
        this.fileWriter = fileWriter;
    }

    public void printplayerdetails(final Player player) throws IOException {
        if (player.getLevel() == -1) {
            fileWriter.write("dead\n");
        } else {
            fileWriter.write(player.getLevel() + " "
                    + player.getCurrentXP() + " " + player.getCurrentHP() + " "
                    + player.getrowPos() + " " + player.getcolumnPos() + '\n');
        }
    }


    public void visit(final Pyromancer pyromancer) {
        try {

            fileWriter.write("P ");
            printplayerdetails(pyromancer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(final Rogue rogue) {
        try {

            fileWriter.write("R ");
            printplayerdetails(rogue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(final Wizard wizard) {
        try {

            fileWriter.write("W ");
            printplayerdetails(wizard);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(final Knight knight) {
        try {

            fileWriter.write("K ");
            printplayerdetails(knight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
