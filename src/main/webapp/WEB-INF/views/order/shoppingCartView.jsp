<%--suppress ALL --%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>


    <link href="<c:url value="/resources/custom/css/loadingCircle.csss" />"
          rel="stylesheet">
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
$(".updateInput").keypress(function(event) {
 
    if (event.which == 13) {
        event.preventDefault();
         var elementId = event.target.id;
                $('#loading' + elementId).fadeIn(200);
                var searchSelector = $('#search' + elementId);
                var searchString = "";
                searchString = searchSelector.val();
                $(searchSelector).fadeOut(200);
                if (searchString < 1) {
                    $(searchSelector).fadeOut(200);
                    var searchString = "";

                    searchString = searchSelector.val();
                    var myUrl = 'orderChange/' + elementId + '/' + searchString;
                    $.ajax({
                        url: myUrl,
                        type: "POST",
                        success: function (data) {
                            $('#loading' + elementId).fadeOut(200);
                            $(searchSelector).fadeIn(200);
                            location.reload();
                        }
                    });

                } else {

                    var myUrl = 'orderChange/' + elementId + '/' + searchString;
                    $.ajax({
                        url: myUrl,
                        type: "POST",
                        success: function (data) {
                            $('#loading' + elementId).fadeOut(200);
                            $(searchSelector).fadeIn(200);
                        }
                    });
                }
    }
});
            $(":button").click(function (event) {
                var elementId = event.target.id;
                $('#loading' + elementId).fadeIn(200);
                var searchSelector = $('#search' + elementId);
                var searchString = "";
                searchString = searchSelector.val();
                $(searchSelector).fadeOut(200);
                if (searchString < 1) {
                    $(searchSelector).fadeOut(200);
                    var searchString = "";

                    searchString = searchSelector.val();
                    var myUrl = 'orderChange/' + elementId + '/' + searchString;
                    $.ajax({
                        url: myUrl,
                        type: "POST",
                        success: function (data) {
                            $('#loading' + elementId).fadeOut(200);
                            $(searchSelector).fadeIn(200);
                            location.reload();
                        }
                    });

                } else {

                    var myUrl = 'orderChange/' + elementId + '/' + searchString;
                    $.ajax({
                        url: myUrl,
                        type: "POST",
                        success: function (data) {
                            $('#loading' + elementId).fadeOut(200);
                            $(searchSelector).fadeIn(200);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<c:forEach var="picture"
           items="${pictureList}" varStatus="loop">
    <img src="data:image/jpeg;base64,${picture}"/> <br>
    <a href="${productWrappers[loop.index].productSubcategory.productCategory.url}/${productWrappers[loop.index].productSubcategory.url}/${productWrappers[loop.index].url}"
       class="glyphicon-ok-circle"> <c:out value="${productWrappers[loop.index].name}"/> </a>
    <br>

    <div id="loading<c:out value="${productWrappers[loop.index].id}"/>">
        <p><img src="<c:url value="/resources/custom/img/loader.gif" />"/></p>
        <script>
            $('#loading' + <c:out value="${productWrappers[loop.index].id}"/>).fadeOut(1);
        </script>
    </div>
    <input class="updateInput" id="search<c:out value="${productWrappers[loop.index].id}"/>" class="form-control" type="text"
           value="<c:out value="${productWrappers[loop.index].orderAmount}"/>"/> <br>

    <c:out value="${productWrappers[loop.index].price}"/><br>

    <input id="<c:out value="${productWrappers[loop.index].id}"/>" type="button" value="update"/>
    <br>

</c:forEach>
<form:form action="/finishOrder"
           method="POST" commandName="order">

    <br>
    <br>
    <br>


    <sec:authorize ifAnyGranted="ROLE_USER"
                   var="isAuthorized"/>
    <c:if test="${not isAuthorized}">
        UZIVATEL NENI PRIHLASEN!!!!
    </c:if>


    Faturacni udaje::<br>
    <spring:message code="label.name"/>: <form:input disabled="${'true'}" path="user.contact.firstName"
                                                     type="text"/><br>
    <form:errors path="user.contact.firstName" cssClass="text-danger"/>

    <spring:message code="label.lastname"/>: <form:input disabled="${'true'}" path="user.contact.lastName" type="text"/><br>
    <form:errors path="user.contact.lastName" cssClass="text-danger"/>

    <spring:message code="label.street"/>: <form:input disabled="${'true'}" path="user.contact.addressStreet"
                                                       type="text"/><br>
    <form:errors path="user.contact.addressStreet" cssClass="text-danger"/>

    <spring:message code="label.addressHn"/>: <form:input disabled="${'true'}" path="user.contact.addressHn"
                                                          type="text"/><br>
    <form:errors path="user.contact.addressHn" cssClass="text-danger"/>

    <spring:message code="label.addressCity"/>: <form:input disabled="${'true'}" path="user.contact.addressCity"
                                                            type="text"/><br>
    <form:errors path="user.contact.addressCity" cssClass="text-danger"/>

    <spring:message code="label.addressPostalcode"/>: <form:input disabled="${'true'}"
                                                                  path="user.contact.addressPostalcode"
                                                                  type="text"/><br>
    <form:errors path="user.contact.lastName" cssClass="text-danger"/>

    <spring:message code="label.addressCountry"/>: <form:input disabled="${'true'}" path="user.contact.addressCountry"
                                                               type="text"/><br>
    <form:errors path="user.contact.addressPostalcode" cssClass="text-danger"/>

    <br><br>


    Dorucovaci udaje:<br>

    <spring:message code="label.name"/>: <form:input path="user.contact.shippingFirstName" type="text"/><br>
    <spring:message code="label.lastname"/>: <form:input path="user.contact.shippingLastName" type="text"/><br>
    <spring:message code="label.street"/>: <form:input path="user.contact.shippingAddressStreet" type="text"/><br>
    <spring:message code="label.addressHn"/>: <form:input path="user.contact.shippingAddressHn" type="text"/><br>
    <spring:message code="label.addressCity"/>: <form:input path="user.contact.shippingAddressCity" type="text"/><br>
    <spring:message code="label.addressPostalcode"/>: <form:input path="user.contact.shippingAddressPostalcode"
                                                                  type="text"/><br>
    <spring:message code="label.addressCountry"/>: <form:input path="user.contact.shippingAddressCountry"
                                                               type="text"/><br>
    <br><br>
    Nazev firmy: <form:input path="user.contact.companyName" type="text"/><br>
    IC:<form:input path="user.contact.companyId" type="text"/><br>
    DIC:<form:input path="user.contact.vatId" type="text"/><br>
    <br><br>
    <form:radiobutton path="shippingType" value="DPD"/>DPD
    <form:radiobutton path="shippingType" value="DHL"/>DHL

    <br>

    <form:radiobutton path="paymentMethod" value="mastercard"/>mastercard
    <form:radiobutton path="paymentMethod" value="mastercard"/>bankovni prevod
    <form:radiobutton path="paymentMethod" value="paypal"/>paypal
    <form:radiobutton path="paymentMethod" value="dobirka"/>dobirka
    <br>
    <input type="submit" value="Odeslat objednavku"></input>
</form:form>


<form action='expresscheckout' METHOD='POST'>
    <input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' border='0'
           align='top' alt='Check out with PayPal'/>
</form>
</body>


</html>
