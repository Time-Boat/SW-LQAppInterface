package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.domain.Role;
import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.persistence.RoleDAO;
import com.mss.shtoone.persistence.UserDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	public Muser getUserByName(String username) {
		List<Muser> ul = userDAO.find("from muser where name=?", username);
		if (ul.size() > 0) {
			return ul.get(0);
		} else {
			return null;
		}
	}
	
	public void saveOrUpdate(Muser user) {
		userDAO.saveOrUpdate(user);
	}
	
	public List<Role> getAllRole() {
		return roleDAO.loadAll();
	}
	
	public Muser getUserById(Integer id) {
		return userDAO.get(id);
	}
}



