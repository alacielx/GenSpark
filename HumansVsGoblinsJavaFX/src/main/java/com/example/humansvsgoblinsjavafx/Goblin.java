package com.example.humansvsgoblinsjavafx;

import javafx.scene.paint.Color;

public class Goblin extends Entity {
    private int health = 60, strength = 15;
    private int mapSize = Main.getMapSize();
    Main.rand rand = new Main.rand();
    public Goblin() {
        super(Color.RED);
        do {
            x = rand.ints(0, mapSize - 1);
            y = rand.ints(0, (mapSize / 2) - 1);
            setTranslateX(x * tileSize);
            setTranslateY(y * tileSize);
        }while(!isEmpty(this));

    }
    public void followPlayer(){
        Human player = Main.getPlayer();

        if(rand.ints(1,3) == 1) {
            /*
            check if y distance is further than x // [0] = y, [1] = x
            */
            if (Math.max(player.getxPos(), this.getxPos()) - Math.min(player.getxPos(), this.getxPos())
                    > Math.max(player.getyPos(), this.getyPos()) - Math.min(player.getyPos(), this.getyPos())) {
                if (player.getxPos() > this.getxPos()) {
                    moveRight();
                } else {
                    moveLeft();
                }
            } else {
                if (player.getyPos() > this.getyPos()) {
                    moveDown();
                } else {
                    moveUp();
                }
            }
        }
    }
    public int getHealth(){
        return health;
    }
    public int getStrength(){
        return strength;
    }
    public void damage(int damageCounter){
        health = Math.max(health - damageCounter, 0);
    }
}
