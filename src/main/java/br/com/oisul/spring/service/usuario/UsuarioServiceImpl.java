package br.com.oisul.spring.service.usuario;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
			if(StringUtils.isEmpty(usuario.getTpUsuario())){
				usuario.setTpUsuario(Usuario.TP_USUARIO_BASICO);
			}
			this.usuarioDAO.addEntity(usuario);
	     	String assunto = "Confirma��o de cadastro";
	     	StringBuilder conteudo = new StringBuilder();
	     	conteudo.append("<h1>Confirma��o de cadastro</h1><br>");
	     	conteudo.append("Ol� ").append(usuario.getNome()).append(", Por favor, acesse o Link abaixo para confirmar seu cadastro.<br>");
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
	public void salvarUsuarioAdmin(Usuario usuario){
		try {
			if(usuario.getIdUsuario() != null){
				this.usuarioDAO.updateEntity(usuario);
			} else {
				this.usuarioDAO.addEntity(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	@Transactional
	public void alteraSenha(Usuario usuario) throws Exception{
		try {
			usuarioDAO.saveEntity(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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

	@Override
	@Transactional 
	public List<Usuario> findUsuario(Usuario usuario){
		return usuarioDAO.findUsuario(usuario);
	}


}
