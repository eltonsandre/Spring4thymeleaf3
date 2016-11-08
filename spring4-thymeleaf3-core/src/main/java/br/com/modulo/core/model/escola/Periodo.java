package br.com.modulo.core.model.escola;

/**
 * Enum para representar período "Diurno" e "Noturno"
 *
 * @author Elton Sandré
 */
public enum Periodo {

	DIURNO("Diurno"),
	NOTURNO("Noturno");

	private String descricao;

	private Periodo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
