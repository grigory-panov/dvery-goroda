<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="container">

    <div class="bg-warning loading"><fmt:message key="loading.label" bundle="${common}"/></div>

    <div class="page-header">
        <h2></h2>
    </div>
    <p class="lead"></p>

    <div id="carousel-example-generic"  class="carousel slide" data-ride="carousel" data-interval="false">
        <!-- Indicators -->
        <ol class="carousel-indicators">
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
    <div id="related">
    </div>

</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/product.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>