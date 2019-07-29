package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerMainTest {
    ControllerMain cont = new ControllerMain();
    @Test
    void filtrowanie() {
        assertTrue(cont.filtrowanie("22", "24"));
        assertFalse(cont.filtrowanie("2s", ""));
        assertTrue(cont.filtrowanie("", ""));
        assertFalse(cont.filtrowanie("", "s"));
    }
}