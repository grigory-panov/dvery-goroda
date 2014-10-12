<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<fmt:setBundle basename="messages.info" var="info"/>
<fmt:setBundle basename="messages.products" var="products"/>

<div class="container">

<h3>Удаленные категории</h3>
<c:if test="${not empty categoriesTrash}">
    <table class="table">
         <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="table.restore" bundle="${common}"/></th>
         </tr>
         <c:forEach var="o" items="${categoriesTrash}">
            <tr>
                <td>${o.id}</td>
                <td>${o.name}</td>
                <td>${o.description}</td>
                <td><a href='<c:url value="/action/restore/category/${o.id}"/>' class="restore"><span class="glyphicon glyphicon-plus"></span></a></td>
            </tr>
         </c:forEach>
    </table>
</c:if>

<h3>Удаленные продукты</h3>
<c:if test="${not empty productsTrash}">
    <table class="table">
         <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="product.category.name" bundle="${products}"/></th>
            <th><fmt:message key="table.dateAdd" bundle="${common}"/></th>
            <th><fmt:message key="table.dateDelete" bundle="${common}"/></th>
            <th><fmt:message key="table.restore" bundle="${common}"/></th>
         </tr>
         <c:forEach var="o" items="${productsTrash}">
            <tr>
                <td>${o.id}</td>
                <td>${o.name}</td>
                <td>${o.description}</td>
                <td>${o.categoryId}</td>
                <td>${o.dateAdd}</td>
                <td>${o.dateDelete}</td>
                <td><a href='<c:url value="/action/restore/product/${o.id}"/>' class="restore"><span class="glyphicon glyphicon-plus"></span></a></td>
            </tr>
         </c:forEach>
    </table>
</c:if>

<h3>Удаленные версии продуктов</h3>
<c:if test="${not empty versionsTrash}">
    <table class="table">
         <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="table.name" bundle="${common}"/></th>
            <th><fmt:message key="table.description" bundle="${common}"/></th>
            <th><fmt:message key="product.name" bundle="${products}"/></th>
            <th><fmt:message key="table.dateAdd" bundle="${common}"/></th>
            <th><fmt:message key="table.dateDelete" bundle="${common}"/></th>
            <th><fmt:message key="table.restore" bundle="${common}"/></th>
         </tr>
         <c:forEach var="o" items="${versionsTrash}">
            <tr>
                <td>${o.id}</td>
                <td>${o.name}</td>
                <td>${o.description}</td>
                <td>${o.productId}</td>
                <td>${o.dateAdd}</td>
                <td>${o.dateDelete}</td>
                <td><a href='<c:url value="/action/restore/productVersion/${o.id}"/>' class="restore"><span class="glyphicon glyphicon-plus"></span></a></td>
            </tr>
         </c:forEach>
    </table>
</c:if>

<h3>Удаленные информационные статьи</h3>
<c:if test="${not empty infosTrash}">
    <table class="table">
        <tr>
            <th><fmt:message key="table.id" bundle="${common}"/></th>
            <th><fmt:message key="info.header.name" bundle="${info}"/></th>
            <th><fmt:message key="table.restore" bundle="${common}"/></th>
        </tr>
        <c:forEach var="o" items="${infosTrash}">
            <tr>
                <td>${o.id}</td>
                <td>${o.header}</td>
                <td><a href='<c:url value="/action/restore/info/${o.id}"/>' class="restore"><span class="glyphicon glyphicon-plus"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>

<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>