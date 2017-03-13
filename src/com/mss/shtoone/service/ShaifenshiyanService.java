package com.mss.shtoone.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Shaifenjieguo;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.domain.Shaifenshiyan;
import com.mss.shtoone.persistence.ShaifenjieguoDAO;
import com.mss.shtoone.persistence.ShaifenjieguoViewDAO;
import com.mss.shtoone.persistence.ShaifenshiyanDAO;
import com.mss.shtoone.persistence.ShaifenshiyanViewDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class ShaifenshiyanService {
	@Autowired
	private ShaifenshiyanViewDAO sfviewDAO;
	
	@Autowired
	private ShaifenshiyanDAO sfDAO;
	
	@Autowired
	private ShaifenjieguoDAO sfjieguoDAO;
	
	@Autowired
	private ShaifenjieguoViewDAO sfjieguoViewDAO;
	
	public GenericPageMode limitsflist(Integer biaoduan,Integer xiangmubu,String shebeibianhao,String startTimeOne,
			String endTimeOne ,String fn, int bsid, int offset, int pagesize,String moren,String ziduanminchen) {
		return sfviewDAO.limitsflist(biaoduan,xiangmubu,shebeibianhao,startTimeOne,endTimeOne,
				fn, bsid, offset, pagesize,moren,ziduanminchen);
	}
	
	public void del(int id){
		sfDAO.deleteByKey(id);
	}

	public void saveOrUpdate(Shaifenshiyan shaifen){
		sfDAO.saveOrUpdate(shaifen);
	}
	
	public Shaifenshiyan getShaifenByid(int id){
		return sfDAO.get(id);
	}
	
	//默认状态下的筛分试验
	public Shaifenshiyan getShaifenByshebei(String shebeibianhao,String cailiao,String swlilunid){
		List<Shaifenshiyan> shaifenlist=sfDAO.find("FROM Shaifenshiyan WHERE moren='1' and llid='"+swlilunid+"' and shebeibianhao='"+shebeibianhao+"' and ziduanminchen='"+cailiao+"'");
		if(shaifenlist!=null && shaifenlist.size()>0){
			return (Shaifenshiyan)shaifenlist.get(0);
		}else{
			return null;
		}
	}
	
	public Shaifenjieguo getShaifenjieguoBylqId(Integer lqbianhao){
		List<Shaifenjieguo> jieguolist=sfjieguoDAO.find("from Shaifenjieguo where swbianhao="+lqbianhao);
		if(jieguolist!=null && jieguolist.size()>0){
			return (Shaifenjieguo)jieguolist.get(0);
		}else{
			return null;
		}
	}
	
	public void saveShaifenjieguo(Shaifenjieguo sfjieguo){
		sfjieguoDAO.saveOrUpdate(sfjieguo);
	}
	
	public ShaifenjieguoView getShaifenjieguoViewByswId(Integer swbianhao){
		//List<ShaifenjieguoView> sfjieguoViewlist=sfjieguoViewDAO.find("from ShaifenjieguoView where swbianhao="+swbianhao);
		List<ShaifenjieguoView>  sfjieguoViewlist = sfjieguoViewDAO.findBySql("select * from ShaifenjieguoView where swbianhao="+swbianhao);
		
		//List<ShaifenjieguoView> test  = sfjieguoViewDAO.find(queryString, values)
		if(sfjieguoViewlist!=null && sfjieguoViewlist.size()>0){
			//return sfjieguoViewlist.get(0);
			return sfjieguoViewlist.get(sfjieguoViewlist.size()-1);
		}else{
			return null;
		}
	}

	
	public GenericPageMode shaifenjieguoviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, Integer bsid, Integer offset, Integer pagesize,String llbuwei){
		return sfjieguoViewDAO.shaifenjieguoviewlist(shebeibianhao, startTimeOne, endTimeOne, biaoduan, xiangmubu, fn, bsid, offset, pagesize,llbuwei);
	}
	
	public List<Shaifenshiyan> getShaifenByshebeibianhao_ziduan(String shebeibianhao,String pici){
		return sfDAO.find("from Shaifenshiyan where shebeibianhao='"+shebeibianhao+"' and newtime='"+pici+"'");
	}
	//查询当前设备编号，其他时间的拌和机的筛分例如数据
	public List<Shaifenshiyan> getShaifenByshebeibianhao_ziduanfou(String shebeibianhao,String pici){
		return sfDAO.find("from Shaifenshiyan where shebeibianhao='"+shebeibianhao+"' and newtime!='"+pici+"'");
	}
	//当一个拌合机和一个材料名称为默认时，将当前材料、当前拌合机改为非默认
	public void changeNotmoren(Integer syid){
		Shaifenshiyan shaifen=sfDAO.get(syid);
		if(shaifen!=null){
			List<Shaifenshiyan> shaifenlist=getShaifenByshebeibianhao_ziduan(shaifen.getShebeibianhao(), shaifen.getNewtime());
			if(StringUtil.Null2Blank(shaifen.getMoren()).equalsIgnoreCase("1")){ //当用户对于当前的材料是默认时,将其更改为非默认
				shaifen.setMoren("2");
				for(Shaifenshiyan shaifenshiyan:shaifenlist){
					shaifenshiyan.setMoren("2");
					sfDAO.saveOrUpdate(shaifenshiyan);
				}
			}else if(StringUtil.Null2Blank(shaifen.getMoren()).equalsIgnoreCase("2")){
				shaifen.setMoren("1");
				for(Shaifenshiyan shaifenshiyan:shaifenlist){
					shaifenshiyan.setMoren("1");
					sfDAO.saveOrUpdate(shaifenshiyan);
				}
			}else if(shaifen.getMoren()==null||shaifen.getMoren()==""){
				shaifen.setMoren("1");
				for(Shaifenshiyan shaifenshiyan:shaifenlist){
					shaifenshiyan.setMoren("1");
					sfDAO.saveOrUpdate(shaifenshiyan);
				}
			}
			sfDAO.saveOrUpdate(shaifen);
			//更新了当前的之后还需要更新着一台拌和机的其他筛分录入设置成非默认,根据拌和机名称和时间
			List<Shaifenshiyan> shaifenlistfou = getShaifenByshebeibianhao_ziduanfou(shaifen.getShebeibianhao(), shaifen.getNewtime());
			for(Shaifenshiyan shaifenshiyanfou:shaifenlistfou){
				shaifenshiyanfou.setMoren("2");
				sfDAO.saveOrUpdate(shaifenshiyanfou);
			}
			
		}
	}
	
}
