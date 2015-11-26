package br.com.oisul.spring.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.oisul.spring.dao.usuario.UsuarioDAO;
import br.com.oisul.spring.model.Person;
import br.com.oisul.spring.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioDAO usuarioDAO;
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	
	@Override
	@Transactional
	public void saveUsuario(Usuario usuario){
		this.usuarioDAO.addEntity(usuario);
	}

	@Override
	@Transactional
	public Usuario getUsuarioById(Integer id) {
		return this.usuarioDAO.getEntityById(id);
	}


	@Override
	@Transactional
	public Usuario ativaUsuario(Integer idUsuario) {
		Usuario usuario = this.getUsuarioById(idUsuario);
		
		return usuario;
	}

	



}
