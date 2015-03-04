<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>


  <body>
    <div class="row">
      <div class="col-xs-6">
        <h2>
          <spring:message code="label.allUsers"/>
        </h2>
      </div>
      <div class="col-xs-6">
        <h3 class="pull-right">
          <a href="<c:url value="/admin/createUser" />">
            <spring:message code="label.newUser"/>
          </a>
        </h3>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-12">
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th class="col-xs-4">
                <spring:message code="label.username"/>
              </th>
              <th class="col-xs-4">
                <spring:message code="label.lastname"/>
              </th>
              <th class="col-xs-4">
                <spring:message code="label.firstname"/>
              </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}"
                       var="user">
              <tr class="clickable-row"
                  data-href="<c:url value="/profile/${user.username}/" />">
                <td class="col-xs-4">
                    ${user.username}
                </td>
                <td class="col-xs-4">
                    ${user.contact.lastName}
                </td>
                <td class="col-xs-4">
                    ${user.contact.firstName}
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>