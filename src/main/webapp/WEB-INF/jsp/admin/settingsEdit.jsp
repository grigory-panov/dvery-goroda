<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.settings" var="settingsMessage"/>

<div class="container">
<form action="<c:url value='settingsList.html'/>" method="POST" class="form-vertical">
    <input type="hidden" name="key" value="${settings.key}">
        <div class="form-group">
            <label for="value">${settings.key}</label>
            <input type="text" required value="<c:out value='${settings.value}'/>" class="form-control" name="value" placeholder='<fmt:message key="settings.value.placeholder" bundle="${settingsMessage}"/>'>
          </div>
          <div class="form-group">
              <label for="description"><fmt:message key="table.description" bundle="${common}"/></label>
              <input type="text" value="${settings.description}" class="form-control" name="description" placeholder='<fmt:message key="settings.description.placeholder" bundle="${settingsMessage}"/>'>
          </div>
          <div class="form-group">
          <button type="submit" id="submitButton" class="btn btn-primary"><fmt:message key="settings.update.button" bundle="${settingsMessage}"/></button>
    </div>
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>