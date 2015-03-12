<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

	<jsp:attribute name="head">
    </jsp:attribute>

	<jsp:attribute name="title">
      <spring:message code="label.profile"/>
    </jsp:attribute>

	<jsp:attribute name="script">
    </jsp:attribute>

    <jsp:body>

        <div class="row">
            <div class="col-xs-6">
                <h2>
                    <spring:message code="label.myProfile"/>
                </h2>
            </div>
            <div class="col-xs-3">
                <h3 class="pull-right">
                    <a href="<c:url value="/profile/edit" />">
                        <spring:message code="label.edit"/>
                    </a>
                </h3>
            </div>
            <div class="col-xs-3">
                <h3 class="pull-right">
                    <a href="<c:url value="/profile/change-password" />">
                        <spring:message code="label.changePassword"/>
                    </a>
                </h3>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <div class="row">
                    <div class="col-xs-12">
                        <h4><spring:message code="label.basicInfo"/></h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.userID"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">${user.id}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.username"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">${user.username}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.lastname"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">${user.contact.lastName}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.firstname"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">${user.contact.firstName}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.email"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">${user.contact.email}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.telephone"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">${user.contact.phoneNumber}</div>
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="row">
                    <div class="col-xs-12">
                        <h4><spring:message code="label.assignedRoles"/></h4>
                    </div>
                </div>
                <c:forEach items="${user.roles}"
                           var="role">
                    <div class="row">

                        <div class="col-xs-12">
                                ${role.authority}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </jsp:body>
</t:menuLVL1>