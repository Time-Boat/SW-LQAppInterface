package com.mss.shtoone.util;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

public class HntExcelUtil {
	static Log logger = LogFactory.getLog(HntExcelUtil.class);	
	public static void importListExcel(String modelName, String excelName,String banhezhanminchen,
			String [] header,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);			
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 0);
			a.setString(String.format("%s拌和站砼生产情况统计表", banhezhanminchen));
			//sheet.addCell(label);
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
				//Label h = (Label)sheet.getWritableCell(i, 1);
				//h.setString(header[i]);
				//Blank eh = (Blank)sheet.getWritableCell(i, 1);
				
				//Label label=new Label(i,1,header[i]);//参数：列，行，文本
				//sheet.addCell(label);
			}
			//生成数据
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (k > 4 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
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
					//Label d = (Label)sheet.getWritableCell(k, j+2);
					//d.setString(dataArray[k]);
					//Label label=new Label(k,j+2,dataArray[k]);//创建一列数据
					//sheet.addCell(label);//添加到工作表中
				}
				
				if (j == dataList.size()-1) {
					  for (int m = 0; m < dataArray.length; m++) {
						if (m == 0) {
							Label label=new Label(0,j+3,"合计");
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
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	public static void importjieguoListExcel(String modelName, String excelName,String banhezhanminchen,
			String [] header,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);			
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 0);
			a.setString(String.format("%s拌和站预警统计表", banhezhanminchen));
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
				Cell cell = sheet.getCell(i,2);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(i,2,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}
	
			}
			//生成数据
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					Label label=new Label(k,j+3,dataArray[k]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}			
				for(int i=0;i<header.length;i++){
					Cell cell = sheet.getCell(i,j+3);
					if (cell.getType()==CellType.EMPTY) {
						Label label=new Label(i,j+3,"");
						label.setCellFormat(cellFormat2); 
						sheet.addCell(label);
					} 
				}
			}
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

    /**
     * 修改公式单元格的值
     * @param dataSheet WritableSheet : 工作表
     * @param col int : 列
     * @param row int : 行
     * @param startPos int : 开始位置
     * @param endPos int : 结束位置
     * @param format
     * @throws RowsExceededException
     * @throws WriteException
     */
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
    
	public static void importWuchaExcel(String modelName, String excelName,String banhezhanminchen,
			String shijian, String [] header,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);			
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s拌和站配合比材料消耗计算表", banhezhanminchen));
			Label b= (Label)sheet.getWritableCell(0, 2);
			b.setString(shijian);
			WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
			WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
			cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border
			 //把水平对齐方式指定为居中 
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat2.setWrap(true);
			jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
			//生成标题
			int cc = 4;
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(cc,5);
				cc = cc + 3;
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
				if (j > 14) {
					sheet.insertRow(j+7);
				}
				for(int k=0;k<dataArray.length;k++){
					if (k > 2 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						Cell cell = sheet.getCell(k,j+7);
						if (cell.getType()==CellType.NUMBER) {
							   jxl.write.Number nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							jxl.write.Number nb=new jxl.write.Number(k,j+7,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k,j+7);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k,j+7,dataArray[k]);
							label.setCellFormat(cellFormat2); 
							sheet.addCell(label);
						}
					}
				}
				if (j == dataList.size()-1) {
				  for (int m = 0; m < dataArray.length; m++) {
					if (m == 0) {
						Label label=new Label(0,j+8,"合计");
						label.setCellFormat(cellFormat2); 
						sheet.addCell(label);
					} else {
						if (m > 2) {
							  modiFormulaCell(sheet, m, j+8, 8, j+8, cellFormat2);
						  }
					}
					  
				  }
				}
			}
			
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	public static void importListSumExcel(String modelName,String excelName,String banhezhanminchen,
			String shijian,String [] header,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s拌和站材料消耗核算表", banhezhanminchen));
			Label b= (Label)sheet.getWritableCell(0, 2);
			b.setString(shijian);
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
			//生成标题
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(0,i+5);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(0,i+5,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}				
			}
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						Cell cell = sheet.getCell(k+2,j+5);
						if (cell.getType()==CellType.NUMBER) {
							   jxl.write.Number nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							jxl.write.Number nb=new jxl.write.Number(k+2,j+5,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k+2,j+5);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k+2,j+5,dataArray[k]);
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
	
	public static void importdanweigongchenExcel(String modelName,String excelName,
			String shijian,String [] header,List<String> dataList,String biaoti){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString("单位工程材料消耗核算表");
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
			a = (Label)sheet.getWritableCell(0, 3);
			a.setString(String.format("单位工程名称：%s", biaoti));
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
			//生成标题
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(0,i+6);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(0,i+6,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}				
			}
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						Cell cell = sheet.getCell(k+2,j+6);
						if (cell.getType()==CellType.NUMBER) {
							   jxl.write.Number nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							jxl.write.Number nb=new jxl.write.Number(k+2,j+6,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k+2,j+6);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k+2,j+6,dataArray[k]);
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
	
	public static void importxiangmubuExcel(String modelName,String excelName,
			String shijian,String [] header,List<String> dataList,String biaoti){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s材料消耗核算表", biaoti));
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
			a = (Label)sheet.getWritableCell(0, 3);
			a.setString(String.format("项目部名称：%s", biaoti));
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
			//生成标题
			for(int i=0;i<header.length;i++){
				Cell cell = sheet.getCell(0,i+6);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(0,i+6,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}				
			}
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						Cell cell = sheet.getCell(k+2,j+6);
						if (cell.getType()==CellType.NUMBER) {
							   jxl.write.Number nb=(jxl.write.Number) cell;
						       nb.setValue(Double.parseDouble(dataArray[k]));
						} else {						
							jxl.write.Number nb=new jxl.write.Number(k+2,j+6,Double.parseDouble(dataArray[k]),wcf);
							nb.setCellFormat(cellFormat2); 
							sheet.addCell(nb);
						}
					} else {
						Cell cell = sheet.getCell(k+2,j+6);
						if (cell.getType()==CellType.LABEL) {
						       Label label=(Label) cell;
						       label.setString(dataArray[k]);
						} else {						
							Label label=new Label(k+2,j+6,dataArray[k]);
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
	
	public static void importshengchanlExcel(String modelName,String excelName,String bhzmc,
			String shijian,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			Label a= (Label)sheet.getWritableCell(0, 1);
			if (null != bhzmc && bhzmc.equalsIgnoreCase("-请选择拌和站-")) {
				bhzmc = "";
			}
			a.setString(String.format("%s拌和站砼生产量核算表", bhzmc));
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
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
					if (k==6 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
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
	
	public static void importcnfxExcel(String modelName,String excelName,String bhzmc,
			String shijian,String header,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			if (null != bhzmc && bhzmc.equalsIgnoreCase("-请选择拌和站-")) {
				bhzmc = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s拌和站产能分析表", bhzmc));
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
			a = (Label)sheet.getWritableCell(1, 4);
			a.setString(header);
			if("日".equals(header)){
				Label b= (Label)sheet.getWritableCell(0, 4);
				b.setString("月份");
			}
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
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
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
	
	public static void importsmsinfoExcel(String modelName,String excelName,String bhzmc,
			String shijian,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			if (null != bhzmc && bhzmc.equalsIgnoreCase("-请选择拌和站-")) {
				bhzmc = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s拌和站短消息一览表", bhzmc));
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
	
			WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
			WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
			cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			 //把水平对齐方式指定为居中 
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat2.setWrap(true);
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
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
	
	public static void importlqscsjjcExcel(String modelName,String excelName,String bhzmc,
			String shijian,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			Label a= (Label)sheet.getWritableCell(0, 10);
			if (null != bhzmc && bhzmc.equalsIgnoreCase("-请选择拌和站-")) {
				bhzmc = "";
			}
			a.setString(String.format("%s各料仓称量波动检查", bhzmc));
			a = (Label)sheet.getWritableCell(2, 6);
			a.setString(shijian);
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
				for(int k=2;k<dataArray.length;k++){
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						if (j==0 && k==2) {
							Cell zlcell = sheet.getCell(10,7);
							if (zlcell.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) zlcell;
							       nb.setValue(Double.parseDouble(dataArray[0]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(10,7,Double.parseDouble(dataArray[0]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
							
							Cell llysbcell = sheet.getCell(13,11);
							if (llysbcell.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) llysbcell;
							       nb.setValue(Double.parseDouble(dataArray[1]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(13,11,Double.parseDouble(dataArray[1]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
							
							Cell sjysbcell = sheet.getCell(13,14);
							try {
								if (sjysbcell.getType()==CellType.NUMBER) {
									   jxl.write.Number nb=(jxl.write.Number) sjysbcell;
								       nb.setValue(Double.parseDouble(dataArray[9])*100/(Double.parseDouble(dataArray[0])-Double.parseDouble(dataArray[9])));
								} else {						
									jxl.write.Number nb=new jxl.write.Number(13,14,Double.parseDouble(dataArray[9])*100/(Double.parseDouble(dataArray[0])-Double.parseDouble(dataArray[9])),wcf);
									nb.setCellFormat(cellFormat2); 
									sheet.addCell(nb);
								}	
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						Cell cell = sheet.getCell(k,j+11);
						try {
							if (cell.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell;
							       nb.setValue(Double.parseDouble(dataArray[k]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(k,j+11,Double.parseDouble(dataArray[k]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					} 		
				}
			}
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//水稳配合比误差列表
	public static void importShuiWenPhbWCListExcel(String modelName, String excelName,String banhezhanminchen,
			String [] header,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);			
			WritableSheet sheet = book.getSheet(0);
			if (null != banhezhanminchen && banhezhanminchen.equalsIgnoreCase("-请选择拌和站-")) {
				banhezhanminchen = "水稳拌和站";
			}
			if(banhezhanminchen.equals("")&&banhezhanminchen.length()==0){
				banhezhanminchen = "水稳拌和站";
			}
			Label a= (Label)sheet.getWritableCell(0, 0);
			a.setString(String.format("%s配比误差统计表", banhezhanminchen));
			//sheet.addCell(label);
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
			
			sheet.setRowView(1, 500); // 设置行的高度
			//生成标题
			for(int i=0;i<header.length;i++){
				sheet.setColumnView(i, 20);//设置列宽
				Cell cell = sheet.getCell(i,1);
				if (cell.getType()==CellType.LABEL) {
				       Label label=(Label) cell;
				       label.setString(header[i]);
				} else {
					Label label=new Label(i,1,header[i]);
					label.setCellFormat(cellFormat2); 
					sheet.addCell(label);
				}
				//Label h = (Label)sheet.getWritableCell(i, 1);
				//h.setString(header[i]);
				//Blank eh = (Blank)sheet.getWritableCell(i, 1);
				
				//Label label=new Label(i,1,header[i]);//参数：列，行，文本
				//sheet.addCell(label);
			}
			//生成数据
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if(k<header.length){//小于标题长度
						if (k >= 2 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
							Cell cell = sheet.getCell(k,j*4+2);
							if (cell.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell;
							       nb.setValue(Double.parseDouble(dataArray[k]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(k,j*4+2,Double.parseDouble(dataArray[k]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
							
							int i=k+7;
							Cell cell1 = sheet.getCell(k,j*4+3);
							if (cell1.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell1;
							       nb.setValue(Double.parseDouble(dataArray[i]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(k,j*4+3,Double.parseDouble(dataArray[i]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
							
							int m=k+14;
							Cell cell2 = sheet.getCell(k,j*4+4);
							if (cell2.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell2;
							       nb.setValue(Double.parseDouble(dataArray[m]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(k,j*4+4,Double.parseDouble(dataArray[m]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
							
							int n=k+21;
							Cell cell3 = sheet.getCell(k,j*4+5);
							if (cell3.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell3;
							       nb.setValue(Double.parseDouble(dataArray[n]));
							} else {						
								jxl.write.Number nb=new jxl.write.Number(k,j*4+5,Double.parseDouble(dataArray[n]),wcf);
								nb.setCellFormat(cellFormat2); 
								sheet.addCell(nb);
							}
						} else {
							
							Cell cell = sheet.getCell(k,j*4+2);
							if (cell.getType()==CellType.LABEL) {
							       Label label=(Label) cell;
							       label.setString(dataArray[k]);
							} else {						
								Label label=new Label(k,j*4+2,dataArray[k]);
								label.setCellFormat(cellFormat2); 
								sheet.addCell(label);
							}
							
							String row2text="";
							String row3text="";
							String row4text="";
							if(k==0){
								row2text="实际配合比(%)";
								row3text="理论配合比";
								row4text="误差";
							}
							Cell cell1 = sheet.getCell(k,j*4+3);
							if (cell1.getType()==CellType.LABEL) {
							       Label label=(Label) cell1;
							       label.setString(row2text);
							} else {						
								Label label=new Label(k,j*4+3,row2text);
								label.setCellFormat(cellFormat2); 
								sheet.addCell(label);
							}
							
							Cell cell2 = sheet.getCell(k,j*4+4);
							if (cell2.getType()==CellType.LABEL) {
							       Label label=(Label) cell2;
							       label.setString(row3text);
							} else {						
								Label label=new Label(k,j*4+4,row3text);
								label.setCellFormat(cellFormat2); 
								sheet.addCell(label);
							}
							
							Cell cell3 = sheet.getCell(k,j*4+5);
							if (cell3.getType()==CellType.LABEL) {
							       Label label=(Label) cell3;
							       label.setString(row4text);
							} else {						
								Label label=new Label(k,j*4+5,row4text);
								label.setCellFormat(cellFormat2); 
								sheet.addCell(label);
							}
						
						}
					}
					//Label d = (Label)sheet.getWritableCell(k, j+2);
					//d.setString(dataArray[k]);
					//Label label=new Label(k,j+2,dataArray[k]);//创建一列数据
					//sheet.addCell(label);//添加到工作表中
				}
				
				
//				if (j == dataList.size()-1) {
//					  for (int m = 0; m < dataArray.length; m++) {
//						if (m == 0) {
//							Label label=new Label(0,j+3,"合计");
//							label.setCellFormat(cellFormat2); 
//							sheet.addCell(label);
//						} else {
//							if (m > 0) {
//								  modiFormulaCell(sheet, m, j+3, 3, j+3, cellFormat2);
//							  }
//						}
//						  
//					  }
//					}
			}
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	public static void importsmstjExcel(String modelName,String excelName,String bhzmc,
			String shijian,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			if (null != bhzmc && bhzmc.equalsIgnoreCase("-请选择拌和站-")) {
				bhzmc = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s拌和站短消息一览表", bhzmc));
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
	
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
			
			jxl.write.NumberFormat format1 = new jxl.write.NumberFormat("###,##0.00"); 
			WritableCellFormat cellFormat1 = new WritableCellFormat(font,format1);
			cellFormat1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			 //把水平对齐方式指定为居中 
			cellFormat1.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat1.setWrap(true);
			
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
						if (k>1 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
							Cell cell = sheet.getCell(k,j+5);
							if (cell.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell;
							       nb.setValue(Double.parseDouble(dataArray[k]));
							} else {		
								if (k == 5) {
									jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
									nb.setCellFormat(cellFormat1); 
									sheet.addCell(nb);
								} else {
									jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
									nb.setCellFormat(cellFormat2); 
									sheet.addCell(nb);
								}
							}
						} else {
						//if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
							Cell cell = sheet.getCell(k,j+5);
							if (cell.getType()==CellType.LABEL) {
							       Label label=(Label) cell;
							       label.setString(dataArray[k]);
							} else {						
								Label label=new Label(k,j+5,dataArray[k]);
								label.setCellFormat(cellFormat2); 
								sheet.addCell(label);
							}
						//} 
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
	
	public static void importuserlogExcel(String modelName,String excelName,
			String shijian,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			
			Label a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
	
			WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);
			WritableCellFormat cellFormat2 = new WritableCellFormat(font); 
			cellFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			 //把水平对齐方式指定为居中 
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			jxl.write.NumberFormat format = new jxl.write.NumberFormat("#"); 
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(format);
			//设置自动换行
			cellFormat2.setWrap(true);
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
					if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
						if (k == 5) {
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
			}
			//写入数据并关闭文件 
			book.write();
			book.close();
			wb.close();
		}catch(Exception e){
		}
	}
	
	public static void importswsmstjExcel(String modelName,String excelName,String bhzmc,
			String shijian,List<String> dataList){
		try{
			//Excel获得文件
			Workbook wb=Workbook.getWorkbook(new File(modelName)); 
			//打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book=Workbook.createWorkbook(new File(excelName), wb);
			WritableSheet sheet = book.getSheet(0);
			if (null != bhzmc && bhzmc.equalsIgnoreCase("-请选择拌和站-")) {
				bhzmc = "";
			}
			Label a= (Label)sheet.getWritableCell(0, 1);
			a.setString(String.format("%s拌和站短消息一览表", bhzmc));
			a = (Label)sheet.getWritableCell(0, 2);
			a.setString(shijian);
	
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
			
			jxl.write.NumberFormat format1 = new jxl.write.NumberFormat("###,##0.00"); 
			WritableCellFormat cellFormat1 = new WritableCellFormat(font,format1);
			cellFormat1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			 //把水平对齐方式指定为居中 
			cellFormat1.setAlignment(jxl.format.Alignment.CENTRE); 
			//把垂直对齐方式指定为居中 
			cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//设置自动换行
			cellFormat1.setWrap(true);
			
			for(int j=0;j<dataList.size();j++){
				String data=(String)dataList.get(j);//获得一条数据
				String [] dataArray=data.split("\\&\\^\\&");//分解数据
				for(int k=0;k<dataArray.length;k++){
						if (k>1 && StringUtil.Null2Blank(dataArray[k]).length() > 0) {
							Cell cell = sheet.getCell(k,j+5);
							if (cell.getType()==CellType.NUMBER) {
								   jxl.write.Number nb=(jxl.write.Number) cell;
							       nb.setValue(Double.parseDouble(dataArray[k]));
							} else {		
								if (k == 5) {
									jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
									nb.setCellFormat(cellFormat1); 
									sheet.addCell(nb);
								} else {
									jxl.write.Number nb=new jxl.write.Number(k,j+5,Double.parseDouble(dataArray[k]),wcf);
									nb.setCellFormat(cellFormat2); 
									sheet.addCell(nb);
								}
							}
						} else {
						//if (StringUtil.Null2Blank(dataArray[k]).length() > 0) {
							Cell cell = sheet.getCell(k,j+5);
							if (cell.getType()==CellType.LABEL) {
							       Label label=(Label) cell;
							       label.setString(dataArray[k]);
							} else {						
								Label label=new Label(k,j+5,dataArray[k]);
								label.setCellFormat(cellFormat2); 
								sheet.addCell(label);
							}
						//} 
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
