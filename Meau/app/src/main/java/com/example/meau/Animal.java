package com.example.meau;

import java.util.HashMap;
import java.util.Map;

public class Animal {

    private static long id=0;
    private String nome;
    private String especie;
    private String sexo;
    private String porte;
    private String idade;
    private boolean brincalhao;
    private boolean timido;
    private boolean calmo;
    private boolean guarda;
    private boolean amoroso;
    private boolean preguicoso;
    private boolean vacinado;
    private boolean vermifugado;
    private boolean castrado;
    private boolean doente;
    private String doencas;
    private String hist;
    private int tipo;
    private String fotos;
    private String uid;
    private boolean available = true;

    public Animal(){}

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("uid", getUid());
        map.put("name", getNome());
        map.put("gender", getSexo());
        map.put("age", getIdade());
        map.put("postage", getEspecie());
        map.put("disease", getDoencas());

        return map;
    }

    public void SetNome(String nome){ this.nome = nome;}
    public String getNome() {return this.nome;}
    public void SetEspecie(String especie){ this.especie = especie;}
    public String getEspecie() {return this.especie;}
    public void SetSexo(String sexo){ this.sexo = sexo;}
    public String getSexo(){ return this.sexo;}
    public void SetPorte(String porte) {this.porte = porte;}
    public String getPorte(){ return this.porte;}
    public void SetIdade(String idade) { this.idade = idade;}
    public String getIdade() {return this.idade;}
    public void SetDoencas(String doencas) {this.doencas = doencas;}
    public String getDoencas() {return this.doencas;}
    public void SetHist(String hist) {this.hist = hist;}
    public String getHist() {return this.hist;}

    public void SetTipo(int tipo){this.tipo = tipo;}
    public int getTipo(){return this.tipo;}

    public void SetBrincalhao(boolean brincalhao){ this.brincalhao = brincalhao;}
    public boolean isBrincalhao() { return this.brincalhao; }

    public void SetTimido(boolean timido) {this.timido = timido;}
    public boolean isTimido() {return this.timido;}

    public void SetCalmo(boolean calmo) {this.calmo = calmo;}
    public boolean isCalmo() {return this.calmo;}

    public void SetGuarda(boolean guarda) {this.guarda = guarda;}
    public boolean isGuarda() {return this.guarda;}

    public void SetAmoroso(boolean amoroso) {this.amoroso = amoroso;}
    public boolean isAmoroso() {return this.amoroso;}

    public void SetPreguicoso(boolean preguicoso) {this.preguicoso = preguicoso;}
    public boolean isPreguicoso() {return this.preguicoso;}

    public void SetVacinado(boolean vacinado) {this.vacinado = vacinado;}
    public boolean isVacinado() {return this.vacinado;}

    public void SetVermifugado(boolean vermifugado) {this.vermifugado = vermifugado;}
    public boolean isVermifugado() {return this.vermifugado;}

    public void SetCastrado(boolean castrado) {this.castrado = castrado;}
    public boolean isCastrado() {return this.castrado;}

    public void SetDoente(boolean doente) {this.doente = doente;}
    public boolean isDoente() {return this.doente;}

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public void setUid(String uid){ this.uid = uid;}
    public String getUid() {return this.uid;}

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
