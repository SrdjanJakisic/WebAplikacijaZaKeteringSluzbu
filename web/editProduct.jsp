<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Измена производа</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <jsp:include page="/navbar.jsp"/>
        <%
            Product product = (Product)request.getAttribute("product");
            ArrayList<Store> stores = (ArrayList<Store>)request.getAttribute("stores");
        %>
        <h1>Измените производ:</h1>
        <div class="formInputs">
            <form action="EditProduct" method="POST" enctype="multipart/form-data">

                <div class="center">
                    <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                    <input type="hidden" name="productTypeId" value="<%= product.getTypeId() %>">

                    <label for="productName" class="form-label">Назив производа</label>
                    <input type="text" class="form-control" id="productName" name="productName" value="<%= product.getProductName() %>">

                    <label for="price" class="form-label">Цена</label>
                    <input type="text" class="form-control" id="price" name="price" value="<%= product.getProductPrice() %>">

                    <label for="weight" class="form-label">Тежина</label>
                    <input type="text" class="form-control" id="weight" name="weight" value="<%= product.getProductWeight() %>">

                    <label for="description" class="form-label">Опис производа</label>
                    <input type="text" class="form-control" id="description" name="description" value="<%= product.getProductDescription() %>"><br> 

                    <label for="store" class="form-label">Продавнице</label>
                    <select name="storeId" id="store">
                        <option value="<%= product.getStoreId() %>">Тренутна продавница</option>
                        <% for(Store a:stores) { %>
                        <option value="<%= a.getStoreId() %>"><%= a.getStoreName() %></option>
                        <% } %>
                    </select><br> 

                    <label for="type" class="form-label">Тип производа</label>
                    <select name="type" id="typeId">
                        <option value="1">Слатко</option>
                        <option value="2">Слано</option>
                    </select><br> 

                    <label for="imgPreview">Тренутна слика</label><br>
                    <img src="images/products/<%= product.getProductImage() %>" alt="images/noimg.png" id="imgPreview" class="img-thumbnail"><br><br>

                    <label for="uploadImage">Изаберите слику</label><br>
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
