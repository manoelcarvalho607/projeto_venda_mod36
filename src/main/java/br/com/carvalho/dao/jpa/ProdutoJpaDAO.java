package br.com.carvalho.dao.jpa;



import br.com.carvalho.dao.generic.jpa.GenericJpaDB2DAO;

import br.com.carvalho.domain.jpa.ProdutoJpa;


/**
 * @author manoel.carvalho
 *
 */
public class ProdutoJpaDAO extends GenericJpaDB2DAO<ProdutoJpa, Long> implements IProdutoJpaDAO{

	public ProdutoJpaDAO() {
		super(ProdutoJpa.class);
	}

}
