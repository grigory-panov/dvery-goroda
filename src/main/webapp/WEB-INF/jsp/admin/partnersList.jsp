<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.partners" var="partnersMessages"/>

<div class="container">

<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<h3><fmt:message key="partners.header" bundle="${partnersMessages}"/></h3>

<a href='<c:url value="partnerAdd.html"/>'><fmt:message key="partner.add.link" bundle="${partnersMessages}"/></a>

<c:if test="${not empty partners}">

    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="partner.url.name" bundle="${partnersMessages}"/></th>
            <th></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${partners}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="partnerEdit.html?id=${o.id}"/>'><c:out value="${o.name}"/></a></td>
                <td><a href="<c:out value='${o.url}'/>"/><c:out value="${o.url}"/></a></td>
                <td><img src="<c:url value='/image/partner/${o.id}'/>" alt="X"></td>
                <td><a href='<c:url value="/action/delete/partner/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty partners}">
    <p><fmt:message key="partner.list.empty" bundle="${partnersMessages}"/></p>
</c:if>

</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>