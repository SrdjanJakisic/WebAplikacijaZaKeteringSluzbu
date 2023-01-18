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
import java.util.*;

@MultipartConfig
public class EditProduct extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("prodId");
        Product product = new Product();
        ArrayList<Store> stores = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

            ProductData pd = new ProductData();
            product = pd.getProductById(conn, productId);

            StoreData ad = new StoreData();
            stores = ad.getAllStores(conn);

            request.setAttribute("stores", stores);
            request.setAttribute("product", product);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
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

        ArrayList<Store> stores = new ArrayList<>();

        String productId = request.getParameter("productId"),
                productName = request.getParameter("productName"),
                price = request.getParameter("price"),
                weight = request.getParameter("weight"),
                description = request.getParameter("description"),
                storeId = request.getParameter("storeId"),
                productTypeId = request.getParameter("type");

        UUID uid = UUID.randomUUID();
        Part filePart = request.getPart("image");
        String image = uid + filePart.getSubmittedFileName();
        for (Part part : request.getParts())
        {
            part.write("C:\\Users\\Mirjana\\Desktop\\SrdjanJakisic26115_WebAplikacijaZaKeteringSluzbu\\web\\images\\products\\" + image);
        }

        if (productName != null && productName.length() > 0
                && price != null && price.length() > 0
                && weight != null && weight.length() > 0
                && description != null && description.length() > 0
                && productTypeId != null && productTypeId.length() > 0
                && image != null && image.length() > 0)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cateringdb", "root", "");

                ProductData pd = new ProductData();
                pd.updateProduct(conn, productId, productName, price, weight, description, image, productTypeId, storeId);

                StoreData ad = new StoreData();
                stores = ad.getAllStores(conn);

                Product product = pd.getProductById(conn, productId);
                request.setAttribute("stores", stores);
                request.setAttribute("product", product);
                request.setAttribute("msgSuccess", "Успешно унет производ!");
                request.getRequestDispatcher("editProduct.jsp").forward(request, response);

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
                ProductData pd = new ProductData();
                Product product = pd.getProductById(conn, productId);
                request.setAttribute("product", product);
                request.setAttribute("msg", "Морате да попуните сва поља!");
                request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                request.setAttribute("msg", "Грешка при конекцији! " + ex.getLocalizedMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
