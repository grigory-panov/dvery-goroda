<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.feedback" var="feedbackMessage"/>

<div class="container">
<h2><fmt:message key="feedback.header" bundle="${feedbackMessage}"/></h2>
<c:if test="${not empty message}">
<p class="${messageClass}">${message}</p>
</c:if>

<c:if test="${not empty feedbacks}">
    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.dateAdd" bundle="${common}"/></th>
            <th><fmt:message key="feedback.author.name" bundle="${feedbackMessage}"/></th>
            <th><fmt:message key="feedback.text.name" bundle="${feedbackMessage}"/></th>
            <th><fmt:message key="feedback.ip.name" bundle="${feedbackMessage}"/></th>
            <th><fmt:message key="feedback.country.name" bundle="${feedbackMessage}"/></th>
            <th><fmt:message key="feedback.city.name" bundle="${feedbackMessage}"/></th>
            <th><fmt:message key="table.approve" bundle="${common}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${feedbacks}">
            <c:if test="${o.approved}">
            <tr class="success">
            </c:if>
            <c:if test="${not o.approved}">
            <tr>
            </c:if>

                <td>${o.id}</td>
                <td><a href='<c:url value="feedbackView.html?id=${o.id}"/>'>${o.dateAdd}</a></td>
                <td><c:out value='${o.author}'/></td>
                <td><c:out value='${fn:substring(o.text, 0, 100)}'/>...</td>
                <td><c:out value='${o.ip}'/></td>
                <td><c:out value='${o.country}'/></td>
                <td><c:out value='${o.city}'/></td>
                <c:if test="${not o.approved}">
                <td><a href='<c:url value="/action/approve/feedback/${o.id}"/>' class="approve"><span class="glyphicon glyphicon-ok"></span></a></td>
                </c:if>
                <c:if test="${o.approved}">
                <td>${o.dateApprove}</td>
                </c:if>
                <td><a href='<c:url value="/action/delete/feedback/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty feedbacks}">
    <p><fmt:message key="feedback.list.empty" bundle="${feedbackMessage}"/></p>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>