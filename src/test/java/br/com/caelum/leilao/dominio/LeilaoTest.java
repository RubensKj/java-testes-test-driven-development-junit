package br.com.caelum.leilao.dominio;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeilaoTest {

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Playstation 5 Novo");

        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("João"), 2000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = new Leilao("Playstation 5");

        leilao.propoe(new Lance(new Usuario("João"), 2000));
        leilao.propoe(new Lance(new Usuario("Maria"), 3000));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor());
        assertEquals(3000, leilao.getLances().get(1).getValor());
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook");

        Usuario usuario = new Usuario("João");

        leilao.propoe(new Lance(usuario, 2000));
        leilao.propoe(new Lance(usuario, 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor());
    }

    @Test
    public void naoDeveAceitarMaisDoQueCincoLancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook");

        Usuario maria = new Usuario("Maria");
        Usuario joao = new Usuario("João");

        leilao.propoe(new Lance(maria, 2000));
        leilao.propoe(new Lance(joao, 3000));

        leilao.propoe(new Lance(maria, 4000));
        leilao.propoe(new Lance(joao, 5000));

        leilao.propoe(new Lance(maria, 6000));
        leilao.propoe(new Lance(joao, 7000));

        leilao.propoe(new Lance(maria, 8000));
        leilao.propoe(new Lance(joao, 9000));

        leilao.propoe(new Lance(maria, 10000));
        leilao.propoe(new Lance(joao, 11000));

        // Deve ser ignorado
        leilao.propoe(new Lance(maria, 12000));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOLanceCasoOUsuarioTenhaDadoUmLance() {
        Leilao leilao = new Leilao("PS5");

        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        leilao.propoe(new Lance(joao, 200));
        leilao.propoe(new Lance(maria, 300));
        leilao.dobraLance(joao);

        assertEquals(3, leilao.getLances().size());
        assertEquals(400, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("PS5");

        Usuario joao = new Usuario("João");

        leilao.dobraLance(joao);

        assertEquals(0, leilao.getLances().size());
    }
}