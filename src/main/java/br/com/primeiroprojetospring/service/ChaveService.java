package br.com.primeiroprojetospring.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.primeiroprojetospring.domain.Chave;
import br.com.primeiroprojetospring.repository.ChaveRepository;

@Service
public class ChaveService {

	@Autowired
	private ChaveRepository chaveRepository;

	public List<Chave> buscarTodasChaves() {

		return chaveRepository.findAll();

	}

	public Chave salvar(Chave chave) {

		return chaveRepository.save(chave);

	}
	
	public Chave buscarPorID(Integer id) {
		Optional<Chave> chave = chaveRepository.findById(id);
		return chave.orElseThrow(()   -> 
		new ObjectNotFoundException(new Chave(), "Chave não encontrada. id: " + id));
	}
	
	public Chave salvarAlteracao(Chave chaveAlterado) throws ObjectNotFoundException{
		Chave chave = buscarPorID(chaveAlterado.getId());
		chave.setId(chaveAlterado.getId());
		chave.setCodigo(chaveAlterado.getCodigo());
		return salvar(chave);
		
	}
	
	public void excluir (Integer id) {
		chaveRepository.deleteById(id);
	}
}
