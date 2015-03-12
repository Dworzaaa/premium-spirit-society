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

    <form:form class="form-horizontal"
               action="/subcategory/${subcategory.id}/edit"
               method="POST"
               commandName="subcategory">

      <div class="form-group">
        <label class="col-xs-3 control-label"
               for="name">
          <spring:message code="label.lastname"/>*
        </label>

        <div class="col-xs-8">
          <form:input id=".name"
                      class=" form-control input-sm"
                      type="text"
                      path="name"/>
          <form:errors path="name"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-3 control-label"
               for="contact.firstName">
          <spring:message code="label.firstname"/>*
        </label>

        <div class="col-xs-8">
          <form:input id="description"
                      class=" form-control input-sm"
                      type="text"
                      path="description"/>
          <form:errors path="description"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-3 control-label"
               for="hidden">
          <spring:message code="label.email"/>*
        </label>

        <div class="col-xs-8">
          <form:input id="hidden"
                      class="form-control input-sm"
                      path="hidden"/>
          <form:errors path="hidden"
                       cssClass="text-danger"/>

        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="userID">
                <spring:message code="label.firstname"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="userID"
                            class=" form-control input-sm"
                            type="text"
                            path="userID"/>
                <form:errors path="userID"
                             cssClass="text-danger"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="url">
                <spring:message code="label.username"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="url"
                            class="form-control input-sm"
                            type="text"
                            path="url"/>
                <form:errors path="url"
                             cssClass="text-danger"/>
            </div>
        </div>

      <div class="form-group">
        <div class="col-xs-offset-3 col-xs-8">
          <button class="btn btn-small btn-primary"
                  type="submit">
            <spring:message code="label.save"/>
          </button>
        </div>
      </div>
    </form:form>
  </jsp:body>
</t:menuLVL1>