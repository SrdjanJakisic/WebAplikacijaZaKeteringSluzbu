package servlets;


import beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;


public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email").trim(),
                password = request.getParameter("password").trim();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        if(email != null && email.length() > 0 && password != null && password.length() > 0)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                stmt = conn.createStatement();
                String query1 = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "' ";
                rs = stmt.executeQuery(query1);
                
                if(rs.next())
                {
                    HttpSession session = request.getSession();
                    
                    User user = new User
                    (
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
                    
                    session.setAttribute("user", user);
                    response.sendRedirect("ShowStores");
                }
                else
                {
                    request.setAttribute("msg", "Погрешна е-Пошта или шифра");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            catch(SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage() );
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else
        {
            request.setAttribute("msg", "Морате унети сва поља!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
