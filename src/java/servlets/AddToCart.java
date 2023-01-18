package servlets;

import beans.Cart;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AddToCart extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Cart> cartList = new ArrayList<>();

        String productId = request.getParameter("productId"),
                type = request.getParameter("typeId"),
                storeId = request.getParameter("storeId");
                
        int idInt = Integer.parseInt(productId),
                typeInt = Integer.parseInt(type),
                storeIdInt = Integer.parseInt(storeId);

        Cart cart = new Cart();
        cart.setProductId(idInt);
        cart.setQuantity(1);

        HttpSession session = request.getSession();
        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

        if (cart_list == null)
        {
            cartList.add(cart);
            session.setAttribute("cart-list", cartList);
            request.setAttribute("msgSuccess", "Производ је додат у корпу!");
            if (typeInt == 1)
            {                
                request.getRequestDispatcher("Show?typeId=1&storeId = '" + storeIdInt + "' ").forward(request, response);
            } 
            else if (typeInt == 2)
            {
                request.getRequestDispatcher("Show?typeId=2&storeId = '" + storeIdInt + "' ").forward(request, response);
            }
        } 
        else
        {
            cartList = cart_list;
            boolean exist = false;

            for (Cart c : cartList)
            {
                if (c.getProductId() == idInt)
                {
                    exist = true;
                    request.setAttribute("msg", "Производ је већ у корпи!");
                    if (typeInt == 1)
                    {   
                        request.getRequestDispatcher("Show?typeId=1&storeId = '" + storeIdInt + "'").forward(request, response);
                    } 
                    else if (typeInt == 2)
                    {
                        request.getRequestDispatcher("Show?typeId=2&storeId = '" + storeIdInt + "'").forward(request, response);
                    }

                }
            }

            if (!exist)
            {
                cartList.add(cart);
                request.setAttribute("msgSuccess", "Производ је додат у корпу!");
                if (typeInt == 1)
                {           
                    request.getRequestDispatcher("Show?typeId=1&storeId=" + storeIdInt).forward(request, response);
                } 
                else if (typeInt == 2)
                {
                    request.getRequestDispatcher("Show?typeId=2&storeId=" + storeIdInt).forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }

}
