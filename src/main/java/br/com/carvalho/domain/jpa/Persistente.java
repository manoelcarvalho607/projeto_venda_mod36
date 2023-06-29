package br.com.carvalho.domain.jpa;

/**
 * @author manoel.carvalho
 *
 * Classe que representa todas as entidades ou objetos da aplicação que seram salvas no banco de dados
 */
public interface Persistente {
	
	public Long getId();
	
	public void setId(Long id);

}
