package br.com.modulo.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.modulo.core.model.Produto;
import br.com.modulo.core.repository.Produtos;
import br.com.modulo.core.service.CadastroProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private CadastroProdutoService cadastroCervejaService;

	@Autowired
	private Produtos produtos;

	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
//		mv.addObject("sabores", Sabor.values());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(produto);
		}

		cadastroCervejaService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto salva com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
	}


	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto) {
		ModelAndView mv = novo(produto);
		mv.addObject(produto);
		return mv;
	}

}
