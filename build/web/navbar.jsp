<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<% 
    User user = (User)session.getAttribute("user"); 
    ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart-list");
%>


<nav class="navbar sticky-top navbar-expand-lg bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="ShowStores"><i class="fa-solid fa-house"></i> Почетна</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <a class="btn btn-outline-primary" href="Comments">Утисци</a>
            </ul>
            <%
                if(session.getAttribute("user") != null) {
            %>

            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <% if(user.getUserRoleId() == 1 && cart_list != null) { %>
                <% 
                    int count = 0;
                    for(Cart c:cart_list) 
                    { 
                        count++;
                    } 
                %>
                <a type="button" class="btn btn-outline-primary position-relative"  href="CartProducts">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                        <%= count %>
                    </span>
                </a>

                <% } %>
                <div class="dropdown">
                    <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa-solid fa-user"></i> Корисник <%= user.getUsername() %>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="editUser.jsp"><i class="fa-solid fa-pencil"></i>  Измени</a></li>
                            <% if(user.getUserRoleId() == 1) { %>
                        <li><a class="dropdown-item" href="UserOrders"><i class="fa-solid fa-list"></i>  Поруџбине</a></li>
                            <% } %>
                            <%
                                if(user.getUserRoleId() == 3) {
                            %>
                        <li><a class="dropdown-item" href="AdminPanel"><i class="fa-solid fa-screwdriver-wrench"></i>  Админ панел</a></li>
                            <% } %>
                            <%
                                    if(user.getUserRoleId() == 2) {
                            %>
                        <li><a class="dropdown-item" href="ManagerPanel"><i class="fa-solid fa-screwdriver-wrench"></i>  Менаџер панел</a></li>
                            <% } %>
                    </ul>

                </div>
                <a class="btn btn-outline-danger" href="Logout" type="submit">Одјавите се</a>
            </div>
            <% } %>
            <%
               if(session.getAttribute("user") == null) {
            %>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <a class="btn btn-outline-primary" href="login.jsp" type="submit">Пријавите се</a>
                <a class="btn btn-outline-primary" href="registration.jsp" type="submit">Регистрација</a> 
            </div>
            <%
                } else {
            %>

            <% } %>
        </div>
    </div>
</nav>
