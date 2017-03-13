package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Diuqishuju;
import com.mss.shtoone.domain.DiuqishujuPageMode;
import com.mss.shtoone.persistence.DiuqishujuDAO;
import com.mss.shtoone.util.DbJdbcUtil;


@Repository
public class DiuqishujuHibernateDAO extends GenericHibernateDAO<Diuqishuju, Integer> implements
DiuqishujuDAO {
	static Log logger = LogFactory.getLog(DiuqishujuHibernateDAO.class);
	@Override
	public DiuqishujuPageMode viewlist(int offset, int pagesize) {
		List<Diuqishuju> _returnValue = new ArrayList<Diuqishuju>();
		DiuqishujuPageMode pagemode = new DiuqishujuPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "Diuqishuju";	
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
			cs.setString(9, "");
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					Diuqishuju hv = new Diuqishuju();
					hv.setId(rs.getInt("id"));
					hv.setDiuqishuju(rs.getString("diuqishuju"));
					hv.setKehuduanbianhao(rs.getString("kehuduanbianhao"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setShuliang(rs.getInt("shuliang"));
					hv.setShijian(rs.getString("shijian"));				
					
					_returnValue.add(hv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, cs,con);
		}
		return pagemode;
	}

}



