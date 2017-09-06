package com.mustafazorbaz.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafazorbaz.dao.UserDao;
import com.mustafazorbaz.entities.Users;
import com.mustafazorbaz.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	UserDao userDao;

	public List<Users> list() {
		return userDao.list();
	}

	public boolean delete(Users users) {
		return userDao.delete(users);
	}

	public boolean saveUser(Users users) {
		return userDao.saveUser(users);
	}

	public Users getUser(int id) {
		return userDao.getUser(id);
	}

	public void removeUser(int id) {
		userDao.removeUser(id);

	}

	public boolean updateUser(Users users) {
		return userDao.updateUser(users);
	}

}
