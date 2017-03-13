package com.mss.shtoone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.persistence.UserTestViewDAO;

@Service
public class QueryUserTestService {

	@Autowired
	private UserTestViewDAO usertestDAO;	

	public GenericPageMode viewlist(String startTimeOne, String endTimeOne, String testname, int offset, int pagesize){
		return usertestDAO.viewlist(startTimeOne, endTimeOne, testname, offset, pagesize);
	}
}
