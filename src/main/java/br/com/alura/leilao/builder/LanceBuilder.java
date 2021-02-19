package br.com.alura.leilao.builder;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;

public class LanceBuilder {
    private BigDecimal valor;
    private Usuario usuario;

    public LanceBuilder value(String valor) {
        this.valor = new BigDecimal(valor);
        return this;
    }

    public LanceBuilder user(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Lance build() {
        return new Lance(usuario, valor);
    }
}
