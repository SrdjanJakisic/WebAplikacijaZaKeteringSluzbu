<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Измена корисника</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <% User user = (User)session.getAttribute("user"); %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Измените податке:</h1>
        <div>
            <form action="editUser" method="POST">
                <div class="center">
                    <div class="formInputs">
                        <label for="username" class="form-label">Корисничко име</label>
                        <input type="text" class="form-control" id="username" name="username" value="<%= user.getUsername() %>">
                        <label for="name" class="form-label">Име</label>
                        <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                        <label for="surname" class="form-label">Презиме</label>
                        <input type="text" class="form-control" id="surname" name="surname" value="${user.surname}">
                        <label for="phone" class="form-label">Телефон</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}"> 
                        <label for="email" class="form-label">Е-Пошта</label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                        <label for="password" class="form-label">Шифра</label>
                        <input type="password" class="form-control" id="password" name="password" value="${user.password}">
                        <label for="address" class="form-label">Адреса</label>
                        <input type="text" class="form-control" id="address" name="address" value="${user.address}">
                        <label for="city" class="form-label">Град</label>
                        <input type="text" class="form-control" id="city" name="city" value="${user.city}">
                    </div><br>
                    <div class="btnConf">
                        <button type="submit" class="btn btn-primary">Измените</button>
                    </div><br>
                    <div class="msg" id="msg">
                        <c:if test="${msg != null}">
                            <div class="alert alert-danger" role="alert">${msg}</div>
                        </c:if>
                        <c:if test="${msgSuccess != null}">
                            <div class="alert alert-success" role="alert">${msgSuccess}</div>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
