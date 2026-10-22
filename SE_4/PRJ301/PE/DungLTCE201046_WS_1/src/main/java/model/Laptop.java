package model;

public class Laptop {
    private int id;
    private String brand;
    private String model;
    private int releaseYear;
    private double price;
    private int quantity;

    public Laptop() {
    }

    public Laptop(int id, String brand, String model, int releaseYear, double price, int quantity) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.releaseYear = releaseYear;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
