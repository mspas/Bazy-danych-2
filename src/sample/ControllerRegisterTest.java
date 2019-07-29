package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerRegisterTest {
    ControllerRegister cont = new ControllerRegister();
    @Test
    void rejestracja() {
    assertTrue(cont.rejestracja("Przemek", "Nowak", "Wrocław", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
            , "Przemo", "Klient"));
        assertTrue(cont.rejestracja("Przemek", "Nowak", "Wrocław", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Dostawca"));
        assertTrue(cont.rejestracja("Przemek", "Nowak", "", "", "", "", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Dostawca"));
        assertTrue(cont.rejestracja("Przemek", "Nowak", "Wrocław", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Restauracja"));
        assertTrue(cont.rejestracja("", "", "Wrocław", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Restauracja"));
        assertFalse(cont.rejestracja("Przemek", "Nowak", "Wrocław", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Dostasdsawca"));
        assertFalse(cont.rejestracja("Przemek", "Nowak", "Wrocław", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", ""));
        assertFalse(cont.rejestracja("Przemek", "Nowak", "", "Przekątna", "5", "2", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Klient"));
        assertFalse(cont.rejestracja("Przemek", "Nowak", "", "Przekątna", "5s", "2s", "2134", "2134", "124364343" ,"Węglin"
                , "Przemo", "Klient"));
        assertFalse(cont.rejestracja("Przemek", "Nowak", "", "Przekątna", "5", "2", "2134", "inny", "124364343" ,"Węglin"
                , "Przemo", "Klient"));
    }
}