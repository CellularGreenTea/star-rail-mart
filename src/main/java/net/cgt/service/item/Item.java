package net.cgt.service.item;

public class Item {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Item(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    @Override
    public String toString() {
        return String.format("Item{code=%s, name='%s', price=%.2f, stock=%d}", this.code, this.name, this.price, this.stock);
    }
}
