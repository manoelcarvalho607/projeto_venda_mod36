package br.com.carvalho.jpa;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.carvalho.dao.jpa.ClienteJpaDAO;
import br.com.carvalho.dao.jpa.IClienteJpaDAO;
import br.com.carvalho.domain.jpa.ClienteJpa;
import br.com.carvalho.exceptions.DAOException;
import br.com.carvalho.exceptions.MaisDeUmRegistroException;
import br.com.carvalho.exceptions.TableException;
import br.com.carvalho.exceptions.TipoChaveNaoEncontradaException;




/**
 * @author manoel.carvalho
 *
 */
public class ClienteJpaDAOTest {
	
private IClienteJpaDAO<ClienteJpa> clienteDao;
	
	private Random rd;
	
	public ClienteJpaDAOTest() {
		this.clienteDao = new ClienteJpaDAO();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException {
		Collection<ClienteJpa> list = clienteDao.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		ClienteJpa cliente = criarCliente();
		clienteDao.cadastrar(cliente);
		
		ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
	}

	
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJpa cliente = criarCliente();
		ClienteJpa retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa clienteConsultado = clienteDao.consultar(retorno.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente);
		
		ClienteJpa clienteConsultado1 = clienteDao.consultar(retorno.getId());
		Assert.assertNull(clienteConsultado1);
	}
	
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJpa cliente = criarCliente();
		ClienteJpa retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJpa cliente = criarCliente();
		ClienteJpa retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Manoel Carvalho");
		clienteDao.alterar(clienteConsultado);
		
		ClienteJpa clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Manoel Carvalho", clienteAlterado.getNome());
		
		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		ClienteJpa cliente = criarCliente();
		ClienteJpa retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		ClienteJpa cliente1 = criarCliente();
		ClienteJpa retorno1 = clienteDao.cadastrar(cliente1);
		Assert.assertNotNull(retorno1);
		
		ClienteJpa cliente2 = criarCliente();
		ClienteJpa retorno2 = clienteDao.cadastrar(cliente2);
		Assert.assertNotNull(retorno2);
		
		Collection<ClienteJpa> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 3);
		
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Collection<ClienteJpa> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}



	private ClienteJpa criarCliente() {
		ClienteJpa cliente = new ClienteJpa();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Manoel");
		cliente.setCidade("Bauru");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(15);
		cliente.setTel(1199999999L);
		return cliente;
	}

}
