import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class hangmanTest {
    String randWord = "HELLO";
    char guess = 'O';
    @BeforeEach
    void setUp() {
        for(char x : randWord.toCharArray())
        hangman.guessed.add('_');
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkGuess() {
        assertEquals(0,hangman.checkGuess(guess, randWord),"Letter was not in the word");
    }
}