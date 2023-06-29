/**
 * 
 */
package br.com.carvalho.jpa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author manoel.carvalho
 *
 */
@RunWith(Suite.class )
@Suite.SuiteClasses({  ClienteJpaDAOTest.class, ProdutoJpaDAOTest.class, VendaJpaDAOTest.class, ClienteJpaDao2BancosTest.class })
public class AllTests {

}
