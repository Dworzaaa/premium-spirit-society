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
                    <spring:message code="label.editCategory"/>
                </h2>
            </div>
        </div>

        <form:form class="form-horizontal"
                   action="/category/create"
                   method="POST"
                   commandName="category">


            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="name">
                    <spring:message code="label.categoryName"/>*
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
                    <spring:message code="label.categoryDescription"/>*
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
                    <spring:message code="label.categoryHidden"/>*
                </label>

                <div class="col-xs-8">
                    <form:checkbox  id="hidden"
                                    class=" form-control input-sm"

                                    path="hidden"/>
                    <form:errors path="hidden"
                                 cssClass="text-danger"/>
                </div>
            </div>


            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="url">
                    <spring:message code="label.categoryUrl"/>*
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