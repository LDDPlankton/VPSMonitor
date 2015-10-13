<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<h2>Verify Phone Number</h2>
<div class="container">
	<div id="form-messages">
	</div>
	
	<span>Verification Code Sent to: @Html.DisplayFor(m => m.PhoneNumber) </span>
	<form:form method="POST" action="#" commandName="ViewModelAuthRegister" accept-charset="UTF-8" class="form-horizontal" role="form" id="form1">		
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Verification Code</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Verification Code" name="verifyCode" type="text" value="" id="verifyCode">
				The verification code should have been sent to your phone. Please wait 30-60s.
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Verify">
			</div>
		</div>	
	</form:form>
		
	<br />
	<h4>Need to change your phone number?.</h4>
	<hr />
 	<form:form method="POST" action="#" commandName="ViewModelAuthRegister" accept-charset="UTF-8" class="form-horizontal" role="form" id="form2">
		<div class="form-group">
			<label for="phoneCountryCodeName" class="col-sm-2 control-label">Phone Country Code:</label>
			<div class="col-sm-10">
				<form:select path="phoneCountryCode" class="col-xs-2">
					<form:options items="${SelectPhoneCountryCode}" />
				</form:select>
				<br />
				<form:errors path="phoneCountryCode" cssClass="css_error"/>
			</div>
		</div>
 	
		<div class="form-group">
			<label for="phoneNumber" class="col-sm-2 control-label">Verification Code</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Phone Number" name="phoneNumber" type="text" value="" id="phoneNumber">
	            Enter your Cell Phone Number.<br />
	            Note: <b>Phone must be able to receive text messages for SMS verification.</b><br />
	            Number must be entered without '-', '(', ')'.
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Change Number">
			</div>
		</div>
	</form:form>   
	
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>