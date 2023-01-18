package servlets;

import beans.*;
import data.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

@MultipartConfig
public class AddStore extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<User> managers = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

            UserData ud = new UserData();
            managers = ud.getAllManagers(conn);

            request.setAttribute("managers", managers);
            request.getRequestDispatcher("addStore.jsp").forward(request, response);

            conn.close();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            request.setAttribute("msg", "Грешка: " + ex.getLocalizedMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<User> managers = new ArrayList<>();

        String storeName = request.getParameter("storeName"),
                storePhone = request.getParameter("storePhone"),
                manager = request.getParameter("manager"),
                storeStreet = request.getParameter("storeStreet"),
                storeCity = request.getParameter("storeCity");

        UUID uid = UUID.randomUUID();
        Part filePart = request.getPart("image");
        String image = uid + filePart.getSubmittedFileName();
        for (Part part : request.getParts())
        {
            part.write("C:\\Users\\Mirjana\\Desktop\\SrdjanJakisic26115_WebAplikacijaZaKeteringSluzbu\\web\\images\\agencyLogo\\" + image);
        }

        if (storeName != null && storeName.length() > 0
                && storePhone != null && storePhone.length() > 0
                && manager != null && manager.length() > 0
                && image != null && image.length() > 0
                && storeStreet != null && storeStreet.length() > 0
                && storeCity != null && storeCity.length() > 0)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                StoreData ad = new StoreData();
                ad.addStore(conn, storeName, storePhone, storeStreet, storeCity, manager, image);

                UserData ud = new UserData();
                managers = ud.getAllManagers(conn);
                
                request.setAttribute("msgSuccess", "Успешно додата агенција!");
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("addStore.jsp").forward(request, response);

                conn.close();
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка: " + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");
                UserData ud = new UserData();
                managers = ud.getAllManagers(conn);
                request.setAttribute("managers", managers);
                request.setAttribute("msg", "Морате унети сва поља!");
                request.getRequestDispatcher("addStore.jsp").forward(request, response);
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка: " + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
