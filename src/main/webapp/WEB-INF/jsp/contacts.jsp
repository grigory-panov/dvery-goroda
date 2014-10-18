<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<fmt:setBundle basename="messages.contacts" var="contacts"/>
<script language="javascript">
    var contacts_lat = ${global.contacts_lat};
    var contacts_lng = ${global.contacts_lng};
</script>
<div class="container">

    <div class="page-header">
        <h2><fmt:message key="contacts.header" bundle="${contacts}"/></h2>
    </div>
    <address>
      <strong>${global.contacts_organization};</strong><br>
      ${global.contacts_address};<br>
      ${global.contacts_phone};
    </address>

    <address>
      <strong>E-mail</strong><br>
      <a href='mailto:${global.contacts_email};'>${global.contacts_email};</a>
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