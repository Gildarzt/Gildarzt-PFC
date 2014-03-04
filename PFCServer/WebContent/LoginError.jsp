<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>LoginError</title>
	</head>
	<body>
		<div id="body_bottom_bgd">
			<div id="page">
				<div id="logo"><img src="images/logo.gif" alt="free flash template logo"><h1><span class="blue">PFC</span>Server</h1></div>
				<div id="header"><span class="blue">¡¡¡ERROR!!!</span></div>
				<div id="blue_line"></div>
				<br>
				<br>
				<div>El nombre de usuario o la contraseña es incorrecto</div>
				<s:form action="ReturnToTitle">
					<s:submit value="volver"/>
				</s:form>
			</div>
		</div>
	</body>
</html>