
package com.mss.shtoone.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.HunnintuwuchaPageMode;
import com.mss.shtoone.domain.HunnintuwuchaView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.fusioncharts.FusionChartsCreator;
import com.mss.shtoone.persistence.HntbhzCfgDAO;
import com.mss.shtoone.persistence.HntbhzCfgViewDAO;
import com.mss.shtoone.persistence.HunnintuwuchaViewDAO;
import com.mss.shtoone.persistence.LiqingViewDAO;
import com.mss.shtoone.persistence.LiqingziduancfgDAO;
import com.mss.shtoone.persistence.LiqingziduancfgViewDAO;
import com.mss.shtoone.util.StringUtil;


@Service
public class QuerywuchaService {

	@Autowired
	private HunnintuwuchaViewDAO hntDAO;
	
	@Autowired
	private LiqingViewDAO lqviewDAO;
	
	@Autowired
	private HntbhzCfgViewDAO hntcfgDAO;
	
	@Autowired
	private HntbhzCfgDAO cfgDAO;
	
	@Autowired
	private LiqingziduancfgDAO lqcfgDAO;
	
	@Autowired
	private LiqingziduancfgViewDAO lqcfgviewDAO;
	
	@Autowired
	private SystemService sysService;
	
	public HunnintuwuchaView xxfindById(Integer xxid) {
		return hntDAO.get(xxid);
	}


	public HunnintuwuchaPageMode viewlist(String shebeibianhao,String gongchenghao,String startTimeOne,String endTimeOne,String jiaozhubuwei, Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize) {
		return hntDAO.viewlist(shebeibianhao,gongchenghao,startTimeOne,endTimeOne,jiaozhubuwei, biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}

	public GenericPageMode lqviewwuchalist(String shebeibianhao,String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize,String peifan) {
		return lqviewDAO.lqviewwuchalist(shebeibianhao,startTimeOne,endTimeOne,biaoduan, 
				xiangmubu, fn, bsid, offset, pagesize,peifan);
	}


	
	public HntbhzziduancfgView gethntcfgisShow(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return hntcfgDAO.findByGprsbhandleixin(shebeibianhao, "21");
		} else {
			return hntcfgDAO.findByGprsbhandleixin("all", "20");
		}
	}
	
	public LiqingziduancfgView getlqcfgisShow(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "21");
		} else {
			return lqcfgviewDAO.findByGprsbhandleixin("all", "20");
		}
	}
	
	public Hntbhzziduancfg gethntcfg(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return cfgDAO.findByGprsbhandleixin(shebeibianhao, "21");
		} else {
			return cfgDAO.findByGprsbhandleixin("all", "20");
		}
	}

	public Liqingziduancfg getlqcfg(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "21");
		} else {
			return lqcfgDAO.findByGprsbhandleixin("all", "20");
		}
	}

	public LiqingziduancfgView getlqviewcfg(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "21");
		} else {
			return lqcfgviewDAO.findByGprsbhandleixin("all", "20");
		}
	}
	
	public Liqingziduancfg getlqcfg4(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "41");
		} else {
			return lqcfgDAO.findByGprsbhandleixin("all", "40");
		}
	}

	public LiqingziduancfgView getlqviewcfg4(String shebeibianhao) {
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			return lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "41");
		} else {
			return lqcfgviewDAO.findByGprsbhandleixin("all", "40");
		}
	}
	
	public HntbhzziduancfgView gethntcfgfield(String shebeibianhao) {
		HntbhzziduancfgView hview = hntcfgDAO.findByGprsbhandleixin(shebeibianhao, "1");
		if (null == hview) {
			hview = hntcfgDAO.findByGprsbhandleixin("all", "100");			
		}
		return hview;		
	}
	
	public LiqingziduancfgView getlqcfgfield(String shebeibianhao) {
		LiqingziduancfgView hview = lqcfgviewDAO.findByGprsbhandleixin(shebeibianhao, "1");
		if (null == hview) {
			hview = lqcfgviewDAO.findByGprsbhandleixin("all", "100");			
		}
		return hview;		
	}
	
	public void saveOrupdate(Hntbhzziduancfg hntbnzcfg) {
		cfgDAO.saveOrUpdate(hntbnzcfg);
	}
	
	public void saveLqcfg(Liqingziduancfg lqbnzcfg) {
		lqcfgDAO.saveOrUpdate(lqbnzcfg);
	}
	
	//混凝土拌和站材料用量查询走势图
	public String getHntCailiaoXml(List<HunnintuwuchaView> hv, HntbhzziduancfgView hntbhzField, HntbhzziduancfgView hntbhzisShow) {
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
		for (HunnintuwuchaView hunnintuView : hv) {
			strXML.append("<category label='");
			strXML.append(hunnintuView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != hntbhzField && null != hntbhzisShow) {
			if (StringUtil.Null2Blank(hntbhzisShow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShui1_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShui1_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui1_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShui1_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShui2_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShui2_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui2_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShui2_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShuini1_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShuini1_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini1_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShuini1_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShuini2_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShuini2_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini2_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShuini2_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getSha1_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getSha1_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha1_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getSha1_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShi1_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShi1_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi1_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShi1_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShi2_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShi2_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi2_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShi2_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getSha2_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getSha2_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha2_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getSha2_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getGuliao5_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGuliao5_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getGuliao5_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getGuliao5_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGuliao5_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getKuangfen3_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getKuangfen3_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getKuangfen3_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getKuangfen3_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getKuangfen3_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFeimeihui4_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFeimeihui4_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFeimeihui4_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFeimeihui4_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFeimeihui4_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao5_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFenliao5_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFenliao5_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao5_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFenliao5_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao6_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFenliao6_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFenliao6_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao6_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFenliao6_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji1_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji1_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji1_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji1_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji2_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji2_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji2_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji2_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji3_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji3_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji3_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji3_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji3_lilunzhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji4_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji4_shijizhi());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji4_lilunzhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji4_lilunzhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWaijiaji4_lilunzhi());
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartbanhecailiao", 1000, 250, false, false);
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
	
	public void appendSetXml4(StringBuilder strb, String value,String cailiao,String shijian) {
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
	
	public void appendSetXmlAll(StringBuilder strb, String value) {
		strb.append("<set value='");
		strb.append(value);
		strb.append("'/>");		
	}
	
	public void appendSetXmlAll2(StringBuilder strb, String value,String cailiao,String shijian) {
		strb.append("<set value='");
		strb.append(value);
		strb.append("' toolText='"+cailiao+","+shijian+","+value+"'/>");		
	}
	
	public String getLiqingCailiaoXml(List<LiqingmanualphbView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='实际用量百分比走势图(%)' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix=''> ");
		strXML.append("<categories>");
		for (LiqingmanualphbView lqView : hv) {
			strXML.append("<category label='");
	//		strXML.append(lqView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != lqField && null != lqisShow) {
			if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjysb());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getSjysb(),lqField.getSjysb(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
							
			if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg1());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg1(),lqField.getSjg1(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg2());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg2(),lqField.getSjg2(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg3());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg3(),lqField.getSjg3(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg4());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg4(),lqField.getSjg4(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg5());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg5(),lqField.getSjg5(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg6());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg6(),lqField.getSjg6(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg7());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg7(),lqField.getSjg7(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf1());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjf1(),lqField.getSjf1(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf2());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjf2(),lqField.getSjf2(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjlq());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjlq(),lqField.getSjlq(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
				
			if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjtjj());
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjtjj(),lqField.getSjtjj(),lqView.getBaocunshijian());					
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartlqclwucha", 1000, 250, false, false);
	}	
	
	public String getdapingLiqingCailiaoXml(List<LiqingphbView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='实际用量百分比走势图(%)' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix=''> ");
		strXML.append("<categories>");
		for (LiqingphbView lqView : hv) {
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
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getSjysb(),lqField.getSjysb(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
						
			if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg1());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg1(),lqField.getSjg1(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg2());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg2(),lqField.getSjg2(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg3());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg3(),lqField.getSjg3(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg4());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg4(),lqField.getSjg4(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg5());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg5(),lqField.getSjg5(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg6());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg6(),lqField.getSjg6(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg7());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjg7(),lqField.getSjg7(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf1());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjf1(),lqField.getSjf1(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf2());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjf2(),lqField.getSjf2(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjlq());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjlq(),lqField.getSjlq(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjtjj());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXml4(strXML, lqView.getPersjtjj(),lqField.getSjtjj(),lqView.getBaocunshijian());					
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapinglqclwucha", 675, 300, false, false);
	}
	
	//混凝土拌和站材料误差查询走势图
	public String getHntCailiaoWuchaXml(List<HunnintuwuchaView> hv, HntbhzziduancfgView hntbhzField, HntbhzziduancfgView hntbhzisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='误差走势图' subcaption='(");
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
		for (HunnintuwuchaView hunnintuView : hv) {
			strXML.append("<category label='");
			strXML.append(hunnintuView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != hntbhzField && null != hntbhzisShow) {
			if (StringUtil.Null2Blank(hntbhzisShow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShw1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}		
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShui2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getShw2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
						
			if (StringUtil.Null2Blank(hntbhzisShow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFlw1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
		
			if (StringUtil.Null2Blank(hntbhzisShow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShuini2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFlw2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}			
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGlw1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}			
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGlw2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}		
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getShi2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGlw3());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
		
			
			if (StringUtil.Null2Blank(hntbhzisShow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getSha2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGlw4());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}			
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getGuliao5_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getGlw5());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getKuangfen3_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFlw3());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			if (StringUtil.Null2Blank(hntbhzisShow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFeimeihui4_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFlw4());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}			
						
			if (StringUtil.Null2Blank(hntbhzisShow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao5_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFlw5());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
						
			if (StringUtil.Null2Blank(hntbhzisShow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getFenliao6_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getFlw6());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}			
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji1_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWjw1());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji2_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWjw2());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
			
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji3_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWjw3());
					strXML.append("'/>");
				}
				strXML.append("</dataset>");	
			}
			
						
			if (StringUtil.Null2Blank(hntbhzisShow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(hntbhzField.getWaijiaji4_shijizhi());
				strXML.append("'>");
				for (HunnintuwuchaView hunnintuView : hv) {
					strXML.append("<set value='");
					strXML.append(hunnintuView.getWjw4());
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartcailiaowucha", 1000, 250, false, false);
	}	
	
	public String getLqCailiaoWuchaXml(List<LiqingmanualphbView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow,int recordcheckbox,String shebeibianhao) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='误差走势图(%)' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("yAxisMinValue='-4' yAxisMaxValue='4' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix=''> ");
		strXML.append("<categories>");
		for (LiqingmanualphbView lqView : hv) {
			strXML.append("<category label='");
//			strXML.append(lqView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if(recordcheckbox==12){
			if (null != lqField && null != lqisShow) {
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='00FF40'  dashed='1' value='");
//					strXML.append("2");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FFFF00' dashed='1'  value='");
//					strXML.append("5");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FF0000'  dashed='1' value='");
//					strXML.append("10");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='00FF40' dashed='1'  value='");
//					strXML.append("-2");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FFFF00'  dashed='1'value='");
//					strXML.append("-5");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FF0000'  dashed='1' value='");
//					strXML.append("-10");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");
				
				
				if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjysb());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjysb(),lqField.getSjysb(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				
							
				if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg1());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg1(),lqField.getSjg1(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg2());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg2(),lqField.getSjg2(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg3());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg3(),lqField.getSjg3(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg4());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg4(),lqField.getSjg4(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg5());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg5(),lqField.getSjg5(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg6());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg6(),lqField.getSjg6(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg7());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg7(),lqField.getSjg7(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjf1());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjf1(),lqField.getSjf1(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjf2());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjf2(),lqField.getSjf2(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjlq());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjlq(),lqField.getSjlq(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjtjj());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjtjj(),lqField.getSjtjj(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				//设置上下线
				strXML.append("<trendlines>");
				strXML.append("<line  alpha='0' startValue='");
				strXML.append(2);
				strXML.append("' color='FFFF00' displayValue='");
				strXML.append(2);
				strXML.append("' showOnTop='1' dashed='1'/>");
				strXML.append("<line alpha='0'   startValue='");
				strXML.append(-2);
				strXML.append("' color='FFFF00' displayValue='");
				strXML.append(-2);
				strXML.append("' showOnTop='1' dashed='1' />");
				strXML.append("<line alpha='0'   startValue='");
				strXML.append(5);
				strXML.append("' color='00FF40' displayValue='");
				strXML.append(5);
				strXML.append("' showOnTop='1' dashed='1' />");
				strXML.append("<line alpha='0'   startValue='");
				strXML.append(-5);
				strXML.append("' color='00FF40' displayValue='");
				strXML.append(-5);
				strXML.append("' showOnTop='1' dashed='1' />");
				strXML.append("<line alpha='0'   startValue='");
				strXML.append(10);
				strXML.append("' color='FF0000' displayValue='");
				strXML.append(10);
				strXML.append("' showOnTop='1' dashed='1' />");
				strXML.append("<line alpha='0'   startValue='");
				strXML.append(-10);
				strXML.append("' color='FF0000' displayValue='");
				strXML.append(-10);
				strXML.append("' showOnTop='1' dashed='1' />");
			    strXML.append("</trendlines>");
			    strXML.append("<dataset  anchorRadius = '0' color='FFFF00' seriesName='");
				strXML.append("初级上限");
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					strXML.append("<set color='FFFF00' dashed='1'  value='");
					strXML.append(2);	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");	
				
				strXML.append("<dataset  anchorRadius = '0' color= 'FFFF00' seriesName='");
				strXML.append("初级下限");
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					strXML.append("<set color='FFFF00' dashed='1'  value='");
					strXML.append(-2);	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");
				strXML.append("<dataset  anchorRadius = '0' color='00FF40' seriesName='");
				strXML.append("中级上限");
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					strXML.append("<set color='00FF40' dashed='1'  value='");
					strXML.append(5);	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");	
				
				strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
				strXML.append("中级下限");
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					strXML.append("<set color='00FF40' dashed='1'  value='");
					strXML.append(-5);	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");
				strXML.append("<dataset  anchorRadius = '0' color='FF0000' seriesName='");
				strXML.append("高级上限");
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					strXML.append("<set color='FF0000' dashed='1'  value='");
					strXML.append(10);	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");	
				
				strXML.append("<dataset  anchorRadius = '0' color= 'FF0000' seriesName='");
				strXML.append("高级下限");
				strXML.append("'>");
				for (LiqingmanualphbView lqView : hv) {
					strXML.append("<set color='FF0000' dashed='1'  value='");
					strXML.append(-10);	
	                strXML.append("' />");
				}
				strXML.append("</dataset>");
			} 
		}else{
			if (null != lqField && null != lqisShow) {
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='00FF40'  dashed='1' value='");
//					strXML.append("2");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FFFF00' dashed='1'  value='");
//					strXML.append("5");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FF0000'  dashed='1' value='");
//					strXML.append("10");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='00FF40' dashed='1'  value='");
//					strXML.append("-2");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FFFF00'  dashed='1'value='");
//					strXML.append("-5");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");	
//				
//				strXML.append("<dataset   seriesName='");
//				strXML.append("");
//				strXML.append("'>");
//				for (LiqingphbView hunnintuView : hv) {
//					strXML.append("<set color='FF0000'  dashed='1' value='");
//					strXML.append("-10");	
//	                strXML.append("' />");
//				}
//				strXML.append("</dataset>");
				
				if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjysb());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjysb(),lqField.getSjysb(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				
							
				if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg1());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg1(),lqField.getSjg1(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg2());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg2(),lqField.getSjg2(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg3());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg3(),lqField.getSjg3(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg4());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg4(),lqField.getSjg4(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg5());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg5(),lqField.getSjg5(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg6());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg6(),lqField.getSjg6(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjg7());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjg7(),lqField.getSjg7(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjf1());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjf1(),lqField.getSjf1(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjf2());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjf2(),lqField.getSjf2(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjlq());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjlq(),lqField.getSjlq(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}
				
				if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
					strXML.append("<dataset seriesName='");
					strXML.append(lqField.getSjtjj());
					strXML.append("'>");
					for (LiqingmanualphbView lqView : hv) {
						appendSetXmlAll2(strXML, lqView.getWsjtjj(),lqField.getSjtjj(),lqView.getBaocunshijian());					
					}
					strXML.append("</dataset>");	
				}	
				Liqingziduancfg lqonelow=sysService.lqsmslowfindBybh(shebeibianhao);
				Liqingziduancfg lqonehigh=sysService.lqsmshighfindBybh(shebeibianhao);
				if(lqonehigh!=null && lqonelow!=null && lqField!=null && lqisShow!=null){
					strXML.append("<trendlines>");
					if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
						strXML.append("<line  alpha='0' startValue='");
						strXML.append(lqonehigh.getSjysb());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjysb());
						strXML.append("' showOnTop='1' dashed='1'/>");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjysb());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjysb());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg1());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg1());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg1());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg1());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg2());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg2());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg2());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg2());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg3());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg3());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg3());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg3());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg4());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg4());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg4());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg4());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg5());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg5());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg5());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg5());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg6());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg6());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg6());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg6());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjg7());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjg7());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjg7());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjg7());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjf1());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjf1());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjf1());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjf1());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjf2());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjf2());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjf2());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjf2());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjlq());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjlq());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjlq());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjlq());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
					if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonehigh.getSjtjj());
						strXML.append("' color='00FF00' displayValue='");
						strXML.append(lqonehigh.getSjtjj());
						strXML.append("' showOnTop='1' dashed='1' />");
						strXML.append("<line alpha='0'   startValue='");
						strXML.append(lqonelow.getSjtjj());
						strXML.append("' color='FF3333' displayValue='");
						strXML.append(lqonelow.getSjtjj());
						strXML.append("' showOnTop='1' dashed='1' />");
					}
					
				    strXML.append("</trendlines>");
				    if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
					    strXML.append("<dataset  anchorRadius = '0' color='00FF00' seriesName='");
						strXML.append(lqField.getSjysb()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjysb());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjysb()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjysb());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
					
				    if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg1()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg1());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg1()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg1());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg2()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg2());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg2()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg2());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg3()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg3());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg3()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg3());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg4()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg4());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg4()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg4());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg5()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg5());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg5()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg5());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg6()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg6());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg6()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg6());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjg7()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjg7());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjg7()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjg7());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjf1()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjf1());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjf1()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjf1());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjf2()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjf2());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjf2()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjf2());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjlq()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjlq());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjlq()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjlq());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				    
				    if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
						strXML.append("<dataset  anchorRadius = '0' color= '00FF00' seriesName='");
						strXML.append(lqField.getSjtjj()+"预警上限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='00FF00' dashed='1'  value='");
							strXML.append(lqonehigh.getSjtjj());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
						
						strXML.append("<dataset  anchorRadius = '0' color= 'FF3333' seriesName='");
						strXML.append(lqField.getSjtjj()+"预警下限");
						strXML.append("'>");
						for (LiqingmanualphbView lqView : hv) {
							strXML.append("<set color='FF3333' dashed='1'  value='");
							strXML.append(lqonelow.getSjtjj());	
			                strXML.append("' />");
						}
						strXML.append("</dataset>");
				    }
				}
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartlqcailiaowucha", 1000, 250, false, false);
	}	
	
	public String getdapingLqCailiaoWuchaXml(List<LiqingphbView> hv, LiqingziduancfgView lqField, LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='误差走势图(%)' subcaption='(");
		strXML.append(hv.get(0).getBaocunshijian());
		strXML.append("至");
		strXML.append(hv.get(hv.size()-1).getBaocunshijian());
		strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("yAxisMinValue='-4' yAxisMaxValue='4' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(hv.size()-1);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix=''> ");
		strXML.append("<categories>");
		for (LiqingphbView lqView : hv) {
			strXML.append("<category label='");
//			strXML.append(lqView.getBaocunshijian());
			strXML.append("'/>");
		}
		strXML.append("</categories>");
		if (null != lqField && null != lqisShow) {
			strXML.append("<dataset   seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (LiqingphbView hunnintuView : hv) {
				strXML.append("<set color='00FF40'  dashed='1' value='");
				strXML.append("2");	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset   seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (LiqingphbView hunnintuView : hv) {
				strXML.append("<set color='FFFF00' dashed='1'  value='");
				strXML.append("5");	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset   seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (LiqingphbView hunnintuView : hv) {
				strXML.append("<set color='FF0000'  dashed='1' value='");
				strXML.append("10");	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset   seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (LiqingphbView hunnintuView : hv) {
				strXML.append("<set color='00FF40' dashed='1'  value='");
				strXML.append("-2");	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset   seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (LiqingphbView hunnintuView : hv) {
				strXML.append("<set color='FFFF00'  dashed='1'value='");
				strXML.append("-5");	
                strXML.append("' />");
			}
			strXML.append("</dataset>");	
			
			strXML.append("<dataset   seriesName='");
			strXML.append("");
			strXML.append("'>");
			for (LiqingphbView hunnintuView : hv) {
				strXML.append("<set color='FF0000'  dashed='1' value='");
				strXML.append("-10");	
                strXML.append("' />");
			}
			strXML.append("</dataset>");
			if (lqisShow.getSjysb().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjysb());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjysb(),lqField.getSjysb(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			
						
			if (lqisShow.getSjg1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg1());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg1(),lqField.getSjg1(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg2());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg2(),lqField.getSjg2(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg3().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg3());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg3(),lqField.getSjg3(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg4().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg4());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg4(),lqField.getSjg4(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg5().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg5());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg5(),lqField.getSjg5(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg6().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg6());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg6(),lqField.getSjg6(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjg7().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjg7());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjg7(),lqField.getSjg7(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjf1().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf1());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjf1(),lqField.getSjf1(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjf2().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjf2());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjf2(),lqField.getSjf2(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjlq().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjlq());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjlq(),lqField.getSjlq(),lqView.getBaocunshijian());					
				}
				strXML.append("</dataset>");	
			}
			
			if (lqisShow.getSjtjj().equalsIgnoreCase("1")) {
				strXML.append("<dataset seriesName='");
				strXML.append(lqField.getSjtjj());
				strXML.append("'>");
				for (LiqingphbView lqView : hv) {
					appendSetXmlAll2(strXML, lqView.getWsjtjj(),lqField.getSjtjj(),lqView.getBaocunshijian());					
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapinglqcailiaowucha", 675, 300, false, false);
	}	
	
	public GenericPageMode lqmanualphbviewwuchalist(String shebeibianhao,String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize,String peifan) {
		return lqviewDAO.lqmanualphbviewwuchalist(shebeibianhao,startTimeOne,endTimeOne,biaoduan, 
				xiangmubu, fn, bsid, offset, pagesize,peifan);
	}
}
