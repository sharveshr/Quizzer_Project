<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int score = (Integer) session.getAttribute("score");
%>
<html>
<head>
    <title>Quiz Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Quiz Completed!</h2>
    <p>Your Score: <strong><%= score %></strong></p>
    <a href="index.jsp" class="btn btn-primary">Return to Main Menu</a>
</body>
</html>
