package servlets;

import java.sql.*;
import beans.*;
import java.util.*;
import data.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Comments extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Comment> comments = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
            
            CommentData cd = new CommentData();
            comments = cd.getAllComments(conn);
            
            conn.close();

            request.setAttribute("comments", comments);
            request.getRequestDispatcher("comments.jsp").forward(request, response);
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String commentContent = request.getParameter("commentContent");
        User user = (User) session.getAttribute("user");
        ArrayList<Comment> comments = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
            
            CommentData cd = new CommentData();
            cd.addComment(conn, commentContent, Integer.toString(user.getId()));
            comments = cd.getAllComments(conn);
            request.setAttribute("comments", comments);
            
            conn.close();
            request.getRequestDispatcher("comments.jsp").forward(request, response);
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
