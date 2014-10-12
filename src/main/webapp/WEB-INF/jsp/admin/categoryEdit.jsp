<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">
<form action='<c:url value="categoryList.html"/>' method="POST" class="form-vertical">
<c:if test="${not empty category}">
     <input name="id" type="hidden" value="${category.id}">
     Name: <input name="name" value="${category.name}">
     Description: <input name="description" value="${category.description}">
     group: <input name="group" value="${category.group}">
     Order: <input name="order" value="${category.order}">
     <input type="submit" value="save">
</c:if>
<c:if test="${empty category}">
category not found
</c:if>
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>