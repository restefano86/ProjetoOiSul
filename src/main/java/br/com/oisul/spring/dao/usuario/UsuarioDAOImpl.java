package br.com.oisul.spring.dao.usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import br.com.oisul.spring.dao.GenericDAOImpl;
import br.com.oisul.spring.model.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Integer, Usuario> implements UsuarioDAO {

	@Override
	public Usuario findUsuarioByLogin(Usuario usuario) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Usuario.class); 
		if(!StringUtils.isEmpty(usuario.getEmail())){
			criteria.add(Restrictions.ge("email",usuario.getEmail()));
			criteria.add(Restrictions.ge("senha",usuario.getSenha()));
			criteria.add(Restrictions.ge("isAtivado","S"));
		}
		Usuario result = (Usuario) criteria.uniqueResult();
		return result;
	}

}
