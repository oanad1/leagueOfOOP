package players;

import abilities.PlayerVisitor;

public interface Visitable {
    public void accept(PlayerVisitor visitor);
}
