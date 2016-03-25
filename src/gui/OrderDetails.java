package gui;

public class OrderDetails {

    private final int ID;
    private final String dishName;
    private final float dishPrice;
    private final int amount;

    public OrderDetails(int ID, String dishName, float dishPrice, int amount) {
        this.ID = ID;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.amount = amount;
    }

    public String getDishName() {
        return this.dishName;
    }

    public int getID() {
        return this.ID;
    }

    public float getDishPrice() {
        return this.dishPrice;
    }

    public int getAmount() {
        return this.amount;
    }
}
