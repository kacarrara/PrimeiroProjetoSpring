package br.com.primeiroprojetospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.primeiroprojetospring.domain.Professor;
import br.com.primeiroprojetospring.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	@GetMapping("/listaProfessores")
	public ModelAndView listaTodosProfessores() {
		ModelAndView mView = new ModelAndView("professor/paginaListaProfessores");
		mView.addObject("professor", professorService.buscarTodosProfessores());
		return mView;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastraProfessor() {
		ModelAndView mView = new ModelAndView("professor/cadastraProfessor");
		mView.addObject("professor", new Professor());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarAlunos(Professor professor) {
		professorService.salvar(professor);
		return listaTodosProfessores();

	}
	
}