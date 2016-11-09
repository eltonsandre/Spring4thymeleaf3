package br.com.modulo.core.model;

public enum EstatoCivil {

	/**
	 * Solteiro(ª) é a pessoa que nunca contraiu núpcias, ou que, tendo
	 * contraído, teve seu casamento anulado;
	 */
	SOLTEIRO("Solteiro"),

	/**
	 * Casado (ª) é a pessoa que contraiu matrimônio perante a lei civil;
	 */
	CASADO("Casado"),

	/**
	 * Separado (ª) judicialmente é a pessoa que formalizou a separação de fato
	 * perante a justiça, mas que ainda não se divorciou;
	 */
	UNIAO_ESTAVEL("União estável"),

	/**
	 * Divorciado (ª) é a pessoa que formalizou a separação de fato perante a
	 * justiça ou por escritura pública de divórcio; e
	 */
	SEPARACAO("Separado"),

	/**
	 * Viúvo (ª) é a pessoa que teve o cônjuge falecido.
	 */
	VIUVO("Viúvo");

	private String descricao;

	private EstatoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
