<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${global.description}">
    <meta name="author" content="${global.author}">
    <link rel="icon" href='<c:url value="icons/favicon.ico"/>'>

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href='<c:url value="css/bootstrap.min.css"/>' rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href='<c:url value="css/sticky-footer-navbar.css"/>' rel="stylesheet">

</head>

<body>

<!-- Fixed navbar -->
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only"><fmt:message key="menu.navigation.toggle" bundle="${common}"/><br/></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href='<c:url value="index.html"/>'>${global.project_name}</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="menu.products" bundle="${common}"/>
                    <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <c:forEach var="i" items="${categories}">
                        <li><a href='<c:url value="index.html?category=${i.id}"/>'><c:out value="${i.name}"/></a></li>
                        </c:forEach>
                        <!--<li class="divider"></li>
                        <li class="dropdown-header"></li>-->
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="menu.information" bundle="${common}"/>
                    <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                    <li><a href="info.html"><fmt:message key="menu.information.articles" bundle="${common}"/></a></li>
                    <li><a href="partners.html"><fmt:message key="menu.information.partners" bundle="${common}"/></a></li>
                    </ul>
                </li>
                <li><a href="feedback.html"><fmt:message key="menu.feedback" bundle="${common}"/></a></li>
                <li><a href="contacts.html"><fmt:message key="menu.contacts" bundle="${common}"/></a></li>

            </ul>
        </div><!--/.nav-collapse -->

    </div>
</div>