<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

    <jsp:attribute name="head">

    </jsp:attribute>
	<jsp:attribute name="title">
      <spring:message code="label.editUser"/>
    </jsp:attribute>

	<jsp:attribute name="script">

    </jsp:attribute>

    <jsp:body>

        <h2>
            <spring:message code="label.editUser"/>
            <c:choose>
                <c:when test="${isAdmin}">
                    <a href="<c:url value="/user/${user.username}" />">${user.username}</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/profile" />">${user.username}</a>
                </c:otherwise>
            </c:choose>
        </h2>

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

            <c:forEach var="order"
                       items="${orders}" varStatus="loop">
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

                        <c:forEach var="test" items="${listOfProductFormWrappers[loop.index]}" varStatus="wrapperLoop">
                            jmeno produktu: <a
                                href="/${listOfProductFormWrappers[loop.index][wrapperLoop.index].productSubcategory.productCategory.url}/${listOfProductFormWrappers[loop.index][wrapperLoop.index].productSubcategory.url}/${listOfProductFormWrappers[loop.index][wrapperLoop.index].url}">${listOfProductFormWrappers[loop.index][wrapperLoop.index].name}</a>
                            <br>
                            cena produktu:  ${listOfProductFormWrappers[loop.index][wrapperLoop.index].price} <br>
                            mnozstvi produktu: ${listOfProductFormWrappers[loop.index][wrapperLoop.index].amount} <br>

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
                            ${listOfTotalPrices[loop.index]}
                    </td>



                    <c:choose>
                        <c:when test="${order.state =='0'}">
                            <td>
                                <spring:message code="label.state.0"/><br>
                            </td>
                        </c:when>
                        <c:when test="${order.state =='1'}">
                            <td>
                                <spring:message code="label.state.1"/><br>
                                <a class="navbar-brand"
                                   href="<c:url value="/invoices/${order.userID}/payorder/${order.orderNumber}" />">
                                    <spring:message code="label.state.performPayment"/><br>
                                </a>
                            </td>
                        </c:when>
                        <c:when test="${order.state =='2'}">
                            <td>
                                <spring:message code="label.state.2"/>
                            </a>
                            </td>
                        </c:when>
                        <c:when test="${order.state =='3'}">
                            <td>
                                <spring:message code="label.state.3"/>
                            </td>
                        </c:when>
                        <c:when test="${order.state =='4'}">
                        <td>
                            <spring:message code="label.state.4"/>
                        </td>
                            <c:when test="${order.state =='5'}">
                                <td>
                                    <spring:message code="label.state.5"/>
                                </td>
                            </c:when>
                    </c:when>
                    </c:choose>

                    <td>
                        <a class="navbar-brand"
                           href="<c:url value="/invoices/${order.userID}/${order.invoice}" />">
                            <img width="120" height="120" src="<c:url value="/resources/custom/img/pdf.ico" />">
                        </a>

                    </td>

                </tr>
            </c:forEach>

            </tbody>

        </table>
    </jsp:body>
</t:menuLVL1>