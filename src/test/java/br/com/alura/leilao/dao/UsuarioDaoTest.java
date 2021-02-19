package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import static br.com.alura.leilao.mock.UsuarioMock.createUsuarioMock;

public class UsuarioDaoTest {

    private EntityManager em;
    private UsuarioDao dao;

    @Before
    public void beforeEach() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tests");
        em = emf.createEntityManager();
        dao = new UsuarioDao(em);

        this.em.getTransaction().begin();
    }

    @After
    public void afterEach() {
        this.em.getTransaction().rollback();
    }

    @Test
    public void deveriaEncontrarFulano() {
        Usuario saved = createUsuarioMock();
        em.persist(saved);

        Usuario retrieve = dao.buscarPorUsername(saved.getNome());

        Assert.assertNotNull(retrieve);
    }

    @Test
    public void naoDeveriaEncontrarNaoCadastrado() {
        Usuario saved = createUsuarioMock();
        em.persist(saved);

        Assert.assertThrows(NoResultException.class, () -> dao.buscarPorUsername("beltrano"));
    }

    @Test
    public void deveriaRemoverUmUsuario() {
        Usuario saved = createUsuarioMock();
        em.persist(saved);

        dao.deletar(saved);

        Assert.assertThrows(NoResultException.class, () -> dao.buscarPorUsername("fulano"));
    }
}
