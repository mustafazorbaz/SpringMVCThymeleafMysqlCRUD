package com.mustafazorbaz.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mustafazorbaz.dao.UserDao;
import com.mustafazorbaz.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Users> list() {
		return sessionFactory.getCurrentSession().createCriteria(Users.class).list();

	}

	public boolean delete(Users users) {
		try {
			sessionFactory.getCurrentSession().delete(users);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean saveUser(Users users) {
		sessionFactory.getCurrentSession().save(users);
		return true;
	}

	public Users getUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();

		// get user
		Users user = (Users) session.get(Users.class, new Integer(id));
		return user;
	}

	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Users p = (Users) session.load(Users.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("User deleted successfully, User details=" + p);

	}

	public boolean updateUser(Users users) { 
		sessionFactory.getCurrentSession().update(users); 
		return true;
	}

}
