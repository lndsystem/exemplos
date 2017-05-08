package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Finalidade;
import com.example.repository.FinalidadeRepository;

@Controller
@RequestMapping("/finalidade")
public class FinalidadeController {

	@Autowired
	private FinalidadeRepository finalidadeRepository;

	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("/finalidade/pesquisa");
		mv.addObject("finalidades", finalidadeRepository.findAll());
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Finalidade finalidade) {
		ModelAndView mv = new ModelAndView("/finalidade/cadastro");
		mv.addObject(finalidade);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Finalidade finalidade, BindingResult result, RedirectAttributes attributs) {
		if (result.hasErrors()) {
			return novo(finalidade);
		}

		finalidadeRepository.save(finalidade);
		attributs.addFlashAttribute("mensagem", "Finalidade salva com sucesso!");
		return new ModelAndView("redirect:/finalidade/novo");
	}

}
