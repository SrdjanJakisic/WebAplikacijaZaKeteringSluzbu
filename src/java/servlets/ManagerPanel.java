package servlets;

import beans.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author srdja
 */
public class ManagerPanel extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Store> stores = new ArrayList<>();
        User user = (User) session.getAttribute("user");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
            String query = "SELECT * \n"
                    + "FROM products JOIN store ON products.storeId = store.storeId JOIN user ON store.userId = user.id\n"
                    + "WHERE user.userRoleId = 2 AND user.id = '" + user.getId() + "'\n"
                    + "ORDER BY productId";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int storeId = 0;
            while (rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductWeight(rs.getInt("productWeight"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setProductImage(rs.getString("productImage"));
                product.setTypeId(rs.getInt("typeId"));
                product.setStoreId(rs.getInt("storeId"));
                products.add(product);
                storeId = rs.getInt("storeId");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            request.setAttribute("storeId", storeId);
            request.setAttribute("products", products);
            request.getRequestDispatcher("managerPanel.jsp").forward(request, response);
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
