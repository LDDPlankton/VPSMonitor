<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="/resources/site/images/favicon.ico">
<title>VPSMonitor -- ${page_title} </title>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/site/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/site/js/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/site/js/docs.min.js" />"></script>
<script src="<c:url value="/resources/site/js/jquery.form.js" />"></script>

<script type="text/javascript">
function form_post(url, data, redirect_url, debug_status)
{
	var formMessages = $('#form-messages');
	$(formMessages).removeClass('alert alert-success');
	$(formMessages).removeClass('alert alert-danger');
	$(formMessages).text("");
	
	var status = false;
	$.ajax({
		type: "POST",
	 	 url: url,
		async: false,
		data: data,
		error: function(data)
		{
			if(debug_status)
				alert(data.responseText);
		},
	  	success: function(data)
			{
				if(debug_status)
					alert(data);

				//IF SOME ERROR HAPPENED WITH MVC, ETC ... WE HAVE A STRING SO CONV STR TO OBJ
				if(typeof data == 'object')
				{
					var JSONObj = data;//jQuery.parseJSON( data );
					obj = new Object();
					obj.status = JSONObj.status;
					obj.redirect = JSONObj.redirect;
					obj.message = JSONObj.message;
					
					//DETERMINE STATUS
					if( !obj.status )
						$(formMessages).addClass('alert alert-danger');
					else
						$(formMessages).addClass('alert alert-success');
					
					//DETERMINE / PERFORM REDIRECT
					if(obj.status && obj.redirect)
						window.location.replace(redirect_url);
					else
						$(formMessages).prepend(obj.message);


					data = obj;
				}
				window.scrollTo(0,0);
				//status = result;

			}

		});

	//return true;
	return status;
}

$(document).ready(function()
{

});
</script>


</head>

<body>
	<div class="navbar navbar-default " role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<b> <a href="<c:url value='/ssm/home/index'/>" class="navbar-brand">VPSMonitor</a> </b>
			</div>

			<div class="navbar-collapse collapse">		
			<c:choose>
			
				<c:when test="${admin_logged_in}">
					<ul class="nav navbar-nav">
						<li> <a href="<c:url value='/admin/'/>">Home</a> </li>
						<li> <a href="<c:url value='/admin/servers'/>">Servers</a> </li>
						<li> <a href="<c:url value='/admin/ips'/>">IP's</a> </li>
						<li> <a href="<c:url value='/admin/monitors'/>">Server Monitoring</a> </li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li> <a href="<c:url value='/admin/settings'/>">Settings</a> </li>
						<li> <a href="<c:url value='/admin/account'/>">My Account</a> </li>
					</ul>
				</c:when>
				
				<c:when test="${user_logged_in}">
					<ul class="nav navbar-nav">
						<li> </li>
					</ul>
				</c:when>
				
				<c:otherwise>
					<ul class="nav navbar-nav">
						<li> <a href="<c:url value='/home/index'/>">Home</a> </li>						
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li> <a href="<c:url value='/auth/register'/>">Register</a> </li>
						<li> <a href="<c:url value='/auth/login'/>">Login</a> </li>
					</ul>
            
				</c:otherwise>
			
			</c:choose>
			</div>
		</div>
	</div>	