import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void tryInt() {
        Main.test = 1;
        assertEquals(1,Main.tryInt(),"Nah");
    }

    @Test
    void tryChar() {
        Main.test = 'y';
        assertEquals('y',Main.tryChar(),"Nah");
    }

    @Test
    void main() {
    }

    @AfterEach
    void tearDown() {
    }

}