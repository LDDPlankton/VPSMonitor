<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url value='/admin/update-settings' var='postURL'/>

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
	<form:form method="POST" action="#" commandName="ViewModelAddMonitor" accept-charset="UTF-8" class="form-horizontal" role="form" id="form1">
		
		
		<h3>Version</h3>
		<div class="form-group">
			<label for="version" class="col-sm-2 control-label">Monitor URL</label>
			<div class="col-sm-10">
				<form:input path="version" class="btn btn-default disabled" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="version" class="col-sm-2 control-label">Version</label>
			<div class="col-sm-10">
				<input class="btn btn-default disabled" placeholder="" name="version" type="text" id="version" value="1.0.0">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Update">
			</div>
		</div>
	
	</form:form>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>