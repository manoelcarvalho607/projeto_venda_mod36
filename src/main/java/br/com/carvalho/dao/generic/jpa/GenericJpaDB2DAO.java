package br.com.carvalho.dao.generic.jpa;

import java.io.Serializable;

import br.com.carvalho.domain.jpa.Persistente;




/**
 * @author manoel.carvalho
 *
 */
public abstract class GenericJpaDB2DAO<T extends Persistente, E extends Serializable> 
      extends GenericJpaDAO<T,E> {

	public GenericJpaDB2DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Mysql1");
	}

}
