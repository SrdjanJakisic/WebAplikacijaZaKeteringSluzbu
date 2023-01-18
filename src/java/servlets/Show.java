package servlets;

import beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

public class Show extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String typeId = request.getParameter("typeId"),
                storeId = request.getParameter("storeId");
        int typeIdInt = Integer.parseInt(typeId);

        ArrayList<Product> products = new ArrayList<>();


        if (typeIdInt == 1)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                String query1 = "SELECT * FROM products WHERE typeId = 1 AND storeId = '" + storeId + "' ";
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query1);

                while (rs.next())
                {
                    products.add(new Product(rs.getInt("productId"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getInt("productWeight"), rs.getString("productDescription"), rs.getString("productImage"), rs.getInt("typeId"), rs.getInt("storeId")));
                }

                request.setAttribute("products", products);
                request.setAttribute("storeId", storeId);
                request.getRequestDispatcher("productDisplay.jsp").forward(request, response);

                conn.close();
                stmt.close();
                rs.close();
            } catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage());
                request.getRequestDispatcher("productDisplay.jsp").forward(request, response);
            }
        } 
        else if (typeIdInt == 2)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                String query1 = "SELECT * FROM products WHERE typeId = 2 AND storeId = '" + storeId + "' ";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query1);

                while (rs.next())
                {
                    products.add(new Product(rs.getInt("productId"), rs.getString("productName"), rs.getInt("productPrice"), rs.getInt("productWeight"), rs.getString("productDescription"), rs.getString("productImage"), rs.getInt("typeId"), rs.getInt("storeId")));
                }

                request.setAttribute("products", products);
                request.setAttribute("storeId", storeId);
                request.getRequestDispatcher("productDisplay.jsp").forward(request, response);

                conn.close();
                stmt.close();
                rs.close();
            } 
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
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
