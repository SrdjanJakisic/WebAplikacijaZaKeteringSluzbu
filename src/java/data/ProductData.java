package data;

import beans.*;
import java.util.*;
import java.sql.*;

public class ProductData
{

    public Product getProductById(Connection conn, String productId) throws SQLException
    {
        Product product = new Product();

        String query = "SELECT * FROM products WHERE productId = '" + productId + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            Product product1 = new Product(rs.getInt("productId"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getInt("productWeight"), rs.getString("productDescription"), rs.getString("productImage"), rs.getInt("typeId"), rs.getInt("storeId"));
            product = product1;
        }

        return product;
    }

    public void updateProduct(Connection conn, String productId, String productName, String price, String weight, String description, String image, String productTypeId, String storeId) throws SQLException
    {
        String query = "UPDATE products SET productName = ?, productPrice = ?, productWeight = ?, productDescription = ?, productImage = ?, typeId = ?, storeId = ? WHERE productId = ?";
        PreparedStatement pstm = conn.prepareStatement(query);

        pstm.setString(1, productName);
        pstm.setDouble(2, Double.parseDouble(price));
        pstm.setInt(3, Integer.parseInt(weight));
        pstm.setString(4, description);
        pstm.setString(5, image);
        pstm.setInt(6, Integer.parseInt(productTypeId));
        pstm.setInt(7, Integer.parseInt(storeId));
        pstm.setInt(8, Integer.parseInt(productId));

        pstm.executeUpdate();
        pstm.close();
    }

    public void addProduct(Connection conn, String productName, String price, String weight, String description, String image, String typeId, String storeId) throws SQLException
    {
        String query = "INSERT INTO products(productName, productPrice, productWeight, productDescription, productImage, typeId, storeId) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(query);

        pstm.setString(1, productName);
        pstm.setDouble(2, Double.parseDouble(price));
        pstm.setInt(3, Integer.parseInt(weight));
        pstm.setString(4, description);
        pstm.setString(5, image);
        pstm.setInt(6, Integer.parseInt(typeId));
        pstm.setInt(7, Integer.parseInt(storeId));

        pstm.executeUpdate();
        pstm.close();
    }

    public void deleteProduct(Connection conn, String productId) throws SQLException
    {
        String query = "DELETE FROM products WHERE productId = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setInt(1, Integer.parseInt(productId));
        pstm.executeUpdate();
        pstm.close();
    }
}
