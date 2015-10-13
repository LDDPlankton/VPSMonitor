<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<c:url value='register' var='postURL'/>
<script type="text/javascript">
$(document).ready(function()
{
	$('#form1').submit(function()
	{
		var result = form_post("${postURL}", $('#form1').serialize(), '#', true );
		return false;
	});
});
</script>

<div class="container">
	<div id="form-messages">
	</div>

	<div class="well">
		<form:form method="POST" action="#" commandName="ViewModelAuthRegister" accept-charset="UTF-8" class="form-horizontal" role="form" id="form1">
		
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Email Address" name="email" type="text" id="email">
			</div>
		</div>
		
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Password" name="passWord" type="password" value="" id="password">
			</div>
		</div>
		
		<div class="form-group">
			<label for="password_confirmation" class="col-sm-2 control-label">Password Confirmation</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Password Confirmation" name="passWordConfirm" type="password" value="" id="passwordc">
			</div>
		</div>
		
		<div class="form-group">
			<label for="phoneCountryCode" class="col-sm-2 control-label">Phone Country Code:</label>
			<div class="col-sm-10">
				<form:select path="phoneCountryCode" class="col-xs-4">
				<c:forEach items="${SelectPhoneCountryCode}" var="item" varStatus="status">
					<option value="${item.value}">${item.key}</option>
				</c:forEach>
				</form:select>
				<br />
				<form:errors path="phoneCountryCode" cssClass="css_error"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="phone_number" class="col-sm-2 control-label">Phone Number</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Phone Number" name="phoneNumber" type="text" value="" id="phoneNumber">
				Enter Cell Phone Number.<br />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Register">
			</div>
		</div>
	
		</form:form>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>