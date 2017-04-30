package com.zs.xml.entity;

public class Book {
    private String id;
    private String name;
    private String author;
    private String year;
    private String price;
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static final String BOOK = "F:\\Workspace\\workspace-intellij-ide\\xml_parse\\src\\main\\resources\\books.xml";
    public static final String BOOK1 = "F:\\Workspace\\workspace-intellij-ide\\xml_parse\\src\\main\\resources\\books1.xml";
    public static final String BOOK2 = "F:\\Workspace\\workspace-intellij-ide\\xml_parse\\src\\main\\resources\\books2.xml";
}
