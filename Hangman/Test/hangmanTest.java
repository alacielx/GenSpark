import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class hangmanTest {
    String randWord = "HELLO";
    char guess = 'O';
    @BeforeEach
    void setUp() {
        hangman.guessed = new ArrayList<>();
        hangman.missed = new ArrayList<>();
        for(char x : randWord.toCharArray())
        hangman.guessed.add('_');
    }

    @Test
    void checkGuess() {
        assertEquals(0,hangman.checkGuess(guess, randWord),"Letter was not in the word");
    }

    @AfterEach
    void tearDown() {
    }
}