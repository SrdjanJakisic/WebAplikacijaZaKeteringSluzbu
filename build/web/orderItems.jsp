<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ставке поруџбине</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <%
            ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>)request.getAttribute("orderItems");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Ставке поруџбине</h1>
        <div class="container my-3">
            <div class="card-header my-3">Ставке поруџбине</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">ID ставке</th>
                        <th scope="col">ID поруџбине</th>
                        <th scope="col">ID производа</th>
                        <th scope="col">Назив производа</th>
                        <th scope="col">Количина</th>
                        <th scope="col">Цена</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(OrderItem o:orderItems) { %>
                    <tr>
                        <td><%= o.getOrderItemId() %></td>
                        <td><%= o.getOrderId() %></td>
                        <td><%= o.getProductId() %></td>
                        <td><%= o.getProductName() %></td>
                        <td><%= o.getQuantity() %></td>
                        <td><%= o.getPrice() %></td>
                    </tr>          
                </tbody>
                <% } %>
            </table>
        </div> 
    </body>
</html>