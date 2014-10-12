<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<fmt:setBundle basename="messages.contacts" var="contacts"/>
<div class="container">

    <div class="page-header">
        <h2><fmt:message key="contacts.header" bundle="${contacts}"/></h2>
    </div>
    <address>
      <strong><fmt:message key="contacts.organization" bundle="${contacts}"/></strong><br>
      <fmt:message key="contacts.address" bundle="${contacts}"/><br>
      <fmt:message key="contacts.phone" bundle="${contacts}"/>
    </address>

    <address>
      <strong>E-mail</strong><br>
      <a href='mailto:<fmt:message key="contacts.email" bundle="${contacts}"/>'><fmt:message key="contacts.email" bundle="${contacts}"/></a>
    </address>

    <input class="btn btn-primary" type="button" value="Показать карту" id="toggle"/>
    <p>
        <div id="map" style="width: 400px; height: 300px"></div>
    </p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
<script src='<c:url value="js/contacts.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>