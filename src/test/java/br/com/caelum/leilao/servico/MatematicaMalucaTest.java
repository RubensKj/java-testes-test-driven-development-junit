package br.com.caelum.leilao.servico;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatematicaMalucaTest {

    @Test
    public void testValorMaiorQueTrinta() {

        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int valorMultiplicadoPorQuatro = matematicaMaluca.contaMaluca(31);

        assertEquals(31 * 4, valorMultiplicadoPorQuatro);
    }

    @Test
    public void testValorMaiorQueDez() {

        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int valorMultiplicadoPorTres = matematicaMaluca.contaMaluca(12);

        assertEquals(12 * 3, valorMultiplicadoPorTres);
    }

    @Test
    public void testValorMenorQueDez() {

        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int valorMultiplicadoPorDois = matematicaMaluca.contaMaluca(8);

        assertEquals(8 * 2, valorMultiplicadoPorDois);
    }
}