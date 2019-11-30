package com.tema.players;

import com.tema.abilities.PlayerVisitor;

public interface Visitable {
    public void accept(PlayerVisitor visitor);
}
