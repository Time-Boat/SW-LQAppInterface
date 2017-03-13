package com.mss.shtoone.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.persistence.ZuoyeduiDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class XiangmubuService {

	@Autowired
	private XiangmubuDAO xmbDAO;	

	@Autowired
	private ZuoyeduiDAO zydDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	public void saveOrUpdate(Xiangmubuxinxi xmbxx){
		xmbDAO.saveOrUpdate(xmbxx);
	}
	
	public void del(int bdid){
		xmbDAO.deleteByKey(bdid);
	}
	
	public List<Xiangmubuxinxi> getAll(){
		return xmbDAO.loadAll();
	}
	
	public List<Xiangmubuxinxi> limitGetxmb(HttpServletRequest request) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Xiangmubuxinxi> xmblist = new ArrayList<Xiangmubuxinxi>();
		String queryString = "from Xiangmubuxinxi as model where model.biaoduanid=?";
		switch (ut) {
			case 1:
				xmblist = xmbDAO.find("from Xiangmubuxinxi");
				break;
			case 2:
				xmblist = xmbDAO.find(queryString, bsid);		
				break;
			case 3:		
				Xiangmubuxinxi xmb = xmbDAO.get(bsid);
				if (null != xmb) {
					xmblist = xmbDAO.find(queryString, xmb.getBiaoduanid());
				}
				break;
			case 4:		
				Zuoyeduixinxi zyd = zydDAO.get(bsid);
				if (null != zyd) {
					xmblist = xmbDAO.find(queryString, zyd.getBiaoduanid());
				}
				break;
			case 5:		
				Banhezhanxinxi bhz = bhzDAO.get(bsid);
				if (null != bhz) {
					xmblist = xmbDAO.find(queryString, bhz.getBiaoduanid());
				}			
				break;
			default:
				break;
		}
		return xmblist;		
	}
	
	
	public Xiangmubuxinxi getBeanById(int id){
		return xmbDAO.get(id);
	}
	
}
