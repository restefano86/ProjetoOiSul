package br.com.oisul.spring.service.usuario;

import br.com.oisul.spring.model.Usuario;

public interface UsuarioService {

	public void saveUsuario(Usuario usuario, String url);
	
	public Usuario getUsuarioById(Integer id);

	public Usuario ativaUsuario(Integer idUsuario);

	public Usuario findUsuarioLogin(Usuario usuario);
	
	public void updateUsuario(Usuario usuario);
	
}
