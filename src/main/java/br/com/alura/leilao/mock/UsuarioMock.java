package br.com.alura.leilao.mock;

import br.com.alura.leilao.builder.UsuarioBuilder;
import br.com.alura.leilao.model.Usuario;

public class UsuarioMock {
    public static Usuario createUsuarioMock() {
        return new UsuarioBuilder()
            .name("fulano")
            .setEmail("fulano@mail.com")
            .password("1234")
            .build();
    }
}
