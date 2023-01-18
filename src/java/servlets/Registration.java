package servlets;

import data.UserData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Registration extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username").trim(),
                name = request.getParameter("name").trim(),
                surname = request.getParameter("surname").trim(),
                phone = request.getParameter("phone").trim(),
                email = request.getParameter("email").trim(),
                password = request.getParameter("password").trim(),
                address = request.getParameter("address").trim(),
                city = request.getParameter("city").trim();

        if(username != null && username.length() > 0 && name != null && name.length() > 0 && surname != null && surname.length() > 0  && 
                phone != null && phone.length() > 0 && email != null && email.length() > 0 && password != null && password.length() > 0 && 
                address != null && address.length() > 0 && city != null && city.length() > 0)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                String query1 = "SELECT * FROM user WHERE username = '" + username + "'";
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query1);

                if (rs.next())
                {
                    request.setAttribute("msg", "Корисничко име је заузето!");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                } 
                else
                {
                    try
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        
                        UserData ud = new UserData();
                        ud.addUser(conn, username, name, surname, email, phone, password, address, city, city, username);
                        
                        request.setAttribute("msgSuccess", "Успешно сте се регистровали! Кликните да би сте се пријавили");
                        request.getRequestDispatcher("registration.jsp").forward(request, response);
                    } 
                    catch (SQLException sqle)
                    {
                        request.setAttribute("msg", "Грешка при конекцији: " + sqle.getMessage());
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
                conn.close();
                stmt.close();
            } 
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } 
        } 
        else
        {
            request.setAttribute("msg", "Морате унети сва поља!");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }

    }
}
