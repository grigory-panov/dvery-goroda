<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">
<form enctype="multipart/form-data" action="<c:url value='productVersionList.html?productId=${product.id}'/>" method="POST" class="form-vertical">
     Name: <input name="name" >
     Description: <input name="description">
     price: <input name="price" >
     size: <input name="size" >
     order: <input name="order" >
     image: <input type="file" name="img">
     <input type="hidden" value="${product.id}" name="productId">
     <input type="submit" value="add">
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>