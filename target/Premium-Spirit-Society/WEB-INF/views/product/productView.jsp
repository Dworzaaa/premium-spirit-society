<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

    <jsp:attribute name="head">

    </jsp:attribute>
	<jsp:attribute name="title">
     ${product.name}
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

        <c:forEach begin="0" end="${fn:length(categories)-1}" varStatus="loop">
            <a href="/<c:out value="${categories[loop.index].url}"/>"><c:out
                    value="${categories[loop.index].name}"/></a>
            <img src="data:image/jpeg;base64,${categoryPictures[loop.index]}" width="200" height="200"/>


        </c:forEach>
        <br>


        <c:forEach begin="0" end="${fn:length(subcategories)-1}" varStatus="loop">
            <a href="/<c:out value="${subcategories[loop.index].productCategory.url}"/>/<c:out value="${subcategories[loop.index].url}"/>">
                <c:out value="${subcategories[loop.index].name}"/></a>
        </c:forEach>
        <br>

        <br>

<a href="/${categoryUrl}"}>${categoryName}</a>><a href="/${categoryUrl}/${subcategoryUrl}"}>${subcategoryName}</a>></a>><a href="/${categoryUrl}/${subcategoryUrl}/${product.url}"}>${product.name}</a>
        <form:form class="form-horizontal"
                   action="/order/addToCart/${product.id}"
                   method="POST"
                   commandName="product">

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="name">
                    <spring:message code="label.productName"/>
                </label>

                <div class="col-xs-8">
                    <form:input  disabled="${'true'}"  id="name"
                                class=" form-control input-sm"
                                type="text"
                                path="name"/>
                    <form:errors path="name"
                                 cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="description">
                    <spring:message code="label.productDescription"/>
                </label>

                <div class="col-xs-8">
                    <form:input  disabled="${'true'}"  id="description"
                                class=" form-control input-sm"
                                type="text"
                                path="description"/>
                    <form:errors path="description"
                                 cssClass="text-danger"/>
                </div>
            </div>
            <!-- zakaznikovi nezobrazovat
            <div class="form-group">
            <label class="col-xs-3 control-label"
            for="hidden">
            <spring:message code="label.email"/>
            </label>

            <div class="col-xs-8">
            <form:input  disabled="${'true'}"  id="hidden"
                        class="form-control input-sm"
                        path="hidden"/>
            <form:errors path="hidden"
                         cssClass="text-danger"/>

            </div>

            <div class="form-group">
            <label class="col-xs-3 control-label"
            for="productSubcategoryID">
            <spring:message code="label.email"/>
            </label>

            <div class="col-xs-8">
            <form:input  disabled="${'true'}"  id="productSubcategoryID"
                        class="form-control input-sm"
                        path="productSubcategoryID"/>
            <form:errors path="productSubcategoryID"
                         cssClass="text-danger"/>

            </div>


            <div class="form-group">
            <label class="col-xs-3 control-label"
            for="url">
            <spring:message code="label.username"/>
            </label>

            <div class="col-xs-8">
            <form:input  disabled="${'true'}"  id="url"
                        class="form-control input-sm"
                        type="text"
                        path="url"/>
            <form:errors path="url"
                         cssClass="text-danger"/>
            </div>
            </div>
            -->

            <div class="form-group">
            <!--
            <label class="col-xs-3 control-label"
            for="count">
            <spring:message code="label.countAvailable"/>


            </label>

            <div class="col-xs-8">
            <form:input  disabled="${'true'}"  id="ethanolVolume"
                        class="form-control input-sm"
                        type="text"
                        path="count"/>
            <form:errors path="count"
                         cssClass="text-danger"/>
            </div>
            </div>
            -->

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="price">
                    <spring:message code="label.price"/>
                </label>

                <div class="col-xs-8">
                    <form:input  disabled="${'true'}"  id="price"
                                class="form-control input-sm"
                                type="text"
                                path="price"/>
                    <form:errors path="price"
                                 cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="volume">
                    <spring:message code="label.volume"/>
                </label>

                <div class="col-xs-8">
                    <form:input  disabled="${'true'}"  id="volume"
                                class="form-control input-sm"
                                type="text"
                                path="volume"/>
                    <form:errors path="volume"
                                 cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="ethanolVolume">
                    <spring:message code="label.ethanolVolume"/>
                </label>

                <div class="col-xs-8">
                    <form:input  disabled="${'true'}"  id="ethanolVolume"
                                class="form-control input-sm"
                                type="text"
                                path="ethanolVolume"/>
                    <form:errors path="ethanolVolume"
                                 cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="ethanolVolume">
                    <spring:message code="label.countryOfOrigin"/>
                </label>

                <div class="col-xs-8">
                    <form:input  disabled="${'true'}"  id="countryOfOrigin"
                                class="form-control input-sm"
                                type="text"
                                path="countryOfOrigin"/>
                    <form:errors path="countryOfOrigin"
                                 cssClass="text-danger"/>
                </div>
            </div>


            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="orderAmount">
                    <spring:message code="label.producer"/>
                </label>

                <div class="col-xs-8">
                    <form:input id="orderAmount"
                                class="form-control input-sm"
                                type="text"
                                path="producer"/>
                    <form:errors path="producer"
                                 cssClass="text-danger"/>
                </div>
            </div>

            <br> <br>
            <br>

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="orderAmount">
                    <spring:message code="label.orderAmount"/>
                </label>

                <div class="col-xs-8">
                    <form:input id="orderAmount"
                                class="form-control input-sm"
                                type="text"
                                path="orderAmount"/>
                    <form:errors path="orderAmount"
                                 cssClass="text-danger"/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-8">
                    <button class="btn btn-small btn-primary"
                            type="submit">
                        Koupit vole
                    </button>
                </div>
            </div>

            <form:input type="hidden" id="id" path="id"/>

        </form:form>
        <c:forEach var="picture"
                   items="${pictureList}">
            <img src="data:image/jpeg;base64,${picture}"/>
        </c:forEach>




    </jsp:body>

</t:menuLVL1>