package com.tema.main;

import com.tema.input.FileReader;
import com.tema.input.GameInfo;

import java.io.IOException;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        FileReader fileReader = new FileReader(args[0], args[1]);
        GameInfo gameInfo = fileReader.FileLoad();

        GameSystem gameSystem = new GameSystem();
        gameSystem.playGame(gameInfo);

    }
}