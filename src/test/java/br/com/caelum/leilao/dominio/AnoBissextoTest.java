package br.com.caelum.leilao.dominio;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AnoBissextoTest {

    @Test
    public void deveSerAnoBissexto() {
        AnoBissexto anoBissexto = new AnoBissexto();

        assertTrue(anoBissexto.ehBissexto(2024));
        assertTrue(anoBissexto.ehBissexto(2016));
        assertTrue(anoBissexto.ehBissexto(2012));
    }

    @Test
    public void naoDeveRetornarAnoBissexto() {
        AnoBissexto anoBissexto = new AnoBissexto();

        assertFalse(anoBissexto.ehBissexto(2022));
        assertFalse(anoBissexto.ehBissexto(2015));
        assertFalse(anoBissexto.ehBissexto(2011));
    }
}