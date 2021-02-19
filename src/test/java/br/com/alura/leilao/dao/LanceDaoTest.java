package br.com.alura.leilao.dao;

import br.com.alura.leilao.builder.LanceBuilder;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

import static br.com.alura.leilao.mock.UsuarioMock.createUsuarioMock;
import static br.com.alura.leilao.mock.LeilaoMock.createLeilaoMock;
import static br.com.alura.leilao.mock.LanceMock.createLanceMock;

public class LanceDaoTest {
    private EntityManager em;
    private LanceDao dao;

    @BeforeEach
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tests");
        em = emf.createEntityManager();
        dao = new LanceDao(em);

        em.getTransaction().begin();
    }

    @AfterEach
    public void destroy() {
        em.getTransaction().rollback();
    }

    @Test
    public void deveriaSalvarUmLanceERetornarOMaiorValor() {
        Usuario usuario = usuarioMock();
        Leilao leilao = leilaoMock(usuario);

        Lance l0 = lanceMock(usuario, "400", leilao);
        Lance l1 = lanceMock(usuario, "700", leilao);

        Lance maior = dao.buscarMaiorLanceDoLeilao();

        Assertions.assertNotNull(maior);
        Assertions.assertEquals(new BigDecimal("700"), maior.getValor());
    }

    @Test
    public void deveriaSalvarUmLanceERetornarOMenorValor() {
        Usuario usuario = usuarioMock();
        Leilao leilao = leilaoMock(usuario);

        Lance l0 = lanceMock(usuario, "300", leilao);
        Lance l1 = lanceMock(usuario, "800", leilao);
        Lance l2 = lanceMock(usuario, "160", leilao);

        Lance menor = dao.buscarOMenorLanceDoLeilao();

        Assertions.assertNotNull(menor);
        Assertions.assertEquals(new BigDecimal("160"), l2.getValor());

    }

    @Test
    public void deveriaRetornarAcimaDe600() {
        Usuario usuario = usuarioMock();
        Leilao leilao = leilaoMock(usuario);

        Lance l0 = lanceMock(usuario, "300", leilao);
        Lance l1 = lanceMock(usuario, "800", leilao);
        Lance l2 = lanceMock(usuario, "650", leilao);
        Lance l3 = lanceMock(usuario, "1200", leilao);

        List<Lance> lances = dao.buscarTodosOsLancesComLimite(new BigDecimal("600"));

        Assertions.assertNotNull(lances);
        Assertions.assertEquals(3, lances.size());
        Assertions.assertEquals(new BigDecimal("650"), lances.get(0).getValor());
    }

    public Usuario usuarioMock() {
        Usuario usuario = createUsuarioMock();
        em.persist(usuario);

        return usuario;
    }

    public Leilao leilaoMock(Usuario usuario) {
        Leilao leilao = createLeilaoMock(usuario);
        em.persist(leilao);

        return leilao;
    }

    public Lance lanceMock(Usuario usuario, String valor, Leilao leilao) {
        Lance lance = createLanceMock(usuario, valor);
        lance.setLeilao(leilao);
        dao.salvar(lance);

        return lance;
    }
}
