package servlets;

import data.*;
import beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class CartCheckOut extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        double total_price = (double) session.getAttribute("total_price");
        int storeId = (int) session.getAttribute("storeId");
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
        ArrayList<Cart> cartProds = (ArrayList<Cart>) request.getSession().getAttribute("cartProds");
        User user = (User) session.getAttribute("user");

        PrintWriter out = response.getWriter();
        int counter = 0;

        if (cart_list != null)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                String query1 = "INSERT INTO orders (email, userId, userName, userSurname, address, phone, storeId, totalPrice) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                PreparedStatement pstm;

                pstm = conn.prepareStatement(query1);
                pstm.setString(1, user.getEmail());
                pstm.setInt(2, user.getId());
                pstm.setString(3, user.getName());
                pstm.setString(4, user.getSurname());
                pstm.setString(5, user.getAddress());
                pstm.setInt(6, user.getPhone());
                pstm.setInt(7, storeId);
                pstm.setDouble(8, total_price);

                pstm.executeUpdate();

                int quantity = 0;

                for (Cart c : cart_list)
                {
                    for (Product p : cartProds)
                    {
                        if (c.getProductId() == p.getProductId())
                        {
                            String lastEntry = "SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(lastEntry);
                            int orderId = 0;
                            while (rs.next())
                            {
                                orderId = rs.getInt("orderId");
                            }

                            String query2 = "INSERT INTO orderItem (quantity, price, productId, orderId) VALUES(?, ?, ?, ?)";
                            pstm = conn.prepareStatement(query2);

                            pstm.setInt(1, c.getQuantity());
                            pstm.setDouble(2, p.getProductPrice());
                            pstm.setInt(3, c.getProductId());
                            pstm.setInt(4, orderId);
                            quantity += c.getQuantity();
                            pstm.executeUpdate();
                            rs.close();
                            stmt.close();
                        }
                    }
                }
                
                int points = user.getPoints();
                if (quantity > 2 && quantity < 6)
                {
                    points += 1;
                    UserData ud = new UserData();
                    ud.updateUserPoints(points, user, conn);
                }
                else if (quantity > 5)
                {
                    points += 2;
                    UserData ud = new UserData();
                    ud.updateUserPoints(points, user, conn);
                }

                session.removeAttribute("cart-list");
                session.removeAttribute("cartProds");
                session.removeAttribute("total_price");
                session.removeAttribute("storeId");        
                
                UserData ad = new UserData();
                user = ad.getUserById(conn, Integer.toString(user.getId()));
                request.getSession().setAttribute("user", user);
                
                pstm.close();
                conn.close();
                request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка: " + ex.getLocalizedMessage());
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
