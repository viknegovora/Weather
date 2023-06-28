package com.example.weather.model;

public class City {

    // поля сущности
//    private String id;// поле идентификатора города
    private String title;// поле заголовка города

    //конструктор

    public City(String title) {
//        this.id = id;
        this.title = title;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
