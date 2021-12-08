package br.com.primeiroprojetospring.service;

import java.io.Serializable;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.primeiroprojetospring.domain.Documento;
import br.com.primeiroprojetospring.domain.QDocumento;
import br.com.primeiroprojetospring.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private EntityManager entityManager;

	public List<Documento> buscarTodosDocumentos() {

		return documentoRepository.findAll();

	}

	public Documento salvar(Documento documento) {

		return documentoRepository.save(documento);

	}

	public Documento buscarPorID(Integer id) throws ObjectNotFoundException {
		Optional<Documento> documento = documentoRepository.findById(id);
		return documento.orElseThrow(() -> new ObjectNotFoundException((Serializable) new Documento(),
				"Documento não encontrado. id: " + id));
	}

	public Documento salvarAlteracao(Documento documentoAlterado) throws ObjectNotFoundException {
		Documento documento = buscarPorID(documentoAlterado.getId());
		documento.setId(documentoAlterado.getId());
		documento.setNome(documentoAlterado.getNome());
		documento.setCodigo(documentoAlterado.getCodigo());
		return documento;
	}

	public void excluir(Integer id) {
		documentoRepository.deleteById(id);
	}

	public List<Documento> findByNomeAndCodigo(String nome, String codigo) {
		QDocumento documento = QDocumento.documento;

		return new JPAQueryFactory(entityManager).selectFrom(documento)
				.where(documento.nome.eq(nome).and(documento.codigo.eq(codigo))).fetch();
	}

}
