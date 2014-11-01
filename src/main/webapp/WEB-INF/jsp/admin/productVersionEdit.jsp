<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.products" var="productMessages"/>

<div class="container">
<form enctype="multipart/form-data" action="<c:url value='productVersionList.html?productId=${product.id}'/>" class="form-horizontal"  method="POST" role="form">
    <input type="hidden" name="id" value="${productVersion.id}">
    <input type="hidden" value="${product.id}" name="productId">
    <div class="form-group">
         <label for="name" class="col-sm-2 control-label"><fmt:message key="table.name" bundle="${common}"/></label>
         <div class="col-xs-5">
            <input type="text" class="form-control"  id="name" name="name" autofocus required
             placeholder='<fmt:message key="version.name.placeholder" bundle="${productMessages}"/>' tabindex="1"
             value="<c:out value='${productVersion.name}'/>">
         </div>
    </div>
    <div class="form-group">
         <label for="description" class="col-sm-2 control-label"><fmt:message key="table.description" bundle="${common}"/></label>
         <div class="col-sm-10">
            <input type="text" class="form-control" id="description" name="description" value="${productVersion.description}"
             placeholder='<fmt:message key="version.description.placeholder" bundle="${productMessages}"/>' tabindex="2">
         </div>
    </div>

    <div class="form-group">
         <label for="price" class="col-sm-2 control-label"><fmt:message key="version.price.name" bundle="${productMessages}"/></label>
         <div class="col-xs-3">
            <input type="text" class="form-control" id="price" name="price" tabindex="3" required value="${productVersion.price}"
             placeholder='<fmt:message key="version.price.placeholder" bundle="${productMessages}"/>'>
         </div>
    </div>
    <div class="form-group">
         <label for="size" class="col-sm-2 control-label"><fmt:message key="version.size.name" bundle="${productMessages}"/></label>
         <div class="col-xs-3">
            <input type="text" class="form-control" id="size" name="size" tabindex="4" value="${productVersion.size}"
            placeholder='<fmt:message key="version.size.placeholder" bundle="${productMessages}"/>'>
         </div>
    </div>
    <div class="form-group">
         <label for="order" class="col-sm-2 control-label"><fmt:message key="version.order.name" bundle="${productMessages}"/></label>
         <div class="col-xs-3">
            <input type="text" class="form-control" id="order" name="order" tabindex="5" required value="${productVersion.order}"
            placeholder='<fmt:message key="version.order.placeholder" bundle="${productMessages}"/>'>
         </div>
    </div>
    <div class="form-group">
         <label for="img" class="col-sm-2 control-label"><fmt:message key="version.file.name" bundle="${productMessages}"/></label>
         <div class="col-xs-3">
            <input type="file" name="img">
         </div>
    </div>
    <div class="form-group">
         <div class="col-sm-offset-2 col-sm-10">
            <img src='<c:url value="/image/thumbnail/${productVersion.productId}/${productVersion.id}"/>' class="img-thumbnail" alt="X">
         </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" id="submitButton" class="btn btn-primary" tabindex="5">
                <fmt:message key="version.save.button" bundle="${productMessages}"/>
            </button>
        </div>
    </div>
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>