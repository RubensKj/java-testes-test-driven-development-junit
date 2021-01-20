package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AvaliadorTest {

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        // parte 1: cenario
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        // parte 2: acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        // parte 3: validacao
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void getMediaDosLances() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        Lance lanceJoao = new Lance(joao, 250.0);
        Lance lanceJose = new Lance(jose, 300.0);
        Lance lanceMaria = new Lance(maria, 400.0);

        leilao.propoe(lanceJoao);
        leilao.propoe(lanceJose);
        leilao.propoe(lanceMaria);

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double mediaDosLances = leiloeiro.getMedia();

        double mediaEsperada = 316.666666;

        assertEquals(mediaEsperada, mediaDosLances, 0.00001);
    }

    @Test
    public void testaMediaDeZeroLance() {
        // cenario
        Usuario ewertom = new Usuario("Ewertom");

        // acao
        Leilao leilao = new Leilao("Iphone 7");

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //validacao
        assertEquals(0, avaliador.getMedia(), 0.0001);
    }
}