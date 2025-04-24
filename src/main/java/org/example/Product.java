package org.example;

public class Product {
    private String cod_p;
    private String type;
    private double price; // price/piece
    private double weight;

    public Product(String cod_p, String type, double price, double weight) {
        this.cod_p = cod_p;
        this.type = type;
        this.price = price;
        this.weight = weight;
    }

    // Getter and Setter
    public String getCod_p() { return cod_p; }
    public void setCod_p(String cod_p) { this.cod_p = cod_p;  }

    public String getType() { return type; }
    public void setType(String type) { this.type = type;  }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price;  }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    @Override
    public String toString() {
        return "Product: " + type + " ( Product Code: " + cod_p + ", Price(/u): " + price + " Weight:(kg) " + weight + " )";
    }
}
