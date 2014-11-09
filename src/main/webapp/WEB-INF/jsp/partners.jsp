<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<fmt:setBundle basename="messages.partners" var="partnersMessage"/>
<div class="container">

    <div class="bg-warning loading"><fmt:message key="loading.label" bundle="${common}"/></div>
    <div class="page-header">
        <h2><fmt:message key="partners.header" bundle="${partnersMessage}"/></h2>
    </div>
    <p class="lead"></p>
    <div id="partners">

    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/partners.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>