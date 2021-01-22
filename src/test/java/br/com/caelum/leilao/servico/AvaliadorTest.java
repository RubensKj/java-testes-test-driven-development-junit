package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.*;

import java.util.List;

import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    // It runs before each test case
    @Before
    public void setUp() {
        joao = new Usuario("João");
        jose = new Usuario("José");
        maria = new Usuario("Maria");

        this.leiloeiro = new Avaliador();
    }

    // It runs after each test case
    @After
    public void finaliza() {
        System.out.println("Fim");
    }

    // It runs before all tests
    @BeforeClass
    public static void testandoBeforeClass() {
        System.out.println("before class");
    }

    // It runs after all tests
    @AfterClass
    public static void testandoAfterClass() {
        System.out.println("after class");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Leilao leilao = new CriadorLeilao().para("Playstation 5 Novo")
                .lance(joao, 250.0)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();

        // parte 2: acao
        leiloeiro.avalia(leilao);

        // parte 3: validacao
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new Leilao("Playstation 5 Novo");

        Lance lance = new Lance(joao, 1000.0);

        leilao.propoe(lance);

        leiloeiro.avalia(leilao);

        assertThat(leilao, temUmLance(lance));
        assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMedia(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorLeilao().para("Playstation 5")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertThat(maiores, hasItems(
                new Lance(maria, 400),
                new Lance(joao, 300),
                new Lance(maria, 200)
        ));
    }

    @Test
    public void testeLeilaoComValoresRandomicos() {
        Leilao leilao = new CriadorLeilao().para("Playstation 5 Novo")
                .lance(joao, 200.0)
                .lance(maria, 450.0)
                .lance(jose, 120.0)
                .lance(joao, 700.0)
                .lance(maria, 630.0)
                .lance(jose, 230.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(700.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
    }

    @Test
    public void testaLeilaoComValoresDecrecentes() {
        Leilao leilao = new CriadorLeilao().para("Playstation 5 Novo")
                .lance(joao, 400.0)
                .lance(maria, 300.0)
                .lance(jose, 200.0)
                .lance(joao, 100.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(100.0));
    }

    @Test
    public void leilaoComCincoLancesDeveEncontrarTresMaiores() {
        Leilao leilao = new CriadorLeilao().para("Playstation 5 Novo")
                .lance(joao, 400.0)
                .lance(maria, 300.0)
                .lance(jose, 200.0)
                .lance(joao, 100.0)
                .lance(joao, 140.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());

        assertThat(maiores, hasItems(
                new Lance(joao, 400),
                new Lance(maria, 300),
                new Lance(jose, 200)
        ));
    }

    @Test
    public void leilaoComDoisLancesDeveEncontrarDoisMaiores() {
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

//        leiloeiro.avalia(leilao);

        // Commented because it's an exercise and it broke bacause we implemented some new features.
        // This will remain commented because in case I want to see the old code from this study.
//        assertEquals(0, leiloeiro.getTresMaiores().size());
    }

    @Test
    public void calculaMediaLances() {
        Leilao leilao = new CriadorLeilao().para("Playstation 5 Novo")
                .lance(joao, 250.0)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        double mediaDosLances = leiloeiro.getMedia();

        double mediaEsperada = 316.666666;

        assertEquals(mediaEsperada, mediaDosLances, 0.00001);
    }

    @Test
    public void testaMediaDeZeroLance() {
        // acao
        Leilao leilao = new Leilao("Iphone 7");

//        leiloeiro.avalia(leilao);

        // Commented because it's an exercise and it broke bacause we implemented some new features.
        // This will remain commented because in case I want to see the old code from this study.
//        assertEquals(0, leiloeiro.getMedia(), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorLeilao().para("PS5").constroi();

        leiloeiro.avalia(leilao);
    }
}