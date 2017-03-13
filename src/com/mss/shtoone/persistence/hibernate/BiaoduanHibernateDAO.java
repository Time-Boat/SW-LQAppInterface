package com.mss.shtoone.persistence.hibernate;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.util.DbJdbcUtil;


@Repository
public class BiaoduanHibernateDAO extends GenericHibernateDAO<Biaoduanxinxi, Integer> implements
BiaoduanDAO {
	@Override
	public void executeUpdate(String sql)  throws Exception{
		Statement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			con.setAutoCommit(false);
			cs = con.createStatement();
			cs.executeUpdate(sql);
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			throw new Exception(sql + ":" + e.getMessage());
		} finally {			
			DbJdbcUtil.closeAll(cs, con);
		}		
	}

	@Override
	public void executeUpdate(String sql, ArrayList imagelist)  throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);					
			for (int i = 0; i < imagelist.size(); i++) {
				ByteArrayInputStream bin = new ByteArrayInputStream((byte[])imagelist.get(i));
				pst.setBinaryStream(i+1, bin, bin.available());
			}					
			pst.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			imagelist.clear();
		} catch (SQLException e) {
			con.rollback();
			throw new Exception(sql + ":" + e.getMessage());
		} finally {			
			DbJdbcUtil.closeAll(pst, con);
		}	
		
	}

	@Override
	public ResultSetMetaData queryMetaData(String sql) {
		Statement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.createStatement();
			return cs.executeQuery(sql).getMetaData();
		} catch (SQLException e) {
			return null;
		} finally {			
			DbJdbcUtil.closeAll(cs,con);
		}	
	}
}



