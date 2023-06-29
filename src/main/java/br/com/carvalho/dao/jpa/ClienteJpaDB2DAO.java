/**
 * 
 */
package br.com.carvalho.dao.jpa;

import br.com.carvalho.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.carvalho.domain.jpa.ClienteJpa2;

/**
 * @author manoel.carvalho
 *
 */
public class ClienteJpaDB2DAO extends GenericJpaDB1DAO<ClienteJpa2, Long> implements IClienteJpaDAO<ClienteJpa2> {

	public ClienteJpaDB2DAO() {
		super(ClienteJpa2.class);
	}

}
