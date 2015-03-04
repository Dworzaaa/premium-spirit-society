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
            <th align="center">stav</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="order"
                       items="${orders}">
                <tr>

                    <td> ${order.id}</td>
                    <td> ${order.date}</td>
                    <td>
                        <c:forEach var="product" items="${order.products}">
                            ${product.id}<br>
                            ${product.name}<br>
                            ${product.price}<br>
                        </c:forEach>
                    </td>
                    <td> ${order.state}</td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </jsp:body>
</t:menuLVL1>