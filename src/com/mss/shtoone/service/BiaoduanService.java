package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.persistence.BiaoduanDAO;

@Service
public class BiaoduanService {

	@Autowired
	private BiaoduanDAO bdDAO;
	
	public void saveOrUpdate(Biaoduanxinxi bdxx){
		
		bdDAO.saveOrUpdate(bdxx);
	}
	
	public void del(int bdid){
		bdDAO.deleteByKey(bdid);
	}
	
	public List<Biaoduanxinxi> getAll(){
		return bdDAO.find("from Biaoduanxinxi order by orderid");
	}
	
	
	public Biaoduanxinxi getBeanById(int id){
		return bdDAO.get(id);
	}
	

}
