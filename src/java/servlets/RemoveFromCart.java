package servlets;

import beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class RemoveFromCart extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String productId = request.getParameter("productId");
        int idInt = Integer.parseInt(productId);

        if (productId != null)
        {
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (cart_list != null)
            {
                for (Cart c : cart_list)
                {
                    if (c.getProductId() == idInt)
                    {
                        cart_list.remove(cart_list.indexOf(c));
                        break;
                    }
                }
                if(cart_list.size() == 0)
                {
                    request.setAttribute("msg", "Корпа је празна");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                else
                {
                    response.sendRedirect("CartProducts");
                }
            } 
        } 
        else
        {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
