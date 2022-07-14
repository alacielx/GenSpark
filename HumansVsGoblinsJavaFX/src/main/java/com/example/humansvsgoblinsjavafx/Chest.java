package com.example.humansvsgoblinsjavafx;

import javafx.scene.paint.Color;

public class Chest extends Entity{
    Main.rand rand = new Main.rand();
    Chest() {
        super(Color.ORANGE);
        do {
            x = rand.ints(0, mapSize - 1);
            y = rand.ints(0, (mapSize / 2) - 1);
            setTranslateX(x * tileSize);
            setTranslateY(y * tileSize);
        }while(!isEmpty(this));
        Main.addEntity(this);
    }
}
