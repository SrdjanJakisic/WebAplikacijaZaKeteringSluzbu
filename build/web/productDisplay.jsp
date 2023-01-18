<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="beans.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Производи</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <jsp:include page="/navbar.jsp"/>
        <h1>Производи:</h1>
        <%
            ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
            User user = (User)session.getAttribute("user");
            String storeId = (String)request.getAttribute("storeId");
        %>
        <input type="hidden" name="storeId" value="<%= storeId %>">
        <div class="container" id="cont"  >
            <% for(Product pom: products) { %>
            <div class="card border-primary mb-3" id="cardBorder">
                <div class="card-header" id="cardHeader">
                    <img src="images/products/<%= pom.getProductImage() %>" id="image">
                </div>
                <div class="card-body">
                    <h3 id="prodTitle"><%= pom.getProductName() %></h3>
                    <p>Цена: <%= pom.getProductPrice() %> динара</p>
                    <p>Тежина: <%= pom.getProductWeight() %> грама</p>
                    <p>Опис: <%= pom.getProductDescription() %></p> 
                    <div class="divButtons">
                        <input type="hidden" name="productId" value="<%= pom.getProductId() %>">
                        <% if(session.getAttribute("user") == null) { %>
                        <h6><a href="login.jsp">Да би наручили пријавите се</a></h6>
                        <% } else if(user.getUserRoleId() == 3) { %>
                        <p><a href="EditProduct?prodId=<%= pom.getProductId()%>&typeId=<%= pom.getTypeId() %>" class="btn btn-primary" role="b?utton">Измени</a> <a href="DeleteProduct?prodId=<%= pom.getProductId()%>&typeId=<%= pom.getTypeId()%>&storeId=<%= storeId %>" class="btn btn-danger" role="button" onclick='return confirmSubmit()'>Обриши</a> </p>
                        <% } else if(user.getUserRoleId() == 1) { %>
                        <div class="d-grid gap-2 d-md-block">
                            <p><a href="AddToCart?productId=<%= pom.getProductId() %>&typeId=<%= pom.getTypeId() %>&storeId=<%= pom.getStoreId() %>" id="buyButton" class="btn btn-success" role="button"><i class="fa-solid fa-cart-plus"></i> Додај у корпу</a>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <div class="container" id="contMsg">
            <c:if test="${msg != null}">
                <div class="alert alert-danger" id="" role="alert">${msg}</div>
            </c:if>
            <c:if test="${msgSuccess != null}">
                <div class="alert alert-success" role="alert">
                    <a href="CartProducts" class="alert-link">${msgSuccess}</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
