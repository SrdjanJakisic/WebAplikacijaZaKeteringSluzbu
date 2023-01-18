<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пријава</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <jsp:include page="/navbar.jsp"/>
        <h1>Региструјте се:</h1>
        <div class="formInputs">
            <form action="Registration" method="POST">
                <div class="center">
                    <div >
                        <label for="username" class="form-label">Корисничко име</label>
                        <input type="text" class="form-control" id="username" name="username">
                        
                        <label for="name" class="form-label">Име</label>
                        <input type="text" class="form-control" id="name" name="name">
                        
                        <label for="surname" class="form-label">Презиме</label>
                        <input type="text" class="form-control" id="surname" name="surname">
                        
                        <label for="phone" class="form-label">Телефон</label>
                        <input type="text" class="form-control" id="phone" name="phone"> 
                        
                        <label for="email" class="form-label">Е-Пошта</label>
                        <input type="email" class="form-control" id="email" name="email">
                        
                        <label for="password" class="form-label">Шифра</label>
                        <input type="password" class="form-control" id="password" name="password">
                        
                        <label for="address" class="form-label">Адреса</label>
                        <input type="text" class="form-control" id="address" name="address">
                        
                        <label for="city" class="form-label">Град</label>
                        <input type="text" class="form-control" id="city" name="city">
                    </div><br>
                    <div class="btnConf">
                        <button type="submit" class="btn btn-primary">Потврди</button>
                    </div><br>
                    <div class="msg">
                        <c:if test="${msg != null}">
                            <div class="alert alert-danger" role="alert">${msg}</div>
                        </c:if>
                        <c:if test="${msgSuccess != null}">
                            <div class="alert alert-success" role="alert">
                                <a href="login.jsp" class="alert-link">${msgSuccess}</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
