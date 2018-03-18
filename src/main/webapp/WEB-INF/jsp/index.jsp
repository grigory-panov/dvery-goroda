<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<fmt:setBundle basename="messages.index" var="index"/>

<div class="jumbotron main container">
    <div class="container">
        <h2><fmt:message key="index.header" bundle="${index}"/></h2>

        <br/>
        <br/>
        <p>
            тел. ${global.contacts_phone}
        </p>
        <p>
            ${global.contacts_address}
        </p>
        <p><a class="btn btn-primary btn-lg" href="category.html" role="button">Перейти в каталог &raquo;</a></p>
      </div>
    </div>
</div>

<div class="container">
      <p>
          У нас большой выбор деревянных межкомнатных дверей <a href="category.html?category=2">из массива сосны</a>, с подетальным окрашиванием,
          покрытых качественным полиуретановым лаком,
          есть варианты с матовым стеклом с гравировкой или наливным или пленочным витражом.
          Возможно браширование, нанесение патины.
      </p>
      <p>
          Также мы предлагаем <a href="category.html?category=6">шпонированные</a> двери, покрытые натуральным шпоном ценных пород дерева,
          с пескоструйным стеклом и фьюзингом.
          Можно выбрать в качестве стекла <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B8%D0%BF%D0%BB%D0%B5%D0%BA%D1%81">триплекс</a> - особо прочное многослойное стекло -
          различных цветов: молочно-матовый, бронза,
            черный, зеркало, либо с нанесенным методом фотопечати рисунком.
          В продаже имеются также <a href="category.html?category=6">каркасно-щитовые</a> двери с ПВХ покрытием, выполненные методом полного окутывания и
          <a href="category.html?category=8">царговые</a> двери, покрытые экошпоном по методу бескромочного окутывания,
          <a href="category.html?category=16">фурнитура</a>
      </p>
      <p>
          Мы изготовим в кратчайшие срооки любую из предложенных моделей, есть возмоность изготовления нестандартных размеров.
          Воспользуйтесь <a href="constructor.html">конструктором</a>, чтобы получить представление, как будет выглядеть ваша новая дверь.
       </p>
       <p>
       На всю продукцию предоставляется гарантия, возможна оплата по безналичному расчету или в рассрочку.
        Если вы не хотите никуда ходить - можно заключить договор на дому по каталогам с образцами цветов.
       </p>
      <div class="row">
        <div class="col-md-4">
          <h2>Предлагаемые услуги</h2>
          <p>
          <ul>
          <li>
          Продажа готовых дверей из наличия в магазине
          </li>
          <li>
          Изготовление межкомнатных дверей на заказ, возможны нестандартные размеры
           </li>
           <li>
           Двери для бань под ваш размер
           </li>
           <li>
           Установка межкомнатных дверей
           </li>
           </li>
           Консультации, замеры, доставка и подъем на этаж - бесплатно.
           </li>
           </ul>
          Звоните по телефону ${global.contacts_phone} или приходите в магазин, наши опытные консультанты помогут вам.</p>
        </div>
        <div class="col-md-4">
          <h2>Двери из массива сосны</h2>
          <div class="thumbnail">
          <a href="product.html?id=${global.default_product}"><img src="image/product/${global.default_product}"></a>
          </div>
          <p><a class="btn btn-default" href="category.html?category=2" role="button">Перейти в каталог &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h2>Конструктор</h2>
          <p>Вы можете посмотреть, как выглядит дверь в вашем интерьере, подобрать модели, размеры и цвета</p>
          <p><a class="btn btn-default" href="constructor.html" role="button">Перейти в конструкор &raquo;</a></p>
        </div>

    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script src='<c:url value="js/jquery.min.js"/>'></script>
<script src='<c:url value="js/bootstrap.min.js"/>'></script>
</body>
</html>