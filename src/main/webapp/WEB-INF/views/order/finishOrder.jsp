<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" %>


<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 23. 2. 2015
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Diky za objednavku<br>
volume: ${productFormWrapperBOs[0].volume}<br>
orderNumber: ${order.orderNumber}<br>

<form action='expresscheckout' METHOD='POST'>
    <input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' border='0'
           align='top' alt='Check out with PayPal'/>


    <br>
    <br>
    <br>


    <table border="1" align="center">
        <thead>

        <th align="center">id objednavky</th>
        <th align="center">datum objednavky</th>
        <th align="center">produkty</th>
        <th align="center">typ dopravy</th>
        <th align="center">cena dopravy</th>
        <th align="center">cena celkem</th>
        <th align="center">stav</th>
        <th align="center">faktura</th>
        </tr>
        </thead>
        <tbody>
        <tr>

            <td> ${order.orderNumber}</td>
            <td> ${order.date}</td>
            <!--<td>
                        <c:forEach var="product" items="${order.products}">
                            ${product.id}<br>
                            ${product.name}<br>
                            ${product.price}<br>
                        </c:forEach>
                    </td> -->
            <td>

                <c:forEach var="test" items="${productFormWrapperBOs}" varStatus="wrapperLoop">
                    jmeno produktu: <a                        href="/${productFormWrapperBOs[wrapperLoop.index].productSubcategory.productCategory.url}/${productFormWrapperBOs[wrapperLoop.index].productSubcategory.url}/${productFormWrapperBOs[wrapperLoop.index].url}">${productFormWrapperBOs[wrapperLoop.index].name}</a>
                    <br>
                    cena produktu:  ${productFormWrapperBOs[wrapperLoop.index].price} <br>
                    mnozstvi produktu: ${productFormWrapperBOs[wrapperLoop.index].orderAmount} <br>

                    ===============================================
                    <br>
                </c:forEach>
            </td>

            <td>
                ${order.shippingType}
            </td>
            <td>
                ${order.shippingPrice}
            </td>
            <td>
                spocitat js
            </td>
            <td><spring:message code="label.state.${order.state}"/></td>
            <td>
                <a class="navbar-brand"
                   href="<c:url value="/invoices/${order.userID}/${order.invoice}" />">
                    <img width="120" height="120" src="<c:url value="/resources/custom/img/pdf.ico" />">
                </a>

            </td>

        </tr>
        </tbody>

    </table>

</form>


</body>
</html>
