
package br.com.modulo.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade Funcionário
 *
 * @author Elton Sandré
 */
@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa implements Serializable {

	private static final long serialVersionUID = 4126290102201393045L;

	private boolean ativo;
	private String codigo;
	private String cargo;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_admissao")
	private Date dataAdmissao;

	private String observacao;

	public boolean isAtivo() {
		return ativo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCargo() {
		return cargo;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
