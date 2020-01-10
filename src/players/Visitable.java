package players;

import main.PlayersVisitor;
import angels.AngelVisitor;

/**
 * An interface for visitable objects with type player.
 */
public interface Visitable {
     void accept(PlayersVisitor visitor);
     void accept(AngelVisitor visitor);
}
