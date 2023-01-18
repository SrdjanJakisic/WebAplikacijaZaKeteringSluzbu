package servlets;

import beans.*;
import data.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

public class CartProducts extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
        User user = (User) session.getAttribute("user");
        ArrayList<Cart> cartProds = new ArrayList<Cart>();
        double total_price = 0;
        

        if (cartList != null)
        {
            try
            {
                if (cartList.size() > 0)
                {
                    for (Cart item : cartList)
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                        String query1 = "SELECT * FROM products JOIN producttype ON typeId = producttype.Id  WHERE productId = ?";
                        PreparedStatement pstm;
                        pstm = conn.prepareStatement(query1);
                        pstm.setInt(1, item.getProductId());
                        ResultSet rs;
                        rs = pstm.executeQuery();
                        int storeId = 0;

                        while (rs.next())
                        {
                            Cart cart = new Cart();
                            cart.setProductId(rs.getInt("productId"));
                            cart.setProductName(rs.getString("productName"));
                            cart.setProductDescription(rs.getString("productDescription"));
                            cart.setProductPrice(rs.getDouble("productPrice") * item.getQuantity());
                            cart.setProductWeight(rs.getInt("productWeight"));
                            cart.setTypeId(rs.getInt("typeId"));
                            cart.setTypeName(rs.getString("producttype.name"));
                            cart.setQuantity(item.getQuantity());
                            cartProds.add(cart);
                            total_price += cart.getProductPrice();
                            storeId = rs.getInt("storeId");
                        }
                        session.setAttribute("storeId", storeId);
                        pstm.close();
                        rs.close();
                    }

                    session.setAttribute("cartProds", cartProds);
                    session.setAttribute("total_price", total_price);
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    
                    
                }
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else
        {
            request.setAttribute("msg", "Корпа је празна!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ArrayList<Cart> cartProds = new ArrayList<Cart>();
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
        String discount5 = request.getParameter("discount5");
        String discount10 = request.getParameter("discount10");

        double total_price = 0;

        if (discount5 != null || discount10 != null)
        {
            if (cartList != null)
            {
                try
                {
                    if (cartList.size() > 0)
                    {
                        for (Cart item : cartList)
                        {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                            String query1 = "SELECT * FROM products WHERE productId = ?";
                            PreparedStatement pstm;
                            pstm = conn.prepareStatement(query1);
                            pstm.setInt(1, item.getProductId());
                            ResultSet rs;
                            rs = pstm.executeQuery();

                            int storeId = 0;

                            while (rs.next())
                            {
                                Cart cart = new Cart();
                                cart.setProductId(rs.getInt("productId"));
                                cart.setProductName(rs.getString("productName"));
                                cart.setProductDescription(rs.getString("productDescription"));
                                cart.setProductPrice(rs.getDouble("productPrice") * item.getQuantity());
                                cart.setProductWeight(rs.getInt("productWeight"));
                                cart.setTypeId(rs.getInt("typeId"));
                                cart.setQuantity(item.getQuantity());
                                cartProds.add(cart);
                                total_price += cart.getProductPrice();
                                storeId = rs.getInt("storeId");
                            }
                            session.setAttribute("storeId", storeId);
                            pstm.close();
                            conn.close();
                        }

                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                        if (discount5 != null)
                        {
                            if (Integer.parseInt(discount5) == 5)
                            {
                                total_price = total_price * 0.9;

                                int points = user.getPoints() - 5;
                                UserData ud = new UserData();
                                ud.updateUserPoints(points, user, conn);
                                conn.close();
                            }
                        }
                        if (discount10 != null)

                        {
                            if (Integer.parseInt(discount10) == 10)
                            {
                                total_price = total_price * 0.8;

                                int points = user.getPoints() - 10;
                                UserData ud = new UserData();
                                ud.updateUserPoints(points, user, conn);
                                conn.close();
                            }
                        }
                        session.setAttribute("cartProds", cartProds);
                        session.setAttribute("total_price", total_price);
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                    }
                }
                catch (SQLException | ClassNotFoundException ex)
                {
                    request.setAttribute("msg", ex.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            else
            {
                request.setAttribute("msg", "Корпа је празна!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
