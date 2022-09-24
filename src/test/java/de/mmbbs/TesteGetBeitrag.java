package de.mmbbs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.mmbbs.menu.MainState;

public class TesteGetBeitrag {
    
    @Test
    public void testegetBeitragAlter() {
        MainState ms = new MainState(null);
        assertEquals(50, ms.getBeitrag(50, 1));
        assertEquals(50, ms.getBeitrag(20, 1));
        assertEquals(50, ms.getBeitrag(18, 1));
        assertEquals(30, ms.getBeitrag(17, 1));
        assertEquals(30, ms.getBeitrag(13, 1));
    }

    @Test
    public void testegetBeitragMitgliedsjahre() {
        MainState ms = new MainState(null);
        assertEquals(50, ms.getBeitrag(50, 1));
        assertEquals(45, ms.getBeitrag(50, 10));
        assertEquals(45, ms.getBeitrag(50, 15));
        assertEquals(40, ms.getBeitrag(50, 20));
        assertEquals(40, ms.getBeitrag(50, 25));
    }


}
