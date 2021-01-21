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

        // parte 3: validacao
        assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(250, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario joao = new Usuario("João");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 1000.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(1000, avaliador.getMaiorLance(), 0.00001);
        assertEquals(1000, avaliador.getMenorLance(), 0.00001);
        assertEquals(1000, avaliador.getMedia(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        List<Lance> tresMaiores = avaliador.getTresMaiores();

        assertEquals(3, tresMaiores.size());
        assertEquals(400, tresMaiores.get(0).getValor(), 0.00001);
        assertEquals(300, tresMaiores.get(1).getValor(), 0.00001);
        assertEquals(200, tresMaiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void testeLeilaoComValoresRandomicos() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario jose = new Usuario("José");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(maria, 450.0));
        leilao.propoe(new Lance(jose, 120.0));
        leilao.propoe(new Lance(joao, 700.0));
        leilao.propoe(new Lance(maria, 630.0));
        leilao.propoe(new Lance(jose, 230.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(700, avaliador.getMaiorLance(), 0.00001);
        assertEquals(120, avaliador.getMenorLance(), 0.00001);
    }

    @Test
    public void testaLeilaoComValoresDecrecentes() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario jose = new Usuario("José");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(maria, 300.0));
        leilao.propoe(new Lance(jose, 200.0));
        leilao.propoe(new Lance(joao, 100.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(400.0, avaliador.getMaiorLance(), 0.00001);
        assertEquals(100.0, avaliador.getMenorLance(), 0.00001);
    }

    @Test
    public void leilaoComCincoLancesDeveEncontrarTresMaiores() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario jose = new Usuario("José");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(maria, 300.0));
        leilao.propoe(new Lance(jose, 200.0));
        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(joao, 140.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(3, avaliador.getTresMaiores().size());
        assertEquals(400, avaliador.getTresMaiores().get(0).getValor(), 0.00001);
        assertEquals(300, avaliador.getTresMaiores().get(1).getValor(), 0.00001);
        assertEquals(200, avaliador.getTresMaiores().get(2).getValor(), 0.00001);
    }

    @Test
    public void leilaoComDoisLancesDeveEncontrarDoisMaiores() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(maria, 300.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(2, avaliador.getTresMaiores().size());
        assertEquals(400, avaliador.getTresMaiores().get(0).getValor(), 0.00001);
        assertEquals(300, avaliador.getTresMaiores().get(1).getValor(), 0.00001);
    }

    @Test
    public void leilaoSemLanceDeveDevolverListaVazia() {
        Leilao leilao = new Leilao("Playstation 5 Novo");

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        assertEquals(0, avaliador.getTresMaiores().size());
    }

    @Test
    public void calculaMediaLances() {
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