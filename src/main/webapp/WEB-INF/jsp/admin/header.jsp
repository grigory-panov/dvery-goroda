<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href='<c:url value="../icons/favicon.ico"/>'>
    <title>${title}</title>
    <!-- Bootstrap core CSS -->
    <link href='<c:url value="../css/bootstrap.min.css"/>' rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href='<c:url value="../css/sticky-footer-navbar.css"/>' rel="stylesheet">
    <script src='<c:url value="../js/jquery.min.js"/>'></script>
    <script src='<c:url value="../js/bootstrap.min.js"/>'></script>
    <script src='<c:url value="../js/admin/delete.js"/>'></script>
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
            <a class="navbar-brand" href='<c:url value="index.html"/>'>${global.project_name}-Admin</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="trashbin.html"><fmt:message key="menu.trashbin" bundle="${common}"/></a></li>
                <li><a href="infoList.html"><fmt:message key="menu.information" bundle="${common}"/></a></li>
                <li><a href="feedbackList.html"><fmt:message key="menu.feedback" bundle="${common}"/></a></li>
                <li><a href="settingsList.html"><fmt:message key="menu.settings" bundle="${common}"/></a></li>
                <li><a href="../logout"><fmt:message key="menu.logout" bundle="${common}"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->

    </div>
</div>