<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url value='/admin/add-ip' var='postURL'/>

<script type="text/javascript">
$(document).ready(function()
{
	$('#form1').submit(function()
	{
		var result = form_post("${postURL}", $('#form1').serialize(), '/admin/ips', false );
		return false;
	});
});
</script>

<div class="container">

	<div id="form-messages">
	</div>

	<div class="well">
	<form:form method="POST" action="#" commandName="ViewModelAddIP" accept-charset="UTF-8" class="form-horizontal" role="form" id="form1">
	
		<h3>Assigned VPS</h3>
		<div class="form-group">
			<label for="vpsID" class="col-sm-2 control-label">Assigned VPS</label>
			<div class="col-xs-7">
				<form:select path="vpsID">
				<form:options items="${VPSListMap}" />
				</form:select>
			</div>
		</div>
	
		<h3>IP Address</h3>
		<div class="form-group">
			<label for="IPAddress" class="col-sm-2 control-label">IP Address</label>
			<div class="col-xs-4">
				<form:input path="IPAddress" class="form-control col-xs-4" />
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Add IP">
			</div>
		</div>

	</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>