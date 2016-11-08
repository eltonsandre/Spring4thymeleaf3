package br.com.modulo.core.model.escola;

public enum Nascionalidade {

	BRASILEIRO("Brasileiro", "RG"),
	ESTRANGEIRO("Estrangeiro", "RNE");

	private String descricao;
	private String documento;

	private Nascionalidade(String descricao, String documento) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

}
