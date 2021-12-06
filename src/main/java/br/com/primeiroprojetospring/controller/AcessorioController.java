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

import br.com.primeiroprojetospring.domain.Acessorio;
import br.com.primeiroprojetospring.service.AcessorioService;

@RestController
@RequestMapping("acessorio")
public class AcessorioController {

	@Autowired
	private AcessorioService acessorioService;

	@PostMapping("/cadastrarAcessorio")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Acessorio> cadastrarAcessorioAPI(@RequestBody Acessorio acessorio){
		return ResponseEntity.ok().body(acessorioService.salvar(acessorio));
	}
    
	@GetMapping("/find/{id}")
	public ResponseEntity<Acessorio> find(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(acessorioService.buscarPorID(id));
	}
	
	@GetMapping("todosAcessorios")
	public ResponseEntity<List<Acessorio>> devolverTodosAcessorios(){
		return ResponseEntity.ok().body(acessorioService.buscarTodosAcessorios());
	}
	
	
	@PutMapping("/alteraAcessorio")
	public ResponseEntity<Acessorio> alteraAcessorioEntity (@RequestBody Acessorio acessorio){
		Acessorio novoAcessorio= acessorioService.salvarAlteracao(acessorio);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoAcessorio);
	}
	
	
	
	
	@GetMapping("/listaAcessorios")
	public ModelAndView listaTodosAcessorios() {
		ModelAndView mView = new ModelAndView("acessorio/paginaListaAcessorios");
		mView.addObject("acessorio", acessorioService.buscarTodosAcessorios());
		return mView;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastraAcessorio() {
		ModelAndView mView = new ModelAndView("acessorio/cadastraAcessorio");
		mView.addObject("acessorio", new Acessorio());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarAcessorios(Acessorio acessorio) {
		acessorioService.salvar(acessorio);
		return listaTodosAcessorios();

	}
	
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAcessorios(@PathVariable("id") Integer idAcessorio) {
		ModelAndView mView = new ModelAndView("acessorio/alteraAcessorio"); 
		mView.addObject("acessorio",  acessorioService.buscarPorID(idAcessorio));
		return mView;
		
	}
	@PostMapping("/alterar")
	public ModelAndView alterar(Acessorio acessorioAlterado) {
		acessorioService.salvarAlteracao(acessorioAlterado);
		return listaTodosAcessorios();
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAcessorio(@PathVariable ("id") Integer id) {
		acessorioService.excluir(id);
		return listaTodosAcessorios();
	}
}