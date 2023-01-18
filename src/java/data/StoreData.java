package data;

import beans.Store;
import java.sql.*;
import java.util.ArrayList;

public class StoreData
{

    public Store getStoreById(Connection conn, String storeId) throws SQLException
    {
        String query = "SELECT * FROM store WHERE storeId = '" + storeId + "'";
        Store store = new Store();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            Store store1 = new Store();
            store1.setStoreId(rs.getInt("storeId"));
            store1.setStoreName(rs.getString("storeName"));
            store1.setStorePhone(rs.getInt("storePhone"));
            store1.setStoreStreet(rs.getString("storeStreet"));
            store1.setStoreCity(rs.getString("storeCity"));
            store1.setStoreImage(rs.getString("storeImage"));
            store1.setUserId(rs.getInt("userId"));
            store = store1;
        }

        return store;
    }

    public ArrayList<Store> getAllStores(Connection conn) throws SQLException
    {
        ArrayList<Store> stores = new ArrayList<>();
        String query = "SELECT * FROM store";
        Store store = new Store();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            stores.add(new Store(rs.getInt("storeId"), rs.getString("storeName"), rs.getInt("storePhone"), rs.getString("storeStreet"), rs.getString("storeCity"), rs.getString("storeImage"), rs.getInt("userId")));
        }

        return stores;
    }

    public void addStore(Connection conn, String storeName, String storePhone, String storeStreet, String storeCity, String manager, String storeImage) throws SQLException
    {
        String query = "INSERT INTO store(storeName, storePhone, storeStreet, storeCity, storeImage, userId) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(query);

        pstm.setString(1, storeName);
        pstm.setInt(2, Integer.parseInt(storePhone));
        pstm.setString(3, storeStreet);
        pstm.setString(4, storeCity);
        pstm.setString(5, storeImage);
        pstm.setInt(6, Integer.parseInt(manager));

        pstm.executeUpdate();
        pstm.close();
    }

    public void updateStore(Connection conn, String storeName, String storePhone, String storeStreet, String storeCity, String manager, String storeImage, String storeId) throws SQLException
    {
        String query = "UPDATE store SET storeName = ?, storePhone = ?, storeStreet = ?, storeCity = ?,  storeImage = ?, userId = ? WHERE storeId = ?";
        PreparedStatement pstm = conn.prepareStatement(query);

        pstm.setString(1, storeName);
        pstm.setInt(2, Integer.parseInt(storePhone));
        pstm.setString(3, storeStreet);
        pstm.setString(4, storeCity);
        pstm.setString(5, storeImage);
        pstm.setInt(6, Integer.parseInt(manager));
        pstm.setInt(7, Integer.parseInt(storeId));

        pstm.executeUpdate();

        pstm.close();
    }

    public void deleteStore(Connection conn, String storeId) throws SQLException
    {
        String query1 = "DELETE FROM store WHERE storeId = ?";
        PreparedStatement pstm = conn.prepareStatement(query1);
        pstm.setInt(1, Integer.parseInt(storeId));
        pstm.executeUpdate();
        pstm.close();
    }
}
