package com.example.meau;

public class TipoApadrinhamento {
    private static long id=0;

    private boolean termoap;
    private boolean auxfinanceiro;
    private boolean alimentacao;
    private boolean saude;
    private boolean objetos;
    private boolean visita;

    TipoApadrinhamento(){}

    public void setTermoap(boolean termoap) {
        this.termoap = termoap;
    }

    public boolean isTermoap() {
        return termoap;
    }

    public void setAuxfinanceiro(boolean auxfinanceiro) {
        this.auxfinanceiro = auxfinanceiro;
    }

    public boolean isAuxfinanceiro() {
        return auxfinanceiro;
    }

    public void setAlimentacao(boolean alimentacao) {
        this.alimentacao = alimentacao;
    }

    public boolean isAlimentacao() {
        return alimentacao;
    }

    public void setSaude(boolean saude) {
        this.saude = saude;
    }

    public boolean isSaude() {
        return saude;
    }

    public void setVisita(boolean visita) {
        this.visita = visita;
    }

    public boolean isVisita() {
        return visita;
    }

    public void setObjetos(boolean objetos) {
        this.objetos = objetos;
    }

    public boolean isObjetos() {
        return objetos;
    }
}
