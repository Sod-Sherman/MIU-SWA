package edu.miu.reactiveclient.DTO;

public class StockDTO {
    private String id;
    private String name;
    private double price;

    public StockDTO(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public StockDTO() {
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
