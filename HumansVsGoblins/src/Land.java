import java.util.*;

public class Land extends HumanVsGoblins {
    static int mapSize;
    static char[][] map;
    static int[] chestPos;
    public static int goblinCount;
    static ArrayList<int[]> chests = new ArrayList<>();
    static ArrayList<Goblin> goblins, deadGoblins = new ArrayList<>();
    static ArrayList<String> message = new ArrayList<>();
    public void generateLand() {
        /* GENERATE MAP */
        mapSize = rand(10, 15);
        map = new char[mapSize][mapSize];
        for (int i = 0; i < map.length; i++) {
            for (int i2 = 0; i2 < map[i].length; i2++) {
                map[i][i2] = 'E';
            }
        }
    }
    public void generateLand(int size) {
        /* GENERATE MAP */
        mapSize = size;
        map = new char[mapSize][mapSize];
        for (int i = 0; i < map.length; i++) {
            for (int i2 = 0; i2 < map[i].length; i2++) {
                map[i][i2] = 'E';
            }
        }
    }
    public void generateGoblins(){
        goblins = new ArrayList<Goblin>();
        goblinCount = rand(3, 8);
        if(goblinCount > (mapSize / 2) * mapSize)
            goblinCount = (mapSize /2) * mapSize;

        for(int i = 0; i < goblinCount; i++){
            goblins.add(new Goblin());
        }
    }
    public void generateGoblins(int count){
        goblins = new ArrayList<Goblin>();
        goblinCount = count;
        if(goblinCount > (mapSize / 2) * mapSize)
            goblinCount = (mapSize /2) * mapSize;

        for(int i = 0; i < goblinCount; i++){
            goblins.add(new Goblin());
        }
    }
    public void generateChest(){
        do {
            chestPos = new int[]{rand(0, mapSize - 1), rand(0, mapSize - 1)};
        }while(!isEmpty(chestPos));
        chests.add(chestPos);
        setPos(chestPos,'C');
    }
    public void drawLand(){
        System.out.println();
        for (char[] firstCycle : map) {
            for (char secondCycle : firstCycle) {
                switch(secondCycle){
                    case 'C': System.out.print("  \uD83D\uDCBC  ");
                        break;
                    case 'H': System.out.print(" \uD83E\uDDD1 ");
                        break;
                    case 'G': System.out.print("  \uD83D\uDC7A  ");
                        break;
                    case 'E': System.out.print("  木  ");
                        break;
                    default: System.out.print(" " + secondCycle + " ");
                }
            }
            System.out.println();
        }
    }
    public Goblin getGoblin(int[] pos){
        for(Goblin x : goblins){
            if(Arrays.equals(x.getPos(),pos)){
                return x;
            }
        }
        return null;
    }
    public int getGoblinCount(){
        return goblinCount;
    }
    public void removeDeadGoblins(){
        goblins.removeAll(deadGoblins);
        deadGoblins.clear();
    }
    public void showMessage(){
        if(message.size() != 0){
            for(String str : message){
                System.out.println(str);
            }
            pause(1000);
            message.clear();
        }
    }
    public ArrayList<Goblin> getGoblinList(){
        return goblins;
    }
    public void setPos(int[] pos, char c){
        map[pos[0]][pos[1]] = c;
    }
    public void setEmptyPos(int[] pos){
        map[pos[0]][pos[1]] = 'E';
    }
    public boolean isEmpty(int[] pos){
        try{
            if(map[pos[0]][pos[1]] == 'E')
                return true;
        }catch(IndexOutOfBoundsException err){
            return false;
        }
        return false;
    }
    public char getPosChar(int[] pos){
        try{
            return map[pos[0]][pos[1]];
        }catch(IndexOutOfBoundsException err){
            return '0';
        }
    }
    public int getMapSize(){
        return mapSize;
    }
    public char[][] getMap(){
        return map;
    }
    public String toString(){
        return "";
    }
}