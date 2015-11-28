<div id="cabecalho">
	<img alt="logo-oi-empresas" src="/ProjetoOiSul/resources/images/logoOiEmpresas.jpg" class="oiEmpresas"
		onclick="abreLink('/ProjetoOiSul');">
	<h1 class="tituloPrincipal">Canal exclusivo para empresas<br>(48) 3266-8000</h1>
	<div id="areaRestrita" style="display: ${!empty sessionScope.usuario.nome ? 'none' : ''}">
		<form:form action="loginUsuario" commandName="usuario" class="usuarioForm">
			<span class="cadastrese"><span style="color: #bbb;">Não tem usuário?</span> <a href="abrirCadUsuarioSite">Cadastre-se</a></span>
				<span>Login:</span><br>
				<input type="text" name="email" id="emailLogin" placeholder="E-mail" style="width: 180px"/> 
				<input type="password" name="senha" id="senhaLogin" placeholder="Senha"/> 
				<img alt="cadeado" id="btLogin" src="/ProjetoOiSul/resources/images/cadeado.jpg">
		</form:form>
	</div>
	<div id="areaRestritalogado" style="display: ${empty sessionScope.usuario.nome ? 'none' : ''}">
		Olá ${sessionScope.usuario.nome}. <a href="logoutUsuario">Sair</a>.
	</div>
</div>
	<div id="barraMenu">  
		<table id="tableMenu"> 
			<tr>
				<td class="itemMenu" width="25%" onclick='abreLink("./solucoesMovel")'>Soluções em Móvel</td>
				<td class="divisorMenu" width="1%"><img src="/ProjetoOiSul/resources/images/divisorMenu.png"></td>
				<td class="itemMenu" width="25%" onclick='abreLink("./solucoesFixo")'>Soluções em Fixo</td>
				<td class="divisorMenu" width="1%"><img src="/ProjetoOiSul/resources/images/divisorMenu.png"></td>
				<td class="itemMenu" width="25%" onclick='abreLink("./parceiros")'>Seja um Parceiro</td>
				<td class="divisorMenu" width="1%"><img src="/ProjetoOiSul/resources/images/divisorMenu.png"></td>
				<td class="itemMenu" width="25%" onclick='abreLink("./contato")'>Contato</td> 
			</tr>
		</table>
	</div>