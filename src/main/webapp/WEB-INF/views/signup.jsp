<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="label.title"/></title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/nivo-lightbox.css" rel="stylesheet"/>
    <link href="/resources/css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/animate.css" rel="stylesheet"/>
    <!-- Squad theme CSS -->
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/color/default.css" rel="stylesheet">
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body data-spy="scroll">

<div class="container">
    <ul id="gn-menu" class="gn-menu-main">
        <li class="gn-trigger">
            <a class="gn-icon gn-icon-menu"><span>Menu</span></a>
            <nav class="gn-menu-wrapper">
                <div class="gn-scroller">
                    <ul class="gn-menu">
                        <li class="gn-search-item">
                            <input placeholder="Search" type="search" class="gn-search">
                            <a class="gn-icon gn-icon-search"><span>Search</span></a>
                        </li>
                    </ul>
                </div>
                <!-- /gn-scroller -->
            </nav>
        </li>
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
    </ul>
</div>

<!-- Section: intro -->
<section id="intro" class="intro">
    <div class="slogan">
        <p class="intro-text">

        <h1><spring:message code="label.addUser"/></h1>


        <form:form class="form-horizontal"
                   action="/signup"
                   method="POST"
                   commandName="user">


            <div class="form-group">
                <c:set var="usernamePlaceholder">
                    <spring:message code="label.username"/>
                </c:set>
                <div class="col-xs-8">
                    <form:input id="username"
                                class="form-control input-sm"
                                type="text"
                                path="username"
                                placeholder="${usernamePlaceholder}"/>
                    <form:errors path="username"
                                 cssClass="text-danger"/>
                    <c:if test="${not uniqueUsername}">
                        <span class="text-danger">
                            <spring:message code="label.nonUnique"/>
                        </span>
                    </c:if>
                </div>
                </span>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
                       for="contact.lastName">
                    <spring:message code="label.lastname"/>*
                </label>

                <div class="col-xs-8">
                    <form:input id="contact.lastName"
                                class=" form-control input-sm"
                                type="text"
                                path="contact.lastName"/>
                    <form:errors path="contact.lastName"
                                 cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
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
                <label class="col-xs-3 control-label"
                       for="contact.email">
                    <spring:message code="label.email"/>*
                </label>

                <div class="col-xs-8">
                    <form:input id="contact.email"
                                class="form-control input-sm"
                                type="email"
                                path="contact.email"/>
                    <form:errors path="contact.email"
                                 cssClass="text-danger"/>
                    <c:if test="${not uniqueEmail}">
                        <span class="text-danger">
                            <spring:message code="label.nonUnique"/>
                        </span>
                    </c:if>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 control-label"
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
            <br>
            <br>
            <br>

            <div class="form-group">
                <label class="col-xs-4 control-label"
                       for="contact.shippingFirstName">
                    shipping first name
                    <spring:message code="label.email"/>
                </label>

                <div class="col-xs-8">
                    <form:input id="contact.shippingFirstName"
                                class=" form-control input-sm"
                                type="text"
                                path="contact.shippingFirstName"/>
                    <form:errors path="contact.shippingFirstName"
                                 cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-4 control-label"
                       for="contact.shippingLastName">
                    shipping last name
                    <spring:message code="label.email"/>
                </label>

                <div class="col-xs-8">
                    <form:input id="contact.shippingLastName"
                                class=" form-control input-sm"
                                type="text"
                                path="contact.shippingLastName"/>
                    <form:errors path="contact.shippingLastName"
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
                <label class="col-xs-4 control-label"
                       for="contact.email">
                    Odebirat novinky:
                </label>

                <div class="col-xs-8">
                    <form:checkbox id="newsletter"
                                   checked="true"
                                   class=" form-control input-sm"
                                   path="newsletter"/>
                </div>
            </div>

            <div class="g-recaptcha" data-sitekey="6Lc5kwoTAAAAAHb0mwSRZEkyly5BfUtB_2uRyQbY"></div>

            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-8">
                    <button class="btn btn-skin scroll">
                        <spring:message code="label.add"/>
                    </button>
                </div>
            </div>

        </form:form>
        </p>
    </div>
</section>


<!-- /Section: contact -->
<%@include file="/WEB-INF/jspf/modal.jspf" %>

<section>

    <%@include file="/WEB-INF/jspf/footer.jspf" %>

</section>

</body>

</html>

