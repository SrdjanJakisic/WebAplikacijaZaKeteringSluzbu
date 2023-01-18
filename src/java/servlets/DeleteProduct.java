package servlets;

import data.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class DeleteProduct extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("prodId"),
                typeId = request.getParameter("typeId"),
                storeId = request.getParameter("storeId");
        int typeIdInt = Integer.parseInt(typeId);
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
            
            ProductData pd = new ProductData();
            pd.deleteProduct(conn, productId);
                   
            if(typeIdInt == 1)
            {
                request.getRequestDispatcher("Show?id=1&storeId=" + storeId).forward(request, response);
            }
            else if(typeIdInt == 2)
            {
                request.getRequestDispatcher("Show?id=2&storeId=" + storeId).forward(request, response);
            }
            conn.close();
        } 
        catch(SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", "Грешка: " + ex.getLocalizedMessage());
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
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
        public String getServletInfo
        
            ()
    {
        return "Short description";
        }// </editor-fold>

    }
