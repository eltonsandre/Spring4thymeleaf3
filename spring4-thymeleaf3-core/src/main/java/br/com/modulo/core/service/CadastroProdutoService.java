package br.com.modulo.core.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import br.com.modulo.core.model.Produto;

@Service
public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 6801088020380898196L;

	public void salvar(Produto produto) {
		// TODO Auto-generated method stub
		System.out.println("CadastroProdutoService.salvar");

	}

}
