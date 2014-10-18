<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.category" var="categoryMessage"/>

<div class="container">
    <div class="panel panel-default">
      <!-- Default panel contents -->
      <div class="panel-heading"><c:out value='${feedback.author}'/></div>
      <div class="panel-body">
        <p><c:out value='${feedback.text}'/></p>
      </div>

      <!-- List group -->
      <ul class="list-group">
        <li class="list-group-item"><c:out value='${feedback.dateAdd}'/></li>
        <li class="list-group-item"><c:out value='${feedback.ip}'/></li>
        <li class="list-group-item"><c:out value='${feedback.country}'/></li>
        <li class="list-group-item"><c:out value='${feedback.city}'/></li>
      </ul>
      </div>

      <c:if test="${not o.approved}">
      <a href='<c:url value="/action/approve/feedback/${feedback.id}"/>' class="btn approve">Approve<span class="glyphicon glyphicon-ok"></span></a>
      </c:if>
      <a class="btn btn-danger delete" href="/action/delete/feedback/${feedback.id}">Delete</a>

</div>

<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>