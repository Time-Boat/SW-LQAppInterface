package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Liqingxixxdanjia;
import com.mss.shtoone.persistence.LiqingxixxdanjiaDAO;

@Service
public class LiqingxixxdanjiaService {

	@Autowired
	private LiqingxixxdanjiaDAO lqdjDAO;
	
	public void saveOrUpdate(Liqingxixxdanjia lqdjxx){
		
		lqdjDAO.saveOrUpdate(lqdjxx);
	}
	
	public void del(int bdid){
		lqdjDAO.deleteByKey(bdid);
	}
	
	public void moren(int bdid){
		Liqingxixxdanjia lqdj = lqdjDAO.get(bdid);
		if (null != lqdj) {
			if (null != lqdj.getDjmoren() && lqdj.getDjmoren().equalsIgnoreCase("1")) {
				lqdj.setDjmoren("0");
			} else {
			  lqdj.setDjmoren("1");
			}
			lqdjDAO.saveOrUpdate(lqdj);
		}
	}
	
	public List<Liqingxixxdanjia> getAll(){
		return lqdjDAO.find("from Liqingxixxdanjia order by djid DESC");
	}
	
	
	public Liqingxixxdanjia getBeanById(int id){
		return lqdjDAO.get(id);
	}
	

}
