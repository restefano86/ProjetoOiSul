package br.com.oisul.spring.dao.usuario;

import java.util.List;

import org.hibernate.Session;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.Usuario;
import br.com.oisul.spring.utils.HQLBuilder;

public class UsuarioDAOImpl extends GenericDAOImpl<Integer, Usuario> implements UsuarioDAO {

	@Override
	public Usuario findUsuarioByLogin(Usuario usuario) {
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from Usuario usuario");
		hql.append("where 1=1");
		hql.appendFiltro("and usuario.email = ?", usuario.getEmail());
		hql.appendFiltro("and usuario.senha = ?", usuario.getSenha());
		hql.appendFiltro("and usuario.isAtivado = ?", "S");
		return (Usuario) hql.uniqueResult();
	}
	
	public Usuario findUsuarioById(Integer idUsuario){
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from Usuario usuario");
		hql.append("where usuario.idUsuario = "+idUsuario);
		return (Usuario) hql.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findUsuario(Usuario usuario) {
		Session session = this.sessionFactory.getCurrentSession();
		HQLBuilder hql = new HQLBuilder(session);
		hql.append("from Usuario usuario");
		hql.append("where 1=1");
		hql.appendFiltro("and upper(usuario.nome) like upper(?) ", "%"+usuario.getNome()+"%");
		hql.appendFiltro("and upper(usuario.email) like upper(?) ", "%"+usuario.getEmail()+"%");
		hql.appendFiltro("and usuario.senha = ?", usuario.getSenha());
		hql.appendFiltro("and usuario.tpUsuario= ?", usuario.getTpUsuario());
		hql.appendFiltro("and usuario.telefone= ?", usuario.getTelefone());
		hql.append(" order by usuario.isAtivado, usuario.nome ");
		return (List<Usuario>) hql.list();
	}
	
}
