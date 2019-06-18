package com.example.listview.models;


import java.io.Serializable;

public class Article implements Serializable {

    private String name;
    private String measure;
    private int qte;
    private boolean checked;

    public Article(String name, String measure, int qte){
        this.name = name;
        this.measure = measure;
        this.qte = qte;
    }

    public Article(String name){
        this.name = name;
        this.measure = "-";
        this.qte = 1;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
