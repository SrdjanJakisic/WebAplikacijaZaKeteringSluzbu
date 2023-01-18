<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Додавање производа</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <%
            Product product = (Product)request.getAttribute("product");
            ArrayList<Store> stores = (ArrayList<Store>)request.getAttribute("stores");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Додајте производ:</h1>
        <div class="formInputs">
            <form action="AddProduct" method="POST" enctype="multipart/form-data">
                <div class="center">
                    <label for="productName" class="form-label">Назив производа</label>
                    <input type="text" class="form-control" id="productName" name="productName">

                    <label for="price" class="form-label">Цена</label>
                    <input type="text" class="form-control" id="price" name="price">

                    <label for="weight" class="form-label">Тежина</label>
                    <input type="text" class="form-control" id="weight" name="weight">

                    <label for="description" class="form-label">Опис производа</label>
                    <input type="text" class="form-control" id="description" name="description"><br> 

                    <label for="store" class="form-label">Агенције</label>
                    <select name="storeId" id="store">
                        <% for(Store a:stores) { %>
                        <option value="<%= a.getStoreId() %>"><%= a.getStoreName() %></option>
                        <% } %>
                    </select><br> 

                    <label for="typeId" class="form-label">Тип производа</label>
                    <select name="typeId" id="typeId">
                        <option value="1">Слатко</option>
                        <option value="2">Слано</option>
                    </select><br> 

                    <input id="uploadImage" type="file" name="image" onchange="PreviewImage();" /><br><br>
                    <img id="uploadPreview" />
                </div>
                <div class="btnConf">
                    <button type="submit" class="btn btn-primary">Потврди</button>
                </div><br>
                <div class="msg">
                    <c:if test="${msg != null}">
                        <div class="alert alert-danger" role="alert">${msg}</div>
                    </c:if>
                    <c:if test="${msgSuccess != null}">
                        <div class="alert alert-success" role="alert">${msgSuccess}</div>
                    </c:if>
                </div>
            </form>
        </div>
    </body>
</html>
