package br.com.modulo.core.model.escola;

public enum Sexo {

	MASCULINO("Masculino", "M", true),
	FEMININO("Feminino", "F", false);

	private String descricao;
	private String code;
	private boolean isMasculino;

	private Sexo(String descricao, String code, boolean isMasculino) {
		this.descricao = descricao;
		this.code = code;
		this.isMasculino = isMasculino;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCode() {
		return code;
	}

	public boolean isMasculino() {
		return isMasculino;
	}

}
