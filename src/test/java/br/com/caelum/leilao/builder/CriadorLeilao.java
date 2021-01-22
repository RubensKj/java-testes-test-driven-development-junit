package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadorLeilao {

    private Leilao leilao;

    public CriadorLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorLeilao lance(Usuario usuario, double valor) {
        Lance lance = new Lance(usuario, valor);

        this.leilao.propoe(lance);

        return this;
    }

    public Leilao constroi() {
        return leilao;
    }
}
