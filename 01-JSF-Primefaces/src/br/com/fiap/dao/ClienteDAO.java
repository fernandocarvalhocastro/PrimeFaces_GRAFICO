package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cliente;

public interface ClienteDAO 
					extends GenericDAO<Cliente, Integer>{

	List<Cliente> buscarPorNome(String nome);

	List<String> completarPorNome(String texto);
	
	long contarPorRating(int rating);
	
	long contarPorMesAnivesario(int mes);
	
}