package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.domain.Tanpujihuaxinxi;
import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.persistence.TanpujihuaxinxiDAO;

@Service
public class TanpujihuaxinxiService {

	@Autowired
	private TanpujihuaxinxiDAO tpjhDAO;
	@Autowired
	private BiaoduanDAO bdDAO;
	
	public void tanpujihuaxinxiAdd(Tanpujihuaxinxi tpjhxx){
		tpjhDAO.saveOrUpdate(tpjhxx);
	}
	
	public void del(int id){
		tpjhDAO.deleteByKey(id);
	}
	
	public List<Tanpujihuaxinxi> getAll(){
		return tpjhDAO.find("from Tanpujihuaxinxi order by jihuastarttime asc");
	}
	
	public Tanpujihuaxinxi getBeanById(int id){
		return tpjhDAO.get(id);
	}
	
	/**
	 * 获取指定标段的摊铺计划信息
	 * @param biaoduan
	 * @return
	 */
	public List<Tanpujihuaxinxi> getTanpujihuaxinxiBybianduan(Integer biaoduan){
		List<Tanpujihuaxinxi> list= tpjhDAO.getTanpujihuaxinxiBybianduan(biaoduan);
		if(list!=null&&list.size()>0){
			for (Tanpujihuaxinxi tanpujihuaxinxi : list) {
				Biaoduanxinxi bdObj=bdDAO.get(new Integer(tanpujihuaxinxi.getBiaoduanid()));
				tanpujihuaxinxi.setRemark(bdObj.getBiaoduanminchen());
			}
		}
		return list;
	}
	
	
	/**
	 * 获取指定标段的摊铺计划时间年份信息
	 * @param biaoduan  标段  必填项
	 * @param startTime 非必填
	 * @param endTime 非必填
	 * @return
	 */
	public List<Tanpujihuaxinxi> getTanpujihuaYearBybianduan(Integer biaoduan,String startTime,String endTime){
		return tpjhDAO.getTanpujihuaYearBybianduan(biaoduan,startTime,endTime);
	}
	
	/**
	 * 摊铺计划统计信息
	 * @param startTimeOne
	 * @param endTimeOne
	 * @param biaoduan
	 * @param fn
	 * @param bsid
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public GenericPageMode getTanpujihuaxinxitongji(
			 String startTimeOne, String endTimeOne,
			 Integer biaoduan,
			String fn, int bsid, int offset, int pagesize){
		
		GenericPageMode list= tpjhDAO.getTanpujihuaxinxitongji(startTimeOne, endTimeOne, biaoduan, fn, bsid, offset, pagesize);
		if(null!=list.getDatas()){
			for (Tanpujihuaxinxi tanpujihuaxinxi : (List<Tanpujihuaxinxi>)list.getDatas()) {
				Biaoduanxinxi bdObj=bdDAO.get(new Integer(tanpujihuaxinxi.getBiaoduanid()));
				tanpujihuaxinxi.setRemark(bdObj.getBiaoduanminchen());
			}
		}
		return list;
	}
	
	/**
	 * 获取指定标段指定时间段的产量和
	 * @param starttime
	 * @param endtime
	 * @param biaoduan
	 * @return
	 */
	public LiqingclDailyView getSumLiqingCLByDate(String starttime,String endtime,String biaoduan){
		return tpjhDAO.getSumLiqingCLByDate(starttime, endtime, biaoduan);
	}
}
