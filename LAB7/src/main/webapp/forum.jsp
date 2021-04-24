<%--
  Created by IntelliJ IDEA.
  User: vivacloins
  Date: 24.04.2021
  Time: 08:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="kz.labka.demo.Controller.ForumController" %>
<%@ page import="kz.labka.demo.Model.Messages" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forum page</title>
</head>
<body>
<%
    ForumController forumController = new ForumController();

    ArrayList<Message> messages = forumController.getForum_messages().getMessagesInForum(1, forumController.getUsersCollection().getConn());

    for (Message message : messages) {
        response.getWriter().println("<h1> " + message.getAuthor() + " </h1>");
        response.getWriter().println("<p> " + message.getMessage() + " </p>");
    }
%>

<form action="/forum" method="post">
    <input type="text" name="message" placeholder="Comment">
    <br>
    <input type="submit" value="Submit">
</form>


</body>
</html>

