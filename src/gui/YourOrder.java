package gui;

public class YourOrder {

    private int ID;
    private String restaurantName, status;
    private float price;
    private int stars;

    public YourOrder(int ID, String restaurantName, float price, String status,
            int stars) {
        this.ID = ID;
        this.restaurantName = restaurantName;
        this.price = price;
        this.status = status;
        this.stars = stars;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public int getID() {
        return this.ID;
    }

    public float getPrice() {
        return this.price;
    }

    public String getStatus() {
        return this.status;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
