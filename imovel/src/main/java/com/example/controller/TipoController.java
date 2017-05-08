package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Tipo;
import com.example.repository.TipoRepository;

@Controller
@RequestMapping("/tipo")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;

	@GetMapping
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("/tipo/pesquisa");
		mv.addObject("tipos", tipoRepository.findAll());
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Tipo tipo) {
		ModelAndView mv = new ModelAndView("/tipo/cadastro");
		mv.addObject(tipo);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Tipo tipo, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipo);
		}

		tipoRepository.save(tipo);
		attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
		return new ModelAndView("redirect:/tipo/novo");
	}
}
