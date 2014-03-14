<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>PatientListPage</title>
	</head>
	<body>
		<div id="body_bottom_bgd">
			<div id="page">
				<div id="logo"><img src="images/logo.gif" alt="free flash template logo"><h1><span class="blue">PFC</span>Server</h1></div>
				<div id="header"><span class="blue">Resultados de la búsqueda</span></div>
				<div id="blue_line"></div>
				<div>
					<s:form action="ReturnToPsiMainPage">
						<s:submit value="Volver"/>
					</s:form>
				</div>
				<div>
				<s:iterator value="patientList">
					<s:form action="ShowPatient">
						<s:textfield label="Id" name="id" readonly="true"></s:textfield>
						<s:textfield label="Nombre" name="name" readonly="true"></s:textfield>
						<s:textfield label="Apellido" name="surname" readonly="true"></s:textfield>
	  					<br>
					</s:form>
				</s:iterator>
				</div>
			</div>
		</div>
	</body>
</html>