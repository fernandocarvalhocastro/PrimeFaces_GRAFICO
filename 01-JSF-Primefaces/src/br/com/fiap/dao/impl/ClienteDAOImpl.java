package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cliente;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Integer> implements ClienteDAO{

	public ClienteDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {
		return em.createQuery(
			"from Cliente c where c.nome like :n",
			Cliente.class)
			.setParameter("n", "%"+nome+"%")
			.getResultList();
	}

	@Override
	public List<String> completarPorNome(String texto) {
		return em.createQuery("select c.nome from Cliente c "
				+ "where c.nome like :n",String.class)
				.setParameter("n", "%"+texto+"%")
				.getResultList();
	}

	@Override
	public long contarPorRating(int rating) {
		return em.createQuery("select count(c) "
				+ "from Cliente c where c.rating = :r"
				,Long.class)
				.setParameter("r", rating)
				.getSingleResult();
	}

	@Override
	public long contarPorMesAnivesario(int mes) {
		return em.createQuery("select count(c) from "
				+ "Cliente c where "
				+ "month(c.dataNascimento) = :m",Long.class)
				.setParameter("m", mes)
				.getSingleResult();
	}

	

}



