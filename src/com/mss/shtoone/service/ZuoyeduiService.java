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
public class ZuoyeduiService {

	@Autowired
	private ZuoyeduiDAO zydDAO;
	
	@Autowired
	private XiangmubuDAO xmbDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	public void saveOrUpdate(Zuoyeduixinxi zydxx){
		zydDAO.saveOrUpdate(zydxx);
	}
	
	public void del(int bdid){
		zydDAO.deleteByKey(bdid);
	}
	
	public List<Zuoyeduixinxi> getAll(){
		return zydDAO.loadAll();
	}
	
	public List<Zuoyeduixinxi> limitGetzuoyedui(HttpServletRequest request) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Zuoyeduixinxi> zydlist = new ArrayList<Zuoyeduixinxi>();
		String queryString = "from Zuoyeduixinxi as model where model.biaoduanid=?";
		switch (ut) {
			case 1:
				zydlist = zydDAO.find("from Zuoyeduixinxi");
				break;
			case 2:
				zydlist = zydDAO.find(queryString, bsid);		
				break;
			case 3:		
				Xiangmubuxinxi xmb = xmbDAO.get(bsid);
				if (null != xmb) {
					zydlist = zydDAO.find(queryString, xmb.getBiaoduanid());
				}
				break;
			case 4:		
				Zuoyeduixinxi zyd = zydDAO.get(bsid);
				if (null != zyd) {
					zydlist = zydDAO.find(queryString, zyd.getBiaoduanid());
				}
				break;
			case 5:		
				Banhezhanxinxi bhz = bhzDAO.get(bsid);
				if (null != bhz) {
					zydlist = zydDAO.find(queryString, bhz.getBiaoduanid());
				}			
				break;
			default:
				break;
		}
		return zydlist;		
	}
	
	public Zuoyeduixinxi getBeanById(int id){
		return zydDAO.get(id);
	}
	

}
