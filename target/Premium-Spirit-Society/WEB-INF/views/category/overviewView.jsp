<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

	<jsp:attribute name="title">
      <spring:message code="label.category"/>
    </jsp:attribute>

  <jsp:body>

    <div class="row">
      <div class="col-xs-2">
        <h3 class="pull-right">
          <a href="<c:url value="/category/${category.id}/edit" />">
            <spring:message code="label.edit"/>
          </a>
        </h3>
      </div>
      <!-- if hidden, then restore instead -->
      <div class="col-xs-2">
        <h3 class="pull-right">
          <a href="#delete-category-${hidden}"
             data-toggle="modal">
            <spring:message code="label.delete"/>
          </a>
        </h3>
      </div>
    </div>


    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.id"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${category.id}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.categoryName"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${category.name}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.categoryDescription"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${category.description}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.categoryHidden"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${category.hidden}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.categoryUrl"/>
      </div>
      <div class="col-xs-6">
        <div class="label label-info">
            ${category.url}
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6">
        <spring:message code="label.subcategories"/>
      </div>
      <div class="col-xs-6">
        <c:forEach var="subcategory"
                   items="${category.productSubcategoriesList}">
          <div class="label label-info">
           <a href="/subcategory/${subcategory.id}">  ${subcategory.name} </a>
          </div>
        </c:forEach>

      </div>
    </div>
    <jsp:include page="../components/deleteModalComponentView.jsp">
      <jsp:param name="modalId"
                 value="delete-category-${category.name}"/>
      <jsp:param name="bodyMessage"
                 value="reallyDeleteUser"/>
      <jsp:param name="deleteUrl"
                 value="/category/${category.id}/hide"/>
    </jsp:include>

  </jsp:body>
</t:menuLVL1>