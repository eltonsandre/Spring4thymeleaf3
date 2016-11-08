package br.com.modulo.core.model.escola;

public enum EstatoCivil {

	SOLTEIRO("Solteiro"),
	CASADO("Casado"),
	UNIAO_ESTAVEL("União estável"),
	SEPARACAO("Separado"),
	VIUVO("Viúvo");

	private String descricao;

	private EstatoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
