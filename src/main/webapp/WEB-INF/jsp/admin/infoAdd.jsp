<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.info" var="info"/>

<div class="container">

    <form action="<c:url value='infoList.html'/>" method="POST" role="form">
        <div class="form-group">
            <label for="header"><fmt:message key="info.header.name" bundle="${info}"/></label>
            <input type="text" class="form-control" id="header" name="header" placeholder='<fmt:message key="info.header.placeholder" bundle="${info}"/>'>
          </div>
          <div class="form-group">
              <label for="body"><fmt:message key="info.body.name" bundle="${info}"/></label>
              <textarea id="body" name="body" class="form-control" rows="20"></textarea>
          </div>
          <div class="form-group">
          <button type="submit" id="submitButton" class="btn btn-primary"><fmt:message key="info.add.button" bundle="${info}"/></button>
          </div>
    </form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>