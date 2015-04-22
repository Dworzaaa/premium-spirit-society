<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

    <jsp:attribute name="title">
      <spring:message code="label.cardIndex"/>
    </jsp:attribute>

	<jsp:attribute name="head">
        <link href="<c:url value="/resources/custom/css/clickable-row.css" />"
              rel="stylesheet">
    </jsp:attribute>

	<jsp:attribute name="script">
        <script src="<c:url value="/resources/custom/js/product-list.js"/>"></script>
    </jsp:attribute>


    <jsp:body>
        <sec:authorize ifAnyGranted="ROLE_DOCTOR,ROLE_SUPER_DOCTOR,ROLE_ADMIN"
                       var="isAuthorized"/>

        <div class="row">
            <div class="col-xs-6">
                <h2>
                    <spring:message code="label.cardIndex"/>
                </h2>
            </div>
            <div class="col-xs-6">
                <c:if test="${isAuthorized}">
                    <h3 class="pull-right">
                        <a href="<c:url value="/admin/createCustomer" />">
                            <spring:message code="label.addPatient"/>
                        </a>
                    </h3>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-2 control-label"
                           for="search">
                        <spring:message code="label.filter"/>:
                    </label>

                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon glyphicon glyphicon-search"></span>
                        <sec:authorize ifAllGranted="ROLE_USER,ROLE_RESEARCHER"
                                       ifNotGranted="ROLE_DOCTOR,ROLE_SUPER_DOCTOR,ROLE_ADMIN"
                                       var="researcherOnly"/>
                        <c:choose>
                            <c:when test="${researcherOnly}">
                                <input type="text" class="form-control" id="search"
                                       placeholder="<spring:message code="label.id"/>"
                                       data-max-results="${maxResult}">
                                %>
                            </c:when>
                            <c:otherwise>
                                <input id="search" class="form-control" type="text"
                                       placeholder="<spring:message code="label.lastname"/>/<spring:message code="label.firstname"/>/<spring:message code="label.nin"/>"
                                       data-max-results="${maxResult}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div class="text-center">
            <ul class="pagination">
                <li>
                    <a class="start" href="#">
                        &laquo;
                    </a>
                </li>
                <li>
                    <a class="prev" href="#">
                        &lsaquo;
                    </a>
                </li>
                <li class="next-li">
                    <a class="next" href="#">
                        &rsaquo;
                    </a>
                </li>
                <li>
                    <a class="end" href="#">
                        &raquo;
                    </a>
                </li>
            </ul>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <td>
                        <b>
                            <spring:message code="label.price"/>
                        </b>
                    </td>
                    <td>
                        <b>
                            <spring:message code="label.name"/>
                        </b>
                    </td>
                    <td>
                        <b>
                            <spring:message code="label.description"/>
                        </b>
                    </td>

                </tr>
                </thead>
                <tbody id="userList">

                </tbody>
            </table>
        </div>

        <div class="text-center">
            <ul class="pagination">
                <li>
                    <a class="start" href="#">
                        &laquo;
                    </a>
                </li>
                <li>
                    <a class="prev" href="#">
                        &lsaquo;
                    </a>
                </li>
                <li class="next-li">
                    <a class="next" href="#">
                        &rsaquo;
                    </a>
                </li>
                <li>
                    <a class="end" href="#">
                        &raquo;
                    </a>
                </li>
            </ul>
        </div>
        <c:forEach begin="0" end="${fn:length(categories)-1}" varStatus="loop">
            <c:out value="${categories[loop.index].name}"/>
            <img src="data:image/jpeg;base64,${categoryPictures[loop.index]}" width="200" height="200"/>


        </c:forEach>
        <br>
        <c:forEach begin="0" end="${fn:length(pictureList)-1}" varStatus="loop">

            <img src="data:image/jpeg;base64,${pictureList[loop.index]}" width="200" height="200"/>
            <img src="data:image/jpeg;base64,${secondPictureList[loop.index]}" width="200" height="200"/>

            <br>
            <a href="${products[loop.index].productSubcategory.productCategory.url}/${products[loop.index].productSubcategory.url}/${products[loop.index].url}"
               class="glyphicon-ok-circle">"${products[loop.index].name}"</a>
            <br>

            <c:out value="${products[loop.index].producer}"/>
            <br>
            Price:
            <c:out value="${products[loop.index].price}"/>
            <br>
        </c:forEach>
    </jsp:body>
</t:menuLVL1>