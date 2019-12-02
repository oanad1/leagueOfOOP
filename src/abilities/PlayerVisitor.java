package abilities;

import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Interface used for implementing a Visitor Pattern with 4 Visitable types:
 * Pyromancer, Rogue, Wizard and Knight
 */
public interface PlayerVisitor {
    void visit(Pyromancer pyromancer);

    void visit(Rogue rogue);

    void visit(Wizard wizard);

    void visit(Knight knight);
}
