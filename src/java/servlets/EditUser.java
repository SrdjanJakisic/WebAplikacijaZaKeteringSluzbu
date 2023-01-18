package servlets;


import beans.*;
import data.UserData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class EditUser extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getId();
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
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                
                UserData ud = new UserData();
                ud.updateUserById(conn, username, name, surname, phone, email, password, address, city, userId); 
                
                user = ud.getUserById(conn, Integer.toString(user.getId()));
                request.getSession().setAttribute("user", user);
                
                conn.close();
                
                request.setAttribute("msgSuccess", "Успешно сте изменили податке!");
                request.getRequestDispatcher("editUser.jsp").forward(request, response);               
            }
            catch(SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Није успела измена података!" + ex.getLocalizedMessage());
                request.getRequestDispatcher("editUser.jsp").forward(request, response);
            }
        }
        else
        {
            request.setAttribute("msg", "Морате попунити сва поља!");
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
