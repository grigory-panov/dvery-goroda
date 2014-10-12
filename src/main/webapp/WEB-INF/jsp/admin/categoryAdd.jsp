<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.category" var="categoryMessage"/>

<div class="container">
<form action="<c:url value='categoryList.html'/>" class="form-horizontal"  method="POST" role="form">
    <div class="form-group">
         <label for="header" class="col-sm-2 control-label"><fmt:message key="table.name" bundle="${common}"/></label>
         <div class="col-xs-5">
            <input type="text" class="form-control"  id="name" name="name" autofocus required
             placeholder='<fmt:message key="category.name.placeholder" bundle="${categoryMessage}"/>' tabindex="1">
         </div>
    </div>
    <div class="form-group">
         <label for="description" class="col-sm-2 control-label"><fmt:message key="table.description" bundle="${common}"/></label>
         <div class="col-sm-10">
            <input type="text" class="form-control" id="description" name="description"
             placeholder='<fmt:message key="category.description.placeholder" bundle="${categoryMessage}"/>' tabindex="2">
         </div>
    </div>

    <div class="form-group">
         <label for="group" class="col-sm-2 control-label"><fmt:message key="category.group.name" bundle="${categoryMessage}"/></label>
         <div class="col-xs-3">
            <input type="text" class="form-control" id="group" name="group" tabindex="3" required
             placeholder='<fmt:message key="category.group.placeholder" bundle="${categoryMessage}"/>'>
         </div>
    </div>
    <div class="form-group">
         <label for="order" class="col-sm-2 control-label"><fmt:message key="category.order.name" bundle="${categoryMessage}"/></label>
         <div class="col-xs-3">
            <input type="text" class="form-control" id="order" name="order" tabindex="4" required
            placeholder='<fmt:message key="category.order.placeholder" bundle="${categoryMessage}"/>'>
         </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" id="submitButton" class="btn btn-primary" tabindex="5">
                <fmt:message key="category.add.button" bundle="${categoryMessage}"/>
            </button>
        </div>
    </div>
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>