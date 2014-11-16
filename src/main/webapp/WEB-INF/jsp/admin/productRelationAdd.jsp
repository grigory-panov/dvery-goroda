<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.products" var="productMessages"/>
<fmt:setBundle basename="messages.category" var="categoryMessages"/>
<script src='<c:url value="../js/admin/select.js"/>'></script>

<div class="container">
<form action="<c:url value='productEdit.html'/>" class="form-horizontal"  method="POST" role="form">
    <input type="hidden" value="${product.id}" name="productId">
    <div class="form-group">
         <label for="category" class="col-sm-2 control-label"><fmt:message key="category.name" bundle="${categoryMessages}"/></label>
         <div class="col-sm-6">
             <select name="category" id="category" required>
                <option value="0"><fmt:message key="category.select" bundle="${categoryMessages}"/></option>
             <c:forEach var="o" items="${categories}">
                <option value="${o.id}"><c:out value="${o.name}"/></option>
             </c:forEach>
             </select>
         </div>
    </div>
    <div class="form-group">
         <label for="product" class="col-sm-2 control-label"><fmt:message key="product.name" bundle="${productMessages}"/></label>
         <div class="col-sm-6">
            <select name="product" id="product" required>
            </select>
         </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" id="submitButton" class="btn btn-primary">
                <fmt:message key="product.relation.add.button" bundle="${productMessages}"/>
            </button>
        </div>
    </div>
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>