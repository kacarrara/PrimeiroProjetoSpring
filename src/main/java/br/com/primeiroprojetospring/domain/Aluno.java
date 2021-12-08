package br.com.primeiroprojetospring.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -886604392341594251L;
	public static Object aluno;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	private String nome;

	

}