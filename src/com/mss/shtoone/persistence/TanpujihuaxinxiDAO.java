package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.domain.Tanpujihuaxinxi;


@Repository
public interface TanpujihuaxinxiDAO extends GenericDAO<Tanpujihuaxinxi, Integer> {

	/**
	 * 获取指定标段的摊铺计划信息
	 * @param biaoduan
	 * @return
	 */
	public List<Tanpujihuaxinxi> getTanpujihuaxinxiBybianduan(Integer biaoduan);
	
	
	/**
	 * 获取指定标段的摊铺计划时间年份信息
	 * @param biaoduan  标段  必填项
	 * @param startTime 非必填
	 * @param endTime 非必填
	 * @return
	 */
	public List<Tanpujihuaxinxi> getTanpujihuaYearBybianduan(Integer biaoduan,String startTime,String endTime);
	
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
			String fn, int bsid, int offset, int pagesize);
	
	/**
	 * 获取指定标段指定时间段的产量和
	 * @param starttime
	 * @param endtime
	 * @param biaoduan
	 * @return
	 */
	public LiqingclDailyView getSumLiqingCLByDate(String starttime,String endtime,String biaoduan);
}
