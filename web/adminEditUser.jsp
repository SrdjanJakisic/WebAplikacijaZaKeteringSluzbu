<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>
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
        <% 
            User user = (User)request.getAttribute("user"); 
            ArrayList<UserRole> userRoles = (ArrayList<UserRole>)request.getAttribute("userRoles");
        %>
        <jsp:include page="/navbar.jsp"/>
        <h1>Измените податке:</h1>
        <div>
            <form action="AdminEditUser" method="POST">
                <div class="center">
                    <div class="formInputs">
                        <input type="hidden" name="userId" value="<%= user.getId() %>">
                        <label for="username" class="form-label">Корисничко име</label>
                        <input type="text" class="form-control" id="username" name="username" value="<%= user.getUsername() %>">
                        <label for="name" class="form-label">Име</label>
                        <input type="text" class="form-control" id="name" name="name" value="<%= user.getName() %>">
                        <label for="surname" class="form-label">Презиме</label>
                        <input type="text" class="form-control" id="surname" name="surname" value="<%= user.getSurname() %>">
                        <label for="phone" class="form-label">Телефон</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="<%= user.getPhone() %>"> 
                        <label for="email" class="form-label">Е-Пошта</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>">
                        <label for="password" class="form-label">Шифра</label>
                        <input type="password" class="form-control" id="password" name="password" value="<%= user.getPassword() %>">
                        <label for="address" class="form-label">Адреса</label>
                        <input type="text" class="form-control" id="address" name="address" value="<%= user.getAddress() %>">
                        <label for="city" class="form-label">Град</label>
                        <input type="text" class="form-control" id="city" name="city" value="<%= user.getCity() %>">
                        <label for="userRoleId" class="form-label">Тренутна позиција</label>
                        <input type="text" class="form-control" id="userRoleId" value="<%= user.getUserRoleId() %>" readonly >
                        <label for="userRoleId" class="form-label">Позиција</label><br>
                        <select name="userRoleId" id="userRoleId">
                            <option value="<%= user.getUserRoleId() %>">Тренутна позиција</option>
                            <% for(UserRole ur:userRoles) { %>
                            <option value="<%= ur.getUserRoleId() %>"><%= ur.getUserRoleId() %> - <%= ur.getRoleName() %></option>
                            <% } %>
                        </select><br> 
                    </div><br>
                    <div class="btnConf">
                        <button type="submit" class="btn btn-primary">Измените</button>
                    </div><br>
                    <c:if test="${msg != null}">
                        <div class="alert alert-danger" role="alert">${msg}</div>
                    </c:if>
                    <c:if test="${msgSuccess != null}">
                        <div class="alert alert-success" role="alert">${msgSuccess}</div>
                    </c:if>
                </div>
            </form>
        </div>>
    </body>
</html>
