<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.settings" var="settingsMessage"/>

<div class="container">

<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<h3><fmt:message key="settings.header" bundle="${settingsMessage}"/></h3>

<c:if test="${not empty settings}">
    <table class="table">
        <tr>
            <th><fmt:message key="settings.key.name" bundle="${settingsMessage}"/></th>
            <th><fmt:message key="settings.value.name" bundle="${settingsMessage}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${settings}">
            <tr>
                <td><a href='<c:url value="settingsEdit.html?key=${o.key}"/>'>${o.key}</a></td>
                <td>${o.value}</td>
                <td>${o.description}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>