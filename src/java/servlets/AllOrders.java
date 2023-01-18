package servlets;

import beans.Order;
import data.OrderData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class AllOrders extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<Order> orders = new ArrayList<Order>();

        String storeId = request.getParameter("storeId");

        PrintWriter out = response.getWriter();

        if (storeId == null)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                OrderData od = new OrderData();
                orders = od.getAllOrders(conn);

                request.setAttribute("orders", orders);
                request.getRequestDispatcher("allOrders.jsp").forward(request, response);
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији: " + ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                OrderData od = new OrderData();
                orders = od.getOrdersByAgency(conn, storeId);

                request.setAttribute("orders", orders);
                request.getRequestDispatcher("allOrders.jsp").forward(request, response);
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
