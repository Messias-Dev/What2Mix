package com.what2mix.domain;

import java.util.Objects;

public class Ingredient {

    private String name;
    private String id;

    public Ingredient() {
    }

    public Ingredient(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return getId() == that.getId() &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingredientName='" + name + '\'' +
                ", ingredientId=" + id +
                '}';
    }
}
