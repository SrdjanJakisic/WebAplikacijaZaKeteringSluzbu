package servlets;

import beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class CartIncDec extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action"),
                productId = request.getParameter("productId");
        int idInt = Integer.parseInt(productId);
        
        ArrayList<Cart> cartList = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
        
        if(action != null && idInt >=1)
        {
            if(action.equals("inc"))
            {
                for(Cart c:cartList)
                {
                    if(c.getProductId() == idInt)
                    {
                        int quantity = c.getQuantity();
                        quantity ++;
                        c.setQuantity(quantity);
                        response.sendRedirect("CartProducts");
                    }
                }
            }
            if(action.equals("dec"))
            {
                for(Cart c:cartList)
                {
                    if(c.getProductId() == idInt && c.getQuantity() > 1)
                    {
                        int quantity = c.getQuantity();
                        quantity --;
                        c.setQuantity(quantity);
                        break;                  
                    }
                }
                response.sendRedirect("CartProducts");
            }
            
            
        }
        else
        {
            response.sendRedirect("CartProducts");
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
