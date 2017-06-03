package com.greenbee.cm.rest;

public class JavaBean {

    private String name = "POJO";
    private String description = "I am POJO";
    private int[] array = { 1, 1, 2, 3, 5, 8, 13, 21 };

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
