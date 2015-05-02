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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script>

    $(document).ready(function(){
        /*! Fades out the whole page when clicking links */
        $('a').click(function(e) {
            e.preventDefault();
            newLocation = this.href;
            $('body').fadeOut(1000, newpage);
        });

        function newpage() {
            window.location = newLocation;
        }

        /*! Fades in whole page on load */
        $('body').css('display', 'none');
        $('body').fadeIn(1000);

    });

    /*! Reloads page on every visit */
    function Reload() {
        try {
            var headElement = document.getElementsByTagName("head")[0];
            if (headElement && headElement.innerHTML)
                headElement.innerHTML += "<meta http-equiv=\"refresh\" content=\"1\">";
        }
        catch (e) {}
    }


    /*! Reloads on every visit in mobile safari */
    if ((/iphone|ipod|ipad.*os 5/gi).test(navigator.appVersion)) {
        window.onpageshow = function(evt) {
            if (evt.persisted) {
                document.body.style.display = "none";
                location.reload();
            }
        };
    }
</script>

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

