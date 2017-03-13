package com.mss.shtoone.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.persistence.HandSetDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class HandSetService {

	@Autowired
	private HandSetDAO handSetDAO;
	
	public void saveOrUpdate(HandSet handset){
		
		handSetDAO.saveOrUpdate(handset);
	}
	
	public void del(int hsid){
		handSetDAO.deleteByKey(hsid);
		
	}
	
	public List getAll(){
		return handSetDAO.loadAll();
	}
	
	public List<HandSet> limitlist(HttpServletRequest request, Integer biaoduan, String ownername, String phone) {
		List<HandSet> hslist = new ArrayList<HandSet>();
		String queryString = "from handset as model where model.biaoduan=?";	
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		switch (ut) {
		case 1:			
			break;
		case 2:
			if (null == biaoduan) {
				biaoduan = bsid;
			};
		case 3:			
		case 4:	
		case 5:	
			break;
		default:
			break;
		}
		if (ut == 1 || ut == 2) {
			if (null != biaoduan) {
				if (StringUtil.Null2Blank(ownername).length()>0 && 
						StringUtil.Null2Blank(phone).length()>0 ) {
					queryString = "from handset as model where model.biaoduan=? and model.owername like ? and model.phone like ?";
					hslist = handSetDAO.find(queryString, biaoduan, "%"+ownername+"%", "%"+phone+"%");
				} else if (StringUtil.Null2Blank(ownername).length()>0) {
					queryString = "from handset as model where model.biaoduan=? and model.owername like ?";
					hslist = handSetDAO.find(queryString, biaoduan, "%"+ownername+"%");
				} else if (	StringUtil.Null2Blank(phone).length()>0 ) {
					queryString = "from handset as model where model.biaoduan=? and model.phone like ?";
					hslist = handSetDAO.find(queryString, biaoduan, "%"+phone+"%");
				} else {
					queryString = "from handset as model where model.biaoduan=?";
					hslist = handSetDAO.find(queryString, biaoduan);
				}
			} else {
				if (StringUtil.Null2Blank(ownername).length()>0 && 
						StringUtil.Null2Blank(phone).length()>0 ) {
					queryString = "from handset as model where model.owername like ? and model.phone like ?";
					hslist = handSetDAO.find(queryString, "%"+ownername+"%", "%"+phone+"%");
				} else if (StringUtil.Null2Blank(ownername).length()>0) {
					queryString = "from handset as model where model.owername like ?";
					hslist = handSetDAO.find(queryString, "%"+ownername+"%");
				} else if (	StringUtil.Null2Blank(phone).length()>0 ) {
					queryString = "from handset as model where model.phone like ?";
					hslist = handSetDAO.find(queryString, "%"+phone+"%");
				} else {
					hslist = handSetDAO.loadAll();
				}
			}
		}
		return hslist;
	}
	
	
	public HandSet getBeanById(int id){
		return handSetDAO.get(id);
	}
	

}
