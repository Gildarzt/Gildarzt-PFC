<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>RegisterPage</title>
	</head>
	<body>
		<div id="body_bottom_bgd">
			<div id="page">
				<div id="logo"><img src="images/logo.gif" alt="free flash template logo"><h1><span class="blue">PFC</span>Server</h1></div>
				<div>
					<s:form action="ReturnToTitle">
						<s:label value="volver al menú principal"/>
						<s:submit value="volver"/>
					</s:form>
				</div>
				<div id="left_col">			
					<table border="0">
						<thead align="left">
							<tr>
								<th>
									<div id="header"><span class="blue">¡Date de Alta!</span></div>
								</th>
							</tr>
							<tr>
								<th>
									<div id="blue_line"></div>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div align="left">Datos</div>
								</td>
							</tr>
							<tr>
								<td>
									<s:form action="RegisterPsi">
										<s:textfield name="id" label="Código Psicologo" value=""/>
										<s:textfield name="name" label="Usuario" value=""/>
										<s:password name="password" label="contraseña" value=""/>
										<s:submit value="Aceptar" align="left"/>
									</s:form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>