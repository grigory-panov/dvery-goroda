<%@ include file="/WEB-INF/jsp/admin/include.jsp" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="container">
<form action="<c:url value='categoryList.html'/>" method="POST" class="form-vertical">
     Name: <input name="name" >
     Description: <input name="description">
     group: <input name="group">
     Order: <input name="order">
     <input type="submit" value="add">
</form>
</div>
<%@ include file="/WEB-INF/jsp/admin/footer.jsp" %>