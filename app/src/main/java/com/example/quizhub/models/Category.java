package com.example.quizhub.models;

public class Category {
    private String name;
    private String colorHex;
    private int backgroundResource;
    
    public Category(String name, String colorHex, int backgroundResource) {
        this.name = name;
        this.colorHex = colorHex;
        this.backgroundResource = backgroundResource;
    }
    
    public String getName() {
        return name;
    }
    
    public String getColorHex() {
        return colorHex;
    }
    
    public int getBackgroundResource() {
        return backgroundResource;
    }
}