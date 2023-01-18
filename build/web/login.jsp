<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пријавите се</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="components/css-js.jsp"/>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <h1>Пријавите се:</h1>
        <div class="formInputs">
            <form action="Login" method="POST">
                <div class="center">
                    <div >
                        <label for="email" class="form-label">Е-Пошта</label>
                        <input type="email" class="form-control" id="email" name="email" focus>
                        <label for="password" class="form-label">Шифра</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div><br>
                    <div class="btnConf">
                        <button type="submit" class="btn btn-primary">Пријава</button>
                    </div>
                </div>
            </form>

        </div>
        <div class="msg">
            <c:if test="${msg != null}">
                <div class="alert alert-danger" role="alert">${msg}</div>
            </c:if>
        </div>
    </body>
</html>