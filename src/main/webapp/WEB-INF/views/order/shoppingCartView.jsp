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
    <c:out value="${productWrappers[loop.index].id}"/> <br>
    <a href="${productWrappers[loop.index].productSubcategory.productCategory.url}/${productWrappers[loop.index].productSubcategory.url}/${productWrappers[loop.index].url}"
       class="glyphicon-ok-circle"> <c:out value="${productWrappers[loop.index].name}"/> </a>
    <br>

    <c:out value="${productWrappers[loop.index].description}"/> <br>
    <c:out value="${productWrappers[loop.index].amount}"/><br>
    <c:out value="${productWrappers[loop.index].price}"/><br>

</c:forEach>
<form:form action="/order/finishOrder"
           method="POST" commandName="order">

    <form:radiobutton path="shippingType" value="DPD"/>DPD
    <form:radiobutton path="shippingType" value="DHL"/>DHL


    <form:radiobutton path="paymentMethod" value="mastercard"/>mastercard
    <form:radiobutton path="paymentMethod" value="paypal"/>paypal
    <form:radiobutton path="paymentMethod" value="dobirka"/>dobirka

    <input type="submit" value="Odeslat objednavku"></inpit>

</form:form>


<form action='expresscheckout' METHOD='POST'>
    <input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' border='0' align='top' alt='Check out with PayPal'/>
</form>

</body>
</html>
