package br.com.modulo.core.model.escola;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CNH {

	@Column(name = "registro_cnh")
	private String registro;

	@Column(name = "validade_cnh")
	private Date validade;

	@Column(name = "data_primeira_cnh")
	private Date dataPrimeiraCNH;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_cnh")
	private CategoriaCNH categoria;

	@Column(name = "anexo_cnh")
	private String anexo;

	public String getRegistro() {
		return registro;
	}

	public Date getValidade() {
		return validade;
	}

	public Date getDataPrimeiraCNH() {
		return dataPrimeiraCNH;
	}

	public CategoriaCNH getCategoria() {
		return categoria;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public void setDataPrimeiraCNH(Date dataPrimeiraCNH) {
		this.dataPrimeiraCNH = dataPrimeiraCNH;
	}

	public void setCategoria(CategoriaCNH categoria) {
		this.categoria = categoria;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

}
