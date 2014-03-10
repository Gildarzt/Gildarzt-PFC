<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>PatientPage</title>
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
									<div id="header"><span class="blue">Perfil</span></div>
								</td>
								<td></td>
								<td></td>
							</tr>
						</thead>
						<tbody align="left">
							<tr>
								<td>
									<div id="header" align="left">Datos de la cuenta</div>						
								</td>
							</tr>
							<tr>
								<td>
									<s:form value="patient">
										Id:<s:property value="id" /> <br>
										Nombre:<s:property value="name" /> <br>
										Apellido:<s:property value="surname" /> <br>
									</s:form>
								</td>
							</tr>
						</tbody>
						<thead align="center">
							<tr>
								<td>
									<div id="header"><span class="blue">Informes</span></div>
								</td>
								<td></td>
								<td></td>
							</tr>
						</thead>
						<tbody align="left">
							<tr>
								<td>
									<div id="header" align="left">Listado de informes</div>						
								</td>
								<td>
									<div id="header" align="left">Buscar informe</div>
								</td>
							</tr>
							<tr>
								<td>
									<s:iterator value="reportList">
										<s:form action="ShowReport">
											Fecha: <s:property value="date" /> <br>
	  										<br>
										</s:form>
									</s:iterator>
								</td>
								<td>
									<s:form action="SearchReport">
										<s:textfield name="date" label="fecha" value=""/>
									<s:submit value="Buscar" align="left"/>
								</s:form>
								</td>
							</tr>
						</tbody>
						<thead align="center">
							<tr>
								<td>
									<div id="header" align="center"><span class="blue">Gráficas</span></div>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div id="header" align="center">Gráfica de aciertos</div>						
								</td>
								<td>
									<div id="header" align="center">Gráfica de fallos</div>
								</td>
							</tr>
							<tr>
								<td>
									<img src="ServletSuccessGraph"/>					
								</td>
								<td>
									<img src="ServletFailGraph"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>