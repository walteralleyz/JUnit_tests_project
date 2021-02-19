package br.com.alura.leilao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class LanceDao {

	private EntityManager em;

	@Autowired
	public LanceDao(EntityManager em) {
		this.em = em;
	}

	public void salvar(Lance lance) {
		em.persist(lance);
	}

	public Lance buscarMaiorLanceDoLeilao() {
		return em.createQuery(
			"SELECT l FROM Lance l WHERE l.valor = (SELECT MAX(lance.valor) FROM Lance lance)",
			Lance.class)
			.getSingleResult();
	}

	public Lance buscarOMenorLanceDoLeilao() {
		return em.createQuery(
			"SELECT l FROM Lance l WHERE l.valor = (SELECT MIN(lance.valor) FROM Lance lance)",
			Lance.class)
			.getSingleResult();
	}

	public List<Lance> buscarTodosOsLancesComLimite(BigDecimal valor) {
		return em.createQuery(
			"SELECT l FROM Lance l WHERE l.valor > :valor ORDER BY l.valor ASC",
			Lance.class)
			.setParameter("valor", valor)
			.getResultList();
	}
	
}
