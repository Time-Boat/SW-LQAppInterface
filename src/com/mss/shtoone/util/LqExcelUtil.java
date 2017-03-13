package com.mss.shtoone.util;

import java.io.File;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.CellFormat;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LqExcelUtil {
	static Log logger = LogFactory.getLog(LqExcelUtil.class);
	
	public static void exportListExcel(String modelName, String excelName,String banhezhanminchen,
			String [] header,List<String> dataList){
		
		try {
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);	
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 0);
			a.setString(String.format("%s沥青拌和站生产情况统计表", banhezhanminchen));
			WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
			NumberFormat format = new NumberFormat("###,##0.00"); //NumberFormat是jxl.write.NumberFormat   
			WritableCellFormat cellFormat1 = new WritableCellFormat(font,format);   
			WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
			cellFormat1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border   
			cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border
			 //把水平对齐方式指定为居中 
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat2.setWrap(true);
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#"); 
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf);
			//生成标题
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(i,1);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(i,1,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}
			}
			
			//生成数据
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (k > 2 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.NUMBER) {
							   jxl.write.Number nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							jxl.write.Number nb=new jxl.write.Number(k,j+2,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k,j+2,dataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
					}					
				}
				
				if (j == dataList.size()-1) {
					  for (int m = 0; m < dataArray.length; m++) {
						if (m == 0) {
							Label label=new Label(0,j+3,"合计");
							sheet.mergeCells(0,j+3,2,j+3);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						} else {
							if (m > 0) {
								  modiFormulaCell(sheet, m, j+3, 3, j+3, cellFormat2);
							  }
						}
					  }
					}
			}
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private static void modiFormulaCell(WritableSheet dataSheet, int col, int row, int startPos, int endPos, CellFormat format) {
        String f = getFormula(col, row, startPos, endPos);
        // 插入公式（只支持插入，不支持修改）
        WritableCell cell = dataSheet.getWritableCell(col, row);
        if (cell.getType() == CellType.EMPTY)
        {                    
            // 公式单元格
            Formula lbl = new Formula(col, row, f);
            if(null != format)
            {
                lbl.setCellFormat(format);
            } else
            {
                lbl.setCellFormat(cell.getCellFormat());
            }
            try {
				dataSheet.addCell(lbl);
			} catch (RowsExceededException e) {
				
			} catch (WriteException e) {
				
			}
        }
    }
	
	/**
     * 得到公式
     * @param col int : 列
     * @param row int : 行
     * @param startPos int : 开始位置
     * @param endPos int : 结束位置
     * @return String
     */
    private static String getFormula(int col, int row, int startPos, int endPos) {
        char base = 'A';
        char c1 = base;
        StringBuffer formula = new StringBuffer(128);
        // 组装公式
        formula.append("SUM(");
        if (col <= 25)
        {
            c1 = (char) (col % 26 + base);
            formula.append(c1).append(startPos).append(":")
                   .append(c1).append(endPos).append(")");
        } else if (col > 25)
        {
            char c2 = (char) ((col - 26) / 26 + base);
            c1 = (char) ((col - 26) % 26 + base);
            formula.append(c2).append(c1).append(startPos).append(":")
                   .append(c2).append(c1).append(endPos).append(")");
        }

        return formula.toString();
    }
    
    public static void exportPbListExcel(String modelName, String excelName,String banhezhanminchen,
			String [] header,List<String> dataList,List<String> phbsjdataList,List<String> phbdataList,List<String> wcdataList){
		
		try {
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);	
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 0);
			a.setString(String.format("%s沥青拌和站生产配合比查询统计表", banhezhanminchen));
			WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
			NumberFormat format = new NumberFormat("###,##0.00"); //NumberFormat是jxl.write.NumberFormat   
			WritableCellFormat cellFormat1 = new WritableCellFormat(font,format);   
			WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
			cellFormat1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border   
			cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border
			 //把水平对齐方式指定为居中 
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat2.setWrap(true);
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#"); 
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf);
			//生成标题
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(i,1);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(i,1,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}
			}
			
			//生成数据
			int allrow = 4*dataList.size();
			for(int j=0;j<allrow;){
				int n = (int)(j/4);
				String data=(String)dataList.get(n);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				
				String phbsjdata=(String)phbsjdataList.get(n);//获得一条数据
				String [] phbsjdataArray=phbsjdata.split("\\&\\^\\&");//分解数据
				
				String phbdata=(String)phbdataList.get(n);//获得一条数据
				String [] phbdataArray=phbdata.split("\\&\\^\\&");//分解数据
				
				String wcdata=(String)wcdataList.get(n);//获得一条数据
				String [] wcdataArray=wcdata.split("\\&\\^\\&");//分解数据
				
				for(int k=0;k<dataArray.length;k++){
					if (k > 9 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						jxl.write.Number nb = null;
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+2,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					    cell = sheet.getCell(k,j+3);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(phbsjdataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+3,Double.parseDouble(phbsjdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
						cell = sheet.getCell(k,j+4);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(phbdataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+4,Double.parseDouble(phbdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
						cell = sheet.getCell(k,j+5);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(wcdataArray[k]));
						} else {						
						    nb=new jxl.write.Number(k,j+5,Double.parseDouble(wcdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k,j+2,dataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
					    cell = sheet.getCell(k,j+3);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(phbsjdataArray[k]);
						} else {						
							Label label=new Label(k,j+3,phbsjdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
						cell = sheet.getCell(k,j+4);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(phbdataArray[k]);
						} else {						
							Label label=new Label(k,j+4,phbdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
						cell = sheet.getCell(k,j+5);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(wcdataArray[k]);
						} else {						
							Label label=new Label(k,j+5,wcdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
					}
				}
			j = j+4;
			}
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
    
    public static void exportChaoBiaoListExcel(String modelName, String excelName,String banhezhanminchen,
			String [] header,List<String> dataList,List<String> phbsjdataList,List<String> phbdataList,List<String> wcdataList,List<String> dataList1,List<String> phbsjdataList1,List<String> phbdataList1,List<String> wcdataList1){
		
		try {
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);	
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 0);
			a.setString(String.format("%s沥青拌和站生产超标查询统计表", banhezhanminchen));
			WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
			NumberFormat format = new NumberFormat("###,##0.00"); //NumberFormat是jxl.write.NumberFormat   
			WritableCellFormat cellFormat1 = new WritableCellFormat(font,format);   
			WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
			cellFormat1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border   
			cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border
			 //把水平对齐方式指定为居中 
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat2.setWrap(true);
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#"); 
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf);
			//生成标题
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(i,1);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(i,1,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}
			}
			
			//生成数据
			int allrow = 4*dataList.size();
			for(int j=0;j<allrow;){
				int n = (int)(j/4);
				String data=(String)dataList.get(n);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				
				String phbsjdata=(String)phbsjdataList.get(n);//获得一条数据
				String [] phbsjdataArray=phbsjdata.split("\\&\\^\\&");//分解数据
				
				String phbdata=(String)phbdataList.get(n);//获得一条数据
				String [] phbdataArray=phbdata.split("\\&\\^\\&");//分解数据
				
				String wcdata=(String)wcdataList.get(n);//获得一条数据
				String [] wcdataArray=wcdata.split("\\&\\^\\&");//分解数据
				
				for(int k=0;k<dataArray.length;k++){
					if (k > 9 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						jxl.write.Number nb = null;
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+2,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					    cell = sheet.getCell(k,j+3);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(phbsjdataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+3,Double.parseDouble(phbsjdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
						cell = sheet.getCell(k,j+4);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(phbdataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+4,Double.parseDouble(phbdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
						cell = sheet.getCell(k,j+5);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(wcdataArray[k]));
						} else {						
						    nb=new jxl.write.Number(k,j+5,Double.parseDouble(wcdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k,j+2,dataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
					    cell = sheet.getCell(k,j+3);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(phbsjdataArray[k]);
						} else {						
							Label label=new Label(k,j+3,phbsjdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
						cell = sheet.getCell(k,j+4);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(phbdataArray[k]);
						} else {						
							Label label=new Label(k,j+4,phbdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
						cell = sheet.getCell(k,j+5);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(wcdataArray[k]);
						} else {						
							Label label=new Label(k,j+5,wcdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
					}
				}
			j = j+4;
			}
			
			//生成数据
			int allrow1 = 4*dataList.size()+4*dataList1.size();
			for(int j=4*dataList.size();j<allrow1;){
				int n = (int)((j-4*dataList.size())/4);
				String data=(String)dataList1.get(n);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				
				String phbsjdata=(String)phbsjdataList1.get(n);//获得一条数据
				String [] phbsjdataArray=phbsjdata.split("\\&\\^\\&");//分解数据
				
				String phbdata=(String)phbdataList1.get(n);//获得一条数据
				String [] phbdataArray=phbdata.split("\\&\\^\\&");//分解数据
				
				String wcdata=(String)wcdataList1.get(n);//获得一条数据
				String [] wcdataArray=wcdata.split("\\&\\^\\&");//分解数据
				
				for(int k=0;k<dataArray.length;k++){
					if (k > 9 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						jxl.write.Number nb = null;
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+2,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					    cell = sheet.getCell(k,j+3);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(phbsjdataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+3,Double.parseDouble(phbsjdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
						cell = sheet.getCell(k,j+4);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(phbdataArray[k]));
						} else {						
							   nb=new jxl.write.Number(k,j+4,Double.parseDouble(phbdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
						cell = sheet.getCell(k,j+5);
						if (cell.getType()==CellType.NUMBER) {
							   nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(wcdataArray[k]));
						} else {						
						    nb=new jxl.write.Number(k,j+5,Double.parseDouble(wcdataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k,j+2);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k,j+2,dataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
					    cell = sheet.getCell(k,j+3);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(phbsjdataArray[k]);
						} else {						
							Label label=new Label(k,j+3,phbsjdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
						cell = sheet.getCell(k,j+4);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(phbdataArray[k]);
						} else {						
							Label label=new Label(k,j+4,phbdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
						
						cell = sheet.getCell(k,j+5);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(wcdataArray[k]);
						} else {						
							Label label=new Label(k,j+5,wcdataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
					}
				}
			j = j+4;
			}
			
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
    
    public static void importclktempExcel(String modelName, String excelName,
    		String top, List<String> dataList,String bhzname) {
    	try{
    		//Excel获得文件
    		Workbook wb=Workbook.getWorkbook(new File(modelName)); 
    		//打开一个文件的副本，并且指定数据写回到原文件
    		WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);  		
    		WritableSheet sheet = book.getSheet(0);
			if (null != bhzname && bhzname.equalsIgnoreCase("-请选择设备-")) {
				bhzname = "出料口温度检测";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s统计表", bhzname));
			a=(Label)sheet.getWritableCell(0, 2);
			a.setString(top);
    		WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
    		WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
    		cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
    		 //把水平对齐方式指定为居中 
    		cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
    		//把垂直对齐方式指定为居中 
    		cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
    		//设置自动换行
    		cellFormat2.setWrap(true);
    		jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
    		jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
    		
    		for(int j=0;j<dataList.size();j++){
    			String data=(String)dataList.get(j);//获得一条数据
    			String [] dataArray=data.split("\\&\\^\\&");//分解数据
    			for(int k=0;k<dataArray.length;k++){
    				if (StringUtil.Null2Blank(dataArray[k]).length() > 0 && k>1) {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.NUMBER) {
    						   jxl.write.Number nb=(jxl.write.Number) cell;
    					       nb.setValue(Double.parseDouble(dataArray[k]));
    					} else {						
    						jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
    						nb.setCellFormat(cellFormat2); 
    						sheet.addCell(nb);
    					}
    				} else {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.LABEL) {
    					       Label label=(Label) cell;
    					       label.setString(dataArray[k]);
    					} else {						
    						Label label=new Label(k,j+5,dataArray[k]);
    						label.setCellFormat(cellFormat2); 
    						sheet.addCell(label);
    					}
    				}				
    				
    			}
    		}
    		//写入数据并关闭文件 
    		book.write();
    		book.close();
    		wb.close();
    	}catch(Exception e){
    	}
    }
    
    public static void importtanputempExcel(String modelName, String excelName,
    		String top, List<String> dataList,String bhzname) {
    	try{
    		//Excel获得文件
    		Workbook wb=Workbook.getWorkbook(new File(modelName)); 
    		//打开一个文件的副本，并且指定数据写回到原文件
    		WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);  		
    		WritableSheet sheet = book.getSheet(0);
			if (null != bhzname && bhzname.equalsIgnoreCase("-请选择设备-")) {
				bhzname = "摊铺机温度";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s统计表", bhzname));
			a=(Label)sheet.getWritableCell(0, 2);
			a.setString(top);
    		WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
    		WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
    		cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
    		 //把水平对齐方式指定为居中 
    		cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
    		//把垂直对齐方式指定为居中 
    		cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
    		//设置自动换行
    		cellFormat2.setWrap(true);
    		jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
    		jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
    		
    		for(int j=0;j<dataList.size();j++){
    			String data=(String)dataList.get(j);//获得一条数据
    			String [] dataArray=data.split("\\&\\^\\&");//分解数据
    			for(int k=0;k<dataArray.length;k++){
    				if (StringUtil.Null2Blank(dataArray[k]).length() > 0 && k>1) {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.NUMBER) {
    						   jxl.write.Number nb=(jxl.write.Number) cell;
    					       nb.setValue(Double.parseDouble(dataArray[k]));
    					} else {						
    						jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
    						nb.setCellFormat(cellFormat2); 
    						sheet.addCell(nb);
    					}
    				} else {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.LABEL) {
    					       Label label=(Label) cell;
    					       label.setString(dataArray[k]);
    					} else {						
    						Label label=new Label(k,j+5,dataArray[k]);
    						label.setCellFormat(cellFormat2); 
    						sheet.addCell(label);
    					}
    				}				
    				
    			}
    		}
    		//写入数据并关闭文件 
    		book.write();
    		book.close();
    		wb.close();
    	}catch(Exception e){
    	}
    }
    
    public static void importyalutempExcel(String modelName, String excelName,
    		String top, List<String> dataList,String bhzname) {
    	try{
    		//Excel获得文件
    		Workbook wb=Workbook.getWorkbook(new File(modelName)); 
    		//打开一个文件的副本，并且指定数据写回到原文件
    		WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);  		
    		WritableSheet sheet = book.getSheet(0);
			if (null != bhzname && bhzname.equalsIgnoreCase("-请选择设备-")) {
				bhzname = "碾压温度";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s统计表", bhzname));
			a=(Label)sheet.getWritableCell(0, 2);
			a.setString(top);
    		WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
    		WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
    		cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
    		 //把水平对齐方式指定为居中 
    		cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
    		//把垂直对齐方式指定为居中 
    		cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
    		//设置自动换行
    		cellFormat2.setWrap(true);
    		jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
    		jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
    		
    		for(int j=0;j<dataList.size();j++){
    			String data=(String)dataList.get(j);//获得一条数据
    			String [] dataArray=data.split("\\&\\^\\&");//分解数据
    			for(int k=0;k<dataArray.length;k++){
    				if (StringUtil.Null2Blank(dataArray[k]).length() > 0 && k>1) {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.NUMBER) {
    						   jxl.write.Number nb=(jxl.write.Number) cell;
    					       nb.setValue(Double.parseDouble(dataArray[k]));
    					} else {						
    						jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
    						nb.setCellFormat(cellFormat2); 
    						sheet.addCell(nb);
    					}
    				} else {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.LABEL) {
    					       Label label=(Label) cell;
    					       label.setString(dataArray[k]);
    					} else {						
    						Label label=new Label(k,j+5,dataArray[k]);
    						label.setCellFormat(cellFormat2); 
    						sheet.addCell(label);
    					}
    				}				
    				
    			}
    		}
    		//写入数据并关闭文件 
    		book.write();
    		book.close();
    		wb.close();
    	}catch(Exception e){
    	}
    }
    
    public static void importtanpuspeedExcel(String modelName, String excelName,
    		String top, List<String> dataList,String bhzname) {
    	try{
    		//Excel获得文件
    		Workbook wb=Workbook.getWorkbook(new File(modelName)); 
    		//打开一个文件的副本，并且指定数据写回到原文件
    		WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);  		
    		WritableSheet sheet = book.getSheet(0);
			if (null != bhzname && bhzname.equalsIgnoreCase("-请选择设备-")) {
				bhzname = "摊铺机速度";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s统计表", bhzname));
			a=(Label)sheet.getWritableCell(0, 2);
			a.setString(top);
    		WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
    		WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
    		cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
    		 //把水平对齐方式指定为居中 
    		cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
    		//把垂直对齐方式指定为居中 
    		cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
    		//设置自动换行
    		cellFormat2.setWrap(true);
    		jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
    		jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
    		
    		for(int j=0;j<dataList.size();j++){
    			String data=(String)dataList.get(j);//获得一条数据
    			String [] dataArray=data.split("\\&\\^\\&");//分解数据
    			for(int k=0;k<dataArray.length;k++){
    				if (StringUtil.Null2Blank(dataArray[k]).length() > 0 && k>1) {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.NUMBER) {
    						   jxl.write.Number nb=(jxl.write.Number) cell;
    					       nb.setValue(Double.parseDouble(dataArray[k]));
    					} else {						
    						jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
    						nb.setCellFormat(cellFormat2); 
    						sheet.addCell(nb);
    					}
    				} else {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.LABEL) {
    					       Label label=(Label) cell;
    					       label.setString(dataArray[k]);
    					} else {						
    						Label label=new Label(k,j+5,dataArray[k]);
    						label.setCellFormat(cellFormat2); 
    						sheet.addCell(label);
    					}
    				}				
    				
    			}
    		}
    		//写入数据并关闭文件 
    		book.write();
    		book.close();
    		wb.close();
    	}catch(Exception e){
    	}
    }
    
    public static void importnianyaspeedExcel(String modelName, String excelName,
    		String top, List<String> dataList,String bhzname) {
    	try{
    		//Excel获得文件
    		Workbook wb=Workbook.getWorkbook(new File(modelName)); 
    		//打开一个文件的副本，并且指定数据写回到原文件
    		WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);  		
    		WritableSheet sheet = book.getSheet(0);
			if (null != bhzname && bhzname.equalsIgnoreCase("-请选择设备-")) {
				bhzname = "碾压速度";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s统计表", bhzname));
			a=(Label)sheet.getWritableCell(0, 2);
			a.setString(top);
    		WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
    		WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
    		cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
    		 //把水平对齐方式指定为居中 
    		cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
    		//把垂直对齐方式指定为居中 
    		cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
    		//设置自动换行
    		cellFormat2.setWrap(true);
    		jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
    		jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
    		
    		for(int j=0;j<dataList.size();j++){
    			String data=(String)dataList.get(j);//获得一条数据
    			String [] dataArray=data.split("\\&\\^\\&");//分解数据
    			for(int k=0;k<dataArray.length;k++){
    				if (StringUtil.Null2Blank(dataArray[k]).length() > 0 && k>1) {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.NUMBER) {
    						   jxl.write.Number nb=(jxl.write.Number) cell;
    					       nb.setValue(Double.parseDouble(dataArray[k]));
    					} else {						
    						jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
    						nb.setCellFormat(cellFormat2); 
    						sheet.addCell(nb);
    					}
    				} else {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.LABEL) {
    					       Label label=(Label) cell;
    					       label.setString(dataArray[k]);
    					} else {						
    						Label label=new Label(k,j+5,dataArray[k]);
    						label.setCellFormat(cellFormat2); 
    						sheet.addCell(label);
    					}
    				}				
    				
    			}
    		}
    		//写入数据并关闭文件 
    		book.write();
    		book.close();
    		wb.close();
    	}catch(Exception e){
    	}
    }
    
    public static void importEnvironmentExcel(String modelName, String excelName,
    		String top, List<String> dataList) {
    	try{
    		//Excel获得文件
    		Workbook wb=Workbook.getWorkbook(new File(modelName)); 
    		//打开一个文件的副本，并且指定数据写回到原文件
    		WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
    		WritableSheet sheet = book.getSheet(0);
    		Label a= (Label)sheet.getWritableCell(0, 1);
    		a = (Label)sheet.getWritableCell(0, 2);
    		a.setString(top);
    		WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
    		WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
    		cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
    		 //把水平对齐方式指定为居中 
    		cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
    		//把垂直对齐方式指定为居中 
    		cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
    		//设置自动换行
    		cellFormat2.setWrap(true);
    		jxl.write.NumberFormat format = new jxl.write.NumberFormat("#.#"); 
    		jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
    		
    		for(int j=0;j<dataList.size();j++){
    			String data=(String)dataList.get(j);//获得一条数据
    			String [] dataArray=data.split("\\&\\^\\&");//分解数据
    			for(int k=0;k<dataArray.length;k++){
    				if (StringUtil.Null2Blank(dataArray[k]).length() > 0 && k>1 && k<4) {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.NUMBER) {
    						   jxl.write.Number nb=(jxl.write.Number) cell;
    					       nb.setValue(Double.parseDouble(dataArray[k]));
    					} else {						
    						jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
    						nb.setCellFormat(cellFormat2); 
    						sheet.addCell(nb);
    					}
    				} else {
    					Cell cell = sheet.getCell(k,j+5);
    					if (cell.getType()==CellType.LABEL) {
    					       Label label=(Label) cell;
    					       label.setString(dataArray[k]);
    					} else {						
    						Label label=new Label(k,j+5,dataArray[k]);
    						label.setCellFormat(cellFormat2); 
    						sheet.addCell(label);
    					}
    				}				
    				
    			}
    		}
    		//写入数据并关闭文件 
    		book.write();
    		book.close();
    		wb.close();
    	}catch(Exception e){
    	}
    }
}
