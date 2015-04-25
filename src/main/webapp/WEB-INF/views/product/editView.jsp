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
               action="/product/${product.id}/edit"
               method="POST"
               commandName="product">

      <div class="form-group">
        <label class="col-xs-3 control-label"
               for="name">
          <spring:message code="label.productName"/>*
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
          <label>
          <spring:message code="label.description"/>*
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
          <spring:message code="label.hidden"/>*
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
               for="productSubcategoryID">
            <spring:message code="label.productSubcategoryID"/>*
        </label>

        <div class="col-xs-8">
            <form:input id="productSubcategoryID"
                        class="form-control input-sm"
                        path="productSubcategoryID"/>
            <form:errors path="productSubcategoryID"
                         cssClass="text-danger"/>

        </div>


        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="url">
                <spring:message code="label.url"/>*
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
            <label class="col-xs-3 control-label"
                   for="count">
                <spring:message code="label.count"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="ethanolVolume"
                            class="form-control input-sm"
                            type="text"
                            path="count"/>
                <form:errors path="count"
                             cssClass="text-danger"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="price">
                <spring:message code="label.price"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="price"
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
                <spring:message code="label.volume"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="volume"
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
                <spring:message code="label.ethanolVolume"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="ethanolVolume"
                            class="form-control input-sm"
                            type="text"
                            path="ethanolVolume"/>
                <form:errors path="ethanolVolume"
                             cssClass="text-danger"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="volume">
                <spring:message code="label.isPromoted"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="volume"
                            class="form-control input-sm"
                            type="text"
                            path="promoted"/>
                <form:errors path="promoted"
                             cssClass="text-danger"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="volume">
                <spring:message code="label.preference"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="volume"
                            class="form-control input-sm"
                            type="text"
                            path="preference"/>
                <form:errors path="preference"
                             cssClass="text-danger"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="volume">
                <spring:message code="label.promotionText"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="volume"
                            class="form-control input-sm"
                            type="text"
                            path="promotionText"/>
                <form:errors path="promotionText"
                             cssClass="text-danger"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="volume">
                <spring:message code="label.countryOfOrigin"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="countryOfOrigin"
                            class="form-control input-sm"
                            type="text"
                            path="countryOfOrigin"/>
                <form:errors path="countryOfOrigin"
                             cssClass="text-danger"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="volume">
                <spring:message code="label.producer"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="producer"
                            class="form-control input-sm"
                            type="text"
                            path="producer"/>
                <form:errors path="producer"
                             cssClass="text-danger"/>
            </div>
        </div>

        <c:forEach var="picture"
                   items="${pictureList}">
            <img src="data:image/jpeg;base64,${picture}" alt="..."/>
        </c:forEach>


      <div class="form-group">
        <div class="col-xs-offset-3 col-xs-8">
          <button class="btn btn-small btn-primary"
                  type="submit">
            <spring:message code="label.save"/>
          </button>
        </div>
      </div>

        <form:input type="hidden" id="id" path="id"/>

    </form:form>
  </jsp:body>
</t:menuLVL1>