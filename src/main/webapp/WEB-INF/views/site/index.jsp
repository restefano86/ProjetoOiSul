<html>
	<head>
		<title>OiSul - Planos empresariais da operadora Oi</title>
		<%@include file="includes.jsp"%> 
		<style type="text/css">
		* {
			color: #000;
		}
		input{
			border-color: #999;
		}
			#sombrarredondada{
			   background-color: #fff;
			   margin: 0 auto;
			   margin-top: 50px;
			   color: #fff;
			   width: 400px;
			   height: 200px;
			   padding: 10px;
			   -moz-border-radius: 15px;
			   -webkit-border-radius: 15px;
			   box-shadow: 5px 5px 3px #333;
			   -webkit-box-shadow: 5px 5px 3px #333;
			   -moz-box-shadow: 5px 5px 3px #333;
			}
			#tabelaContato td{
				padding: 5px;
			}
</style>
	</head>
	<body style="background-color: #6A65A6; background-image: none;">
		<div id="sombrarredondada">
		<h2 style="text-align: center; width: 100%">OiSul - Área do consultor</h2>
		<form:form action="loginUsuario" commandName="usuario" class="usuarioForm">
			<table width="90%" id="tabelaContato">
				<tr>
					<td style="text-align: right;">Login:</td>
					<td><input type="text" name="email" id="emailLogin" class="form-control floatLeft" /> <br></td>
				</tr>
				<tr>
					<td style="text-align: right;">Senha:</td>
					<td><input type="password" name="senha" id="senhaLogin" class="form-control floatLeft"/></td>
				</tr>
			</table>
		
			<br>
			<div style="width: 100%; text-align: center;">
			<input type="submit" id="btLogin" value="Entrar" class="btn btn-primary" style="width: 90%;"  />
			</div>
		</form:form>
		</div>
	</body>
</html>