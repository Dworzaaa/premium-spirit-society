<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>

<%-- Taglib section --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%-- Attribute section --%>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="menuLVL1" fragment="true" %>
<%@ attribute name="script" fragment="true" %>

<%-- Template section --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="description" content="">
    <meta name="author" content="GENEPI team">

    <%-- Hook for filling title of page --%>
    <title>
        <jsp:invoke fragment="title"/>
    </title>

    <link rel="icon" type="image/png" href="<c:url value="/resources/custom/img/logoIcon.ico" />">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/custom/css/menu.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/custom/css/custom.css" />" rel="stylesheet">

    <%-- Hook for adding something to HEAD --%>
    <jsp:invoke fragment="head"/>

</head>
<body>
<%-- Navbar section --%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button class="navbar-toggle" type="button"
                    data-toggle="collapse"
                    data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="<c:url value="/" />">
                <img src="<c:url value="/resources/custom/img/logo.ico" />">GENEPI
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>

                </li>
                <li>
                    <a href="<c:url value="/j_spring_security_logout"/>">
                        <span class="glyphicon glyphicon-off"></span>
                        <spring:message code="label.logOut"/>
                    </a>
                </li>
                <li>
                    <a href="?lang=cs">
                        CZ
                    </a>
                </li>
                <li>
                    <a href="?lang=en">
                        EN
                    </a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<!-- /nav-->

<%-- Body section --%>
<div class="container">
    <div class="row">
        <button id="menu-button"
                class="btn btn-default btn-lg btn-block visible-xs"
                type="button">
            <spring:message code="label.menuContent"/>
        </button>
        <br>
        <%-- Menu section --%>
        <div id="menu"
             class="hide-content col-sm-4 col-md-4 col-lg-3"
             role="navigation">
            <%-- Menu hook --%>
            <jsp:invoke fragment="menuLVL1"/>
        </div>

        <%-- Content section --%>
        <div id="content"
             class="col-sm-8 col-md-8 col-lg-9">
            <%-- Hook for body --%>
            <jsp:doBody/>
        </div>
    </div>

    <%-- Footer section --%>
    <hr>
    <footer>
        <p>
            &copy; GENEPI 2013
        </p>
    </footer>

</div>

<%-- Script section --%>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/custom/js/menu.js"/>"></script>

<%-- Hook for adding something to Script section --%>
<jsp:invoke fragment="script"/>
</body>
</html>