import java.util.ArrayList;

public class Goblin extends Land {
    private int[] pos;
    private int health = 60, strength = 15;

    public Goblin(){
        do {
            pos = new int[]{rand(0, (getMapSize() /2) - 1), rand(0, getMapSize() - 1)};
        }while(!isEmpty(pos));
        setPos(pos,'G');
    }
    public void followPlayer(){
        int[] movePos = getPos().clone();

        //check if y distance is further than x // [0] = y, [1] = x
        if(Math.max(player.getPos()[0],getPos()[0])-Math.min(player.getPos()[0],getPos()[0])
                > Math.max(player.getPos()[1],getPos()[1])-Math.min(player.getPos()[1],getPos()[1])){
            if(player.getPos()[0] > getPos()[0]){
                movePos[0] += 1;
            }else{
                movePos[0] -= 1;
            }
        }else{
            if(player.getPos()[1] > getPos()[1]){
                movePos[1] += 1;
            }else{
                movePos[1] -= 1;
            }
        }
        if(getPosChar(movePos) == 'H' && rand(1,3) == 1){
            setEmptyPos(pos);
            pos = movePos.clone();
            setPos(pos, player.fight());
        }
        if(isEmpty(movePos) && rand(1,3) == 1){
            setEmptyPos(pos);
            pos = movePos.clone();
            setPos(pos,'G');
        }
    }
    public void setPos(int[] movePos){
        setEmptyPos(pos);
        pos = movePos.clone();
        setPos(pos,'G');
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
    public int[] getPos(){
        return pos;
    }
    public String toString(){
        return "Health: " + health + " Strength: " + strength + " Pos: " + pos[0] + "," + pos[1];
    }
}
