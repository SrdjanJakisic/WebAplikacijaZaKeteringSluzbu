package data;

import beans.UserRole;
import beans.User;
import beans.User;
import beans.UserRole;
import java.sql.*;
import java.util.ArrayList;

public class UserData
{

    public ArrayList<User> getAllManagers(Connection conn) throws SQLException
    {
        ArrayList<User> managers = new ArrayList<>();
        String query1 = "SELECT * FROM user WHERE userRoleId = 2";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query1);

        while (rs.next())
        {
            User manager = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getInt("phone"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getInt("points"),
                    rs.getInt("userRoleId")
            );
            managers.add(manager);
        }

        rs.close();
        stmt.close();

        return managers;
    }

    public ArrayList<User> getAllUsers(Connection conn) throws SQLException
    {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE userRoleId = 1";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getInt("phone"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getInt("points"),
                    rs.getInt("userRoleId")
            );
            users.add(user);
        }
        rs.close();
        stmt.close();

        return users;
    }

    public User getUserById(Connection conn, String userId) throws SQLException
    {
        User user = new User();
        String query = "SELECT * FROM user WHERE id = '" + userId + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            User user1 = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getInt("phone"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getInt("points"),
                    rs.getInt("userRoleId")
            );
            user = user1;

        }
        rs.close();
        stmt.close();

        return user;
    }

    public void updateUserPoints(int points, User user, Connection conn) throws SQLException
    {
        String query2 = "UPDATE user SET points = '" + points + "' WHERE id = '" + user.getId() + "' ";
        Statement stmt;
        stmt = conn.createStatement();
        stmt.executeUpdate(query2);
        stmt.close();
    }

    public ArrayList<UserRole> getAllUserRoles(Connection conn) throws SQLException
    {
        ArrayList<UserRole> userRoles = new ArrayList<>();
        String query = "SELECT * FROM userrole";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            UserRole role = new UserRole();
            role.setUserRoleId(rs.getInt("userRoleId"));
            role.setRoleName(rs.getString("roleName"));

            userRoles.add(role);
        }
        
        stmt.close();
        rs.close();
        return userRoles;
    }

    public void deleteUser(Connection conn, String userId) throws SQLException
    {
        String query = "DELETE FROM user WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);

        pstm.setInt(1, Integer.parseInt(userId));
        pstm.executeUpdate();

        pstm.close();
    }

    public void updateUserById(Connection conn, String username, String name, String surname, String phone, String email, String password, String address, String city, int userId) throws SQLException
    {
        String query = "UPDATE user SET username = '" + username + "', name = '" + name + "', surname = '" + surname + "', phone = '" + phone + "', email = '" + email + "', password = '" + password + "', address = '" + address + "', city = '" + city + "' WHERE id = '" + userId + "' ";

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    public void addUser(Connection conn, String username, String name, String surname, String email, String phone, String password, String address, String city, String points, String userRoleId) throws SQLException
    {
        String query2 = "INSERT INTO user (username, name, surname, email, phone, password, address, city, points, userRoleId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query2);

        ps.setString(1, username);
        ps.setString(2, name);
        ps.setString(3, surname);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, password);
        ps.setString(7, address);
        ps.setString(8, city);
        ps.setInt(9, 0);
        ps.setInt(10, 1);

        ps.executeUpdate();
        ps.close();
    }
}
