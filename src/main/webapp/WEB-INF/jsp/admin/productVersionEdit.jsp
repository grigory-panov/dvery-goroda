<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">
<form enctype="multipart/form-data" action="<c:url value='productVersionList.html?productId=${product.id}'/>" method="POST" class="form-vertical">
     <input type="hidden" name="id" value="${productVersion.id}">
     <input type="hidden" value="${product.id}" name="productId">
     Name: <input name="name" value="${productVersion.name}">
     Description: <input name="description" value="${productVersion.description}">
     price: <input name="price" value="${productVersion.price}">
     size: <input name="size" value="${productVersion.size}">
     order: <input name="order" value="${productVersion.order}">
     image: <input type="file" name="img">
     <input type="submit" value="save">
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>