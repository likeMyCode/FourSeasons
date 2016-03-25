package four_seasons;

import gui.MenuEntry;
import gui.OrderDetails;
import gui.OrderEntry;
import gui.YourOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

public class DatabaseRequest {

    private static final String DB_USER_NAME = "<DATABASE_LOGIN>";
    private static final String DB_PASSWORD = "<DATABASE_PASSWORD>";
    private static final String DB_DOMAIN = "jdbc:oracle:thin:@//"
            + "admlab2-main.cs.put.poznan.pl:1521/"
            + "dblab01.cs.put.poznan.pl";

    private static Connection conn;
    private static Properties connectionProps;

    public static void connectToDatabase() {

        conn = null;
        connectionProps = new Properties();
        connectionProps.put("user", DB_USER_NAME);
        connectionProps.put("password", DB_PASSWORD);
        System.out.println("Polaczono");
        try {
            conn = DriverManager.getConnection(DB_DOMAIN, connectionProps);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Niepolaczono");
            System.exit(-1);
        }

    }

    public static int signIn(String login, String password) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement signIn = null;
        int returnValue = -1;

        try {
            signIn = conn.prepareStatement("SELECT TYPE FROM USERS WHERE "
                    + "UPPER(LOGIN)=? AND PASSWORD=?");
            signIn.setString(1, login.toUpperCase());
            signIn.setString(2, password);

            ResultSet rs = signIn.executeQuery();

            if (!rs.isFirst()) {
                returnValue = 0;
            }

            while (rs.next()) {
                switch (rs.getString(1)) {
                    case "CLIENT":
                        returnValue = 1;
                        break;
                    case "MANAGER":
                        returnValue = 2;
                        break;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (signIn != null) {
                try {
                    signIn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return returnValue;
    }

    public static int registerClient(String login, String password,
            String city, String street, String houseNumber, String flatNumber) {

        if (conn == null) {
            connectToDatabase();
        }

        int returnValue = -2;
        PreparedStatement streetExists = null;
        PreparedStatement loginTaken = null;
        PreparedStatement insertUser = null;

        try {
            loginTaken = conn.prepareStatement("SELECT 1 FROM USERS WHERE "
                    + "UPPER(LOGIN)=?");
            loginTaken.setString(1, login.toUpperCase());

            streetExists = conn.prepareStatement("SELECT 1 FROM DISTRICTS WHERE "
                    + "UPPER(STREET)=? AND UPPER(CITY)=?");
            streetExists.setString(1, street.toUpperCase());
            streetExists.setString(2, city.toUpperCase());

            insertUser = conn.prepareStatement("INSERT INTO USERS (LOGIN, PASSWORD, "
                    + "TYPE, CITY, STREET, HOUSE_NUMBER, FLAT_NUMBER) VALUES "
                    + "(?,?,?,?,?,?,?)");
            insertUser.setString(1, login);
            insertUser.setString(2, password);
            insertUser.setString(3, "CLIENT");
            insertUser.setString(4, city);
            insertUser.setString(5, street);
            insertUser.setString(6, houseNumber);
            insertUser.setString(7, flatNumber);

            ResultSet rs = loginTaken.executeQuery();

            if (rs.next()) {
                returnValue = 0;   // LOGIN TAKEN
            } else {
                rs = streetExists.executeQuery();

                if (rs.next()) {
                    insertUser.executeUpdate();
                    returnValue = 1;   // USER REGISTERED
                } else {
                    returnValue = -1;   // STREET DOES NOT EXIST

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (loginTaken != null) {
                try {
                    loginTaken.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (streetExists != null) {
                try {
                    streetExists.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (insertUser != null) {
                try {
                    insertUser.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return returnValue;
    }

    public static int registerRestaurant(String name, String city, String street,
            String houseNumber, String flatNumber, String cuisine, String deliveryCost,
            String minimumCost, String managerLogin) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement addressCorrect = null;
        PreparedStatement insertRestaurant = null;
        PreparedStatement managerNoRestaurant = null;
        int returnValue = -2;

        try {
            addressCorrect = conn.prepareStatement("SELECT 1 FROM DISTRICTS WHERE "
                    + "UPPER(STREET)=UPPER(?) AND UPPER(CITY)=UPPER(?)");
            addressCorrect.setString(1, street.toUpperCase());
            addressCorrect.setString(2, city.toUpperCase());

            managerNoRestaurant = conn.prepareStatement(
                    "SELECT 1 FROM RESTAURANTS WHERE UPPER(MANAGER)=UPPER(?)");
            managerNoRestaurant.setString(1, managerLogin.toUpperCase());

            insertRestaurant = conn.prepareStatement("INSERT INTO RESTAURANTS (NAME, "
                    + "CITY, STREET, HOUSE_NUMBER, FLAT_NUMBER, CUISINE, DELIVERY_COST, "
                    + "MINIMUM_COST, MANAGER) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)");
            insertRestaurant.setString(1, name);
            insertRestaurant.setString(2, city);
            insertRestaurant.setString(3, street);
            insertRestaurant.setString(4, houseNumber);
            insertRestaurant.setString(5, flatNumber);
            insertRestaurant.setString(6, cuisine);
            insertRestaurant.setString(7, deliveryCost);
            insertRestaurant.setString(8, minimumCost);
            insertRestaurant.setString(9, managerLogin);

            ResultSet rs = managerNoRestaurant.executeQuery();

            if (rs.next()) {
                return 0;
            } else {
                rs = addressCorrect.executeQuery();
                if (rs.next()) {
                    insertRestaurant.executeUpdate();
                    returnValue = 1;
                } else {
                    returnValue = -1;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (addressCorrect != null) {
                try {
                    addressCorrect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (managerNoRestaurant != null) {
                try {
                    managerNoRestaurant.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (insertRestaurant != null) {
                try {
                    insertRestaurant.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return returnValue;
    }

    public static boolean managerHasRestaurant(String login) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement searchManager = null;
        boolean returnValue = false;
        try {
            searchManager = conn.prepareStatement(
                    "SELECT 1 FROM RESTAURANTS WHERE UPPER(MANAGER)=UPPER(?)");
            searchManager.setString(1, login.toUpperCase());

            ResultSet rs = searchManager.executeQuery();

            if (rs.next()) {
                returnValue = true;
            } else {
                returnValue = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (searchManager != null) {
                try {
                    searchManager.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return returnValue;
    }

    public static ArrayList<String> getRestaurantList(String login) {
        ArrayList<String> restaurantList = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement getUserDistrict = null;
        PreparedStatement findRestaurants = null;

        try {
            getUserDistrict = conn.prepareStatement(
                    "SELECT DISTRICTS.NAME FROM USERS INNER JOIN DISTRICTS "
                    + "ON UPPER(DISTRICTS.STREET)=UPPER(USERS.STREET) AND "
                    + "UPPER(DISTRICTS.CITY)=UPPER(USERS.CITY) WHERE UPPER(USERS.LOGIN)=UPPER(?)");
            getUserDistrict.setString(1, login.toUpperCase());

            findRestaurants = conn.prepareStatement(
                    "SELECT R.ID, R.NAME, R.CUISINE, "
                    + "NVL((SELECT AVG(S.STARS) FROM RATES S WHERE S.RESTAURANT_ID=R.ID), 0), "
                    + "R.DELIVERY_COST, R.MINIMUM_COST FROM RESTAURANTS R "
                    + "WHERE (SELECT UPPER(D.NAME) FROM RESTAURANTS R2 INNER JOIN "
                    + "DISTRICTS D ON UPPER(D.STREET)=UPPER(R2.STREET) AND "
                    + "UPPER(D.CITY)=UPPER(R2.CITY) WHERE R2.ID=R.ID)=UPPER(?) ORDER BY R.NAME");

            ResultSet rs = getUserDistrict.executeQuery();

            if (rs.next()) {
                String userDistrict = rs.getString(1);
                findRestaurants.setString(1, userDistrict.toUpperCase());
                rs = findRestaurants.executeQuery();

                while (rs.next()) {
                    restaurantList.add(rs.getString(1));
                    restaurantList.add(rs.getString(2));
                    restaurantList.add(rs.getString(3));
                    restaurantList.add(rs.getString(4));
                    restaurantList.add(rs.getString(5));
                    restaurantList.add(rs.getString(6));
                }
            } else {
                restaurantList = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getUserDistrict != null) {
                try {
                    getUserDistrict.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (findRestaurants != null) {
                try {
                    findRestaurants.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return restaurantList;
    }

    public static ArrayList<MenuEntry> getDishList(int resID) {
        ArrayList<MenuEntry> menuEntry = new ArrayList<>();
        int dishID = 0;

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement findDishes = null;

        try {
            findDishes = conn.prepareStatement(
                    "SELECT NAME, DESCRIPTION, PRICE, TYPE, VEGETARIAN, SPICY, FISH, "
                    + "GARLIC, ID FROM DISHES WHERE RESTAURANT_ID=? ORDER BY NAME");

            findDishes.setInt(1, resID);
            ResultSet rs = findDishes.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                String description = rs.getString(2);
                String price = Float.toString(rs.getFloat(3)).replaceAll(".0", "");
                String type = rs.getString(4);
                boolean vegetarian, spicy, fish, garlic;

                if (rs.getInt(5) == 0) {
                    vegetarian = false;
                } else {
                    vegetarian = true;
                }

                if (rs.getInt(6) == 0) {
                    spicy = false;
                } else {
                    spicy = true;
                }

                if (rs.getInt(7) == 0) {
                    fish = false;
                } else {
                    fish = true;
                }

                if (rs.getInt(8) == 0) {
                    garlic = false;
                } else {
                    garlic = true;
                }

                dishID = rs.getInt(9);

                menuEntry.add(new MenuEntry(dishID, name, description, price, type, vegetarian,
                        spicy, fish, garlic));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (findDishes != null) {
                try {
                    findDishes.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return menuEntry;
    }

    public static int getRestaurantID(String login) {
        int ID = 0;

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement findResID = null;

        try {
            findResID = conn.prepareStatement(
                    "SELECT R.ID FROM RESTAURANTS R JOIN USERS U "
                    + "ON UPPER(R.MANAGER)=UPPER(U.LOGIN) WHERE "
                    + "UPPER(U.LOGIN)=UPPER(?)");
            findResID.setString(1, login.toUpperCase());

            ResultSet rs = findResID.executeQuery();

            while (rs.next()) {
                ID = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (findResID != null) {
                try {
                    findResID.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ID;
    }

    public static void addNewDish(String name, String description, float price,
            String type, boolean vegetarian, boolean spicy, boolean fish,
            boolean garlic, String login) {

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement insertDish = null;
        int resID = getRestaurantID(login);

        try {
            insertDish = conn.prepareStatement(
                    "INSERT INTO DISHES (NAME, DESCRIPTION, PRICE, TYPE, VEGETARIAN, "
                    + "SPICY, FISH, GARLIC, RESTAURANT_ID) VALUES (?,?,?,?,?,?,?,?,?)");
            insertDish.setString(1, name);
            insertDish.setString(2, description);
            insertDish.setFloat(3, price);
            insertDish.setString(4, type);
            if (vegetarian) {
                insertDish.setInt(5, 1);
            } else {
                insertDish.setInt(5, 0);
            }

            if (spicy) {
                insertDish.setInt(6, 1);
            } else {
                insertDish.setInt(6, 0);
            }

            if (fish) {
                insertDish.setInt(7, 1);
            } else {
                insertDish.setInt(7, 0);
            }

            if (garlic) {
                insertDish.setInt(8, 1);
            } else {
                insertDish.setInt(8, 0);
            }

            insertDish.setInt(9, resID);
            insertDish.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (insertDish != null) {
                try {
                    insertDish.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void deleteDish(int dishID) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement deleteDish = null;

        try {
            deleteDish = conn.prepareStatement(
                    "DELETE FROM DISHES WHERE ID=?");

            deleteDish.setInt(1, dishID);
            deleteDish.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (deleteDish != null) {
                try {
                    deleteDish.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static String getRestaurantName(int resID) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement getResName = null;
        String resName = null;

        try {
            getResName = conn.prepareStatement(
                    "SELECT NAME FROM RESTAURANTS WHERE ID=?");

            getResName.setInt(1, resID);
            ResultSet rs = getResName.executeQuery();

            while (rs.next()) {
                resName = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getResName != null) {
                try {
                    getResName.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resName;
    }

    public static ArrayList<String> getRestaurantData(int resID) {
        ArrayList<String> restaurantDataList = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement restaurantInfo = null;

        try {
            restaurantInfo = conn.prepareStatement(
                    "SELECT NAME, CUISINE, CITY, STREET, HOUSE_NUMBER, FLAT_NUMBER, "
                    + "DELIVERY_COST, MINIMUM_COST FROM RESTAURANTS "
                    + "WHERE ID=?");
            restaurantInfo.setInt(1, resID);

            ResultSet rs = restaurantInfo.executeQuery();

            while (rs.next()) {
                restaurantDataList.add(rs.getString(1));
                restaurantDataList.add(rs.getString(2));
                restaurantDataList.add(rs.getString(3));
                restaurantDataList.add(rs.getString(4));
                restaurantDataList.add(rs.getString(5));
                restaurantDataList.add(rs.getString(6));
                restaurantDataList.add(rs.getString(7));
                restaurantDataList.add(rs.getString(8));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (restaurantInfo != null) {
                try {
                    restaurantInfo.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return restaurantDataList;
    }

    public static int editRestaurant(String name, String cuisine, String city, String street,
            String houseNumber, String flatNumber, int deliveryCost, int minimumCost, int resID) {
        int returnValue = -2;

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement streetExists = null;
        PreparedStatement editRestaurant = null;

        try {
            streetExists = conn.prepareStatement("SELECT 1 FROM DISTRICTS WHERE "
                    + "UPPER(STREET)=UPPER(?) AND UPPER(CITY)=UPPER(?)");
            streetExists.setString(1, street.toUpperCase());
            streetExists.setString(2, city.toUpperCase());

            editRestaurant = conn.prepareStatement(
                    "UPDATE RESTAURANTS SET NAME=?, CUISINE=?, CITY=?, STREET=?, HOUSE_NUMBER=?, "
                    + "FLAT_NUMBER=?, DELIVERY_COST=?, MINIMUM_COST=? WHERE ID=?");
            editRestaurant.setString(1, name);
            editRestaurant.setString(2, cuisine);
            editRestaurant.setString(3, city);
            editRestaurant.setString(4, street);
            editRestaurant.setString(5, houseNumber);
            editRestaurant.setString(6, flatNumber);
            editRestaurant.setInt(7, deliveryCost);
            editRestaurant.setInt(8, minimumCost);
            editRestaurant.setInt(9, resID);

            ResultSet rs = streetExists.executeQuery();

            if (rs.next()) {
                editRestaurant.executeUpdate();
                returnValue = 1;
            } else {
                returnValue = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (streetExists != null) {
                try {
                    streetExists.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (editRestaurant != null) {
                try {
                    editRestaurant.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return returnValue;
    }

    public static ArrayList<String> getDishData(int dishID) {
        ArrayList<String> dishDataList = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement dishInfo = null;

        try {
            dishInfo = conn.prepareStatement(
                    "SELECT NAME, DESCRIPTION, TYPE, PRICE, VEGETARIAN, SPICY, "
                    + "FISH, GARLIC FROM DISHES "
                    + "WHERE ID=?");
            dishInfo.setInt(1, dishID);

            ResultSet rs = dishInfo.executeQuery();

            while (rs.next()) {
                dishDataList.add(rs.getString(1));
                dishDataList.add(rs.getString(2));
                dishDataList.add(rs.getString(3));
                dishDataList.add(rs.getString(4));
                dishDataList.add(rs.getString(5));
                dishDataList.add(rs.getString(6));
                dishDataList.add(rs.getString(7));
                dishDataList.add(rs.getString(8));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (dishInfo != null) {
                try {
                    dishInfo.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dishDataList;
    }

    public static void editDish(int dishID, String name, String description, float price,
            String type, boolean vegetarian, boolean spicy, boolean fish,
            boolean garlic) {

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement editDish = null;

        try {
            editDish = conn.prepareStatement(
                    "UPDATE DISHES SET NAME=?, DESCRIPTION=?, PRICE=?, TYPE=?, VEGETARIAN=?, "
                    + "SPICY=?, FISH=?, GARLIC=? WHERE ID=?");
            editDish.setString(1, name);
            editDish.setString(2, description);
            editDish.setFloat(3, price);
            editDish.setString(4, type);

            if (vegetarian) {
                editDish.setInt(5, 1);
            } else {
                editDish.setInt(5, 0);
            }

            if (spicy) {
                editDish.setInt(6, 1);
            } else {
                editDish.setInt(6, 0);
            }

            if (fish) {
                editDish.setInt(7, 1);
            } else {
                editDish.setInt(7, 0);
            }

            if (garlic) {
                editDish.setInt(8, 1);
            } else {
                editDish.setInt(8, 0);
            }

            editDish.setInt(9, dishID);
            editDish.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (editDish != null) {
                try {
                    editDish.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void orderFood(int resID, String login, ArrayList<OrderEntry> orderedDishList, int deliveryCost) {
        float totalPrice = deliveryCost;
        for (OrderEntry entry : orderedDishList) {
            totalPrice += entry.getPrice() * entry.getAmount();
        }

        if (conn == null) {
            connectToDatabase();
        }

        CallableStatement newOrder = null;
        PreparedStatement orderDetails = null;

        try {
            newOrder = conn.prepareCall(
                    "BEGIN INSERT INTO ORDERS (RESTAURANT_ID, PRICE, CLIENT, STATUS, STARS) VALUES (?,?,?,?,?) "
                    + "RETURNING ID INTO ?; END;");
            newOrder.setInt(1, resID);
            newOrder.setFloat(2, totalPrice);
            newOrder.setString(3, login);
            newOrder.setString(4, "WAIT_TO_ACCEPT");
            newOrder.setInt(5, 0);
            newOrder.registerOutParameter(6, OracleTypes.NUMBER);
            newOrder.executeUpdate();

            int orderID = newOrder.getInt(6);

            for (OrderEntry orderEntry : orderedDishList) {
                orderDetails = conn.prepareStatement(
                        "INSERT INTO ORDER_DETAIlS (ORDER_ID, DISH_NAME, DISH_PRICE, AMOUNT) "
                        + "VALUES (?,?,?,?)");
                orderDetails.setInt(1, orderID);
                orderDetails.setString(2, orderEntry.getName());
                orderDetails.setFloat(3, orderEntry.getPrice());
                orderDetails.setInt(4, orderEntry.getAmount());
                orderDetails.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (newOrder != null) {
                try {
                    newOrder.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (orderDetails != null) {
                try {
                    orderDetails.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<String> getClientData(String login) {
        ArrayList<String> clientDataList = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement clientInfo = null;

        try {
            clientInfo = conn.prepareStatement(
                    "SELECT CITY, STREET, HOUSE_NUMBER, FLAT_NUMBER FROM USERS "
                    + "WHERE UPPER(LOGIN)=UPPER(?)");
            clientInfo.setString(1, login.toUpperCase());

            ResultSet rs = clientInfo.executeQuery();

            while (rs.next()) {
                clientDataList.add(rs.getString(1));
                clientDataList.add(rs.getString(2));
                clientDataList.add(rs.getString(3));
                clientDataList.add(rs.getString(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (clientInfo != null) {
                try {
                    clientInfo.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return clientDataList;
    }

    public static int editClientData(String login, String city, String street, String houseNumber, String flatNumber) {

        if (conn == null) {
            connectToDatabase();
        }

        int returnValue = -2;
        PreparedStatement streetExists = null;
        PreparedStatement editData = null;

        try {
            streetExists = conn.prepareStatement("SELECT 1 FROM DISTRICTS WHERE "
                    + "UPPER(STREET)=? AND UPPER(CITY)=?");
            streetExists.setString(1, street.toUpperCase());
            streetExists.setString(2, city.toUpperCase());

            editData = conn.prepareStatement(
                    "UPDATE USERS SET CITY=?, STREET=?, HOUSE_NUMBER=?, FLAT_NUMBER=? "
                    + "WHERE UPPER(LOGIN)=UPPER(?)");
            editData.setString(1, city);
            editData.setString(2, street);
            editData.setString(3, houseNumber);
            editData.setString(4, flatNumber);
            editData.setString(5, login.toUpperCase());

            ResultSet rs = streetExists.executeQuery();

            if (rs.next()) {
                editData.executeUpdate();
                returnValue = 1;   // USER EDITED
            } else {
                returnValue = -1;   // STREET DOES NOT EXIST
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (editData != null) {
                try {
                    editData.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (streetExists != null) {
                try {
                    streetExists.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return returnValue;
    }

    public static void changeClientPassword(String login, String newPassword) {

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement changePass = null;

        try {

            changePass = conn.prepareStatement(
                    "UPDATE USERS SET PASSWORD=? WHERE UPPER(LOGIN)=UPPER(?)");
            changePass.setString(1, newPassword);
            changePass.setString(2, login);

            changePass.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (changePass != null) {
                try {
                    changePass.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<YourOrder> getOrderList(String login) {
        ArrayList<YourOrder> orderList = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement orderInfo = null;

        try {
            orderInfo = conn.prepareStatement(
                    "SELECT O.ID, R.NAME, O.PRICE, O.STATUS, O.STARS FROM "
                    + "ORDERS O JOIN RESTAURANTS R ON (O.RESTAURANT_ID=R.ID) "
                    + "WHERE O.CLIENT=? ORDER BY O.ID DESC");
            orderInfo.setString(1, login);

            ResultSet rs = orderInfo.executeQuery();

            while (rs.next()) {
                orderList.add(new YourOrder(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (orderInfo != null) {
                try {
                    orderInfo.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderList;
    }

    public static void rateOrder(int orderID, int stars) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement getRestaurantID = null;
        PreparedStatement rateOrder = null;
        PreparedStatement addRate = null;

        try {
            rateOrder = conn.prepareStatement(
                    "UPDATE ORDERS SET STARS=? WHERE ID=?");
            rateOrder.setInt(1, stars);
            rateOrder.setInt(2, orderID);

            getRestaurantID = conn.prepareStatement(
                    "SELECT RESTAURANT_ID FROM ORDERS WHERE ID=?");
            getRestaurantID.setInt(1, orderID);

            addRate = conn.prepareStatement(
                    "INSERT INTO RATES (RESTAURANT_ID, STARS) VALUES (?,?)");

            rateOrder.executeUpdate();
            ResultSet rs = getRestaurantID.executeQuery();

            while (rs.next()) {
                addRate.setInt(1, rs.getInt(1));
                addRate.setInt(2, stars);
            }

            addRate.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rateOrder != null) {
                try {
                    rateOrder.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<YourOrder> getOrderList(int resID) {
        ArrayList<YourOrder> orderList = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement orderInfo = null;

        try {
            orderInfo = conn.prepareStatement(
                    "SELECT O.ID, O.CLIENT, O.PRICE, O.STATUS, O.STARS FROM "
                    + "ORDERS O "
                    + "WHERE O.RESTAURANT_ID=? ORDER BY O.ID DESC");
            orderInfo.setInt(1, resID);

            ResultSet rs = orderInfo.executeQuery();

            while (rs.next()) {
                orderList.add(new YourOrder(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (orderInfo != null) {
                try {
                    orderInfo.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderList;
    }

    public static void setOrderStatus(int orderID, String status) {
        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement setStatus = null;

        try {
            setStatus = conn.prepareStatement(
                    "UPDATE ORDERS SET STATUS=? WHERE ID=?");
            setStatus.setString(1, status);
            setStatus.setInt(2, orderID);

            setStatus.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (setStatus != null) {
                try {
                    setStatus.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<OrderDetails> getOrderDetails(int orderID) {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();

        if (conn == null) {
            connectToDatabase();
        }

        PreparedStatement getOrderDetails = null;

        try {
            getOrderDetails = conn.prepareStatement(
            "SELECT DISH_NAME, DISH_PRICE, AMOUNT FROM ORDER_DETAILS "
                    + "WHERE ORDER_ID=?");
            getOrderDetails.setInt(1, orderID);

            ResultSet rs = getOrderDetails.executeQuery();

            while (rs.next()) {
                orderDetails.add(new OrderDetails(orderID, rs.getString(1), rs.getFloat(2), rs.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (getOrderDetails != null) {
                try {
                    getOrderDetails.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderDetails;
    }
}
