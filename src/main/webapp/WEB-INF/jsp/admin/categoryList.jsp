<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.category" var="categoryMessage"/>

<div class="container">

<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<a href='<c:url value="categoryAdd.html"/>'><th><fmt:message key="category.add.link" bundle="${categoryMessage}"/></th></a>

<c:if test="${not empty categories}">
    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="category.group.name" bundle="${categoryMessage}"/></th>
            <th><fmt:message key="category.order.name" bundle="${categoryMessage}"/></th>
            <th><fmt:message key="category.product.list" bundle="${categoryMessage}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${categories}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="categoryEdit.html?id=${o.id}"/>'><c:out value="${o.name}"/></a></td>
                <td><c:out value="${o.description}"/></td>
                <td><c:out value="${o.group}"/></td>
                <td><c:out value="${o.order}"/></td>
                <td><a href='<c:url value="productList.html?categoryId=${o.id}"/>'><span class="glyphicon glyphicon-list-alt"></span></a></td>
                <td><a href='<c:url value="/action/delete/category/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty categories}">
    <p><fmt:message key="category.list.empty" bundle="${categoryMessage}"/></p>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>
