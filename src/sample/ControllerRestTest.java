package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerRestTest {
    ControllerRest cont = new ControllerRest();
    @Test
    void dodajDanie() {
        assertTrue(cont.dodajDanie("Pizza", "24", "Włoska"));
        assertTrue(cont.dodajDanie("Pizza", "24,35", "Włoska"));
        assertFalse(cont.dodajDanie("Pizza", "24,3s5", "Włoska"));
        assertFalse(cont.dodajDanie("", "24,35", "Włoska"));
        assertFalse(cont.dodajDanie("Pizza", "24,35", ""));
        assertFalse(cont.dodajDanie("Pizza", "24,3s5", ""));
        assertFalse(cont.dodajDanie("", "24,35", ""));
    }
}