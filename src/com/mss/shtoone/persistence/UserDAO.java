package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.UserPageMode;

@Repository
public interface UserDAO extends GenericDAO<Muser, Integer> {
	public Muser findByName(String username);
	public UserPageMode viewlist(String username, Integer usertype, 
			Integer biaoduan, Integer xiangmubu, Integer shebeibianhao, 
			int ut, int bsid, int offset, int pagesize);
}



