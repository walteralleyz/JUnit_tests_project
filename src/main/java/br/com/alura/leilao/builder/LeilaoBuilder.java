package br.com.alura.leilao.builder;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeilaoBuilder {
    private String nome;
    private BigDecimal valorInicial;
    private Usuario usuario;
    private LocalDate dataAbertura;

    public LeilaoBuilder name(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder value(String valor) {
        this.valorInicial = new BigDecimal(valor);
        return this;
    }

    public LeilaoBuilder user(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public LeilaoBuilder date() {
        this.dataAbertura = LocalDate.now();
        return this;
    }

    public Leilao build() {
        return new Leilao(nome, valorInicial, dataAbertura, usuario);
    }
}
