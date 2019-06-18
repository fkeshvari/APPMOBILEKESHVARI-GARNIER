package com.example.listview.models;

import com.example.listview.models.Article;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {

    private String name;
    private ArrayList<Article> articleList;
    private boolean isSelected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<Article> articleList) {
        this.articleList = articleList;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Shop(String name, ArrayList<Article> articleList){
        this.name = name;
        this.articleList = articleList;
    }
}
