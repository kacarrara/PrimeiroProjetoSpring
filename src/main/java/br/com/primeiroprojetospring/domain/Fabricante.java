package br.com.primeiroprojetospring.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Fabricante implements Serializable {
	
	private static final long serialVersionUID = -955769089251692025L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@Column(name="NOME_FABRICANTE")
	private String nome;
	
	@Column(name="PAIS_FABRICANTE")
	private String pais;

		
	
	
}

