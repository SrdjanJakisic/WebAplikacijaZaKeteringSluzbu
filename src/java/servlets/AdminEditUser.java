package servlets;

import beans.User;
import beans.UserRole;
import data.UserData;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

public class AdminEditUser extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        User user = new User();
        String userId = request.getParameter("userId");

        ArrayList<UserRole> userRoles = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

            UserData ud = new UserData();
            user = ud.getUserById(conn, userId);
            userRoles = ud.getAllUserRoles(conn);

            request.setAttribute("userRoles", userRoles);
            request.setAttribute("user", user);
            request.getRequestDispatcher("adminEditUser.jsp").forward(request, response);

            conn.close();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", "Грешка при конекцији! " + ex.getLocalizedMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        User user = new User();
        ArrayList<UserRole> userRoles = new ArrayList<>();

        String username = request.getParameter("username"),
                name = request.getParameter("name"),
                surname = request.getParameter("surname"),
                phone = request.getParameter("phone"),
                email = request.getParameter("email"),
                password = request.getParameter("password"),
                address = request.getParameter("address"),
                city = request.getParameter("city"),
                userId = request.getParameter("userId"),
                userRoleId = request.getParameter("userRoleId");

        if (username != null && username.length() > 0 && name != null && name.length() > 0 && surname != null && surname.length() > 0
                && phone != null && phone.length() > 0 && email != null && email.length() > 0 && password != null && password.length() > 0
                && address != null && address.length() > 0 && city != null && city.length() > 0 && userId != null && userId.length() > 0 && userRoleId != null && userRoleId.length() > 0)
        {
            Statement stmt = null;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                String query1 = "UPDATE user SET username = '" + username + "', name = '" + name + "', surname = '" + surname + "', phone = '" + phone + "', email = '" + email + "', password = '" + password + "', address = '" + address + "', city = '" + city + "', userRoleId = '" + userRoleId + "' WHERE id = '" + userId + "' ";
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                stmt = conn.createStatement();
                stmt.executeUpdate(query1);

                UserData ud = new UserData();
                user = ud.getUserById(conn, userId);
                userRoles = ud.getAllUserRoles(conn);

                request.setAttribute("userRoles", userRoles);
                request.setAttribute("user", user);
                request.setAttribute("msgSuccess", "Успешно сте изменили податке!");
                request.getRequestDispatcher("adminEditUser.jsp").forward(request, response);

                stmt.close();
                conn.close();
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Није успела измена података!" + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                UserData ud = new UserData();
                user = ud.getUserById(conn, userId);

                request.setAttribute("user", user);
                request.setAttribute("msg", "Морате попунити сва поља!");
                request.getRequestDispatcher("adminEditUser.jsp").forward(request, response);

                conn.close();
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији! " + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
