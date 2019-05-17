package com.example.meau;

public class TipoAjuda {
    private static long id=0;

    private boolean alimento;
    private boolean auxfinanceiro;
    private boolean medicamento;
    private String nomemedicamento;
    private boolean objetos;
    private String specobjetos;

    public void setAlimento(boolean alimento) {
        this.alimento = alimento;
    }

    public boolean isAlimento() {
        return alimento;
    }

    public void setAuxfinanceiro(boolean auxfinanceiro) {
        this.auxfinanceiro = auxfinanceiro;
    }

    public boolean isAuxfinanceiro() {
        return auxfinanceiro;
    }

    public void setMedicamento(boolean medicamento) {
        this.medicamento = medicamento;
    }

    public boolean isMedicamento() {
        return medicamento;
    }

    public void setNomemedicamento(String nomemedicamento) {
        this.nomemedicamento = nomemedicamento;
    }

    public String getNomemedicamento() {
        return nomemedicamento;
    }

    public void setObjetos(boolean objetos) {
        this.objetos = objetos;
    }

    public boolean isObjetos() {
        return objetos;
    }

    public void setSpecobjetos(String specobjetos) {
        this.specobjetos = specobjetos;
    }

    public String getSpecobjetos() {
        return specobjetos;
    }
}
