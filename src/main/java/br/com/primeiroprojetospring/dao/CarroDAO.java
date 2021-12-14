package br.com.primeiroprojetospring.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.primeiroprojetospring.domain.Acessorio;
import br.com.primeiroprojetospring.domain.Carro;
import br.com.primeiroprojetospring.domain.QCarro;

@Repository
public class CarroDAO {

	private final String TETO_SOLAR = "TETO SOLAR";

	@Autowired
	private EntityManager em;

	private QCarro carro = QCarro.carro;

	public List<Carro> buscarCarroPorIdFabricante(Integer idFabricante) {

		return new JPAQueryFactory(em).selectFrom(carro).where(carro.fabricanteCarro.id.eq(idFabricante)).fetch();

	}

	public List<Carro> buscarCarroPorIdDocumento(Integer idDocumento) {

		return new JPAQueryFactory(em).selectFrom(carro).where(carro.documentoCarro.id.eq(idDocumento)).fetch();
	}

	public List<Carro> buscarCarrosComTetoSolar(Acessorio acessorios) {

		return new JPAQueryFactory(em).selectFrom(carro)
				.where(carro.acessorios.any().nome.equalsIgnoreCase(TETO_SOLAR))
				.fetch();
	}

	

	
	
}