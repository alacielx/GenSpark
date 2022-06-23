import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GoblinKillsBeforeDeath {
    Land game;
    Human player;
    @BeforeEach
    void setUp() {
        int humanAbility = 1;
        double humanWon=0, goblinsKilled=0;
        for(int i = 0 ; i < 10000; i++) {
            player = new Human();
            humanWon=0;
            player.generateLand();
            player.generateGoblins(10);
            player.spawn();
           // player.addAgility(1);
            while(player.getPosChar(player.getPos())=='H' && player.getGoblinCount() > 0){
                player.getGoblinList().get(0).setPos(player.getPos());
                player.setPos(player.getPos(),player.testFight());
                if(player.getPosChar(player.getPos()) == 'H'){
                    humanWon++;
                }
            }
            goblinsKilled += humanWon;
        }
        System.out.println("Average # of goblins killed before dying: " + goblinsKilled/10000);
        player.showStats();
    }

    @Test
    void fight() {
    }
}