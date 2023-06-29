/**
 * 
 */
package br.com.carvalho.dao.generic.jpa;

import java.io.Serializable;

import br.com.carvalho.domain.jpa.Persistente;



/**
 * @author manoel.carvalho
 *
 */
public abstract class GenericJpaDB1DAO <T extends Persistente, E extends Serializable>
	extends GenericJpaDAO<T,E> {

	public GenericJpaDB1DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre1");
	}
//Postgre1
}
