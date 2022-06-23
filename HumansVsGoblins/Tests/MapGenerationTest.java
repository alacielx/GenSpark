import org.junit.jupiter.api.*;

class MapGenerationTest {
    Human player = new Human();

    @BeforeEach
    void setUp() {
        player.generateLand(10);
        player.generateGoblins();
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