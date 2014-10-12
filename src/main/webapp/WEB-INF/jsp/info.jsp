<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<fmt:setBundle basename="messages.info" var="info"/>
<div class="container">
    <div class="bg-warning loading"><fmt:message key="loading.label" bundle="${common}"/></div>
    <div class="page-header">
        <h2><fmt:message key="info.header" bundle="${info}"/></h2>
    </div>

    <ul class="pager">
      <li class="previous disabled"><a href="#">&larr;</a></li>
      <li class="next disabled"><a href="#">&rarr;</a></li>
    </ul>

    <div id="content">

    </div>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/showdown.js"/>'></script>
<script src='<c:url value="js/info.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>