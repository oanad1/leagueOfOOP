package com.tema.abilities;

import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public interface PlayerVisitor {
    public void visit(Pyromancer pyromancer);

    public void visit(Rogue rogue);

    public void visit(Wizard wizard);

    public void visit(Knight knight);
}
