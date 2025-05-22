<%@ page import="quizzing.QuizManager, java.util.*" %>
<%
    int qIndex = (session.getAttribute("qIndex") != null) ? (Integer)session.getAttribute("qIndex") : 0;
    int score = (session.getAttribute("score") != null) ? (Integer)session.getAttribute("score") : 0;

    // Get or load questions
    List<String[]> questionsList = (List<String[]>) session.getAttribute("questionsList");

    if (questionsList == null) {
        try {
            QuizManager qm = new QuizManager();
            questionsList = qm.getQuestions();
            session.setAttribute("questionsList", questionsList);
        } catch (Exception e) {
            out.println("Error loading questions: " + e.getMessage());
            return;
        }
    }

    // Handle answer if not first question
    if (request.getParameter("answer") != null && qIndex > 0) {
        String[] prevQ = questionsList.get(qIndex - 1);
        String correct = prevQ[5];
        if (request.getParameter("answer").equals(correct)) {
            score++;
            session.setAttribute("score", score);
        }
    }

    // If finished
    if (qIndex >= questionsList.size()) {
        response.sendRedirect("result.jsp");
        return;
    }

    String[] currentQ = questionsList.get(qIndex);
    List<String> options = Arrays.asList(currentQ[1], currentQ[2], currentQ[3], currentQ[4]);
    Collections.shuffle(options);

    session.setAttribute("qIndex", qIndex + 1);
%>

<html>
<head>
    <title>Quiz Question</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <form method="post" action="quiz.jsp">
        <h4><%= currentQ[0] %></h4>
        <% for (String opt : options) { %>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="answer" value="<%= opt %>" required>
                <label class="form-check-label"><%= opt %></label>
            </div>
        <% } %>
        <button type="submit" class="btn btn-primary mt-3">Next</button>
    </form>
</body>
</html>
