package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.HunnintuPageMode;
import com.mss.shtoone.domain.HunnintuView;
import com.mss.shtoone.domain.HunnintujieguoPageMode;
import com.mss.shtoone.domain.UserlogView;


@Repository
public interface HunnintuViewDAO extends GenericDAO<HunnintuView, Integer> {
	public HunnintuPageMode viewlist(String shebeibianhao,String gongchenghao,
			String startTimeOne,String endTimeOne,String jiaozhubuwei, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	public HunnintujieguoPageMode viewjieguolist(String shebeibianhao,String gongchenghao,
			String startTimeOne,String endTimeOne,String jiaozhubuwei, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	public List<Object> findTop();
	public HunnintuView materiallist(String gongchengmingcheng,String jiaozhubuwei,String startTime,
			String endTime,	String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid);
	public List<HunnintuView> hntsclhslist(String startTime, String endTime, String shebeibianhao, 
			String gongchengmingcheng, String jiaozuobuwei, String qiangdudengji, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid);
	public List<HunnintuView> hntcnfxlist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid);
	public HunnintuView hntcnfxdetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid);
	public HunnintuView hntcnfxlilundetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid);
	public GenericPageMode viewspeedlist(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	public GenericPageMode viewtmplist(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	public GenericPageMode viewtjjlist(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	public GenericPageMode viewwddlist(String shebeibianhao,
			 String startTimeOne,String endTimeOne,int offset, int pagesize);
	public GenericPageMode viewydlist(String shebeibianhao,
			 String startTimeOne,String endTimeOne,int offset, int pagesize);
	public GenericPageMode viewswlist(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize,String llbuwei);
	
	public GenericPageMode viewtemplist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize);
	
	//登录日志
	public GenericPageMode viewuserlogtongjilist(String username,String danwei,String zhiwei,String startTimeOne,
		String endTimeOne, Integer usertype, Integer biaoduan, Integer xiangmubu, Integer pageNo, int maxPageItems, String shebeibianhao);
	
	public GenericPageMode viewuserloglist(String shebeibianhao,String startTimeOne,
			String endTimeOne, int offset, int pagesize);
	
	public List<UserlogView> viewuserlogtongjilist(String shebeibianhao,String startTimeOne,
			String endTimeOne);
	
	public GenericPageMode viewtanpuspeedlist(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	public GenericPageMode viewNianyaTemplist(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	//环境参数查询	
	public GenericPageMode environmentView(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	//出料口温度
	public GenericPageMode viewChuliaokoulist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize);
	
	//沥青设计生产量
	public GenericPageMode lqshejisclview(String shebeibianhao,String peifang,String startTime,String endTime,Integer biaoduan,
				Integer xiangmubu,String fn,int bsid,Integer offset,Integer pagesize);
}



