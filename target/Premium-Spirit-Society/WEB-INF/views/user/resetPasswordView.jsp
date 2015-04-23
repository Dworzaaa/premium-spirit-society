<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 22. 4. 2015
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>


<form:form class="form-horizontal"
           action="/reset-password"
           method="POST"
           commandName="contact">

  <div class="form-group">
    <label class="col-xs-3 control-label"
           for="email">
      <spring:message code="label.email"/>*
    </label>

    <div class="col-xs-8">
      <form:input id="email"
                  class=" form-control input-sm"
                  type="text"
                  path="email"/>
      <form:errors path="email"
                   cssClass="text-danger"/>
    </div>
  </div>


  <div class="form-group">
    <div class="col-xs-offset-3 col-xs-8">
      <button class="btn btn-small btn-primary"
              type="submit">
       Reset password
      </button>
    </div>
  </div>


</form:form>

</body>
</html>
