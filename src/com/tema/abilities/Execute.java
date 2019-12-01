package com.tema.abilities;

import com.tema.abilities.PlayerVisitor;
import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Execute implements PlayerVisitor {
    private static Execute instance = null;

    private Execute(){}
    public static Execute getInstance() {
        if(instance == null) {
            instance = new Execute();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {

    }


    public void visit(Rogue rogue) {

    }


    public void visit(Wizard wizard) {

    }


    public void visit(Knight knight) {

    }
}