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
								<td>
									<s:form value="report">
										Id: <s:property value="id" /> <br>
										Fecha: <s:property value="date" /> <br>
										Número de aciertos: <s:property value="nSuccess" /> <br>
										Número de fallos: <s:property value="nFailure" /> <br>
										Número de aciertos en bonus: <s:property value="nBonus" /> <br>
										Mayor racha de aciertos: <s:property value="successSpree" /> <br>
										Mayor racha de fallos: <s:property value="failSpree" /> <br>
									</s:form>
								</td>
								<td>
									<s:iterator value="tryList">
										<s:form>
											Resultado:<s:property value="result" /> <br>
											Bonus activado:<s:property value="bonus_ready" /> <br>
											En modo bonus:<s:property value="bonus_on" /> <br>
											dificultad:<s:property value="diffcult"/><br>
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