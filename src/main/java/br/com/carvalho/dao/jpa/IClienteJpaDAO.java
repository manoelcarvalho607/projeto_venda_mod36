package br.com.carvalho.dao.jpa;

import br.com.carvalho.dao.generics.jpa.IGenericJpaDAO;
import br.com.carvalho.domain.jpa.Persistente;

/**
 * @author manoel.carvalho
 *
 */
public interface IClienteJpaDAO<T extends Persistente> extends IGenericJpaDAO<T, Long> {

}
