package com.mss.shtoone.app.persistence.hibernate;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.app.domain.AppInterfaceChaobiaoEntity;
import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.persistence.AppDAO;
import com.mss.shtoone.persistence.hibernate.GenericHibernateDAO;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;

@Repository
public class AppHibernateDAO extends GenericHibernateDAO<AppLoginLogEntity, Integer> implements AppDAO{

//	public void departTree(List list, String fn, String biaoshiid) {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" FROM ShuiwenmanualphbView WHERE 1=1 ");
//		if (!fn.equalsIgnoreCase("all")) {
//			sql.append(" and "+fn+"=" + biaoshiid);
//		}
//	}
	
	// 拼查询条件（where语句）
	private String getSqlWhere(String startTime,String endTime,
			String sbbh,String biaoduan,String xmb) {
		// 拼出条件语句
		String sqlWhere = "";
		if (StringUtil.isNotEmpty(startTime) && StringUtil.isNotEmpty(endTime)) {
			sqlWhere += " and (convert(datetime,shijian,121) between '" + startTime
				+ "' and '" + endTime + "') ";
		}
		
		if (StringUtil.isNotEmpty(biaoduan)){
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and ";
			}
			sqlWhere += " biaoduanid = " + biaoduan + " ";
					
		}
		
		if (StringUtil.isNotEmpty(xmb)) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and ";
			}
			sqlWhere += " xiangmubuid = " + xmb + " ";
		}
		
		if (StringUtil.isNotEmpty(sbbh)) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and ";
			}
			sqlWhere += " shebeibianhao = '"+sbbh + "'";
		}
		
		return sqlWhere;
	}	
		
	@Override
	public Map<String, String> getCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu, String shebeibianhao) {
		
		Map<String, String> map = new HashMap<String,String>();
		
		StringBuffer sql = new StringBuffer(" select SUM(CASE WHEN (leixing = '1') OR (leixing = '2') OR (leixing = '3') then 1 else 0 end) as a, "
				+ " SUM(CASE WHEN (leixing = '2') OR (leixing = '3') then 1 else 0 end) as b, " 
				+ " SUM(CASE WHEN (leixing = '3') then 1 else 0 end) as c from ShuiwenmanaualchaobiaoView where (filepath is NOT NULL or  ISNULL(chulijieguo,'')<>'') ");
		
		if (StringUtil.Null2Blank(startTime).length() > 0 && StringUtil.Null2Blank(endTime).length() > 0) {
			sql.append(" and (convert(datetime,baocunshijian,121) between '" + startTime + "' and '" + endTime + "') ");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan );
		}
		
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu );
		}
		
		if (null != shebeibianhao) {
			sql.append(" and shebeibianhao='" + shebeibianhao + "' ");
		}
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getCon();
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			if (rs.next()) {
				map.put("a", rs.getString("a"));
				map.put("b", rs.getString("b"));
				map.put("c", rs.getString("c"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbJdbcUtil.closeAll(rs, st, con);
		}
		return map;
	}

	
	
	
	
	//沥青impl--------------------------------------------------------------------------------------------------
	@Override
	public List<AppInterfaceChaobiaoEntity> lqsmstongji(String startTime, String endTime, Integer biaoduan, Integer xiangmubu, String sbbh,
			String fn, Integer bsid, Integer fzlx) {
		List<AppInterfaceChaobiaoEntity> _returnValue = new ArrayList<AppInterfaceChaobiaoEntity>();
		
		StringBuffer sql = new StringBuffer(" select ");
		
		//总   (同初级)
		sql.append("SUM(CASE WHEN (sjysb is not null) ");
		sql.append("OR (sjg1 is not null) ");
		sql.append("OR (sjg2 is not null) ");
		sql.append("OR (sjg3 is not null) ");
		sql.append("OR (sjg4 is not null) ");
		sql.append("OR (sjg5 is not null) ");
		sql.append("OR (sjg6 is not null) ");
		sql.append("OR (sjg7 is not null) ");
		sql.append("OR (sjf1 is not null) ");
		sql.append("OR (sjf2 is not null) ");
		sql.append("OR (sjlq is not null) ");
		sql.append("OR (sjtjj is not null) ");
		sql.append("THEN 1 ELSE 0 END) AS cbps,");
		
		//中级
		sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') ");
		sql.append("OR (sjg1 = '3') OR (sjg1 = '6') ");
		sql.append("OR (sjg2 = '3') OR (sjg2 = '6') ");
		sql.append("OR (sjg3 = '3') OR (sjg3 = '6') ");
		sql.append("OR (sjg4 = '3') OR (sjg4 = '6') ");
		sql.append("OR (sjg5 = '3') OR (sjg5 = '6') ");
		sql.append("OR (sjg6 = '3') OR (sjg6 = '6') ");
		sql.append("OR (sjg7 = '3') OR (sjg7 = '6') ");
		sql.append("OR (sjf1 = '3') OR (sjf1 = '6') ");
		sql.append("OR (sjf2 = '3') OR (sjf2 = '6') ");
		sql.append("OR (sjlq = '3') OR (sjlq = '6') ");
		sql.append("OR (sjtjj = '3') OR (sjtjj = '6') ");
		sql.append("OR (sjysb = '2') OR (sjysb = '5') ");
		sql.append("OR (sjg1 = '2') OR (sjg1 = '5') ");
		sql.append("OR (sjg2 = '2') OR (sjg2 = '5') ");
		sql.append("OR (sjg3 = '2') OR (sjg3 = '5') ");
		sql.append("OR (sjg4 = '2') OR (sjg4 = '5') ");
		sql.append("OR (sjg5 = '2') OR (sjg5 = '5') ");
		sql.append("OR (sjg6 = '2') OR (sjg6 = '5') ");
		sql.append("OR (sjg7 = '2') OR (sjg7 = '5') ");
		sql.append("OR (sjf1 = '2') OR (sjf1 = '5') ");
		sql.append("OR (sjf2 = '2') OR (sjf2 = '5') ");
		sql.append("OR (sjlq = '2') OR (sjlq = '5') ");
		sql.append("OR (sjtjj = '2') OR (sjtjj = '5') ");
		sql.append("OR (sjysb = '0') OR (sjysb = '7') ");
		sql.append("OR (sjg1 = '0') OR (sjg1 = '7') ");
		sql.append("OR (sjg2 = '0') OR (sjg2 = '7') ");
		sql.append("OR (sjg3 = '0') OR (sjg3 = '7') ");
		sql.append("OR (sjg4 = '0') OR (sjg4 = '7') ");
		sql.append("OR (sjg5 = '0') OR (sjg5 = '7') ");
		sql.append("OR (sjg6 = '0') OR (sjg6 = '7') ");
		sql.append("OR (sjg7 = '0') OR (sjg7 = '7') ");
		sql.append("OR (sjf1 = '0') OR (sjf1 = '7') ");
		sql.append("OR (sjf2 = '0') OR (sjf2 = '7') ");
		sql.append("OR (sjlq = '0') OR (sjlq = '7') ");
		sql.append("OR (sjtjj = '0') OR (sjtjj = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS middleps,");
		
		//高级
		sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') OR (sjysb = '0') OR (sjysb = '7') ");
		sql.append("OR (sjg1 = '3') OR (sjg1 = '6') OR (sjg1 = '0') OR (sjg1 = '7') ");
		sql.append("OR (sjg2 = '3') OR (sjg2 = '6') OR (sjg2 = '0') OR (sjg2 = '7') ");
		sql.append("OR (sjg3 = '3') OR (sjg3 = '6') OR (sjg3 = '0') OR (sjg3 = '7') ");
		sql.append("OR (sjg4 = '3') OR (sjg4 = '6') OR (sjg4 = '0') OR (sjg4 = '7') ");
		sql.append("OR (sjg5 = '3') OR (sjg5 = '6') OR (sjg5 = '0') OR (sjg5 = '7') ");
		sql.append("OR (sjg6 = '3') OR (sjg6 = '6') OR (sjg6 = '0') OR (sjg6 = '7') ");
		sql.append("OR (sjg7 = '3') OR (sjg7 = '6') OR (sjg7 = '0') OR (sjg7 = '7') ");
		sql.append("OR (sjf1 = '3') OR (sjf1 = '6') OR (sjf1 = '0') OR (sjf1 = '7') ");
		sql.append("OR (sjf2 = '3') OR (sjf2 = '6') OR (sjf2 = '0') OR (sjf2 = '7') ");
		sql.append("OR (sjlq = '3') OR (sjlq = '6') OR (sjlq = '0') OR (sjlq = '7') ");
		sql.append("OR (sjtjj = '3') OR (sjtjj = '6') OR (sjtjj = '0') OR (sjtjj = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highps,");
		
		sql.append("MAX(shebeibianhao) AS shebeibianhao,banhezhanminchen ");
		sql.append(" FROM LiqingxixxjieguoTjView where 1=1 ");
		
		String sqlWhere = getSqlWhere(startTime, endTime, sbbh, biaoduan+"", xiangmubu+"");
		if (!sqlWhere.isEmpty()) {
			sql.append(sqlWhere);
		}
		
		sql.append(" group by shebeibianhao,banhezhanminchen ");
//		lqcbList = (AppInterfaceChaobiaoCountReal)jdbcDao.findForObject(sql.toString(),AppInterfaceChaobiaoCountReal.class,new HashMap<String, String>());
		
		//获取总盘数和总产量
		Map<String, Object> map = getpangshu(startTime,endTime,sbbh,biaoduan+"",xiangmubu+"");
		//各个级别超标盘数
		Map<String, String> gcps = getPreHandle(startTime,endTime,sbbh,biaoduan+"",xiangmubu+"");
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getCon();
 			st = con.createStatement();		
			rs = st.executeQuery(sql.toString());
//			while (rs.next()) {
//				AppInterfaceChaobiaoCountReal cbCount = new AppInterfaceChaobiaoCountReal();
//				//这里设置总盘数和总产量
//				cbCount.setPanshu((String) map.get("panshu"));
//				cbCount.setChangliang((String) map.get("chanliang"));
//				cbCount.setCbps(rs.getString("cbps"));
//				cbCount.setPrimaryps(rs.getString("cbps"));
//				cbCount.setMiddleps(rs.getString("middleps"));
//				cbCount.setHighps(rs.getString("highps"));
//				int zongpangshu = 0;
//				try{
//					zongpangshu = Integer.parseInt(cbCount.getPanshu());
//					if(zongpangshu>0){
//						cbCount.setCblv(String.format("%1$.2f",Double.parseDouble(cbCount.getCbps())*100/zongpangshu));
//						cbCount.setPrimarylv(String.format("%1$.2f",Double.parseDouble(cbCount.getPrimaryps())*100/zongpangshu));
//						cbCount.setMiddlelv(String.format("%1$.2f",Double.parseDouble(cbCount.getMiddleps())*100/zongpangshu));
//						cbCount.setHighlv(String.format("%1$.2f",Double.parseDouble(cbCount.getHighps())*100/zongpangshu));
//					}
//				}catch(Exception ex){}
//				
//				//处置率
//				cbCount.setReallv("");
//				cbCount.setPrimaryreallv("");
//				cbCount.setHighreallv("");
//				cbCount.setMiddlereallv("");
//				
//				cbCount.setBanhezhanminchen(rs.getString("banhezhanminchen"));
//				cbCount.setShebeibianhao(rs.getString("shebeibianhao"));
//				_returnValue.add(cbCount);
//			}
			
			String ps = (String) map.get("panshu");
			String cl = (String) map.get("chanliang");
			
			while (rs.next()) {
				AppInterfaceChaobiaoEntity cbCount = new AppInterfaceChaobiaoEntity();
				cbCount.setBanhezhanminchen("全线超标总数");
				cbCount.setDengji("总");
				//这里得到总盘数和总产量
				cbCount.setChangliang(cl);
				cbCount.setPanshu(ps);
				
				cbCount.setCbps(rs.getString("cbps"));
				//低级处置盘数
				String plv = gcps.get("plv");
				int zongpangshu=0;
				try{
					zongpangshu = Integer.parseInt(cbCount.getPanshu());
					if(zongpangshu>0){
						cbCount.setCblv(String.format("%1$.2f",Double.parseDouble(cbCount.getCbps())*100/zongpangshu));
						if(StringUtil.isNotEmpty(plv))
							cbCount.setReallv(String.format("%1$.2f",Double.parseDouble(plv)*100/zongpangshu));
					}
				}catch(Exception ex){}
				
				cbCount.setShebeibianhao(rs.getString("shebeibianhao"));
				_returnValue.add(cbCount);
				
				cbCount = new AppInterfaceChaobiaoEntity();
				cbCount.setBanhezhanminchen(rs.getString("banhezhanminchen")+"初级");
				cbCount.setDengji("初级");
				//这里得到总盘数和总产量
				cbCount.setChangliang(cl);
				cbCount.setPanshu(ps);
				
				cbCount.setCbps(rs.getString("cbps"));
				//中级处置盘数
				String mlv = gcps.get("mlv");
				try{
					if(zongpangshu>0){
						cbCount.setCblv(String.format("%1$.2f",Double.parseDouble(cbCount.getCbps())*100/zongpangshu));
						if(StringUtil.isNotEmpty(mlv))
							cbCount.setReallv(String.format("%1$.2f",Double.parseDouble(mlv)*100/zongpangshu));
					}
				}catch(Exception ex){}
				cbCount.setShebeibianhao(rs.getString("shebeibianhao"));
				_returnValue.add(cbCount);
				
				cbCount = new AppInterfaceChaobiaoEntity();
				cbCount.setBanhezhanminchen(rs.getString("banhezhanminchen")+"中级");
				cbCount.setDengji("中级");
				//这里得到总盘数和总产量
				cbCount.setChangliang(cl);
				cbCount.setPanshu(ps);
				
				cbCount.setCbps(rs.getString("middleps"));
				//高级处置盘数
				String hlv = gcps.get("hlv");
				try{
					if(zongpangshu>0){
						cbCount.setCblv(String.format("%1$.2f",Double.parseDouble(cbCount.getCbps())*100/zongpangshu));
						if(StringUtil.isNotEmpty(hlv))
							cbCount.setReallv(String.format("%1$.2f",Double.parseDouble(hlv)*100/zongpangshu));
					}
				}catch(Exception ex){}
				
				cbCount.setShebeibianhao(rs.getString("shebeibianhao"));
				_returnValue.add(cbCount);
				
				cbCount = new AppInterfaceChaobiaoEntity();
				cbCount.setBanhezhanminchen(rs.getString("banhezhanminchen")+"高级");
				cbCount.setDengji("高级");
				//这里得到总盘数和总产量
				cbCount.setChangliang(cl);
				cbCount.setPanshu(ps);
				
				cbCount.setCbps(rs.getString("highps"));
				try{
					if(zongpangshu>0){
						cbCount.setCblv(String.format("%1$.2f",Double.parseDouble(cbCount.getCbps())*100/zongpangshu));
					}
				}catch(Exception ex){}
				//处置率
				cbCount.setReallv("");
				cbCount.setShebeibianhao(rs.getString("shebeibianhao"));
				_returnValue.add(cbCount);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbJdbcUtil.closeAll(rs, st, con);
		}
		
		return _returnValue;
		
	}

	
	//获取沥青试验总盘数
	public Map<String,Object> getpangshu(String startTime, String endTime, String sbbh, String biaoduan, String xmb) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu");	
		sql.append(" from LiqingView where 1=1 ");
		
		String str = getSqlWhere(startTime, endTime, sbbh, biaoduan, xmb);
		sql.append(str);
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getCon();
			st = con.createStatement();		
			
			if (true) {
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					map.put("chanliang", String.format("%1$.1f",rs.getFloat("changliang")/1000));
					map.put("panshu",rs.getString("pangshu"));
				}
			}
		} catch (Exception e) {
			System.out.println("试验总盘数查询异常");
		} finally {
			DbJdbcUtil.closeAll(rs,st,con);
		}
		return map;
	}

	//各等级处置盘数
	private Map<String,String> getPreHandle(String startTime, String endTime, String sbbh, String biaoduan, String xmb){
		Map<String,String> map = new HashMap<String,String>();
		
		StringBuffer sql = new StringBuffer();
		
		String sql1 = "select count(leixing) as reallv from LiqingxixxjieguoTjView where chulijieguo is not null and leixing=1 ";
		sql.append(sql1);
		
		String str = getSqlWhere(startTime, endTime, sbbh, biaoduan, xmb);
		sql.append(str);
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getCon();
			st = con.createStatement();
			if (true) {
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					map.put("plv", rs.getString("reallv"));
				}
				
				int index = sql.indexOf("leixing=1");
				sql.replace(index+8, index+9, "2");
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					map.put("mlv", rs.getString("reallv"));
				}
				
				sql.replace(index+8, index+9, "3");
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					map.put("hlv", rs.getString("reallv"));
				}
			}
		} catch (Exception e) {
			System.out.println("处置盘数查询异常");
		} finally {
			closeCon(rs,st,con);
		}
		return map;
	}  
	
	@Override
	public Map<String, String> getLqCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu) {
		
		Map<String, String> map = new HashMap<String,String>();
		
		StringBuffer sql = new StringBuffer(" select SUM(CASE WHEN (leixing = '1') OR (leixing = '2') OR (leixing = '3') then 1 else 0 end) as a, "
				+ " SUM(CASE WHEN (leixing = '2') OR (leixing = '3') then 1 else 0 end) as b, " 
				+ " SUM(CASE WHEN (leixing = '3') then 1 else 0 end) as c from LiqingmanualchaobiaoView where (filepath is NOT NULL or  ISNULL(chulijieguo,'')<>'') ");
		
		if (StringUtil.Null2Blank(startTime).length() > 0 && StringUtil.Null2Blank(endTime).length() > 0) {
			sql.append(" and (convert(datetime,baocunshijian,121) between '" + startTime + "' and '" + endTime + "') ");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan );
		}
		
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu );
		}
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getCon();
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			if (rs.next()) {
				map.put("a", rs.getString("a"));
				map.put("b", rs.getString("b"));
				map.put("c", rs.getString("c"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbJdbcUtil.closeAll(rs, st, con);
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void closeCon(ResultSet rs, Statement st, Connection con) {
		DbJdbcUtil.closeAll(rs,st,con);
	}

	private Connection getCon() {
		return getTemplate().getSessionFactory().openSession().connection();
	}
	
	
}
