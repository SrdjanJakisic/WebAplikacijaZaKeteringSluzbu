<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="beans.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Почетна</title> 
        
        <jsp:include page="/components/css-js.jsp"/>
        <link href="components/css.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="/navbar.jsp"/>
        <%
            ArrayList<Store> stores = (ArrayList<Store>)request.getAttribute("stores");
            User user = (User)session.getAttribute("user");      
        %>
        <h1>Продавнице</h1>
        <div class="container" id="cont"  >
            <% for(Store pom: stores) { %>
            <div class="card border-primary mb-3" id="cardBorder">
                <div class="card-header" id="cardHeader">
                    <img src="images/agencyLogo/<%= pom.getStoreImage() %>" id="image">
                </div>
                <div class="card-body">
                    <h3 id="prodTitle"><%= pom.getStoreName() %></h3>
                    <p>Телефон: <%= pom.getStorePhone() %></p> 
                    <p>Улица: <%= pom.getStoreStreet() %></p> 
                    <p>Град: <%= pom.getStoreCity() %></p> 
                    <div class="divButtons">
                        <input type="hidden" name="productId" value="<%= pom.getStoreId() %>">
                        <% if(user == null || user.getUserRoleId() == 1 || user.getUserRoleId() == 2){ %>
                        <p>
                        <div class="btn-group">
                            <a href="Show?typeId=1&storeId=<%= pom.getStoreId() %>" class="btn btn-primary">Слатко</a>
                            <a href="Show?typeId=2&storeId=<%= pom.getStoreId() %>" class="btn btn-primary">Слано</a>
                        </div>   
                        </p>
                        <% } else if(user.getUserRoleId() == 3) { %>
                        <p>
                        <div class="btn-group">
                            <a href="Show?typeId=1&storeId=<%= pom.getStoreId() %>" class="btn btn-primary">Слатко</a>
                            <a href="Show?typeId=2&storeId=<%= pom.getStoreId() %>" class="btn btn-primary">Слано</a>
                        </div>
                        </p>
                        <p>
                        <div class="btn-group">
                            <a href="EditStore?storeId=<%= pom.getStoreId() %>" class="btn btn-success">Измени</a>
                            <a href="DeleteStore?storeId=<%= pom.getStoreId() %>" class="btn btn-danger" onclick='return confirmSubmit()'>Обриши</a>
                        </div>
                        </p>
                        <% } %>

                        <div class="msg">
                            <c:if test="${msg != null}">
                                <div class="alert alert-danger" role="alert">${msg}</div>
                            </c:if>
                            <c:if test="${msgSuccess != null}">
                                <div class="alert alert-success" role="alert">${msgSuccess}</div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div> 
    </body>
</html>
