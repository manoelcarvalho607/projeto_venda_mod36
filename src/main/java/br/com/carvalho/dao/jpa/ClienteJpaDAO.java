package br.com.carvalho.dao.jpa;



import br.com.carvalho.dao.generic.jpa.GenericJpaDB2DAO;
import br.com.carvalho.domain.jpa.ClienteJpa;



/**
 * @author manoel.carvalho
 *
 */
public class ClienteJpaDAO extends GenericJpaDB2DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {
	
	public ClienteJpaDAO() {
		super(ClienteJpa.class);
	}


}
