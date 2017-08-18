package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

public class ClienteBO {

	private EntityManagerFactory fabrica = 
			EntityManagerFactorySingleton.getInstance();
	
	public List<Cliente> listar(){
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		List<Cliente> lista = dao.listar();
		em.close();
		return lista;
	}
	
	
	public Cliente buscar(int codigo) {
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		Cliente cliente = dao.pesquisar(codigo);
		em.close();
		return cliente;
	}

	public void cadastrar(Cliente cliente) throws DBException {
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		dao.cadastrar(cliente);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}finally {
			em.close();
		}
	}

	public void atualizar(Cliente cliente) throws DBException {
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		dao.alterar(cliente);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}finally {
			em.close();
		}
	}

	public void remover(int codigo) throws DBException {
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		try {
			dao.remover(codigo);
			dao.salvar();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		} finally {
			em.close();
		}
	}


	public List<Cliente> buscarPorNome(String nome) {
		ClienteDAO dao = new ClienteDAOImpl(
			EntityManagerFactorySingleton.getInstance()
			.createEntityManager());
		return dao.buscarPorNome(nome);
	}


	public List<String> completarPorNome(String texto) {
		ClienteDAO dao = new ClienteDAOImpl(fabrica.createEntityManager());
		return dao.completarPorNome(texto);
	}
	
	public long contarPorRating(int rating){
		ClienteDAO dao = new ClienteDAOImpl(
				fabrica.createEntityManager());
		return dao.contarPorRating(rating);
	}
	
	public long contarPorMes(int mes){
		ClienteDAO dao = new ClienteDAOImpl(fabrica.createEntityManager());
		return dao.contarPorMesAnivesario(mes);
	}
	
}






