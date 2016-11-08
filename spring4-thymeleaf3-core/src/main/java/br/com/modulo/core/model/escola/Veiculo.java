package br.com.modulo.core.model.escola;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author elton.santos
 */

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	private boolean ativo;

	private String codigo;
	private String patrimonio;
	private String descricao;

	private String modelo;
	private String marca;
	private String placa;

	@Column(name = "cor_predominante")
	private String corPredominante;

	@Column(name = "tipo_veiculo")
	private String tipoVeiculo;

	@Column(name = "ano_fabricacao")
	private Integer anoFabricacao;

	@Column(name = "ano_modelo")
	private Integer anoModelo;

	private String chassi;
	private String renavam;

	@Column(name = "numero_serie")
	private String numeroSerie;
	private String combustivel;

	@Column(name = "data_aquisicao")
	private Date dataAquisicao;

	@Column(name = "valor_aquisicao")
	private String valorAquisicao;

	private String observacao;

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
		Veiculo other = (Veiculo) obj;
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

	public boolean isAtivo() {
		return ativo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public String getPlaca() {
		return placa;
	}

	public String getCorPredominante() {
		return corPredominante;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public String getChassi() {
		return chassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public String getValorAquisicao() {
		return valorAquisicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setCorPredominante(String corPredominante) {
		this.corPredominante = corPredominante;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public void setValorAquisicao(String valorAquisicao) {
		this.valorAquisicao = valorAquisicao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
