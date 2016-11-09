package br.com.modulo.core.util;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressaoRegular {

    private Pattern padrao;
    private Matcher pesquisa;
    private String expressaoRegular;
    private String validar;

    public ExpressaoRegular() {
    }

    public ExpressaoRegular(Pattern padrao, Matcher pesquisa, String expressaoRegular, String validar) {
        this.padrao = padrao;
        this.pesquisa = pesquisa;
        this.expressaoRegular = expressaoRegular;
        this.validar = validar;
    }

    public void setExpressaoRegular(String expressaoRegular) {
        this.expressaoRegular = expressaoRegular;
    }

    public void setValidar(String validar){
        this.validar = validar;
    }

    public void setPadrao() {
        this.padrao = compile(this.expressaoRegular);
    }

    public void setPesquisa() {
        this.pesquisa = this.padrao.matcher(this.validar);
    }

    public String getExpressaoRegular() {
        return expressaoRegular;
    }

    public String getValidar(){
        return validar;
    }

    public Pattern getPadrao() {
        return padrao;
    }

    public Matcher getPesquisa() {
        return pesquisa;
    }
}