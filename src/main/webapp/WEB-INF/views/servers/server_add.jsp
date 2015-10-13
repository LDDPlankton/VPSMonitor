<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url value='/admin/add-server' var='postURL'/>

<script type="text/javascript">
$(document).ready(function()
{
	$('#form1').submit(function()
	{
		var result = form_post("${postURL}", $('#form1').serialize(), '/admin/servers', false );
		return false;
	});
});
</script>

<div class="container">

	<div id="form-messages">
	</div>

	<div class="well">
	<form:form method="POST" action="#" commandName="ViewModelAddServer" accept-charset="UTF-8" class="form-horizontal" role="form" id="form1">

		<h3>Basic Information</h3>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">VPS Name</label>
			<div class="col-xs-4">
				<form:input path="name" class="form-control col-xs-4" />
			</div>
		</div>

		<div class="form-group">
			<label for="primaryIP" class="col-sm-2 control-label">VPS Primary IP</label>
			<div class="col-xs-4">
				<form:input path="primaryIP" class="form-control col-xs-4" />
			</div>
		</div>

		<div class="form-group">
			<label for="status" class="col-sm-2 control-label">VPS Active</label>
			<div class="col-sm-10">
				<form:select path="status">
				<form:options items="${VPSStatusMap}" />
				</form:select>
			</div>
		</div>
		
		<h3>SSH Wheel User</h3>
		<div class="form-group">
			<label for="sshIsNormalUserRequired" class="col-sm-2 control-label">Require Wheel User</label>
			<div class="col-xs-7">
			<form:radiobutton path="sshIsNormalUserRequired" value="1" label="Yes" />
			<form:radiobutton path="sshIsNormalUserRequired" value="0" label="No" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="sshNormalUser" class="col-sm-2 control-label">SSH Wheel User</label>
			<div class="col-xs-4">
				<form:input path="sshNormalUser" class="form-control col-xs-4" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="sshNormalPass" class="col-sm-2 control-label">SSH Wheel User Pass</label>
			<div class="col-xs-4">
				<form:input path="sshNormalPass" class="form-control col-xs-4" />
			</div>
		</div>
		
		<h3>SSH Root Password Information</h3>
		<div class="form-group">
			<label for="sshIsRootKeyRequired" class="col-sm-2 control-label">Use SSH Root Key?</label>
			<div class="col-xs-7">
			<form:radiobutton path="sshIsRootKeyRequired" value="1" label="Yes" />
			<form:radiobutton path="sshIsRootKeyRequired" value="0" label="No" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="sshRootPassword" class="col-sm-2 control-label">SSH Root Password</label>
			<div class="col-xs-4">
				<form:input path="sshRootPassword" class="form-control col-xs-4" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="sshRootKey" class="col-sm-2 control-label">SSH Root Private Key</label>
			<div class="col-sm-10">
				<form:textarea path="sshRootKey" rows="10" class="form-control col-xs-4" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Add VPS">
			</div>
		</div>
	
	</form:form>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>