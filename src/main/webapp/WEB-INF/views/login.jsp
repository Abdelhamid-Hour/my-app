<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
    <h1>Connexion</h1>
    <c:if test="${not empty erreur}">
        <p style="color: red"><c:out value="${erreur}"/></p>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <label>Nom d'utilisateur:
            <input type="text" name="username" value="<c:out value='${username}'/>" required>
        </label>
        <br>
        <label>Mot de passe:
            <input type="password" name="password" required>
        </label>
        <br>
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>
