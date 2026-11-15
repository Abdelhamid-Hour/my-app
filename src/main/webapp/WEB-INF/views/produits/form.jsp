<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${empty produit.id}">Nouveau produit</c:when><c:otherwise>Modifier le produit</c:otherwise></c:choose></title>
</head>
<body>
    <h1>
        <c:choose>
            <c:when test="${empty produit.id}">Nouveau produit</c:when>
            <c:otherwise>Modifier le produit</c:otherwise>
        </c:choose>
    </h1>
    <form method="post" action="${ctx}/produits">
        <c:if test="${not empty produit.id}">
            <input type="hidden" name="id" value="${produit.id}">
        </c:if>
        <label>Nom: <input type="text" name="nom" value="<c:out value='${produit.nom}'/>"></label>
        <c:if test="${not empty erreurs.nom}"><p style="color: red"><c:out value="${erreurs.nom}"/></p></c:if>
        <br>
        <label>Description: <textarea name="description"><c:out value="${produit.description}"/></textarea></label>
        <c:if test="${not empty erreurs.description}"><p style="color: red"><c:out value="${erreurs.description}"/></p></c:if>
        <br>
        <label>Prix: <input type="text" name="prix" value="<c:out value='${produit.prix}'/>"></label>
        <c:if test="${not empty erreurs.prix}"><p style="color: red"><c:out value="${erreurs.prix}"/></p></c:if>
        <br>
        <label>Stock: <input type="number" name="stock" value="${produit.stock}" min="0"></label>
        <c:if test="${not empty erreurs.stock}"><p style="color: red"><c:out value="${erreurs.stock}"/></p></c:if>
        <br>
        <button type="submit">Enregistrer</button>
    </form>
    <p><a href="${ctx}/produits">Annuler</a></p>
</body>
</html>
