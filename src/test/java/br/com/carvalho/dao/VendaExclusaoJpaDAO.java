package br.com.carvalho.dao;



import br.com.carvalho.dao.generic.jpa.GenericJpaDB2DAO;
import br.com.carvalho.dao.jpa.IVendaJpaDAO;
import br.com.carvalho.domain.jpa.VendaJpa;
import br.com.carvalho.exceptions.DAOException;

import br.com.carvalho.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author manoel.carvalho
 *
 * Classe utilizada somente para teste para fazer a exclusão das vendas
 */
public class VendaExclusaoJpaDAO extends GenericJpaDB2DAO<VendaJpa, Long> implements IVendaJpaDAO {

	public VendaExclusaoJpaDAO() {
		super(VendaJpa.class);
		
	}

	
	@Override
	public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");

	}

	@Override
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");

	}

	@Override
	public VendaJpa consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}
