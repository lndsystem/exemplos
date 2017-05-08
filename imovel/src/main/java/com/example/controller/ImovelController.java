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

import com.example.model.Imovel;
import com.example.repository.FinalidadeRepository;
import com.example.repository.ImovelRepository;
import com.example.repository.TipoRepository;

@Controller
@RequestMapping("/imovel")
public class ImovelController {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private TipoRepository tipoRepository;

	@Autowired
	private FinalidadeRepository finalidadeRepository;

	@GetMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/imovel/pesquisa");
		mv.addObject("imoveis", imovelRepository.findAll());
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Imovel imovel) {
		ModelAndView mv = new ModelAndView("/imovel/cadastro");
		mv.addObject(imovel);
		mv.addObject("finaliades", finalidadeRepository.findAll());
		mv.addObject("tipos", tipoRepository.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Imovel imovel, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(imovel);
		}

		imovelRepository.save(imovel);
		attributes.addFlashAttribute("mensagem", "Imovel salvo com sucesso!");
		return new ModelAndView("redirect:/imovel/novo");
	}
}
