<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.info" var="info"/>

<div class="container">

<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<h3>${category.name}</h3>

<a href='<c:url value="infoAdd.html"/>'><fmt:message key="info.add.link" bundle="${info}"/></a>

<c:if test="${not empty infos}">
    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="info.header.name" bundle="${info}"/></th>
            <th><fmt:message key="table.dateAdd" bundle="${common}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${infos}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="infoEdit.html?id=${o.id}"/>'>${o.header}</a></td>
                <td>${o.dateAdd}</td>
                <td><a href='<c:url value="/action/delete/info/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>