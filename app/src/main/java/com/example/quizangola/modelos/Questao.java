package com.example.quizangola.modelos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "questao")
public class Questao {
    @DatabaseField(generatedId=true)
    private long id;
    @DatabaseField
    private String r1;
    @DatabaseField
    private String r2;
    @DatabaseField
    private String r3;
    @DatabaseField
    private String rv;
    @DatabaseField
    private String pergunta;
    @DatabaseField
    private String categoria;

    public Questao(){}
    public Questao(String r1, String r2, String r3, String rv, String pergunta, String categoria) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.rv = rv;
        this.pergunta = pergunta;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getRv() {
        return rv;
    }

    public void setRv(String rv) {
        this.rv = rv;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}
