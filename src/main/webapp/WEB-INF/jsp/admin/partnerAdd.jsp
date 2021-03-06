<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.partners" var="partnerMessages"/>

<div class="container">
<form enctype="multipart/form-data" action="<c:url value='partnersList.html'/>" class="form-horizontal"  method="POST" role="form">
    <div class="form-group">
         <label for="name" class="col-sm-2 control-label"><fmt:message key="table.name" bundle="${common}"/></label>
         <div class="col-xs-5">
            <input type="text" class="form-control"  id="name" name="name" autofocus required
             placeholder='<fmt:message key="partner.name.placeholder" bundle="${partnerMessages}"/>' tabindex="1">
         </div>
    </div>

    <div class="form-group">
         <label for="url" class="col-sm-2 control-label"><fmt:message key="partner.url.name" bundle="${partnerMessages}"/></label>
         <div class="col-xs-3">
            <input type="text" class="form-control" id="url" name="url" tabindex="2"
             placeholder='<fmt:message key="partner.url.placeholder" bundle="${partnerMessages}"/>'>
         </div>
    </div>
    <div class="form-group">
         <label for="banner" class="col-sm-2 control-label"><fmt:message key="partner.banner.name" bundle="${partnerMessages}"/></label>
         <div class="col-xs-3">
            <input type="file" name="banner">
         </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" id="submitButton" class="btn btn-primary" tabindex="5">
                <fmt:message key="partner.add.button" bundle="${partnerMessages}"/>
            </button>
        </div>
    </div>
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>