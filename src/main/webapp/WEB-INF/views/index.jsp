<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<c:url value='posttest' var='postURL'/>
<script type="text/javascript">
$(document).ready(function()
{
	$('#server_add').submit(function()
	{
		var result = form_post("${postURL}", $('#server_add').serialize(), '#', true );
		return false;
	});
});
</script>

		<c:if test="${!empty userList}">
			USER LIST IS NOT EMPTY!
		</c:if>
	
        
<style>
.css_error
{
font-weight: bold;
}

.textarea-fixed-height
{
	width: 50%;
	height: 180px !important;
	resize: none;
}
</style>

<div class="container">
	<div id="form-messages">
	</div>
	
	
	<c:choose>
			
		<c:when test="${ListUserPasteCount == 0}">
					<tr>
						<td colspan='4'>You do not have any IP's to display!</td>
					</tr>
		</c:when>
			
		<c:otherwise>
			<div class="table-responsive">
				<table class="table">
				<tbody>
				<tr>
				
				<c:forEach items="${ListUserPaste}" var="item" varStatus="status">
					<td>${item.pasteText}</td>
				
					<c:if test="${status.count % 2 == 0}">
						<tr>
							
						</tr>
					</c:if>

				</c:forEach>
			
				</tr>
				</tbody>
				</table>
			</div>
		</c:otherwise>
			
	</c:choose>
	
	
	<div class="well">
	<form:form method="POST" action="#" commandName="ViewModelAddPaste" accept-charset="UTF-8" class="form-horizontal" role="form" id="server_add">
	<h3>New Paste Settings</h3>
	
	<spring:bind path="pasteText">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<label for="pasteText" class="col-sm-2 control-label">New Paste:</label>
		<br />
		<form:textarea path="pasteText" class="form-control textarea-fixed-height"></form:textarea>
		<br />
		<form:errors path="pasteText" cssClass="css_error"/>
	</div>
	</spring:bind>
	
	<h3>Optional Paste Settings</h3>
	<spring:bind path="pasteExpiration">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<label for="pasteExpiration" class="col-sm-2 control-label">Paste Expiration:</label>
		<div class="col-sm-10">
			<form:select path="pasteExpiration" class="col-xs-2">
				<form:options items="${SelectPasteExpiration}" />
			</form:select>
			<br />
			<form:errors path="pasteExpiration" cssClass="css_error"/>
		</div>
	</div>
    </spring:bind>
    
    <spring:bind path="pasteExposure">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<label for="pasteExposure" class="col-sm-2 control-label">Paste Exposure:</label>
		<div class="col-sm-10">
			<form:select path="pasteExposure" class="col-xs-2">
				<form:options items="${SelectPasteExposure}" />
			</form:select>
			<br />
			<form:errors path="pasteExposure" cssClass="css_error"/>
		</div>
	</div>
	</spring:bind>
	
	<spring:bind path="pasteTitle">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<label for="pasteTitle" class="col-sm-2 control-label">Paste Title</label>
		<div class="col-xs-4">
			<form:input path="pasteTitle" name="paste_name" maxlength="60" class="form-control col-xs-4" />
			<br />
			<form:errors path="pasteTitle" cssClass="css_error"/>
		</div>
	</div>
	</spring:bind>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input class="btn btn-primary" type="submit" value="Add Server">
		</div>
	</div>
	
	</form:form>
	</div>
	
</div>   
        
  
<%@ include file="/WEB-INF/views/footer.jsp" %>