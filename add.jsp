<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add a Question</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2 class="mb-4">Add a New Quiz Question</h2>

    <%
        String message = "";

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                String question = request.getParameter("question");
                String optiona = request.getParameter("optiona");
                String optionb = request.getParameter("optionb");
                String optionc = request.getParameter("optionc");
                String optiond = request.getParameter("optiond");
                String answer = request.getParameter("answer");

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
                String sql = "INSERT INTO Quizzer (questions, optiona, optionb, optionc, optiond, answer) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, question);
                stmt.setString(2, optiona);
                stmt.setString(3, optionb);
                stmt.setString(4, optionc);
                stmt.setString(5, optiond);
                stmt.setString(6, answer);
                stmt.executeUpdate();

                message = "✅ Question added successfully!";
            } catch (Exception e) {
                message = "❌ Error: " + e.getMessage();
            }
        }
    %>

    <% if (!message.isEmpty()) { %>
        <div class="alert alert-info"><%= message %></div>
    <% } %>

    <form method="post">
        <div class="mb-3">
            <label class="form-label">Question</label>
            <input name="question" class="form-control" required>
        </div>
        <div class="mb-3"><input name="optiona" class="form-control" placeholder="Option A" required></div>
        <div class="mb-3"><input name="optionb" class="form-control" placeholder="Option B" required></div>
        <div class="mb-3"><input name="optionc" class="form-control" placeholder="Option C" required></div>
        <div class="mb-3"><input name="optiond" class="form-control" placeholder="Option D" required></div>
        <div class="mb-3">
            <label class="form-label">Correct Answer</label>
            <input name="answer" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Add Question</button>
        <a href="index.jsp" class="btn btn-secondary ms-2">Back</a>
    </form>
</body>
</html>
