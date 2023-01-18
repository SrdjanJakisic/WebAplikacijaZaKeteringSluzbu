<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Додавање агенције</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>   
        <script>
            
        </script>
    <body>
        <%
            ArrayList<User> managers = (ArrayList<User>)request.getAttribute("managers");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Додавање агенције</h1>
        <img src="logo4.jpg" alt=""/>
        <div class="formInputs">
            <form action="AddStore" method="POST" enctype="multipart/form-data">
                <div class="center">
                    <label for="storeName" class="form-label">Назив продавнице</label>
                    <input type="text" class="form-control" id="storeName" name="storeName">
                    
                    <label for="storePhone" class="form-label">Телефон</label>
                    <input type="text" class="form-control" id="storePhone" name="storePhone">

                    <label for="storeStreet" class="form-label">Улица</label>
                    <input type="text" class="form-control" id="storeStreet" name="storeStreet">

                    <label for="storeCity" class="form-label">Град</label>
                    <input type="text" class="form-control" id="storeCity" name="storeCity"><br>

                    <label for="manager" class="form-label">Менаџер</label>
                    <select name="manager" id="manager">
                        <% for(User m:managers) { %>
                        <option value="<%= m.getId() %>"> <%= m.getId() %> - <%= m.getName() %> <%= m.getSurname() %></option>
                        <% } %>
                    </select><br><br>

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
