package com.example.proodoo;

public class DataModel {

    private String name;
    private String description;
    private String prix;
    private int id_image;
    public DataModel(String name, String description, String prix ,Integer id_)
    {
        this.name=name;
        this.description=description;
        this.prix=prix;
        this.id_image=id_;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public String getPrix() {
        return prix;
    }
    public int getid() { return id_image ;}
}


