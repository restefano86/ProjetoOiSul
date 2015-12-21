package br.com.oisul.spring.dao.usuario;

import br.com.oisul.spring.dao.GenericDAO;
import br.com.oisul.spring.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Integer, Usuario> {
	
	public Usuario findUsuarioById(Integer idUsuario);
	
	public Usuario findUsuarioByLogin(Usuario usuario);
	
}
