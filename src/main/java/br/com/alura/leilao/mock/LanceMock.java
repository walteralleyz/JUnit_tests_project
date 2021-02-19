package br.com.alura.leilao.mock;

import br.com.alura.leilao.builder.LanceBuilder;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Usuario;

public class LanceMock {
    public static Lance createLanceMock(Usuario usuario, String valor) {
        return new LanceBuilder()
            .user(usuario)
            .value(valor)
            .build();
    }
}
