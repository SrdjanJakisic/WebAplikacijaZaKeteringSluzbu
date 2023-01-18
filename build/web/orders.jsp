<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поруџбине</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <%
            ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Све проуџбине</h1>
        <div class="container my-3">
            <div class="card-header my-3">Све наруџбине</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Датум</th>
                        <th scope="col">ID поруџбине</th>
                        <th scope="col">е-Пошта</th>
                        <th scope="col">Адреса</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Укупна цена</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Order o : orders) {%>
                    <tr>
                        <td><%= o.getDate() %></td>
                        <td><%= o.getOrderId()%></td>
                        <td><%= o.getEmail() %></td>
                        <td><%= o.getAddress()%></td>
                        <td><%= o.getPhone() %></td>
                        <td><%= o.getTotalPrice() %></td>
                    </tr>
                </tbody>
                <% }%>
            </table>
        </div> 
    </body>
</html>
