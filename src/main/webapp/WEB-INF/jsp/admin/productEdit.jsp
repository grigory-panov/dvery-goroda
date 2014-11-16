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
            <input type="text" class="form-control" id="description" name="description"
             value="<c:out value='${product.description}'/>" tabindex="2"
             placeholder='<fmt:message key="product.description.placeholder" bundle="${productMessages}"/>' >
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

<hr>

<a href='<c:url value="productRelationAdd.html?productId=${product.id}"/>' class="btn btn-primary"><fmt:message key="product.relation.add.link" bundle="${productMessages}"/></a>

<c:if test="${not empty productRelations}">

    <table class="table">
        <tr>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${productRelations}">
            <tr>
                <td><c:out value="${o.name}"/></td>
                <td><c:out value="${o.description}"/></td>
                <td><a href='<c:url value="/action/delete/relation/${product.id}/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty productRelations}">
    <p><fmt:message key="product.relation.list.empty" bundle="${productMessages}"/></p>
</c:if>

<hr>
<a href='<c:url value="productVersionAdd.html?productId=${product.id}"/>' class="btn btn-primary"><fmt:message key="version.add.link" bundle="${productMessages}"/></a>

<c:if test="${not empty productVersions}">
    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="version.price.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="version.size.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="version.order.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="version.file.name" bundle="${productMessages}"/></th>
            <th><fmt:message key="table.delete" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${productVersions}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="productVersionEdit.html?id=${o.id}"/>'><c:out value="${o.name}"/></a></td>
                <td><c:out value="${o.description}"/></td>
                <td><c:out value="${o.price}"/></td>
                <td><c:out value="${o.size}"/></td>
                <td><c:out value="${o.order}"/></td>
                <td>
                    <a href='<c:url value="/image/version/${o.productId}/${o.id}"/>'>
                        <img src='<c:url value="/image/thumbnail/${o.productId}/${o.id}"/>'
                        class="img-thumbnail" alt="X">
                    <a>
                </td>
                <td><a href='<c:url value="/action/delete/productVersion/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty productVersions}">
    <p><fmt:message key="versions.list.empty" bundle="${productMessages}"/></p>
</c:if>




</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>