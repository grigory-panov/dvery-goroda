<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">

<c:if test="${not empty message}">
<p class="text-info">${message}</p>
</c:if>

<h3>${category.name}</h3>

<a href='<c:url value="productAdd.html?categoryId=${category.id}"/>'>Add new product</a>

<c:if test="${not empty products}">
    <table class="table">

        <c:forEach var="o" items="${products}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="productEdit.html?id=${o.id}"/>'>${o.name}</a></td>
                <td>${o.description}</td>
                <td>${o.dateAdd}</td>
                <td>${o.dateUpdate}</td>
                <td><a href='<c:url value="productVersionList.html?productId=${o.id}"/>'>versions</a></td>
                <td><a href='<c:url value="/action/delete/product/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>