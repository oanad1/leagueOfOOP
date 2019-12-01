package players;

import abilities.PlayerVisitor;

public interface Visitable {
     void accept(PlayerVisitor visitor);
}
