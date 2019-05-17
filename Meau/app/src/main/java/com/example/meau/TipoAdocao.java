package com.example.meau;

public class TipoAdocao {
    private static long id=0;
    private boolean termoadocao;
    private boolean fotoscasa;
    private boolean visitaprevia;
    private boolean acompanhamento;
    private boolean ummes;
    private boolean tresmes;
    private boolean seismes;

    public TipoAdocao(){}

    public void setTermoadocao(boolean termoadocao) {
        this.termoadocao = termoadocao;
    }

    public boolean isTermoadocao() {
        return this.termoadocao;
    }

    public void setFotoscasa(boolean fotoscasa){
        this.fotoscasa = fotoscasa;
    }

    public boolean isFotoscasa() {
        return this.fotoscasa;
    }

    public void setVisitaprevia(boolean visitaprevia){this.visitaprevia = visitaprevia;}
    public boolean isVisitaprevia(){return this.visitaprevia;}

    public void setAcompanhamento(boolean acompanhamento){this.acompanhamento = acompanhamento;}
    public boolean isAcompanhamento(){return this.acompanhamento;}

    public void setUmmes(boolean ummes){this.ummes = ummes;}
    public boolean isUmmes(){return this.ummes;}

    public void setTresmes(boolean tresmes){this.tresmes = tresmes;}
    public boolean isTresmes(){return this.tresmes;}

    public void setSeismes(boolean seismes){this.seismes = seismes;}
    public boolean isSeismes(){return this.seismes;}
}
