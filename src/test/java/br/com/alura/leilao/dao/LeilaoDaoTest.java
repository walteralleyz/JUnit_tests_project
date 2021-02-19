package br.com.alura.leilao.dao;

import br.com.alura.leilao.builder.UsuarioBuilder;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static br.com.alura.leilao.mock.LeilaoMock.createLeilaoMock;
import static br.com.alura.leilao.mock.UsuarioMock.createUsuarioMock;

public class LeilaoDaoTest {

    private EntityManager em;
    private LeilaoDao dao;

    @Before
    public void beforeEach() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tests");
        em = emf.createEntityManager();
        dao = new LeilaoDao(em);

        this.em.getTransaction().begin();
    }

    @After
    public void afterEach() {
        this.em.getTransaction().rollback();
    }

    @Test
    public void deveriaCadastrarUmLeilao() {
        Usuario usuario = createUsuarioMock();
        Leilao leilao = createLeilaoMock(usuario);

        em.persist(usuario);

        leilao = dao.salvar(leilao);

        Leilao savedL = dao.buscarPorId(leilao.getId());

        Assert.assertNotNull(savedL);
    }
}
