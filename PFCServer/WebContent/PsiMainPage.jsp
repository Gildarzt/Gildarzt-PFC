<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>PsiMainPage</title>
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
									<s:form action="CloseSession">
										<s:submit value="Cerrar Sesion" align="left"/>
									</s:form>
								</td>
							</tr>
						</thead>
						<thead align="center">
							<tr>
								<td>
									<div id="header"><span class="blue">Pacientes</span></div>
								</td>
								<td></td>
								<td></td>
							</tr>
						</thead>
						<tbody align="left">
							<tr>
								<td>
									<div id="header" align="left">Listado de pacientes</div>						
								</td>
								<td>
									<div id="header" align="left">Buscar paciente</div>
								</td>
								<td>
									<div id="header" align="left">Informes sin leer</div>
								</td>
							</tr>
							<tr>
								<td>
									<s:iterator value="patientList">
										<s:form action="ShowPatient">
											Id: <s:property value="id" /> <br>
											Nombre: <s:property value="name" /> <br>
											Primer apellido: <s:property value="surname" /> <br>
											Id Psicologo: <s:property value="idPsi" /> <br>
	  										<br>
										</s:form>
									</s:iterator>
								</td>
								<td>
									<s:form action="SearchPatient">
										<s:textfield name="name" label="Nombre" value="" />
										<s:textfield name="surname" label="Apellido" value=""/>
									<s:submit value="Buscar" align="left"/>
								</s:form>
								</td>
								<td>
									<s:iterator value="reportList">
										<s:form action="ShowReport">
											Id: <s:property value="id" /> <br>
											Fecha: <s:property value="date" /> <br>
											Número de aciertos: <s:property value="nSuccess" /> <br>
											Número de fallos: <s:property value="nFailure" /> <br>
											Número de aciertos en bonus: <s:property value="nBonus" /> <br>
											Mayor racha de aciertos: <s:property value="successSpree" /> <br>
											Mayor racha de fallos: <s:property value="failSpree" /> <br>
	  										<br>
										</s:form>
									</s:iterator>
								</td>
							</tr>
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
								<td>
									<div id="header" align="left">Modificar cuenta</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<s:form value="psic">
										Id:<s:property value="id" /> <br>
										Nombre:<s:property value="name" /> <br>
										Contraseña:<s:property value="password" /> <br>
									</s:form>
								</td>
								<td>
									<s:form action="ChangePersonalData">
										<s:textfield name="id" label="id del psicologo"  value=""/>
										<s:textfield name="name" label="Nombre de Usuario" value=""/>
										<s:textfield name="password" label="Contraseña"  value=""/>									
										<s:submit value="Cambiar" align="right"/>
									</s:form>								
								</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>