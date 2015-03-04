<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

	<jsp:attribute name="title">
      <spring:message code="label.user"/>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-xs-3">
                <h2>
                    <spring:message code="label.user"/>
                </h2>
            </div>
            <div class="col-xs-2">
                <h3 class="pull-right">
                    <a href="<c:url value="/user/${user.username}/edit" />">
                        <spring:message code="label.edit"/>
                    </a>
                </h3>
            </div>
            <div class="col-xs-2">
                <h3 class="pull-right">
                    <a href="<c:url value="/user/${user.id}/edit-roles" />">
                        <spring:message code="label.editRoles"/>
                    </a>
                </h3>
            </div>
            <div class="col-xs-2">
                <h3 class="pull-right">
                    <a href="<c:url value="/user/${user.id}/change-password" />">
                        <spring:message code="label.changePassword"/>
                    </a>
                </h3>
            </div>
            <div class="col-xs-2">
                <h3 class="pull-right">
                    <a href="#delete-user-${user.id}"
                       data-toggle="modal">
                        <spring:message code="label.delete"/>
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
                        <div class="label label-info">
                                ${user.id}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.username"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">
                                ${user.username}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.lastname"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">
                                ${user.contact.lastName}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.firstname"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">
                                ${user.contact.firstName}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.email"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">
                                ${user.contact.email}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <spring:message code="label.telephone"/>
                    </div>
                    <div class="col-xs-6">
                        <div class="label label-info">
                                ${user.contact.phoneNumber}
                        </div>
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

        <jsp:include page="../components/deleteModalComponentView.jsp">
            <jsp:param name="modalId"
                       value="delete-user-${user.id}"/>
            <jsp:param name="bodyMessage"
                       value="reallyDeleteUser"/>
            <jsp:param name="deleteUrl"
                       value="/user/${user.id}/hide"/>
        </jsp:include>

    </jsp:body>
</t:menuLVL1>