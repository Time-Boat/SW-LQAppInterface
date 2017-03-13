package com.mss.shtoone.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Liqingxixxlilun;
import com.mss.shtoone.domain.LiqingxixxlilunView;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.LiqingxixxlilunDAO;
import com.mss.shtoone.persistence.LiqingxixxlilunViewDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class LiqingxixxlilunService {

	@Autowired
	private LiqingxixxlilunDAO lqllDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private LiqingxixxlilunViewDAO lqllViewDAO;
	
	public void saveOrUpdate(Liqingxixxlilun lqllxx){
		
		lqllDAO.saveOrUpdate(lqllxx);
	}
	
	public void del(int bdid){
		lqllDAO.deleteByKey(bdid);
	}
	
	public List<LiqingxixxlilunView> getAll(Integer biaoduan,Integer xiangmubu,String shebeibianhao,String fn,int bsid){
		String queryCondition=" where 1=1 ";
		if(biaoduan!=null){
			queryCondition+=" and biaoduanid="+biaoduan;
		}
		if(xiangmubu!=null){
			queryCondition +=" and xiangmubuid="+xiangmubu;
		}
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			queryCondition +=" and llshebeibianhao='"+shebeibianhao+"'";
		}
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		return lqllViewDAO.find("from LiqingxixxlilunView "+queryCondition+" order by llid DESC");
	}
	
	
	public Liqingxixxlilun getBeanById(int id){
		return lqllDAO.get(id);
	}
	
	public void moren(int llid, String llbuwei, String llshebeibianhao){
		Liqingxixxlilun lqll = lqllDAO.get(llid);
		if (null != lqll) {
			List<Liqingxixxlilun> lllist = lqllDAO.getLilunlist(llbuwei, llshebeibianhao);
			if (null != lqll.getLlmoren() && lqll.getLlmoren().equalsIgnoreCase("1")) {
				lqll.setLlmoren("0");
			} else {
			  lqll.setLlmoren("1");
			  for (Liqingxixxlilun lllilun : lllist) {
				lllilun.setLlmoren("0");
				lqllDAO.saveOrUpdate(lllilun);
			  }
			  if (StringUtil.Null2Blank(llshebeibianhao).length()>0) {
				  Banhezhanxinxi bhz = bhzDAO.getBhzbybianhao(llshebeibianhao);
				  if (null != bhz && null!=bhz.getSmssettype() && bhz.getSmssettype().equalsIgnoreCase("1") 
						  && null != bhz.getShoujihao() && bhz.getShoujihao().length()>0) {
					    String smscoent = llshebeibianhao+"调整理论配比为编号"+llid+"部位"+llbuwei+"配比"+lqll.getLlysb()+":"+lqll.getLlg1()
					    +":"+lqll.getLlg2()+":"+lqll.getLlg3()+":"+lqll.getLlg4()+":"+lqll.getLlg5()+":"+lqll.getLlf1();
						sysService.saveandSendSms(llshebeibianhao, bhz.getShoujihao(), smscoent, null);
					}
				}
			}
			lqllDAO.saveOrUpdate(lqll);
		}
	}
	
	public Liqingxixxlilun getlqlilunMoren(String shebeibianhao){
		List<Liqingxixxlilun> list=lqllDAO.find("from Liqingxixxlilun where llmoren='1' and llshebeibianhao='"+shebeibianhao+"'");
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<LiqingxixxlilunView> getLqlilunByshebeibianhao(HttpServletRequest request,String shebeibianhao){
		String queryCondition=" where 1=1 ";
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			queryCondition +=" and llshebeibianhao ='"+shebeibianhao+"'";
		}
		return lqllViewDAO.find("from LiqingxixxlilunView "+queryCondition);
	}
}
