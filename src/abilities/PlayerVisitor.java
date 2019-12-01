package abilities;

import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public interface PlayerVisitor {
    public void visit(Pyromancer pyromancer);

    public void visit(Rogue rogue);

    public void visit(Wizard wizard);

    public void visit(Knight knight);
}
