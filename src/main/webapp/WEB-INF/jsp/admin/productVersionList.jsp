<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.products" var="productMessages"/>

<div class="container">

<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<h3><a href='<c:url value="categoryList.html"/>'>${category.name}</a> - <a href='<c:url value="productList.html?categoryId=${category.id}"/>'>${product.name}</a></h3>

<a href='<c:url value="productVersionAdd.html?productId=${product.id}"/>' class="btn btn-primary"><fmt:message key="version.add.link" bundle="${productMessages}"/></a>

<c:if test="${not empty productVersions}">
    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="version.price.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="version.size.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="version.order.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="version.file.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${productVersions}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="productVersionEdit.html?id=${o.id}"/>'><c:out value="${o.name}"/></a></td>
                <td><c:out value="${o.description}"/></td>
                <td><c:out value="${o.price}"/></td>
                <td><c:out value="${o.size}"/></td>
                <td><c:out value="${o.order}"/></td>
                <td>
                    <a href='<c:url value="/image/version/${o.productId}/${o.id}"/>'>
                        <img src='<c:url value="/image/thumbnail/${o.productId}/${o.id}"/>'
                        class="img-thumbnail" alt="X">
                    <a>
                </td>
                <td><a href='<c:url value="/action/delete/productVersion/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty productVersions}">
    <p><fmt:message key="versions.list.empty" bundle="${productMessages}"/></p>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>