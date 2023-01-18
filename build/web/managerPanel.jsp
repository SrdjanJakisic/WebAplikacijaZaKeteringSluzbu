<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Менаџер панел</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("user");
            int storeId = (int)request.getAttribute("storeId");
            ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1><i class="fa-solid fa-screwdriver-wrench"></i> Менаџер панел</h1>
        <div class="container my-3">
            <h2>Производи <i class="fa-brands fa-product-hunt"></i></h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th>ID производа</th>
                        <th>Назив</th>
                        <th>Цена</th>
                        <th>Тежина</th>
                        <th>Измени</th>
                        <th>Обриши</th>
                    </tr>
                </thead>
                <% for(Product p:products) { %>
                <tbody>
                    <tr>
                        <td><%= p.getProductId() %></td>
                        <td><%= p.getProductName() %></td>
                        <td><%= p.getProductPrice() %></td>
                        <td><%= p.getProductWeight() %></td>
                        <td><a href="EditProduct?prodId=<%= p.getProductId() %>" class="btn btn-sm btn-danger"><i class="fa-solid fa-pencil"></i> Измени</a></td>
                        <td><a href="DeleteProduct?prodId=<%= p.getProductId() %>&typeId=<%= p.getTypeId() %>&storeId=<%= p.getStoreId() %>" class="btn btn-sm btn-danger" onclick='return confirmSubmit()'><i class="fa-solid fa-trash-can"></i> Обриши</a></td>
                    </tr>
                </tbody>
                <% } %>

            </table>
            <div class="btn-group">
                <a href="AddProduct" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Додај производ</a>
                <a href="AllOrders?storeId=<%= storeId %>" class="btn btn-primary"><i class="fa-solid fa-list"></i> Све проуџбине</a>
            </div>
        </div>

        <div class="msg">
            <c:if test="${msg != null}">
                <div class="alert alert-danger" role="alert">${msg}</div>
            </c:if>
            <c:if test="${msgSuccess != null}">
                <div class="alert alert-success" role="alert">${msgSuccess}</div>
            </c:if>
        </div>
    </div>
</body>
</html>
