package com.mss.shtoone.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.ChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.Clksmscfg;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.HunnintuPageMode;
import com.mss.shtoone.domain.HunnintuView;
import com.mss.shtoone.domain.HunnintujieguoPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingxixxdanjia;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.LqshaifenjieguoView;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Shuiwenxixxdanjia;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.SpeeddataView;
import com.mss.shtoone.domain.T_YanDu;
import com.mss.shtoone.domain.T_YanDuPageMode;
import com.mss.shtoone.domain.TemperaturedataView;
import com.mss.shtoone.domain.TjjdataView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopShuiwenView;
import com.mss.shtoone.domain.UserlogView;
import com.mss.shtoone.domain.XCData1PageMode;
import com.mss.shtoone.domain.XCData2PageMode;
import com.mss.shtoone.domain.Xcsmscfg;
import com.mss.shtoone.domain.Xiangxixx;
import com.mss.shtoone.domain.Yandudata;
import com.mss.shtoone.fusioncharts.FusionChartsCreator;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.HntbhzCfgViewDAO;
import com.mss.shtoone.persistence.HunnintuViewDAO;
import com.mss.shtoone.persistence.LiqingViewDAO;
import com.mss.shtoone.persistence.LiqingmanualphbViewDAO;
import com.mss.shtoone.persistence.LiqingxixxDAO;
import com.mss.shtoone.persistence.LiqingxixxdanjiaDAO;
import com.mss.shtoone.persistence.LiqingziduancfgDAO;
import com.mss.shtoone.persistence.LiqingziduancfgViewDAO;
import com.mss.shtoone.persistence.ShuiwenmanualphbViewDAO;
import com.mss.shtoone.persistence.ShuiwenxixxViewDAO;
import com.mss.shtoone.persistence.ShuiwenxixxdanjiaDAO;
import com.mss.shtoone.persistence.ShuiwenziduancfgDAO;
import com.mss.shtoone.persistence.ShuiwenziduancfgViewDAO;
import com.mss.shtoone.persistence.T_YanDuDAO;
import com.mss.shtoone.persistence.TopLiqingViewDAO;
import com.mss.shtoone.persistence.TopShuiwenViewDAO;
import com.mss.shtoone.persistence.XCData1DAO;
import com.mss.shtoone.persistence.XCData2DAO;
import com.mss.shtoone.persistence.XCData3DAO;
import com.mss.shtoone.persistence.XcsmscfgDAO;
import com.mss.shtoone.persistence.XiangxixxDAO;
import com.mss.shtoone.persistence.YandudataDAO;
import com.mss.shtoone.util.StringUtil;


@Service
public class QueryService {
	private static Log logger = LogFactory.getLog(QueryService.class);
	 
	@Autowired
	private HunnintuViewDAO hntDAO;
	
	@Autowired
	private LiqingViewDAO lqDAO;
	
	@Autowired
	private T_YanDuDAO t_YanDuDAO;
	
	@Autowired
	private YandudataDAO yanDuDataDAO;

	
	@Autowired
	private TopLiqingViewDAO toplqDAO;
	
	@Autowired
	private HntbhzCfgViewDAO hntcfgviewDAO;
	
	@Autowired
	private LiqingziduancfgDAO lqcfgDAO;
	
	@Autowired
	private LiqingziduancfgViewDAO lqcfgviewDAO;
	
	@Autowired
	private XiangxixxDAO hntxxDAO;

	@Autowired
	private LiqingxixxDAO lqxxDAO;
	
	@Autowired
	private XCData1DAO xc1DAO;
	
	@Autowired
	private XCData2DAO xc2DAO;
	
	@Autowired
	private XCData3DAO xc3DAO;
	
	@Autowired
	private LiqingxixxdanjiaDAO djDAO;
	
	@Autowired
	private LiqingViewDAO lqviewDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	@Autowired
	private XcsmscfgDAO xcsmscfgDAO;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private ShuiwenziduancfgViewDAO swcfgviewDAO;
	
	@Autowired
	private TopShuiwenViewDAO topswDAO;
	
	@Autowired
	private ShuiwenxixxViewDAO swViewDAO;
	
	@Autowired
	private ShuiwenxixxdanjiaDAO swdjDAO;
	
	@Autowired
	private ShuiwenziduancfgDAO swcfgDAO;
	
	@Autowired
	private ShuiwenxixxdanjiaDAO swdanjiaDAO;
	
	@Autowired
	private ShuiwenmanualphbViewDAO swmanualphbViewDAO;
	
	@Autowired
	private LiqingmanualphbViewDAO lqmanualphbViewDAO;
	
	public Xiangxixx xxfindById(Integer xxid) {
		return hntxxDAO.get(xxid);
	}

	public LiqingView lqxxfindById(Integer xxid) {
		LiqingView lqInfo=new LiqingView();
		LiqingView lqxixx=lqDAO.get(xxid);
		lqInfo.setBianhao(lqxixx.getBianhao());
		lqInfo.setShijian(lqxixx.getShijian());
		lqInfo.setBaocunshijian(lqxixx.getBaocunshijian());
		lqInfo.setCaijishijian(lqxixx.getCaijishijian());
		lqInfo.setJbsj(lqxixx.getJbsj());
		lqInfo.setYonghu(lqxixx.getYonghu());
		lqInfo.setPeifan(lqxixx.getPeifan());
		lqInfo.setBiaoshi(lqxixx.getBiaoshi());
		try{
			if(StringUtil.Null2Blank(lqxixx.getLqwd()).length()>0){
				lqInfo.setLqwd(String.format("%1$.1f",Double.parseDouble(lqxixx.getLqwd())));
			}else{
				lqInfo.setLqwd("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getGlwd()).length()>0){
				lqInfo.setGlwd(String.format("%1$.1f",Double.parseDouble(lqxixx.getGlwd())));
			}else{
				lqInfo.setGlwd("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getClwd()).length()>0){
				lqInfo.setClwd(String.format("%1$.1f",Double.parseDouble(lqxixx.getClwd())));
			}else{
				lqInfo.setClwd("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getChangliang()).length()>0){
				lqInfo.setChangliang(String.format("%1$.1f",Double.parseDouble(lqxixx.getChangliang())));
			}else{
				lqInfo.setChangliang("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjysb()).length()>0){
				lqInfo.setSjysb(String.format("%1$.2f",Double.parseDouble(lqxixx.getSjysb())));
			}else{
				lqInfo.setSjysb("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjlq()).length()>0){
				lqInfo.setSjlq(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjlq())));
			}else{
				lqInfo.setSjlq("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg1()).length()>0){
				lqInfo.setSjg1(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg1())));
			}else{
				lqInfo.setSjg1("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg2()).length()>0){
				lqInfo.setSjg2(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg2())));
			}else{
				lqInfo.setSjg2("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg3()).length()>0){
				lqInfo.setSjg3(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg3())));
			}else{
				lqInfo.setSjg3("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg4()).length()>0){
				lqInfo.setSjg4(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg4())));
			}else{
				lqInfo.setSjg4("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg5()).length()>0){
				lqInfo.setSjg5(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg5())));
			}else{
				lqInfo.setSjg5("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg6()).length()>0){
				lqInfo.setSjg6(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg6())));
			}else{
				lqInfo.setSjg6("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjg7()).length()>0){
				lqInfo.setSjg7(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjg7())));
			}else{
				lqInfo.setSjg7("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjf1()).length()>0){
				lqInfo.setSjf1(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjf1())));
			}else{
				lqInfo.setSjf1("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjf2()).length()>0){
				lqInfo.setSjf2(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjf2())));
			}else{
				lqInfo.setSjf2("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getSjtjj()).length()>0){
				lqInfo.setSjtjj(String.format("%1$.1f",Double.parseDouble(lqxixx.getSjtjj())));
			}else{
				lqInfo.setSjtjj("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		//实际配比
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg1()).length()>0){
				lqInfo.setPersjg1(String.format("%1$.2f",Double.parseDouble(lqxixx.getPersjg1())));
			}else{
				lqInfo.setPersjg1("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg2()).length()>0){
				lqInfo.setPersjg2(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjg1())));
			}else{
				lqInfo.setPersjg2("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg3()).length()>0){
				lqInfo.setPersjg3(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjg3())));
			}else{
				lqInfo.setPersjg3("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg4()).length()>0){
				lqInfo.setPersjg4(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjg4())));
			}else{
				lqInfo.setPersjg4("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg5()).length()>0){
				lqInfo.setPersjg5(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjg5())));
			}else{
				lqInfo.setPersjg5("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg6()).length()>0){
				lqInfo.setPersjg6(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjg6())));
			}else{
				lqInfo.setPersjg6("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjg7()).length()>0){
				lqInfo.setPersjg7(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjg7())));
			}else{
				lqInfo.setPersjg7("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjf1()).length()>0){
				lqInfo.setPersjf1(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjf1())));
			}else{
				lqInfo.setPersjf1("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjf2()).length()>0){
				lqInfo.setPersjf2(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjf2())));
			}else{
				lqInfo.setPersjf2("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjlq()).length()>0){
				lqInfo.setPersjlq(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjlq())));
			}else{
				lqInfo.setPersjlq("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		try{
			if(StringUtil.Null2Blank(lqxixx.getPersjtjj()).length()>0){
				lqInfo.setPersjtjj(String.format("%1$.1f",Double.parseDouble(lqxixx.getPersjtjj())));
			}else{
				lqInfo.setPersjtjj("");
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}
		
		if(StringUtil.Null2Blank(lqxixx.getBiaoshi()).length()>0){
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllysb()).length()>0){
					lqInfo.setLlysb(String.format("%1$.2f",Double.parseDouble(lqxixx.getPerllysb())));
				}else{
					lqInfo.setLlysb("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerlllq()).length()>0){
					lqInfo.setLllq(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerlllq())));
				}else{
					lqInfo.setLllq("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg1()).length()>0){
					lqInfo.setLlg1(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg1())));
				}else{
					lqInfo.setLlg1("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg2()).length()>0){
					lqInfo.setLlg2(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg2())));
				}else{
					lqInfo.setLlg2("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg3()).length()>0){
					lqInfo.setLlg3(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg3())));
				}else{
					lqInfo.setLlg3("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg4()).length()>0){
					lqInfo.setLlg4(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg4())));
				}else{
					lqInfo.setLlg4("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg5()).length()>0){
					lqInfo.setLlg5(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg5())));
				}else{
					lqInfo.setLlg5("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg6()).length()>0){
					lqInfo.setLlg6(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg6())));
				}else{
					lqInfo.setLlg6("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllg7()).length()>0){
					lqInfo.setLlg7(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllg7())));
				}else{
					lqInfo.setLlg7("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllf1()).length()>0){
					lqInfo.setLlf1(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllf1())));
				}else{
					lqInfo.setLlf1("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerllf2()).length()>0){
					lqInfo.setLlf2(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerllf2())));
				}else{
					lqInfo.setLlf2("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getPerlltjj()).length()>0){
					lqInfo.setLltjj(String.format("%1$.1f",Double.parseDouble(lqxixx.getPerlltjj())));
				}else{
					lqInfo.setLltjj("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
		}else{
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlysb()).length()>0){
					lqInfo.setLlysb(String.format("%1$.2f",Double.parseDouble(lqxixx.getLlysb())));
				}else{
					lqInfo.setLlysb("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLllq()).length()>0){
					lqInfo.setLllq(String.format("%1$.1f",Double.parseDouble(lqxixx.getLllq())));
				}else{
					lqInfo.setLllq("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg1()).length()>0){
					lqInfo.setLlg1(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg1())));
				}else{
					lqInfo.setLlg1("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg2()).length()>0){
					lqInfo.setLlg2(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg2())));
				}else{
					lqInfo.setLlg2("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg3()).length()>0){
					lqInfo.setLlg3(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg3())));
				}else{
					lqInfo.setLlg3("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg4()).length()>0){
					lqInfo.setLlg4(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg4())));
				}else{
					lqInfo.setLlg4("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg5()).length()>0){
					lqInfo.setLlg5(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg5())));
				}else{
					lqInfo.setLlg5("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg6()).length()>0){
					lqInfo.setLlg6(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg6())));
				}else{
					lqInfo.setLlg6("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlg7()).length()>0){
					lqInfo.setLlg7(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlg7())));
				}else{
					lqInfo.setLlg7("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlf1()).length()>0){
					lqInfo.setLlf1(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlf1())));
				}else{
					lqInfo.setLlf1("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLlf2()).length()>0){
					lqInfo.setLlf2(String.format("%1$.1f",Double.parseDouble(lqxixx.getLlf2())));
				}else{
					lqInfo.setLlf2("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
			
			try{
				if(StringUtil.Null2Blank(lqxixx.getLltjj()).length()>0){
					lqInfo.setLltjj(String.format("%1$.1f",Double.parseDouble(lqxixx.getLltjj())));
				}else{
					lqInfo.setLltjj("");
				}
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
		}
		
		lqInfo.setBh(lqxixx.getBh());
		lqInfo.setKhmc(lqxixx.getKhmc());
		lqInfo.setGcmc(lqxixx.getGcmc());
		lqInfo.setSgbw(lqxixx.getSgbw());
		lqInfo.setLjsl(lqxixx.getLjsl());
		lqInfo.setBeiy1(lqxixx.getBeiy1());
		lqInfo.setBeiy2(lqxixx.getBeiy2());
		lqInfo.setBeiy3(lqxixx.getBeiy3());
		lqInfo.setShebeibianhao(lqxixx.getShebeibianhao());
		lqInfo.setKehuduanbianhao(lqxixx.getKehuduanbianhao());
		return lqInfo;		
	}
	
	//延度试验
	public Yandudata getYanDuData(String date_time){
		return yanDuDataDAO.getYanDuData(date_time);
	}
	public List<Yandudata> GetYanDuDataTimeList(){
		return yanDuDataDAO.GetYanDuDataTimeList();
	}
	
	public T_YanDuPageMode viewlist(String testNo,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize){
		return t_YanDuDAO.viewlist(testNo, startTimeOne, endTimeOne, BiaoDuanId, offset, pagesize);
	}
	public T_YanDu getT_YanDu(String testNo){
		return t_YanDuDAO.getT_YanDu(testNo);
	}
   public void saveOrUpdate(T_YanDu bdxx){
		
	   t_YanDuDAO.saveOrUpdate(bdxx);
	}
	
	public void del(T_YanDu bdid){
		t_YanDuDAO.delete(bdid);
	}
	public void deleteByKey(String id){
		t_YanDuDAO.deleteByKey(id);
	}

	
	public HunnintuPageMode viewlist(String shebeibianhao,String gongchenghao,String startTimeOne,
			String endTimeOne,String jiaozhubuwei, Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewlist(shebeibianhao,gongchenghao,startTimeOne,endTimeOne,jiaozhubuwei, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode viewspeedlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewspeedlist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode viewNianyaTemplist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewNianyaTemplist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode viewChuliaokoulist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewChuliaokoulist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode viewwddlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,int offset, int pagesize) {
		return hntDAO.viewwddlist(shebeibianhao,startTimeOne,endTimeOne,offset, pagesize);
	}
	
	public GenericPageMode viewydlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,int offset, int pagesize) {
		return hntDAO.viewydlist(shebeibianhao,startTimeOne,endTimeOne,offset, pagesize);
	}
	
	public GenericPageMode viewtanpuspeedlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewtanpuspeedlist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode viewtmplist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewtmplist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode viewtjjlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewtjjlist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode lqviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize, int queryalldata, String peifan) {
		return lqDAO.lqviewlist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize, queryalldata,peifan);
	}
	
	public GenericPageMode lqphbviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize, String peifan) {
		return lqDAO.lqphbviewlist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize,peifan);
	}
	
	public GenericPageMode lqmanualphbviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize, String peifan) {
		return lqDAO.lqmanualphbviewlist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize,peifan);
	}
	
	public GenericPageMode lqchaobiaoviewlist(LiqingziduancfgView lqisshow, Integer chaobiaolx,String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return lqDAO.lqchaobiaoviewlist(lqisshow, chaobiaolx,shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode lqchaobiaomanualviewlist(LiqingziduancfgView lqisshow, Integer chaobiaolx,String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize,Integer cllx,String bianhao) {
		return lqDAO.lqchaobiaomanualviewlist(lqisshow, chaobiaolx,shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize,cllx,bianhao);
	}
	
	public GenericPageMode viewrunstatelist(String shebeibianhao,String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return lqDAO.viewrunstatelist(shebeibianhao,startTimeOne,endTimeOne, biaoduan, xiangmubu, 
				fn, bsid, offset, pagesize);
	}
	public HunnintujieguoPageMode viewjieguolist(String shebeibianhao,String gongchenghao,String startTimeOne,
			String endTimeOne,String jiaozhubuwei, Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewjieguolist(shebeibianhao,gongchenghao,startTimeOne,endTimeOne,jiaozhubuwei, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode lqviewjieguolist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return lqDAO.lqviewjieguolist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public List<LiqingView> lqzhfxlist(String startTime,String endTime,String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		return lqDAO.lqzhfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,cnfxlx,fn,bsid);
	}
	
	public List<LiqingView> lqmainlistzhfxlist(String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		List<TopLiqingView> toplqlist = toplqDAO.loadAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
		if (toplqlist.size() > 0) {
			TopLiqingView toplq = toplqlist.get(0);
			if (null != toplq) {
				nowtime = toplq.getBaocunshijian();
			}
		}
		try {
			day.setTime(sdf.parse(nowtime));
		} catch (Exception e) {
		}		
		day.add(Calendar.DATE, -6);
    	begintime = sdf.format(day.getTime());
		return lqDAO.lqzhfxlist(begintime, nowtime, shebeibianhao,biaoduan,xiangmubu,cnfxlx,fn,bsid);
	}
	
	public LiqingView lqstatisticsinfo(){
		return lqDAO.lqstatisticsinfo();
	}


	
	public HntbhzziduancfgView gethntcfgfield(String shebeibianhao) {
		HntbhzziduancfgView hview = hntcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "1");
		if (null == hview) {
			hview = hntcfgviewDAO.findByGprsbhandleixin("all", "100");			
		}
		return hview;
	}
	
	public LiqingziduancfgView getlqcfgfield(String shebeibianhao) {
		LiqingziduancfgView lqview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "1");
		if (null == lqview) {
			lqview = lqcfgviewDAO.findByGprsbhandleixin("all", "100");			
		}
		return lqview;
	}

	public HntbhzziduancfgView gethntcfgisShow(String shebeibianhao) {
		HntbhzziduancfgView hview = hntcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "2");
		if (null == hview) {
			hview = hntcfgviewDAO.findByGprsbhandleixin("all", "101");			
		}
		return hview;
	}
	
	public LiqingziduancfgView getlqcfgisShow(String shebeibianhao) {
		LiqingziduancfgView lqview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "2");
		if (null == lqview) {
			lqview = lqcfgviewDAO.findByGprsbhandleixin("all", "101");			
		}
		return lqview;
	}
	
	public LiqingziduancfgView getlqcfgisShow2(String shebeibianhao) {
		LiqingziduancfgView lqview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "21");
		if (null == lqview) {
			lqview = lqcfgviewDAO.findByGprsbhandleixin("all", "20");			
		}
		return lqview;
	}
	
	public LiqingziduancfgView getlqcfgisShow4(String shebeibianhao) {
		LiqingziduancfgView lqview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "41");
		if (null == lqview) {
			lqview = lqcfgviewDAO.findByGprsbhandleixin("all", "40");			
		}
		return lqview;
	}
	
	public LiqingziduancfgView getlqchaobiaoisshowcfg(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "31");
		} else {
			return lqcfgviewDAO.findByGprsbhandleixin("all", "30");
		}
	}
	
	public Liqingziduancfg getlqchaobiaocfg(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "31");
		} else {
			return lqcfgDAO.findByGprsbhandleixin("all", "30");
		}
	}
	
	public void saveChaobiaocfg(Liqingziduancfg lqcfg) {
		lqcfgDAO.saveOrUpdate(lqcfg);
	}
	
	//混凝土拌和站拌和时间查询走势图
	public String getHntBanheshijianXml(List<HunnintuView> hv) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='拌和时间走势图' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='s'> ");
		strXML.append("<categories>");
		for (HunnintuView hunnintuView : hv) {
			strXML.append("<category label='");
			strXML.append(hunnintuView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		strXML.append("<dataset color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C'>");
		for (HunnintuView hunnintuView : hv) {
			strXML.append("<set value='");
			strXML.append(hunnintuView.getJiaobanshijian());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartlqshijian", 1000, 200, false, false);
	}	
	
	//沥青拌和站拌和时间查询走势图
	public String getlqBanheshijianXml(List<LiqingView> hv) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='湿拌时间走势图(s)' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='60'> ");
		strXML.append("<categories>");
		for (LiqingView hunnintuView : hv) {
			strXML.append("<category label='");
//			strXML.append(hunnintuView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		strXML.append("<dataset color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C' >");
		for (LiqingView hunnintuView : hv) {
			strXML.append("<set value='");
			strXML.append(hunnintuView.getJbsj());
			strXML.append("' toolText='"+hunnintuView.getBaocunshijian()+","+hunnintuView.getJbsj()+"'/>");
		}
		strXML.append("</dataset>");
		//加上下线
		if(hv!=null){
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(30);
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(30);
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(50);
			strXML.append("' color='00FF00' displayValue='");
			strXML.append(50);
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
		    strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("预警下限");
			strXML.append("'>");
			for (LiqingView hunnintuView : hv) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(30);	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  color= '00FF00' seriesName='");
			strXML.append("预警上限");
			strXML.append("'>");
			for (LiqingView hunnintuView : hv) {
				strXML.append("<set color='00FF00' dashed='1'  value='");
				strXML.append(50);	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}
				
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartlqshijian", 1000, 200, false, false);
	}	
	
	public void appendSetXml(StringBuilder strb, String value) {
		float tf = -1;
		tf = StringUtil.StrToFloat(value);
		if (tf > 0) {
			strb.append("<set value='");
			strb.append(tf);
			strb.append("'/>");
		} else {
			strb.append("<set />");
		}		
	}
	
	
	public void appendSetXml3(StringBuilder strb, String value,String cailiao,String shijian) {
		float tf = -1;
		tf = StringUtil.StrToFloat(value);
		if (tf > 0) {
			strb.append("<set value='");
			strb.append(tf);
			strb.append("' toolText='"+cailiao+","+shijian+","+value+"'/>");
		} else {
			strb.append("<set />");
		}		
	}
	
	public void appendSetXml5(StringBuilder strb, String value,String shijian) {
		float tf = -1;
		tf = StringUtil.StrToFloat(value);
		if (tf > 0) {
			strb.append("<set value='");
			strb.append(tf);
			strb.append("' toolText='"+shijian+","+value+"'/>");
		} else {
			strb.append("<set />");
		}		
	}
	
	public void appendSetXml2(StringBuilder strb, String value) {
		float tf = -1;
		tf = StringUtil.StrToFloat(value);
		if (tf > 0) {
			strb.append("<set color='00FF40'  dashed='1' value='");
			strb.append(tf);
			strb.append("'/>");
		} else {
			strb.append("<set />");
		}		
	}
	
	//混凝土拌和站材料用量查询走势图
	public String getHntCailiaoXml(List<HunnintuView> hv, HntbhzziduancfgView hntbhzField, HntbhzziduancfgView hntbhzisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='材料用量走势图' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='kg'> ");
		strXML.append("<categories>");
		for (HunnintuView hunnintuView : hv) {
			strXML.append("<category label='");
			strXML.append(hunnintuView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != hntbhzField && null != hntbhzisShow) {
			if (hntbhzisShow.getShui1_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui1_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShui1_shijizhi());					
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShui1_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui1_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShui1_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShui2_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui2_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShui2_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShui2_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui2_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShui2_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShuini1_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini1_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShuini1_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShuini1_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini1_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShuini1_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShuini2_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini2_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShuini2_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShuini2_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini2_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShuini2_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getSha1_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha1_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getSha1_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getSha1_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha1_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getSha1_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShi1_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi1_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShi1_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShi1_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi1_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShi1_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShi2_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi2_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShi2_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getShi2_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi2_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getShi2_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getSha2_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha2_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getSha2_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getSha2_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha2_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getSha2_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getGuliao5_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getGuliao5_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getGuliao5_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getGuliao5_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getGuliao5_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getGuliao5_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getKuangfen3_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getKuangfen3_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getKuangfen3_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getKuangfen3_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getKuangfen3_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getKuangfen3_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getFeimeihui4_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFeimeihui4_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getFeimeihui4_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getFeimeihui4_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFeimeihui4_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getFeimeihui4_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getFenliao5_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao5_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getFenliao5_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getFenliao5_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao5_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getFenliao5_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getFenliao6_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao6_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getFenliao6_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getFenliao6_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao6_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getFenliao6_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji1_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji1_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji1_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji1_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji1_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji1_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji2_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji2_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji2_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji2_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji2_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji2_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji3_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji3_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji3_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji3_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji3_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji3_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji4_shijizhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji4_shijizhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji4_shijizhi());
				}
				strXML.append("</dataset>");	
			}
			
			if (hntbhzisShow.getWaijiaji4_lilunzhi().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji4_lilunzhi());
				strXML.append("'>");
				for (HunnintuView hunnintuView : hv) {
					appendSetXml(strXML, hunnintuView.getWaijiaji4_lilunzhi());
				}
				strXML.append("</dataset>");	
			}
			
		} else {
			strXML.append("<dataset seriesName='水'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getShui1_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='粉料1'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getShuini1_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='粉料2'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getShuini2_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='粉料3'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getKuangfen3_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='粉料4'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getFeimeihui4_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='骨料1'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getSha1_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='骨料2'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getShi1_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='骨料3'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getShi2_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='骨料4'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getSha2_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");		
			
			strXML.append("<dataset seriesName='外加剂1'>");
			for (HunnintuView hunnintuView : hv) {
				strXML.append("<set value='");
				strXML.append(hunnintuView.getWaijiaji1_shijizhi());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
		}

		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijian", 1000, 300, false, false);
	}	
	
	//温度速度查询走势图
	public String getTmpXml(List<TemperaturedataView> tempdata,String shebeibianhao) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		if(StringUtil.Null2Blank(shebeibianhao).length()==0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='摊铺机温度走势图(°C)' subcaption='(");
		}else{
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+tempdata.get(0).getBanhezhanminchen()+"走势图(°C)' subcaption='(");
		}		
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getTmpshijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='20' numDivLines='4' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='100' yAxisMaxValue='200'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (TemperaturedataView tmp : tempdata) {
			strXML.append("<category showlabel='1' label='");
//			strXML.append(tmp.getTmpshijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset  color='0000FF' seriesName='温度'>");
		for (TemperaturedataView tmp : tempdata) {	
			strXML.append("<set color='0000FF'   value='");
			strXML.append(tmp.getTmpdata());	
            strXML.append("' toolText='"+tmp.getTmpshijian()+","+tmp.getTmpdata()+"'/>");
		}
		strXML.append("</dataset>");	
		}		
		Xcsmscfg xc=getXccfg(tempdata.get(0).getTmpno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' color='00FF00' displayValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("道路石油沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getTmplow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  color= '00FF00' seriesName='");
			strXML.append("改性沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='00FF00' dashed='1'  value='");
				strXML.append(xc.getTmphigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijian",1000, 300, false, false);
	}
	
	//温度速度查询走势图
	public String getSpeedXml(List<SpeeddataView> speeddata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='速度走势图' subcaption='(");
		if (null != speeddata && speeddata.size()>0) {
			strXML.append(speeddata.get(0).getShijian());
			strXML.append("至");
			strXML.append(speeddata.get(speeddata.size()-1).getShijian());
			datasize = speeddata.size()/5;
		}
		strXML.append(")' lineThickness='2' showValues='1' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='20'> ");
		strXML.append("<categories>");
		if (null != speeddata && speeddata.size()>0) {
			for (SpeeddataView speed : speeddata) {
				strXML.append("<category label='");
				strXML.append(speed.getShijian());
				strXML.append("'/>");
			}
		}
		strXML.append("</categories>");		
		if (null != speeddata && speeddata.size()>0) {
			strXML.append("<dataset seriesName='速度'>");
			for (SpeeddataView speed : speeddata) {
				appendSetXml(strXML, speed.getSudu());					
			}
			strXML.append("</dataset>");
		}
		Xcsmscfg xc=getXccfg(speeddata.get(0).getGpsno());
		if(xc!=null){
			strXML.append("<trendlines>");
			strXML.append("<line startValue='"+xc.getSpeedlow()+"' color='0000FF' displayValue='初级下线,"+xc.getSpeedlow()+"' showOnTop='1'/>");
			strXML.append("<line startValue='"+xc.getSpeedhigh()+"' color='80FF80' displayValue='初级上线,"+xc.getSpeedhigh()+"' showOnTop='1'/>");
			strXML.append("<line startValue='"+xc.getSpeedlow1()+"' color='8000FF' displayValue='中级下线,"+xc.getSpeedlow1()+"' showOnTop='1'/>");
			strXML.append("<line startValue='"+xc.getSpeedhigh1()+"' color='000000' displayValue='中级上线,"+xc.getSpeedhigh1()+"' showOnTop='1'/>");
			strXML.append("<line startValue='"+xc.getSpeedlow2()+"' color='400080' displayValue='高级下线,"+xc.getSpeedlow2()+"' showOnTop='1'/>");
			strXML.append("<line startValue='"+xc.getSpeedhigh2()+"' color='FF0000' displayValue='高级上线,"+xc.getSpeedhigh2()+"' showOnTop='1'/>");
			strXML.append("</trendlines>");			
		}
		strXML.append(" <styles>");		
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijian", 1000, 300, false, false);
	}
	
	//温度速度查询走势图
	public String getnianyaSpeedXml(List<SpeeddataView> speeddata,String shebeibianhao) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		if(StringUtil.Null2Blank(shebeibianhao).length()==0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='碾压速度走势图(km/h)' subcaption='(");
		}else{
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+speeddata.get(0).getBanhezhanminchen()+"走势图(km/h)' subcaption='(");
		}
		if (null != speeddata && speeddata.size()>0) {
			strXML.append(speeddata.get(0).getShijian());
			strXML.append("至");
			strXML.append(speeddata.get(speeddata.size()-1).getShijian());
			datasize = speeddata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='7'> ");
		strXML.append("<categories>");
		if (null != speeddata && speeddata.size()>0) {
			for (SpeeddataView speed : speeddata) {
				strXML.append("<category label='");
//				strXML.append(speed.getShijian());
				strXML.append("'/>");
			}
		}
		strXML.append("</categories>");		
		if (null != speeddata && speeddata.size()>0) {
			strXML.append("<dataset seriesName='速度' color='0000FF'>");
			for (SpeeddataView speed : speeddata) {
				appendSetXml5(strXML, speed.getSudu(),speed.getShijian());					
			}
			strXML.append("</dataset>");
		}
		Xcsmscfg xc=getXccfg(speeddata.get(0).getGpsno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("碾压速度预警线");
			strXML.append("'>");
			for (SpeeddataView speed : speeddata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedlow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  anchorBorderColor = 'FF3333' seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (SpeeddataView speed : speeddata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedhigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}
		strXML.append(" <styles>");		
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijian", 1000, 300, false, false);
	}
	
	//大屏碾压速度查询走势图
	public String getDaPingnianyaSpeedXml(List<SpeeddataView> speeddata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='碾压速度走势图(km/h)' subcaption='(");
		if (null != speeddata && speeddata.size()>0) {
			strXML.append(speeddata.get(0).getShijian());
			strXML.append("至");
			strXML.append(speeddata.get(speeddata.size()-1).getShijian());
			datasize = speeddata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='7'> ");
		strXML.append("<categories>");
		if (null != speeddata && speeddata.size()>0) {
			for (SpeeddataView speed : speeddata) {
				strXML.append("<category label='");
//				strXML.append(speed.getShijian());
				strXML.append("'/>");
			}
		}
		strXML.append("</categories>");		
		if (null != speeddata && speeddata.size()>0) {
			strXML.append("<dataset seriesName='速度' color='0000FF'>");
			for (SpeeddataView speed : speeddata) {
				appendSetXml5(strXML, speed.getSudu(),speed.getShijian());					
			}
			strXML.append("</dataset>");
		}
		Xcsmscfg xc=getXccfg(speeddata.get(0).getGpsno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("碾压速度预警线");
			strXML.append("'>");
			for (SpeeddataView speed : speeddata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedlow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  anchorBorderColor = 'FF3333' seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (SpeeddataView speed : speeddata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedhigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}
		strXML.append(" <styles>");		
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapingnianyasudu", 675, 325, false, false);
	}
	
	//添加剂查询走势图
	public String getTjjXml(List<TjjdataView> tjjdata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='添加剂走势图' subcaption='(");
		if (null != tjjdata && tjjdata.size()>0) {
			strXML.append(tjjdata.get(0).getTjjshijian());
			strXML.append("至");
			strXML.append(tjjdata.get(tjjdata.size()-1).getTjjshijian());
			datasize = tjjdata.size()/5;
		} 
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5'> ");
		strXML.append("<categories>");
		if (null != tjjdata && tjjdata.size()>0) {
		for (TjjdataView tjj : tjjdata) {
			strXML.append("<category label='");
			strXML.append(tjj.getTjjshijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tjjdata && tjjdata.size()>0) {
		strXML.append("<dataset seriesName='重量'>");
		for (TjjdataView tjj : tjjdata) {
			appendSetXml(strXML, tjj.getTjjdata());					
		}		
		strXML.append("</dataset>");
		}
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "charttjj", 1000, 300, false, false);
	}
	
	//沥青拌和站材料用量查询走势图
	public String getLiqingCailiaoXml(List<LiqingView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='材料用量走势图(kg)' subcaption='(");
		strXML.append(hv.get(0).getShijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getShijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='1200'> ");
		strXML.append("<categories>");
		for (LiqingView lqView : hv) {
			strXML.append("<category label='");
//			strXML.append(lqView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != lqField && null != lqisShow) {			
			if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg1());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg1(),lqField.getSjg1(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg2());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg2(),lqField.getSjg2(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg3());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg3(),lqField.getSjg3(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg4());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg4(),lqField.getSjg4(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg5());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg5(),lqField.getSjg5(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg6());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg6(),lqField.getSjg6(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg7());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjg7(),lqField.getSjg7(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf1());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjf1(),lqField.getSjf1(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf2());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjf2(),lqField.getSjf2(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjlq());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjlq(),lqField.getSjlq(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjtjj());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjtjj(),lqField.getSjtjj(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
		} 		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijian", 1000, 300, false, false);
	}	
	
	//沥青拌和站材料温度查询走势图
	public String getLiqingTempXml(List<LiqingView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='温度走势图(°C)' subcaption='(");
		strXML.append(hv.get(0).getShijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getShijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='200' yAxisMinValue='100'> ");
		strXML.append("<categories>");
		for (LiqingView lqView : hv) {
			strXML.append("<category label='");
//			strXML.append(lqView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != lqField && null != lqisShow) {
			if (lqisShow.getGlwd().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getGlwd());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getGlwd(),lqField.getGlwd(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getLqwd().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getLqwd());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getLqwd(),lqField.getLqwd(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getClwd().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getClwd());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getClwd(),lqField.getClwd(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}									
		} 		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijianTemp", 1000, 300, false, false);
	}	
	
	//沥青拌和站材料用量查询走势图
	public String getLiqingCailiaoXmlysb(List<LiqingView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='油石比走势图(%)' subcaption='(");
		strXML.append(hv.get(0).getShijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getShijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='3' yAxisMaxValue='7'> ");
		strXML.append("<categories>");
		for (LiqingView lqView : hv) {
			strXML.append("<category label='");
//			strXML.append(lqView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != lqField && null != lqisShow) {
			if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjysb());
				strXML.append("'>");
				for (LiqingView lqView : hv) {
					appendSetXml3(strXML, lqView.getSjysb(),lqField.getSjysb(),lqView.getShijian());					
				}
				strXML.append("</dataset>");	
			}
		} 
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartysb", 1000, 300, false, false);
	}	
	
	
	public HunnintuView materiallist(String gongchengmingcheng,String jiaozhubuwei,
			String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		return hntDAO.materiallist(gongchengmingcheng, jiaozhubuwei,
				startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid);
	}
	
	public LiqingphbView lqmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		return lqDAO.lqmateriallist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid);
	}
	
	public Shuiwenxixxdanjia SwcalTotaljiage(ShuiwenphbView swxixx, String sbbh){
		Shuiwenxixxdanjia danjia =null;
		ArrayList<Shuiwenxixxdanjia> jglist = null;
		if (StringUtil.Null2Blank(sbbh).length()>0) {
			jglist = (ArrayList<Shuiwenxixxdanjia>) swdanjiaDAO.find("from Shuiwenxixxdanjia where djmoren='1' and djshebeibianhao=?", sbbh);
		} else {
			jglist = (ArrayList<Shuiwenxixxdanjia>) swdanjiaDAO.find("from Shuiwenxixxdanjia where djmoren='1'");
		}
		
		if (null != jglist && jglist.size() > 0) {
			danjia = jglist.get(0);
			if (null != danjia) {
				try {
					danjia.setDjg1(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg1())*StringUtil.StrToZero(swxixx.getSjgl1())/1000));
					danjia.setDjg2(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg2())*StringUtil.StrToZero(swxixx.getSjgl2())/1000));
					danjia.setDjg3(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg3())*StringUtil.StrToZero(swxixx.getSjgl3())/1000));
					danjia.setDjg4(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg4())*StringUtil.StrToZero(swxixx.getSjgl4())/1000));
					danjia.setDjg5(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg5())*StringUtil.StrToZero(swxixx.getSjgl5())/1000));
					danjia.setDjf1(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjf1())*StringUtil.StrToZero(swxixx.getSjfl1())/1000));
					danjia.setDjf2(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjf2())*StringUtil.StrToZero(swxixx.getSjfl2())/1000));
				} catch (Exception e) {}
			}
		}
		return danjia;
	}
	
	public List<HunnintuView> hntsclhslist(String startTime,String endTime,String shebeibianhao, 
			String gongchengmingcheng, String jiaozuobuwei, String qiangdudengji, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid){
		return hntDAO.hntsclhslist(startTime, endTime, shebeibianhao,gongchengmingcheng,
				jiaozuobuwei,qiangdudengji,biaoduan,xiangmubu,fn,bsid);
	}
	
	public List<LiqingView> lqsclhslist(String startTime,String endTime,String shebeibianhao, 			 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, String peifan){
		return lqDAO.lqsclhslist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid,peifan);
	}
	
	public List<LiqingView> lqscsjjcphb(String startTime,String endTime,String shebeibianhao, 			 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, String peifan){
		return lqDAO.lqscsjjcphb(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid,peifan);
	}
	
	public List<LiqingView> lqscsjjcmanualphb(String startTime,String endTime,String shebeibianhao, 			 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, String peifan){
		return lqDAO.lqscsjjcmanualphb(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid,peifan);
	}
	
	public List<HunnintuView> hntcnfxlist(String startTime,String endTime,String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		return hntDAO.hntcnfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,cnfxlx,fn,bsid);
	}
	
	public List<LiqingView> lqcnfxlist(String startTime,String endTime,String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		return lqDAO.lqcnfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,cnfxlx,fn,bsid);
	}
	
	public HunnintuView hntcnfxdetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid){
		return hntDAO.hntcnfxdetail(startTime, endTime, shebeibianhao,biaod,xiangmb,myvar1,myvar2,cnfxlx,fn,bsid);
	}
	
	public LiqingView lqcnfxdetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid){
		return lqDAO.lqcnfxdetail(startTime, endTime, shebeibianhao,biaod,xiangmb,myvar1,myvar2,cnfxlx,fn,bsid);
	}
	
	public HunnintuView hntcnfxlilundetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid){
		return hntDAO.hntcnfxlilundetail(startTime, endTime, shebeibianhao,biaod,xiangmb,myvar1,myvar2,cnfxlx,fn,bsid);
	}
	
	public LiqingView lqcnfxlilundetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid){
		return lqDAO.lqcnfxlilundetail(startTime, endTime, shebeibianhao,biaod,xiangmb,myvar1,myvar2,cnfxlx,fn,bsid);
	}
	
	public XCData1PageMode xcdata1viewlist(Integer gprsDeviceId,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize) {
		return xc1DAO.viewlist(gprsDeviceId, startTimeOne, endTimeOne, BiaoDuanId, offset, pagesize);
	}
	public XCData2PageMode xcdata2viewlist(Integer gprsDeviceId,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize) {
		return xc2DAO.viewlist(gprsDeviceId, startTimeOne, endTimeOne, BiaoDuanId, offset, pagesize);
	}
	
	public GenericPageMode xcdata3viewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,
			String fn, int bsid, int offset, int pagesize) {
		return xc3DAO.viewgpslist(shebeibianhao, startTimeOne, endTimeOne, fn, bsid, offset, pagesize);
	}
	
	//水稳
	public GenericPageMode viewswlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize,String llbuwei) {
		return hntDAO.viewswlist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize,llbuwei);
	}
	
	//水稳
	public String getSwCailiaoXml(List<ShuiwenxixxView> swcl) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='材料用量走势图' subcaption='(");
		strXML.append(swcl.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(swcl.get(swcl.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(swcl.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='kg'> ");
		strXML.append("<categories>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<category label='");
			strXML.append(sw.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");

		strXML.append("<dataset seriesName='理论骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='理论骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
	
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartswcl", 1000, 300, false, false);
	}
		
	//水稳
	public String getSwCailiaoXml1(List<ShuiwenxixxView> swcl,String swField[],String swShow[]) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='材料用量走势图' subcaption='(");
		strXML.append(swcl.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(swcl.get(swcl.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(swcl.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='kg'> ");
		strXML.append("<categories>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<category label='");
			strXML.append(sw.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		/*
			strXML.append("<dataset seriesName='日产1'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getSjgl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产2'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getLlgl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产3'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getPergl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产4'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getSjshui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产5'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getLlshui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产6'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getPershui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
		*/	
		
		
		if (null != swField && null != swShow) {
			for(int i=0;i<swField.length;i++){
				//19-26.6mm
				if(swField[i].equalsIgnoreCase("19-26.6mm")&&swShow[i].equalsIgnoreCase("1")){
						strXML.append("<dataset seriesName='");
						strXML.append(swField[i]+"理论");
						strXML.append("'>");
						for (ShuiwenxixxView sw : swcl) {
							strXML.append("<set value='");
							strXML.append(sw.getLlgl1());
							strXML.append("'/>");
						}
						strXML.append("</dataset>");
						
						
						strXML.append("<dataset seriesName='");
						strXML.append(swField[i]+"实际");
						strXML.append("'>");
						for (ShuiwenxixxView sw : swcl) {
							strXML.append("<set value='");
							strXML.append(sw.getSjgl1());
							strXML.append("'/>");
						}
						strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("9.5-19mm")&&swShow[i].equalsIgnoreCase("1")){
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlgl2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getSjgl2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				
				if(swField[i].equalsIgnoreCase("4.75-9.5mm")&&swShow[i].equalsIgnoreCase("1")){
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlgl3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getSjgl3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("0-4.75mm")&&swShow[i].equalsIgnoreCase("1")){
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlgl4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getSjgl4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("水泥")&&swShow[i].equalsIgnoreCase("1")){
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlfl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenxixxView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getSjfl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				
			}
			
		}
		
		/*
		strXML.append("<dataset seriesName='理论骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='理论骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		*/
		
		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartswcl", 1000, 300, false, false);
	}
	
	
	
	//水稳配合比误差走势图---与手动输入的配比做对比
	public String getSwmanualphbwcXml(List<ShuiwenmanualphbView> swcl,String swField[],String swShow[]) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='配比误差走势图' subcaption='(");
		strXML.append(swcl.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(swcl.get(swcl.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		//strXML.append(" numDivLines='10' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		//strXML.append("labelStep='");
		//strXML.append(swcl.size()/5);
		//strXML.append("' ");
		strXML.append(" numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='%'> ");
		strXML.append("<categories>");
		for (ShuiwenmanualphbView sw : swcl) {
			strXML.append("<category label='");
			strXML.append(sw.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		/*
			strXML.append("<dataset seriesName='日产1'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getSjgl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产2'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getLlgl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产3'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getPergl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产4'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getSjshui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产5'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getLlshui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产6'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getPershui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
		*/	
		
		
		if (null != swField && null != swShow) {
			for(int i=0;i<swField.length;i++){
				//19-26.6mm
				if(swField[i].equalsIgnoreCase("0-4.75mm")&&swShow[i].equalsIgnoreCase("1")){
					
				    strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"上限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg1upper());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"下限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg1floor());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("9.5-19mm")&&swShow[i].equalsIgnoreCase("1")){
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"上限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg2upper());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"下限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg2floor());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				
				if(swField[i].equalsIgnoreCase("19-31.5mm")&&swShow[i].equalsIgnoreCase("1")){
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"上限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg3upper());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"下限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg3floor());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("4.75-9.5mm")&&swShow[i].equalsIgnoreCase("1")){
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"上限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg4upper());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"下限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlg4floor());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("水泥")&&swShow[i].equalsIgnoreCase("1")){
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlf1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjfl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"上限");
					strXML.append("'>");
					for (ShuiwenmanualphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getLlf1upper());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				
			}
			
		}
		
		/*
		strXML.append("<dataset seriesName='理论骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='理论骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		*/
		
		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartswcl", 1000, 300, false, false);
	}
	
	//水稳配合比误差走势图---与采集到的配比做对比
	public String getSwphbwcXml(List<ShuiwenphbView> swcl,String swField[],String swShow[]) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='配比误差走势图' subcaption='(");
		strXML.append(swcl.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(swcl.get(swcl.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(swcl.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='kg'> ");
		strXML.append("<categories>");
		for (ShuiwenphbView sw : swcl) {
			strXML.append("<category label='");
			strXML.append(sw.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		/*
			strXML.append("<dataset seriesName='日产1'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getSjgl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产2'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getLlgl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产3'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getPergl4());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产4'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getSjshui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产5'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getLlshui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
			
			strXML.append("<dataset seriesName='日产6'>");
			for (ShuiwenxixxView sw : swcl) {
				strXML.append("<set value='");
				strXML.append(sw.getPershui());
				strXML.append("'/>");
			}
			strXML.append("</dataset>");
		*/	
		
		
		if (null != swField && null != swShow) {
			for(int i=0;i<swField.length;i++){
				//19-26.6mm
				if(swField[i].equalsIgnoreCase("19-26.6mm")&&swShow[i].equalsIgnoreCase("1")){
					/*
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPerllgl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					*/
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"误差");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getWgl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("9.5-19mm")&&swShow[i].equalsIgnoreCase("1")){
					/*
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPerllgl2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					*/
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"误差");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getWgl2());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				
				if(swField[i].equalsIgnoreCase("4.75-9.5mm")&&swShow[i].equalsIgnoreCase("1")){
					/*
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPerllgl3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					*/
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"误差");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getWgl3());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("0-4.75mm")&&swShow[i].equalsIgnoreCase("1")){
					/*
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPerllgl4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjgl4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					*/
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"误差");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getWgl4());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				if(swField[i].equalsIgnoreCase("水泥")&&swShow[i].equalsIgnoreCase("1")){
					/*
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"理论");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPerllfl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"实际");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getPersjfl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
					*/
					strXML.append("<dataset seriesName='");
					strXML.append(swField[i]+"误差");
					strXML.append("'>");
					for (ShuiwenphbView sw : swcl) {
						strXML.append("<set value='");
						strXML.append(sw.getWfl1());
						strXML.append("'/>");
					}
					strXML.append("</dataset>");
				}
				
				
			}
			
		}
		
		/*
		strXML.append("<dataset seriesName='理论骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='理论骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");

		strXML.append("<dataset seriesName='理论骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getLlgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料1'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl1());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料2'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl2());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料3'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl3());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='实际骨料4'>");
		for (ShuiwenxixxView sw : swcl) {
			strXML.append("<set value='");
			strXML.append(sw.getSjgl4());
			strXML.append("'/>");
		}
		strXML.append("</dataset>");
		
		*/
		
		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartswcl", 1000, 300, false, false);
	}
	
	
	//温度速度查询走势图
		public String getTempXml(List<TemperaturedataView> tempdata,String shebeibianhao) {
			StringBuilder strXML = new StringBuilder("");
			int datasize = 3;
			if(StringUtil.Null2Blank(shebeibianhao).length()==0){
				strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='摊铺机温度走势图(°C)' subcaption='(");
			}else{
				strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+StringUtil.Null2Blank(tempdata.get(0).getBanhezhanminchen())+"温度走势图(°C)' subcaption='(");
			}
			if (null != tempdata && tempdata.size()>0) {
				strXML.append(tempdata.get(0).getTmpshijian());
				strXML.append("至");
				strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
				datasize = tempdata.size()-1;
			}
			strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
			strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
			strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
			strXML.append("labelStep='");
			strXML.append(datasize);
			strXML.append("' numvdivlines='20' numDivLines='4' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
			strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='100' yAxisMaxValue='200'> ");
			strXML.append("<categories>");
			if (null != tempdata && tempdata.size()>0) {
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<category showlabel='1' label='");
//				strXML.append(tmp.getTmpshijian());
				strXML.append("'/>");
			}
			}
			strXML.append("</categories>");
			if (null != tempdata && tempdata.size()>0) {
			strXML.append("<dataset  color='0000FF' seriesName='温度'>");
			for (TemperaturedataView tmp : tempdata) {	
				strXML.append("<set color='0000FF'   value='");
				strXML.append(tmp.getTmpdata());	
	            strXML.append("' toolText='"+tmp.getTmpshijian()+","+tmp.getTmpdata()+"'/>");
			}
			strXML.append("</dataset>");	
			}		
			Xcsmscfg xc=getXccfg(tempdata.get(0).getTmpno());
			if(xc!=null){
				//画上下限
				strXML.append("<trendlines>");
				strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
				strXML.append(xc.getTmplow());
				strXML.append("' color='FF3333' displayValue='");
				strXML.append(xc.getTmplow());
				strXML.append("' showOnTop='1' dashed='1'/>");
				strXML.append("<line alpha='0'   startValue='");
				strXML.append(xc.getTmphigh());
				strXML.append("' color='00FF00' displayValue='");
				strXML.append(xc.getTmphigh());
				strXML.append("' showOnTop='1' dashed='1' />");
			    strXML.append("</trendlines>");
			    
				strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
				strXML.append("道路石油沥青");
				strXML.append("'>");
				for (TemperaturedataView tmp : tempdata) {
					strXML.append("<set color='FF3333' dashed='1'  value='");
					strXML.append(xc.getTmplow());	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");	
				
				strXML.append("<dataset  anchorRadius = '0'  color= '00FF00' seriesName='");
				strXML.append("改性沥青");
				strXML.append("'>");
				for (TemperaturedataView tmp : tempdata) {
					strXML.append("<set color='00FF00' dashed='1'  value='");
					strXML.append(xc.getTmphigh());	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");
			}		
			strXML.append(" <styles>");
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhechuliaotemp", 1000, 300, false, false);
		}

	
//	//温度速度查询走势图
//	public String getTempXml(List<TemperaturedataView> tempdata) {
//		StringBuilder strXML = new StringBuilder("");
//		int datasize = 3;
//		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='温度走势图(°C)' subcaption='(");
//		if (null != tempdata && tempdata.size()>0) {
//			strXML.append(tempdata.get(0).getTmpshijian());
//			strXML.append("至");
//			strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
//			datasize = tempdata.size()-1;
//		}
//		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
//		strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
//		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
//		strXML.append("labelStep='");
//		strXML.append(datasize);
//		strXML.append("' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
//		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='200' numberSuffix='°C'> ");
//		strXML.append("<categories>");
//		if (null != tempdata && tempdata.size()>0) {
//		for (TemperaturedataView tmp : tempdata) {
//			strXML.append("<category label='");
//			strXML.append(tmp.getTmpshijian());
//			strXML.append("'/>");
//		}
//		}
//		strXML.append("</categories>");
//		if (null != tempdata && tempdata.size()>0) {
//		strXML.append("<dataset seriesName='温度'>");
//		for (TemperaturedataView tmp : tempdata) {
//			appendSetXml(strXML, tmp.getTmpdata());			
//		}
//		strXML.append("</dataset>");	
//		}		
//		Xcsmscfg xc=getXccfg(tempdata.get(0).getTmpno());
//		if(xc!=null){
//			strXML.append("<dataset seriesName='");
//			strXML.append("摊铺温度预警线");
//			strXML.append("' color='FF0000'>");
//			for (TemperaturedataView tmp : tempdata) {
//				strXML.append("<set color='FF0000'  dashed='1' value='");
//				strXML.append(xc.getTmplow());	
//                strXML.append("' showValues='1'/>");
//			}
//			strXML.append("</dataset>");	
//			
//			strXML.append("<dataset seriesName='");
//			strXML.append("");
//			strXML.append("'>");
//			for (TemperaturedataView tmp : tempdata) {
//				strXML.append("<set color='FF0000'  dashed='1' value='");
//				strXML.append(xc.getTmphigh());	
//                strXML.append("' />");
//			}
//			strXML.append("</dataset>");
//		}
////			strXML.append("<dataset seriesName='");
////			strXML.append("");
////			strXML.append("'>");
////			for (TemperaturedataView tmp : tempdata) {
////				strXML.append("<set color='FFFF00'  dashed='1' value='");
////				strXML.append(xc.getTmplow1());	
////                strXML.append("' />");
////			}
////			strXML.append("</dataset>");
////			
////			strXML.append("<dataset seriesName='");
////			strXML.append("");
////			strXML.append("'>");
////			for (TemperaturedataView tmp : tempdata) {
////				strXML.append("<set color='FFFF00'  dashed='1' value='");
////				strXML.append(xc.getTmphigh1());	
////                strXML.append("' />");
////			}
////			strXML.append("</dataset>");
////			
////			strXML.append("<dataset seriesName='");
////			strXML.append("");
////			strXML.append("'>");
////			for (TemperaturedataView tmp : tempdata) {
////				strXML.append("<set color='FF0000'  dashed='1' value='");
////				strXML.append(xc.getTmplow2());	
////                strXML.append("' />");
////			}
////			strXML.append("</dataset>");
////			
////			strXML.append("<dataset seriesName='");
////			strXML.append("");
////			strXML.append("'>");
////			for (TemperaturedataView tmp : tempdata) {
////				strXML.append("<set color='FF0000'  dashed='1' value='");
////				strXML.append(xc.getTmphigh2());	
////                strXML.append("' />");
////			}
////			strXML.append("</dataset>");
////		}	
////		strXML.append("<trendLines>");
////		strXML.append("<line startValue='"+xc.getTmplow()+"' color='FF0000' displayvalue='初级上线("+xc.getTmplow()+"°C)' dashed='1' /> ");
////		strXML.append("<line startValue='"+xc.getTmphigh()+"' color='FF0000' displayvalue='初级下线("+xc.getTmphigh()+"°C)' dashed='1'/> ");
////		strXML.append("</trendLines>");
//		strXML.append(" <styles>");
//        strXML.append(" <definition>"); 
//        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
//        strXML.append(" </definition>"); 
//        strXML.append(" <application>"); 
//        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
//        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
//        strXML.append(" </application>"); 
//        strXML.append(" </styles>"); 
//        strXML.append(" </chart>"); 
//		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhechuliaotemp", 1000, 300, false, false);
//	}
	
	
	//大屏温度速度查询走势图
	public String getDaPingTempXml(List<TemperaturedataView> tempdata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='摊铺温度走势图(°C)' subcaption='(");
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getTmpshijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='20' numDivLines='4' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='100' yAxisMaxValue='200'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (TemperaturedataView tmp : tempdata) {
			strXML.append("<category showlabel='1' label='");
//			strXML.append(tmp.getTmpshijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset  color='0000FF' seriesName='温度'>");
		for (TemperaturedataView tmp : tempdata) {	
			strXML.append("<set color='0000FF'   value='");
			strXML.append(tmp.getTmpdata());	
            strXML.append("' toolText='"+tmp.getTmpshijian()+","+tmp.getTmpdata()+"'/>");
		}
		strXML.append("</dataset>");	
		}		
		Xcsmscfg xc=getXccfg(tempdata.get(0).getTmpno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' color='00FF00' displayValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("道路石油沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getTmplow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  color= '00FF00' seriesName='");
			strXML.append("改性沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='00FF00' dashed='1'  value='");
				strXML.append(xc.getTmphigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhechuliaotemp_daping", 675, 325, false, false);
	}
	
	//温度速度查询走势图
	public String getnianyaTempXml(List<TemperaturedataView> tempdata,String shebeibianhao) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		if(StringUtil.Null2Blank(shebeibianhao).length()==0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='碾压温度走势图(°C)' subcaption='(");
		}else{
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+tempdata.get(0).getBanhezhanminchen()+"走势图(°C)' subcaption='(");
		}
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getTmpshijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='50' yAxisMaxValue='200'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (TemperaturedataView tmp : tempdata) {
			strXML.append("<category label='");
//			strXML.append(tmp.getTmpshijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset seriesName='温度' color='0000FF'>");
		for (TemperaturedataView tmp : tempdata) {
			appendSetXml5(strXML, tmp.getTmpdata(),tmp.getTmpshijian());
		}
		strXML.append("</dataset>");	
		}			
		Xcsmscfg xc=getXccfg(tempdata.get(0).getTmpno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' color='00FF00' displayValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("道路石油沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getTmplow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
			strXML.append("改性沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='00FF00' dashed='1'  value='");
				strXML.append(xc.getTmphigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}
//			strXML.append("<trendlines>");
//			strXML.append("<line startValue='"+xc.getTmplow()+"' color='0000FF' displayValue='初级下线,"+xc.getTmplow()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmphigh()+"' color='80FF80' displayValue='初级上线,"+xc.getTmphigh()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmplow1()+"' color='8000FF' displayValue='中级下线,"+xc.getTmplow1()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmphigh1()+"' color='000000' displayValue='中级上线,"+xc.getTmphigh1()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmplow2()+"' color='400080' displayValue='高级下线,"+xc.getTmplow2()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmphigh2()+"' color='FF0000' displayValue='高级上线,"+xc.getTmphigh2()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("</trendlines>");			
//		}		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhechuliaotemp", 1000, 300, false, false);
	}
	
	//温度速度查询走势图
	public String getChuliaokouXml(List<ChuliaokouTemperaturedataView> tempdata,String shebeibianhao) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		if(StringUtil.Null2Blank(shebeibianhao).length()==0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='出料口温度走势图(°C)' subcaption='(");
		}else{
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+tempdata.get(0).getBanhezhanminchen()+"走势图(°C)' subcaption='(");
		}
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getTmpshijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='100'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (ChuliaokouTemperaturedataView tmp : tempdata) {
			strXML.append("<category label='");
//			strXML.append(tmp.getTmpshijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset seriesName='温度' color='0000FF'>");
		for (ChuliaokouTemperaturedataView tmp : tempdata) {
			appendSetXml5(strXML, tmp.getTmpdata(),tmp.getTmpshijian());			
		}
		strXML.append("</dataset>");	
		}		
		Clksmscfg clk=sysService.getClksmscfg(tempdata.get(0).getTmpno());
		if(clk!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(clk.getTmplow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(clk.getTmplow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(clk.getTmphigh());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(clk.getTmphigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("出料口温度预警线");
			strXML.append("'>");
			for (ChuliaokouTemperaturedataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(clk.getTmplow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  anchorBorderColor = 'FF3333' seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (ChuliaokouTemperaturedataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(clk.getTmphigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhechuliaotemp", 1000, 300, false, false);
	}
	
	//大屏碾压温度查询走势图
	public String getDaPingnianyaTempXml(List<TemperaturedataView> tempdata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='碾压温度走势图(°C)' subcaption='(");
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getTmpshijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getTmpshijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMinValue='50' yAxisMaxValue='200'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (TemperaturedataView tmp : tempdata) {
			strXML.append("<category label='");
//			strXML.append(tmp.getTmpshijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset seriesName='温度' color='0000FF'>");
		for (TemperaturedataView tmp : tempdata) {
			appendSetXml5(strXML, tmp.getTmpdata(),tmp.getTmpshijian());			
		}
		strXML.append("</dataset>");	
		}			
		Xcsmscfg xc=getXccfg(tempdata.get(0).getTmpno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getTmplow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' color='00FF00' displayValue='");
			strXML.append(xc.getTmphigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("道路石油沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getTmplow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
			strXML.append("改性沥青");
			strXML.append("'>");
			for (TemperaturedataView tmp : tempdata) {
				strXML.append("<set color='00FF00' dashed='1'  value='");
				strXML.append(xc.getTmphigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}
//			strXML.append("<trendlines>");
//			strXML.append("<line startValue='"+xc.getTmplow()+"' color='0000FF' displayValue='初级下线,"+xc.getTmplow()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmphigh()+"' color='80FF80' displayValue='初级上线,"+xc.getTmphigh()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmplow1()+"' color='8000FF' displayValue='中级下线,"+xc.getTmplow1()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmphigh1()+"' color='000000' displayValue='中级上线,"+xc.getTmphigh1()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmplow2()+"' color='400080' displayValue='高级下线,"+xc.getTmplow2()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("<line startValue='"+xc.getTmphigh2()+"' color='FF0000' displayValue='高级上线,"+xc.getTmphigh2()+"' showOnTop='1' dashed='1'/>");
//			strXML.append("</trendlines>");			
//		}		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>");  
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapingnianyawendu", 675, 325, false, false);
	}
	
	//走势图
	public String getTanpuSpeedXml(List<SpeeddataView> tempdata,String shebeibianhao) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		if(StringUtil.Null2Blank(shebeibianhao).length()==0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='摊铺速度走势图(m/min)' subcaption='(");
		}else{
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+tempdata.get(0).getBanhezhanminchen()+"速度走势图(m/min)' subcaption='(");
		}
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getShijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getShijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='7'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (SpeeddataView tmp : tempdata) {
			strXML.append("<category label='");
//			strXML.append(tmp.getShijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset seriesName='速度' color='0000FF'>");
		for (SpeeddataView tmp : tempdata) {
			appendSetXml5(strXML, tmp.getSudu(),tmp.getShijian());			
		}
		strXML.append("</dataset>");	
		}		
		Xcsmscfg xc=getXccfg(tempdata.get(0).getGpsno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("摊铺速度预警线");
			strXML.append("'>");
			for (SpeeddataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedlow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  anchorBorderColor = 'FF3333' seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (SpeeddataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedhigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}	
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhechuliaotemp", 1000, 300, false, false);
	}
	
	//温度速度查询走势图
	public String getDaPingTanpuSpeedXml(List<SpeeddataView> tempdata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='摊铺速度走势图(m/min)' subcaption='(");
		if (null != tempdata && tempdata.size()>0) {
			strXML.append(tempdata.get(0).getShijian());
			strXML.append("至");
			strXML.append(tempdata.get(tempdata.size()-1).getShijian());
			datasize = tempdata.size()-1;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='7'> ");
		strXML.append("<categories>");
		if (null != tempdata && tempdata.size()>0) {
		for (SpeeddataView tmp : tempdata) {
			strXML.append("<category label='");
//			strXML.append(tmp.getShijian());
			strXML.append("'/>");
		}
		}
		strXML.append("</categories>");
		if (null != tempdata && tempdata.size()>0) {
		strXML.append("<dataset seriesName='速度' color='0000FF'>");
		for (SpeeddataView tmp : tempdata) {
			appendSetXml5(strXML, tmp.getSudu(),tmp.getShijian());			
		}
		strXML.append("</dataset>");	
		}		
		Xcsmscfg xc=getXccfg(tempdata.get(0).getGpsno());
		if(xc!=null){
			//画上下限
			strXML.append("<trendlines>");
			strXML.append("<line  alpha='0'  showOnTop='1' startValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedlow());
			strXML.append("' showOnTop='1' dashed='1'/>");
			strXML.append("<line alpha='0'   startValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' color='FF3333' displayValue='");
			strXML.append(xc.getSpeedhigh());
			strXML.append("' showOnTop='1' dashed='1' />");
		    strXML.append("</trendlines>");
		    
			strXML.append("<dataset  anchorRadius = '0' color='FF3333' seriesName='");
			strXML.append("摊铺速度预警线");
			strXML.append("'>");
			for (SpeeddataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedlow());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset  anchorRadius = '0'  anchorBorderColor = 'FF3333' seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (SpeeddataView tmp : tempdata) {
				strXML.append("<set color='FF3333' dashed='1'  value='");
				strXML.append(xc.getSpeedhigh());	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
		}	
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapingtanpusudu", 675, 325, false, false);
	}
	
	public GenericPageMode viewtemplist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewtemplist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public GenericPageMode lqsjviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn,Integer bsid,Integer offset,Integer pagesize) {
		return lqviewDAO.lqsjviewlist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public LiqingziduancfgView getlqcfgfield(Integer bhzid) {
		if (null != bhzid) {
			Banhezhanxinxi hview = bhzDAO.get(bhzid);
			return getlqcfgfield(hview.getGprsbianhao());
		}  
		return lqcfgviewDAO.findByGprsbhandleixin("all", "100");
	}
	
	//登录日志
	public GenericPageMode viewuserlogtongjilist(String username,String danwei,String zhiwei,String startTimeOne,
			String endTimeOne, Integer usertype, Integer biaoduan, Integer xiangmubu, Integer pageNo, int maxPageItems, String shebeibianhao) {
		return hntDAO.viewuserlogtongjilist(username,danwei,zhiwei,startTimeOne,endTimeOne,usertype,biaoduan,xiangmubu,pageNo,maxPageItems,shebeibianhao);
	}
	
	public GenericPageMode viewuserloglist(String shebeibianhao,String startTimeOne,
			String endTimeOne, int offset, int pagesize) {
		return hntDAO.viewuserloglist(shebeibianhao,startTimeOne,endTimeOne,offset, pagesize);
	}
	
	public GenericPageMode lqllviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn,Integer bsid,Integer offset,Integer pagesize) {
		return lqviewDAO.lqllviewlist(shebeibianhao,startTimeOne,endTimeOne, 
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	public List<UserlogView> viewuserlogtongjilist(String shebeibianhao,String startTimeOne,
			String endTimeOne) {
		return hntDAO.viewuserlogtongjilist(shebeibianhao,startTimeOne,endTimeOne);
	}
	
	//温度速度查询走势图
	public String getSpeedandTmpXml(List<TemperaturedataView> tmpdata, List<SpeeddataView> speeddata) {
		StringBuilder strXML = new StringBuilder("");
		int datasize = 3;
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='温度速度走势图' subcaption='(");
		if (null != tmpdata && tmpdata.size()>0) {
			strXML.append(tmpdata.get(0).getTmpshijian());
			strXML.append("至");
			strXML.append(tmpdata.get(tmpdata.size()-1).getTmpshijian());
			datasize = tmpdata.size()/5;
		} else if (null != speeddata && speeddata.size()>0) {
			strXML.append(speeddata.get(0).getShijian());
			strXML.append("至");
			strXML.append(speeddata.get(speeddata.size()-1).getShijian());
			datasize = speeddata.size()/5;
		}
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(datasize);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5'> ");
		strXML.append("<categories>");
		if (null != tmpdata && tmpdata.size()>0) {
		for (TemperaturedataView tmp : tmpdata) {
			strXML.append("<category label='");
			strXML.append(tmp.getTmpshijian());
			strXML.append("'/>");
		}
		}
		else if (null != speeddata && speeddata.size()>0) {
			for (SpeeddataView speed : speeddata) {
				strXML.append("<category label='");
				strXML.append(speed.getShijian());
				strXML.append("'/>");
			}
		}
		strXML.append("</categories>");
		if (null != tmpdata && tmpdata.size()>0) {
		strXML.append("<dataset seriesName='温度'>");
		for (TemperaturedataView tmp : tmpdata) {
			appendSetXml(strXML, tmp.getTmpdata());					
		}
		strXML.append("</dataset>");	
		}
		if (null != speeddata && speeddata.size()>0) {
			strXML.append("<dataset seriesName='速度'>");
			for (SpeeddataView speed : speeddata) {
				appendSetXml(strXML, speed.getShijian());					
			}
			strXML.append("</dataset>");
		}

		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanheshijian", 1000, 300, false, false);
	}
	
	//砼强度等级
	public List<LiqingView> getlqxinghao(){
		return hntDAO.find("select model.peifan as peifan from LiqingView as model group by model.peifan");
	}
	
	//查询出现场中初级、中。高级上下线
	public Xcsmscfg getXccfg(String shebeibianhao){
		List<Xcsmscfg> xccfglist=xcsmscfgDAO.find("from Xcsmscfg where shebeibianhao='"+shebeibianhao+"'");
		if(xccfglist!=null && xccfglist.size()>0){
			return (Xcsmscfg)xccfglist.get(0);
		}else{
			return null;
		}
	}
	
	public GenericPageMode environmentView(String shebeibianhao,
			String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize){
		return hntDAO.environmentView(shebeibianhao, startTimeOne, endTimeOne, biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	//沥青设计生产量
	public GenericPageMode lqshejisclview(String shebeibianhao,String peifang,String startTime,String endTime,Integer biaoduan,
			Integer xiangmubu,String fn,int bsid,Integer offset,Integer pagesize){
		return hntDAO.lqshejisclview(shebeibianhao,peifang, startTime, endTime, biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	/************水稳****************/
	public ShuiwenziduancfgView getSwcfgisShow(String shebeibianhao) {
		ShuiwenziduancfgView swview = swcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "2");
		if (null == swview) {
			swview = swcfgviewDAO.findByGprsbhandleixin("all", "101");			
		}
		return swview;
	}
	
	public ShuiwenziduancfgView getSwfield(String shebeibianhao) {
		ShuiwenziduancfgView swview = swcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "1");
		if (null == swview) {
			swview = swcfgviewDAO.findByGprsbhandleixin("all", "100");			
		}
		return swview;
	}
	
	public LiqingziduancfgView getLqfield(String shebeibianhao) {
		LiqingziduancfgView lqview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "1");
		if (null == lqview) {
			lqview = lqcfgviewDAO.findByGprsbhandleixin("all", "100");			
		}
		return lqview;
	}

	public ShuiwenziduancfgView getSwzstcfgisShow(String shebeibianhao) {
		ShuiwenziduancfgView swview = swcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "16");
		if (null == swview) {
			swview = swcfgviewDAO.findByGprsbhandleixin("all", "102");			
		}
		return swview;
	}
	
	private String mainlistcreateswzhfxxml(List<ShuiwenxixxView> swviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='2' caption='最近生产情况' "); 
        strXML.append(" clickURL='"); 
        strXML.append(StringUtil.getWebrootpath()); 
        strXML.append("/query/swzhfx?pid=2&record=13&'"); 
        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
        strXML.append(" PYAxisName='生产量(t)' SYAxisName='预警率(%)'"); 
        strXML.append(" SNumberSuffix='%' PNumberSuffix='t' formatNumberScale='0'>"); 
        String sxa = "年";
        String sxb;
        switch (cnfxlx) {
			case 1:
				sxb = "季度";
				break;
			case 2:
				sxb = "月份";
				break;
			case 3:
				sxb = "周";
				break;
			case 4:
				sxa = "月";
				sxb = "日";
				break;
			default:
				sxb = "月份";
				break;
		}
        
        strXML.append("<categories>");
        for (ShuiwenxixxView hv : swviews) {
        	strXML.append("<category label='"); 
        	strXML.append(hv.getSmstype());
        	strXML.append("-");
        	strXML.append(hv.getSendtype());
            strXML.append("'/>");    	
        }
        strXML.append("</categories>"); 
        
        strXML.append("<dataset seriesName='生产量(t)'  showValues='0'>");
        for (ShuiwenxixxView hv : swviews) {
        	strXML.append("<set value='"); 
        	strXML.append(hv.getGlchangliang());
        	strXML.append("' displayValue='");
        	strXML.append(hv.getGlchangliang());
        	strXML.append(",");
        	strXML.append(hv.getGlchangliang());
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");
        
        strXML.append("<dataset lineThickness='3' seriesName='预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView hv : swviews) {
        	strXML.append("<set value='"); 
        	strXML.append(hv.getAmbegin());
        	strXML.append("' displayValue='");
        	strXML.append(hv.getAmbegin());
        	strXML.append(",");
        	strXML.append(hv.getAmbegin());       	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>"); 
        strXML.append("</chart>");
		return strXML.toString();
	}
	
	public String mainlistgetswzhfxXml(List<ShuiwenxixxView> swviews, int cnfxlx) {
		String strXML = mainlistcreateswzhfxxml(swviews, cnfxlx); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "hntzhfxchart", 500, 200, false, false);
	}
	
	public List<ShuiwenxixxView> swmainlistzhfxlist(String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		List<TopShuiwenView> topswlist = topswDAO.loadAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
		if (topswlist.size() > 0) {
			TopShuiwenView topsw = topswlist.get(0);
			if (null != topsw) {
				nowtime = topsw.getBaocunshijian();
			}
		}
		try {
			day.setTime(sdf.parse(nowtime));
		} catch (Exception e) {
		}		
		day.add(Calendar.DATE, -6);
    	begintime = sdf.format(day.getTime());
		return swViewDAO.swzhfxlist(begintime, nowtime, shebeibianhao,biaoduan,xiangmubu,cnfxlx,fn,bsid);
	}
	
	public List<ShuiwenxixxView> swcnfxlist(String startTime,String endTime,String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		return swViewDAO.swcnfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,cnfxlx,fn,bsid);
	}
	
	public ShuiwenxixxView swxxfindById(Integer xxid) {
		ShuiwenxixxView shuiwenxixx=swViewDAO.get(xxid);
		try{
			shuiwenxixx.setSjgl1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjgl1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjgl2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjgl2())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjgl3(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjgl3())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjgl4(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjgl4())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjgl5(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjgl5())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setBeiy1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getBeiy1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjfl1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjfl1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjfl2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjfl2())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setSjshui(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getSjshui())));
		}catch(Exception ex){}
		
		try{
			shuiwenxixx.setLlgl1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlgl1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlgl2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlgl2())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlgl3(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlgl3())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlgl4(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlgl4())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlgl5(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlgl5())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setBeiy2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getBeiy2())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlfl1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlfl1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlfl2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlfl2())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setLlshui(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getLlshui())));
		}catch(Exception ex){}
		
		try{
			shuiwenxixx.setPersjgl1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjgl1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setPersjgl2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjgl2())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setPersjgl3(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjgl3())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setPersjgl4(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjgl4())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setPersjgl5(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjgl5())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setPersjfl1(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjfl1())));
		}catch(Exception ex){}
		try{
			shuiwenxixx.setPersjfl2(String.format("%1.2f",StringUtil.StrToZero(shuiwenxixx.getPersjfl2())));
		}catch(Exception ex){}
		return shuiwenxixx;
	}
	
	public ShuiwenmanualphbView swmanualphbfindById(Integer xxid) {
		ShuiwenmanualphbView swmanualphbxixxView=swmanualphbViewDAO.get(xxid);
		//实际用量
		try{
			swmanualphbxixxView.setSjgl1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjgl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjgl2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjgl2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjgl3(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjgl3())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjgl4(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjgl4())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjgl5(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjgl5())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setBeiy1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getBeiy1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjfl1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjfl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjfl2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjfl2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setSjshui(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getSjshui())));
		}catch(Exception ex){}
		
		//王恒搞的，水稳理论配比
		try{
			swmanualphbxixxView.setLlgl1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlgl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlgl2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlgl2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlgl3(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlgl3())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlgl4(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlgl4())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlgl5(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlgl5())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setBeiy2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getBeiy2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlfl1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlfl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlfl2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlfl2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setLlshui(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getLlshui())));
		}catch(Exception ex){}
		
		//实际配比
		try{
			swmanualphbxixxView.setPersjgl1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjgl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setPersjgl2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjgl2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setPersjgl3(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjgl3())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setPersjgl4(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjgl4())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setPersjgl5(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjgl5())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setPersjfl1(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjfl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setPersjfl2(String.format("%1.2f",StringUtil.StrToZero(swmanualphbxixxView.getPersjfl2())));
		}catch(Exception ex){}
		//目标配比
		
		//实际配比-目标配比所得的误差
		try{
			swmanualphbxixxView.setManualwgl1(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwgl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setManualwgl2(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwgl2())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setManualwgl3(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwgl3())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setManualwgl4(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwgl4())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setManualwgl5(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwgl5())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setManualwfl1(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwfl1())));
		}catch(Exception ex){}
		try{
			swmanualphbxixxView.setManualwfl2(String.format("%1.2f", StringUtil.StrToZero(swmanualphbxixxView.getManualwfl2())));
		}catch(Exception ex){}
		return swmanualphbxixxView;
	}
	
	public LiqingmanualphbView lqmanualphbfindById(Integer xxid) {
		LiqingmanualphbView lqmanualphbxixxView=lqmanualphbViewDAO.get(xxid);
		//实际用量
		try{
			lqmanualphbxixxView.setSjysb(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjysb())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg1())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg2())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg3())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg4(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg4())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg5(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg5())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg6(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg6())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjg7(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjg7())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setBeiy1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy1())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setBeiy2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy2())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setBeiy3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy3())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjf1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjf1())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjf2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjf2())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjlq(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjlq())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setSjtjj(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getSjtjj())));
		}catch(Exception ex){}
		
		//沥青理论配比
		if(StringUtil.Null2Blank(lqmanualphbxixxView.getBiaoshi()).length()==0){
			try{
				lqmanualphbxixxView.setLlysb(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlysb())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg1())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg2())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg3())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg4(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg4())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg5(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg5())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg6(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg6())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg7(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlg7())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setBeiy1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy1())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setBeiy2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy2())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setBeiy3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy3())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlf1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlf1())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlf2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLlf2())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLllq(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLllq())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLltjj(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getLltjj())));
			}catch(Exception ex){}
		}else{
			try{
				lqmanualphbxixxView.setLlysb(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllysb())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg1())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg2())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg3())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg4(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg4())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg5(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg5())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg6(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg6())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlg7(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllg7())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setBeiy1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy1())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setBeiy2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy2())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setBeiy3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getBeiy3())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlf1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllf1())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLlf2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerllf2())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLllq(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerlllq())));
			}catch(Exception ex){}
			try{
				lqmanualphbxixxView.setLltjj(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPerlltjj())));
			}catch(Exception ex){}
		}
		
		//实际配比
		try{
			lqmanualphbxixxView.setPersjg1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg1())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjg2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg2())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjg3(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg3())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjg4(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg4())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjg5(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg5())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjg6(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg6())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjg7(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjg7())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjf1(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjf1())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjf2(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjf2())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjlq(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjlq())));
		}catch(Exception ex){}
		try{
			lqmanualphbxixxView.setPersjtjj(String.format("%1.2f",StringUtil.StrToZero(lqmanualphbxixxView.getPersjtjj())));
		}catch(Exception ex){}
		//目标配比
		
		//实际配比-目标配比所得的误差
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlysb())>0){
			try{
				lqmanualphbxixxView.setManualwsjysb(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getSjysb())-StringUtil.StrToZero(lqmanualphbxixxView.getLlysb()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjysb("");
		}
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg1())>0){
			try{
				lqmanualphbxixxView.setManualwsjg1(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg1())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg1()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg1("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg2())>0){
			try{
				lqmanualphbxixxView.setManualwsjg2(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg2())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg2()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg2("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg3())>0){
			try{
				lqmanualphbxixxView.setManualwsjg3(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg3())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg3()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg3("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg4())>0){
			try{
				lqmanualphbxixxView.setManualwsjg4(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg4())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg4()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg4("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg5())>0){
			try{
				lqmanualphbxixxView.setManualwsjg5(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg5())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg5()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg5("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg6())>0){
			try{
				lqmanualphbxixxView.setManualwsjg6(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg6())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg6()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg6("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlg7())>0){
			try{
				lqmanualphbxixxView.setManualwsjg7(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjg7())-StringUtil.StrToZero(lqmanualphbxixxView.getLlg7()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjg7("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlf1())>0){
			try{
				lqmanualphbxixxView.setManualwsjf1(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjf1())-StringUtil.StrToZero(lqmanualphbxixxView.getLlf1()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjf1("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLlf2())>0){
			try{
				lqmanualphbxixxView.setManualwsjf2(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjf2())-StringUtil.StrToZero(lqmanualphbxixxView.getLlf2()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjf2("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLllq())>0){
			try{
				lqmanualphbxixxView.setManualwsjlq(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjlq())-StringUtil.StrToZero(lqmanualphbxixxView.getLllq()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjlq("");
		}
		
		if(StringUtil.StrToZero(lqmanualphbxixxView.getLltjj())>0){
			try{
				lqmanualphbxixxView.setManualwsjtjj(String.format("%1.2f", (StringUtil.StrToZero(lqmanualphbxixxView.getPersjtjj())-StringUtil.StrToZero(lqmanualphbxixxView.getLltjj()))));
			}catch(Exception ex){}
		}else{
			lqmanualphbxixxView.setManualwsjtjj("");
		}
		
		return lqmanualphbxixxView;
	}
	
	//水稳配合比误差走势图---与采集到的配比做对比
	public String getSwphbwcXml(List<ShuiwenmanualphbView> swmanualphb,ShuiwenziduancfgView swField,ShuiwenziduancfgView swzstisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='误差走势图(%)' subcaption='(");
		strXML.append(swmanualphb.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(swmanualphb.get(swmanualphb.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(swmanualphb.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5'> ");
		strXML.append("<categories>");
		for (ShuiwenmanualphbView sw : swmanualphb) {
			strXML.append("<category label='");
			strXML.append(sw.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");

		if (null != swField && null != swzstisShow) {
			if(swzstisShow.getSjgl1().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjgl1());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWgl1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
				
			if(swzstisShow.getSjgl2().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjgl2());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWgl2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
				
			if(swzstisShow.getSjgl3().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjgl3());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWgl3());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
				
			if(swzstisShow.getSjgl4().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjgl4());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWgl4());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
				
			if(swzstisShow.getSjgl5().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjgl5());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWgl5());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
				
			if(swzstisShow.getSjfl1().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjfl1());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWfl1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
			
			if(swzstisShow.getSjfl2().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='");
				strXML.append(swField.getSjfl2());
				strXML.append("'>");
				for (ShuiwenmanualphbView manualphb :swmanualphb) {
					strXML.append("<set value='");
					strXML.append(manualphb.getWfl2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");
			}
			
		}
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartswwuchaper", 1000, 300, false, false);
	}
	
	//水稳配合比误差走势图---与手动输入的配比做对比
	public String getSwmanualphbwcXml(List<ShuiwenmanualphbView> swcl,ShuiwenziduancfgView swField,ShuiwenziduancfgView swisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='实际用量百分比走势图(%)' subcaption='(");
		strXML.append(swcl.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(swcl.get(swcl.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(swcl.size()/5);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5'> ");
		strXML.append("<categories>");
		for (ShuiwenmanualphbView sw : swcl) {
			strXML.append("<category label='");
			strXML.append(sw.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != swField && null != swisShow) {
			if(swisShow.getSjgl1().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjgl1()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjgl1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");							
			}
			
			if(swisShow.getSjgl2().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjgl2()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjgl2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");				
			}
			
			if(swisShow.getSjgl3().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjgl3()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjgl3());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");				
			}
			
			if(swisShow.getSjgl4().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjgl4()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjgl4());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");				
			}
			
			if(swisShow.getSjgl5().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjgl5()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjgl5());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");				
			}
			
			if(swisShow.getSjfl1().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjfl1()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjfl1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");				
			}
			
			if(swisShow.getSjfl2().equalsIgnoreCase("1")){
				strXML.append("<dataset seriesName='"+swField.getSjfl2()+"'>");
				for (ShuiwenmanualphbView sw : swcl) {
					strXML.append("<set value='");
					strXML.append(sw.getPersjfl2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");				
			}
		}
		
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartsjper", 1000, 300, false, false);
	}
	
	public List<Shuiwenxixxdanjia> getDanjiaAll(){
		return swdjDAO.find("from Shuiwenxixxdanjia ORDER BY djid DESC");
	}
	
	public void saveDanjia(Shuiwenxixxdanjia danjia){
		swdjDAO.saveOrUpdate(danjia);
	}
	
	public void delswDanjia(Integer djid){
		swdjDAO.deleteByKey(djid);
	}
	
	public Shuiwenxixxdanjia getBeanById(Integer djid){
		return swdjDAO.get(djid);
	}
	
	public void moren(int bdid){
		Shuiwenxixxdanjia swdj = swdjDAO.get(bdid);
		if (null != swdj) {
			if (null != swdj.getDjmoren() && swdj.getDjmoren().equalsIgnoreCase("1")) {
				swdj.setDjmoren("0");
			} else {
				swdj.setDjmoren("1");
			}
			swdjDAO.saveOrUpdate(swdj);
		}
	}
	
	public List<ShuiwenxixxView> swsmstongji(String startTime,String endTime,Integer biaoduan,Integer xiangmubu,String shebeibianhao,
			String fn,Integer bsid,Integer fzlx){
		return swViewDAO.swsmstongji(startTime, endTime, biaoduan, xiangmubu, shebeibianhao, fn, bsid,fzlx);
	}
	
	public Liqingxixxdanjia calTotaljiage(LiqingphbView lqv, String sbbh){
		Liqingxixxdanjia jiage = new Liqingxixxdanjia();
		Liqingxixxdanjia danjia = null;
		ArrayList<Liqingxixxdanjia> jglist = null;
		if (StringUtil.Null2Blank(sbbh).length()>0) {
			jglist = (ArrayList<Liqingxixxdanjia>) djDAO.find("from Liqingxixxdanjia where djmoren='1' and djshebeibianhao=?", sbbh);
		} else {
			jglist = (ArrayList<Liqingxixxdanjia>) djDAO.find("from Liqingxixxdanjia where djmoren='1'");
		}
		
		if (null != jglist && jglist.size() > 0) {
			danjia = jglist.get(0);
			if (null != danjia) {
				try {
					jiage.setDjg1(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg1())*StringUtil.StrToZero(lqv.getSjg1())/1000));
					jiage.setDjg2(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg2())*StringUtil.StrToZero(lqv.getSjg2())/1000));
					jiage.setDjg3(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg3())*StringUtil.StrToZero(lqv.getSjg3())/1000));
					jiage.setDjg4(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg4())*StringUtil.StrToZero(lqv.getSjg4())/1000));
					jiage.setDjg5(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjg5())*StringUtil.StrToZero(lqv.getSjg5())/1000));
					jiage.setDjf1(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjf1())*StringUtil.StrToZero(lqv.getSjf1())/1000));
					jiage.setDjf2(String.format("%1$.3f",StringUtil.StrToZero(danjia.getDjf2())*StringUtil.StrToZero(lqv.getSjf2())/1000));
				} catch (Exception e) {
				}
			}
		}
		return jiage;
	}
	
	public String getswmaterialhsXml(ShuiwenphbView hv, ShuiwenziduancfgView fieldview) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='4' caption='材料成本核算(单位:t)' "); 
        strXML.append(" showValues='0' "); 
        strXML.append(" xAxisName='材料名称' yAxisName='实际用量'"); 
        strXML.append(" formatNumberScale='0'>"); 
        if(fieldview!=null){
		      	strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjgl1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjgl1()); 
		        strXML.append("'/>"); 
	        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjgl2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjgl2()); 
		        strXML.append("'/>"); 		                
	        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjgl3()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjgl3()); 
		        strXML.append("'/>");		      	        
	        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjgl4()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjgl4()); 
		        strXML.append("'/>");		        
	        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjgl5()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjgl5()); 
		        strXML.append("'/>");		        	        
        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjfl1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjfl1()); 
		        strXML.append("' />"); 		                
            
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjfl2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjfl2()); 
		        strXML.append("'/>"); 		       
        }       
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Column3D.swf", "",strXML.toString(), "lqmaterialhschart", 1000, 250, false, false);
	}
	
	//沥青拌和站材料用量查询走势图
	public String getShijiShaifenXml(List<ShaifenjieguoView> hv) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='关键筛分通过率变化图' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='100'  > ");
		strXML.append("<categories>");
		for (int i=0;i<hv.size();i++) {
			strXML.append("<category label='"+String.valueOf(i)+"'/>");
		}
		strXML.append("</categories>");
		
		strXML.append("<dataset seriesName='-0.075-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			
			if(StringUtil.Null2Blank(sfjieguo.getPassper1()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper1())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper1()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		/*
		strXML.append("<dataset seriesName='-2.36-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper6()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper6())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper6()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		*/
		strXML.append("<dataset seriesName='-4.75-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			
			if(StringUtil.Null2Blank(sfjieguo.getPassper7()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper7())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper7()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-9.5-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			
			if(StringUtil.Null2Blank(sfjieguo.getPassper8()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper8())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper8()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		strXML.append("<dataset seriesName='-19-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			
			if(StringUtil.Null2Blank(sfjieguo.getPassper11()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper11())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper11()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		strXML.append("<dataset seriesName='-31.5-' decimalPrecision='0'>");
		for(ShaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper13()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper13())!=0){
				float f = StringUtil.StrToZero(sfjieguo.getPassper13());
				 //System.out.println(new BigDecimal(f).setScale(0, BigDecimal.ROUND_HALF_UP));	
				strXML.append("<set value='"+new BigDecimal(f).setScale(0, BigDecimal.ROUND_HALF_UP)+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		/*
		strXML.append("<dataset seriesName='-13.2-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper9()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper9())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper9()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-26.5-'>");
		for(ShaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper12()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper12())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper12()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");	*/
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
        //System.out.println(strXML.toString());

		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartShijiShaifen", 1000, 300, false, false);
	}
	
	public Shuiwenziduancfg getswcfg(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return swcfgDAO.findByGprsbhandleixin(shebeibianhao, "21");
		} else {
			return swcfgDAO.findByGprsbhandleixin("all", "20");
		}
	}
	
	public void saveswcfg(Shuiwenziduancfg swziduancfg) {
		swcfgDAO.saveOrUpdate(swziduancfg);
	}
	
	public void savelqcfg(Liqingziduancfg lqziduancfg) {
		lqcfgDAO.saveOrUpdate(lqziduancfg);
	}
	
	public ShuiwenziduancfgView getswcfgisShow(String shebeibianhao) {
		ShuiwenziduancfgView swziduancfgview = swcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "21");
		if (null == swziduancfgview) {
			swziduancfgview = swcfgviewDAO.findByGprsbhandleixin("all", "20");			
		}
		return swziduancfgview;
	}
	
	
	//沥青拌和站材料用量查询走势图
	public String getLqShijiShaifenXml(List<LqshaifenjieguoView> hv) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='关键筛孔通过率变化图' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='100'> ");
		strXML.append("<categories>");
		for (int i=0;i<hv.size();i++) {
			strXML.append("<category label='"+String.valueOf(i)+"'/>");
		}
		strXML.append("</categories>");
		
		strXML.append("<dataset seriesName='-0.075-'>");
		for(LqshaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper1()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper1())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper1()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-2.36-'>");
		for(LqshaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper6()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper6())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper6()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-4.75-'>");
		for(LqshaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper7()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper7())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper7()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-13.2-'>");
		for(LqshaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper9()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper9())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper9()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-26.5-'>");
		for(LqshaifenjieguoView sfjieguo:hv){
			if(StringUtil.Null2Blank(sfjieguo.getPassper12()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper12())!=0){
				strXML.append("<set value='"+sfjieguo.getPassper12()+"'/>");
			}else{
				strXML.append("<set value=''/>");
			}
		}
		strXML.append("</dataset>");	
		strXML.append(" <styles>");
        strXML.append(" <definition>"); 
        strXML.append(" <style type='font' name='captionFont' size='12'/>"); 
        strXML.append(" </definition>"); 
        strXML.append(" <application>"); 
        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
        strXML.append(" <apply toObject='SubCaption' styles='SubcaptionFont' />"); 
        strXML.append(" </application>"); 
        strXML.append(" </styles>"); 
        strXML.append(" </chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartShijiShaifen", 1000, 300, false, false);
	}	
	
	public Shuiwenziduancfg getswcfg40(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return swcfgDAO.findByGprsbhandleixin(shebeibianhao, "41");
		} else {
			return swcfgDAO.findByGprsbhandleixin("all", "40");
		}
	}
	
	public Shuiwenziduancfg getswcfg70(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return swcfgDAO.findByGprsbhandleixin(shebeibianhao, "71");
		} else {
			return swcfgDAO.findByGprsbhandleixin("all", "70");
		}
	}
	
	public Liqingziduancfg getlqcfg70(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "71");
		} else {
			return lqcfgDAO.findByGprsbhandleixin("all", "70");
		}
	}
	
	public ShuiwenziduancfgView getswcfgisShow40(String shebeibianhao) {
		ShuiwenziduancfgView swziduancfgview = swcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "41");
		if (null == swziduancfgview) {
			swziduancfgview = swcfgviewDAO.findByGprsbhandleixin("all", "40");			
		}
		return swziduancfgview;
	}
	
	public LiqingziduancfgView getlqcfgisShow40(String shebeibianhao) {
		LiqingziduancfgView lqziduancfgview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "41");
		if (null == lqziduancfgview) {
			lqziduancfgview = lqcfgviewDAO.findByGprsbhandleixin("all", "40");			
		}
		return lqziduancfgview;
	}
	
	public ShuiwenziduancfgView getswcfgisShow70(String shebeibianhao) {
		ShuiwenziduancfgView swziduancfgview = swcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "71");
		if (null == swziduancfgview) {
			swziduancfgview = swcfgviewDAO.findByGprsbhandleixin("all", "70");			
		}
		return swziduancfgview;
	}
	
	public ShuiwenxixxView swstatisticsinfo(){
		return swViewDAO.swstatisticsinfo();
	}
	
	public LiqingziduancfgView getlqcfgisShow70(String shebeibianhao) {
		LiqingziduancfgView lqziduancfgview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "71");
		if (null == lqziduancfgview) {
			lqziduancfgview = lqcfgviewDAO.findByGprsbhandleixin("all", "70");			
		}
		return lqziduancfgview;
	}
	
}
