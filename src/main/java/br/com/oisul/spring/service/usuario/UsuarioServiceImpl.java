package br.com.oisul.spring.service.usuario;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.oisul.spring.dao.usuario.UsuarioDAO;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.utils.EmailUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioDAO usuarioDAO;
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	
	@Override
	@Transactional
	public void saveUsuario(Usuario usuario, String url){
		try {
			this.usuarioDAO.addEntity(usuario);
	     	String assunto = "Confirmação de cadastro";
	     	StringBuilder conteudo = new StringBuilder();
	     	conteudo.append("<h1>Confirmação de cadastro</h1><br>");
	     	conteudo.append("Olá ").append(usuario.getNome()).append(", Por favor, acesse o Link abaixo para confirmar seu cadastro.<br>");
	     	conteudo.append("<a href='"+url+"confirmarUsuario?idUsuario="+usuario.getIdUsuario()+"'>Clique aqui</a>");
			EmailUtils.enviaEmail(usuario.getEmail(), conteudo.toString(), assunto);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	@Transactional
	public Usuario getUsuarioById(Integer id) {
		return this.usuarioDAO.getEntityById(id);
	}
	
	@Override
	@Transactional
	public void updateUsuario(Usuario usuario) {
		this.usuarioDAO.updateEntity(usuario);
	}



	@Override
	@Transactional
	public Usuario ativaUsuario(Integer idUsuario) {
		Usuario usuario = this.getUsuarioById(idUsuario);
		usuario.setIsAtivado("S");
		this.updateUsuario(usuario);
		return usuario;
	}
	
	@Override
	@Transactional 
	public Usuario findUsuarioLogin(Usuario usuario){
		return usuarioDAO.findUsuarioByLogin(usuario);
	}

	



}
