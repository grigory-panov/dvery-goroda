<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container" id="container">
    <div class="page-header">
            <h2></h2>
        </div>
        <form class="form-inline">
            <div class="form-group">
                Ширина
                <select id="size">
                    <option value="166">50 см</option>
                    <option value="200">60 см</option>
                    <option value="233" selected="selected">70 см</option>
                    <option value="266">80 см</option>
                    <option value="300">90 см</option>
                </select>

                <select id="model" class="img-select">
                    <option value="112" data-img-src="image/product/122" selected="selected">Вега</option>
                    <option value="2" data-img-src="image/product/2">Гамма</option>
                </select>

                <div class="color_box bleached selected_color">Беленый</div>
                <div class="color_box venge">Венге</div>
                <div class="color_box valnut">Орех</div>
                <div class="color_box pear">Груша</div>
                <div class="color_box coffee">Кофе</div>
                <div class="color_box sandal">Сандал</div>
                <div class="color_box white">Белый</div>
                <div class="color_box black">Черный</div>
            </div>
            <div class="form-group">
                <a href="#" id="wallColor">Выберите цвет стен</a>
                <input type="hidden" id="wallColorInput">
            </div>
        </form>
        <p class="lead"></p>
        <div id="room" >
            <div id="content" style="height:666px; display:inline-block; float:none;">

            </div>
        </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/svg.js"/>'></script>
<script src='<c:url value="js/chosen.jquery.min.js"/>'></script>
<script src='<c:url value="js/image-select.jquery.js"/>'></script>
<script src='<c:url value="js/constructor.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
<script src='<c:url value="js/bootstrap-colorpicker.min.js"/>'></script>
</body>
</html>