package gui;

public class MenuEntry {

    private int ID;
    private String name, description, price, type;
    private boolean vegetarian, spicy, fish, garlic;

    public MenuEntry(int ID, String name, String description, String price,
            String type, boolean vegetarian, boolean spicy,
            boolean fish, boolean garlic) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.vegetarian = vegetarian;
        this.spicy = spicy;
        this.fish = fish;
        this.garlic = garlic;
    }

    public String getName() {
        return this.name;
    }
    
    public int getID() {
        return this.ID;
    }

    public String getDescription() {
        String formatDescription = "";
        formatDescription = formatDescription.concat(this.description);
        return formatDescription;
    }

    public String getPrice() {
        return this.price;
    }
    
    public String getRealType() {
        return this.type;
    }

    public String getType() {
        switch (this.type) {
            case "STARTER":
                return "Starter";
            case "SOUP":
                return "Soup";
            case "MAIN_COURSE":
                return "Main Course";
            case "PASTA":
                return "Pasta";
            case "PIZZA":
                return "Pizza";
            case "DESSERT":
                return "Dessert";
            case "OTHER":
                return "Other";
            default:
                return null;
        }
    }

    public boolean isVegetarian() {
        return this.vegetarian;
    }

    public boolean isFish() {
        return this.fish;
    }

    public boolean isSpicy() {
        return this.spicy;
    }

    public boolean isGarlic() {
        return this.garlic;
    }
}
