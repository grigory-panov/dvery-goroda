<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">

<c:if test="${not empty message}">
<p class="text-info">${message}</p>
</c:if>

<h3>${category.name} - ${product.name}</h3>

<a href='<c:url value="productVersionAdd.html?productId=${product.id}"/>'>Add new product version</a>

<c:if test="${not empty productVersions}">
    <table class="table">

        <c:forEach var="o" items="${productVersions}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="productVersionEdit.html?id=${o.id}"/>'>${o.name}</a></td>
                <td>${o.description}</td>
                <td>${o.price}</td>
                <td>${o.size}</td>
                <td>${o.order}</td>
                <td><a href='<c:url value="/image/thumbnail/${o.productId}/${o.id}"/>'>image<a></td>
                <td><a href='<c:url value="/action/delete/productVersion/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>