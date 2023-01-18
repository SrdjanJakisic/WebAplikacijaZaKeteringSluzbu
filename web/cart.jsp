<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Корпа</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <% 
            User user = (User)session.getAttribute("user");
            ArrayList<Cart> cartProds = (ArrayList<Cart>)session.getAttribute("cartProds");
            double total_price = (double)session.getAttribute("total_price"); 
        %>

        <jsp:include page="/navbar.jsp"/>

        <div class="container my-3">
            <div class="d-flex py-3"><h3>Укупна цена: <%= total_price %> динара </h3> <a class="mx-3 btn btn-primary" href="CartCheckOut"><i class="fa-solid fa-bag-shopping"></i> Купи</a></div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Назив производа</th>
                        <th scope="col">Категорија</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Количина</th>
                        <th scope="col">Обриши</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Cart p:cartProds) { %>
                    <tr>
                        <td><%= p.getProductName() %></td>
                        <td><%= p.getTypeName() %></td>
                        <td><%= p.getProductPrice() %></td>
                        <td>
                            <input type="hidden" name="id" value="<%= p.getProductId() %>"  class="form-input">
                            <div class="form-group d-flex justify-content-between">
                                <a class="btn btn-sm btn-incre" href="CartIncDec?action=inc&productId=<%= p.getProductId() %>"><i class="fa-solid fa-circle-plus"></i></a> 
                                <input type="text" name="quantity" class="form-control" value="<%= p.getQuantity() %>" readonly> 
                                <a class="btn btn-sm btn-decre" href="CartIncDec?action=dec&productId=<%= p.getProductId() %>"><i class="fa-solid fa-circle-minus"></i></a> 
                            </div>
                        </td>
                        <td><a href="RemoveFromCart?productId=<%= p.getProductId() %>" class="btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i> Обриши</a></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <form action="CartProducts" method="POST">
                <label>Имате <%= user.getPoints() %> поена </label>
                <% if(user.getPoints() >= 5 && user.getPoints() < 10) { %>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="5" id="flexCheckDefault" name="discount5">
                    <label class="form-check-label" for="flexCheckDefault">
                        Да ли желите да искористете бонус od 10%?
                    </label>
                </div>
                <button type="submit" class="btn btn-success">Примени</button>
                <% }  %>
                <% if(user.getPoints() >= 10) { %>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="10" id="flexCheckDefault" name="discount10">
                    <label class="form-check-label" for="flexCheckDefault">
                        Да ли желите да искористете бонус od 20%?
                    </label>
                </div>
                <button type="submit" class="btn btn-success">Примени</button>
                <% } %>
            </form>
        </div>  

    </body>
</html>
