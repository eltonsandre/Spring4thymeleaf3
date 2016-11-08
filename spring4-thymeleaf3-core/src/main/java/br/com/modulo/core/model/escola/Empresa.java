package br.com.modulo.core.model.escola;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.modulo.core.model.Endereco;

/**
 * @author elton.santos
 */
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	private String codigo;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	private String cnpj;

	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;

	private String uf;

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public String getUf() {
		return uf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
