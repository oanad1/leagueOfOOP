package abilities;

import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public interface PlayerVisitor {
    void visit(Pyromancer pyromancer);

    void visit(Rogue rogue);

    void visit(Wizard wizard);

    void visit(Knight knight);
}
