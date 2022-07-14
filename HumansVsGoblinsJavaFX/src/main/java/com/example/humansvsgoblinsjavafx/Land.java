package com.example.humansvsgoblinsjavafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class Land extends Rectangle {
    Land(int x, int y){
        super(Main.getTileSize(), Main.getTileSize(), Color.ANTIQUEWHITE);
        setWidth(Main.getTileSize());
        setHeight(Main.getTileSize());

        setTranslateX(x * Main.getTileSize());
        setTranslateY(y * Main.getTileSize());
        setFill(Color.ANTIQUEWHITE);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }
    public static void moveGoblins(){

        for (Entity x : Main.getEntities()) {
            if (x.getClass().getSimpleName().equals("Goblin") && !x.isDead()){
                Goblin current = (Goblin)x;
                current.followPlayer();
            }
        }
    }
    public static int getGoblinCount(){
        int goblinCount = 0;
        for (Entity x : Main.getEntities()) {
            if(x.getClass().getSimpleName().equals("Goblin") && !x.isDead())
                goblinCount++;
        }
        return goblinCount;
    }
    public static List getDeadEntities(){
        List deadEntities = new ArrayList<>();
        for (Entity x : Main.getEntities()) {
            if (x.dead == true)
                deadEntities.add(x);
        }
        return deadEntities;
    }
}