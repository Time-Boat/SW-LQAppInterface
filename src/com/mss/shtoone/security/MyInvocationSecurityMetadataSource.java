package com.mss.shtoone.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.mss.shtoone.domain.Resource;

public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	static Log logger = LogFactory.getLog(MyInvocationSecurityMetadataSource.class);
	private static UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }
	
	private Connection getCon() throws SQLException{
		 String filename=this.getClass().getResource("/").getPath()+"dbconf.ini";
		 Properties prop=new Properties();
		 File fp = new File(filename);
		 String conurl = "jdbc:jtds:sqlserver://127.0.0.1;DatabaseName=ggmss;";
		 String username = "sa";
		 String pwd = "";
		 if(fp.exists()){
			 try {
				prop.load(new FileInputStream(fp));
				conurl = prop.getProperty("conurl", conurl);				
				username = prop.getProperty("username", username);				
				pwd = prop.getProperty("pwd", pwd);	
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			} 
		 }
		 //Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1;DatabaseName=mss;","sa","toonesh");
		 //Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1;DatabaseName=mss;","sa","QWERTYUIOP[]");
		 //Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:2433;DatabaseName=cymss;","sa","toonesh888");
		 Connection con = DriverManager.getConnection(conurl,username,pwd);
		 return con;
	}
	

    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();        
        String sql = "SELECT * FROM Resource";
    	Connection con = null;
    	Statement stmt = null;	
    	ResultSet rs = null;
    	Connection rolecon = null;
    	Statement rolestmt = null;	
    	ResultSet rolers = null; 
    	try {
    	con = getCon();
    	stmt = con.createStatement();
        rs=stmt.executeQuery(sql);
        List<Resource> resourcelist = new ArrayList<Resource>();
        while(rs.next()){
        	Resource resource = new Resource();
        	resource.setId(rs.getInt("id"));
        	resource.setType(rs.getString("type"));
        	resource.setSvalue(rs.getString("svalue"));
        	resourcelist.add(resource);        	
        }
        
    	rolecon = getCon();
        for (Resource resource : resourcelist) {
        	try {
                String rolesql = "SELECT a.* FROM role a inner join role_resource b " +
        		"on a.id=b.role_id where b.resource_id="+resource.getId();
                rolestmt = getCon().createStatement();
                rolers=rolestmt.executeQuery(rolesql);
                Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                while(rolers.next()){
                  ConfigAttribute ca = new SecurityConfig(rolers.getString("name"));
             	  atts.add(ca);       
                }
                resourceMap.put(resource.getSvalue(), atts);
			} catch (Exception e) {
			} finally {
				closeAll(rolers, rolestmt);
			}

		}
    	} catch (Exception e) {
    		logger.error(e.getMessage());
		} finally {
			closeAll(rs, stmt, con);
			closeAll(rolers, rolestmt, rolecon);
		}
        /*        if (null != template) {
    	List<Resource> resourceList = template.find("from Resource");
        for (Resource resource : resourceList) {
        	Set<Role> roleList = resource.getRoles();
        	for (Role role : roleList) {
        	   ConfigAttribute ca = new SecurityConfig(role.getName());
        	   atts.add(ca);
			}
			resourceMap.put(resource.getValue(), atts);
		}
	   }    */ 
   
    }
    
    public void closeAll(ResultSet rs, Statement stmt, Connection con) {
        if (rs != null) {
          try {
            rs.close();
          }
          catch (Exception ex) {
          }
        }

        if (stmt != null) {
          try {
            stmt.close();
          }
          catch (Exception ex) {
          }
        }

        if (con != null) {
          try {
            con.close();
          }
          catch (Exception ex) {
          }
        }
      }
    
    
    public void closeAll(ResultSet rs, Statement stmt) {
        if (rs != null) {
          try {
            rs.close();
          }
          catch (Exception ex) {
          }
        }

        if (stmt != null) {
          try {
            stmt.close();
          }
          catch (Exception ex) {
          }
        }  
      }
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();					
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}
	

	public static Collection<ConfigAttribute> getConfigAttributes(String url){
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();					
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}



