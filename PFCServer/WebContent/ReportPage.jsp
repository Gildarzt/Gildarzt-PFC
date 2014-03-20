<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>ReportPage</title>
	</head>
	<body>
		<div id="body_bottom_bgd">
			<div id="page">
				<div id="logo"><img src="images/logo.gif" alt="free flash template logo"><h1><span class="blue">PFC</span>Server</h1></div>
				<div id="left_col">		
					<table border="0">
					<thead align="left">
							<tr>
								<td>
									<s:form action="ReturnToPsiMainPage">
										<s:submit value="Volver" align="left"/>
									</s:form>
								</td>
							</tr>
						</thead>
						<thead align="center">
							<tr>
								<td>
									<div id="header"><span class="blue">Información</span></div>
								</td>
							</tr>
						</thead>
						<tbody align="left">
							<tr>
								<td valign="top">
									<s:form value="report">
									<s:textfield label="id paciente" name="report.getIdPatient()" readonly="true"></s:textfield>
									<s:textfield label="fecha" name="report.getDate()" readonly="true"></s:textfield>
									<s:textfield label="Nª aciertos" name="report.getnSuccess()" readonly="true"></s:textfield>
									<s:textfield label="Nª fallos" name="report.getnFailure()" readonly="true"></s:textfield>
									<s:textfield label="Racha de aciertos" name="report.getSuccessSpree()" readonly="true"></s:textfield>
									<s:textfield label="Racha de fallos" name="report.getFailSpree()" readonly="true"></s:textfield>
									<s:textfield label="Dificultad Inicial" name="report.getInitialDifficult()" readonly="true"></s:textfield>	
									<s:textfield label="Racha de aciertos en bonus" name="report.getSuccessBonusSpree()" readonly="true"></s:textfield>
									<s:textfield label="Racha de fallos en bonus" name="report.getFailBonusSpree()" readonly="true"></s:textfield>
									</s:form>
								</td>
								<td valign="top">
									<s:iterator value="tryList">
										<s:form>
										<s:div>Intento</s:div>
										<s:textfield label="Resultado" name="result" readonly="true"></s:textfield>
										<s:textfield label="¿Bonus disponible?" name="bonus_ready" readonly="true"></s:textfield>
										<s:textfield label="¿En modo bonus?" name="bonus_on" readonly="true"></s:textfield>
										<s:textfield label="Dificultad" name="difficult" readonly="true"></s:textfield>	
										</s:form>
									</s:iterator>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>