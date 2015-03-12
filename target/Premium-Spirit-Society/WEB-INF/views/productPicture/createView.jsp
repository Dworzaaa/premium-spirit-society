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
          <spring:message code="label.addUser"/>
        </h2>
      </div>
    </div>

      <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>File Upload Example in JSP and Servlet - Java web application</title>
      </head>

      <body>
      <div>
          <h3> Choose File to Upload in Server </h3>
          <form action="upload" method="post" enctype="multipart/form-data">
              <input type="file" name="file" />
              <input type="submit" value="upload" />
          </form>
      </div>

      </body>

  </jsp:body>
</t:menuLVL1>