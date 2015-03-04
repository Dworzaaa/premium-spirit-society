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
<c:forEach var="product"
           items="${order.products}">
    <a href="${product.productSubcategory.productCategory.url}/${product.productSubcategory.url}/${product.url}"
       class="glyphicon-ok-circle">"${product.productSubcategory.productCategory.url}/${product.productSubcategory.url}/${product.url}"</a>
    <br>

</c:forEach>

<br><br><br><br><br><br><br><br><br><br>
<c:forEach var="picture"
           items="${pictureList}" varStatus="loop">
    <img src="data:image/jpeg;base64,${picture}"/> <br>
    <c:out value="${productWrappers[loop.index].id}"/> <br>
    <a href="${productWrappers[loop.index].productSubcategory.productCategory.url}/${productWrappers[loop.index].productSubcategory.url}/${productWrappers[loop.index].url}"
       class="glyphicon-ok-circle">    <c:out value="${productWrappers[loop.index].name}"/> </a>
    <br>

    <c:out value="${productWrappers[loop.index].description}"/> <br>
    <c:out value="${productWrappers[loop.index].amount}"/><br>
    <c:out value="${productWrappers[loop.index].price}"/><br>

</c:forEach>
<form:form action="/order/finishOrder"
           method="POST" commandName="order">

    <form:radiobutton path="shippingType" value="DPD"/>DPD
    <form:radiobutton path="shippingType" value="POST"/>POST
    <form:radiobutton path="shippingType" value="DHL"/>DHL


    <form:radiobutton path="paymentMethod" value="mastercard"/>DPD
    <form:radiobutton path="paymentMethod" value="paypal"/>POST
    <form:radiobutton path="paymentMethod" value="dobirka"/>DHL

    <input type="submit" value="Odeslat objednavku"></inpit>

</form:form>

</body>
</html>
