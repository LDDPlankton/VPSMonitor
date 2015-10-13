<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@page session="true"%>

<div class="container">

	<div class="well">
		<h2>Please Login</h2>
	
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		
<c:if test="${param.error != null}">
<p>
Invalid username and password.
</p>
</c:if>

<sec:authorize access="isAuthenticated()">
Username: <sec:authentication property="principal.username" />
<a href="${pageContext.request.contextPath}/sec/moderation.html">Moderation page</a><br/>

		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="submit" value="Log out" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<a href="/logout">Logout</a><br/>
</sec:authorize>

<sec:authorize access="isAnonymous()">
This will be shown only to not-authenticated users.
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
This will be shown only to users who have the "ADMIN" authority.
</sec:authorize>

<sec:authorize access="hasAnyRole('ADMIN', 'WARRIOR')">
This will be shown only to users who have the "ADMIN" or the "WARRIOR" authority.
</sec:authorize>
		
		


		
		<c:url value="/login" var="loginUrl" />
		<form method="POST" action="${loginUrl}" accept-charset="UTF-8" class="form-horizontal" role="form">
		
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Email Address" name="username" type="text" id="email">
			</div>
		</div>
		
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input class="form-control col-xs-4" placeholder="Password" name="password" type="password" value="" id="password">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Login">
			</div>
		</div>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>