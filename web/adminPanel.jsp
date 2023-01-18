<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Админ панел</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <%
            ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
            ArrayList<User> managers = (ArrayList<User>)request.getAttribute("managers");
            ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1><i class="fa-solid fa-screwdriver-wrench"></i> Админ панел</h1>
        <div class="container my-3">
            <h2>Корисници <i class="fa-solid fa-users"></i></h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Корисничко име</th>
                        <th>Име</th>
                        <th>Презиме</th>
                        <th>е-Пошта</th>
                        <th>Телефон</th>
                        <th>Адреса</th>
                        <th>Град</th>
                        <th>Поени</th>
                        <th>Измени</th>
                        <th>Обриши</th>
                    </tr>
                </thead>
                <% for(User u:users) { %>
                <tbody>
                    <tr>
                        <td><%= u.getId() %></td>
                        <td><%= u.getUsername() %></td>
                        <td><%= u.getName() %></td>
                        <td><%= u.getSurname() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getPhone() %></td>
                        <td><%= u.getAddress() %></td>
                        <td><%= u.getCity() %></td>
                        <td><%= u.getPoints() %></td>
                        <td><a href="AdminEditUser?&userId=<%= u.getId() %>" class="btn btn-sm btn-danger"><i class="fa-solid fa-pencil"></i> Измени</a></td>
                        <td><a href="AdminDeleteUser?&userId=<%= u.getId() %>" class="btn btn-sm btn-danger" onclick='return confirmSubmit()'><i class="fa-solid fa-trash-can"></i> Обриши</a></td>
                    </tr>
                </tbody>
                <% } %>
            </table>
        </div>

        <div class="container my-3">
            <h2>Менаџери <i class="fa-solid fa-users"></i></h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Корисничко име</th>
                        <th>Име</th>
                        <th>Презиме</th>
                        <th>е-Пошта</th>
                        <th>Телефон</th>
                        <th>Адреса</th>
                        <th>Град</th>
                        <th>Измени</th>
                        <th>Обриши</th>
                    </tr>
                </thead>
                <% for(User u:managers) { %>
                <tbody>
                    <tr>
                        <td><%= u.getId() %></td>
                        <td><%= u.getUsername() %></td>
                        <td><%= u.getName() %></td>
                        <td><%= u.getSurname() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getPhone() %></td>
                        <td><%= u.getAddress() %></td>
                        <td><%= u.getCity() %></td>
                        <td><a href="AdminEditUser?&userId=<%= u.getId() %>" class="btn btn-sm btn-danger"><i class="fa-solid fa-pencil"></i> Измени</a></td>
                        <td><a href="AdminDeleteUser?&userId=<%= u.getId() %>" class="btn btn-sm btn-danger" onclick='return confirmSubmit()'><i class="fa-solid fa-trash-can" ></i> Обриши</a></td>
                    </tr>
                </tbody>
                <% } %>
            </table>
            <div class="btn-group">
                <a href="AddProduct" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Додај производ</a>
                <a href="AddStore" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Додај продавницу</a>
                <a href="AllOrders" class="btn btn-primary"><i class="fa-solid fa-list"></i> Све проуџбине</a>
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
