package com.example.listview;


import java.io.Serializable;

public class Article implements Serializable {

    private String name;
    private int measure;
    private int qte;
    private boolean checked;

    public Article(String name, int measure, int qte){
        this.name = name;
        this.measure = measure;
        this.qte = qte;
    }

    public Article(String name){
        this.name = name;
        this.measure = Measure.NOTYPE;
        this.qte = 1;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
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
