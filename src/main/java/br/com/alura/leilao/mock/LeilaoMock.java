package br.com.alura.leilao.mock;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

public class LeilaoMock {
    public static Leilao createLeilaoMock(Usuario usuario) {
        return new LeilaoBuilder()
            .name("Playstation 5 novo")
            .date()
            .user(usuario)
            .value("4000")
            .build();
    }
}
