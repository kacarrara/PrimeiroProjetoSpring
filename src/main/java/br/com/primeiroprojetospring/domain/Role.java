package br.com.primeiroprojetospring.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Data
public class Role  implements GrantedAuthority{
	
	private static final long serialVersionUID = -597476406094599240L;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Id
	private String nomeRole;
	
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;
	


			
	@Override
	public String getAuthority() {
		
		return this.nomeRole;

		
	}




	public void setRoles(List<Role> asList) {
		// TODO Auto-generated method stub
		
	}

}