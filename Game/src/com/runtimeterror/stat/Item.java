package com.runtimeterror.stat;

public class Item {



    // FIELDS
    private final String name;
    private final String type;
    private final String description;

    //CONSTRUCTOR
    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
