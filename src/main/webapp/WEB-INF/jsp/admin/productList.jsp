<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.products" var="productMessages"/>

<div class="container">

<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<h3><a href='<c:url value="categoryList.html"/>'>${category.name}</a></h3>

<a href='<c:url value="productAdd.html?categoryId=${category.id}"/>'><fmt:message key="product.add.link" bundle="${productMessages}"/></a>

<c:if test="${not empty products}">

    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="table.dateAdd" bundle="${common}"/></th>
            <th><fmt:message key="table.dateUpdate" bundle="${common}"/></th>
            <th><fmt:message key="product.order.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="product.version.list" bundle="${productMessages}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${products}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="productEdit.html?id=${o.id}"/>'><c:out value="${o.name}"/></a></td>
                <td><c:out value="${o.description}"/></td>
                <td>${o.dateAdd}</td>
                <td>${o.dateUpdate}</td>
                <td>${o.order}</td>
                <td><a href='<c:url value="productVersionList.html?productId=${o.id}"/>'><span class="glyphicon glyphicon-list-alt"></span></a></td>
                <td><a href='<c:url value="/action/delete/product/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty products}">
    <p><fmt:message key="product.list.empty" bundle="${productMessages}"/></p>
</c:if>

</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>