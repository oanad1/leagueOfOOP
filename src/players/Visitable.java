package players;

import abilities.PlayerVisitor;

/**
 * An interface for visitable objects with type player
 */
public interface Visitable {
     void accept(PlayerVisitor visitor);
}
