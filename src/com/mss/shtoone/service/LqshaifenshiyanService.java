package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Lqshaifenjieguo;
import com.mss.shtoone.domain.LqshaifenjieguoView;
import com.mss.shtoone.domain.Lqshaifenshiyan;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.persistence.LqshaifenjieguoDAO;
import com.mss.shtoone.persistence.LqshaifenjieguoViewDAO;
import com.mss.shtoone.persistence.LqshaifenshiyanDAO;
import com.mss.shtoone.persistence.LqshaifenshiyanViewDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class LqshaifenshiyanService {
	@Autowired
	private LqshaifenshiyanViewDAO sfviewDAO;
	
	@Autowired
	private LqshaifenshiyanDAO sfDAO;
	
	@Autowired
	private LqshaifenjieguoDAO sfjieguoDAO;
	
	@Autowired
	private LqshaifenjieguoViewDAO sfjieguoViewDAO;
	
	public void del(int id){
		sfDAO.deleteByKey(id);
	}

	public void saveOrUpdate(Lqshaifenshiyan shaifen){
		sfDAO.saveOrUpdate(shaifen);
	}
	
	public Lqshaifenshiyan getLqshaifenByid(int id){
		return sfDAO.get(id);
	}
	
	//默认状态下的筛分试验
	public Lqshaifenshiyan getLqshaifenByshebei(String shebeibianhao,String cailiao,String swlilunid){
		List<Lqshaifenshiyan> shaifenlist=sfDAO.find("FROM Lqshaifenshiyan WHERE moren='1' and llid='"+swlilunid+"' and shebeibianhao='"+shebeibianhao+"' and ziduanminchen='"+cailiao+"'");
		if(shaifenlist!=null && shaifenlist.size()>0){
			return (Lqshaifenshiyan)shaifenlist.get(0);
		}else{
			return null;
		}
	}
	
	public Lqshaifenjieguo getLqshaifenjieguoBylqId(Integer lqbianhao){
		List<Lqshaifenjieguo> jieguolist=sfjieguoDAO.find("from Lqshaifenjieguo where swbianhao="+lqbianhao);
		if(jieguolist!=null && jieguolist.size()>0){
			return (Lqshaifenjieguo)jieguolist.get(0);
		}else{
			return null;
		}
	}
	
	public LqshaifenjieguoView getLqshaifenjieguoViewBylqId(Integer lqbianhao){
		List<LqshaifenjieguoView> sfjieguoViewlist=sfjieguoViewDAO.find("from LqshaifenjieguoView where lqbianhao="+lqbianhao);
		if(sfjieguoViewlist!=null && sfjieguoViewlist.size()>0){
			return sfjieguoViewlist.get(0);
		}else{
			return null;
		}
	}
	
	public void saveLqshaifenjieguo(Lqshaifenjieguo sfjieguo){
		sfjieguoDAO.saveOrUpdate(sfjieguo);
	}
	
	public LqshaifenjieguoView getLqshaifenjieguoViewByswId(Integer swbianhao){
		List<LqshaifenjieguoView> sfjieguoViewlist=sfjieguoViewDAO.find("from LqshaifenjieguoView where swbianhao="+swbianhao);
		if(sfjieguoViewlist!=null && sfjieguoViewlist.size()>0){
			return sfjieguoViewlist.get(0);
		}else{
			return null;
		}
	}
	
	public List<Lqshaifenshiyan> getLqshaifenByshebeibianhao_ziduan(String shebeibianhao,String pici){
		return sfDAO.find("from Lqshaifenshiyan where shebeibianhao='"+shebeibianhao+"' and newtime='"+pici+"'");
	}
	
	//当一个拌合机和一个材料名称为默认时，将当前材料、当前拌合机改为非默认
	public void changeNotmoren(Integer syid){
		Lqshaifenshiyan shaifen=sfDAO.get(syid);
		if(shaifen!=null){
			List<Lqshaifenshiyan> shaifenlist=getLqshaifenByshebeibianhao_ziduan(shaifen.getShebeibianhao(), shaifen.getNewtime());
			if(StringUtil.Null2Blank(shaifen.getMoren()).equalsIgnoreCase("1")){ //当用户对于当前的材料是默认时,将其更改为非默认
				shaifen.setMoren("2");
				for(Lqshaifenshiyan shaifenshiyan:shaifenlist){
					shaifenshiyan.setMoren("2");
					sfDAO.saveOrUpdate(shaifenshiyan);
				}
			}else if(StringUtil.Null2Blank(shaifen.getMoren()).equalsIgnoreCase("2")){
				shaifen.setMoren("1");
				for(Lqshaifenshiyan shaifenshiyan:shaifenlist){
					shaifenshiyan.setMoren("1");
					sfDAO.saveOrUpdate(shaifenshiyan);
				}
			}
			sfDAO.saveOrUpdate(shaifen);
		}
	}
	
	public GenericPageMode limitLqsflist(Integer biaoduan,Integer xiangmubu,String shebeibianhao,String startTimeOne,
			String endTimeOne ,String fn, int bsid, int offset, int pagesize,String moren,String ziduanminchen) {
		return sfviewDAO.limitLqsflist(biaoduan,xiangmubu,shebeibianhao,startTimeOne,endTimeOne,
				fn, bsid, offset, pagesize,moren,ziduanminchen);
	}
	
	public Lqshaifenjieguo getLqShaifenjieguoBylqId(Integer lqbianhao){
		List<Lqshaifenjieguo> jieguolist=sfjieguoDAO.find("from Lqshaifenjieguo where swbianhao="+lqbianhao);
		if(jieguolist!=null && jieguolist.size()>0){
			return (Lqshaifenjieguo)jieguolist.get(0);
		}else{
			return null;
		}
	}
	
	public void saveLqShaifenjieguo(Lqshaifenjieguo sfjieguo){
		sfjieguoDAO.saveOrUpdate(sfjieguo);
	}
}
