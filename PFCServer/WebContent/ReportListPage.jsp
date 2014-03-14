<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>ReportListPage</title>
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
				<s:iterator value="reportList">
					<s:form action="ShowReport">
						<s:textfield label="id paciente" name="idPatient" readonly="true"></s:textfield>
						<s:textfield label="id" name="id" readonly="true"></s:textfield>
						<s:textfield label="fecha" name="date" readonly="true"></s:textfield>
						<s:textfield label="Nª aciertos" name="nSuccess" readonly="true"></s:textfield>
						<s:textfield label="Nª fallos" name="nFailure" readonly="true"></s:textfield>
	  					<s:submit value="mostrar" align="left"></s:submit>
					</s:form>
				</s:iterator>
				</div>
			</div>
		</div>
	</body>
</html>