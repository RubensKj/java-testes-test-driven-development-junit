package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Usuario usuario) {
        int total = quantidadeDeLancesDo(usuario);

        return (!ultimoLanceDado().getUsuario().equals(usuario)) && total < 5;
    }

    private int quantidadeDeLancesDo(Usuario usuario) {
        int total = 0;

        for (Lance lanc : lances) {
            if (lanc.getUsuario().equals(usuario)) {
                total++;
            }
        }
        return total;
    }

    public void dobraLance(Usuario usuario) {
        Lance ultimoLanceUsuario = encontraUltimoLance(usuario);

        if (ultimoLanceUsuario == null) {
            return;
        }

        Lance lance = new Lance(usuario, dobraValor(ultimoLanceUsuario));

        this.propoe(lance);
    }

    private Lance encontraUltimoLance(Usuario usuario) {
        Lance ultimoLanceUsuario = null;

        for (Lance lance : lances) {
            if (lance.getUsuario().equals(usuario)) {
                ultimoLanceUsuario = lance;
            }
        }

        return ultimoLanceUsuario;
    }

    private double dobraValor(Lance ultimoLanceUsuario) {
        return ultimoLanceUsuario.getValor() * 2;
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size() - 1);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

}
