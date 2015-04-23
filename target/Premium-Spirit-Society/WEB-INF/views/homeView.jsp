<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My ubercool eshop!!!:)</title>

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

<div class="container">


    <c:forEach begin="0" end="${fn:length(pictureList)-1}" varStatus="loop">

        <img src="data:image/jpeg;base64,${pictureList[loop.index]}" alt="..."/>
        <br>
        <a href="${urlList[loop.index]}"> ${urlList[loop.index]} </a>
        <c:out value="${promotionTextList[loop.index]}" />       <br>
        <c:out value="${promotionHeaderList[loop.index]}" />       <br>
    </c:forEach>




    <ul id="gn-menu" class="gn-menu-main">

        <%@include file="/WEB-INF/jspf/menu.jspf" %>
    </ul>
</div>

<%@include file="/WEB-INF/jspf/modal.jspf" %>
</body>

</html>

