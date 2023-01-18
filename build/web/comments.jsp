<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Утисци</title>
        <link href="components/css.css" rel="stylesheet" type="text/css">
        <jsp:include page="/components/css-js.jsp"/>
    </head>
    <body>
        <jsp:include page="/navbar.jsp"/>
        <%
            ArrayList<Comment> comments = (ArrayList<Comment>)request.getAttribute("comments");
            User user = (User)session.getAttribute("user");
        %>
        <form action="Comments" method="POST">
            <% if(comments != null) {%>
            <h1>Сви утисци</h1>
            <div class="container my-3">
                <% for(Comment c:comments) { %>
                <div class="form-floating">
                    <textarea class="form-control"  id="floatingTextarea"><%= c.getCommentContent() %></textarea><br>
                    <label for="floatingTextarea">Корисник <%= c.getUsername()  %></label>
                </div>
                <% } %>
                <% } else { %>
                <h1>Нема коментара</h1>
                <% } %>
                <% if(session.getAttribute("user") != null && user.getUserRoleId() == 1) { %>
                <div class="form-floating">
                    <textarea class="form-control" name="commentContent" id="floatingTextarea"></textarea>
                    <label for="floatingTextarea">Унесите ваш утисак</label>
                </div><br>
                <button type="submit" class="btn btn-primary">Коментариши</button>
                <% } %>
            </div>
        </form>
    </body>
</html>
