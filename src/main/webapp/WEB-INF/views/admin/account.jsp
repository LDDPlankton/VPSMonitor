<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url value='/admin/account' var='postURL'/>

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
	<form:form method="POST" action="#" commandName="ViewModelAdminUpdateAccount" accept-charset="UTF-8" class="form-horizontal" role="form" id="form1">
		
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input path="email" class="form-control col-xs-4" placeholder="Enter Email Address" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="passWord" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<form:input path="passWord" class="form-control col-xs-4" placeholder="Enter Password" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="passWordConfirm" class="col-sm-2 control-label">Re-Enter Password</label>
			<div class="col-sm-10">
				<form:input path="passWordConfirm" class="form-control col-xs-4" placeholder="Enter Password Again" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="phoneNumber" class="col-sm-2 control-label">Phone Number</label>
			<div class="col-sm-10">
				<form:input path="phoneNumber" class="form-control col-xs-4" placeholder="Enter Phone Number" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Save Changes">
			</div>
		</div>

	</form:form>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>