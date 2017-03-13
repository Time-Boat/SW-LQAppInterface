package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Liqingxixxlilun;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LqshaifenshiyanView;
import com.mss.shtoone.persistence.LiqingxixxlilunDAO;
import com.mss.shtoone.persistence.LqshaifenshiyanViewDAO;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;

@Repository
public class LqshaifenshiyanViewHibernateDAO extends GenericHibernateDAO<LqshaifenshiyanView, Integer> implements LqshaifenshiyanViewDAO{
	private static Log logger = LogFactory.getLog(LqshaifenshiyanViewHibernateDAO.class);
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private LiqingxixxlilunDAO lqllDAO;
		
	@Override
	public GenericPageMode limitLqsflist(Integer biaoduan,Integer xiangmubu,String shebeibianhao,String startTimeOne, String endTimeOne, 
			String fn, int bsid,int offset, int pagesize,String moren,String ziduanminchen) {
		List<LqshaifenshiyanView> _returnValue = new ArrayList<LqshaifenshiyanView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LqshaifenshiyanView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE, -30);
		String strstartTime = sdf.format(day.getTime());		
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {	
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  " (convert(datetime,newtime,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		if(biaoduan!=null){
			queryCondition+=" and biaoduanid="+biaoduan;
		}
		if(xiangmubu!=null){
			queryCondition+=" and xiangmubuid="+xiangmubu;
		}
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		if(StringUtil.Null2Blank(moren).length()>0){
			queryCondition+=" and moren='"+moren+"'";
		}
		if(StringUtil.Null2Blank(ziduanminchen).length()>0){
			queryCondition+=" and ziduanminchen='"+ziduanminchen+"'";
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "id");
			cs.setString(4, "id DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LqshaifenshiyanView sfview = new LqshaifenshiyanView();
					sfview.setId(rs.getInt("id"));
					sfview.setMoren(rs.getString("moren"));
					sfview.setZiduanminchen(rs.getString("ziduanminchen"));
					sfview.setShebeibianhao(rs.getString("shebeibianhao"));
					sfview.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					sfview.setZiduanminchen(rs.getString("ziduanminchen"));
					sfview.setPassper1(rs.getString("passper1"));					
					sfview.setPassper2(rs.getString("passper2"));
					sfview.setPassper3(rs.getString("passper3"));
					sfview.setPassper4(rs.getString("passper4"));
					sfview.setPassper5(rs.getString("passper5"));
					sfview.setPassper6(rs.getString("passper6"));
					sfview.setPassper7(rs.getString("passper7"));
					sfview.setPassper8(rs.getString("passper8"));
					sfview.setPassper9(rs.getString("passper9"));
					sfview.setPassper10(rs.getString("passper10"));
					sfview.setPassper11(rs.getString("passper11"));
					sfview.setPassper12(rs.getString("passper12"));
					sfview.setPassper13(rs.getString("passper13"));
					sfview.setPassper14(rs.getString("passper14"));
					sfview.setPassper15(rs.getString("passper15"));
					sfview.setNewtime(rs.getString("newtime"));
					if(StringUtil.Null2Blank(sfview.getShebeibianhao()).length()>0){
						try{
							Liqingziduancfg lqziduancfg=sysService.liqingfieldnameBybh(sfview.getShebeibianhao());
							sfview.setBeizhu((String)lqziduancfg.getClass().getMethod("get"+sfview.getZiduanminchen().replaceFirst(sfview.getZiduanminchen().substring(0,1),sfview.getZiduanminchen().substring(0,1).toUpperCase()),new Class[]{}).invoke(lqziduancfg,new Object[]{}));
						}catch(Exception ex){}
					}
					if(StringUtil.Null2Blank(rs.getString("Lqllid")).length()>0){
						Liqingxixxlilun lqlilun=lqllDAO.get(Integer.parseInt(rs.getString("Lqllid")));
						if(lqlilun!=null){
							sfview.setLqllid(lqlilun.getLlbuwei());
						}
					}
					_returnValue.add(sfview);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, cs, con);
		}
		return pagemode;
	}
}
