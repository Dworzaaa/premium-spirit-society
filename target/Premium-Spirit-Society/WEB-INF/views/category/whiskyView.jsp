<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Whisky</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/nivo-lightbox.css" rel="stylesheet"/>
    <link href="/resources/css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/animate.css" rel="stylesheet"/>
    <!-- Squad theme CSS -->
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/color/default.css" rel="stylesheet">

</head>
<body data-spy="scroll">
<div class="container">
    <ul id="gn-menu" class="gn-menu-main">
        <li class="gn-trigger">
            <a class="gn-icon gn-icon-menu"><span>Menu</span></a>
            <nav class="gn-menu-wrapper">
                <div class="gn-scroller">
                    <ul class="gn-menu">
                        <li class="gn-search-item">
                            <input placeholder="Search" type="search" class="gn-search">
                            <a class="gn-icon gn-icon-search"><span>Search</span></a>
                        </li>

                        <li><a href="whisky/all" class="gn-menu-item">All</a></li>
                        <li><a href="whisky/irish" class="gn-menu-item">Irish</a></li>
                        <li><a href="whisky/scotch" class="gn-menu-item">Scotch</a></li>
                        <li><a href="whisky/other" class="gn-menu-item">Other</a></li>
                    </ul>
                </div>
                <!-- /gn-scroller -->
            </nav>
        </li>
        <%@include file="/WEB-INF/jspf/menu.jspf" %>

    </ul>
</div>

<!-- Section: intro -->
<section id="whisky" class="whisky">
    <div class="slogan">
        <h1>Whisky</h1>

        <p>Is it whisky or whiskey?</p>
        <a href="whisky/all" class="btn btn-skin scroll">Learn more</a>
    </div>
</section>
<!-- /Section: intro -->


<%@include file="/WEB-INF/jspf/modal.jspf" %>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>

</html>
