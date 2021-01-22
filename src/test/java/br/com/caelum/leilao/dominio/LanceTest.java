package br.com.caelum.leilao.dominio;

import org.junit.Before;
import org.junit.Test;

public class LanceTest {

    private Usuario usuario;

    @Before
    public void setUp() {
        this.usuario = new Usuario("João");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaExcecaoCasoLanceSejaNegativo() {
        new Lance(usuario, -50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaExcecaoCasoLanceSejaIgualZero() {
        new Lance(usuario, 0);
    }
}