<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script language="javascript">
var RECORD_PER_PAGE = ${global.products_per_page};
</script>
<div class="container" id="container">
    <div class="bg-warning loading"><fmt:message key="loading.label" bundle="${common}"/></div>
    <div class="page-header">
        <h2></h2>
    </div>
    <p class="lead"></p>

    <div id="products">

    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/index.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>