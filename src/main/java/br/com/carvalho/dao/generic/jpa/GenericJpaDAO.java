package br.com.carvalho.dao.generic.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.carvalho.dao.generics.jpa.IGenericJpaDAO;
import br.com.carvalho.domain.jpa.Persistente;
import br.com.carvalho.exceptions.DAOException;
import br.com.carvalho.exceptions.MaisDeUmRegistroException;
import br.com.carvalho.exceptions.TableException;
import br.com.carvalho.exceptions.TipoChaveNaoEncontradaException;



/**
 * @author manoel.carvalho
 *
 */
public class GenericJpaDAO<T extends Persistente, E extends Serializable> implements IGenericJpaDAO <T,E>  {
	
	
	private static final String PERSISTENCE_UNIT_NAME = "Mysql1"; 
	
	protected EntityManagerFactory entityManagerFactory;
	
	protected EntityManager entityManager;
	
	private Class<T> persistenteClass;
	
	private String persistenceUnitName;
	
	public GenericJpaDAO(Class<T> persistenteClass, String persistenceUnitName) {
		this.persistenteClass = persistenteClass;
		this.persistenceUnitName = persistenceUnitName;
	}

	@Override
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		closeConnection();
		return entity;
	}
	

	@Override
	public void excluir(T entity) throws DAOException {
		openConnection();
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		closeConnection();
		
	}

	@Override
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
		openConnection();
		T entity = entityManager.find(this.persistenteClass, valor);
		entityManager.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		openConnection();
		List<T> list = 
				entityManager.createQuery(getSelectSql(), this.persistenteClass).getResultList();
		closeConnection();
		return list;
	}
	
	protected void openConnection() {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory(getPersistenceUnitName());
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}
	
	protected void closeConnection() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	private String getPersistenceUnitName() {
		if (persistenceUnitName != null 
				&& !"".equals(persistenceUnitName)) {
			return persistenceUnitName;
		} else {
			return PERSISTENCE_UNIT_NAME;
		}
	}
	
	private String getSelectSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.persistenteClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}

}
