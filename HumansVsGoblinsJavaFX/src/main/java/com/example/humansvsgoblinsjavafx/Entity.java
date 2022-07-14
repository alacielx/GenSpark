package com.example.humansvsgoblinsjavafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Entity extends Rectangle {
    boolean dead = false;
    int x,y;
    static int mapSize = Main.getMapSize(), tileSize = Main.getTileSize();
    Entity(Color color){
        super(tileSize,tileSize, color);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }
    public boolean moveLeft(){
        boolean moved;
        setTranslateX(getTranslateX() - getWidth() >= 0 && canMove(-1,0) ? getTranslateX() - getWidth() : getTranslateX());
        moved = x == (int)getTranslateX() / tileSize ? false : true;
        x = (int)getTranslateX() / tileSize;
        return moved;
    }
    public boolean moveRight(){
        boolean moved;
        setTranslateX(getTranslateX() + getWidth() < mapSize * getWidth() && canMove(1,0) ? getTranslateX() + getWidth() : getTranslateX());
        moved = x == (int)getTranslateX() / tileSize ? false : true;
        x = (int)getTranslateX() / tileSize;
        return moved;
    }
    public boolean moveUp(){
        boolean moved;
        setTranslateY(getTranslateY() - getWidth() >= 0 && canMove(0,-1) ? getTranslateY() - getWidth() : getTranslateY());
        moved = y == (int)getTranslateX() / tileSize ? false : true;
        y = (int)getTranslateY() / tileSize;
        return moved;
    }
    public boolean moveDown(){
        boolean moved;
        setTranslateY(getTranslateY() + getWidth() < mapSize * getWidth() && canMove(0,1) ? getTranslateY() + getWidth() : getTranslateY());
        moved = y == (int)getTranslateX() / tileSize ? false : true;
        y = (int)getTranslateY() / tileSize;
        return moved;
    }
    public void kill(){dead=true;}
    public boolean isDead(){return dead;}
    public boolean isEmpty(Entity e){
        for (Entity x : Main.getEntities()){
            if(e.getxPos() == x.getxPos() && e.getyPos() == x.getyPos() && e != x)
                return false;
        }
        return true;
    }
    public boolean canMove(int xPos, int yPos){
        for (Entity x : Main.getEntities()){
            if(this.getxPos()+xPos == x.getxPos() && this.getyPos()+yPos == x.getyPos() && this.getClass().getSimpleName().equals(x.getClass().getSimpleName()))
                return false;
        }
        return true;
    }
    public Entity getEntity(Entity e){
        for (Entity x : Main.getEntities()){
            if(e.getTranslateX() == x.getTranslateX() && e.getTranslateY() == x.getTranslateY() && e != x)
                return x;
        }
        return null;
    }
    public int getxPos(){return x;}
    public int getyPos(){return y;}
}
