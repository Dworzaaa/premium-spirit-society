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
                 action="/productPicture/create"
                 method="POST"
                 commandName="productPicture" enctype="multipart/form-data">


          <div class="form-group">
              <label class="col-xs-3 control-label"
                     for="name">
                  <spring:message code="label.username"/>*
              </label>

              <div class="col-xs-8">
                  <form:input id="name"
                              class="form-control input-sm"
                              type="text"
                              path="name"/>
                  <form:errors path="name"
                               cssClass="text-danger"/>
              </div>
          </div>


          <div class="form-group">
              <label class="col-xs-3 control-label"
                     for="hidden">
                  <spring:message code="label.username"/>*
              </label>

              <div class="col-xs-8">
                  <form:input id="name"
                              class="form-control input-sm"
                              type="text"
                              path="hidden"/>
                  <form:errors path="hidden"
                               cssClass="text-danger"/>
              </div>
          </div>

          <div class="form-group">
              <label class="col-xs-3 control-label"
                     for="productId">
                  <spring:message code="label.username"/>*
              </label>

              <div class="col-xs-8">
                  <form:input id="productId"
                              class="form-control input-sm"
                              type="text"
                              path="productId"/>
                  <form:errors path="productId"
                               cssClass="text-danger"/>
              </div>
          </div>

          <div class="form-group">
              <label class="col-xs-3 control-label"
                     for="picOrder">
                  <spring:message code="label.username"/>*
              </label>

              <div class="col-xs-8">
                  <form:input id="picOrder"
                              class="form-control input-sm"
                              type="text"
                              path="picOrder"/>
                  <form:errors path="picOrder"
                               cssClass="text-danger"/>
              </div>
          </div>


          <div class="form-group">
              <label class="col-xs-3 control-label"
                     for="picture">
                  <spring:message code="label.username"/>*
              </label>

              <div class="col-xs-8">
                  <form:input id="name"
                              class="form-control input-sm"
                              type="file" size="50"
                              path="picture"/>
                  <form:errors path="picture"
                               cssClass="text-danger"/>
              </div>
          </div>


          <img src="data:image/jpeg;base64,${productPicture.picture}" alt="..." width="200" height="200">`


          <div class="form-group">
              <div class="col-xs-offset-3 col-xs-8">
                  <button class="btn btn-small btn-primary"
                          type="submit">
                      <spring:message code="label.add"/>
                  </button>
              </div>
          </div>
      </form:form>
      <img src="data:image/jpeg;base64,${image}" alt="..." width="200" height="200">`
      <img src="data:image/jpeg;base64,${productPicture.picture}" width="200" height="200">`
  </jsp:body>

</t:menuLVL1>