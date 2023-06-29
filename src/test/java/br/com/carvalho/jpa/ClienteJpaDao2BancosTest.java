/**
 * 
 */
package br.com.carvalho.jpa;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.carvalho.dao.jpa.ClienteJpaDAO;
import br.com.carvalho.dao.jpa.ClienteJpaDB2DAO;
import br.com.carvalho.dao.jpa.IClienteJpaDAO;
import br.com.carvalho.domain.jpa.ClienteJpa;
import br.com.carvalho.domain.jpa.ClienteJpa2;
import br.com.carvalho.exceptions.DAOException;
import br.com.carvalho.exceptions.MaisDeUmRegistroException;
import br.com.carvalho.exceptions.TableException;
import br.com.carvalho.exceptions.TipoChaveNaoEncontradaException;


/**
 * @author manoel.carvalho
 *
 */
public class ClienteJpaDao2BancosTest {
	
	private IClienteJpaDAO<ClienteJpa> clienteDao;
	
	private IClienteJpaDAO<ClienteJpa2> clienteDB2Dao;
	
	private Random rd;
	
	public ClienteJpaDao2BancosTest() {
		this.clienteDao = new ClienteJpaDAO();
		this.clienteDB2Dao = new ClienteJpaDB2DAO();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException {
		Collection<ClienteJpa> list1 = clienteDao.buscarTodos();
		excluir1(list1);
		
		Collection<ClienteJpa2> list2 = clienteDB2Dao.buscarTodos();
		excluir2(list2);
	}
	
	private void excluir1(Collection<ClienteJpa> list) {
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private void excluir2(Collection<ClienteJpa2> list) {
		list.forEach(cli -> {
			try {
				clienteDB2Dao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		ClienteJpa2 cliente = criarCliente();
		clienteDB2Dao.cadastrar(cliente);
		
		ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		cliente.setId(null);
		clienteDB2Dao.cadastrar(cliente);
		
		ClienteJpa2 clienteConsultado2 = clienteDB2Dao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado2);
		
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJpa2 cliente = criarCliente();
		ClienteJpa2 retorno = clienteDB2Dao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa2 clienteConsultado = clienteDB2Dao.consultar(retorno.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDB2Dao.excluir(cliente);
		
		ClienteJpa2 clienteConsultado1 = clienteDB2Dao.consultar(retorno.getId());
		Assert.assertNull(clienteConsultado1);
		
		ClienteJpa cliente2 = criarCliente2();
		ClienteJpa retorno2 = clienteDao.cadastrar(cliente2);
		Assert.assertNotNull(retorno2);
		
		ClienteJpa clienteConsultado2 = clienteDao.consultar(retorno.getId());
		Assert.assertNotNull(clienteConsultado2);
		
		clienteDao.excluir(cliente2);
		
		ClienteJpa clienteConsultado3 = clienteDao.consultar(retorno.getId());
		Assert.assertNull(clienteConsultado3);
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJpa2 cliente = criarCliente();
		ClienteJpa2 retorno = clienteDB2Dao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa2 clienteConsultado = clienteDB2Dao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDB2Dao.excluir(cliente);
		clienteConsultado = clienteDB2Dao.consultar(cliente.getId());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJpa2 cliente = criarCliente();
		ClienteJpa2 retorno = clienteDB2Dao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa2 clienteConsultado = clienteDB2Dao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Silva");
		clienteDB2Dao.alterar(clienteConsultado);
		
		ClienteJpa2 clienteAlterado = clienteDB2Dao.consultar(clienteConsultado.getId());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Silva", clienteAlterado.getNome());
		
		clienteDB2Dao.excluir(cliente);
		clienteConsultado = clienteDB2Dao.consultar(clienteAlterado.getId());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		ClienteJpa2 cliente = criarCliente();
		ClienteJpa2 retorno = clienteDB2Dao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa2 cliente1 = criarCliente();
		ClienteJpa2 retorno1 = clienteDB2Dao.cadastrar(cliente1);
		Assert.assertNotNull(retorno1);
		
		Collection<ClienteJpa2> list = clienteDB2Dao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				clienteDB2Dao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Collection<ClienteJpa2> list1 = clienteDB2Dao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
	
	private ClienteJpa2 criarCliente() {
		ClienteJpa2 cliente = new ClienteJpa2();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Manoel");
		cliente.setCidade("Bauru");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(11999888888L);
		return cliente;
	}
	
	private ClienteJpa criarCliente2() {
		ClienteJpa cliente = new ClienteJpa();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Manoel Carvalho");
		cliente.setCidade("Bauru");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(11999888888L);
		return cliente;
	}

}
