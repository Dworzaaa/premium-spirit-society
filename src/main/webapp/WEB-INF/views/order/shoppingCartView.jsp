<%--suppress ALL --%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title></title>
</head>
<body>

<c:forEach var="picture"
           items="${pictureList}" varStatus="loop">
    <img src="data:image/jpeg;base64,${picture}"/> <br>
    <a href="${productWrappers[loop.index].productSubcategory.productCategory.url}/${productWrappers[loop.index].productSubcategory.url}/${productWrappers[loop.index].url}"
       class="glyphicon-ok-circle"> <c:out value="${productWrappers[loop.index].name}"/> </a>
    <br>
    <c:out value="${productWrappers[loop.index].amount}"/><br>
    <c:out value="${productWrappers[loop.index].price}"/><br>

</c:forEach>
<form:form action="/finishOrder"
           method="POST" commandName="order">

    <br>
    <br>
    <br>
    Faturacni udaje::<br>
    <spring:message code="label.name"/>: <form:input disabled="${'true'}" path="user.contact.firstName" type="text"/><br>
    <form:errors path="user.contact.firstName" cssClass="text-danger"/>

    <spring:message code="label.lastname"/>: <form:input disabled="${'true'}" path="user.contact.lastName" type="text"/><br>
    <form:errors path="user.contact.lastName" cssClass="text-danger"/>

    <spring:message code="label.street"/>: <form:input disabled="${'true'}" path="user.contact.addressStreet" type="text"/><br>
    <form:errors path="user.contact.addressStreet" cssClass="text-danger"/>

    <spring:message code="label.addressHn"/>: <form:input disabled="${'true'}" path="user.contact.addressHn" type="text"/><br>
    <form:errors path="user.contact.addressHn" cssClass="text-danger"/>

    <spring:message code="label.addressCity"/>: <form:input disabled="${'true'}" path="user.contact.addressCity" type="text"/><br>
    <form:errors path="user.contact.addressCity" cssClass="text-danger"/>

    <spring:message code="label.addressPostalcode"/>: <form:input disabled="${'true'}" path="user.contact.addressPostalcode"
                                                                  type="text"/><br>
    <form:errors path="user.contact.lastName" cssClass="text-danger"/>

    <spring:message code="label.addressCountry"/>: <form:input disabled="${'true'}" path="user.contact.addressCountry" type="text"/><br>
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

<sec:authorize ifAnyGranted="ROLE_USER"
               var="isAuthorized"/>
<c:if test="${not isAuthorized}">
    uzivatel neni prihlasen
</c:if>
<form action='expresscheckout' METHOD='POST'>
    <input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' border='0'
           align='top' alt='Check out with PayPal'/>
</form>

</body>
</html>
