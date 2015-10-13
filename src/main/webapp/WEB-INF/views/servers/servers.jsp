<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container">

	<div class="well">
		<h2>Server List</h2>
		<button type="button" class="btn btn-success" onclick="window.location='add-server';return false;">Add Server</button>
		
		<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Primary IP</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
			
				<c:when test="${serverCount == 0}">
					<tr>
						<td colspan='5'>You do not have any servers to display!</td>
					</tr>
				</c:when>
			
				<c:otherwise>
					<c:forEach var="server" items="${serverList}">
						<tr>
							<td>${server.ID}</td>
							<td>${server.name}</td>
							<td>${server.primaryIP}</td>
							<td>${server.status}</td>
							<td> <a href='<c:url value='/admin/edit-server/${server.ID}' />'>EDIT</a> <a href='/admin/delete-server/${server.ID}'>DELETE</a> </td>
						</tr>
					</c:forEach>
				</c:otherwise>
			
			</c:choose>
		</tbody>
		</table>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>