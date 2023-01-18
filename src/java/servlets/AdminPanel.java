package servlets;

import beans.*;
import data.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

public class AdminPanel extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> managers = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
            
            UserData ud = new UserData();
            users = ud.getAllUsers(conn);
            managers = ud.getAllManagers(conn);

            request.setAttribute("users", users);
            request.setAttribute("managers", managers);
            request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
            
            conn.close();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }
}
