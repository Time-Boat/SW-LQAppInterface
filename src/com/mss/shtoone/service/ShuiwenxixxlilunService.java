package com.mss.shtoone.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Shuiwenxixxlilun;
import com.mss.shtoone.domain.ShuiwenxixxlilunView;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.ShuiwenxixxlilunDAO;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.StringUtil;

@Service
public class ShuiwenxixxlilunService {

	@Autowired
	private ShuiwenxixxlilunDAO swllDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	@Autowired
	private SystemService sysService;
	
	public void saveOrUpdate(Shuiwenxixxlilun swllxx){
		
		swllDAO.saveOrUpdate(swllxx);
	}
	
	public void del(int bdid){
		swllDAO.deleteByKey(bdid);
	}
	
	public List<ShuiwenxixxlilunView> getAll(Integer biaoduan,Integer xiangmubu,String shebeibianhao,String fn,int bsid){
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
		return swllDAO.find("from ShuiwenxixxlilunView "+queryCondition+" order by llid DESC");
	}
	
	public List<ShuiwenxixxlilunView> getSwllpbhlistByToday(String fn,int bsid){
		String queryCondition= " where substring(llshigongdateS,1,11)='"+GetDate.getNowTime("yyyy-MM-dd")+"'";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		return swllDAO.find("from ShuiwenxixxlilunView "+queryCondition);
	}
	
	public List<Shuiwenxixxlilun> getllPhbBySbbh(String shebeibianhao){
		return swllDAO.find("from Shuiwenxixxlilun where llshebeibianhao='"+shebeibianhao+"'");
	}
	
	
	public Shuiwenxixxlilun getBeanById(int id){
		return swllDAO.get(id);
	}
	
	public void moren(int llid, String llbuwei, String llshebeibianhao){
		Shuiwenxixxlilun lqll = swllDAO.get(llid);
		if (null != lqll) {
			List<Shuiwenxixxlilun> lllist = swllDAO.getLilunlist(llbuwei, llshebeibianhao);
			if (StringUtil.Null2Blank(lqll.getLlmoren()).equalsIgnoreCase("1")) {  //默认
				lqll.setLlmoren("0");
			} else {
				lqll.setLlmoren("1");
				for (Shuiwenxixxlilun lllilun : lllist) {
					lllilun.setLlmoren("0");
					swllDAO.saveOrUpdate(lllilun);
				}
				if (StringUtil.Null2Blank(llshebeibianhao).length()>0) {
					Banhezhanxinxi bhz = bhzDAO.getBhzbybianhao(llshebeibianhao);
					if (null != bhz && null!=bhz.getSmssettype() && bhz.getSmssettype().equalsIgnoreCase("1") 
						  && null != bhz.getShoujihao() && bhz.getShoujihao().length()>0) {
					  /*  
					  String smscoent = llshebeibianhao+"调整理论配比为编号"+llid+"部位"+llbuwei+"配比"+lqll.getLlysb()+":"+lqll.getLlg1()
					    +":"+lqll.getLlg2()+":"+lqll.getLlg3()+":"+lqll.getLlg4()+":"+lqll.getLlg5()+":"+lqll.getLlf1();
						sysService.saveandSendSms(llshebeibianhao, bhz.getShoujihao(), smscoent, null);
						*/
					}
				}
			}
			swllDAO.saveOrUpdate(lqll);
		}
	}
	
	
	public List<Shuiwenxixxlilun> getLilunBySbbhAndTime(String llshebeibianhao,
			String nowDate) {
		return swllDAO.getLilunBySbbhAndTime(llshebeibianhao, nowDate);
	}

	public Shuiwenxixxlilun getswlilunMoren(String shebeibianhao){
		List<Shuiwenxixxlilun> list=swllDAO.find("from Shuiwenxixxlilun where llmoren='1' and llshebeibianhao='"+shebeibianhao+"'");
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<ShuiwenxixxlilunView> getSwlilunByshebeibianhao(HttpServletRequest request,String shebeibianhao){
		String queryCondition=" where 1=1 ";
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			queryCondition +=" and llshebeibianhao ='"+shebeibianhao+"'";
		}
		return swllDAO.find("from ShuiwenxixxlilunView "+queryCondition);
	}
}
