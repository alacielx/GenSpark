import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HumanVsGoblins {
    static Human player;
    public static void playGame(int numberOfGoblins){
        player = new Human();

        player.generateLand();
        player.generateGoblins(numberOfGoblins);
        player.spawn();
        reloadScreen();

        while(!playerDied(player) && player.getGoblinCount() !=0){
            playerTurn();
            if(!playerDied(player) && player.getGoblinCount() !=0){
                goblinTurn();
            }
        }
        if(playerDied(player)){
            System.out.println("Game Over.");
        }else{
            System.out.println("You win!!");
        }
    }
    public static void playerTurn(){
        player.move(player.tryMove());
        reloadScreen();
        player.regen(2);
    }
    public static void goblinTurn(){
        for(Goblin x : player.getGoblinList()){
            x.followPlayer();
            if(Arrays.equals(x.getPos(),player.getPos())){
                break;
            }
        }
        reloadScreen();
    }
    public static void reloadScreen(){
        player.drawLand();
        player.showStats();
        player.showMessage();
    }
    public static boolean playerDied(Human player){
        return player.getPosChar(player.getPos()) != 'H';
    }
    public static int rand(int origin, int end) {
        Random rand = new Random();
        int result;
        result = rand.ints(origin, end + 1).findFirst().getAsInt();
        return result;
    }
    public static void pause(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
    }
}
