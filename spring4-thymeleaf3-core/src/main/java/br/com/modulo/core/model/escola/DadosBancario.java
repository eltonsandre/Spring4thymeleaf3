package br.com.modulo.core.model.escola;


/**
 * @author elton.santos
 */
public class DadosBancario {

    private String banco;
    private String agencia;
    private int tipoVariacao;
    private String conta;
    private String pagamentos;
    private String nomeTitularConta;
    private String observacao;

    public DadosBancario() {
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public int getTipoVariacao() {
        return tipoVariacao;
    }

    public void setTipoVariacao(int tipoVariacao) {
        this.tipoVariacao = tipoVariacao;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(String pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getNomeTitularConta() {
        return nomeTitularConta;
    }

    public void setNomeTitularConta(String nomeTitularConta) {
        this.nomeTitularConta = nomeTitularConta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
       
}
