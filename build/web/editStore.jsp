<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Измена агенције</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <%
            ArrayList<User> managers = (ArrayList<User>)request.getAttribute("managers");
            Store store = (Store)request.getAttribute("store");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Измена агенције</h1>

        <div class="formInputs">
            <form action="EditStore" method="POST" enctype="multipart/form-data">
                <div class="center">
                    <input type="hidden" name="storeId" value="<%= store.getStoreId() %>">
                    <label for="storeName" class="form-label">Назив продавнице</label>
                    <input type="text" class="form-control" id="storeName" name="storeName" value="<%= store.getStoreName() %>">
                    
                    <label for="storePhone" class="form-label">Телефон</label>
                    <input type="text" class="form-control" id="storePhone" name="storePhone" value="<%= store.getStorePhone() %>">

                    <label for="street" class="form-label">Улица</label>
                    <input type="text" class="form-control" id="street" name="street" value="<%= store.getStoreStreet() %>"><br>

                    <label for="city" class="form-label">Град</label>
                    <input type="text" class="form-control" id="city" name="city" value="<%= store.getStoreCity() %>"><br>

                    <label for="currentManagerId" class="form-label">ID тренутног менаџера</label>
                    <input type="text" id="currentManagerId" value="<%= store.getUserId() %>" readonly size="1" style="text-align: center">
                    <br>
                    <label for="manager" class="form-label">Менаџер</label><br>
                    <select name="manager" id="manager">
                        <option value="<%= store.getUserId() %>">Тренутни менаџер</option>
                        <% for(User m:managers) { %>
                        <option value="<%= m.getId() %>"> <%= m.getId() %> - <%= m.getName() %> <%= m.getSurname() %></option>
                        <% } %>
                    </select><br><br>
                    <label>Тренутна слика</label><br>
                    <img src="images/agencyLogo/<%= store.getStoreImage() %>" alt="images/noimg.png" id="imgPreview" /><br><br>

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
