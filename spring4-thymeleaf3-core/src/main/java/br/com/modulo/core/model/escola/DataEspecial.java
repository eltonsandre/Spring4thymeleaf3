package br.com.modulo.core.model.escola;

import java.util.Date;

public class DataEspecial {

    private int id;
    private java.util.Date dataCadastro;
    private String nomeData;
    private String descricaoData;
    private String tipoData;
    private java.util.Date dataInicial;
    private java.util.Date dataFinal;

    public DataEspecial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeData() {
        return nomeData;
    }

    public void setNomeData(String nomeData) {
        this.nomeData = nomeData;
    }

    public String getDescricaoData() {
        return descricaoData;
    }

    public void setDescricaoData(String descricaoData) {
        this.descricaoData = descricaoData;
    }

    public String getTipoData() {
        return tipoData;
    }

    public void setTipoData(String tipoData) {
        this.tipoData = tipoData;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
  }
