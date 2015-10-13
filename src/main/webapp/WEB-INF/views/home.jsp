<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


<sec:authorize access="hasRole('ROLE_ADMIN')">
  This content will only be visible to users who have the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
</sec:authorize>

<sec:authorize var="loggedIn" access="hasRole('ROLE_ADMIN')" />
<c:choose>
    <c:when test="${loggedIn}">
        You are logged in
    </c:when>
    <c:otherwise>
        You are logged out
    </c:otherwise>
</c:choose>


</body>
</html>
