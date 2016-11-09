package br.com.modulo.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.modulo.core.model.validation.TipoPessoaGroupSequenceProvider;
import br.com.modulo.core.model.validation.group.CnpjGroup;
import br.com.modulo.core.model.validation.group.CpfGroup;

@MappedSuperclass
@GroupSequenceProvider(TipoPessoaGroupSequenceProvider.class)
public abstract class Pessoa  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "Tipo pessoa é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;

	@NotBlank(message = "CPF/CNPJ é obrigatório")
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(name = "cpf_cnpj")
	private String cpfOuCnpj;

	private String rg;
	@Column(name = "data_emissao")
	private Date data_emissao;

	@Column(name = "orgao_emissor")
	private String orgaoEmissor;

	private String naturalidade;
	private Nascionalidade nascionalidade;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "estato_civil")
	private EstatoCivil estatoCivil;

	@JsonIgnore
	@Embedded
	private CNH cnh;

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	private Contato contato;

	@PrePersist
	@PreUpdate
	private void prePersistPreUpdate() {
		this.cpfOuCnpj = TipoPessoa.removerFormatacao(this.cpfOuCnpj);
	}

	@PostLoad
	private void postLoad() {
		this.cpfOuCnpj = this.tipoPessoa.formatar(this.cpfOuCnpj);
	}

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
		Pessoa other = (Pessoa) obj;
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

	public String getNome() {
		return nome;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public String getRg() {
		return rg;
	}

	public Date getData_emissao() {
		return data_emissao;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public Nascionalidade getNascionalidade() {
		return nascionalidade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public EstatoCivil getEstatoCivil() {
		return estatoCivil;
	}

	public CNH getCnh() {
		return cnh;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public void setNascionalidade(Nascionalidade nascionalidade) {
		this.nascionalidade = nascionalidade;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEstatoCivil(EstatoCivil estatoCivil) {
		this.estatoCivil = estatoCivil;
	}

	public void setCnh(CNH cnh) {
		this.cnh = cnh;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
