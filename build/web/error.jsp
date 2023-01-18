<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Грешка</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <jsp:include page="/navbar.jsp"/>
        <div class="msg" id="msg">
            <c:if test="${msg != null}">
                <div class="alert alert-danger" role="alert">${msg}</div>
            </c:if>
            <c:if test="${msgSuccess != null}">
                <div class="alert alert-success" role="alert">${msgSuccess}</div>
            </c:if>
        </div>
    </body>
</html>
