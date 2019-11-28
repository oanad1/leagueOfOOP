package com.tema.players;

import com.tema.abilities.PlayerVisitor;

interface Visitable {
    public void accept(PlayerVisitor visitor);
}
