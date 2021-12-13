package br.com.primeiroprojetospring.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;


@Entity
@Data
public class Permissao  implements GrantedAuthority{
	
	private static final long serialVersionUID = -597476406094599240L;
	
	public static final String INSERT = "isert";



	
	
	@Id
	private String nomePermissao;
	
	@ManyToMany (mappedBy = "permissoes")
	private List<Usuario> usuarios;
	
	@Override
	public String getAuthority() {
		return this.nomePermissao;
	}
	
	
}