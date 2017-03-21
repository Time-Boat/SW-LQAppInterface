package com.mss.shtoone.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.domain.ChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.EnvironmentView;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.Hntview;
import com.mss.shtoone.domain.HunnintuView;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.LqshaifenjieguoView;
import com.mss.shtoone.domain.Sessionupdatetime;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.domain.Shaifenziduancfg;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Shuiwenxixxlilun;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.SpeeddataView;
import com.mss.shtoone.domain.Tanpujihuaxinxi;
import com.mss.shtoone.domain.TemperaturedataView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.TopRealTjjdataView;
import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.fusioncharts.FusionChartsCreator;
import com.mss.shtoone.persistence.ChuliaokouTemperaturedataViewDAO;
import com.mss.shtoone.persistence.HntbhzCfgViewDAO;
import com.mss.shtoone.persistence.HntviewDAO;
import com.mss.shtoone.persistence.HunnintuViewDAO;
import com.mss.shtoone.persistence.LiqingViewDAO;
import com.mss.shtoone.persistence.LiqingmanualphbViewDAO;
import com.mss.shtoone.persistence.LiqingphbViewDAO;
import com.mss.shtoone.persistence.LiqingziduancfgViewDAO;
import com.mss.shtoone.persistence.SessionupdatetimeDAO;
import com.mss.shtoone.persistence.ShuiwenmanualphbViewDAO;
import com.mss.shtoone.persistence.SpeeddataViewDAO;
import com.mss.shtoone.persistence.ShuiwenphbViewDAO;
import com.mss.shtoone.persistence.TemperaturedataViewDAO;
import com.mss.shtoone.persistence.TopLiqingViewDAO;
import com.mss.shtoone.persistence.TopRealChuliaokouTemperaturedataViewDAO;
import com.mss.shtoone.persistence.TopRealMaxLiqingViewDAO;
import com.mss.shtoone.persistence.TopRealMaxShuiwenxixxViewDAO;
import com.mss.shtoone.persistence.TopRealSpeeddataViewDAO;
import com.mss.shtoone.persistence.TopRealTemperaturedataViewDAO;
import com.mss.shtoone.persistence.TophunnintuViewDAO;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.StringUtil;

/**      
 * 数据服务类
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class GetdataService {
	private static Log logger = LogFactory.getLog(GetdataService.class);
	private static String lqcl = "lqcl";
	private static String glwd = "glwd";
	private static String lqwd = "lqwd";
	private static String lqysb = "lqysb";
	private static String cplwd = "cplwd";
	private static String hntcl = "hntcl";
	private static String hntsj = "hntsj";
	private static String speed = "speed";
	private static String tmp = "tmp";
	private static String tjj = "tjj";
	private static String swcl = "swcl";
	

	@Autowired
	private TophunnintuViewDAO topDAO;
	
	@Autowired
	private TopLiqingViewDAO toplqDAO;
	
	@Autowired
	private SessionupdatetimeDAO suDAO;	

	@Autowired
	private HntbhzCfgViewDAO hntcfgDAO;
	
	@Autowired
	private LiqingziduancfgViewDAO lqcfgDAO;
	
	@Autowired
	private HunnintuViewDAO hntviewDAO;
	
	@Autowired
	private TopRealMaxLiqingViewDAO topreallqviewDAO;
	
	@Autowired
	private TopRealTemperaturedataViewDAO toprealtmpviewDAO;
	
	@Autowired
	private TopRealChuliaokouTemperaturedataViewDAO toprealChuliaokoutmpviewDAO;
	
	@Autowired
	private TopRealSpeeddataViewDAO toprealspeedviewDAO;
	
	@Autowired
	private HntviewDAO hntDAO;
	
	@Autowired
	private LiqingphbViewDAO lqphbviewDAO;
	
	@Autowired
	private TemperaturedataViewDAO tmpdataviewDAO;
	
	@Autowired
	private ChuliaokouTemperaturedataViewDAO clktmpdataviewDAO;
	
	@Autowired
	private SpeeddataViewDAO speeddataviewDAO;
	
	@Autowired
	private LiqingmanualphbViewDAO lqphbmanualviewDAO;
	
	@Autowired
	private ShuiwenmanualphbViewDAO swphbmanualviewDAO;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private TopRealMaxShuiwenxixxViewDAO topswDAO;
	
	@Autowired
	private ShuiwenphbViewDAO swphbViewDAO;
	
	@Autowired
	private LiqingViewDAO lqViewDAO;

	@Autowired
	private ShuiwenxixxlilunService swllService;

	//主界面温度表盘指示1
	public String getTemperatureAngularXml() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", StringUtil.getWebrootpath()+"/realdata/Angular3.xml","", "chart2Id", 250, 250, false, false);
	}
	//主界面温度表盘指示2
	public String getTemperatureAngularXml1() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", StringUtil.getWebrootpath()+"/realdata/Angular8.xml","", "chart3Id", 250, 250, false, false);
	}
	//实时温度刷新
	public String updateTemperatureAngularRealValue() {
		StringBuilder strXML = new StringBuilder(""); 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());
		strXML.append("&label=" + timeLabel + "&value=" +getRandom(100, 200));
		return strXML.toString();		
	}	
	
	public double getRandom(int lowerLimit, int upperLimit) {		
		double randomValue = Math.random()*100*(upperLimit-lowerLimit)/100+lowerLimit;
		long factor = (long)Math.pow(10,2);
		randomValue = randomValue * factor;
		long tmp = Math.round(randomValue);
		return (double)tmp / factor;
	}
	
	public double getRandom(float lowerLimit, float upperLimit) {		
		double randomValue = Math.random()*100*(upperLimit-lowerLimit)/100+lowerLimit;
		long factor = (long)Math.pow(10,2);
		randomValue = randomValue * factor;
		long tmp = Math.round(randomValue);
		return (double)tmp / factor;
	}
	
	//主界面压力机圆球指示
	public String getPressureBubbleXml() {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart bgAlpha='0' canvasBgAlpha='0' ");
		strXML.append("xAxisName='监控时间' showYAxisValues='0' numDivLines='4' showAlternateVGridColor='1' ");
		strXML.append("AlternateVGridAlpha='30'> ");
		strXML.append("<categories verticalLineColor='666666' verticalLineAlpha='20'> ");
		strXML.append("<category label='10:00' x='0' /> ");
		strXML.append("<category label='10:10' x='5' sL='1'/> ");
		strXML.append("<category label='10:20' x='10' sL='1'/> ");
		strXML.append("<category label='10:30' x='15' sL='1'/> ");
		strXML.append("<category label='10:40' x='20' sL='1'/> ");
		strXML.append("</categories> ");
		strXML.append("<dataSet seriesName='压力机' showValues='0'> ");
		strXML.append("<set x='2' y='35' z='40' name='Mango' showValue='1' displayValue='270KN' toolText='第一合同段：压力270KN'/> ");
		strXML.append("<set x='8' y='40' z='9' name='Apple' showValue='1' displayValue='70KN' toolText='第二合同段：压力70KN'/> ");
		strXML.append("<set x='12' y='5' z='10' name='Orange' showValue='1' displayValue='1400KN' toolText='第十合同段：压力1400KN'/> ");
		strXML.append("<set x='17' y='50' z='8' name='Strawberry' showValue='1' displayValue='880KN' toolText='第五合同段：压力880KN'/> ");
		strXML.append("<set x='19' y='10' z='13' name='Kiwi' showValue='1' displayValue='60KN' toolText='第一合同段：压力60KN'/> ");
		strXML.append("</dataSet> ");
		strXML.append("<dataSet color='ebce05' seriesName='万能试验机' > ");
		strXML.append("<set x='4' y='15' z='8' name='Mango' showValue='1' displayValue='1280KN' toolText='第一合同段：拉力1280KN'/> ");
		strXML.append("<set x='6' y='60' z='10' name='Apple' showValue='1' displayValue='1660KN' toolText='第八合同段：拉力1660KN'/> ");
		strXML.append("<set x='10' y='35' z='15' name='Orange' showValue='1' displayValue='1070KN' toolText='第八合同段：拉力1070KN'/> ");
		strXML.append("<set x='15' y='10' z='8' name='Strawberry' showValue='1' displayValue='1120KN' toolText='第四合同段：拉力1120KN'/> ");
		strXML.append("<set x='17' y='20' z='9' name='Kiwi' showValue='1' displayValue='980KN' toolText='第一合同段：拉力980KN'/> ");
		strXML.append("</dataSet> ");
		strXML.append("</chart>");
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "chart1Id", 500, 560, false, false);
	}	

	//主界面压力机指示刷新
	public String updatePressureBubbleXml() {
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart bgAlpha='0' canvasBgAlpha='0' ");
		strXML.append("xAxisName='监控时间' showYAxisValues='0' numDivLines='4' showAlternateVGridColor='1' ");
		strXML.append("AlternateVGridAlpha='30'> ");
		strXML.append("<categories verticalLineColor='666666' verticalLineAlpha='20'> ");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());
		strXML.append("<category label='"+timeLabel+"' x='0' /> ");
		cal.add(Calendar.SECOND, +10);
		timeLabel = sdf.format(cal.getTime());
		strXML.append("<category label='"+timeLabel+"' x='5' sL='1'/> ");
		cal.add(Calendar.SECOND, +10);
		timeLabel = sdf.format(cal.getTime());		
		strXML.append("<category label='"+timeLabel+"' x='10' sL='1'/> ");
		cal.add(Calendar.SECOND, +10);
		timeLabel = sdf.format(cal.getTime());
		strXML.append("<category label='"+timeLabel+"' x='15' sL='1'/> ");
		cal.add(Calendar.SECOND, +10);
		timeLabel = sdf.format(cal.getTime());
		strXML.append("<category label='"+timeLabel+"' x='20' sL='1'/> ");
		strXML.append("</categories> ");
		strXML.append("<dataSet seriesName='压力机' showValues='0'> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(30,45)+"' name='Mango' showValue='1' displayValue='"+getRandomString(100,2000)+"KN' toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Apple' showValue='1' displayValue='"+getRandomString(100,2000)+"KN'  toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Orange' showValue='1' displayValue='"+getRandomString(100,2000)+"KN'  toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Strawberry' showValue='1' displayValue='"+getRandomString(100,2000)+"KN'  toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Kiwi' showValue='1' displayValue='"+getRandomString(100,2000)+"KN'  toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("</dataSet> ");
		strXML.append("<dataSet color='ebce05' seriesName='万能试验机' showValues='0'> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Mango' showValue='1' displayValue='"+getRandomString(100,2000)+"KN' toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Apple' showValue='1' displayValue='"+getRandomString(100,2000)+"KN' toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Orange' showValue='1' displayValue='"+getRandomString(100,2000)+"KN' toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Strawberry' showValue='1' displayValue='"+getRandomString(100,2000)+"KN' toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("<set x='"+getRandomString(2,18)+"' y='"+getRandomString(15,60)+"' z='"+getRandomString(8,16)+"' name='Kiwi' showValue='1' displayValue='"+getRandomString(100,2000)+"KN' toolText='第"+getRandomString(1,20)+"合同段："+getRandomString(1,10)+"号设备'/> ");
		strXML.append("</dataSet> ");
		strXML.append("</chart>");
	    return strXML.toString();
	}
	
	public String getRandomString(int lowerLimit, int upperLimit) {
		double rd = getRandom(lowerLimit, upperLimit);
		return String.valueOf((int)rd);
	}
	
	public String getRandomString(float lowerLimit, float upperLimit) {
		double rd = getRandom(lowerLimit, upperLimit);
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.00");  
		return df.format(rd); 
	}
	
	//混凝土拌和时间线形指示
	public String getTemperatureLineXml() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", StringUtil.getWebrootpath()+"/realdata/RealTimeLine1.xml","", "chart4Id", 500, 310, false, false);
	}
	
	//混凝土拌和时间刷新
/*	public String updateTemperatureLineRealValue() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());	
		return "&label=" + timeLabel + "&value=" +getRandom(100, 200)+"|"+getRandom(110, 190);		
	}*/
	
	public String updateJiaobanshijianValue(String sessionid, String shebeibianhao) {
		List results = findbysql("select baocunshijian,jiaobanshijian,chuliaoshijian from TophunnintuView where shebeibianhao = '"+shebeibianhao+"'");
		String result = "";
		if (results.size()>0) {
			Object[] row = (Object[]) results.get(0);   
            String baocunshijian = (String) row[2]; 
            if (StringUtil.Null2Blank(baocunshijian).length()==0) {
            	baocunshijian = (String) row[0];
			} 
            if (updateSessionUpdateTime(sessionid, shebeibianhao, baocunshijian,hntsj)) {
            String jiaobanshijian = (String) row[1]; 
            result = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian);
			if (logger.isDebugEnabled()) {
				logger.debug(result);
			}
            }
		}			
		return result;
	}	
	
	public String updatexcqueryValue(String sessionid, Integer biaoduan, String proname, int bsid) {
		StringBuffer strbuf = new StringBuffer();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Calendar day=Calendar.getInstance();
    	day.add(Calendar.MONTH, -10);
    	Date shijian=day.getTime();
		try {
	    List<TopRealSpeeddataView> toplist = sysService.limitSpeedlist(biaoduan, proname, bsid);
	    for (TopRealSpeeddataView speeddata : toplist) {
	    	strbuf.append("|");
    		strbuf.append(speeddata.getSudu());
	    	if (updateSessionUpdateTime(sessionid, speeddata.getGpsno(), speeddata.getShijian(), speed)) {
	    		if (sdf.parse(speeddata.getShijian()).after(shijian)) {
	              shijian = sdf.parse(speeddata.getShijian());
	    		}
		   } 
	    }
	    List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(biaoduan, proname, bsid);
	    for (TopRealTemperaturedataView tmpdata : toptmplist) {
	    	strbuf.append("|");
    		strbuf.append(tmpdata.getTmpdata());
	    	if (updateSessionUpdateTime(sessionid, tmpdata.getTmpno(), tmpdata.getTmpshijian(), tmp)) {
	    		if (sdf.parse(tmpdata.getTmpshijian()).after(shijian)) {
	              shijian = sdf.parse(tmpdata.getTmpshijian());
	    		}
		   }
		}
		} catch (ParseException e) {
		}
		if (shijian.after(day.getTime())) {
			return "&label="+sdf.format(shijian)+"&value="+strbuf.toString().substring(1);
		} else {
            return "";
		}
	}	
	
	public String updatetjjqueryValue(String sessionid, Integer biaoduan, String proname, int bsid) {
		StringBuffer strbuf = new StringBuffer();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Calendar day=Calendar.getInstance();
    	day.add(Calendar.MONTH, -10);
    	Date shijian=day.getTime();
		try {
	    List<TopRealTjjdataView> toplist = sysService.limitTjjlist(biaoduan, proname, bsid);
	    for (TopRealTjjdataView tjjdata : toplist) {
	    	strbuf.append("|");
    		strbuf.append(tjjdata.getTjjdata());
	    	if (updateSessionUpdateTime(sessionid, tjjdata.getTjjno(), tjjdata.getTjjshijian(), tjj)) {
	    		if (sdf.parse(tjjdata.getTjjshijian()).after(shijian)) {
	              shijian = sdf.parse(tjjdata.getTjjshijian());
	    		}
		   } 
	    }
		} catch (ParseException e) {
		}
		if (shijian.after(day.getTime())) {
			return "&label="+sdf.format(shijian)+"&value="+strbuf.toString().substring(1);
		} else {
            return "";
		}
	}
	
	public String updatelqysbValue(String sessionid, String shebeibianhao) {
		List results = findbysql("select baocunshijian,sjysb,shijian from TopLiqingView where shebeibianhao = '"+shebeibianhao+"'");
		String result = "";
		if (results.size()>0) {
			Object[] row = (Object[]) results.get(0);   
            String baocunshijian =GetDate.formatshijian2((String) row[2]); 
            if (StringUtil.Null2Blank(baocunshijian).length()==0) {
            	baocunshijian = GetDate.formatshijian2((String) row[0]);
			} 
            if (updateSessionUpdateTime(sessionid, shebeibianhao, baocunshijian, lqysb)) {
            String jiaobanshijian = (String) row[1]; 
            result = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian);
			if (logger.isDebugEnabled()) {
				logger.debug(result);
			}
            }
		}			
		return result;
	}	
	
	public String updatetop10lqysbValue(String sessionid, String shebeibianhao) {
		List results = findbysql("select baocunshijian,sjysb,shijian from Top10ysbView where shebeibianhao = '"+shebeibianhao+"'");
		String result = "";
		String result1="";
		String result2="";
		String result3="";
		String result4="";
		String result5="";
		String result6="";
		String result7="";
		String result8="";
		String result9="";
		String result10="";
		if (results.size()>0) {
			for(int i=0;i<results.size();i++){
				Object[] row = (Object[]) results.get(i);   
	            String baocunshijian = (String) row[2]; 
	            if (StringUtil.Null2Blank(baocunshijian).length()==0) {
	            	baocunshijian = (String) row[0];
				} 
	            if (updateSessionUpdateTime(sessionid, shebeibianhao, baocunshijian, lqysb)) {
	            String jiaobanshijian = (String) row[1]; 
	            if(i==0){
	            	result1 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==1){
	            	result2 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==2){
	            	result3 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==3){
	            	result4 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==4){
	            	result5 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==5){
	            	result6 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==6){
	            	result7 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==7){
	            	result8 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==8){
	            	result9 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }else if(i==9){
	            	result10 = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(jiaobanshijian) ;
	            }
				if (logger.isDebugEnabled()) {
					logger.debug(result);
				}
	            }
			}			
		}	
		result=result1+result2+result3+result4+result5+result6+result7+result8+result9+result10;
		return result;
	}	

	//混凝土产能分析
	public String getHntAnalysisColumnXml(List<HunnintuView> hntviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
        strXML.append("maxColWidth='70' rotateYAxisName='0' caption='拌和站产能分析' ");  
        strXML.append("startingAngle='125' shownames='1' showvalues='1' numberSuffix='m³'");
        strXML.append("formatNumberScale='0'>");
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
		default:
			sxb = "月份";
			break;
		}
        for (HunnintuView hv : hntviews) {
        	strXML.append("<set label='");
        	strXML.append(hv.getXa());
        	strXML.append(sxa);
        	strXML.append(hv.getXb());
        	strXML.append(sxb);
        	strXML.append("' value='");
        	strXML.append(hv.getGujifangshu());
        	strXML.append("' displayValue='");
        	strXML.append(hv.getGujifangshu());
        	strXML.append("m³(");
        	strXML.append(hv.getPangshu());
        	strXML.append("盘)");
        	strXML.append("' link='JavaScript:myJS(");
        	strXML.append(hv.getXa());
        	strXML.append(",");
        	strXML.append(hv.getXb());
           	strXML.append(",");
        	strXML.append(cnfxlx);
        	strXML.append(")'/>");
		}       
        strXML.append("</chart>"); 
        return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Column3D.swf", "",strXML.toString(), "HntAnalysisChart", 1000, 300, false, false);
	}	
	
	//沥青综合分析
	public String getlqzhfxXml(List<LiqingView> lqviews, int cnfxlx) {
		String strXML = createlqzhfxxml(lqviews, cnfxlx); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "hntzhfxchart", 1000, 500, false, false);
	}
	//大屏显示日产量统计
	public String getdapinglqzhfxXml_day(List<LiqingView> lqviews, int cnfxlx) {
		String strXML = createlqzhfxxml(lqviews, cnfxlx); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "chartdapingchanliangtongji_day", 675, 345, false, false);
	}
	
	//大屏显示周产量统计
	public String getdapinglqzhfxXml_week(List<LiqingView> lqviews, int cnfxlx) {
		String strXML = createlqzhfxxml(lqviews, cnfxlx); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "chartdapingchanliangtongji_week", 675, 345, false, false);
	}
	
	//大屏显示月产量统计
	public String getdapinglqzhfxXml_month(List<LiqingView> lqviews, int cnfxlx) {
		String strXML = createlqzhfxxml(lqviews, cnfxlx); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "chartdapingchanliangtongji_month", 675, 345, false, false);
	}
	
	public String mainlistgetlqzhfxXml(List<LiqingView> lqviews, int cnfxlx) {
		String strXML = mainlistcreatelqzhfxxml(lqviews, cnfxlx); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "hntzhfxchart", 500, 200, false, false);
	}
	
	private String mainlistcreatelqzhfxxml(List<LiqingView> lqviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='2' caption='最近生产情况' "); 
        strXML.append(" clickURL='"); 
        strXML.append(StringUtil.getWebrootpath()); 
        strXML.append("/query/lqzhfx?pid=2&record=13&'"); 
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
        for (LiqingView hv : lqviews) {
        	strXML.append("<category label='"); 
        	strXML.append(hv.getXa());
        	strXML.append("-");
        	strXML.append(hv.getXb());
            strXML.append("'/>");    	
        }
        strXML.append("</categories>"); 
        
        strXML.append("<dataset seriesName='生产量(t)'  showValues='0'>");
        for (LiqingView hv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(hv.getChangliang());
        	strXML.append("' displayValue='");
        	strXML.append(hv.getChangliang());
        	strXML.append(",");
        	strXML.append(hv.getPangshu());
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");
        
//        strXML.append("<dataset seriesName='厚度(cm)' renderAs='Area' parentYAxis='S' showValues='0'>"); 
//        for (LiqingView hv : lqviews) {
//        	strXML.append("<set value='"); 
//        	strXML.append(hv.getHoudu());
//        	strXML.append("' displayValue='");
//        	strXML.append(hv.getHoudu());
//        	strXML.append("cm");
//        	strXML.append("'/>");
//        }
//        strXML.append("</dataset>"); 
      
        
//        strXML.append("<dataset lineThickness='3' seriesName='预警率(%)' renderAs='Line' parentYAxis='S' showValues='1'>");
//        for (LiqingView hv : lqviews) {
//        	strXML.append("<set value='"); 
//        	strXML.append(hv.getChaobiaobfl());
//        	strXML.append("' displayValue='");
//        	strXML.append(hv.getChaobiaobfl());
//        	strXML.append("%(");
//        	strXML.append(hv.getChaobiaops());
//        	strXML.append("盘)");        	
//        	strXML.append("'/>");
//        }
//        strXML.append("</dataset>");     
//        strXML.append("<dataset lineThickness='3' seriesName='高级超标率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
//        for (LiqingView lqv : lqviews) {
//        	strXML.append("<set value='"); 
//        	strXML.append(lqv.getBiaoshi());
//        	strXML.append("' displayValue='");
//        	strXML.append(lqv.getBiaoshi());
//        	strXML.append("%(");
//        	strXML.append(lqv.getBeizhu());
//        	strXML.append("盘)");        	
//        	strXML.append("'/>");
//        }
//        strXML.append("</dataset>");   
//        
//        strXML.append("<dataset lineThickness='3' seriesName='中级超标率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
//        for (LiqingView lqv : lqviews) {
//        	strXML.append("<set value='"); 
//        	strXML.append(lqv.getPmend());
//        	strXML.append("' displayValue='");
//        	strXML.append(lqv.getPmend());
//        	strXML.append("%(");
//        	strXML.append(lqv.getPmbegin());
//        	strXML.append("盘)");        	
//        	strXML.append("'/>");
//        }
//        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getAmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getAmend());
        	strXML.append(",");
        	strXML.append(lqv.getAmbegin());       	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>"); 
        strXML.append("</chart>");
		return strXML.toString();
	}
	
	private String createlqzhfxxml(List<LiqingView> lqviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        String sxa = "年";
        String sxb;
        switch (cnfxlx) {
		case 1:
			strXML.append("<?xml version='1.0' encoding='utf-8'?>");
	        strXML.append("<chart palette='2' caption='沥青混合料生产量统计(t)' "); 
	        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
	        strXML.append(" PYAxisName='生产量(t)' SYAxisName='预警率(%)'"); 
	        strXML.append(" SNumberSuffix='%' formatNumberScale='0'>"); 
			sxb = "季度";
			break;
		case 2:
			strXML.append("<?xml version='1.0' encoding='utf-8'?>");
	        strXML.append("<chart palette='2' caption='沥青混合料生产量统计(t)' "); 
	        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
	        strXML.append(" PYAxisName='生产量(t)' SYAxisName='预警率(%)'"); 
	        strXML.append(" SNumberSuffix='%' formatNumberScale='0'>"); 
			sxb = "月份";
			break;
		case 3:
			strXML.append("<?xml version='1.0' encoding='utf-8'?>");
	        strXML.append("<chart palette='2' caption='沥青混合料生产量统计(t)' "); 
	        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
	        strXML.append(" PYAxisName='生产量(t)' SYAxisName='预警率(%)'"); 
	        strXML.append(" SNumberSuffix='%' formatNumberScale='0'>"); 
			sxb = "周";
			break;
		case 4:
			strXML.append("<?xml version='1.0' encoding='utf-8'?>");
	        strXML.append("<chart palette='2' caption='沥青混合料生产量统计(kg)' "); 
	        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
	        strXML.append(" PYAxisName='生产量(kg)' SYAxisName='预警率(%)'"); 
	        strXML.append(" SNumberSuffix='%' formatNumberScale='0'>"); 
			sxa = "月";
			sxb = "日";
			break;
		default:
			strXML.append("<?xml version='1.0' encoding='utf-8'?>");
	        strXML.append("<chart palette='2' caption='沥青混合料生产量统计(t)' "); 
	        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
	        strXML.append(" PYAxisName='生产量(t)' SYAxisName='预警率(%)'"); 
	        strXML.append(" SNumberSuffix='%' formatNumberScale='0'>"); 
			sxb = "月份";
			break;
		}
        strXML.append("<categories>");
        for (LiqingView hv : lqviews) {
        	strXML.append("<category label='"); 
        	strXML.append(hv.getXa());
        	strXML.append("-");
        	strXML.append(hv.getXb());
            strXML.append("'/>");    	
        }
        strXML.append("</categories>"); 
        
        switch (cnfxlx) {
        case 4:
        	strXML.append("<dataset seriesName='生产量(kg)'  showValues='0'>");
            for (LiqingView hv : lqviews) {
            	strXML.append("<set value='"); 
            	strXML.append(hv.getChangliang());
            	strXML.append("' displayValue='");
            	strXML.append(hv.getChangliang());
            	strXML.append(",");
            	strXML.append(hv.getPangshu());
            	strXML.append("'/>");
            }
            strXML.append("</dataset>");
            break;
           default:
        	   strXML.append("<dataset seriesName='生产量(t)'  showValues='0'>");
               for (LiqingView hv : lqviews) {
               	strXML.append("<set value='"); 
               	strXML.append(hv.getChangliang());
               	strXML.append("' displayValue='");
               	strXML.append(hv.getChangliang());
               	strXML.append(",");
               	strXML.append(hv.getPangshu());
               	strXML.append("'/>");
               }
               strXML.append("</dataset>");
              break;
        }
        
        
//        strXML.append("<dataset seriesName='厚度(cm)' renderAs='Area' parentYAxis='S' showValues='0'>"); 
//        for (LiqingView hv : lqviews) {
//        	strXML.append("<set value='"); 
//        	strXML.append(hv.getHoudu());
//        	strXML.append("' displayValue='");
//        	strXML.append(hv.getHoudu());
//        	strXML.append("cm");
//        	strXML.append("'/>");
//        }
//        strXML.append("</dataset>"); 
        
        strXML.append("<dataset lineThickness='3' seriesName='高级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getBiaoshi());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getBiaoshi());
        	strXML.append(",");
        	strXML.append(lqv.getBeizhu());      	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");   
        
        strXML.append("<dataset lineThickness='3' seriesName='中级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getPmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getPmend());
        	strXML.append(",");
        	strXML.append(lqv.getPmbegin());
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='初级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getAmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getAmend());
        	strXML.append(",");
        	strXML.append(lqv.getAmbegin());
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");        
        strXML.append("</chart>");
		return strXML.toString();		
	}
	
	//沥青产能分析
	public String getLqAnalysisColumnXml(List<LiqingView> lqviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
        strXML.append("maxColWidth='70' rotateYAxisName='0' caption='拌和站产能分析(t)' ");  
        strXML.append("startingAngle='125' shownames='1' showvalues='0' ");
        strXML.append("formatNumberScale='0'>");
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
		case 5:
			sxa = "日";
			sxb = "时";
			break;
		default:
			sxb = "月份";
			break;
		}
        strXML.append("<categories>");
        for (LiqingView lq : lqviews) {
        	strXML.append("<category label='"); 
        	strXML.append(lq.getXa());
        	strXML.append(sxa);
        	strXML.append(lq.getXb());
        	strXML.append(sxb);
            strXML.append("'/>"); 
        }
        strXML.append("</categories>");      
        strXML.append("<dataset seriesName='生产量(t)'  showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(String.format("%1$.2f",Double.valueOf(lqv.getChangliang())/1000));
        	strXML.append("' displayValue='");
        	strXML.append(String.format("%1$.2f",Double.valueOf(lqv.getChangliang())/1000));
        	strXML.append(",");
        	strXML.append(lqv.getPangshu());
        	strXML.append("' link='JavaScript:myJS(");
        	strXML.append(lqv.getXa());
        	strXML.append(",");
        	strXML.append(lqv.getXb());
           	strXML.append(",");
        	strXML.append(cnfxlx);
        	strXML.append(")'/>");
        }
        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='高级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getBiaoshi());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getBiaoshi());
        	strXML.append(",");
        	strXML.append(lqv.getBeizhu());      	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");   
        
        strXML.append("<dataset lineThickness='3' seriesName='中级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getPmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getPmend());
        	strXML.append(",");
        	strXML.append(lqv.getPmbegin());       	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='初级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (LiqingView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getAmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getAmend());
        	strXML.append(",");
        	strXML.append(lqv.getAmbegin());      	
        	strXML.append("'/>");
        }
         
        strXML.append("</dataset>"); 
        strXML.append("</chart>");
        return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "LqAnalysisChart", 1000, 300, false, false);
	}	
	
	//混凝土各材料用量饼图
	public String getHntShijiAnalysisPieXml(HunnintuView hv, HntbhzziduancfgView fieldview) {
		StringBuilder strXML = new StringBuilder(""); 
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
		strXML.append("rotateYAxisName='0' caption='实际材料用量'");   
		strXML.append("showValues='1'  numberSuffix='kg' formatNumberScale='0'>");
		if (null != hv) {
			strXML.append("<set label='");
			strXML.append(fieldview.getSha1_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getSha1_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSha2_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getSha2_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getShi1_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getShi1_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getShi2_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getShi2_shijizhi());
			strXML.append("' />");

			strXML.append("<set label='");
			strXML.append(fieldview.getShuini1_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getShuini1_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getShuini2_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getShuini2_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getKuangfen3_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getKuangfen3_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getFeimeihui4_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getFeimeihui4_shijizhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getFenliao5_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getFenliao5_shijizhi());
			strXML.append("' />");
			
			strXML.append("<set label='");
			strXML.append(fieldview.getWaijiaji1_shijizhi());
			strXML.append("' value='");
			strXML.append(hv.getWaijiaji1_shijizhi());
			strXML.append("' />");
		}	
        strXML.append("</chart>"); 
		return strXML.toString();		
	}	
	
	//沥青各材料用量饼图
	public String getLqShijiAnalysisPieXml(LiqingView lqv, LiqingziduancfgView fieldview) {
		StringBuilder strXML = new StringBuilder(""); 
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
		strXML.append("rotateYAxisName='0' caption='实际材料用量'");   
		strXML.append("showValues='1'  numberSuffix='kg' formatNumberScale='0'>");
		if (null != lqv) {
			strXML.append("<set label='");
			strXML.append(fieldview.getSjg1());
			strXML.append("' value='");
			strXML.append(lqv.getSjg1());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjg2());
			strXML.append("' value='");
			strXML.append(lqv.getSjg2());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjg3());
			strXML.append("' value='");
			strXML.append(lqv.getSjg3());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjg4());
			strXML.append("' value='");
			strXML.append(lqv.getSjg4());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjg5());
			strXML.append("' value='");
			strXML.append(lqv.getSjg5());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjg6());
			strXML.append("' value='");
			strXML.append(lqv.getSjg6());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjf1());
			strXML.append("' value='");
			strXML.append(lqv.getSjf1());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjf2());
			strXML.append("' value='");
			strXML.append(lqv.getSjf2());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSjlq());
			strXML.append("' value='");
			strXML.append(lqv.getSjlq());
			strXML.append("' />");
		}	
        strXML.append("</chart>"); 
		return strXML.toString();		
	}	
	
	public String mainlistgetPieXml(LiqingView lqv) {
		String strXML = createMainPieXml(lqv); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Pie3D.swf", "",strXML, "lqmainpiechart", 500, 300, false, false);
	}
	
	private String createMainPieXml(LiqingView lqv) {
		StringBuilder strXML = new StringBuilder(""); 
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
		strXML.append("rotateYAxisName='0' caption='生产统计'");   
		strXML.append("showValues='1'  numberSuffix='盘' formatNumberScale='0'>");
		if (null != lqv) {
			Integer hegeps = 0; 
			try {	
			   hegeps = Integer.valueOf(lqv.getPangshu())-Integer.valueOf(lqv.getChaobiaops());
			} catch (Exception e) {
			}
			strXML.append("<set label='合格' value='");			
			strXML.append(hegeps);			
			
			strXML.append("' />");
			strXML.append("<set label='预警' value='");
			strXML.append(lqv.getChaobiaops());
			strXML.append("' />");
		}	
        strXML.append("</chart>"); 
		return strXML.toString();		
	}	
	
	public String getHntLilunAnalysisPieXml(HunnintuView hv, HntbhzziduancfgView fieldview) {
		StringBuilder strXML = new StringBuilder(""); 
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
		strXML.append("rotateYAxisName='0' caption='理论材料用量'");   
		strXML.append("showValues='1'  numberSuffix='kg' formatNumberScale='0'>");
		if (null != hv) {
			strXML.append("<set label='");
			strXML.append(fieldview.getSha1_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getSha1_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getSha2_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getSha2_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getShi1_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getShi1_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getShi2_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getShi2_lilunzhi());
			strXML.append("' />");

			strXML.append("<set label='");
			strXML.append(fieldview.getShuini1_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getShuini1_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getShuini2_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getShuini2_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getKuangfen3_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getKuangfen3_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getFeimeihui4_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getFeimeihui4_lilunzhi());
			strXML.append("' />");
			strXML.append("<set label='");
			strXML.append(fieldview.getFenliao5_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getFenliao5_lilunzhi());
			strXML.append("' />");
			
			strXML.append("<set label='");
			strXML.append(fieldview.getWaijiaji1_lilunzhi());
			strXML.append("' value='");
			strXML.append(hv.getWaijiaji1_lilunzhi());
			strXML.append("' />");
		}	
        strXML.append("</chart>"); 
		return strXML.toString();		
	}	
	
	public String getLqLilunAnalysisPieXml(LiqingView lqv) {
		StringBuilder strXML = new StringBuilder(""); 
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
		strXML.append("rotateYAxisName='0' caption='理论材料用量'");   
		strXML.append("showValues='1'  numberSuffix='%' formatNumberScale='0'>");
		if (null != lqv) {
			if (StringUtil.StrToFloat(lqv.getLlg1())>0) {
				strXML.append("<set label='");
				strXML.append("石料1");
				strXML.append("' value='");
				strXML.append(lqv.getLlg1());
				strXML.append("' />");	
			}
			if (StringUtil.StrToFloat(lqv.getLlg2())>0) {
				strXML.append("<set label='");
			strXML.append("石料2");
			strXML.append("' value='");
			strXML.append(lqv.getLlg2());
			strXML.append("' />");
			}
			if (StringUtil.StrToFloat(lqv.getLlg3())>0) {
				strXML.append("<set label='");
			strXML.append("石料3");
			strXML.append("' value='");
			strXML.append(lqv.getLlg3());
			strXML.append("' />");
			}
			if (StringUtil.StrToFloat(lqv.getLlg4())>0) {
				strXML.append("<set label='");
			strXML.append("石料4");
			strXML.append("' value='");
			strXML.append(lqv.getLlg4());
			strXML.append("' />");
			}
			if (StringUtil.StrToFloat(lqv.getLlg5())>0) {
				strXML.append("<set label='");
			strXML.append("石料5");
			strXML.append("' value='");
			strXML.append(lqv.getLlg5());
			strXML.append("' />");
			}
			if (StringUtil.StrToFloat(lqv.getLlf1())>0) {
				strXML.append("<set label='");
			strXML.append("粉料1");
			strXML.append("' value='");
			strXML.append(lqv.getLlf1());
			strXML.append("' />");
			}
			if (StringUtil.StrToFloat(lqv.getLlf2())>0) {
				strXML.append("<set label='");
			strXML.append("粉料2");
			strXML.append("' value='");
			strXML.append(lqv.getLlf2());
			strXML.append("' />");
			}
			if (StringUtil.StrToFloat(lqv.getLllq())>0) {
				strXML.append("<set label='");
			strXML.append("沥青");
			strXML.append("' value='");
			strXML.append(lqv.getLllq());
			strXML.append("' />");
			}
			
		}	
        strXML.append("</chart>"); 
		return strXML.toString();		
	}

	
	//沥青混凝土产能分析
	public String getCapacityAnalysisColumnXml() {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' maxColWidth='70' rotateYAxisName='0' caption='拌和站产能分析' "  
                 + "startingAngle='125' shownames='1' showvalues='1' numberSuffix='吨' formatNumberScale='0'>"); 
        strXML.append("<set label='2009年11月' value='1880'link='JavaScript:myJS(911)'/>");
        strXML.append("<set label='2009年12月' value='1690'link='JavaScript:myJS(912)'/>");
        strXML.append("<set label='2010年1月' value='980'link='JavaScript:myJS(1001)'/>");
        strXML.append("<set label='2010年2月' value='460'link='JavaScript:myJS(1002)'/>");
        strXML.append("<set label='2010年3月' value='1220'link='JavaScript:myJS(1003)'/>");
        strXML.append("</chart>"); 
        return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Column3D.swf", "",strXML.toString(), "NewChart", 850, 300, false, false);
	}
	
	//沥青混凝土各材料用量饼图
	public String getCapacityAnalysisPieXml(String shebeiid) {
		StringBuilder strXML = new StringBuilder(""); 
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' rotateYAxisName='0' caption='详细材料用量' xAxisName='月份' refreshInterval='60'"   
                +" yAxisName='用量' showValues='1'  numberSuffix='吨' formatNumberScale='0'>"); 
		if (shebeiid.equalsIgnoreCase("911")) {
			strXML.append("<set label='水泥' value='94' />");
			strXML.append("<set label='集料1' value='284' />");
			strXML.append("<set label='集料2' value='376' />");
			strXML.append("<set label='集料3' value='476' />");
			strXML.append("<set label='集料4' value='370' />");
			strXML.append("<set label='矿粉' value='280' />");
		} else if (shebeiid.equalsIgnoreCase("912")) {
			strXML.append("<set label='水泥' value='84' />");
			strXML.append("<set label='集料1' value='256' />");
			strXML.append("<set label='集料2' value='338' />");
			strXML.append("<set label='集料3' value='420' />");
			strXML.append("<set label='集料4' value='340' />");
			strXML.append("<set label='矿粉' value='250' />");
		} else if (shebeiid.equalsIgnoreCase("1001")) {
			strXML.append("<set label='水泥' value='49' />");
			strXML.append("<set label='集料1' value='196' />");
			strXML.append("<set label='集料2' value='345' />");
			strXML.append("<set label='集料3' value='196' />");
			strXML.append("<set label='矿粉' value='145' />");
		} else if (shebeiid.equalsIgnoreCase("1002")) {
			strXML.append("<set label='水泥' value='20' />");
			strXML.append("<set label='集料1' value='95' />");
			strXML.append("<set label='集料2' value='161' />");
			strXML.append("<set label='集料3' value='92' />");
			strXML.append("<set label='矿粉' value='69' />");
		} else if (shebeiid.equalsIgnoreCase("1003")) {
			strXML.append("<set label='水泥' value='60' />");
			strXML.append("<set label='集料1' value='245' />");
			strXML.append("<set label='集料2' value='427' />");
			strXML.append("<set label='集料3' value='244' />");
			strXML.append("<set label='矿粉' value='183' />");
		}
        strXML.append("</chart>"); 
		return strXML.toString();		
	}	
	
	//试验检测监控主界面
	public String getTestMonitorLineXml() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", StringUtil.getWebrootpath()+"/realdata/MultipleDS.xml","", "myChartId", 1000, 400, false, true);
	}
	
	//实时压力机压力刷新
	public String updateTestMonitorLineRealValue() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());	
		return "&label=" + timeLabel + "&value=" +getRandom(100, 1000)+"|"+getRandom(600, 2000);		
	}	

	//混凝土材料用量柱状图指示
	public String gethntcailiaoyongliangXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeColumn.swf", "",hntbanhecailiaoXml(sessionid, shebeibianhao, proname, bsid), "hntbanhecailiaochart", 1000, 500, false, false);
	}
	
	//沥青材料用量柱状图指示
	public String getlqcailiaoyongliangXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeColumn.swf", "",lqcailiaoXml(sessionid, shebeibianhao, proname, bsid), "lqcailiaochart", 675, 350, false, false);
	}
	
	public String hntbanhecailiaoXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TophunnintuView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TophunnintuView tview = findbyshebeibianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, hntcl);
			HntbhzziduancfgView hntcfgisshow = hntcfgDAO.findByGprsbhandleixin(shebeibianhao, "2");
			HntbhzziduancfgView hntcfg = hntcfgDAO.findByGprsbhandleixin(shebeibianhao, "1");
			if (null == hntcfgisshow) {
				hntcfgisshow = hntcfgDAO.findByGprsbhandleixin("all", "101");
			}
			if (null == hntcfg) {
				hntcfg = hntcfgDAO.findByGprsbhandleixin("all", "100");
			}
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("材料用量实时监控' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/hunnintucailiaoreal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' borderColor='899FB6' canvasborderColor='DAE1E8'"); 
	        strXML.append(" canvasBgColor='FFFFFF' bgColor='EEF2FB' numDivLines='6' divLineColor='DAE1E8'"); 
	        strXML.append(" divLineAlpha='75' alternateHGridAlpha='30' baseFontColor='899FB6'"); 
	        strXML.append(" outCnvBaseFontColor='444C60' toolTipBorderColor='DAE1E8' numberSuffix='kg'"); 
	        strXML.append(" toolTipBgColor='FFFFFF' toolTipSepChar=' ' showAlternateHGridColor='1'"); 
	        strXML.append(" alternateHGridColor='DAE1E8' refreshInterval='10' numDisplaySets='3' showLegend='1' showLabels='1'"); 
	        strXML.append(" showRealTimeValue='1' labelDisplay='none' showShadow='1' showPlotBorder='0'"); 
	        strXML.append(" plotBordercolor='FFFFFF' plotGradientColor='' formatNumberScale='0' animation='1' logoScale='24'"); 
	        strXML.append(" canvasLeftMargin='50'>"); 
	        strXML.append(" <categories></categories>"); 
	        if (null != hntcfgisshow && null != hntcfg) {
	        	if (hntcfgisshow.getShuini1_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getShuini1_shijizhi(), strXML); 
					appendDataSet(hntcfg.getShuini1_lilunzhi(), strXML); 
				}	   
				
				if (hntcfgisshow.getShui2_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getShui2_shijizhi(), strXML); 
					appendDataSet(hntcfg.getShui2_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getSha1_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getSha1_shijizhi(), strXML);
					appendDataSet(hntcfg.getSha1_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getSha2_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getSha2_shijizhi(), strXML); 
					appendDataSet(hntcfg.getSha2_lilunzhi(), strXML);
				}
				
				if (hntcfgisshow.getShi1_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getShi1_shijizhi(), strXML); 
					appendDataSet(hntcfg.getShi1_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getShi2_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getShi2_shijizhi(), strXML);
					appendDataSet(hntcfg.getShi2_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getGuliao5_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getGuliao5_shijizhi(), strXML); 
					appendDataSet(hntcfg.getGuliao5_lilunzhi(), strXML);
				}
				
				if (hntcfgisshow.getShuini2_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getShuini2_shijizhi(), strXML); 
					appendDataSet(hntcfg.getShuini2_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getKuangfen3_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getKuangfen3_shijizhi(), strXML); 
					appendDataSet(hntcfg.getKuangfen3_lilunzhi(), strXML);
				}
				
				if (hntcfgisshow.getFeimeihui4_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getFeimeihui4_shijizhi(), strXML);
					appendDataSet(hntcfg.getFeimeihui4_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getFenliao5_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getFenliao5_shijizhi(), strXML); 
					appendDataSet(hntcfg.getFenliao5_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getFenliao6_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getFenliao6_shijizhi(), strXML); 
					appendDataSet(hntcfg.getFenliao6_lilunzhi(), strXML);
				}
				
				if (hntcfgisshow.getWaijiaji1_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getWaijiaji1_shijizhi(), strXML); 
					appendDataSet(hntcfg.getWaijiaji1_lilunzhi(), strXML);
				}
				
				if (hntcfgisshow.getWaijiaji2_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getWaijiaji2_shijizhi(), strXML); 
					appendDataSet(hntcfg.getWaijiaji2_lilunzhi(), strXML); 
				}
				
				if (hntcfgisshow.getWaijiaji3_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getWaijiaji3_shijizhi(), strXML);
					appendDataSet(hntcfg.getWaijiaji3_lilunzhi(), strXML);
				}
				
				if (hntcfgisshow.getWaijiaji4_shijizhi().equalsIgnoreCase("1")) {
					appendDataSet(hntcfg.getWaijiaji4_shijizhi(), strXML);
					appendDataSet(hntcfg.getWaijiaji4_lilunzhi(), strXML);
				}
			} 
	        strXML.append(" <styles>"); 
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12' color='899FB6'/>"); 
	        strXML.append(" <style type='font' name='subcaptionFont' color='899FB6'/>"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='SubCaption' styles='subcaptionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	public String lqcailiaoXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopLiqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, lqcl);
			LiqingziduancfgView lqcfgisshow = lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "2");
			LiqingziduancfgView lqcfg = lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "1");
			if (null == lqcfgisshow) {
				lqcfgisshow = lqcfgDAO.findByGprsbhandleixin("all", "101");
			}
			if (null == lqcfg) {
				lqcfg = lqcfgDAO.findByGprsbhandleixin("all", "100");
			}
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("数据实时监控(单位:kg)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/lqcailiaoreal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' borderColor='899FB6' canvasborderColor='DAE1E8'"); 
	        strXML.append(" canvasBgColor='FFFFFF' bgColor='EEF2FB' numDivLines='6' divLineColor='DAE1E8'"); 
	        strXML.append(" divLineAlpha='75' alternateHGridAlpha='30' baseFontColor='899FB6'"); 
	        strXML.append(" outCnvBaseFontColor='444C60' toolTipBorderColor='DAE1E8' numberSuffix=''"); 
	        strXML.append(" toolTipBgColor='FFFFFF' toolTipSepChar=' ' showAlternateHGridColor='1'"); 
	        strXML.append(" alternateHGridColor='DAE1E8' refreshInterval='10' numDisplaySets='3' showLegend='1' showLabels='1'"); 
	        strXML.append(" showRealTimeValue='0' labelDisplay='none' showShadow='1' showPlotBorder='0'"); 
	        strXML.append(" plotBordercolor='FFFFFF' plotGradientColor='' formatNumberScale='0'"); 
	        strXML.append(" canvasLeftMargin='50' yAxisMaxValue='1000' showToolTip='1'>"); 
	        strXML.append(" <categories></categories>"); 
	        if (null != lqcfgisshow && null != lqcfg) {
	        	if (lqcfgisshow.getSjlq().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjlq(), strXML); 
				}
	        	if (lqcfgisshow.getLllq().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	
	        	if (lqcfgisshow.getSjg1().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg1(), strXML); 
				}
	        	if (lqcfgisshow.getLlg1().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjg2().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg2(), strXML); 
				}
	        	if (lqcfgisshow.getLlg2().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjg3().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg3(), strXML); 
				}
	        	if (lqcfgisshow.getLlg3().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjg4().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg4(), strXML); 
				}
	        	if (lqcfgisshow.getLlg4().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjg5().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg5(), strXML); 
				}
	        	if (lqcfgisshow.getLlg5().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjg6().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg6(), strXML); 
				}
	        	if (lqcfgisshow.getLlg6().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjg7().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjg7(), strXML); 
				}
	        	if (lqcfgisshow.getLlg7().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjf1().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjf1(), strXML); 
				}
	        	if (lqcfgisshow.getLlf1().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjf2().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjf2(), strXML); 
				}
	        	if (lqcfgisshow.getLlf2().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if (lqcfgisshow.getSjtjj().equalsIgnoreCase("1")) {
	        		appendLqsjDataSet(lqcfg.getSjtjj(), strXML); 
				}
	        	if (lqcfgisshow.getLltjj().equalsIgnoreCase("1")) {
	        		appendLqllDataSet("", strXML); 
				}
	        	
			}         
	        strXML.append(" <styles>"); 
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12' color='899FB6'/>"); 
	        strXML.append(" <style type='font' name='subcaptionFont' color='899FB6'/>"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='SubCaption' styles='subcaptionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	private void appendDataSet(String seriesName, StringBuilder strXML) {
		strXML.append(" <dataSet seriesName='"); 
		strXML.append(seriesName);
		strXML.append("' showValues='0'></dataSet>");
	}
	
	private void appendLqsjDataSet(String seriesName, StringBuilder strXML) {
		strXML.append(" <dataSet seriesName='"); 
		strXML.append(seriesName);
		strXML.append("' showValues='0'></dataSet>");
	}
	
	private void appendLqllDataSet(String seriesName, StringBuilder strXML) {
		strXML.append(" <dataSet seriesName='"); 
		strXML.append(seriesName);
		strXML.append("' showValues='0' color='0099FF'></dataSet>");
	}
	
	private void appendBlankDateSet(StringBuilder strXML){
		strXML.append("<dataSet seriesName= '' showValues='0'></dataSet>");
	}
	
	private void appendDataSetY(String seriesName, StringBuilder strXML) {
		strXML.append(" <dataSet seriesName='"); 
		strXML.append(seriesName);
		strXML.append("' showValues='0' parentYAxis='S'></dataSet>");
	}
	
	//沥青混凝土监控温度表盘指示1
	public String getAsphaltAngularXml1() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", StringUtil.getWebrootpath()+"/realdata/Angular10.xml","", "AsphaltAngular1Id", 250, 250, false, false);
	}
	
	//沥青混凝土监控温度表盘指示2
	public String getAsphaltAngularXml2() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", StringUtil.getWebrootpath()+"/realdata/Angular15.xml","", "AsphaltAngular2Id", 250, 250, false, false);
	}
	
	//沥青混凝土监控温度表盘指示3
	public String getAsphaltAngularXml3() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", StringUtil.getWebrootpath()+"/realdata/Angular16.xml","", "AsphaltAngular3Id", 250, 250, false, false);
	}
	
	//沥青混凝土监控温度表盘指示4
	public String getAsphaltAngularXml4() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", StringUtil.getWebrootpath()+"/realdata/Angular17.xml","", "AsphaltAngular4Id", 250, 250, false, false);
	}
	
	//沥青混凝土实时材料用量刷新
	public String updateAsphaltDosageColumnRealValue() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());
		return "&label=" + timeLabel + "&value=" +getRandom(0, 200)+"|"+getRandom(0, 500)+"|"+getRandom(0, 500)+"|"+getRandom(0, 500)+"|"+getRandom(0, 500);	
	}
	
	//混凝土实时材料用量刷新
	public String updatehunnintucailiaorealvalue(String sessionid, String shebeibianhao) {
		TophunnintuView topview = findbyshebeibianhao(shebeibianhao);
		StringBuilder result = new StringBuilder(""); 
		if (null != topview) {  
			String bcsj = topview.getChuliaoshijian();
			if (StringUtil.Null2Blank(bcsj).length()==0) {
				bcsj = topview.getBaocunshijian();
			}
            if (updateSessionUpdateTime(sessionid, shebeibianhao, bcsj, hntcl)) {
    			HntbhzziduancfgView hntcfgisshow = hntcfgDAO.findByGprsbhandleixin(shebeibianhao, "2");
    			if (null == hntcfgisshow) {
    				hntcfgisshow = hntcfgDAO.findByGprsbhandleixin("all", "101");
    			}
    			result.append("&label=");
    			result.append(bcsj);
    			result.append("&value=");
    			if (null != hntcfgisshow) {
    			  if (hntcfgisshow.getShuini1_shijizhi().equalsIgnoreCase("1")) {
      				  result.append(topview.getShuini1_shijizhi());
      				  result.append("|");
      				result.append(topview.getShuini1_lilunzhi());
    				  result.append("|");
      			  }
    			  
    			  if (hntcfgisshow.getShui1_shijizhi().equalsIgnoreCase("1")) {
          			  result.append(topview.getShui1_shijizhi());
        			  result.append("|");
        			  result.append(topview.getShui1_lilunzhi());
        			  result.append("|");
				  }
    			  
    			  if (hntcfgisshow.getShui2_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getShui2_shijizhi());
    				  result.append("|");
    				  result.append(topview.getShui2_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getSha1_shijizhi().equalsIgnoreCase("1")) {    			  
    				  result.append(topview.getSha1_shijizhi());
    				  result.append("|");
    				  result.append(topview.getSha1_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getSha2_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getSha2_shijizhi());
    				  result.append("|");
    				  result.append(topview.getSha2_lilunzhi());
    				  result.append("|");
    			  }
    			 
    			  if (hntcfgisshow.getShi1_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getShi1_shijizhi());
    				  result.append("|");
    				  result.append(topview.getShi1_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getShi2_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getShi2_shijizhi());
    				  result.append("|");
    				  result.append(topview.getShi2_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getGuliao5_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getGuliao5_shijizhi());
    				  result.append("|");
    				  result.append(topview.getGuliao5_lilunzhi());
    				  result.append("|");
    			  }    	
    			  
    			  if (hntcfgisshow.getShuini2_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getShuini2_shijizhi());
    				  result.append("|");
    				  result.append(topview.getShuini2_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getKuangfen3_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getKuangfen3_shijizhi());
    				  result.append("|");
    				  result.append(topview.getKuangfen3_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getFeimeihui4_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getFeimeihui4_shijizhi());
    				  result.append("|");
    				  result.append(topview.getFeimeihui4_lilunzhi());
    				  result.append("|");
    			  }	
    			  
    			  if (hntcfgisshow.getFenliao5_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getFenliao5_shijizhi());
    				  result.append("|");
    				  result.append(topview.getFenliao5_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getFenliao6_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getFenliao6_shijizhi());
    				  result.append("|");
    				  result.append(topview.getFenliao6_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getWaijiaji1_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getWaijiaji1_shijizhi());
    				  result.append("|");
    				  result.append(topview.getWaijiaji1_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getWaijiaji2_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getWaijiaji2_shijizhi());
    				  result.append("|");
    				  result.append(topview.getWaijiaji2_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getWaijiaji3_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getWaijiaji3_shijizhi());
    				  result.append("|");
    				  result.append(topview.getWaijiaji3_lilunzhi());
    				  result.append("|");
    			  }
    			  
    			  if (hntcfgisshow.getWaijiaji4_shijizhi().equalsIgnoreCase("1")) {
    				  result.append(topview.getWaijiaji4_shijizhi());
    				  result.append("|");
    				  result.append(topview.getWaijiaji4_lilunzhi());
    			  }
    			}              
    			if (logger.isDebugEnabled()) {
    				logger.debug(result);
    			}
			}            
		}		
		return result.toString();			
	}
	
	//沥青实时材料用量刷新
	public String updatelqcailiaorealvalue(String sessionid, String shebeibianhao) {
		TopLiqingView topview = toplqDAO.findByGprsbianhao(shebeibianhao);
		StringBuilder result = new StringBuilder(""); 
		if (null != topview) {  
			String bcsj =GetDate.formatshijian2(topview.getShijian());
			if (StringUtil.Null2Blank(bcsj).length()==0) {
				bcsj =GetDate.formatshijian2(topview.getBaocunshijian());
			}
            if (updateSessionUpdateTime(sessionid, shebeibianhao, bcsj, lqcl)) {
            	LiqingziduancfgView lqcfgisshow = lqcfgDAO.findByGprsbhandleixin(shebeibianhao, "2");
    			if (null == lqcfgisshow) {
    				lqcfgisshow = lqcfgDAO.findByGprsbhandleixin("all", "101");
    			}
    			result.append("&label=");
    			result.append(bcsj);
    			result.append("&value=");
    			if (null != lqcfgisshow) {
    				try{
	    			  if (lqcfgisshow.getSjlq().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjlq())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLllq().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLllq())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }	    			
	    			  
	    			  result.append("");
	  				  result.append("|"); 
	    			  
	    			  if (lqcfgisshow.getSjg1().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg1())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg1().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg1())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }	    			
	    			  
	    			  result.append("");
	  				  result.append("|");
	    			  
	    			  if (lqcfgisshow.getSjg2().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg2())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg2().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg2())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  
	    			  result.append("");
	  				  result.append("|");
	    			  
	    			  if (lqcfgisshow.getSjg3().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg3())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg3().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg3())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	    			  
	    			  if (lqcfgisshow.getSjg4().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg4())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg4().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg4())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	  				  
	    			  if (lqcfgisshow.getSjg5().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg5())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg5().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg5())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	  				  
	    			  if (lqcfgisshow.getSjg6().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg6())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg6().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg6())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	  				  
	    			  if (lqcfgisshow.getSjg7().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjg7())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlg7().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlg7())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	  				  
	    			  if (lqcfgisshow.getSjf1().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjf1())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlf1().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlf1())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	    			  
	    			  if (lqcfgisshow.getSjf2().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjf2())));
	      				  result.append("|");      				
	      			  }
	    			  if (lqcfgisshow.getLlf2().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLlf2())/100*Float.parseFloat(topview.getChangliang())));
	      				  result.append("|");      				
	      			  }
	    			  result.append("");
	  				  result.append("|");
	  				  
	    			  if (lqcfgisshow.getSjtjj().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getSjtjj())));
	      				  result.append("|"); 
	      			  }
	    			  if (lqcfgisshow.getLltjj().equalsIgnoreCase("1")) {
	      				  result.append(String.format("%1$.1f",Float.parseFloat(topview.getLltjj())/100*Float.parseFloat(topview.getChangliang())));
	      			  }	   
	    			  	    			 	  	     
    				}catch(Exception ex){}
    			}              
    			if (logger.isDebugEnabled()) {
    				logger.debug(result);
    			}
			}            
		}		
		return result.toString();			
	}
    
	//水泥混凝土拌和站一览
	public String gethntbhzXml(int xmltype, String fieldname, int bsid) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='拌和站一览表(红色代表正在生产，蓝色代表未生产)' bgColor='FFFFFF'");
        strXML.append(" canvasBorderColor='333333' numdivlines='0' decimals='1' xAxisMinValue='0'"); 
        strXML.append(" xAxisMaxValue='32' bubbleScale='2' showLimits='0' yAxisMaxValue='20'"); 
        strXML.append(" chartRightMargin='0' showPlotBorder='0' plotFillAlpha='80' showBorder='0'>"); 
        strXML.append(" <categories verticalLineColor='666666' verticalLineAlpha='20'>"); 
        strXML.append(" <category name=' ' x='2' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='4' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='6' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='8' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='10' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='12' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='14' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='16' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='18' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='20' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='22' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='24' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='26' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='28' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='30' showVerticalLine='1'/>"); 
        strXML.append(" </categories>"); 
        strXML.append(" <dataset showValues='0'>"); 
		
        ArrayList<TophunnintuView> topviewlist = (ArrayList<TophunnintuView>) findByProperty(fieldname, bsid);
        int i = 1;
        int y = 10;
        if (topviewlist.size() > 16) {
			y = 15;
		}
        for (TophunnintuView tophunnintuView : topviewlist) {
        	if (i > 31) {
				i = 1;
				y = 5;
			}
        	strXML.append("<set z='1' x='");
        	strXML.append(i);
        	strXML.append("' y='");
        	strXML.append(y);
        	strXML.append("' name='r");
        	strXML.append(i);
        	strXML.append("' showValue='1' displayValue='");
        	strXML.append(tophunnintuView.getJianchen());
        	String bcsj = tophunnintuView.getChuliaoshijian();
        	if (StringUtil.Null2Blank(bcsj).length()>0) {
        		strXML.append("' toolText='最新出料时间：");
			} else {
				strXML.append("' toolText='最新保存时间：");
				bcsj = tophunnintuView.getBaocunshijian();
			}
        	
        	strXML.append(bcsj);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Calendar day=Calendar.getInstance(); 
        	Date baocun;     	
			try {				
				baocun = sdf.parse(tophunnintuView.getBaocunshijian());
	        	day.add(Calendar.DATE, -1);
	        	if (baocun.after(day.getTime())) {
	        		strXML.append("' color='ff5904");
	        	}
	        	/*day.add(Calendar.DATE, -2);
	        	if (baocun.before(day.getTime())) {
	        		strXML.append("' color='4371AB");
	        	}*/
			} catch (ParseException e) {
			}
        	
            switch (xmltype) {
			case 1:
				strXML.append("' link='");
				strXML.append(StringUtil.getWebrootpath());
				strXML.append("/getdata/hntcailiaoyongliangmonitor?pid=1&shebeibianhao=");
				strXML.append(tophunnintuView.getShebeibianhao());
				strXML.append("'/>");
				break;
			case 2:
				strXML.append("' link='JavaScript:myJS(&quot;");
	        	strXML.append(tophunnintuView.getShebeibianhao());
	        	strXML.append("&quot;)'/>");
				break;
			default:
				break;
			}    	
        	i++;
        	i++;
		}

        strXML.append("</dataset>");   
        strXML.append("<styles>");
        strXML.append("<definition>");
        strXML.append("<style name='myCaptionFont' type='font' size='12'/>");
        strXML.append("</definition>");
        strXML.append("<application>");
        strXML.append("<apply toObject='Caption' styles='myCaptionFont' />");
        strXML.append("</application>");
        strXML.append("</styles>");
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "hntbhzchart", 1000, 150, false, false);
	}
	
	//沥青拌和站一览
	public String getliqingXml(int xmltype,Integer biaoduan, String fieldname, int bsid) {
		String strXML = createliqingxml(xmltype, biaoduan,fieldname, bsid); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "lqbhzchart", 1000, 150, false, false);
	}
	
	public String mainlistgetliqingXml(int xmltype,Integer biaoduan,String fieldname, int bsid) {
		String strXML = createliqingxml(xmltype,biaoduan ,fieldname, bsid); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "lqbhzchart", 480, 150, false, false);
	}
	
	private String createliqingxml(int xmltype,Integer biaoduan,String fieldname,int bsid) {
		StringBuilder strXML = new StringBuilder("");  
		List<TopRealMaxLiqingView> topLqviewlist=null;
		if (fieldname.equalsIgnoreCase("all")) {
			if (null != biaoduan) {
				topLqviewlist = topreallqviewDAO.find("from TopRealMaxLiqingView where biaoduanid=? order by bianhao desc", biaoduan);
			} else {
				topLqviewlist = topreallqviewDAO.find("from TopRealMaxLiqingView where biaoduanid=1 order by bianhao desc");
			}
			
		} else {
			if (null != biaoduan) {
				topLqviewlist = topreallqviewDAO.find("from TopRealMaxLiqingView where biaoduanid=? and "+fieldname+"=? order by bianhao desc", biaoduan,bsid);
			} else {
				topLqviewlist = topreallqviewDAO.find("from TopRealMaxLiqingView where "+fieldname+"=? order by bianhao desc", bsid);
			}
		}
		if(topLqviewlist!=null && topLqviewlist.size()>0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='拌和站一览表(红色代表正在生产，蓝色代表未生产(30分钟内))' bgColor='FFFFFF'");
	        strXML.append(" canvasBorderColor='333333' numdivlines='0' decimals='1' xAxisMinValue='0'"); 
	        strXML.append(" xAxisMaxValue='20' bubbleScale='2' showLimits='0' yAxisMaxValue='20'"); 
	        strXML.append(" chartRightMargin='0' showPlotBorder='0' plotFillAlpha='80' showBorder='0'>"); 
	        strXML.append(" <categories verticalLineColor='666666' verticalLineAlpha='20'>"); 
	        strXML.append(" <category name=' ' x='2' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='4' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='6' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='8' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='10' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='12' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='14' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='16' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='18' showVerticalLine='1'/>"); 
	        strXML.append(" </categories>"); 
	        strXML.append(" <dataset showValues='0'>"); 
	        int i = 1;
	        int y = 10;
	        if (topLqviewlist.size() > 10) {
				y = 15;
			}
	        for (TopRealMaxLiqingView toplqView : topLqviewlist) {
	        	if (i > 19) {
					i = 1;
					y = 5;
				}
	        	
	        	strXML.append("<set z='1' x='");
	        	strXML.append(i);
	        	strXML.append("' y='");
	        	strXML.append(y);
	        	strXML.append("' name='r");
	        	strXML.append(i);
	        	strXML.append("' showValue='1' displayValue='");
	        	strXML.append(toplqView.getJianchen());
	        	String bcsj = toplqView.getShijian();
	        	if (StringUtil.Null2Blank(bcsj).length()>0) {
	        		strXML.append("' toolText='最新出料时间：");
				} else {
					strXML.append("' toolText='最新保存时间：");
					bcsj = toplqView.getBaocunshijian();
				}
	        	
	        	strXML.append(bcsj);
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	Calendar day=Calendar.getInstance(); 
	        	Date baocun;     	
				try {				
					baocun = sdf.parse(toplqView.getBaocunshijian());
		        	day.add(Calendar.MINUTE, -30);
		        	if (baocun.after(day.getTime())) {
		        		strXML.append("' color='ff5904");
		        	}
				} catch (ParseException e) {}
	        	
	            switch (xmltype) {
					case 1:
						strXML.append("' link='");
						strXML.append(StringUtil.getWebrootpath());
						strXML.append("/query/Lqquerylist?pid=5&record=1&method=swlist&biaoduan="+biaoduan+"&shebeibianhao=");
						//strXML.append("/getdata/lqcailiaoyongliangmonitor?pid=5&record=1&shebeibianhao=");
						strXML.append(toplqView.getShebeibianhao());
						strXML.append("'/>");
						break;
					case 2:
						strXML.append("' link='JavaScript:myJS(&quot;");
			        	strXML.append(toplqView.getShebeibianhao());
			        	strXML.append("&quot;)'/>");
						break;
					default:
						break;
				}
		        i++;
		        i++;
			}

	        strXML.append("</dataset>");   
	        strXML.append("<styles>");
	        strXML.append("<definition>");
	        strXML.append("<style name='myCaptionFont' type='font' size='12'/>");
	        strXML.append("</definition>");
	        strXML.append("<application>");
	        strXML.append("<apply toObject='Caption' styles='myCaptionFont' />");
	        strXML.append("</application>");
	        strXML.append("</styles>");
	        strXML.append("</chart>");
		}
        
		return strXML.toString();
	}
	
	//材料成本核算
	public String gethntmaterialhsXml(HunnintuView hv, HntbhzziduancfgView fieldview) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='2' caption='材料成本核算' "); 
        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1'"); 
        strXML.append(" PYAxisName='实际用量' SYAxisName='误差' numberSuffix='kg'"); 
        strXML.append(" SNumberSuffix='kg' PNumberSuffix='kg' formatNumberScale='0'>"); 
        strXML.append("<categories>"); 
        strXML.append("<category label='"); 
        strXML.append(fieldview.getSha1_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<category label='"); 
        strXML.append(fieldview.getShi1_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getSha2_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getShi2_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getGuliao5_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getShuini1_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getShuini2_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getKuangfen3_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getFeimeihui4_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getFenliao5_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getShui1_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getShui2_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getWaijiaji1_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<category label='"); 
        strXML.append(fieldview.getWaijiaji2_shijizhi()); 
        strXML.append("'/>");
        strXML.append("</categories>"); 
        
        strXML.append("<dataset seriesName='实际用量'>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getSha1_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShi1_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShi2_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getSha2_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getGuliao5_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getShuini1_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShuini2_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getKuangfen3_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getFeimeihui4_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getFenliao5_shijizhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getShui1_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShui2_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getWaijiaji1_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getWaijiaji2_shijizhi()); 
        strXML.append("'/>"); 
        strXML.append("</dataset>");
        
        strXML.append("<dataset seriesName='理论用量' renderAs='Area' parentYAxis='P' showValues='0'>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getSha1_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShi1_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShi2_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getSha2_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getGuliao5_lilunzhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getShuini1_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShuini2_lilunzhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getKuangfen3_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getFeimeihui4_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getFenliao5_lilunzhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getShui1_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShui2_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getWaijiaji1_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getWaijiaji2_lilunzhi()); 
        strXML.append("'/>"); 
        strXML.append("</dataset>"); 
        
        strXML.append("<dataset lineThickness='3' seriesName='误差' renderAs='Line' parentYAxis='S' showValues='1'>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getSha1chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShi1chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShi2chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getSha2chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getGuliao5chazhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getShuini1chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShuini2chazhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getKuangfen3chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getFeimeihui4chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getFenliao5chazhi()); 
        strXML.append("'/>");
        strXML.append("<set value='"); 
        strXML.append(hv.getShui1chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getShui2chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getWaijiaji1chazhi()); 
        strXML.append("'/>"); 
        strXML.append("<set value='"); 
        strXML.append(hv.getWaijiaji2chazhi()); 
        strXML.append("'/>"); 
        strXML.append("</dataset>");        
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "hntmaterialhschart", 1000, 400, false, false);
	}
	
	public String getlqmaterialhsXml(LiqingphbView hv, LiqingziduancfgView fieldview,LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='4' caption='材料成本核算(单位:t)' "); 
        strXML.append(" showValues='0' "); 
        strXML.append(" xAxisName='材料名称' yAxisName='实际用量'"); 
        strXML.append(" formatNumberScale='0'>"); 
        if(fieldview!=null && lqisShow!=null){       	
	        if(lqisShow.getSjg1().equalsIgnoreCase("1")){	
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg1()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg1()); 
		        strXML.append("' color='0099FF'/>");
	        }
	        
	        if(lqisShow.getSjg2().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjg2()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLlg2()); 
		        strXML.append("' color='0099FF'/>"); 
	        }	        
	        
	        if(lqisShow.getSjg3().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg3()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg3()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg3()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg3()); 
		        strXML.append("' color='0099FF'/>");
	        }	        
	        
	        if(lqisShow.getSjg4().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg4()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg4()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg4()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg4()); 
		        strXML.append("' color='0099FF'/>");
	        }
	        
	        if(lqisShow.getSjg5().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg5()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg5()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg5()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg5()); 
		        strXML.append("' color='0099FF'/>");
	        }
	        
	        if(lqisShow.getSjg6().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg6()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg6()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg6()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg6()); 
		        strXML.append("' color='0099FF'/>");
	        }
	        
	        if(lqisShow.getSjg7().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg7()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjg7()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg7()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLlg7()); 
		        strXML.append("' color='0099FF'/>");
	        }
        
	        if(lqisShow.getSjf1().equalsIgnoreCase("1")){   
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjf1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjf1()); 
		        strXML.append("' />"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlf1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlf1()); 
		        strXML.append("' color='0099FF'/>");
	        }	        
        
	        if(lqisShow.getSjf2().equalsIgnoreCase("1")){     
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjf2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjf2()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlf2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLlf2()); 
		        strXML.append("' color='0099FF'/>"); 
	        }
        
	        if(lqisShow.getSjlq().equalsIgnoreCase("1")){    
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjlq()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjlq()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLllq()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLllq()); 
		        strXML.append("' color='0099FF'/>");
	        }
        
	        if(lqisShow.getSjtjj().equalsIgnoreCase("1")){  
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjtjj()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjtjj()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLltjj()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLltjj()); 
		        strXML.append("' color='0099FF'/>");
	        }
        }       
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Column3D.swf", "",strXML.toString(), "lqmaterialhschart", 1000, 250, false, false);
	}
	
	public String getDapinglqmaterialhsXml(LiqingphbView hv, LiqingziduancfgView fieldview,LiqingziduancfgView lqisShow) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='4' caption='材料成本核算(t)' showBorder='1'  borderColor='899FB6' "); 
        strXML.append(" showValues='0' divLineDecimalPrecision='1' limitsDecimalPrecision='1' "); 
        strXML.append(" xAxisName='材料名称' yAxisName='实际用量'"); 
        strXML.append(" NumberSuffix='' formatNumberScale='0'>"); 
        if(fieldview!=null && lqisShow!=null){       	
	        if(lqisShow.getSjg1().equalsIgnoreCase("1")){	
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg1()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg1()); 
		        strXML.append("'/>");
	        }
	        
	        if(lqisShow.getSjg2().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjg2()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLlg2()); 
		        strXML.append("'/>"); 
	        }	        
	        
	        if(lqisShow.getSjg3().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg3()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg3()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg3()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg3()); 
		        strXML.append("'/>");
	        }	        
	        
	        if(lqisShow.getSjg4().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg4()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg4()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg4()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg4()); 
		        strXML.append("'/>");
	        }
	        
	        if(lqisShow.getSjg5().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg5()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg5()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg5()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg5()); 
		        strXML.append("'/>");
	        }
	        
	        if(lqisShow.getSjg6().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg6()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjg6()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg6()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlg6()); 
		        strXML.append("'/>");
	        }
	        
	        if(lqisShow.getSjg7().equalsIgnoreCase("1")){
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjg7()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjg7()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlg7()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLlg7()); 
		        strXML.append("'/>");
	        }
        
	        if(lqisShow.getSjf1().equalsIgnoreCase("1")){   
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjf1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjf1()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlf1()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLlf1()); 
		        strXML.append("'/>");
	        }	        
        
	        if(lqisShow.getSjf2().equalsIgnoreCase("1")){     
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjf2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjf2()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLlf2()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLlf2()); 
		        strXML.append("'/>"); 
	        }
        
	        if(lqisShow.getSjlq().equalsIgnoreCase("1")){    
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjlq()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getSjlq()); 
		        strXML.append("'/>");
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLllq()); 
		        strXML.append("' value='");  
		        strXML.append(hv.getLllq()); 
		        strXML.append("'/>");
	        }
        
	        if(lqisShow.getSjtjj().equalsIgnoreCase("1")){  
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getSjtjj()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getSjtjj()); 
		        strXML.append("'/>"); 
		        
		        strXML.append("<set label='"); 
		        strXML.append(fieldview.getLltjj()); 
		        strXML.append("' value='"); 
		        strXML.append(hv.getLltjj()); 
		        strXML.append("'/>");
	        }
        }       
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Column3D.swf", "",strXML.toString(), "lqmaterialhschartdaping", 675, 345, false, false);
	}
	
	//水泥混凝土监控主界面拌和时间
	public String gethntbanheshijianXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", "",hntbanheshijianXml(sessionid, shebeibianhao, proname, bsid), "hntbanheshijianchart", 1000, 450, false, false);		
	}
	
	public String hntbanheshijianXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TophunnintuView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}
		TophunnintuView tview = findbyshebeibianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, hntsj);
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='3' caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("拌和时间实时监控' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/jiaobanshijianreal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' refreshInterval='10' numberSuffix='S' setAdaptiveYMin='1' xAxisName='时间'"); 
	        strXML.append(" showRealTimeValue='0' realTimeValuePadding='50'  slantLabels='0' anchorRadius='5'"); 
	        strXML.append(" numDisplaySets='10' yAxisMinValue='100' yAxisMaxValue='200' lineThickness='5'>"); 
	        strXML.append(" <categories></categories><dataset showValues='0' color='ff5904'></dataset>"); 
	        strXML.append(" <trendlines>"); 
	        strXML.append(" <line parentYAxis='P' startValue='110' displayValue='下限' thickness='1'"); 
	        strXML.append(" color='0372AB' dashed='1' />"); 
	        strXML.append(" <line parentYAxis='P' startValue='190' displayValue='上限' thickness='1'"); 
	        strXML.append(" color='DF8600' dashed='1' />"); 
	        strXML.append(" </trendlines>"); 
	        strXML.append(" <styles>"); 
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12' />"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='Realtimevalue' styles='captionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}	
	
	public String getxcqueryXml(String sessionid, Integer biaoduan, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLineDY.swf", "",xcqueryXml(sessionid, biaoduan, proname, bsid), "xcquerychart", 1000, 450, false, false);		
	}	
	
	public String xcqueryXml(String sessionid, Integer biaoduan, String proname, int bsid) {
		if (null != biaoduan) {
			    List<TopRealSpeeddataView> toplist = sysService.limitSpeedlist(biaoduan, proname, bsid);
			    List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(biaoduan, proname, bsid);
				
				StringBuilder strXML = new StringBuilder("");  
		        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart manageResize='1' palette='3' caption='");
		        strXML.append("现场数据实时监控' dataStreamURL='");
		        strXML.append(StringUtil.getWebrootpath());
		        strXML.append("/getdata/xcqueryreal?biaoduan=");
		        strXML.append(biaoduan);
		        strXML.append("' refreshInterval='10' numberSuffix='℃' SNumberSuffix='m/min' setAdaptiveYMin='1'"); 
		        strXML.append(" setAdaptiveSYMin='1'  showRealTimeValue='1' labelStep='3'  anchorRadius='5'"); 
		        strXML.append(" realTimeValuePadding='50' slantLabels='0' numDisplaySets='10' lineThickness='5'>"); 
		        strXML.append(" PYAxisMinValue='100' PYAxisMaxValue='200' SYAxisMinValue='0' SYAXisMaxValue='80'"); 
		        strXML.append(" <categories></categories>"); 
		        for (TopRealSpeeddataView speeddata : toplist) {
		        	appendDataSetY(speeddata.getJianchen(), strXML);
		        	delSessionUpdateTime(sessionid, speeddata.getGpsno(), speed);
				}
		        for (TopRealTemperaturedataView tmpdata : toptmplist) {
					appendDataSet(tmpdata.getJianchen(), strXML);
					delSessionUpdateTime(sessionid, tmpdata.getTmpno(), tmp);
				}
		        strXML.append(" <styles>"); 
		        strXML.append(" <definition>"); 
		        strXML.append(" <style type='font' name='captionFont' size='12' />"); 
		        strXML.append(" </definition>"); 
		        strXML.append(" <application>"); 
		        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
		        strXML.append(" <apply toObject='Realtimevalue' styles='captionFont' />"); 
		        strXML.append(" </application>"); 
		        strXML.append(" </styles>"); 
		        strXML.append(" </chart>"); 
				return strXML.toString();		
		}
        else {
			return "";
		}		
	}	
	
	public String gettjjqueryXml(String sessionid, Integer biaoduan, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", "",tjjqueryXml(sessionid, biaoduan, proname, bsid), "xcquerychart", 1000, 450, false, false);		
	}	
	
	public String tjjqueryXml(String sessionid, Integer biaoduan, String proname, int bsid) {
		if (null != biaoduan) {
			    List<TopRealTjjdataView> toptjjlist = sysService.limitTjjlist(biaoduan, proname, bsid);
				StringBuilder strXML = new StringBuilder("");  
		        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart manageResize='1' palette='3' caption='");
		        strXML.append("添加剂数据实时监控' dataStreamURL='");
		        strXML.append(StringUtil.getWebrootpath());
		        strXML.append("/getdata/tjjqueryreal?biaoduan=");
		        strXML.append(biaoduan);
		        strXML.append("' refreshInterval='10' numberSuffix='kg' setAdaptiveYMin='1'"); 
		        strXML.append(" showRealTimeValue='1' labelStep='3'  anchorRadius='8'"); 
		        strXML.append(" realTimeValuePadding='50' slantLabels='0' numDisplaySets='10' lineThickness='5'>"); 
		        strXML.append(" <categories></categories>"); 
		        for (TopRealTjjdataView tjjdata : toptjjlist) {
					appendDataSet(tjjdata.getJianchen(), strXML);
					delSessionUpdateTime(sessionid, tjjdata.getTjjno(), tjj);
				}
		        strXML.append(" <styles>"); 
		        strXML.append(" <definition>"); 
		        strXML.append(" <style type='font' name='captionFont' size='12' />"); 
		        strXML.append(" </definition>"); 
		        strXML.append(" <application>"); 
		        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
		        strXML.append(" <apply toObject='Realtimevalue' styles='captionFont' />"); 
		        strXML.append(" </application>"); 
		        strXML.append(" </styles>"); 
		        strXML.append(" </chart>"); 
				return strXML.toString();		
		}
        else {
			return "";
		}		
	}	
	
	public String getlqysbXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", "",lqysbXml(sessionid, shebeibianhao, proname, bsid), "lqysbchart", 400, 250, false, false);		
	}	
	
	public String gettop10lqysbXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", "",top10lqysbXml(sessionid, shebeibianhao, proname, bsid), "lqysbchart", 400, 300, false, false);		
	}	
	
	public String getdapingtop10lqysbXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLine.swf", "",top10lqysbXml(sessionid, shebeibianhao, proname, bsid), "dapinglqysbchart", 675, 350, false, false);		
	}
	
	public String lqysbXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopLiqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, lqysb);
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='3' caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("油石比实时监控' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/lqysbreal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' refreshInterval='10' setAdaptiveYMin='1' xAxisName='时间'"); 
	        strXML.append(" showRealTimeValue='0' realTimeValuePadding='50'  slantLabels='0' anchorRadius='5'"); 
	        strXML.append(" numDisplaySets='10' lineThickness='5'>"); 
	        strXML.append(" <categories></categories><dataset showValues='0' color='ff5904'></dataset>"); 
	        strXML.append(" <styles>"); 
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12' />"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='Realtimevalue' styles='captionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}	
	
	 //按照东交给的要求， 显示近10或者更多盘的料，保证最后一盘是最新生产的
	public String top10lqysbXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopLiqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, lqysb);
			StringBuilder strXML = new StringBuilder("");  
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='3' caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("油石比实时监控(%)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/lqysbreal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' refreshInterval='10' setAdaptiveYMin='1' xAxisName='时间'"); 
	        strXML.append(" showRealTimeValue='0' realTimeValuePadding='50'  slantLabels='0' anchorRadius='5'"); 
	        strXML.append(" numDisplaySets='6' lineThickness='5'>"); 
	        strXML.append("<categories>");
	        List<LiqingView> top10ysblist=lqViewDAO.findTop10(shebeibianhao);
	        if(top10ysblist!=null && top10ysblist.size()>0){
	        	for(LiqingView top10ysb:top10ysblist){
	        		String baocunshijian=GetDate.formatshijian2(top10ysb.getShijian());
	        		if(StringUtil.Null2Blank(baocunshijian).length()==0){
	        			baocunshijian=GetDate.formatshijian2(top10ysb.getBaocunshijian());
	        		}
	        		strXML.append("<category label='"+baocunshijian+"'>");
	        		strXML.append("</category>");
	        	}
	        	strXML.append("</categories>");
		        strXML.append("<dataset showValues='0' color='ff5904'>");
	        	for(LiqingView top10ysb:top10ysblist){	        		
	        		strXML.append("<set value='"+top10ysb.getSjysb()+"'/>");
	        	}
	        }	        
	        strXML.append("</dataset>");
	        
	        /***预警的上下线**/
	        Liqingziduancfg lqonelow=sysService.lqsmslowfindBybh(shebeibianhao);
	        Liqingziduancfg lqonehigh=sysService.lqsmshighfindBybh(shebeibianhao);
	        if(lqonehigh!=null && lqonelow!=null){
	        	
	        }
	        strXML.append(" <styles>"); 
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12' />"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='Realtimevalue' styles='captionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}	

	//画图环境温度图
	public String getdapinghuanjingXml(String sessionid, String shebeibianhao, List tempdata1) {
		List<EnvironmentView> tempdata = (List<EnvironmentView>) tempdata1;	
		StringBuilder strXML = new StringBuilder("");
			int datasize = 3;
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='环境温度走势图(°C)' subcaption='(");
			if (null != tempdata && tempdata.size()>0) {
				strXML.append(tempdata.get(0).getFensushijian());
				strXML.append("至");
				strXML.append(tempdata.get(tempdata.size()-1).getFensushijian());
				datasize = tempdata.size()-1;
			}
			strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
			strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
			strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
			strXML.append("labelStep='");
			strXML.append(datasize);
			strXML.append("' showLabels='0' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
			strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='60' numberSuffix=''> ");
			strXML.append("<categories>");
			if (null != tempdata && tempdata.size()>0) {
			for (EnvironmentView tmp : tempdata) {
				strXML.append("<category  label='");
				strXML.append(tmp.getFensushijian());
				strXML.append("'/>");
			}
			}
			strXML.append("</categories>");
			if (null != tempdata && tempdata.size()>0) {
			strXML.append("<dataset seriesName='温度'>");
			for (EnvironmentView tmp : tempdata) {
				appendSetXml(strXML,tmp.getWendu());			
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
			return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapinghuanjingwendu", 675, 325, false, false);
		}
	
	//画图环境湿度图
	public String getdapinghuanjingshiduXml(String sessionid, String shebeibianhao, List tempdata1) {
		List<EnvironmentView> tempdata = (List<EnvironmentView>) tempdata1;	
		StringBuilder strXML = new StringBuilder("");
			int datasize = 3;
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='环境湿度走势图(%)' subcaption='(");
			if (null != tempdata && tempdata.size()>0) {
				strXML.append(tempdata.get(0).getFensushijian());
				strXML.append("至");
				strXML.append(tempdata.get(tempdata.size()-1).getFensushijian());
				datasize = tempdata.size()-1;
			}
			strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
			strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
			strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
			strXML.append("labelStep='");
			strXML.append(datasize);
			strXML.append("' showLabels='0' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
			strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' yAxisMaxValue='100' numberSuffix=''> ");
			strXML.append("<categories>");
			if (null != tempdata && tempdata.size()>0) {
			for (EnvironmentView tmp : tempdata) {
				strXML.append("<category  label='");
				strXML.append(tmp.getFensushijian());
				strXML.append("'/>");
			}
			}
			strXML.append("</categories>");
			
			strXML.append("<dataset seriesName='湿度'>");
			for (EnvironmentView tmp : tempdata) {
				appendSetXml(strXML,tmp.getShidu());			
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
			return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapinghuanjingshidu", 675, 325, false, false);
		}
	
	//画图环境风速图
	public String getdapinghuanjingfengsuXml(String sessionid, String shebeibianhao, List tempdata1) {
		List<EnvironmentView> tempdata = (List<EnvironmentView>) tempdata1;	
		StringBuilder strXML = new StringBuilder("");
			int datasize = 3;
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='环境风速走势图(m/s)' subcaption='(");
			if (null != tempdata && tempdata.size()>0) {
				strXML.append(tempdata.get(0).getFensushijian());
				strXML.append("至");
				strXML.append(tempdata.get(tempdata.size()-1).getFensushijian());
				datasize = tempdata.size()-1;
			}
			strXML.append(")' lineThickness='2' showValues='0' anchorRadius='2' ");
			strXML.append("divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
			strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");

			strXML.append(" showLabels='0' numvdivlines='20' chartRightMargin='14' chartLeftMargin='14' formatNumberScale='0' ");
			strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5'  numberSuffix=''> ");
			strXML.append("<categories>");
			if (null != tempdata && tempdata.size()>0) {
			for (EnvironmentView tmp : tempdata) {
				strXML.append("<category  label='");
				strXML.append(tmp.getFensushijian());
				strXML.append("'/>");
			}
			}
			strXML.append("</categories>");
			
			strXML.append("<dataset seriesName='风速'>");
			for (EnvironmentView tmp : tempdata) {
				appendSetXml(strXML,tmp.getFensu());	
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
			return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartdapinghuanjingfengsu", 675, 325, false, false);
		}
	//沥青混凝土监控温度表盘指示1
	public String getAsphaltAngularXml1(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",lqbanheguliaowenduXml(sessionid, shebeibianhao, proname, bsid), "AsphaltAngular2Id", 250, 250, false, false);
	}
	
	//沥青混凝土监控温度表盘指示2
	public String getAsphaltAngularXml2(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",lqbanheliqingwenduXml(sessionid, shebeibianhao, proname, bsid), "AsphaltAngular3Id", 250, 250, false, false);
	}
	
	//沥青混凝土监控温度表盘指示3
	public String getAsphaltAngularXml3(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",lqbanhechenpingwenduXml(sessionid, shebeibianhao, proname, bsid), "AsphaltAngular4Id", 300,300, false, false);
	}
	
	//沥青混凝土监控温度表盘指示3-大屏
	public String getdapingAsphaltAngularXml3(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",dapinglqbanhechenpingwenduXml(sessionid, shebeibianhao, proname, bsid), "AsphaltAngular4Iddaping", 330,330, false, false);
	}
	
	//沥青骨料实时温度刷新
	public String updateLiqingguliaowenduRealValue(String sessionid, String shebeibianhao) {
		List results = findbysql("select baocunshijian,glwd,shijian from TopLiqingView where shebeibianhao = '"+shebeibianhao+"'");
		String result = "";
		if (results.size()>0) {
			try{	            
				Object[] row = (Object[]) results.get(0);   
	            String baocunshijian = (String) row[2]; 
		        if (StringUtil.Null2Blank(baocunshijian).length()==0) {
		        	baocunshijian = (String) row[0];
		        } 
	            if (updateSessionUpdateTime(sessionid, shebeibianhao, baocunshijian, glwd)) {
	            	double guliaowendu=Double.parseDouble(row[1].toString());
	            	guliaowendu =Double.parseDouble(String.format("%1$.1f",guliaowendu));	            
	            	result = "&label=" + baocunshijian + "&value=" +guliaowendu;
					if (logger.isDebugEnabled()) {
						logger.debug(result);
					}
	            }
            }catch(Exception ex){}
        }		
		return result;	
	}	
	
	//沥青实时温度刷新
	public String updateLiqingliqingwenduRealValue(String sessionid, String shebeibianhao) {
		List results = findbysql("select baocunshijian,lqwd,shijian from TopLiqingView where shebeibianhao = '"+shebeibianhao+"'");
		String result = "";
		if (results.size()>0) {
			try{
            Object[] row = (Object[]) results.get(0);   
            String baocunshijian = (String) row[2]; 
            if (StringUtil.Null2Blank(baocunshijian).length()==0) {
            	baocunshijian = (String) row[0];
			} 
            if (updateSessionUpdateTime(sessionid, shebeibianhao, baocunshijian, lqwd)) {
            double liqingwendu =Double.parseDouble(row[1].toString()); 
            liqingwendu =Double.parseDouble(String.format("%1$.1f",liqingwendu));
            result = "&label=" + baocunshijian + "&value=" +liqingwendu;
			if (logger.isDebugEnabled()) {
				logger.debug(result);
			}
            }
			}catch(Exception ex){}
		}			
		return result;	
	}	
	
	//沥青成品料实时温度刷新
	public String updateLiqingchenpingwenduRealValue(String sessionid, String shebeibianhao) {
		List results = findbysql("select baocunshijian,clwd,shijian from TopLiqingView where shebeibianhao = '"+shebeibianhao+"'");
		String result = "";
		if (results.size()>0) {
			try{
				Object[] row = (Object[]) results.get(0);   
				String baocunshijian = (String) row[2]; 
				if (StringUtil.Null2Blank(baocunshijian).length()==0) {
					baocunshijian = (String) row[0];
				} 
	            if (updateSessionUpdateTime(sessionid, shebeibianhao, baocunshijian, cplwd)) {
	            	double chengpinliaowendu =Double.parseDouble(row[1].toString());
	            	chengpinliaowendu =Double.parseDouble(String.format("%1$.1f",chengpinliaowendu));
	            	result = "&label=" + baocunshijian + "&value=" +chengpinliaowendu;					
	            }
			}catch(Exception ex){
				logger.debug(ex.getMessage());
			}
		}			
		return result;	
	}
	
	public String lqbanheguliaowenduXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopliqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, glwd);
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart tooltip='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("骨料温度实时监控(°C)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/liqingguliaowendureal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' bgColor='FFFFFF' upperLimit='200' lowerLimit='160'"); 
	        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
	        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
	        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
	        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
	        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
	        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
	        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
	        strXML.append("<colorRange>"); 
	        strXML.append("<color minValue='0' maxValue='80' code='C1E1C1' alpha='40'/>"); 
	        strXML.append("<color minValue='80' maxValue='160' code='F6F164' alpha='40'/>"); 
	        strXML.append("<color minValue='160' maxValue='200' code='F70118' alpha='40'/>"); 
	        strXML.append("</colorRange>"); 
	        strXML.append("<dials>"); 
	        strXML.append("<dial value='65' borderColor='FFFFFF' bgColor='000000,CCCCCC,000000'"); 
	        strXML.append(" borderAlpha='0' baseWidth='10'/>"); 
	        strXML.append("</dials>"); 
	        strXML.append("<annotations>"); 
	        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
	        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
	        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
	        strXML.append(" fillAngle='-45'/>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
	        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
	        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
	        strXML.append(" fillAngle='-45'/>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
	        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
	        strXML.append("</annotationGroup>"); 
	        strXML.append("</annotations>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	public String lqbanheliqingwenduXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopliqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, lqwd);
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart tooltip='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("沥青温度实时监控(°C)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/liqingliqingwendureal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' bgColor='FFFFFF' upperLimit='200' lowerLimit='0' showLimits='1'"); 
	        strXML.append(" baseFontColor='666666'  majorTMNumber='11' majorTMColor='666666'"); 
	        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='666666' minorTMHeight='3'"); 
	        strXML.append(" pivotRadius='20' showGaugeBorder='0' gaugeOuterRadius='80'"); 
	        strXML.append(" gaugeInnerRadius='70' gaugeOriginX='125' gaugeOriginY='125'"); 
	        strXML.append(" gaugeScaleAngle='320' displayValueDistance='10' placeValuesInside='1'"); 
	        strXML.append(" gaugeFillMix='' pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'"); 
	        strXML.append(" refreshInterval='10' pivotfillRatio='80,20' showShadow='0'>"); 
	        strXML.append("<colorRange>"); 
	        strXML.append("<color minValue='0' maxValue='160' code='00FF00' alpha='0'/>"); 
	        strXML.append("<color minValue='160' maxValue='200' name='Danger' code='FF0000' alpha='50'/>"); 
	        strXML.append("</colorRange>"); 
	        strXML.append("<dials>"); 
	        strXML.append("<dial value='65' bordercolor='FFFFFF' bgColor='bebcb0, f0efea, bebcb0'"); 
	        strXML.append(" borderAlpha='0' baseWidth='10' topWidth='3'/>"); 
	        strXML.append("</dials>"); 
	        strXML.append("<annotations>"); 
	        strXML.append("<annotationGroup xPos='125' yPos='125' fillRatio='10,125,254' fillPattern='radial' >"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='120'"); 
	        strXML.append(" borderColor= 'bebcb0' fillAsGradient='1' fillColor='f0efea, bebcb0'  fillRatio='85,15'/>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='100'"); 
	        strXML.append(" fillColor='bebcb0, f0efea' fillRatio='85,15' />"); 
	        strXML.append("<annotation type='circle' xPos='0' color='FFFFFF' yPos='0'"); 
	        strXML.append(" radius='80' borderColor= 'f0efea' />"); 
	        strXML.append("</annotationGroup>"); 
	        strXML.append("</annotations>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	public String lqbanhechenpingwenduXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopliqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, cplwd);
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("出料温度实时监控(°C)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/liqingchenpingwendureal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' bgColor='FFFFFF' upperLimit='200' lowerLimit='0' majorTMNumber='7'"); 
	        strXML.append(" majorTMColor='AF9A03'  majorTMHeight='8' minorTMNumber='0'"); 
	        strXML.append(" majorTMThickness='8' showGaugeBorder='0' gaugeOuterRadius='100'"); 
	        strXML.append(" gaugeOriginX='150' gaugeOriginY='150' gaugeScaleAngle='280'"); 
	        strXML.append(" placeValuesInside='1' gaugeInnerRadius='90' displayValueDistance='17'"); 
	        strXML.append(" pivotRadius='12' pivotFillMix='{AF9A03},{ffffff}' pivotBorderColor='AF9A03'"); 
	        strXML.append(" pivotBorderThickness='2' pivotFillRatio='50,50' pivotFillType='linear'"); 
	        strXML.append(" refreshInterval='10' showPivotBorder='1' showShadow='0'>");
	        strXML.append("<dials>"); 
	        strXML.append("<dial value='25' borderAlpha='0'  bgColor='6A6FA6,AF9A03'"); 
	        strXML.append(" baseWidth='4' topWidth='4' radius='93'/>"); 
	        strXML.append("</dials>"); 
	        strXML.append("<annotations>"); 
	        strXML.append("<annotationGroup xPos='150' yPos='150'>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='110'"); 
	        strXML.append(" fillPattern='linear' fillColor='eeeeee,ebce05,eeeeee'"); 
	        strXML.append(" fillRatio='0,100,0' fillAngle='270' showBorder='1'"); 
	        strXML.append(" borderColor='444444' borderThickness='1'/>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='100'"); 
	        strXML.append(" fillPattern='linear' fillColor='ffffff,ebce05,eeeeee'"); 
	        strXML.append(" fillAlpha='100,10,100'  fillRatio='5,83,12' fillAngle='270'/>"); 
	        strXML.append("</annotationGroup>"); 
	        strXML.append("</annotations>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	public String dapinglqbanhechenpingwenduXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopliqingView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TopLiqingView tview = toplqDAO.findByGprsbianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, cplwd);
			StringBuilder strXML = new StringBuilder("");  
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart  caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("出料温度实时监控(°C)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/liqingchenpingwendureal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' bgColor='FFFFFF' upperLimit='200' lowerLimit='0' majorTMNumber='7'"); 
	        strXML.append(" majorTMColor='AF9A03'  majorTMHeight='8' minorTMNumber='0'"); 
	        strXML.append(" majorTMThickness='8' showGaugeBorder='0' gaugeOuterRadius='100'"); 
	        strXML.append(" gaugeOriginX='165' gaugeOriginY='165' gaugeScaleAngle='280'"); 
	        strXML.append(" placeValuesInside='1' gaugeInnerRadius='90' displayValueDistance='17'"); 
	        strXML.append(" pivotRadius='12' pivotFillMix='{AF9A03},{ffffff}' pivotBorderColor='AF9A03'"); 
	        strXML.append(" pivotBorderThickness='2' pivotFillRatio='50,50' pivotFillType='linear'"); 
	        strXML.append(" refreshInterval='10' showPivotBorder='1' showShadow='0'>");
	        strXML.append("<dials>"); 
	        strXML.append("<dial value='25' borderAlpha='0'  bgColor='6A6FA6,AF9A03'"); 
	        strXML.append(" baseWidth='4' topWidth='4' radius='93'/>"); 
	        strXML.append("</dials>"); 
	        strXML.append("<annotations>"); 
	        strXML.append("<annotationGroup xPos='165' yPos='165' >"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='110'"); 
	        strXML.append(" fillPattern='linear' fillColor='eeeeee,ebce05,eeeeee'"); 
	        strXML.append(" fillRatio='0,100,0' fillAngle='270' showBorder='1'"); 
	        strXML.append(" borderColor='444444' borderThickness='1'/>"); 
	        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='100'"); 
	        strXML.append(" fillPattern='linear' fillColor='ffffff,ebce05,eeeeee'"); 
	        strXML.append(" fillAlpha='100,10,100'  fillRatio='5,83,12' fillAngle='270'/>"); 
	        strXML.append("</annotationGroup>"); 
	        strXML.append("</annotations>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	//水泥混凝土监控主界面材料用量
	public String getConcreteAreaXml() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeStackedArea.swf", StringUtil.getWebrootpath()+"/realdata/RealTimeStArea1.xml","", "hntbanhecailiaochart", 1000, 280, false, false);
	}
	
	//水泥混凝土实时材料用量刷新
	public String updateConcreteDosageLineRealValue() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());
		return "&label=" + timeLabel + "&value=" +getRandom(100, 400)+"|"+getRandom(200, 800)+"|"+getRandom(400, 1200);	
	}
	
	//路面施工监控主界面
	public String getConstructionMonitorLineXml() {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeLineDY.swf", StringUtil.getWebrootpath()+"/realdata/RealTimeDYLine2.xml","", "myChartId", 1000, 400, false, true);
	}
	
	//路面施工实时温度刷新
	public String updateConstructMonitorLineRealValue() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeLabel = sdf.format(cal.getTime());	
		return "&label=" + timeLabel + "&value=" +getRandom(140, 200)+"|"+getRandom(130, 180);		
	}	
	

	

	
	public List<TophunnintuView> findByProperty(String propertyName, int bsid) {
		return topDAO.findByProperty(propertyName, bsid);
	}
	
	public List<TopLiqingView> findTopLiqingView(String propertyName,int bsid) {
		return toplqDAO.findByProperty(propertyName ,bsid);
	}

	public TophunnintuView findbyshebeibianhao(String shebeibianhao) {
		return topDAO.findByGprsbianhao(shebeibianhao);
	}
	

	
	public List jzbwList(Integer biaoduan, Integer xiangmubu, String shebeibianhao){
		return topDAO.findJzbw(biaoduan, xiangmubu, shebeibianhao);
	}
	
	public List getAllByFiled(String filed){
		return topDAO.findAllByField(filed);
	}
	
	public List findbysql(String sql) {
		return topDAO.findBySql(sql);
	}
	
	public List findTop() {
		return hntviewDAO.findTop();
	}
	
	public List findLiqingTop() {
		return topreallqviewDAO.findTop();
	}
	
	public List findShuiwenTop() {
		return topswDAO.findTop();
	}
	
	public List findTemperatureTop() {
		return toprealtmpviewDAO.findTop();
	}
	
	//出料口
	public List findChuliaokouTemperatureTop() {
		return toprealChuliaokoutmpviewDAO.findTop();
	}
	
	public List findSpeedTop() {
		return toprealspeedviewDAO.findTop();
	}
	
	public Hntview getHviewById(Integer id) {
		return hntDAO.get(id);
	}
	
	public LiqingphbView getLqphbviewById(Integer id) {
		return lqphbviewDAO.get(id);
	}
	
	public ShuiwenphbView getSwphbviewById(Integer id) {
		//return swphbviewDAO.get(id);
		return swphbViewDAO.get(id);
	}
	
	public TemperaturedataView getTmpdataView(Integer id){
		return tmpdataviewDAO.get(id);
	}
	
	//出料口
	public ChuliaokouTemperaturedataView getChuliaokouTmpdataView(Integer id){
		return clktmpdataviewDAO.get(id);
	}
	
	public SpeeddataView getSpeeddataView(Integer id){
		return speeddataviewDAO.get(id);
	}
	
	public LiqingmanualphbView getLqphbmanualviewById(Integer id) {
		return lqphbmanualviewDAO.get(id);
	}
	
	public ShuiwenmanualphbView getSwphbmanualviewById(Integer id) {
		return swphbmanualviewDAO.get(id);
	}
	
	
	public boolean updateSessionUpdateTime(String sessionid, String shebeibianhao, String updatetime, String updatetype) {
		Sessionupdatetime su =  suDAO.findbyidbh(sessionid, shebeibianhao, updatetype);
		if (null != su) {
			if (su.getUpdatetime().equalsIgnoreCase(updatetime)) {
				return false;
			} else {
				su.setUpdatetime(updatetime);
				suDAO.saveOrUpdate(su);
				return true;
			}				
		} else {
			su = new Sessionupdatetime();
			su.setSessionid(sessionid);
			su.setShebeibianhao(shebeibianhao);
			su.setUpdatetime(updatetime);
			su.setUpdatetype(updatetype);
			suDAO.saveOrUpdate(su);
			return true;
		} 
	}
	
	public void delSessionUpdateTime(String sessionid, String shebeibianhao, String updatetype) {
		Sessionupdatetime su =  suDAO.findbyidbh(sessionid, shebeibianhao, updatetype);
		if (null != su) {
			suDAO.delete(su);	
		}			
	}
	
	public String getLedData(String sbbh, String gcmc, String sgbw, String sgdd, String biaoduanid, String xiangmubuid, String zuoyeduiid) {
		TophunnintuView tv = topDAO.findToponehntview(sbbh, gcmc, sgbw, sgdd, biaoduanid, xiangmubuid, zuoyeduiid);
		StringBuffer sbuffer = new StringBuffer();
		String bgs = ":[";
		String eds = "] ";
		if (null != tv) {
			HntbhzziduancfgView hbfield = queryService.gethntcfgfield(sbbh);
			if (null != hbfield) {
				if (StringUtil.Null2Blank(tv.getBanhezhanminchen()).length()>0) {
					sbuffer.append("拌和站名称");
					sbuffer.append(bgs);
					sbuffer.append(tv.getBanhezhanminchen());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getChuliaoshijian()).length()>0) {
					sbuffer.append(hbfield.getChuliaoshijian());
					sbuffer.append(bgs);
					sbuffer.append(tv.getChuliaoshijian());
					sbuffer.append(eds);
				}
/*				if (StringUtil.Null2Blank(tv.getGongchengmingcheng()).length()>0) {
					sbuffer.append(hbfield.getGongchengmingcheng());
					sbuffer.append(bgs);
					sbuffer.append(tv.getGongchengmingcheng());
					sbuffer.append(eds);
				}*/
				if (StringUtil.Null2Blank(tv.getJiaozuobuwei()).length()>0) {
					sbuffer.append(hbfield.getJiaozuobuwei());
					sbuffer.append(bgs);
					sbuffer.append(tv.getJiaozuobuwei());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getSigongdidian()).length()>0) {
					sbuffer.append(hbfield.getSigongdidian());
					sbuffer.append(bgs);
					sbuffer.append(tv.getSigongdidian());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getPeifanghao()).length()>0) {
					sbuffer.append(hbfield.getPeifanghao());
					sbuffer.append(bgs);
					sbuffer.append(tv.getPeifanghao());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getChaozuozhe()).length()>0) {
					sbuffer.append(hbfield.getChaozuozhe());
					sbuffer.append(bgs);
					sbuffer.append(tv.getChaozuozhe());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getGujifangshu()).length()>0) {
					sbuffer.append(hbfield.getGujifangshu());
					sbuffer.append(bgs);
					sbuffer.append(tv.getGujifangshu());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getQiangdudengji()).length()>0) {
					sbuffer.append(hbfield.getQiangdudengji());
					sbuffer.append(bgs);
					sbuffer.append(tv.getQiangdudengji());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getJiaobanshijian()).length()>0) {
					sbuffer.append(hbfield.getJiaobanshijian());
					sbuffer.append(bgs);
					sbuffer.append(tv.getJiaobanshijian());
					sbuffer.append(eds);
				}
				if (StringUtil.Null2Blank(tv.getGongdanhao()).length()>0) {
					sbuffer.append(hbfield.getGongdanhao());
					sbuffer.append(bgs);
					sbuffer.append(tv.getGongdanhao());
					sbuffer.append(eds);
				}
				try {
					if (StringUtil.Null2Blank(tv.getSha1_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getSha1_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getSha1_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getSha1_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getSha1_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getSha1_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getSha1_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getSha1_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getSha2_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getSha2_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getSha2_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getSha2_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				try {
					if (StringUtil.Null2Blank(tv.getSha2_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getSha2_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getSha2_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getSha2_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShi1_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShi1_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getShi1_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShi1_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				try {
					if (StringUtil.Null2Blank(tv.getShi1_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShi1_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getShi1_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShi1_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShi2_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShi2_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getShi2_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShi2_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShi2_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShi2_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getShi2_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShi2_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getGuliao5_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getGuliao5_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getGuliao5_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getGuliao5_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getGuliao5_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getGuliao5_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getGuliao5_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getGuliao5_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				try {
					if (StringUtil.Null2Blank(tv.getShuini1_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShuini1_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getShuini1_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShuini1_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShuini1_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShuini1_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getShuini1_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShuini1_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShuini2_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShuini2_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getShuini2_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShuini2_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShuini2_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShuini2_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getShuini2_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShuini2_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				try {
					if (StringUtil.Null2Blank(tv.getKuangfen3_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getKuangfen3_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getKuangfen3_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getKuangfen3_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getKuangfen3_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getKuangfen3_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getKuangfen3_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getKuangfen3_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getFeimeihui4_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getFeimeihui4_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getFeimeihui4_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getFeimeihui4_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getFeimeihui4_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getFeimeihui4_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getFeimeihui4_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getFeimeihui4_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getFenliao5_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getFenliao5_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getFenliao5_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getFenliao5_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getFenliao5_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getFenliao5_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getFenliao5_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getFenliao5_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getFenliao6_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getFenliao6_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getFenliao6_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getFenliao6_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getFenliao6_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getFenliao6_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getFenliao6_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getFenliao6_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShui1_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShui1_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getShui1_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShui1_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShui1_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShui1_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getShui1_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShui1_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShui2_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShui2_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getShui2_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShui2_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getShui2_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getShui2_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getShui2_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getShui2_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji1_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji1_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji1_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji1_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji1_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji1_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji1_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji1_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji2_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji2_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji2_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji2_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji2_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji2_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji2_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji2_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji3_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji3_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji3_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji3_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji3_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji3_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji3_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji3_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji4_lilunzhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji4_lilunzhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji4_lilunzhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji4_lilunzhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if (StringUtil.Null2Blank(tv.getWaijiaji4_shijizhi()).length()>0 && 
							Double.parseDouble(StringUtil.Null2Blank(tv.getWaijiaji4_shijizhi()))-0.8>0) {
						sbuffer.append(hbfield.getWaijiaji4_shijizhi());
						sbuffer.append(bgs);
						sbuffer.append(tv.getWaijiaji4_shijizhi());
						sbuffer.append(eds);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return sbuffer.toString();
	}
	
	//摊铺数据数据
	public String tanpuRealValue(String sessionid, String shebeibianhao, String fieldValue) {
		String sql = "select a.CollectTime,a."+fieldValue+" from XCData1 a where ID = (select max(s.ID) from XCData1 s where GprsDeviceId = "+Integer.parseInt(shebeibianhao) + ")";
		List results = findbysql(sql);
		//logger.info(sql);
		String result = "";
		try{
			if (results.size()>0) {
	            Object[] row = (Object[]) results.get(0);   
	            String baocunshijian =  (String) row[0];; 
	            String tanpudata = String.valueOf(row[1]) ; 
	            result = "&label=" + baocunshijian + "&value=" +StringUtil.Null2Zero(tanpudata);
				if (logger.isDebugEnabled()) {
					logger.debug(result);
				}
			}		
		}catch(Exception e){
			logger.debug(e);
		}
			
		return result;	
	}
	
	//碾压-碾压温度
	public String getNianYaXML1(String sessionid, int gprsDeviceId, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",NianYaTempXml(sessionid, gprsDeviceId, proname, bsid), "nianya1Id", 250, 250, false, false);
	}
	public String getNianYaXML2(String sessionid, int gprsDeviceId, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",NianYaTempXml(sessionid, gprsDeviceId, proname, bsid), "nianya2Id", 250, 250, false, false);
	}
	
	//碾压数据
	public String NianYaTempXml(String sessionid, int gprsDeviceId, String proname, int bsid) {
		//XCData2 xcData = xcData2DAO.getXCData2(gprsDeviceId);
		String shebeibianhao = String.valueOf(gprsDeviceId);
		//delSessionUpdateTime(sessionid, shebeibianhao, nianya);
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart showRealTimeValue='1'  labelDisplay='Rotate' slantLabels='1' showValue='1' caption='test' subcaption='1234' tooltip='");
        strXML.append("碾压温度实时监控' dataStreamURL='");
        strXML.append(StringUtil.getWebrootpath());
        strXML.append("/getdata/nianyawendureal?shebeibianhao=");
        strXML.append(shebeibianhao);
        strXML.append("' bgColor='FFFFFF' upperLimit='180' lowerLimit='20'"); 
        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
        strXML.append("<colorRange>"); 
        strXML.append("<color minValue='0' maxValue='60' code='C1E1C1' alpha='40'/>"); 
        strXML.append("<color minValue='60' maxValue='120' code='F6F164' alpha='40'/>"); 
        strXML.append("<color minValue='120' maxValue='180' code='F70118' alpha='40'/>"); 
        strXML.append("</colorRange>"); 
        strXML.append("<dials>"); 
        strXML.append("<dial toolText='碾压温度' value='0' borderColor='FFFFFF' bgColor='111111,CCCCCC,000000'  valueY='190'"); 
        strXML.append(" borderAlpha='11' baseWidth='10' showValue='1'/>"); 
     
        strXML.append("</dials>"); 
        strXML.append("<annotations>"); 
        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
        strXML.append("</annotationGroup>"); 
        strXML.append("</annotations>"); 
        strXML.append(" </chart>"); 
		return strXML.toString();			
	}
	
	//摊铺机-摊铺温度
	public String getTanPuJiXml1(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",TanPuJiTempXml1(sessionid, shebeibianhao, proname, bsid), "tanpu1Id", 250, 250, false, false);
	}
	public String getTanPuJiXml2(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",TanPuJiTempXml2(sessionid, shebeibianhao, proname, bsid), "tanpu2Id", 250, 250, false, false);
	}
	public String getTanPuJiXml3(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",TanPuJiTempXml3(sessionid, shebeibianhao, proname, bsid), "tanpu3Id", 250, 250, false, false);
	}
	public String getTanPuJiXml4(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",TanPuJiTempXml4(sessionid, shebeibianhao, proname, bsid), "tanpu4Id", 250, 250, false, false);
	}
	public String getTanPuJiXml5(String sessionid, String shebeibianhao, String proname, int bsid) {
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/AngularGauge.swf", "",TanPuJiTempXml5(sessionid, shebeibianhao, proname, bsid), "tanpu5Id", 250, 250, false, false);
	}
	
	//摊铺温度
	public String TanPuJiTempXml1(String sessionid, String gprsDeviceId, String proname, int bsid) {
		//XCData2 xcData = xcData2DAO.getXCData2(gprsDeviceId);
		String shebeibianhao = String.valueOf(gprsDeviceId);
		//delSessionUpdateTime(sessionid, shebeibianhao, nianya);
		String mark = "摊铺温度";
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart showRealTimeValue='1'  labelDisplay='Rotate' slantLabels='1' showValue='1' caption='test' subcaption='1234' tooltip='");
        strXML.append("摊铺数据实时监控' dataStreamURL='");
        strXML.append(StringUtil.getWebrootpath());
        strXML.append("/getdata/tanpureal?shebeibianhao=");
        strXML.append(shebeibianhao);
        strXML.append("&wmfield=");
        strXML.append(proname);
        strXML.append("' bgColor='FFFFFF' upperLimit='210' lowerLimit='0'"); 
        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
        strXML.append("<colorRange>"); 
        strXML.append("<color minValue='0' maxValue='70' code='C1E1C1' alpha='40'/>"); 
        strXML.append("<color minValue='70' maxValue='140' code='F6F164' alpha='40'/>"); 
        strXML.append("<color minValue='140' maxValue='210' code='F70118' alpha='40'/>"); 
        strXML.append("</colorRange>"); 
        strXML.append("<dials>"); 
        strXML.append("<dial toolText='"+mark+"' value='0' borderColor='FFFFFF' bgColor='111111,CCCCCC,000000'  valueY='190'"); 
        strXML.append(" borderAlpha='11' baseWidth='10' showValue='1'/>"); 
     
        strXML.append("</dials>"); 
        strXML.append("<annotations>"); 
        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
        strXML.append("</annotationGroup>"); 
        strXML.append("</annotations>"); 
        strXML.append(" </chart>"); 
		return strXML.toString();			
	}
	//大气温度
	public String TanPuJiTempXml2(String sessionid, String gprsDeviceId, String proname, int bsid) {
		//XCData2 xcData = xcData2DAO.getXCData2(gprsDeviceId);
		String shebeibianhao = String.valueOf(gprsDeviceId);
		//delSessionUpdateTime(sessionid, shebeibianhao, nianya);
		String mark = "大气温度";
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart showRealTimeValue='1'  labelDisplay='Rotate' slantLabels='1' showValue='1' caption='test' subcaption='1234' tooltip='");
        strXML.append("摊铺数据实时监控' dataStreamURL='");
        strXML.append(StringUtil.getWebrootpath());
        strXML.append("/getdata/tanpureal?shebeibianhao=");
        strXML.append(shebeibianhao);
        strXML.append("&wmfield=");
        strXML.append(proname);
        strXML.append("' bgColor='FFFFFF' upperLimit='60' lowerLimit='0'"); 
        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
        strXML.append("<colorRange>"); 
        strXML.append("<color minValue='0' maxValue='20' code='C1E1C1' alpha='40'/>"); 
        strXML.append("<color minValue='20' maxValue='40' code='F6F164' alpha='40'/>"); 
        strXML.append("<color minValue='40' maxValue='60' code='F70118' alpha='40'/>"); 
        strXML.append("</colorRange>"); 
        strXML.append("<dials>"); 
        strXML.append("<dial toolText='"+mark+"' value='0' borderColor='FFFFFF' bgColor='111111,CCCCCC,000000'  valueY='190'"); 
        strXML.append(" borderAlpha='11' baseWidth='10' showValue='1'/>"); 
     
        strXML.append("</dials>"); 
        strXML.append("<annotations>"); 
        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
        strXML.append("</annotationGroup>"); 
        strXML.append("</annotations>"); 
        strXML.append(" </chart>"); 
		return strXML.toString();			
	}
	//大气风速
	public String TanPuJiTempXml3(String sessionid, String gprsDeviceId, String proname, int bsid) {
		//XCData2 xcData = xcData2DAO.getXCData2(gprsDeviceId);
		String shebeibianhao = String.valueOf(gprsDeviceId);
		//delSessionUpdateTime(sessionid, shebeibianhao, nianya);
		String mark = "大气风速";
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart showRealTimeValue='1'  labelDisplay='Rotate' slantLabels='1' showValue='1' caption='test' subcaption='1234' tooltip='");
        strXML.append("摊铺数据实时监控' dataStreamURL='");
        strXML.append(StringUtil.getWebrootpath());
        strXML.append("/getdata/tanpureal?shebeibianhao=");
        strXML.append(shebeibianhao);
        strXML.append("&wmfield=");
        strXML.append(proname);
        strXML.append("' bgColor='FFFFFF' upperLimit='20' lowerLimit='0'"); 
        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
        strXML.append("<colorRange>"); 
        strXML.append("<color minValue='0' maxValue='7' code='C1E1C1' alpha='40'/>"); 
        strXML.append("<color minValue='7' maxValue='14' code='F6F164' alpha='40'/>"); 
        strXML.append("<color minValue='14' maxValue='21' code='F70118' alpha='40'/>"); 
        strXML.append("</colorRange>"); 
        strXML.append("<dials>"); 
        strXML.append("<dial toolText='"+mark+"' value='0' borderColor='FFFFFF' bgColor='111111,CCCCCC,000000'  valueY='190'"); 
        strXML.append(" borderAlpha='11' baseWidth='10' showValue='1'/>"); 
     
        strXML.append("</dials>"); 
        strXML.append("<annotations>"); 
        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
        strXML.append("</annotationGroup>"); 
        strXML.append("</annotations>"); 
        strXML.append(" </chart>"); 
		return strXML.toString();			
	}
	//大气湿度
	public String TanPuJiTempXml4(String sessionid, String gprsDeviceId, String proname, int bsid) {
		//XCData2 xcData = xcData2DAO.getXCData2(gprsDeviceId);
		String shebeibianhao = String.valueOf(gprsDeviceId);
		//delSessionUpdateTime(sessionid, shebeibianhao, nianya);
		String mark = "大气湿度";
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart showRealTimeValue='1'  labelDisplay='Rotate' slantLabels='1' showValue='1' caption='test' subcaption='1234' tooltip='");
        strXML.append("摊铺数据实时监控' dataStreamURL='");
        strXML.append(StringUtil.getWebrootpath());
        strXML.append("/getdata/tanpureal?shebeibianhao=");
        strXML.append(shebeibianhao);
        strXML.append("&wmfield=");
        strXML.append(proname);
        strXML.append("' bgColor='FFFFFF' upperLimit='300' lowerLimit='0'"); 
        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
        strXML.append("<colorRange>"); 
        strXML.append("<color minValue='0' maxValue='100' code='C1E1C1' alpha='40'/>"); 
        strXML.append("<color minValue='100' maxValue='200' code='F6F164' alpha='40'/>"); 
        strXML.append("<color minValue='200' maxValue='300' code='F70118' alpha='40'/>"); 
        strXML.append("</colorRange>"); 
        strXML.append("<dials>"); 
        strXML.append("<dial toolText='"+mark+"' value='0' borderColor='FFFFFF' bgColor='111111,CCCCCC,000000'  valueY='190'"); 
        strXML.append(" borderAlpha='11' baseWidth='10' showValue='1'/>"); 
     
        strXML.append("</dials>"); 
        strXML.append("<annotations>"); 
        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
        strXML.append("</annotationGroup>"); 
        strXML.append("</annotations>"); 
        strXML.append(" </chart>"); 
		return strXML.toString();			
	}
	//摊铺速度
	public String TanPuJiTempXml5(String sessionid, String gprsDeviceId, String proname, int bsid) {
		//XCData2 xcData = xcData2DAO.getXCData2(gprsDeviceId);
		String shebeibianhao = String.valueOf(gprsDeviceId);
		//delSessionUpdateTime(sessionid, shebeibianhao, nianya);
		String mark = "摊铺速度";
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart showRealTimeValue='1'  labelDisplay='Rotate' slantLabels='1' showValue='1' caption='test' subcaption='1234' tooltip='");
        strXML.append("摊铺数据实时监控' dataStreamURL='");
        strXML.append(StringUtil.getWebrootpath());
        strXML.append("/getdata/tanpureal1?shebeibianhao=");
        strXML.append(shebeibianhao);
        strXML.append("&wmfield=");
        strXML.append(proname);
        strXML.append("' bgColor='FFFFFF' upperLimit='9' lowerLimit='0'"); 
        strXML.append(" baseFontColor='FFFFFF' majorTMNumber='11' majorTMColor='FFFFFF'"); 
        strXML.append(" majorTMHeight='8' minorTMNumber='5' minorTMColor='FFFFFF' minorTMHeight='3'"); 
        strXML.append(" toolTipBorderColor='FFFFFF' toolTipBgColor='333333' gaugeOuterRadius='80'"); 
        strXML.append(" gaugeOriginX='125' gaugeOriginY='125' gaugeScaleAngle='270'"); 
        strXML.append(" placeValuesInside='1' gaugeInnerRadius='80%25' annRenderDelay='0' "); 
        strXML.append(" gaugeFillMix='' pivotRadius='10' showPivotBorder='0' refreshInterval='10'"); 
        strXML.append(" pivotFillMix='{CCCCCC},{333333}' pivotFillRatio='50,50' showShadow='0'>"); 
        strXML.append("<colorRange>"); 
        strXML.append("<color minValue='0' maxValue='3' code='C1E1C1' alpha='40'/>"); 
        strXML.append("<color minValue='3' maxValue='6' code='F6F164' alpha='40'/>"); 
        strXML.append("<color minValue='6' maxValue='9' code='F70118' alpha='40'/>"); 
        strXML.append("</colorRange>"); 
        strXML.append("<dials>"); 
        strXML.append("<dial toolText='"+mark+"' value='0' borderColor='FFFFFF' bgColor='111111,CCCCCC,000000'  valueY='190'"); 
        strXML.append(" borderAlpha='11' baseWidth='10' showValue='1'/>"); 
     
        strXML.append("</dials>"); 
        strXML.append("<annotations>"); 
        strXML.append("<annotationGroup xPos='125' yPos='125' showBelow='1'>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='115'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='CCCCCC,111111'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='90'"); 
        strXML.append(" startAngle='0' endAngle='360' fillColor='111111,cccccc'"); 
        strXML.append(" fillPattern='linear' fillAlpha='100,100'  fillRatio='50,50'"); 
        strXML.append(" fillAngle='-45'/>"); 
        strXML.append("<annotation type='circle' xPos='0' yPos='0' radius='80'"); 
        strXML.append(" startAngle='0' endAngle='360' color='666666'/>"); 
        strXML.append("</annotationGroup>"); 
        strXML.append("</annotations>"); 
        strXML.append(" </chart>"); 
		return strXML.toString();			
	}
	
	
	/**
	 * 摊铺计划横道图(甘特图)
	 * @param topMenu1   开始日期&结束日期&年份  指定年份的起止日期
	 * @param topMenu2  开始日期,结束日期    所有的数据的起止日期
	 * @param leftMenu  菜单ID&菜单名称   左边菜单
	 * @param width  生产图标的宽度
	 * @param height 生成图标的高度
	 * @param dataList
	 * @return
	 */
	public String TanpujihuaXML(String [] topMenu1,String[] topMenu2,String[] leftMenu,List<Tanpujihuaxinxi> dataList,int width,int height){
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='GB2312'?><chart dateFormat='yyyy-mm-dd'  showBorder='1'  borderColor='899FB6' ");// ganttWidthPercent='70' forceGanttWidthPercent='1' ganttPaneDuration='"+topMenu2.length+"'
		strXML.append(" ganttPaneDurationUnit='m' showTaskNames='1' paletteThemeColor='453269'  ganttLineColor='99CC00' gridBorderColor='99CC00' scrollColor='76EE00' scrollPadding='3' scrollHeight='18' scrollBtnWidth='28' scrollToEnd='1'>");
		strXML.append("<categories  bgColor='333333' fontColor='99cc00' isBold='1' fontSize='14' scrollStartX='50' >");
		for(int i=0;i<topMenu1.length;i++){
			String [] tempStr=topMenu1[i].split("&");
			strXML.append("<category start='"+tempStr[0]+"' end='"+tempStr[1]+"' name='"+tempStr[2]+"' isBold='1' fontSize='14' />");
		}
		strXML.append("</categories>");
		strXML.append("<categories bgColor='99cc00' bgAlpha='40' fontColor='333333' align='center' fontSize='10' isBold='1'>");
		
		if(dataList!=null&&dataList.size()>0){
		String startTime=topMenu2[0];
		String endTime=topMenu2[1];
		String[] startTimeArray=startTime.split("-");
		String[] endTimeArray=endTime.split("-");
		for(int i=0;i<topMenu1.length;i++){
			String [] tempStr=topMenu1[i].split("&");
			
			Integer tempYear=new Integer(tempStr[2]);//获取年份
			Integer towMonth=28;
			if(tempYear%4==0&&tempYear%100!=0||tempYear%400==0){
				towMonth=29;
			}else{
				towMonth=28;
			}
			
			int kaishiyuefen=1;//开始月份
			int jieshuyuefen=12;//结束月份
			
			if(i==0&&topMenu1.length>1){
				kaishiyuefen=new Integer(startTimeArray[1]);
				jieshuyuefen=12;
			}
			if(i==topMenu1.length-1){
				kaishiyuefen=1;
				jieshuyuefen=new Integer(endTimeArray[1]);
				if(i==0){
					kaishiyuefen=new Integer(startTimeArray[1]);
					jieshuyuefen=new Integer(endTimeArray[1]);
				}
			}
			for(int j=kaishiyuefen; j<=jieshuyuefen; j++){
				switch(j){
				case 1:strXML.append("<category start='"+tempYear+"/1/1' end='"+tempYear+"/1/31' name='1月' />");break;
				case 2:strXML.append("<category start='"+tempYear+"/2/1' end='"+tempYear+"/2/"+towMonth+"' name='2月' />");break;
				case 3:strXML.append("<category start='"+tempYear+"/3/1' end='"+tempYear+"/3/31' name='3月' />");break;
				case 4:strXML.append("<category start='"+tempYear+"/4/1' end='"+tempYear+"/4/30' name='4月' />");break;
				case 5:strXML.append("<category start='"+tempYear+"/5/1' end='"+tempYear+"/5/31' name='5月' />");break;
				case 6:strXML.append("<category start='"+tempYear+"/6/1' end='"+tempYear+"/6/30' name='6月' />");break;
				case 7:strXML.append("<category start='"+tempYear+"/7/1' end='"+tempYear+"/7/31' name='7月' />");break;
				case 8:strXML.append("<category start='"+tempYear+"/8/1' end='"+tempYear+"/8/31' name='8月' />");break;
				case 9:strXML.append("<category start='"+tempYear+"/9/1' end='"+tempYear+"/9/30' name='9月' />");break;
				case 10:strXML.append("<category start='"+tempYear+"/10/1' end='"+tempYear+"/10/31' name='10月' />");break;
				case 11:strXML.append("<category start='"+tempYear+"/11/1' end='"+tempYear+"/11/30' name='11月' />");break;
				case 12:strXML.append("<category start='"+tempYear+"/12/1' end='"+tempYear+"/12/31' name='12月' />");break;
				}
			}
		}
		strXML.append("</categories>");
		
		strXML.append("<processes positionInGrid='bottom' width='100' align='center' headerText=' "+leftMenu[0]+"  ' fontColor='7171C6' fontSize='11' isBold='1' isAnimated='1' bgColor='99cc00' headerbgColor='333333' headerFontColor='99CC00' headerFontSize='16' bgAlpha='60'>");
		//左边菜单
		for(int i=1;i<leftMenu.length;i++){
			String [] str= leftMenu[i].split("&");
			strXML.append("<process Name='"+str[1]+"' id='"+str[0]+"' />");
			//strXML.append("<process Name='"+leftMenu[i]+"' id='"+leftMenu[i]+"' />");
		}
		strXML.append("</processes>");
		strXML.append("<dataTable showProcessName='1' nameAlign='left' headerFontSize='16' headerFontIsBold='1'></dataTable>");
		strXML.append("<tasks>");
		for(Tanpujihuaxinxi tpjhxx : dataList) {
			if(tpjhxx.getJihuatanpucengmian().equals("上面层")){
				strXML.append("<task toolText='"+tpjhxx.getJihuatanpucengmian()+"…"+tpjhxx.getJihuastarttime()+"…"+tpjhxx.getJihuaendtime()+"…里程(km)："+tpjhxx.getJihuatanpulicheng()+"…产量(T)："+tpjhxx.getJihuashengchanliang()+"' name='里程:"+tpjhxx.getJihuatanpulicheng()+"km 产量:"+tpjhxx.getJihuashengchanliang()+"T' processId='"+tpjhxx.getBiaoduanid()+"' start='"+tpjhxx.getJihuastarttime()+"' end='"+tpjhxx.getJihuaendtime()+"' Id='"+tpjhxx.getBiaoduanid()+"' color='99CC00' height='15' topPadding='15%' sborderColor='99CC00'/>");
			}else if(tpjhxx.getJihuatanpucengmian().equals("中面层")){
				strXML.append("<task toolText='"+tpjhxx.getJihuatanpucengmian()+"…"+tpjhxx.getJihuastarttime()+"…"+tpjhxx.getJihuaendtime()+"…里程(km)："+tpjhxx.getJihuatanpulicheng()+"…产量(T)："+tpjhxx.getJihuashengchanliang()+"' name='里程:"+tpjhxx.getJihuatanpulicheng()+"km 产量:"+tpjhxx.getJihuashengchanliang()+"T' processId='"+tpjhxx.getBiaoduanid()+"' start='"+tpjhxx.getJihuastarttime()+"' end='"+tpjhxx.getJihuaendtime()+"' Id='"+tpjhxx.getBiaoduanid()+"' color='FF00FF' height='15' topPadding='45%' sborderColor='99CC00'/>");
			}else{
				strXML.append("<task toolText='"+tpjhxx.getJihuatanpucengmian()+"…"+tpjhxx.getJihuastarttime()+"…"+tpjhxx.getJihuaendtime()+"…里程(km)："+tpjhxx.getJihuatanpulicheng()+"…产量(T)："+tpjhxx.getJihuashengchanliang()+"' name='里程:"+tpjhxx.getJihuatanpulicheng()+"km 产量:"+tpjhxx.getJihuashengchanliang()+"T' processId='"+tpjhxx.getBiaoduanid()+"' start='"+tpjhxx.getJihuastarttime()+"' end='"+tpjhxx.getJihuaendtime()+"' Id='"+tpjhxx.getBiaoduanid()+"' color='FFFF00' height='15' topPadding='75%' sborderColor='99CC00'/>");
			}
		}
		strXML.append("</tasks>");
		}
		strXML.append("<legend>");
		strXML.append("<item label='上面层' color='99CC00' />");
		strXML.append("<item label='中面层' color='FF00FF' />");
		strXML.append("<item label='下面层' color='FFFF00' />");
		strXML.append("</legend>");
	
		strXML.append("</chart>");		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Gantt.swf", "",strXML.toString(), "NewChart", width, height, false, false);

	}
	
	/**
	 * 获取摊铺计划与实际生产量的比对XML
	 * @param biaoduanName
	 * @param tpjhxxList
	 * @param lqclDailyViewList
	 * @return
	 */
	public String TanpujihuaTongjiXML(String biaoduanName,List<Tanpujihuaxinxi> tpjhxxList,List<LiqingclDailyView> lqclDailyViewList){
		
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='GB2312'?><chart palette='1' xaxisname='时间' yaxisname='产量(T)' NumberSuffix='kg' numdivlines='10' caption='生产量比对("+biaoduanName+")'  formatNumberScale='0' baseFontColor='660066' baseFontSize ='14'>");
		
		strXML.append("<categories font='Arial' >");
		
		for(Tanpujihuaxinxi tpjhxxObj:tpjhxxList){
			strXML.append("<category label='"+tpjhxxObj.getJihuastarttime()+"*"+tpjhxxObj.getJihuaendtime()+"*里程:"+tpjhxxObj.getJihuatanpulicheng()+"km' toolText='"+tpjhxxObj.getJihuastarttime()+"*"+tpjhxxObj.getJihuaendtime()+"*里程:"+tpjhxxObj.getJihuatanpulicheng()+"km'/>");
		}
		
		strXML.append(" </categories>");
		
		strXML.append("<dataset seriesname='计划产量' color='8BBA00' >");
		for(Tanpujihuaxinxi tpjhxxObj:tpjhxxList){
			strXML.append("<set value='"+tpjhxxObj.getJihuashengchanliang()+"' toolText='"+tpjhxxObj.getJihuastarttime()+"*"+tpjhxxObj.getJihuaendtime()+"*里程:"+tpjhxxObj.getJihuatanpulicheng()+"km*产量:"+tpjhxxObj.getJihuashengchanliang()+"kg' />");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesname='实际产量' color='A66EDD' >");
		int i=0;
		for(LiqingclDailyView lqclDailyViewObj:lqclDailyViewList){
			Tanpujihuaxinxi tpjhxxObj=tpjhxxList.get(i);
			strXML.append("<set value='"+lqclDailyViewObj.getDailycl()+"' toolText='"+tpjhxxObj.getJihuastarttime()+"*"+tpjhxxObj.getJihuaendtime()+"*产量:"+lqclDailyViewObj.getDailycl()+"kg'/>");
			i++;
		}
		strXML.append("</dataset>");
		strXML.append("</chart>");
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSColumn3D.swf", "",strXML.toString(), "Tanpujihuaxinxibidui", 1024, 500, false, false);
	}
	
	//水稳
	public String getswcailiaoyongliangXml(String sessionid, String shebeibianhao, String proname, int bsid) {		
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/RealTimeColumn.swf", "",swbanhecailiaoXml(sessionid, shebeibianhao, proname, bsid), "swbanhecailiaochart", 1000, 500, false, false);
	}
	
	public List<TopRealMaxShuiwenxixxView> findTopSwView(String propertyName, int bsid) {
		if (propertyName.equalsIgnoreCase("all")) {
			propertyName = "biaoduanid";
		}
		return topswDAO.findByProperty(propertyName, bsid);
	}
	
	public List<TopRealMaxLiqingView> findTopLqView(String propertyName, int bsid) {
		if (propertyName.equalsIgnoreCase("all")) {
			propertyName = "biaoduanid";
		}
		return topreallqviewDAO.findByProperty(propertyName, bsid);
	}
	
	public TopRealMaxShuiwenxixxView findswbyshebeibianhao(String shebeibianhao) {
		return topswDAO.findByGprsbianhao(shebeibianhao);
	}
	
	public String swbanhecailiaoXml(String sessionid, String shebeibianhao, String proname, int bsid) {
		if (0 == StringUtil.Null2Blank(shebeibianhao).length()) {
			if (proname.equalsIgnoreCase("all")) {
				proname = "biaoduanid";
			}
			List results = findbysql("select top 1 shebeibianhao from TopRealMaxShuiwenxixxView where "+proname+"="+bsid+" order by bianhao DESC");
			if (results.size()>0) {
	            shebeibianhao = (String)results.get(0);  
			}	
		}		
		TopRealMaxShuiwenxixxView tview = findswbyshebeibianhao(shebeibianhao);
		if (null != tview) {
			delSessionUpdateTime(sessionid, shebeibianhao, swcl);			
			StringBuilder strXML = new StringBuilder("");  	        
	        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='");
	        strXML.append(tview.getBanhezhanminchen());
	        strXML.append("水稳材料用量实时监控(单位:kg)' dataStreamURL='");
	        strXML.append(StringUtil.getWebrootpath());
	        strXML.append("/getdata/swcailiaoreal?shebeibianhao=");
	        strXML.append(shebeibianhao);
	        strXML.append("' borderColor='899FB6' canvasborderColor='DAE1E8'"); 
	        strXML.append(" canvasBgColor='FFFFFF' bgColor='EEF2FB' numDivLines='6' divLineColor='DAE1E8'"); 
	        strXML.append(" divLineAlpha='75' alternateHGridAlpha='30' baseFontColor='899FB6'"); 
	        strXML.append(" outCnvBaseFontColor='444C60' toolTipBorderColor='DAE1E8' numberSuffix=''"); 
	        strXML.append(" toolTipBgColor='FFFFFF' toolTipSepChar=' ' showAlternateHGridColor='1'"); 
	        strXML.append(" alternateHGridColor='DAE1E8' refreshInterval='10' numDisplaySets='3' showLegend='1' showLabels='1'"); 
	        strXML.append(" showRealTimeValue='0' labelDisplay='none' showShadow='1' showPlotBorder='0'"); 
	        strXML.append(" plotBordercolor='FFFFFF' plotGradientColor='' formatNumberScale='0'"); 
	        strXML.append(" canvasLeftMargin='50' yAxisMaxValue='1000' showToolTip='1'>"); 
	        strXML.append(" <categories></categories>"); 
			ShuiwenziduancfgView swisshow=queryService.getSwzstcfgisShow(shebeibianhao);
			ShuiwenziduancfgView swfield=queryService.getSwfield(shebeibianhao);
	        if(swfield!=null && swisshow!=null){
	        	if(StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjgl1(),strXML);	        		
	        	}        	
	        	if(StringUtil.Null2Blank(swisshow.getLlgl1()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if(StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjgl2(),strXML);
	        	}	        	
	        	if(StringUtil.Null2Blank(swisshow.getLlgl2()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if(StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjgl3(),strXML);
	        	}	        	
	        	if(StringUtil.Null2Blank(swisshow.getLlgl3()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if(StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjgl4(),strXML);
	        	}	        	
	        	if(StringUtil.Null2Blank(swisshow.getLlgl4()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if(StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjgl5(),strXML);
	        	}	        	
	        	if(StringUtil.Null2Blank(swisshow.getLlgl5()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}
	        	appendBlankDateSet(strXML);
	        	
	        	if(StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjfl1(),strXML);
	        	}	        	
	        	if(StringUtil.Null2Blank(swisshow.getLlfl1()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}	        	
	        	appendBlankDateSet(strXML);
	        	
	        	if(StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjfl2(),strXML);
	        	}	        	
	        	if(StringUtil.Null2Blank(swisshow.getLlfl2()).equalsIgnoreCase("1")){
	        		appendLlDataSet("",strXML);
	        	}
	        	appendBlankDateSet(strXML);
	        	
	        	//水
	        	if(StringUtil.Null2Blank(swisshow.getSjshui()).equalsIgnoreCase("1")){
	        		appendDataSet(swfield.getSjshui(),strXML);
	        	}
	        	if(StringUtil.Null2Blank(swisshow.getLlshui()).equalsIgnoreCase("1")){
	        		appendLlDataSet("理论值",strXML);
	        	}
	        }
	        strXML.append(" <styles>"); 
	        strXML.append(" <definition>"); 
	        strXML.append(" <style type='font' name='captionFont' size='12' color='899FB6'/>"); 
	        strXML.append(" <style type='font' name='subcaptionFont' color='899FB6'/>"); 
	        strXML.append(" </definition>"); 
	        strXML.append(" <application>"); 
	        strXML.append(" <apply toObject='Caption' styles='captionFont' />"); 
	        strXML.append(" <apply toObject='SubCaption' styles='subcaptionFont' />"); 
	        strXML.append(" </application>"); 
	        strXML.append(" </styles>"); 
	        strXML.append(" </chart>"); 
			return strXML.toString();		
		} else {
			return "";
		}		
	}
	
	private void appendLlDataSet(String seriesName, StringBuilder strXML) {
		strXML.append(" <dataSet seriesName='"); 
		strXML.append(seriesName);
		strXML.append("' color='0099FF' showValues='0'></dataSet>");
	}
	
	public String getswbhzXml(int xmltype, String fieldname, int bsid) {
		ArrayList<TopRealMaxShuiwenxixxView> topviewlist = (ArrayList<TopRealMaxShuiwenxixxView>) findTopSwView(fieldname, bsid);
		if (topviewlist.size() == 0) {
			return "";
		}
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='水稳拌和站一览表(红色代表正在生产，蓝色代表未生产)' bgColor='FFFFFF'");
        strXML.append(" canvasBorderColor='333333' numdivlines='0' decimals='1' xAxisMinValue='0'"); 
        strXML.append(" xAxisMaxValue='32' bubbleScale='2' showLimits='0' yAxisMaxValue='20'"); 
        strXML.append(" chartRightMargin='0' showPlotBorder='0' plotFillAlpha='80' showBorder='0'>"); 
        strXML.append(" <categories verticalLineColor='666666' verticalLineAlpha='20'>"); 
        strXML.append(" <category name=' ' x='2' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='4' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='6' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='8' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='10' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='12' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='14' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='16' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='18' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='20' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='22' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='24' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='26' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='28' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='30' showVerticalLine='1'/>"); 
        strXML.append(" </categories>"); 
        strXML.append(" <dataset showValues='0'>"); 
		if (fieldname.equalsIgnoreCase("all")) {
			fieldname = "biaoduanid";
		}
        
        int i = 1;
        int y = 10;
        if (topviewlist.size() > 16) {
			y = 15;
		}
        for (TopRealMaxShuiwenxixxView topswView : topviewlist) {
        	if (i > 31) {
				i = 1;
				y = 5;
			}
        	strXML.append("<set z='1' x='");
        	strXML.append(i);
        	strXML.append("' y='");
        	strXML.append(y);
        	strXML.append("' name='r");
        	strXML.append(i);
        	strXML.append("' showValue='1' displayValue='");
        	strXML.append(topswView.getJianchen());
        	String bcsj = topswView.getShijianE();
        	if (StringUtil.Null2Blank(bcsj).length()>0) {
        		strXML.append("' toolText='最新出料时间：");
			} else {
				strXML.append("' toolText='最新保存时间：");
				bcsj = topswView.getBaocunshijian();
			}
        	
        	strXML.append(bcsj);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Calendar day=Calendar.getInstance(); 
        	Date baocun;     	
			try {				
				baocun = sdf.parse(topswView.getBaocunshijian());
				day.add(Calendar.MINUTE, -30);
	        	if (baocun.after(day.getTime())) {
	        		strXML.append("' color='ff5904");
	        	}
			} catch (ParseException e) {
			}
        	
            switch (xmltype) {
			case 1:
				strXML.append("' link='");
				strXML.append(StringUtil.getWebrootpath());
				strXML.append("/query/swcllist?pid=5&shebeibianhao=");
				strXML.append(topswView.getShebeibianhao());
				strXML.append("'/>");
				break;
			case 2:
				strXML.append("' link='JavaScript:myJS(&quot;");
	        	strXML.append(topswView.getShebeibianhao());
	        	strXML.append("&quot;)'/>");
				break;
			default:
				break;
			}    	
        	i++;
        	i++;
		}

        strXML.append("</dataset>");   
        strXML.append("<styles>");
        strXML.append("<definition>");
        strXML.append("<style name='myCaptionFont' type='font' size='12'/>");
        strXML.append("</definition>");
        strXML.append("<application>");
        strXML.append("<apply toObject='Caption' styles='myCaptionFont' />");
        strXML.append("</application>");
        strXML.append("</styles>");
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "swbhzchart", 900, 150, false, false);
	}
	
	public String getswchaobiaobhzXml(int xmltype, String fieldname, int bsid) {
		ArrayList<TopRealMaxShuiwenxixxView> topviewlist = (ArrayList<TopRealMaxShuiwenxixxView>) findTopSwView(fieldname, bsid);
		if (topviewlist.size() == 0) {
			return "";
		}
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='水稳拌和站一览表(红色代表正在生产，蓝色代表未生产)' bgColor='FFFFFF'");
        strXML.append(" canvasBorderColor='333333' numdivlines='0' decimals='1' xAxisMinValue='0'"); 
        strXML.append(" xAxisMaxValue='32' bubbleScale='2' showLimits='0' yAxisMaxValue='20'"); 
        strXML.append(" chartRightMargin='0' showPlotBorder='0' plotFillAlpha='80' showBorder='0'>"); 
        strXML.append(" <categories verticalLineColor='666666' verticalLineAlpha='20'>"); 
        strXML.append(" <category name=' ' x='2' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='4' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='6' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='8' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='10' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='12' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='14' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='16' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='18' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='20' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='22' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='24' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='26' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='28' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='30' showVerticalLine='1'/>"); 
        strXML.append(" </categories>"); 
        strXML.append(" <dataset showValues='0'>"); 
		if (fieldname.equalsIgnoreCase("all")) {
			fieldname = "biaoduanid";
		}
        
        int i = 1;
        int y = 10;
        if (topviewlist.size() > 16) {
			y = 15;
		}
        for (TopRealMaxShuiwenxixxView topswView : topviewlist) {
        	if (i > 31) {
				i = 1;
				y = 5;
			}
        	strXML.append("<set z='1' x='");
        	strXML.append(i);
        	strXML.append("' y='");
        	strXML.append(y);
        	strXML.append("' name='r");
        	strXML.append(i);
        	strXML.append("' showValue='1' displayValue='");
        	strXML.append(topswView.getJianchen());
        	String bcsj = topswView.getShijianE();
        	if (StringUtil.Null2Blank(bcsj).length()>0) {
        		strXML.append("' toolText='最新出料时间：");
			} else {
				strXML.append("' toolText='最新保存时间：");
				bcsj = topswView.getBaocunshijian();
			}
        	
        	strXML.append(bcsj);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Calendar day=Calendar.getInstance(); 
        	Date baocun;     	
			try {				
				baocun = sdf.parse(topswView.getBaocunshijian());
				day.add(Calendar.MINUTE, -30);
	        	if (baocun.after(day.getTime())) {
	        		strXML.append("' color='ff5904");
	        	}
			} catch (ParseException e) {
			}
        	
            switch (xmltype) {
			case 1:
				strXML.append("' link='");
				strXML.append(StringUtil.getWebrootpath());
				strXML.append("/query/swcllist?pid=5&shebeibianhao=");
				strXML.append(topswView.getShebeibianhao());
				strXML.append("'/>");
				break;
			case 2:
				strXML.append("' link='JavaScript:myJS(&quot;");
	        	strXML.append(topswView.getShebeibianhao());
	        	strXML.append("&quot;)'/>");
				break;
			default:
				break;
			}    	
        	i++;
        	i++;
		}

        strXML.append("</dataset>");   
        strXML.append("<styles>");
        strXML.append("<definition>");
        strXML.append("<style name='myCaptionFont' type='font' size='12'/>");
        strXML.append("</definition>");
        strXML.append("<application>");
        strXML.append("<apply toObject='Caption' styles='myCaptionFont' />");
        strXML.append("</application>");
        strXML.append("</styles>");
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "swbhzchart", 900, 150, false, false);
	}
	
	public String getlqchaobiaobhzXml(int xmltype, String fieldname, int bsid) {
		ArrayList<TopRealMaxLiqingView> topviewlist = (ArrayList<TopRealMaxLiqingView>) findTopLqView(fieldname, bsid);
		if (topviewlist.size() == 0) {
			return "";
		}
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='沥青拌和站一览表(红色代表正在生产，蓝色代表未生产)' bgColor='FFFFFF'");
        strXML.append(" canvasBorderColor='333333' numdivlines='0' decimals='1' xAxisMinValue='0'"); 
        strXML.append(" xAxisMaxValue='32' bubbleScale='2' showLimits='0' yAxisMaxValue='20'"); 
        strXML.append(" chartRightMargin='0' showPlotBorder='0' plotFillAlpha='80' showBorder='0'>"); 
        strXML.append(" <categories verticalLineColor='666666' verticalLineAlpha='20'>"); 
        strXML.append(" <category name=' ' x='2' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='4' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='6' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='8' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='10' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='12' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='14' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='16' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='18' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='20' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='22' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='24' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='26' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='28' showVerticalLine='1'/>"); 
        strXML.append(" <category name=' ' x='30' showVerticalLine='1'/>"); 
        strXML.append(" </categories>"); 
        strXML.append(" <dataset showValues='0'>"); 
		if (fieldname.equalsIgnoreCase("all")) {
			fieldname = "biaoduanid";
		}
        
        int i = 1;
        int y = 10;
        if (topviewlist.size() > 16) {
			y = 15;
		}
        for (TopRealMaxLiqingView toplqView : topviewlist) {
        	if (i > 31) {
				i = 1;
				y = 5;
			}
        	strXML.append("<set z='1' x='");
        	strXML.append(i);
        	strXML.append("' y='");
        	strXML.append(y);
        	strXML.append("' name='r");
        	strXML.append(i);
        	strXML.append("' showValue='1' displayValue='");
        	strXML.append(toplqView.getJianchen());
        	String bcsj = toplqView.getShijian();
        	if (StringUtil.Null2Blank(bcsj).length()>0) {
        		strXML.append("' toolText='最新出料时间：");
			} else {
				strXML.append("' toolText='最新保存时间：");
				bcsj = toplqView.getBaocunshijian();
			}
        	
        	strXML.append(bcsj);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Calendar day=Calendar.getInstance(); 
        	Date baocun;     	
			try {				
				baocun = sdf.parse(toplqView.getBaocunshijian());
				day.add(Calendar.MINUTE, -30);
	        	if (baocun.after(day.getTime())) {
	        		strXML.append("' color='ff5904");
	        	}
			} catch (ParseException e) {
			}
        	
            switch (xmltype) {
			case 1:
				strXML.append("' link='");
				strXML.append(StringUtil.getWebrootpath());
				strXML.append("/query/lqcllist?pid=5&shebeibianhao=");
				strXML.append(toplqView.getShebeibianhao());
				strXML.append("'/>");
				break;
			case 2:
				strXML.append("' link='JavaScript:myJS(&quot;");
	        	strXML.append(toplqView.getShebeibianhao());
	        	strXML.append("&quot;)'/>");
				break;
			default:
				break;
			}    	
        	i++;
        	i++;
		}

        strXML.append("</dataset>");   
        strXML.append("<styles>");
        strXML.append("<definition>");
        strXML.append("<style name='myCaptionFont' type='font' size='12'/>");
        strXML.append("</definition>");
        strXML.append("<application>");
        strXML.append("<apply toObject='Caption' styles='myCaptionFont' />");
        strXML.append("</application>");
        strXML.append("</styles>");
        strXML.append("</chart>"); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "lqbhzchart2", 900, 150, false, false);
	}
	
	public String updateswcailiaorealvalue(String sessionid, String shebeibianhao) {
		TopRealMaxShuiwenxixxView topview = findswbyshebeibianhao(shebeibianhao);
		StringBuilder result = new StringBuilder(""); 
		if (null != topview) {  
			String bcsj = topview.getShijianS();
			if (StringUtil.Null2Blank(bcsj).length()==0) {
				bcsj = topview.getBaocunshijian();
			}
            if (updateSessionUpdateTime(sessionid, shebeibianhao, bcsj, swcl)) {
            	ShuiwenziduancfgView swisshow=queryService.getSwzstcfgisShow(shebeibianhao);
            	if(swisshow!=null){
        			result.append("&label=");
        			result.append(bcsj);
        			result.append("&value=");
        			if(StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjgl1())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlgl1()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlgl1())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
	    			result.append("");
	  				result.append("|"); 
        			
        			if(StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjgl2())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlgl2()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlgl2())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
	    			result.append("");
	  				result.append("|"); 
        			
        			if(StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjgl3())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlgl3()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlgl3())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
	    			result.append("");
	  				result.append("|"); 
        			
        			if(StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjgl4())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlgl4()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlgl4())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
	    			result.append("");
	  				result.append("|"); 
        			
        			if(StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjgl5())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlgl5()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlgl5())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
	    			result.append("");
	  				result.append("|"); 
        			
        			if(StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjfl1())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlfl1()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlfl1())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
	    			result.append("");
	  				result.append("|"); 
        			
        			if(StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjfl2())));
        				result.append("|");
        			}      			
        			if(StringUtil.Null2Blank(swisshow.getLlfl2()).equalsIgnoreCase("1")){
        				result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlfl2())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
        			}
        			
        			result.append("");
	  				result.append("|"); 
	  				
	  				if(StringUtil.Null2Blank(swisshow.getSjshui()).equalsIgnoreCase("1")){
	  					result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getSjshui())));
        				result.append("|");
	  				}
	  				if(StringUtil.Null2Blank(swisshow.getLlshui()).equalsIgnoreCase("1")){
	  					result.append(String.format("%1$.1f", StringUtil.StrToZero(topview.getLlshui())/100*StringUtil.StrToFloat(topview.getGlchangliang())));
        				result.append("|");
	  				}
            	}
				
    			if (logger.isDebugEnabled()) {
    				logger.debug(result);
    			}
			}            
		}		
		return result.toString();			
	}

	
	public void appendSetXml(StringBuilder strb, String value) {
		float tf = -1;
		tf = StringUtil.StrToFloat(value);
		if (tf >= 0) {
			strb.append("<set value='");
			strb.append(tf);
			strb.append("'/>");
		} else {
			strb.append("<set />");
		}		
	}
	
	/**************************水稳**************************/
	private String createShuiwenxml(int xmltype, Integer biaoduan,String fieldname,int bsid) {
		StringBuilder strXML = new StringBuilder(""); 
		List<TopRealMaxShuiwenxixxView> topviewlist=null;
		if (fieldname.equalsIgnoreCase("all")) {
			if (null != biaoduan) {
				topviewlist = topswDAO.find("from TopRealMaxShuiwenxixxView where biaoduanid=? order by bianhao desc", biaoduan);
			} else {
				topviewlist = topswDAO.find("from TopRealMaxShuiwenxixxView where biaoduanid=1 order by bianhao desc");
			}
			
		} else {
			if (null != biaoduan) {
				topviewlist = topswDAO.find("from TopRealMaxShuiwenxixxView where biaoduanid=? and "+fieldname+"=? order by bianhao desc", biaoduan,bsid);
			} else {
				topviewlist = topswDAO.find("from TopRealMaxShuiwenxixxView where "+fieldname+"=? order by bianhao desc", bsid);
			}
		}
		if(topviewlist!=null && topviewlist.size()>0){
			strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='水稳拌和站一览表(红色代表正在生产，蓝色代表未生产(30分钟内))' bgColor='FFFFFF'");
	        strXML.append(" canvasBorderColor='333333' numdivlines='0' decimals='1' xAxisMinValue='0'"); 
	        strXML.append(" xAxisMaxValue='20' bubbleScale='2' showLimits='0' yAxisMaxValue='20'"); 
	        strXML.append(" chartRightMargin='0' showPlotBorder='0' plotFillAlpha='80' showBorder='0'>"); 
	        strXML.append(" <categories verticalLineColor='666666' verticalLineAlpha='20'>"); 
	        strXML.append(" <category name=' ' x='2' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='4' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='6' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='8' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='10' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='12' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='14' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='16' showVerticalLine='1'/>"); 
	        strXML.append(" <category name=' ' x='18' showVerticalLine='1'/>"); 
	        strXML.append(" </categories>"); 
	        strXML.append(" <dataset showValues='0'>"); 
			
	        int i = 1;
	        int y = 10;
	        if (topviewlist.size() > 10) {
				y = 15;
			}
	        for (TopRealMaxShuiwenxixxView toplqView : topviewlist) {
	        	if (i > 19) {
					i = 1;
					y = 5;
				}
	        	
	        	strXML.append("<set z='1' x='");
	        	strXML.append(i);
	        	strXML.append("' y='");
	        	strXML.append(y);
	        	strXML.append("' name='r");
	        	strXML.append(i);
	        	strXML.append("' showValue='1' displayValue='");
	        	strXML.append(toplqView.getJianchen());
	        	String bcsj = toplqView.getShijianS();
	        	if (StringUtil.Null2Blank(bcsj).length()>0) {
	        		strXML.append("' toolText='最新出料时间：");
				} else {
					strXML.append("' toolText='最新保存时间：");
					bcsj = toplqView.getBaocunshijian();
				}
	        	
	        	strXML.append(bcsj);
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	Calendar day=Calendar.getInstance(); 
	        	Date baocun;     	
				try {				
					baocun = sdf.parse(toplqView.getBaocunshijian());
		        	day.add(Calendar.MINUTE, -30);
		        	if (baocun.after(day.getTime())) {
		        		strXML.append("' color='ff5904");
		        	}
		        	
				} catch (ParseException e) {
				}
	        	
	            switch (xmltype) {
				case 1:
					strXML.append("' link='");
					strXML.append(StringUtil.getWebrootpath());
					strXML.append("/query/querylist?pid=6&record=34&method=swlist&biaoduan="+biaoduan+"&shebeibianhao=");
					strXML.append(toplqView.getShebeibianhao());
					strXML.append("'/>");
					break;
				case 2:
					strXML.append("' link='JavaScript:myJS(&quot;");
		        	strXML.append(toplqView.getShebeibianhao());
		        	strXML.append("&quot;)'/>");
					break;
				default:
					break;
				}    	
	        	i++;
	        	i++;
			}

	        strXML.append("</dataset>");   
	        strXML.append("<styles>");
	        strXML.append("<definition>");
	        strXML.append("<style name='myCaptionFont' type='font' size='12'/>");
	        strXML.append("</definition>");
	        strXML.append("<application>");
	        strXML.append("<apply toObject='Caption' styles='myCaptionFont' />");
	        strXML.append("</application>");
	        strXML.append("</styles>");
	        strXML.append("</chart>");
		}
		return strXML.toString();
	}
	
	public String mainlistgetShuiwenXml(int xmltype, Integer biaoduan,String fieldname, int bsid) {
		String strXML = createShuiwenxml(xmltype,biaoduan,fieldname, bsid); 
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/Bubble.swf", "",strXML.toString(), "swbhzchart", 480, 150, false, false);
	}
	
	private String mainlistcreateswzhfxxml(List<ShuiwenxixxView> swviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?>");
        strXML.append("<chart palette='2' caption='最近生产情况' "); 
        strXML.append(" clickURL='"); 
        strXML.append(StringUtil.getWebrootpath()); 
        strXML.append("/query/swcnfx?pid=7&record=50&'"); 
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
        
        strXML.append("<dataset lineThickness='3' seriesName='高级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView lqv : swviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getBiaoshi());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getBiaoshi());
        	strXML.append(",");
        	strXML.append(lqv.getBeizhu());      	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");   
        
        strXML.append("<dataset lineThickness='3' seriesName='中级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView lqv : swviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getPmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getPmend());
        	strXML.append(",");
        	strXML.append(lqv.getPmbegin());       	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='初级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView lqv : swviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getAmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getAmend());
        	strXML.append(",");
        	strXML.append(lqv.getAmbegin());      	
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
	
	//水稳产能分析
	public String getSwAnalysisColumnXml(List<ShuiwenxixxView> lqviews, int cnfxlx) {
		StringBuilder strXML = new StringBuilder("");  
        strXML.append("<?xml version='1.0' encoding='utf-8'?><chart palette='2' ");
        strXML.append("maxColWidth='70' rotateYAxisName='0' caption='水稳拌和站产能分析(t)' ");  
        strXML.append("startingAngle='125' shownames='1' showvalues='0' ");
        strXML.append("formatNumberScale='0'>");
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
			case 5:
				sxa = "日";
				sxb = "时";
				break;
			default:
				sxb = "月份";
				break;
		}
        strXML.append("<categories>");
        for (ShuiwenxixxView lq : lqviews) {
        	strXML.append("<category label='"); 
        	strXML.append(lq.getSmstype());
        	strXML.append(sxa);
        	strXML.append(lq.getSendtype());
        	strXML.append(sxb);
            strXML.append("'/>"); 
        }
        strXML.append("</categories>");      
        strXML.append("<dataset seriesName='生产量(t)'  showValues='0'>");
        for (ShuiwenxixxView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(String.format("%1$.2f",Double.valueOf(lqv.getGlchangliang())/1000));
        	strXML.append("' displayValue='");
        	strXML.append(String.format("%1$.2f",Double.valueOf(lqv.getGlchangliang())/1000));
        	strXML.append(",");
        	strXML.append(lqv.getPanshu());
        	strXML.append("' link='JavaScript:myJS(");
        	strXML.append(lqv.getSmstype());
        	strXML.append(",");
        	strXML.append(lqv.getSendtype());
           	strXML.append(",");
        	strXML.append(cnfxlx);
        	strXML.append(")'/>");
        }
        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='高级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getBiaoshi());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getBiaoshi());
        	strXML.append(",");
        	strXML.append(lqv.getBeizhu());      	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");   
        
        strXML.append("<dataset lineThickness='3' seriesName='中级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getPmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getPmend());
        	strXML.append(",");
        	strXML.append(lqv.getPmbegin());       	
        	strXML.append("'/>");
        }
        strXML.append("</dataset>");
        strXML.append("<dataset lineThickness='3' seriesName='初级预警率(%)' renderAs='Line' parentYAxis='S' showValues='0'>");
        for (ShuiwenxixxView lqv : lqviews) {
        	strXML.append("<set value='"); 
        	strXML.append(lqv.getAmend());
        	strXML.append("' displayValue='");
        	strXML.append(lqv.getAmend());
        	strXML.append(",");
        	strXML.append(lqv.getAmbegin());      	
        	strXML.append("'/>");
        }
         
        strXML.append("</dataset>"); 
        strXML.append("</chart>");
        return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSCombiDY2D.swf", "",strXML.toString(), "LqAnalysisChart", 1000, 300, false, false);
	}
	
	//水稳级配曲线图
			public String chartJipeipic(ShaifenjieguoView sfjieguo,Shaifenziduancfg swsfsmslow,Shaifenziduancfg swsfsmshigh){
				String buwei="";
				Shuiwenxixxlilun swlilun=swllService.getBeanById(sfjieguo.getLlid());
				if(swlilun!=null){
					buwei=swlilun.getLlbuwei();
				}
				StringBuilder strXML = new StringBuilder("");
				strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='"+StringUtil.Null2Blank(buwei)+"合成级配曲线图' subcaption='");
				strXML.append("' lineThickness='2' showValues='0' anchorRadius='2' ");
				strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
				strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
				strXML.append("labelStep='");
				strXML.append(1);
				strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
				strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='' yAxisName='各筛分通过质量百分率(%)'> ");
				strXML.append("<categories>");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					strXML.append("<category label='0.075'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					strXML.append("<category label='0.15'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					strXML.append("<category label='0.3'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					strXML.append("<category label='0.6'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					strXML.append("<category label='1.18'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					strXML.append("<category label='2.36'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					strXML.append("<category label='4.75'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					strXML.append("<category label='9.5'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					strXML.append("<category label='13.2'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					strXML.append("<category label='16'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					strXML.append("<category label='19'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					strXML.append("<category label='26.5'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					strXML.append("<category label='31.5'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					strXML.append("<category label='37.5'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					strXML.append("<category label='53'/>");
				}
				strXML.append("</categories>");
			
				strXML.append("<dataset seriesName='-允许波动上限-' Color='#FF0000' anchorRadius='0' lineThickness='0.1'>");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper1()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper2()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper3()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper4()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper5()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper6()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper7()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper8()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper9()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper10()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper11()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper12()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper13()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper14()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMaxpassper15()+"'/>");
				}
				strXML.append("</dataset>");
				
				strXML.append("<dataset seriesName='-允许波动下限-' Color='#FF0000' anchorRadius='0' lineThickness='0.1'> ");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper1()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper2()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper3()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper4()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper5()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper6()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper7()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper8()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper9()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper10()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper11()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper12()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper13()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper14()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					strXML.append("<set value='"+sfjieguo.getMinpassper15()+"'/>");
				}
				strXML.append("</dataset>");
				
				strXML.append("<dataset seriesName='-标准级配-' color='#00EC00'>");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper1()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper2()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper3()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper4()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper5()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper6()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper7()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper8()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper9()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper10()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper11()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper12()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper13()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper14()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					strXML.append("<set value='"+sfjieguo.getStandPassper15()+"'/>");
				}
				strXML.append("</dataset>");
				
				strXML.append("<dataset seriesName='-实际级配-' color='#0000E3'>");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper1()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper2()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper3()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper4()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper5()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper6()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper7()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper8()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper9()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper10()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper11()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper12()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper13()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper14()+"'/>");
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					strXML.append("<set value='"+sfjieguo.getPassper15()+"'/>");
				}
				strXML.append("</dataset>");
				//预警上限(标准级配+预警误差值上限)
				strXML.append("<dataset seriesName='-预警上限-' color='#FFFF00' anchorRadius='0' dashed='1'>");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper1())+Double.valueOf(swsfsmshigh.getPassper1());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper2())+Double.valueOf(swsfsmshigh.getPassper2());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper3())+Double.valueOf(swsfsmshigh.getPassper3());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper4())+Double.valueOf(swsfsmshigh.getPassper4());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper5())+Double.valueOf(swsfsmshigh.getPassper5());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper6())+Double.valueOf(swsfsmshigh.getPassper6());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper7())+Double.valueOf(swsfsmshigh.getPassper7());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper8())+Double.valueOf(swsfsmshigh.getPassper8());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper9())+Double.valueOf(swsfsmshigh.getPassper9());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper10())+Double.valueOf(swsfsmshigh.getPassper10());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper11())+Double.valueOf(swsfsmshigh.getPassper11());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper12())+Double.valueOf(swsfsmshigh.getPassper12());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper13())+Double.valueOf(swsfsmshigh.getPassper13());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper14())+Double.valueOf(swsfsmshigh.getPassper14());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					try {
						Double yjsx=Double.valueOf(sfjieguo.getStandPassper15())+Double.valueOf(swsfsmshigh.getPassper15());
						strXML.append("<set value='"+yjsx+"'/>");
					}catch(Exception e){}
				}
				strXML.append("</dataset>");
				//预警下限
				strXML.append("<dataset seriesName='-预警下限-' color='#FFFF00' anchorRadius='0' dashed='1'>");
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper1())+Double.valueOf(swsfsmslow.getPassper1());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper2())+Double.valueOf(swsfsmslow.getPassper2());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper3())+Double.valueOf(swsfsmslow.getPassper3());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper4())+Double.valueOf(swsfsmslow.getPassper4());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper5())+Double.valueOf(swsfsmslow.getPassper5());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper6())+Double.valueOf(swsfsmslow.getPassper6());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper7())+Double.valueOf(swsfsmslow.getPassper7());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper8())+Double.valueOf(swsfsmslow.getPassper8());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper9())+Double.valueOf(swsfsmslow.getPassper9());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper10())+Double.valueOf(swsfsmslow.getPassper10());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper11())+Double.valueOf(swsfsmslow.getPassper11());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper12())+Double.valueOf(swsfsmslow.getPassper12());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper13())+Double.valueOf(swsfsmslow.getPassper13());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper14()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper14())+Double.valueOf(swsfsmslow.getPassper14());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
				}
				if(StringUtil.Null2Blank(sfjieguo.getStandPassper15()).length()>0){
					try {
						Double yjxx=Double.valueOf(sfjieguo.getStandPassper15())+Double.valueOf(swsfsmslow.getPassper15());
						strXML.append("<set value='"+yjxx+"'/>");
					}catch(Exception e){}
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
				return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartshaifenjipei", 650, 400, false, false);
			}
		
			//级配曲线下方的表格
			public String chartJipeiTable(ShaifenjieguoView sfjieguo,Shaifenziduancfg swsfsmslow,Shaifenziduancfg swsfsmshigh){
				Double wucha1 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper1()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper1()));
				Double wucha2 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper4()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper4()));
				Double wucha3 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper6()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper6()));
				Double wucha4 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper7()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper7()));
				Double wucha5 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper8()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper8()));
				Double wucha6 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper11()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper11()));
				Double wucha7 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper12()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper12()));
				Double wucha8 = Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper13()))-Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper13()));
				String [] wc = new String [8];
				Double [] wucha = {wucha1,wucha2,wucha3,wucha4,wucha5,wucha6,wucha7,wucha8};
				for(int i=0;i<8;i++){
					try {
						wc[i] = String.format("%1$.2f",Double.valueOf(wucha[i]));
					}catch(Exception e){}
				}
				String htmlStr="";
				String [] wc_color = new String [8];
				//String b="";
				//给误差（超过预警上下限）加上红色
				if(Math.abs(Double.valueOf(wc[0]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper1()))){
					wc_color[0]="<font color='red'>"+wc[0]+"</font>";
				}else{
					wc_color[0]=wc[0];
				}
				if(Math.abs(Double.valueOf(wc[1]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper4()))){
					wc_color[1]="<font color='red'>"+wc[1]+"</font>";
				}else{
					wc_color[1]=wc[1];
				}
				if(Math.abs(Double.valueOf(wc[2]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper6()))){
					wc_color[2]="<font color='red'>"+wc[2]+"</font>";
				}else{
					wc_color[2]=wc[2];
				}
				if(Math.abs(Double.valueOf(wc[3]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper7()))){
					wc_color[3]="<font color='red'>"+wc[3]+"</font>";
				}else{
					wc_color[3]=wc[3];
				}
				if(Math.abs(Double.valueOf(wc[4]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper8()))){
					wc_color[4]="<font color='red'>"+wc[4]+"</font>";
				}else{
					wc_color[4]=wc[4];
				}
				if(Math.abs(Double.valueOf(wc[5]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper11()))){
					wc_color[5]="<font color='red'>"+wc[5]+"</font>";
				}else{
					wc_color[5]=wc[5];
				}
				if(Math.abs(Double.valueOf(wc[6]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper12()))){
					wc_color[6]="<font color='red'>"+wc[6]+"</font>";
				}else{
					wc_color[6]=wc[6];
				}
				if(Math.abs(Double.valueOf(wc[7]))>Double.valueOf(StringUtil.Null2Zero(swsfsmshigh.getPassper13()))){
					wc_color[7]="<font color='red'>"+wc[7]+"</font>";
				}else{
					wc_color[7]=wc[7];
				}
				
				htmlStr ="<div align='center'><table id='clothtable' name='clothtable' style='width:650px;' >" +
						"<tr>" +
						  "<th align='center' rowspan='3'>级配名称及粒径(mm)" +
						     "<tr><th colspan='8' align='center'>合成后各筛孔尺寸的通过百分率(%)</th></tr>" +
						     "<tr><th>0.075</th> <th>0.6</th><th>2.36</th><th>4.75</th><th>9.5</th><th>19</th><th>26.5</th><th>31.5</th></tr>"+
				   	      "</th>" +
				   	    "</tr>" +
				   	    "<tr align='center'><th width='80px'>标准合成级配</th>" +
				   	    	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper1())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper4())))+"</td>" +
				   	    	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper6())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper7())))+"</td>" +
				   	    	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper8())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper11())))+"</td>" +
				   	    	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper12())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getStandPassper13())))+"</td>" +
				   	    "</tr>" +
				   	    "<tr align='center'><th width='80px'>实际合成级配</th>" +
				   	       "<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper1())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper4())))+"</td>" +
				   	       	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper6())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper7())))+"</td>" +
				   	       	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper8())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper11())))+"</td>" +
				   	       	"<td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper12())))+"</td><td>"+String.format("%1$.2f",Double.valueOf(StringUtil.Null2Zero(sfjieguo.getPassper13())))+"</td>" +
				   	    "</tr>" +
				   	    "<tr align='center'><th width='80px'>误差(%)</th>"+
				   	    	"<td>"+wc_color[0]+"</td><td>"+wc_color[1]+"</td><td>"+wc_color[2]+"</td><td>"+wc_color[3]+"</td><td>"+wc_color[4]+"</td><td>"+wc_color[5]+"</td><td>"+wc_color[6]+"</td><td>"+wc_color[7]+"</td>" +
				   	    "</tr>" +
				   	    "<tr align='center'><th width='80px'>预警值(%)</th>" +
				   	    	"<td>"+swsfsmslow.getPassper1()+"~"+swsfsmshigh.getPassper1()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper4()+"~"+swsfsmshigh.getPassper4()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper6()+"~"+swsfsmshigh.getPassper6()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper7()+"~"+swsfsmshigh.getPassper7()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper8()+"~"+swsfsmshigh.getPassper8()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper11()+"~"+swsfsmshigh.getPassper11()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper12()+"~"+swsfsmshigh.getPassper12()+"</td>" +
				   	    	"<td>"+swsfsmslow.getPassper13()+"~"+swsfsmshigh.getPassper13()+"</td>" +
				   	    "</tr>" +
				   	    "</table></div>";
				return htmlStr;
			}
	
	public String chartLqJipeipic(LqshaifenjieguoView sfjieguo){
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?><chart caption='关键筛分通过率波动图' subcaption='");
		strXML.append("' lineThickness='2' showValues='0' anchorRadius='2' ");
		strXML.append("divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' slantLabels='1' ");
		strXML.append("showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' ");
		strXML.append("labelStep='");
		strXML.append(3);
		strXML.append("' numvdivlines='15' chartRightMargin='35' chartLeftMargin='35' formatNumberScale='0' ");
		strXML.append("bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' alternateHGridAlpha='5' numberSuffix='' yAxisName='各筛分通过质量百分率(%)'> ");
		strXML.append("<categories>");
		strXML.append("<category label='0.075'/>");
		strXML.append("<category label='0.15'/>");
		strXML.append("<category label='0.3'/>");
		strXML.append("<category label='0.6'/>");
		strXML.append("<category label='1.18'/>");
		strXML.append("<category label='2.36'/>");
		strXML.append("<category label='4.75'/>");
		strXML.append("<category label='9.5'/>");
		strXML.append("<category label='13.2'/>");
		strXML.append("<category label='16'/>");
		strXML.append("<category label='19'/>");
		strXML.append("<category label='26.5'/>");
		strXML.append("<category label='31.5'/>");
		strXML.append("<category label='37.5'/>");
		strXML.append("<category label='53'/>");
		strXML.append("</categories>");
	
		strXML.append("<dataset seriesName='-允许波动上限-'>");
		strXML.append("<set value='7'/>");
		strXML.append("<set value='8'/>");
		strXML.append("<set value='9'/>");
		strXML.append("<set value='13'/>");
		strXML.append("<set value='16'/>");
		strXML.append("<set value='23'/>");
		strXML.append("<set value='33'/>");
		strXML.append("<set value='49'/>");
		strXML.append("<set value='59'/>");
		strXML.append("<set value='66'/>");
		strXML.append("<set value='72'/>");
		strXML.append("<set value='90'/>");
		strXML.append("<set value='100'/>");
		strXML.append("<set value='100'/>");
		strXML.append("<set value='100'/>");
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-允许波动下限-'>");
		strXML.append("<set value='3'/>");
		strXML.append("<set value='4'/>");
		strXML.append("<set value='5'/>");
		strXML.append("<set value='7'/>");
		strXML.append("<set value='10'/>");
		strXML.append("<set value='15'/>");
		strXML.append("<set value='25'/>");
		strXML.append("<set value='39'/>");
		strXML.append("<set value='49'/>");
		strXML.append("<set value='57'/>");
		strXML.append("<set value='63'/>");
		strXML.append("<set value='82'/>");
		strXML.append("<set value='90'/>");
		strXML.append("<set value='100'/>");
		strXML.append("<set value='100'/>");
		strXML.append("</dataset>");
			
		strXML.append("<dataset seriesName='-标准级配-'>");
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper1()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper1())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper1()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper2()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper2())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper2()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper3()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper3())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper3()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper4()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper4())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper4()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper5()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper5())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper5()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper6()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper6())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper6()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper7()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper7())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper7()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper8()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper8())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper8()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper9()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper9())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper9()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper10()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper10())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper10()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper11()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper11())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper11()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper12()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper12())!=0){
		strXML.append("<set value='"+sfjieguo.getStandPassper12()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper13())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper13()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper13())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper14()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getStandPassper13()).length()>0 && StringUtil.StrToZero(sfjieguo.getStandPassper13())!=0){
			strXML.append("<set value='"+sfjieguo.getStandPassper15()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		strXML.append("</dataset>");
		
		strXML.append("<dataset seriesName='-实际级配-'>");
		if(StringUtil.Null2Blank(sfjieguo.getPassper1()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper1())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper1()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper2()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper2())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper2()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper3()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper3())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper3()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper4()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper4())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper4()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper5()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper5())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper5()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper6()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper6())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper6()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper7()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper7())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper7()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper8()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper8())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper8()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper9()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper9())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper9()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper10()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper10())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper10()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper11()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper11())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper11()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper12()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper12())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper12()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper13()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper13())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper13()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper14()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper14())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper14()+"'/>");
		}else{
			strXML.append("<set value=''/>");
		}
		if(StringUtil.Null2Blank(sfjieguo.getPassper15()).length()>0 && StringUtil.StrToZero(sfjieguo.getPassper15())!=0){
			strXML.append("<set value='"+sfjieguo.getPassper15()+"'/>");
		}else{
			strXML.append("<set value=''/>");
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSLine.swf", "",strXML.toString(), "chartshaifenjipei", 650, 450, false, false);
	}
	
	public String showShuiwenUsepic(ShuiwenmanualphbView swmanualphb,ShuiwenziduancfgView swField){
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?>");
		strXML.append("<chart caption='"+swmanualphb.getBanhezhanminchen()+"实际与理论用量比对图' xAxisName='材料名称' yAxisName='用量' showValues='0' numberPrefix='kg'>");
		strXML.append("<categories>");
		strXML.append("<category label='"+swField.getSjgl1()+"' />");
		strXML.append("<category label='"+swField.getSjgl2()+"' />");
		strXML.append("<category label='"+swField.getSjgl3()+"' />");
		strXML.append("<category label='"+swField.getSjgl4()+"' />");
		strXML.append("<category label='"+swField.getSjgl5()+"' />");
		strXML.append("<category label='"+swField.getSjfl1()+"' />");
		strXML.append("<category label='"+swField.getSjfl2()+"' />");
		strXML.append("</categories>");
		//实际值
		strXML.append("<dataset seriesName='实际用量'>");
		strXML.append("<set value='"+swmanualphb.getSjgl1()+"'/>");
		strXML.append("<set value='"+swmanualphb.getSjgl2()+"'/>");
		strXML.append("<set value='"+swmanualphb.getSjgl3()+"'/>");
		strXML.append("<set value='"+swmanualphb.getSjgl4()+"'/>");
		strXML.append("<set value='"+swmanualphb.getSjgl5()+"'/>");
		strXML.append("<set value='"+swmanualphb.getSjfl1()+"'/>");
		strXML.append("<set value='"+swmanualphb.getSjfl2()+"'/>");
		strXML.append("</dataset>");
		
		//理论值
		strXML.append("<dataset seriesName='理论用量'>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlgl1())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlgl2())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlgl3())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlgl4())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlgl5())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlfl1())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(swmanualphb.getLlfl2())/100*StringUtil.StrToZero(swmanualphb.getGlchangliang()))+"'/>");
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSColumn2D.swf", "",strXML.toString(), "ShuiwenUse", 650, 450, false, false);
	}
	
	public String showLqUsepic(LiqingmanualphbView lqmanualphb,LiqingziduancfgView lqField){
		StringBuilder strXML = new StringBuilder("");
		strXML.append("<?xml version='1.0' encoding='utf-8'?>");
		strXML.append("<chart caption='"+lqmanualphb.getBanhezhanminchen()+"实际与理论用量比对图' xAxisName='材料名称' yAxisName='用量' showValues='0' numberPrefix='kg'>");
		strXML.append("<categories>");
		strXML.append("<category label='"+lqField.getSjg1()+"' />");
		strXML.append("<category label='"+lqField.getSjg2()+"' />");
		strXML.append("<category label='"+lqField.getSjg3()+"' />");
		strXML.append("<category label='"+lqField.getSjg4()+"' />");
		strXML.append("<category label='"+lqField.getSjg5()+"' />");
		strXML.append("<category label='"+lqField.getSjg6()+"' />");
		strXML.append("<category label='"+lqField.getSjg7()+"' />");
		strXML.append("<category label='"+lqField.getSjf1()+"' />");
		strXML.append("<category label='"+lqField.getSjf2()+"' />");
		strXML.append("<category label='"+lqField.getSjlq()+"' />");
		strXML.append("<category label='"+lqField.getSjtjj()+"' />");
		strXML.append("</categories>");
		//实际值
		strXML.append("<dataset seriesName='实际用量'>");
		strXML.append("<set value='"+lqmanualphb.getSjg1()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjg2()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjg3()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjg4()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjg5()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjg6()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjg7()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjf1()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjf2()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjlq()+"'/>");
		strXML.append("<set value='"+lqmanualphb.getSjtjj()+"'/>");
		strXML.append("</dataset>");
		
		//理论值
		strXML.append("<dataset seriesName='理论用量'>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg1())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg2())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg3())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg4())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg5())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg6())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlg7())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlf1())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLlf2())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLllq())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
		strXML.append("<set value='"+String.format("%1$.2f",StringUtil.StrToZero(lqmanualphb.getLltjj())/100*StringUtil.StrToZero(lqmanualphb.getChangliang()))+"'/>");
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
		return FusionChartsCreator.createChart(StringUtil.getWebrootpath()+"/FusionCharts/MSColumn2D.swf", "",strXML.toString(), "LiqingUse", 650, 450, false, false);
	}
}