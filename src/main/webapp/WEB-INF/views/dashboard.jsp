<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Tableau de bord</title>
</head>
<body>
	<h1>Bienvenue <c:out value="${user}"/></h1>
	<p><a href="${pageContext.request.contextPath}/produits">Voir les produits</a></p>
	<p><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></p>
</body>
</html>
