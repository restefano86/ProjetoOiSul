<c:if test="${sessionScope.usuario.tpUsuario eq 'C' || 1==1}">
	<div id="titulo">
		<img alt="logo-oi-empresas" src="/ProjetoOiSul/resources/images/logoOiSulConsultores.jpg" class="oiEmpresas"
			onclick="abreLink('/ProjetoOiSul');">
	</div>
	<div id="menu">
		<div class="btn-group" role="group" aria-label="...">
		  <button type="button" class="btn btn-default" onclick="abreLink('aquisicaoAdminPasso0');">Cadastrar nova venda</button>
		  <button type="button" class="btn btn-default">Meus Pedidos</button>
		  <button type="button" class="btn btn-default">Alterar Senha</button>
		 
		  <!-- 
		  <div class="btn-group" role="group">
		    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      Dropdown
		      <span class="caret"></span>
		    </button>
		    <ul class="dropdown-menu">
		      <li><a href="#">Dropdown link</a></li>
		      <li><a href="#">Dropdown link</a></li>
		    </ul>
		  </div>
		   -->
		   
		</div>	
	</div>
</c:if>

<c:if test="${sessionScope.usuario.tpUsuario eq 'A'}">
	<div id="titulo">
		<img alt="logo-oi-empresas" src="/ProjetoOiSul/resources/images/logoOiSulPortalAdministrativo.jpg" class="oiEmpresas"
			onclick="abreLink('/ProjetoOiSul');">
	</div>
	<div id="menu">
		<div class="btn-group" role="group" aria-label="...">
		  <button type="button" class="btn btn-default">Cadastrar nova venda</button>
		  <button type="button" class="btn btn-default">Meus Pedidos</button>
		  <button type="button" class="btn btn-default">Alterar Senha</button>
		 
		  <!-- 
		  <div class="btn-group" role="group">
		    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      Dropdown
		      <span class="caret"></span>
		    </button>
		    <ul class="dropdown-menu">
		      <li><a href="#">Dropdown link</a></li>
		      <li><a href="#">Dropdown link</a></li>
		    </ul>
		  </div>
		   -->
		  
		</div>	
	</div>
</c:if>