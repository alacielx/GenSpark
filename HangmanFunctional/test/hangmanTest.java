import org.junit.jupiter.api.*;


class hangmanTest {

    @BeforeEach
     void setUp() {
    }

    @Test
    void wordGenerator() {
        System.out.println(hangman.wordGenerator("PHRASES"));
    }

    @Test
    void drawHangman() {
        hangman.drawHangman(4);
    }
}