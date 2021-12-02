package br.com.primeiroprojetospring.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeiroprojetospring.domain.Aluno;
import br.com.primeiroprojetospring.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository AlunoRepository;

	public List<Aluno> buscarTodosAlunos() {

		return AlunoRepository.findAll();

	}

	public Aluno salvar(Aluno aluno) {

		return AlunoRepository.save(aluno);

	}
	
	public Aluno buscarPorID(Integer id) {
		Optional<Aluno> aluno = AlunoRepository.findById(id);
		return aluno.orElseThrow(()   -> 
		new ObjectNotFoundException(new Aluno(), "Aluno não encontrado. id: " + id));
	}
	
	public Aluno salvarAlteracao(Aluno alunoAlterado) throws ObjectNotFoundException{
		Aluno aluno = buscarPorID(alunoAlterado.getId());
		aluno.setId(alunoAlterado.getId());
		aluno.setNome(alunoAlterado.getNome());
		return salvar(aluno);
		
	}
	
	public void excluir (Integer id) {
		AlunoRepository.deleteById(id);
	}

	
}
