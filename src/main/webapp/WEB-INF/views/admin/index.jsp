<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container">

	<sec:authorize access="hasRole('ROLE_ADMIN')">
	AUTHORIZED 1
	</sec:authorize>

	<div class="well">
		<h2>Admin Home</h2>

		Total VPS Count: ${total_vps_count} <br />
		Total IP Count: ${total_ip_count} <br />
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>