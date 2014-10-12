<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">

<c:if test="${not empty message}">
<p class="text-info">${message}</p>
</c:if>

<a href='<c:url value="categoryAdd.html"/>'>Add new category</a>

<c:if test="${not empty categories}">
    <table class="table">

        <c:forEach var="o" items="${categories}">
            <tr>
                <td>${o.id}</td>
                <td><a href='<c:url value="categoryEdit.html?id=${o.id}"/>'>${o.name}</a></td>
                <td>${o.description}</td>
                <td>${o.group}</td>
                <td>${o.order}</td>
                <td><a href='<c:url value="productList.html?categoryId=${o.id}"/>'>Product List</a></td>
                <td><a href='<c:url value="/action/delete/category/${o.id}"/>' class="delete"><span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>
