package br.com.modulo.core.model.escola;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "curso_aluno")
public class CursoAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	private Aluno aluno;

	@Column(name = "maximo_aulas")
	private Integer maximoAulas;

	@Column(name = "maximo_aulas_diurnas")
	private Integer maximoAulasDiurnas;

	@Column(name = "maximo_aulas_noturnas")
	private Integer maximoAulasNoturnas;

	private Boolean status;

	@OneToMany(mappedBy = "curso_aluno", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Agendamento> agendamentos = new ArrayList<Agendamento>();

	public Long getId() {
		return id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Integer getMaximoAulas() {
		return maximoAulas;
	}

	public Integer getMaximoAulasDiurnas() {
		return maximoAulasDiurnas;
	}

	public Integer getMaximoAulasNoturnas() {
		return maximoAulasNoturnas;
	}

	public Boolean getStatus() {
		return status;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setMaximoAulas(Integer maximoAulas) {
		this.maximoAulas = maximoAulas;
	}

	public void setMaximoAulasDiurnas(Integer maximoAulasDiurnas) {
		this.maximoAulasDiurnas = maximoAulasDiurnas;
	}

	public void setMaximoAulasNoturnas(Integer maximoAulasNoturnas) {
		this.maximoAulasNoturnas = maximoAulasNoturnas;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
}