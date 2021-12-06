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

import br.com.primeiroprojetospring.domain.Fabricante;
import br.com.primeiroprojetospring.service.FabricanteService;

@RestController
@RequestMapping("fabricante")
public class FabricanteController {

	@Autowired
	private FabricanteService fabricanteService;

	@PostMapping("/cadastrarFabricante")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Fabricante> cadastrarFabricanteAPI(@RequestBody Fabricante fabricante) {
		return ResponseEntity.ok().body(fabricanteService.salvar(fabricante));
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Fabricante> find(@PathVariable("id") Integer id) {
		return ResponseEntity.ok().body(fabricanteService.buscarPorID(id));
	}

	@GetMapping("todosFabricantes")
	public ResponseEntity<List<Fabricante>> devolverTodosFabricantes() {
		return ResponseEntity.ok().body(fabricanteService.buscarTodosFabricantes());
	}

	@PutMapping("/alteraFabricante")
	public ResponseEntity<Fabricante> alteraFabricantEntity(@RequestBody Fabricante fabricante) {
		Fabricante novoFabricante = fabricanteService.salvarAlteracao(fabricante);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoFabricante);
	}

	@GetMapping("/listaFabricantes")
	public ModelAndView listaTodosFabricantes() {
		ModelAndView mView = new ModelAndView("fabricante/paginaListaFabricantes");
		mView.addObject("fabricante", fabricanteService.buscarTodosFabricantes());
		return mView;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarFabricantes() {
		ModelAndView mView = new ModelAndView("fabricante/cadastraFabricante");
		mView.addObject("fabricante", new Fabricante());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarFabricantes(Fabricante fabricante) {
		fabricanteService.salvar(fabricante);
		return listaTodosFabricantes();

	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarFabricante(@PathVariable("id") Integer idFabricante) {
		ModelAndView mView = new ModelAndView("fabricante/alteraFabricante");
		mView.addObject("fabricante", fabricanteService.buscarPorID(idFabricante));
		return mView;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(Fabricante fabricanteAlterado) {
		fabricanteService.salvarAlteracao(fabricanteAlterado);
		return listaTodosFabricantes();
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluirFabricante(@PathVariable("id") Integer id) {
		fabricanteService.excluir(id);
		return listaTodosFabricantes();
	}
}