package br.com.carvalho.dao.jpa;

import br.com.carvalho.dao.generics.jpa.IGenericJpaDAO;
import br.com.carvalho.domain.jpa.VendaJpa;
import br.com.carvalho.exceptions.DAOException;
import br.com.carvalho.exceptions.TipoChaveNaoEncontradaException;


/**
 * @author manoel.carvalho
 *
 */
public interface IVendaJpaDAO extends IGenericJpaDAO<VendaJpa, Long>{
	
	public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	/**
	 * Usando este método para evitar a exception org.hibernate.LazyInitializationException
	 * Ele busca todos os dados de objetos que tenham colletion pois a mesma por default é lazy
	 * Mas você pode configurar a propriedade da collection como fetch = FetchType.EAGER na anotação @OneToMany e usar o consultar genérico normal
	 * 
	 * OBS: Não é uma boa prática utiliar FetchType.EAGER pois ele sempre irá trazer todos os objetos da collection
	 * mesmo sem precisar utilizar.
	 * 
	 * 
	 * @see VendaJpa produtos
	 * 
	 * @param id
	 * @return
	 */
	public VendaJpa consultarComCollection(Long id);

}
