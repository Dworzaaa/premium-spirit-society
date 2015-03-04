<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:useBean id="begginningEpiOk"
             scope="request"
             type="java.lang.Boolean"/>

<jsp:useBean id="doctors"
             scope="request"
             type="java.util.List"/>

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
           for="birthday">
        <spring:message code="label.birthdate"/>*
    </label>

    <div class="col-xs-8">
        <div class='input-group date datepicker-simple'>
            <form:input id="birthday"
                        class="form-control input-sm"
                        type="text"
                        autocomplete="off"
                        path="birthday"/>
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
        <form:errors path="birthday"
                     cssClass="text-danger"/>
    </div>
</div>

<div class="form-group">
    <label class="col-xs-4 control-label"
           for="date">
        <spring:message code="label.beginningEpilepsy"/>
    </label>

    <div class="col-xs-8">
        <div class='input-group date datepicker-simple'>
            <form:input id="date"
                        class="input-sm form-control"
                        type="text"
                        autocomplete="off"
                        path="beginningEpilepsy"/>
                    <span class="input-group-addon">
                      <span class="glyphicon glyphicon-calendar"></span>
                    </span>
        </div>
        <form:errors path="beginningEpilepsy"
                     cssClass="text-danger"/>
        <c:if test="${not begginningEpiOk}">
            <br>
            <span id="date.errors"
                  class="text-danger">
                 <spring:message code="label.cannotBeAfterOldestSeizure"/>
            </span>
        </c:if>
    </div>
</div>

<div class="form-group">
    <label class="col-xs-4 control-label"
           for="nin">
        <spring:message code="label.idnumber"/>
    </label>

    <div class="col-xs-8">
        <form:input id="nin"
                    class=" form-control input-sm"
                    type="text"
                    path="nin"/>
        <form:errors path="nin"
                     cssClass="text-danger"/>
    </div>
</div>

<div class="form-group">
    <label class="col-xs-4 control-label">
        <spring:message code="label.gender"/>*
    </label>

    <div class="col-xs-8">

        <div class="radio">
            <label>
                <form:radiobutton value="1"
                                  checked="true"
                                  path="gender"/>
                <spring:message code="label.male"/>
            </label>
        </div>
        <div class="radio">
            <label>
                <form:radiobutton value="2"
                                  path="gender"/>
                <spring:message code="label.female"/>
            </label>
        </div>
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
    <label for="doctorId" class="col-xs-4 control-label">
        <spring:message code="label.doctor"/>*
    </label>

    <div class="col-xs-8">
        <c:choose>
            <c:when test="${!empty doctors}">
                <form:select id="doctorId"
                             class=" form-control input-sm"
                             path="doctorId">

                    <form:option value="0">
                        <spring:message code="label.chooseDoctor"/>
                    </form:option>

                    <c:forEach items="${doctors}"
                               var="doctor">
                        <form:option value="${doctor.id}">
                            ${doctor.contact.firstName} ${doctor.contact.lastName}
                        </form:option>
                    </c:forEach>

                </form:select>
                <form:errors path="doctorId"
                             cssClass="text-danger"/>
            </c:when>
            <c:otherwise>
                <form:select id="doctorId"
                             class=" form-control input-sm"
                             disabled="true"
                             path="doctorId">
                </form:select>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="form-group">
    <label class="col-xs-4 control-label"
           for="indicatingDoctor">
        <spring:message code="label.indicatingDoctor"/>
    </label>

    <div class="col-xs-8">
        <form:input id="indicatingDoctor"
                    class=" form-control input-sm"
                    type="text"
                    path="indicatingDoctor"/>
        <form:errors path="indicatingDoctor"
                     cssClass="text-danger"/>
    </div>
</div>

<datalist id="countries">
<option value="<spring:message code="country.AD" />"></option>
<option value="<spring:message code="country.AE" />"></option>
<option value="<spring:message code="country.AF" />"></option>
<option value="<spring:message code="country.AG" />"></option>
<option value="<spring:message code="country.AI" />"></option>
<option value="<spring:message code="country.AL" />"></option>
<option value="<spring:message code="country.AM" />"></option>
<option value="<spring:message code="country.AO" />"></option>
<option value="<spring:message code="country.AQ" />"></option>
<option value="<spring:message code="country.AR" />"></option>
<option value="<spring:message code="country.AS" />"></option>
<option value="<spring:message code="country.AT" />"></option>
<option value="<spring:message code="country.AU" />"></option>
<option value="<spring:message code="country.AW" />"></option>
<option value="<spring:message code="country.AX" />"></option>
<option value="<spring:message code="country.AZ" />"></option>
<option value="<spring:message code="country.BA" />"></option>
<option value="<spring:message code="country.BB" />"></option>
<option value="<spring:message code="country.BD" />"></option>
<option value="<spring:message code="country.BE" />"></option>
<option value="<spring:message code="country.BF" />"></option>
<option value="<spring:message code="country.BG" />"></option>
<option value="<spring:message code="country.BH" />"></option>
<option value="<spring:message code="country.BI" />"></option>
<option value="<spring:message code="country.BJ" />"></option>
<option value="<spring:message code="country.BL" />"></option>
<option value="<spring:message code="country.BM" />"></option>
<option value="<spring:message code="country.BN" />"></option>
<option value="<spring:message code="country.BO" />"></option>
<option value="<spring:message code="country.BQ" />"></option>
<option value="<spring:message code="country.BR" />"></option>
<option value="<spring:message code="country.BS" />"></option>
<option value="<spring:message code="country.BT" />"></option>
<option value="<spring:message code="country.BV" />"></option>
<option value="<spring:message code="country.BW" />"></option>
<option value="<spring:message code="country.BY" />"></option>
<option value="<spring:message code="country.BZ" />"></option>
<option value="<spring:message code="country.CA" />"></option>
<option value="<spring:message code="country.CC" />"></option>
<option value="<spring:message code="country.CD" />"></option>
<option value="<spring:message code="country.CF" />"></option>
<option value="<spring:message code="country.CG" />"></option>
<option value="<spring:message code="country.CI" />"></option>
<option value="<spring:message code="country.CK" />"></option>
<option value="<spring:message code="country.CL" />"></option>
<option value="<spring:message code="country.CM" />"></option>
<option value="<spring:message code="country.CN" />"></option>
<option value="<spring:message code="country.CO" />"></option>
<option value="<spring:message code="country.CR" />"></option>
<option value="<spring:message code="country.CU" />"></option>
<option value="<spring:message code="country.CV" />"></option>
<option value="<spring:message code="country.CW" />"></option>
<option value="<spring:message code="country.CX" />"></option>
<option value="<spring:message code="country.CY" />"></option>
<option value="<spring:message code="country.CZ" />"></option>
<option value="<spring:message code="country.DE" />"></option>
<option value="<spring:message code="country.DJ" />"></option>
<option value="<spring:message code="country.DK" />"></option>
<option value="<spring:message code="country.DM" />"></option>
<option value="<spring:message code="country.DO" />"></option>
<option value="<spring:message code="country.DZ" />"></option>
<option value="<spring:message code="country.EC" />"></option>
<option value="<spring:message code="country.EE" />"></option>
<option value="<spring:message code="country.EG" />"></option>
<option value="<spring:message code="country.EH" />"></option>
<option value="<spring:message code="country.ER" />"></option>
<option value="<spring:message code="country.ES" />"></option>
<option value="<spring:message code="country.ET" />"></option>
<option value="<spring:message code="country.FI" />"></option>
<option value="<spring:message code="country.FJ" />"></option>
<option value="<spring:message code="country.FK" />"></option>
<option value="<spring:message code="country.FM" />"></option>
<option value="<spring:message code="country.FO" />"></option>
<option value="<spring:message code="country.FR" />"></option>
<option value="<spring:message code="country.GA" />"></option>
<option value="<spring:message code="country.GB" />"></option>
<option value="<spring:message code="country.GD" />"></option>
<option value="<spring:message code="country.GE" />"></option>
<option value="<spring:message code="country.GF" />"></option>
<option value="<spring:message code="country.GG" />"></option>
<option value="<spring:message code="country.GH" />"></option>
<option value="<spring:message code="country.GI" />"></option>
<option value="<spring:message code="country.GL" />"></option>
<option value="<spring:message code="country.GM" />"></option>
<option value="<spring:message code="country.GN" />"></option>
<option value="<spring:message code="country.GP" />"></option>
<option value="<spring:message code="country.GQ" />"></option>
<option value="<spring:message code="country.GR" />"></option>
<option value="<spring:message code="country.GS" />"></option>
<option value="<spring:message code="country.GT" />"></option>
<option value="<spring:message code="country.GU" />"></option>
<option value="<spring:message code="country.GW" />"></option>
<option value="<spring:message code="country.GY" />"></option>
<option value="<spring:message code="country.HK" />"></option>
<option value="<spring:message code="country.HM" />"></option>
<option value="<spring:message code="country.HN" />"></option>
<option value="<spring:message code="country.HR" />"></option>
<option value="<spring:message code="country.HT" />"></option>
<option value="<spring:message code="country.HU" />"></option>
<option value="<spring:message code="country.CH" />"></option>
<option value="<spring:message code="country.ID" />"></option>
<option value="<spring:message code="country.IE" />"></option>
<option value="<spring:message code="country.IL" />"></option>
<option value="<spring:message code="country.IM" />"></option>
<option value="<spring:message code="country.IN" />"></option>
<option value="<spring:message code="country.IO" />"></option>
<option value="<spring:message code="country.IQ" />"></option>
<option value="<spring:message code="country.IR" />"></option>
<option value="<spring:message code="country.IS" />"></option>
<option value="<spring:message code="country.IT" />"></option>
<option value="<spring:message code="country.JE" />"></option>
<option value="<spring:message code="country.JM" />"></option>
<option value="<spring:message code="country.JO" />"></option>
<option value="<spring:message code="country.JP" />"></option>
<option value="<spring:message code="country.KE" />"></option>
<option value="<spring:message code="country.KG" />"></option>
<option value="<spring:message code="country.KH" />"></option>
<option value="<spring:message code="country.KI" />"></option>
<option value="<spring:message code="country.KM" />"></option>
<option value="<spring:message code="country.KN" />"></option>
<option value="<spring:message code="country.KP" />"></option>
<option value="<spring:message code="country.KR" />"></option>
<option value="<spring:message code="country.KW" />"></option>
<option value="<spring:message code="country.KY" />"></option>
<option value="<spring:message code="country.KZ" />"></option>
<option value="<spring:message code="country.LA" />"></option>
<option value="<spring:message code="country.LB" />"></option>
<option value="<spring:message code="country.LC" />"></option>
<option value="<spring:message code="country.LI" />"></option>
<option value="<spring:message code="country.LK" />"></option>
<option value="<spring:message code="country.LR" />"></option>
<option value="<spring:message code="country.LS" />"></option>
<option value="<spring:message code="country.LT" />"></option>
<option value="<spring:message code="country.LU" />"></option>
<option value="<spring:message code="country.LV" />"></option>
<option value="<spring:message code="country.LY" />"></option>
<option value="<spring:message code="country.MA" />"></option>
<option value="<spring:message code="country.MC" />"></option>
<option value="<spring:message code="country.MD" />"></option>
<option value="<spring:message code="country.ME" />"></option>
<option value="<spring:message code="country.MF" />"></option>
<option value="<spring:message code="country.MG" />"></option>
<option value="<spring:message code="country.MH" />"></option>
<option value="<spring:message code="country.MK" />"></option>
<option value="<spring:message code="country.ML" />"></option>
<option value="<spring:message code="country.MM" />"></option>
<option value="<spring:message code="country.MN" />"></option>
<option value="<spring:message code="country.MO" />"></option>
<option value="<spring:message code="country.MP" />"></option>
<option value="<spring:message code="country.MQ" />"></option>
<option value="<spring:message code="country.MR" />"></option>
<option value="<spring:message code="country.MS" />"></option>
<option value="<spring:message code="country.MT" />"></option>
<option value="<spring:message code="country.MU" />"></option>
<option value="<spring:message code="country.MV" />"></option>
<option value="<spring:message code="country.MW" />"></option>
<option value="<spring:message code="country.MX" />"></option>
<option value="<spring:message code="country.MY" />"></option>
<option value="<spring:message code="country.MZ" />"></option>
<option value="<spring:message code="country.NA" />"></option>
<option value="<spring:message code="country.NC" />"></option>
<option value="<spring:message code="country.NE" />"></option>
<option value="<spring:message code="country.NF" />"></option>
<option value="<spring:message code="country.NG" />"></option>
<option value="<spring:message code="country.NI" />"></option>
<option value="<spring:message code="country.NL" />"></option>
<option value="<spring:message code="country.NO" />"></option>
<option value="<spring:message code="country.NP" />"></option>
<option value="<spring:message code="country.NR" />"></option>
<option value="<spring:message code="country.NU" />"></option>
<option value="<spring:message code="country.NZ" />"></option>
<option value="<spring:message code="country.OM" />"></option>
<option value="<spring:message code="country.PA" />"></option>
<option value="<spring:message code="country.PE" />"></option>
<option value="<spring:message code="country.PF" />"></option>
<option value="<spring:message code="country.PG" />"></option>
<option value="<spring:message code="country.PH" />"></option>
<option value="<spring:message code="country.PK" />"></option>
<option value="<spring:message code="country.PL" />"></option>
<option value="<spring:message code="country.PM" />"></option>
<option value="<spring:message code="country.PN" />"></option>
<option value="<spring:message code="country.PR" />"></option>
<option value="<spring:message code="country.PS" />"></option>
<option value="<spring:message code="country.PT" />"></option>
<option value="<spring:message code="country.PW" />"></option>
<option value="<spring:message code="country.PY" />"></option>
<option value="<spring:message code="country.QA" />"></option>
<option value="<spring:message code="country.RE" />"></option>
<option value="<spring:message code="country.RO" />"></option>
<option value="<spring:message code="country.RS" />"></option>
<option value="<spring:message code="country.RU" />"></option>
<option value="<spring:message code="country.RW" />"></option>
<option value="<spring:message code="country.SA" />"></option>
<option value="<spring:message code="country.SB" />"></option>
<option value="<spring:message code="country.SC" />"></option>
<option value="<spring:message code="country.SD" />"></option>
<option value="<spring:message code="country.SE" />"></option>
<option value="<spring:message code="country.SG" />"></option>
<option value="<spring:message code="country.SH" />"></option>
<option value="<spring:message code="country.SI" />"></option>
<option value="<spring:message code="country.SJ" />"></option>
<option value="<spring:message code="country.SK" />"></option>
<option value="<spring:message code="country.SL" />"></option>
<option value="<spring:message code="country.SM" />"></option>
<option value="<spring:message code="country.SN" />"></option>
<option value="<spring:message code="country.SO" />"></option>
<option value="<spring:message code="country.SR" />"></option>
<option value="<spring:message code="country.SS" />"></option>
<option value="<spring:message code="country.ST" />"></option>
<option value="<spring:message code="country.SV" />"></option>
<option value="<spring:message code="country.SX" />"></option>
<option value="<spring:message code="country.SY" />"></option>
<option value="<spring:message code="country.SZ" />"></option>
<option value="<spring:message code="country.TC" />"></option>
<option value="<spring:message code="country.TD" />"></option>
<option value="<spring:message code="country.TF" />"></option>
<option value="<spring:message code="country.TG" />"></option>
<option value="<spring:message code="country.TH" />"></option>
<option value="<spring:message code="country.TJ" />"></option>
<option value="<spring:message code="country.TK" />"></option>
<option value="<spring:message code="country.TL" />"></option>
<option value="<spring:message code="country.TM" />"></option>
<option value="<spring:message code="country.TN" />"></option>
<option value="<spring:message code="country.TO" />"></option>
<option value="<spring:message code="country.TR" />"></option>
<option value="<spring:message code="country.TT" />"></option>
<option value="<spring:message code="country.TV" />"></option>
<option value="<spring:message code="country.TW" />"></option>
<option value="<spring:message code="country.TZ" />"></option>
<option value="<spring:message code="country.UA" />"></option>
<option value="<spring:message code="country.UG" />"></option>
<option value="<spring:message code="country.UM" />"></option>
<option value="<spring:message code="country.US" />"></option>
<option value="<spring:message code="country.UY" />"></option>
<option value="<spring:message code="country.UZ" />"></option>
<option value="<spring:message code="country.VA" />"></option>
<option value="<spring:message code="country.VC" />"></option>
<option value="<spring:message code="country.VE" />"></option>
<option value="<spring:message code="country.VG" />"></option>
<option value="<spring:message code="country.VI" />"></option>
<option value="<spring:message code="country.VN" />"></option>
<option value="<spring:message code="country.VU" />"></option>
<option value="<spring:message code="country.WF" />"></option>
<option value="<spring:message code="country.WS" />"></option>
<option value="<spring:message code="country.XK" />"></option>
<option value="<spring:message code="country.YE" />"></option>
<option value="<spring:message code="country.YT" />"></option>
<option value="<spring:message code="country.ZA" />"></option>
<option value="<spring:message code="country.ZM" />"></option>
<option value="<spring:message code="country.ZW" />"></option>
</datalist>