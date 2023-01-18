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
public class EditStore extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String storeId = request.getParameter("storeId");
        ArrayList<User> managers = new ArrayList<>();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

            StoreData ad = new StoreData();
            Store store = ad.getStoreById(conn, storeId);

            UserData ud = new UserData();
            managers = ud.getAllManagers(conn);

            request.setAttribute("store", store);
            request.setAttribute("managers", managers);
            request.getRequestDispatcher("editStore.jsp").forward(request, response);
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
                street = request.getParameter("street"),
                city = request.getParameter("city"),
                manager = request.getParameter("manager"),
                storeId = request.getParameter("storeId");
        
        UUID uid = UUID.randomUUID();
        Part filePart = request.getPart("image");
        String image = uid + filePart.getSubmittedFileName();
        for (Part part : request.getParts())
        {
            part.write("C:\\Users\\Mirjana\\Desktop\\SrdjanJakisic26115_WebAplikacijaZaKeteringSluzbu\\web\\images\\agencyLogo\\" + image);
        }

        if (storeName != null && storeName.length() > 0
                && storePhone != null && storePhone.length() > 0
                && street != null && street.length() > 0
                && city != null && city.length() > 0
                && manager != null && manager.length() > 0
                && image != null && image.length() > 0)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                StoreData ad = new StoreData();
                ad.updateStore(conn, storeName, storePhone, street, city, manager, image, storeId);
                Store store = ad.getStoreById(conn, storeId);

                UserData ud = new UserData();
                managers = ud.getAllManagers(conn);

                request.setAttribute("store", store);
                request.setAttribute("managers", managers);
                request.setAttribute("msgSuccess", "Успешно унета продавница!");
                request.getRequestDispatcher("editStore.jsp").forward(request, response);
                
                conn.close();
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији! " + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                StoreData ad = new StoreData();
                Store store = ad.getStoreById(conn, storeId);

                UserData ud = new UserData();
                managers = ud.getAllManagers(conn);

                request.setAttribute("store", store);
                request.setAttribute("managers", managers);
                request.setAttribute("msg", "Морате попунити сва поља");
                request.getRequestDispatcher("editStore.jsp").forward(request, response);
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка: " + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
