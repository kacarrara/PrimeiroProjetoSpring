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

import br.com.primeiroprojetospring.domain.Chave;
import br.com.primeiroprojetospring.service.ChaveService;

@RestController
@RequestMapping("chave")
public class ChaveController {

	@Autowired
	private ChaveService chaveService;

	@GetMapping("/findByCodigo/{codigo}")
	public ResponseEntity<List<Chave>> findByCodigo(@PathVariable("codigo") String codigo) {
		return ResponseEntity.ok().body(chaveService.findByCodigo(codigo));
	}
	
	
	@PostMapping("/cadastrarChave")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Chave> cadastrarChaveAPI(@RequestBody Chave chave){
		return ResponseEntity.ok().body(chaveService.salvar(chave));
	}
    
	@GetMapping("/find/{id}")
	public ResponseEntity<Chave> find(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(chaveService.buscarChaveID(id));
	}
	
	@GetMapping("todasChaves")
	public ResponseEntity<List<Chave>> devolverTodosChaves(){
		return ResponseEntity.ok().body(chaveService.buscarTodasChaves());
	}
	
	
	@PutMapping("/alteraChave")
	public ResponseEntity<Chave> alteraAlunoEntity (@RequestBody Chave chave){
		Chave novaChave= chaveService.salvarAlteracao(chave);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaChave);
	}
	
	
	
	@GetMapping("/listaChaves")
	public ModelAndView listaTodasChaves() {
		ModelAndView mView = new ModelAndView("chave/paginaListaChaves");
		mView.addObject("chave", chaveService.buscarTodasChaves());
		return mView;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastraChave() {
		ModelAndView mView = new ModelAndView("chave/cadastraChave");
		mView.addObject("chave", new Chave());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarChaves(Chave chave) {
		chaveService.salvar(chave);
		return listaTodasChaves();

	}
	
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarChave(@PathVariable("id") Integer idChave) {
		ModelAndView mView = new ModelAndView("chave/alteraChave"); 
		mView.addObject("chave",  chaveService.buscarChaveID(idChave));
		return mView;
		
	}
	@PostMapping("/alterar")
	public ModelAndView alterar(Chave chaveAlterada) {
		chaveService.salvarAlteracao(chaveAlterada);
		return listaTodasChaves();
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable ("id") Integer id) {
		chaveService.excluir(id);
		return listaTodasChaves();
	}
}