<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="stylesheet.css" rel="stylesheet" type="text/css" />
		<title>TitlePage</title>
	</head>
	<body>
		<div id="body_bottom_bgd">
			<div id="page">
				<div id="logo"><img src="images/logo.gif" alt="free flash template logo"><h1><span class="blue">PFC</span>Server</h1></div>
				<div id="left_col">		
					<table border="0">
						<thead>
							<tr>
								<td>
									<div id="header"><span class="blue">Login</span></div>	
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<s:form action= "Login">
										<s:textfield name="id" label="Código" value=""/>
										<s:password name="password" label="Contraseña"/>
										<s:submit  value="Acceder" align="left"/>
									</s:form>
								</td>
							</tr>
							<tr>
								<th>
									<div>¿Aún no te has registrado?</div>
								</th>
							</tr>
							<tr>
								<td><s:form action="MoveToRegister">
										<s:submit value="Darse de alta" id="left_col"/>
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