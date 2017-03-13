package com.mss.shtoone.util;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class DbJdbcUtil {
	static Log logger = LogFactory.getLog(DbJdbcUtil.class);

  /**
   * default constructor
   */
  public DbJdbcUtil() {
  }

  /**
   * close the connected connection
   */
  public void finalize(Connection con) {
    try {
      if (con != null) con.close();
    }
    catch (Exception e) {
    	logger.error(e.getMessage());
    }
  }

  /**
   * get the connection from the connection pool or jdbc API
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   * @return con if get the connection successful
   *  else throw Exception;
   */

  public static Connection connectToDb() throws Exception {
     Connection con = null;
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1;DatabaseName=mss;tds=8.0;lastupdatecount=true",
                                          "sa",
                                          "");    
    }
    catch (Exception e) {
    	logger.error(e.getMessage());
    }
    return con;
  }

  /**
   * close the connection
   */
  public static void close(Connection con) {

    try {
      if (con != null) con.close();
    }
    catch (Exception e) {
    	logger.error(e.getMessage());
    }
  }

  /**
   * close the database resource
   *
   * @param rs the ResultSet to be closed
   * @param stmt the Statement to be closed
   * @param dBean the DBean instance holding the connection
   */
  public static void closeAll(ResultSet rs, Statement stmt, Connection con) {

    if (rs != null) {
      try {
        rs.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }

    if (stmt != null) {
      try {
        stmt.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }

    if (con != null) {
      try {
        con.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }
  }

  /**
   * close the database resource
   * @param rs ResultSet
   * @param pStmt PreparedStatement
   * @param dbUtil DbUtil
   */
  public static void closeAll(ResultSet rs, PreparedStatement pStmt, Connection con) {

    if (rs != null) {
      try {
        rs.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }

    if (pStmt != null) {
      try {
        pStmt.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }

    if (con != null) {
      try {
        con.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }
  }
  
  	/**
  	 * close the database resource
  	 * @param rs ResultSet
  	 * @param pStmt PreparedStatement
  	 * @param dbUtil DbUtil
   	*/
  	public static void closeAll(ResultSet rs, CallableStatement cs, Connection con) {
  		if (rs != null) {
  			try {
  				rs.close();
  			}catch (Exception ex) {
  				logger.error(ex.getMessage());
  			}
  		}

  		if (cs != null) {
  			try {
  				cs.close();
  			}catch (Exception ex) {
  				logger.error(ex.getMessage());
  			}
  		}

  		if (con != null) {
  			try {
  				con.close();
  			}catch (Exception ex) {
  				logger.error(ex.getMessage());
  			}
  		}
  	}

  public static void closeAll(ResultSet rs, PreparedStatement pStmt) {

    if (rs != null) {
      try {
        rs.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }

    if (pStmt != null) {
      try {
        pStmt.close();
      }
      catch (Exception ex) {
    	  logger.error(ex.getMessage());
      }
    }
  }
  
  public static void closeAll(Statement pStmt, Connection con) {
	    if (pStmt != null) {
	      try {
	        pStmt.close();
	      }
	      catch (Exception ex) {
	    	  logger.error(ex.getMessage());
	      }
	    }

	    if (con != null) {
	      try {
	        con.close();
	      }
	      catch (Exception ex) {
	    	  logger.error(ex.getMessage());
	      }
	    }
	  }



}
