package br.com.primeiroprojetospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.primeiroprojetospring.domain.Documento;
import br.com.primeiroprojetospring.service.DocumentoService;

@RestController
@RequestMapping("documento")
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;
	
	
	@PostMapping("/cadastrarDocumento")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Documento> cadastrarDocumentoAPI(@RequestBody Documento documento){
		return ResponseEntity.ok().body(documentoService.salvar(documento));
	}
    
	@GetMapping("/find/{id}")
	public ResponseEntity<Documento> find(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(documentoService.buscarPorID(id));
	}
	
	@GetMapping("todosDocumentos")
	public ResponseEntity<List<Documento>> devolverTodosDocumentos(){
		return ResponseEntity.ok().body(documentoService.buscarTodosDocumentos());
	}
	
	
	@PutMapping("/alteraDocumento")
	public ResponseEntity<Documento> alteraDocumentoEntity (@RequestBody Documento documento){
		Documento novoDocumento= documentoService.salvarAlteracao(documento);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoDocumento);
	}

	@GetMapping("/listaDocumentos")
	public ModelAndView listaTodosDocumentos() {
		ModelAndView mView = new ModelAndView("documento/paginaListaDocumentos");
		mView.addObject("documentos", documentoService.buscarTodosDocumentos());
		return mView;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarDocumentos() {
		ModelAndView mView = new ModelAndView("documento/cadastraDocumento");
		mView.addObject("documento", new Documento());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarDocumentos(Documento documento) {
		documentoService.salvar(documento);
		return listaTodosDocumentos();

	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Integer idDocumento) {
		ModelAndView mView = new ModelAndView("documento/alteraDocumento");
		mView.addObject("documento", documentoService.buscarPorID(idDocumento));
		return mView;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(Documento documentoAlterado) {
		documentoService.salvarAlteracao(documentoAlterado);
		return listaTodosDocumentos();
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluirDocumento(@PathVariable("id") Integer id) {
		documentoService.excluir(id);
		return listaTodosDocumentos();
	}
}