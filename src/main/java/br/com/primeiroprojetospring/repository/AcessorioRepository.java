package br.com.primeiroprojetospring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.primeiroprojetospring.domain.Acessorio;

public interface AcessorioRepository extends JpaRepository<Acessorio, Integer>{
	
	@Query("SELECT a "
	+ "FROM Acessorio a "	
	+ "WHERE a.nome = :nomeAcessorio")
	List<Acessorio> findByNomeAcessorioJPQL(@Param("nomeAcessorio") String nomeAcessorio);

}