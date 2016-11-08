package br.com.modulo.core.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.modulo.core.model.escola.Pessoa;


public class TipoPessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {

	@Override
	public List<Class<?>> getValidationGroups(Pessoa pessoa) {
		List<Class<?>> grupos = new ArrayList<Class<?>>();
		grupos.add(Pessoa.class);

		if (isPessoaSelecionada(pessoa)) {
			grupos.add(pessoa.getTipoPessoa().getGrupo());
		}

		return grupos;
	}

	private boolean isPessoaSelecionada(Pessoa pessoa) {
		return pessoa != null && pessoa.getTipoPessoa() != null;
	}

}
