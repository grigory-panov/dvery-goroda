<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.products" var="productMessages"/>

<div class="container">
<form action="<c:url value='productList.html?categoryId=${category.id}'/>" class="form-horizontal"  method="POST" role="form">
    <input type="hidden" name="id" value="${product.id}">
    <div class="form-group">
         <label for="name" class="col-sm-2 control-label"><fmt:message key="table.name" bundle="${common}"/></label>
         <div class="col-xs-5">
            <input type="text" class="form-control"  id="name" name="name" autofocus required value="<c:out value='${product.name}'/>"
             placeholder='<fmt:message key="product.name.placeholder" bundle="${productMessages}"/>' tabindex="1">
         </div>
    </div>
    <div class="form-group">
         <label for="description" class="col-sm-2 control-label"><fmt:message key="table.description" bundle="${common}"/></label>
         <div class="col-sm-10">
            <input type="text" class="form-control" id="description" name="description" value="${product.description}"
             placeholder='<fmt:message key="product.description.placeholder" bundle="${productMessages}"/>' tabindex="2">
         </div>
    </div>

    <div class="form-group">
         <label for="newCategoryId" class="col-sm-2 control-label"><fmt:message key="product.category.name" bundle="${productMessages}"/></label>
         <div class="col-xs-5">
             <select name="newCategoryId" tabindex="3" class="form-control">
                    <c:forEach var="o" items="${categories}">
                     <c:if test="${o.id eq product.categoryId}">
                        <option value="${o.id}" selected>${o.name}</option>
                     </c:if>
                     <c:if test="${o.id ne product.categoryId}">
                        <option value="${o.id}">${o.name}</option>
                     </c:if>
                    </c:forEach>
                 </select>
         </div>
    </div>
        </div>
            <div class="form-group">
                 <label for="order" class="col-sm-2 control-label"><fmt:message key="product.order.name" bundle="${productMessages}"/></label>
                 <div class="col-xs-5">
                    <input type="text" class="form-control"  id="order" name="order" autofocus required value="${product.order}"
                     placeholder='<fmt:message key="product.order.placeholder" bundle="${productMessages}"/>' tabindex="5">
                 </div>
            </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" id="submitButton" class="btn btn-primary" tabindex="6">
                <fmt:message key="product.save.button" bundle="${productMessages}"/>
            </button>
        </div>
    </div>
</form>

</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>