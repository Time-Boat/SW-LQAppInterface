package com.mss.shtoone.service;

import java.util.*;
import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.util.XMLUtils;
import com.mss.shtoone.util.StringUtil;

@Service
public class DatasubmitService {
	
	@Autowired
	private BiaoduanDAO bdDAO;

    private boolean isSpecialElement(Node node, String nodeName) {
        return (Node.ELEMENT_NODE == node.getNodeType()) && node.getNodeName().equals(nodeName);
    }

    private String getFieldNameList(Element elRecord) {
        StringBuffer sFieldNameList = new StringBuffer();
        Element elField;
        Node node = elRecord.getFirstChild();
        String fieldName;
        while (null != node) {
            //Filed -> F
            if (isSpecialElement(node, "F")) {
                elField = (Element) node;
                //Name -> N
                fieldName = elField.getAttribute("N");
                if (null != fieldName) {
                    sFieldNameList.append(",[" + fieldName + "]");
                }
            }
            node = node.getNextSibling();
        }
        if (sFieldNameList.length() > 0) {
            sFieldNameList.deleteCharAt(0);
        }
        return sFieldNameList.toString();
    }

    private String getData(int fieldType, Element elField, ArrayList imagelist) {
        String encodedata = XMLUtils.getText(elField); //2011-6-30上传字段全部解码，防止特殊字符
        String data="";
        if ((Types.CHAR == fieldType) ||
            (Types.VARCHAR == fieldType) ||
            (Types.TIMESTAMP == fieldType) ||
            (Types.DATE == fieldType) || (Types.NVARCHAR == fieldType)) {
        	data = new String(StringUtil.getFromBASE64(encodedata));
            return (0 == data.length()) ? "Null" : "'" + data.replaceAll("'", "''") + "'";
        }
        else if ((Types.BIT == fieldType) ||
                 (Types.BOOLEAN == fieldType)) {
        	data = new String(StringUtil.getFromBASE64(encodedata));
            if (data.equals("True")) {
                data = "1";
            }
            else if (data.equals("False")) {
                data = "0";
            }
        }  else if (Types.BLOB == fieldType) 
        {    
        	imagelist.add(StringUtil.getFromBASE64(encodedata));
        	data = "?";
        } else {
        	data = new String(StringUtil.getFromBASE64(encodedata));
        }
        return (0 == data.length()) ? "Null" : data;
    }

    private int getFieldType(String fieldName, HashMap ftypes) throws Exception {
        Object objFieldType = ftypes.get(fieldName);
        if (null != objFieldType) {
            return ((Integer)(objFieldType)).intValue();
        }
        else {
            throw new Exception("表结构异常,请检查数据库是否升级!");
        }
    }

    private String getFieldValueList(Element elRecord, HashMap ftypes, ArrayList imagelist) throws Exception {
        StringBuffer sFieldValueList = new StringBuffer();
        Element elField;
        Node node = elRecord.getFirstChild();
        int fieldType;
        String fieldName;
        while (null != node) {
            //Field -> F
            if (isSpecialElement(node, "F")) {
                elField = (Element) node;
                //Name -> N
                fieldName = elField.getAttribute("N");
                if (null != fieldName) {
                    fieldType = getFieldType(fieldName, ftypes);
                    sFieldValueList.append("," + getData(fieldType, elField, imagelist));
                }
            }
            node = node.getNextSibling();
        }

        if (sFieldValueList.length() > 0) {
            sFieldValueList.deleteCharAt(0);
        }
        return sFieldValueList.toString();
    }

    private String getSetValueList(Element elRecord, HashMap ftypes, ArrayList imagelist) throws Exception {
        StringBuffer sSetValueList = new StringBuffer();
        Element elField;
        Node node = elRecord.getFirstChild();
        int fieldType;
        String fieldName;
        while (null != node) {
            //Field -> F
            if (isSpecialElement(node, "F")) {
                elField = (Element) node;
                //Name -> N
                fieldName = elField.getAttribute("N");
                if (null != fieldName) {
                    fieldType = getFieldType(fieldName, ftypes);
                    sSetValueList.append(",[" + fieldName + "]=" + getData(fieldType, elField, imagelist));
                }
            }
            node = node.getNextSibling();
        }
        if (sSetValueList.length() > 0) {
            sSetValueList.deleteCharAt(0);
        }
        return sSetValueList.toString();
    }

    private String getNoList(Element elTable) {
        StringBuffer result = new StringBuffer();
        Node ndRecord = elTable.getFirstChild();
        while (null != ndRecord) {
            //Record -> R
            if (isSpecialElement(ndRecord, "R")) {
                //TestNo -> No
                String testNo = ((Element)ndRecord).getAttribute("No");
                result.append(",'" + testNo + "'");
            }
            ndRecord = ndRecord.getNextSibling();
        }
        if (result.length() > 0) {
            result.deleteCharAt(0);
            return "(" + result + ")";
        }
        return null;
    }


    private String generateSQL(String tableName, Element elRecord, HashMap ftypes, ArrayList imagelist) throws Exception {
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("INSERT INTO " + tableName + " (" +
                       getFieldNameList(elRecord) + ") VALUES ("+
                       getFieldValueList(elRecord, ftypes, imagelist) + ")");
        }
        catch (Exception ex) {
            throw new Exception(tableName + ":" + ex.getMessage());
        }
        return sql.toString();
    }

    private void addFailedNo(HashMap fbhs, String testNo, String msg) {
        String text = (String)(fbhs.get(testNo));
        if (null == text) {
            text = msg;
        }
        else {
            text += '\n' + msg;
        }
        fbhs.put(testNo, text);
    }

    private void deleteRecords(String tableName,Element elTable) throws
        Exception {
    	try {
    		String noList = getNoList(elTable);
    		bdDAO.executeUpdate("DELETE FROM " + tableName + " WHERE TestNo IN " + noList); 
		} catch (Exception e) {
			throw new Exception(tableName + ":" + e.getMessage());
		}
             
    }

    private void submitRecord(String tableName, Element elRecord, HashSet nos, HashMap fbhs, HashMap ftypes, ArrayList imagelist) throws
        Exception {
    	String testNo = elRecord.getAttribute("No");
        if (null != testNo) {
            String sql = generateSQL(tableName, elRecord, ftypes, imagelist);
            try {
            	if (imagelist.size() > 0) {									
					bdDAO.executeUpdate(sql, imagelist);	
				} else {					
					bdDAO.executeUpdate(sql);
				}
            	nos.add(testNo);
			} catch (Exception e) {
				throw new Exception(tableName + ":" + e.getMessage());
			}
                
        }
    }

    private void getFieldTypes(String tableName, HashMap ftypes) throws SQLException {
    	ftypes.clear();
        ResultSetMetaData md = bdDAO.queryMetaData("SELECT TOP 0 * FROM " + tableName);
        String fieldName;
        int fieldType;
        if (null != md) {
        	for (int i = 1; i <= md.getColumnCount(); i++) {
                fieldName = md.getColumnName(i);
                fieldType = md.getColumnType(i);
                ftypes.put(fieldName, new Integer(fieldType));
            }
		}
    }

    private void submitTable(Element elTable, HashSet nos, HashMap fbhs, HashMap ftypes, 
    		ArrayList imagelist) throws SQLException, Exception {
        Node ndRecord = elTable.getFirstChild();
        String tableName = elTable.getAttribute("N");
        if (StringUtil.Null2Blank(tableName).length()>0) {            
            try {
            	deleteRecords(tableName, elTable);
            	getFieldTypes(tableName, ftypes);
			} catch (Exception e) {
				throw new Exception(tableName + ":" + e.getMessage());
			}
			  
              while (null != ndRecord) {
                if (isSpecialElement(ndRecord, "R")) {
                	try {
                		submitRecord(tableName, (Element) ndRecord, nos, fbhs, ftypes, imagelist);
					} catch (Exception e) {
						throw new Exception(tableName + ":" + e.getMessage());
					}
                    
                }
                ndRecord = ndRecord.getNextSibling();
              }
        }
    }

    private String generateResultXml(String mainTableName, HashSet bhs, HashMap fbhs) {
        StringBuffer xml = new StringBuffer();
        String testNo, msg;
        for (Iterator itTestNo = fbhs.keySet().iterator();
             itTestNo.hasNext(); ) {
            testNo = (String) (itTestNo.next());
            bhs.remove(testNo);
        }
        xml.append("<Main Name=\"" + mainTableName + "\">\n");
        xml.append("<ok>\n");
        for (Iterator itTestNo = bhs.iterator(); itTestNo.hasNext();) {
            testNo = (String) (itTestNo.next());
            xml.append("<Record TestNo=\"" + testNo + "\"/>\n");
        }
        xml.append("</ok>\n");
        xml.append("<Failed>\n");
        Iterator itTestNo = fbhs.keySet().iterator();
        for (; itTestNo.hasNext(); ) {
            testNo = (String) (itTestNo.next());
            msg = (String) (fbhs.get(testNo));
            xml.append("<Record TestNo=\"" + testNo + "\">");
            xml.append(msg);
            xml.append("</Record>\n");
        }
        xml.append("</Failed>\n");
        xml.append("</Main>\n");
        return xml.toString();
    }

    private String generateExceptionsXml(ArrayList exceps) {
        StringBuffer xml = new StringBuffer();
        String exMsg;
        for (Iterator itExceptions = exceps.iterator(); itExceptions.hasNext(); ) {
            exMsg = (String)(itExceptions.next());
            xml.append("<Exception>" + exMsg + "</Exception>\n");
        }
        return xml.toString();
    }

    public String submit(Element elData) {
        HashSet nos = new HashSet();
        HashMap failedNos = new HashMap();
        HashMap fieldTypes = new HashMap();
        ArrayList exceptions= new ArrayList();
        ArrayList imagedataList = new ArrayList();

        Node ndMain, ndTable;
        Element elMain;
        String mainTableName;
        ndMain = elData.getFirstChild();
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"gbk\"?>\n");
        xml.append("<Data>\n");
        while (null != ndMain) {
            //Main -> M
            if (isSpecialElement(ndMain, "M")) {
                elMain = (Element) ndMain;
                //Name -> N
                mainTableName = elMain.getAttribute("N");
                if (null != mainTableName) {
                    nos.clear();
                    failedNos.clear();
                    ndTable = ndMain.getFirstChild();
                    while (null != ndTable) {
                        //Table -> T
                        if (isSpecialElement(ndTable, "T")) {
                            try {
                            	submitTable( (Element) ndTable, nos, failedNos, fieldTypes, imagedataList);
                            }
                            catch (Exception ex) {
                                exceptions.add(ex.getMessage());
                            }
                        }
                        ndTable = ndTable.getNextSibling();
                    }
                    xml.append(generateResultXml(mainTableName, nos, failedNos));
                }
            }
            ndMain = ndMain.getNextSibling();
        }
        xml.append("<Other>\n");
        xml.append(generateExceptionsXml(exceptions));
        xml.append("</Other>\n");
        xml.append("</Data>");
        return xml.toString();
    }
}
