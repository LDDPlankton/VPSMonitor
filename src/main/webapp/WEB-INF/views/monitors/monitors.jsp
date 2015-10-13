<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container">

	<div class="well">
		<h2>Monitor List</h2>
		<button type="button" class="btn btn-success" onclick="window.location='add-monitor';return false;">Add Monitor</button>
		
		<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Assigned VPS</th>
				<th>Monitored URLP</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
			
				<c:when test="${monitorCount == 0}">
					<tr>
						<td colspan='4'>You do not have any monitors to display!</td>
					</tr>
				</c:when>
			
				<c:otherwise>
					<c:forEach var="monitor" items="${monitorList}">
						<tr>
							<td>${monitor.monitorID}</td>
							<td>${monitor.vpsName} -- <c:out value="${monitor.vpsName}"/> </td>
							<td>${monitor.monitorIPOrUrl} -- <c:out value="${monitor.monitorIPOrUrl}"/> </td>
							<td> <a href='<c:url value='/admin/edit-monitor/${monitor.monitorID}' />'>EDIT</a> <a href='/admin/delete-monitor/${monitor.monitorID}'>DELETE</a> </td>
						</tr>
					</c:forEach>
				</c:otherwise>
			
			</c:choose>
		</tbody>
		</table>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>