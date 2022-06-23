import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HumanWinPercentage {
    Land game;
    Human player;
    @BeforeEach
    void setUp() {
        double humanWon = 0;
        int humanAbility = 1;
        for(int i = 0 ; i < 10000; i++) {
            game = new Land();
            player = new Human();

            player.addAgility(humanAbility-1);
            game.generateLand();
            game.generateGoblins(5);
            player.spawn();
            game.getGoblinList().get(0).setPos(player.getPos());
            if(player.testFight() == 'H'){
                humanWon++;
            }
        }
        System.out.println("Percentage of human winning fight with "
                + humanAbility + " ability point(s): " + humanWon/100 + "%");
    }

    @Test
    void fight() {
    }
}