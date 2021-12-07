package br.com.primeiroprojetospring.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeiroprojetospring.domain.Fabricante;
import br.com.primeiroprojetospring.repository.FabricanteRepository;

@Service
public class FabricanteService {
	
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public List<Fabricante> buscarTodosFabricantes() {

		return fabricanteRepository.findAll();

	}

	public Fabricante salvar(Fabricante fabricante) {

		return fabricanteRepository.save(fabricante);

	}
	
	public Fabricante buscarPorID(Integer id) throws ObjectNotFoundException {
		Optional<Fabricante> fabricante = fabricanteRepository.findById(id);
		return fabricante.orElseThrow(()   -> 
		new ObjectNotFoundException((Serializable) new Fabricante(), "Fabricante não encontrado. id: " + id));
	}
	
	public Fabricante salvarAlteracao(Fabricante fabricanteAlterado) throws ObjectNotFoundException {
		Fabricante fabricante = buscarPorID(fabricanteAlterado.getId());
		fabricante.setId(fabricanteAlterado.getId());
		fabricante.setNome(fabricanteAlterado.getNome());
		fabricante.setPais(fabricanteAlterado.getPais());
		return fabricante;
	}
	
	public void excluir(Integer id) {
		fabricanteRepository.deleteById(id);
	}
	}