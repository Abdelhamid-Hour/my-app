<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produits</title>
</head>
<body>
    <h1>Catalogue de produits</h1>
    <c:if test="${not empty sessionScope.flash}">
        <p style="color: green"><c:out value="${sessionScope.flash}"/></p>
        <c:remove var="flash" scope="session"/>
    </c:if>
    <p><a href="${ctx}/produits?action=new">Ajouter un produit</a></p>
    <c:choose>
        <c:when test="${empty produits}">
            <p>Aucun produit.</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <tr><th>Nom</th><th>Description</th><th>Prix</th><th>Stock</th><th>Actions</th></tr>
                <c:forEach var="produit" items="${produits}">
                    <tr>
                        <td><c:out value="${produit.nom}"/></td>
                        <td><c:out value="${produit.description}"/></td>
                        <td><fmt:formatNumber value="${produit.prix}" minFractionDigits="2"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${produit.stock == 0}">
                                    <span style="color: red">${produit.stock}</span>
                                </c:when>
                                <c:otherwise>${produit.stock}</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${ctx}/produits?action=edit&id=${produit.id}">Éditer</a>
                            <form method="post" action="${ctx}/produits" style="display:inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${produit.id}">
                                <button type="submit" onclick="return confirm('Supprimer ce produit ?')">Supprimer</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <p><a href="${ctx}/dashboard">Retour au tableau de bord</a></p>
</body>
</html>
