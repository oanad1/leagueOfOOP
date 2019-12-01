package com.tema.main;

import com.tema.abilities.FightMode;
import com.tema.abilities.PlayerVisitor;
import com.tema.input.Battlefield;
import com.tema.input.GameInfo;
import com.tema.players.*;
import fileio.FileSystem;

import java.io.FileWriter;
import java.io.IOException;

public class ScoreOutput implements PlayerVisitor {
    private final FileWriter fileWriter;

    public ScoreOutput(FileWriter fileWriter) throws IOException {
        this.fileWriter = fileWriter;
    }

    public void printPlayerDetails(Player player) throws IOException {
        if(player.getLevel() == -1) {
            fileWriter.write("dead\n");
        } else {
            fileWriter.write(player.getLevel() + " " + player.getCurrentXP() + " " + player.getCurrentHP() + " " +
                    player.getrowPos() + " " + player.getcolumnPos() + '\n');
        }
    }


    public void visit(Pyromancer pyromancer){
        try {

            fileWriter.write("P ");
            printPlayerDetails(pyromancer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(Rogue rogue){
        try {

            fileWriter.write("R ");
            printPlayerDetails(rogue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(Wizard wizard){
        try {

            fileWriter.write("W ");
            printPlayerDetails(wizard);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(Knight knight){
        try {

            fileWriter.write("K ");
            printPlayerDetails(knight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
