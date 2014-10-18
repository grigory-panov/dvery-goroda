<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script language="javascript">
    var FEEDBACK_PER_PAGE = ${global.feedbacks_per_page};
</script>

<fmt:setBundle basename="messages.feedback" var="feedback"/>
<div class="container" id="container">
    <div class="bg-warning loading"><fmt:message key="loading.label" bundle="${common}"/></div>
    <div class="page-header">
        <h2><fmt:message key="feedback.header" bundle="${feedback}"/></h2>
    </div>
    <p class="lead"><fmt:message key="feedback.sendFeedBackPrompt" bundle="${feedback}"/></p>
    <form id="feedbackForm" role="form">
        <div class="form-group">
            <label for="userName"><fmt:message key="feedback.inputName" bundle="${feedback}"/></label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder='<fmt:message key="feedback.inputName.placeholder" bundle="${feedback}"/>'>
          </div>
          <div class="form-group">
              <label for="feedbackText"><fmt:message key="feedback.feedbackText" bundle="${feedback}"/></label>
              <textarea id="feedbackText" name="feedbackText" class="form-control" rows="5"></textarea>
          </div>
          <span class="help-block"><fmt:message key="feedback.help" bundle="${feedback}"/></span>
          <div class="form-group">
          <button type="button" id="previewButton" class="btn btn-default"><fmt:message key="feedback.preview.button" bundle="${feedback}"/></button>
          <button type="button" id="submitButton" class="btn btn-primary"><fmt:message key="feedback.send.button" bundle="${feedback}"/></button>
          </div>
    </form>
    <div id="preview">
    </div>

    <div id="content">

    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/showdown.js"/>'></script>
<script src='<c:url value="js/feedback.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>