package com.mss.shtoone.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mss.shtoone.domain.DiuqishujuPageMode;
import com.mss.shtoone.persistence.DiuqishujuDAO;

@Service
public class DiuqishujuService {

	@Autowired
	private DiuqishujuDAO dqDAO;

	public DiuqishujuPageMode getAll(int offset, int pagesize){
		return dqDAO.viewlist(offset, pagesize);
	}
	
	

	

}
