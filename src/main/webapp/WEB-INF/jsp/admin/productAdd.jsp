<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">
<form action="<c:url value='productList.html?categoryId=${category.id}'/>" method="POST" class="form-vertical">
     Name: <input name="name" >
     Description: <input name="description">
     category: <select name="categoryId">
        <c:forEach var="o" items="${categories}">
         <c:if test="${o.id eq category.id}">
            <option value="${o.id}" selected>${o.name}</option>
         </c:if>
         <c:if test="${o.id ne category.id}">
            <option value="${o.id}">${o.name}</option>
         </c:if>
        </c:forEach>
     </select>
     <input type="submit" value="add">
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>