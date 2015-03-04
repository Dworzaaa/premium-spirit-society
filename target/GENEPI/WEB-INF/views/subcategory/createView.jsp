<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

    <jsp:attribute name="head">
    </jsp:attribute>
	<jsp:attribute name="title">
      <spring:message code="label.addUser"/>
    </jsp:attribute>
	<jsp:attribute name="script">
    </jsp:attribute>

  <jsp:body>
    <div class="row">
      <div class="col-xs-12">
        <h2>
          <spring:message code="label.addSubcategory"/>
        </h2>
      </div>
    </div>

    <form:form class="form-horizontal"
               action="/subcategory/create"
               method="POST"
               commandName="subcategory">


      <div class="form-group">
        <label class="col-xs-3 control-label"
            >
          <spring:message code="label.subcategoryName"/>*
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
               for="description">
          <spring:message code="label.subcategoryDescription"/>*
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
          <spring:message code="label.subcategoryHidden"/>*
        </label>

        <div class="col-xs-8">
          <form:input id="hidden"
                      class=" form-control input-sm"
                      type="text"
                      path="hidden"/>
          <form:errors path="hidden"
                       cssClass="text-danger"/>
        </div>
      </div>

        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="productCategoryID">
                <spring:message code="label.category"/>*
            </label>

            <div class="col-xs-8">
                <form:input id="productCategoryID"
                            class=" form-control input-sm"
                            type="text"
                            path="productCategoryID"/>
                <form:errors path="productCategoryID"
                             cssClass="text-danger"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-3 control-label"
                   for="url">
                <spring:message code="label.subcategoryUrl"/>*
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
            <spring:message code="label.add"/>
          </button>
        </div>
      </div>
    </form:form>
  </jsp:body>
</t:menuLVL1>