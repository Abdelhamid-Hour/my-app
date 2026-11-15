<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription réussie</title>
</head>
<body>
    <h1>Bienvenue <c:out value="${utilisateur}"/> !</h1>
    <a href="${pageContext.request.contextPath}/login">Se connecter</a>
</body>
</html>
