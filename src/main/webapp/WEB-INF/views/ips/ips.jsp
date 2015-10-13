<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container">

	<div class="well">
		<h2>IP List</h2>
		<button type="button" class="btn btn-success" onclick="window.location='add-ip';return false;">Add IP</button>
		
		<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Assigned VPS</th>
				<th>Primary IP</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
			
				<c:when test="${ipCount == 0}">
					<tr>
						<td colspan='4'>You do not have any ips to display!</td>
					</tr>
				</c:when>
			
				<c:otherwise>
					<c:forEach var="ip" items="${ipList}">
						<tr>
							<td>${ip.ID}</td>
							<td>${ip.vpsName}</td>
							<td>${ip.IPAddress}</td>
							<td> <a href='<c:url value='/admin/edit-ip/${ip.ID}' />'>EDIT</a> <a href='/admin/delete-ip/${ip.ID}'>DELETE</a> </td>
						</tr>
					</c:forEach>
				</c:otherwise>
			
			</c:choose>
		</tbody>
		</table>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>