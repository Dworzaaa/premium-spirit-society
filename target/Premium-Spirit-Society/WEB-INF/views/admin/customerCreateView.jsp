<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<body>
    <div class="row">
      <div class="col-xs-12">
        <h2>
          <spring:message code="label.addPatient"/>
        </h2>
      </div>
    </div>

    <form:form class="form-horizontal"
               action="/admin/createCustomer"
               method="POST"
               commandName="userFormBO">

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.lastName">
          <spring:message code="label.lastname"/>*
        </label>

        <div class="col-xs-8">
          <form:input id="contact.lastName"
                      class="form-control input-sm"
                      type="text"
                      path="contact.lastName"/>
          <form:errors path="contact.lastName"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.firstName">
          <spring:message code="label.firstname"/>*
        </label>

        <div class="col-xs-8">
          <form:input id="contact.firstName"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.firstName"/>
          <form:errors path="contact.firstName"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.addressStreet">
          <spring:message code="label.street"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.addressStreet"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.addressStreet"/>
          <form:errors path="contact.addressStreet"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.addressHn">
          <spring:message code="label.addressHn"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.addressHn"
                      class="form-control input-sm"
                      type="text"
                      path="contact.addressHn"/>
          <form:errors path="contact.addressHn" cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.addressCity">
          <spring:message code="label.addressCity"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.addressCity"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.addressCity"/>
          <form:errors path="contact.addressCity"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.addressPostalcode">
          <spring:message code="label.addressPostalcode"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.addressPostalcode"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.addressPostalcode"/>
          <form:errors path="contact.addressPostalcode"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.addressCountry">
          <spring:message code="label.addressCountry"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.addressCountry"
                      class=" form-control input-sm"
                      type="text"
                      list="countries"
                      path="contact.addressCountry"/>
          <form:errors path="contact.addressCountry"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.phoneNumber">
          <spring:message code="label.telephone"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.phoneNumber"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.phoneNumber"/>
          <form:errors path="contact.phoneNumber"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.email"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.email"/>
          <form:errors path="contact.email"
                       cssClass="text-danger"/>
        </div>

      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address street
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.shippingAddressStreet"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.shippingAddressStreet"/>
          <form:errors path="contact.shippingAddressStreet"
                       cssClass="text-danger"/>
        </div>
      </div>
      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address hn
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.shippingAddressHn"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.shippingAddressHn"/>
          <form:errors path="contact.shippingAddressHn"
                       cssClass="text-danger"/>
        </div>
      </div>
      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address city
          <spring:message code="label.addressCity"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.shippingAddressCity"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.shippingAddressCity"/>
          <form:errors path="contact.shippingAddressCity"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address postal
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.shippingAddressPostalCode"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.shippingAddressPostalCode"/>
          <form:errors path="contact.shippingAddressPostalCode"
                       cssClass="text-danger"/>
        </div>
      </div>
      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address country
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.shippingAddressCountry"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.shippingAddressCountry"/>
          <form:errors path="contact.shippingAddressCountry"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address comp name
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.companyName"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.companyName"/>
          <form:errors path="contact.companyName"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address comp id
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.companyId"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.companyId"/>
          <form:errors path="contact.companyId"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="col-xs-4 control-label"
               for="contact.email">
          ship address comp vatId
          <spring:message code="label.email"/>
        </label>

        <div class="col-xs-8">
          <form:input id="contact.vatId"
                      class=" form-control input-sm"
                      type="text"
                      path="contact.vatId"/>
          <form:errors path="contact.vatId"
                       cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <div class="col-xs-offset-4 col-xs-8">
          <button class="btn btn-small btn-primary"
                  type="submit">
                            <spring:message code="label.add"/>
          </button>
        </div>
      </div>
    </form:form></body>