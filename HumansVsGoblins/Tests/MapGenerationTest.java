import org.junit.jupiter.api.*;

class MapGenerationTest {
    Human player = new Human();

    @BeforeEach
    void setUp() {
        player.generateLand(2);
        player.generateGoblins(10);
        player.spawn();
        player.drawLand();
    }

    @Test
    void generateLand() {
    }

    @AfterEach
    void tearDown() {
    }
}