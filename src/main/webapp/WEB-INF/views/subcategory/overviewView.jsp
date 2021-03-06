<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
          <a href="<c:url value="/subcategory/${subcategory.id}/edit" />">
            <spring:message code="label.edit"/>
          </a>
        </h3>
      </div>

      <div class="col-xs-2">
        <h3 class="pull-right">
          <a href="<c:url value="/user/${subcategory.name}/edit-roles" />">
            <spring:message code="label.editRoles"/>
          </a>
        </h3>
      </div>
      <div class="col-xs-2">
        <h3 class="pull-right">
          <a href="<c:url value="/user/${subcategory.description}/change-password" />">
            <spring:message code="label.changePassword"/>
          </a>
        </h3>
      </div>
      <div class="col-xs-2">
        <h3 class="pull-right">
          <a href="#delete-subcategory-${hidden}"
             data-toggle="modal">
            <spring:message code="label.delete"/>
          </a>
        </h3>
      </div>
    </div>


    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.username"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${subcategory.id}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.lastname"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${subcategory.name}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.firstname"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${subcategory.description}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.email"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${subcategory.hidden}
        </div>
      </div>
    </div>
    <jsp:include page="../components/deleteModalComponentView.jsp">
      <jsp:param name="modalId"
                 value="delete-subcategory-${subcategory.name}"/>
      <jsp:param name="bodyMessage"
                 value="reallyDeleteUser"/>
      <jsp:param name="deleteUrl"
                 value="/subcategory/${subcategory.id}/hide"/>
    </jsp:include>

  </jsp:body>
</t:menuLVL1>