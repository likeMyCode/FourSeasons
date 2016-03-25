package gui;

public class OrderEntry {

    private String name;
    private float price;
    private int amount;

    public OrderEntry(String name, float price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
