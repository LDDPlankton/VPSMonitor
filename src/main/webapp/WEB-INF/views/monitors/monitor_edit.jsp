<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url value='/admin/edit-monitor/${ViewModelAddMonitor.monitorID}' var='postURL'/>

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
	
		<h3>Assigned VPS</h3>
		<div class="form-group">
			<label for="vpsID" class="col-sm-2 control-label">Assigned VPS</label>
			<div class="col-sm-10">
				<form:select path="vpsID">
				<form:options items="${VPSListMap}" />
				</form:select>
			</div>
		</div>
	
		<h3>URL Monitor</h3>
		<div class="form-group">
			<label for="monitorIPOrUrl" class="col-sm-2 control-label">Monitor URL</label>
			<div class="col-sm-10">
				<form:input path="monitorIPOrUrl" class="form-control col-xs-4" />
			</div>
		</div>

		<h3>Monitor Port</h3>
		<div class="form-group">
			<label for="monitorPort" class="col-sm-2 control-label">Monitor Port</label>
			<div class="col-sm-10">
				<form:input path="monitorPort" class="form-control col-xs-4" />
				<form:errors path="monitorPort" cssClass="css_error"/>
			</div>
		</div>
		
		<h3>Monitor Timeout</h3>
		<div class="form-group">
		<label for="monitorTimeout" class="col-sm-2 control-label">Monitor Timeout</label>
		<div class="col-sm-10">
			<form:input path="monitorTimeout" class="form-control col-xs-4" />
			<form:errors path="monitorTimeout" cssClass="css_error"/>
		</div>
		</div>
		
		<h3>Check for Content String</h3>
		<div class="form-group">
			<label for="isRequireValidContent" class="col-sm-2 control-label">Check for Content String</label>
			<form:radiobutton path="isRequireValidContent" value="1" label="Yes" />
			<form:radiobutton path="isRequireValidContent" value="0" label="No" />
		</div>

		<h3>Monitor Content</h3>
		<div class="form-group">
			<label for="validContent" class="col-sm-2 control-label">Monitor Content</label>
			<div class="col-sm-10">
				<form:input path="validContent" class="form-control col-xs-4" />
				Monitor URL for Content String
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Add Monitor">
			</div>
		</div>

	</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>