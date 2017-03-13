package com.mss.shtoone.util;

import java.text.DateFormat;   
import java.text.ParseException;
import java.text.ParsePosition;   
import java.text.SimpleDateFormat;   
import java.util.Calendar;   
import java.util.Date;   
import java.util.GregorianCalendar;   
  
public class GetDate {   
    //锟斤拷4全锟街匡拷锟斤拷 锟斤拷一锟杰ｏ拷锟斤拷锟杰ｏ拷锟斤拷一锟杰碉拷锟斤拷锟斤拷浠�  
    private  int weeks = 0;   
    private int MaxDate;//一锟斤拷锟斤拷锟斤拷锟斤拷锟�  
    private int MaxYear;//一锟斤拷锟斤拷锟斤拷锟斤拷锟�  
       
       
    /**  
     * @param args  
     * @throws ParseException 
     */  
    public static void main(String[] args) throws ParseException {   
    	GetDate tt = new GetDate();   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷:"+tt.getNowTime("yyyy-MM-dd"));   
        System.out.println("锟斤拷取锟斤拷锟斤拷一锟斤拷锟斤拷:"+tt.getMondayOFWeek());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟秸碉拷锟斤拷锟斤拷~:"+tt.getCurrentWeekday());   
        System.out.println("锟斤拷取锟斤拷锟斤拷一锟斤拷锟斤拷:"+tt.getPreviousWeekday());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷:"+tt.getPreviousWeekSunday());   
        System.out.println("锟斤拷取锟斤拷锟斤拷一锟斤拷锟斤拷:"+tt.getNextMonday());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷:"+tt.getNextSunday());   
        System.out.println("锟斤拷锟斤拷锟接︼拷艿锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷:"+tt.getNowTime("yyyy-MM-dd"));   
        System.out.println("锟斤拷取锟斤拷锟铰碉拷一锟斤拷锟斤拷锟斤拷:"+tt.getFirstDayOfMonth());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�"+tt.getDefaultDay());   
        System.out.println("锟斤拷取锟斤拷锟铰碉拷一锟斤拷锟斤拷锟斤拷:"+tt.getPreviousMonthFirst());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟斤拷:"+tt.getPreviousMonthEnd());   
        System.out.println("锟斤拷取锟斤拷锟铰碉拷一锟斤拷锟斤拷锟斤拷:"+tt.getNextMonthFirst());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�"+tt.getNextMonthEnd());   
        System.out.println("锟斤拷取锟斤拷锟斤拷牡锟揭伙拷锟斤拷锟斤拷锟�"+tt.getCurrentYearFirst());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�"+tt.getCurrentYearEnd());   
        System.out.println("锟斤拷取去锟斤拷牡锟揭伙拷锟斤拷锟斤拷锟�"+tt.getPreviousYearFirst());   
        System.out.println("锟斤拷取去锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷:"+tt.getPreviousYearEnd());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�"+tt.getNextYearFirst());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�"+tt.getNextYearEnd());   
        System.out.println("锟斤拷取锟斤拷锟斤拷锟饺碉拷一锟届到锟斤拷锟揭伙拷锟�"+tt.getThisSeasonTime(11));   
        System.out.println("锟斤拷取}锟斤拷锟斤拷锟斤拷之锟斤拷锟斤拷锟斤拷锟斤拷2008-12-1~2008-9.29:"+GetDate.getTwoDay("2012-01-01","2011-01-01")); 
        
        SimpleDateFormat myFormatter = new SimpleDateFormat("MM-dd HH:mm:ss");  
        System.out.println(new Date("8/17/2011"));
        System.out.println("date:"+myFormatter.format(new Date("2014/5/12 17:53:19")));
        
        
        System.out.println(GetDate.getTwoDay("2012-01-01","2011-01-01")+"==="+GetDate.getTwoDay("2012-01-05","2012-01-01"));
        
        
        System.out.println(GetDate.compare_date("12:30:00", "08:30:00", "HH:mm:ss"));
        System.out.println(GetDate.compare_date("05:30:00", "08:30:00", "HH:mm:ss"));
        
//        System.out.println("锟斤拷锟斤拷时锟斤拷:"+GetDate.strToDate("2014-05-06 12:23:34"));
        System.out.println("鏍煎紡鍖栨椂闂�"+GetDate.formatshijian("2014/6/7 12:23:34"));
        
        System.out.println(getCurrentTime());
        
    }   
       
       
    /**  
        * 锟矫碉拷锟斤拷锟斤拷锟斤拷锟节硷拷募锟斤拷锟斤拷锟斤拷 (锟斤拷锟斤拷前锟斤拷锟斤拷锟节达拷锟节猴拷锟斤拷锟斤拷锟斤拷) 
        */  
    public static String getTwoDay(String sj1, String sj2) {   
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");   
        long day = 0;   
        try {   
         java.util.Date date = myFormatter.parse(sj1);   
         java.util.Date mydate = myFormatter.parse(sj2);   
         day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);   
        } catch (Exception e) {   
         return "";   
        }   
        return day + "";   
    }   
    
    	
    /**  
        * 锟斤拷锟揭伙拷锟斤拷锟斤拷冢锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷诩锟斤拷锟斤拷址锟� 
        *   
        * @param sdate  
        * @return  
        */  
    public static String getWeek(String sdate) {   
        // 锟斤拷转锟斤拷为时锟斤拷   
        Date date = GetDate.strToDate(sdate);   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        // int hour=c.get(Calendar.DAY_OF_WEEK);   
        // hour锟叫达拷木锟斤拷锟斤拷锟斤拷诩锟斤拷耍锟斤拷浞段�1~7   
        // 1=锟斤拷锟斤拷锟斤拷 7=锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷   
        return new SimpleDateFormat("EEEE").format(c.getTime());   
    }   
  
    
    /**  
     * 锟斤拷锟斤拷时锟斤拷锟绞斤拷址锟阶拷锟轿憋拷锟�yyyy-MM-dd   
     *   
     * @param strDate 
     * @return  
     */  
	 public static Date strToDate(String strDate) {   
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
	     ParsePosition pos = new ParsePosition(0);   
	     Date strtodate = formatter.parse(strDate, pos);   
	     return strtodate;   
	 }   
 
    /**  
        * 锟斤拷锟斤拷时锟斤拷锟绞斤拷址锟阶拷锟轿憋拷锟�yyyy-MM-dd (锟斤拷式锟皆讹拷) 
        *   
        * @param strDate
        * @param formatterStr  
        * @return  
        */  
    public static Date strToDate(String strDate,String formatterStr) {   
        SimpleDateFormat formatter = new SimpleDateFormat(formatterStr);   
        ParsePosition pos = new ParsePosition(0);   
        Date strtodate = formatter.parse(strDate, pos);   
        return strtodate;   
    } 
    
    /**
     * 锟饺斤拷}锟斤拷时锟斤拷锟街凤拷
     * @param DATE1
     * @param DATE2
     * @param formatStr 
     * @return 1锟斤拷DATE1锟斤拷DATE2前  -1锟斤拷DATE1锟斤拷DATE2锟斤拷  0:锟斤拷锟斤拷
     */
    public static int compare_date(String DATE1, String DATE2,String formatStr) {
    	DateFormat df=new SimpleDateFormat(formatStr); 
    	Calendar c1=Calendar.getInstance(); 
    	Calendar c2=Calendar.getInstance(); 
    	try{  
    		c1.setTime(df.parse(DATE1)); 
    		c2.setTime(df.parse(DATE2)); 
    	}catch(java.text.ParseException e){ 
    		System.err.println("锟斤拷式锟斤拷锟斤拷确"); 
    	}  
    	int result=c1.compareTo(c2); 
    	if(result==0){
    		//System.out.println("c1锟斤拷锟絚2"); 
    	}else if(result<0){    		
    		//System.out.println("c1小锟斤拷c2"); 
    	}else {
    		//	System.out.println("c1锟斤拷锟斤拷c2");
    	}
    	return result;
    }

  
    /**  
        * }锟斤拷时锟斤拷之锟斤拷锟斤拷锟斤拷锟� 
        *   
        * @param date1  
        * @param date2  
        * @return  
        */  
    public static long getDays(String date1, String date2) {   
        if (date1 == null || date1.equals(""))   
         return 0;   
        if (date2 == null || date2.equals(""))   
         return 0;   
        // 转锟斤拷为锟斤拷准时锟斤拷   
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");   
        java.util.Date date = null;   
        java.util.Date mydate = null;   
        try {   
         date = myFormatter.parse(date1);   
         mydate = myFormatter.parse(date2);   
        } catch (Exception e) {   
        }   
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);   
        return day;   
    }   
  
  
  
       
    // 锟斤拷锟姐当锟斤拷锟斤拷锟揭伙拷锟�锟斤拷锟斤拷锟街凤拷   
    public String getDefaultDay(){     
       String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
       Calendar lastDate = Calendar.getInstance();   
       lastDate.set(Calendar.DATE,1);//锟斤拷为锟斤拷前锟铰碉拷1锟斤拷   
       lastDate.add(Calendar.MONTH,1);//锟斤拷一锟斤拷锟铰ｏ拷锟斤拷为锟斤拷锟铰碉拷1锟斤拷   
       lastDate.add(Calendar.DATE,-1);//锟斤拷去一锟届，锟斤拷为锟斤拷锟斤拷锟斤拷锟揭伙拷锟�  
          
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
    // 锟斤拷锟铰碉拷一锟斤拷   
    public String getPreviousMonthFirst(){     
       String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
       Calendar lastDate = Calendar.getInstance();   
       lastDate.set(Calendar.DATE,1);//锟斤拷为锟斤拷前锟铰碉拷1锟斤拷   
       lastDate.add(Calendar.MONTH,-1);//锟斤拷一锟斤拷锟铰ｏ拷锟斤拷为锟斤拷锟铰碉拷1锟斤拷   
       //lastDate.add(Calendar.DATE,-1);//锟斤拷去一锟届，锟斤拷为锟斤拷锟斤拷锟斤拷锟揭伙拷锟�  
          
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
    //锟斤拷取锟斤拷锟铰碉拷一锟斤拷   
    public String getFirstDayOfMonth(){     
       String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
       Calendar lastDate = Calendar.getInstance();   
       lastDate.set(Calendar.DATE,1);//锟斤拷为锟斤拷前锟铰碉拷1锟斤拷   
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
    // 锟斤拷帽锟斤拷锟斤拷锟斤拷锟斤拷盏锟斤拷锟斤拷锟�    
    public String getCurrentWeekday() {   
        weeks = 0;   
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus+6);   
        Date monday = currentDate.getTime();   
           
        ////DateFormat df = DateFormat.getDateInstance();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);   
        return preMonday;   
    }   
       
       
    //锟斤拷取锟斤拷锟斤拷时锟斤拷    
    public static String getNowTime(String dateformat){   
        Date   now   =   new   Date();      
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�     
        String  hehe  = dateFormat.format(now);      
        return hehe;   
    }   
       
    // 锟斤拷玫锟角帮拷锟斤拷锟斤拷氡撅拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�  
    private int getMondayPlus() {   
        Calendar cd = Calendar.getInstance();   
        // 锟斤拷媒锟斤拷锟斤拷锟揭伙拷艿牡诩锟斤拷欤拷锟斤拷锟斤拷锟斤拷堑锟揭伙拷欤拷锟斤拷诙锟斤拷堑诙锟斤拷锟�.....   
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;         //锟斤拷为锟斤拷锟叫癸拷锟斤拷锟揭伙拷锟轿拷锟揭伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷1   
        if (dayOfWeek == 1) {   
            return 0;   
        } else {   
            return 1 - dayOfWeek;   
        }   
    }   
       
    //锟斤拷帽锟斤拷锟揭伙拷锟斤拷锟斤拷锟�  
    public String getMondayOFWeek(){   
         weeks = 0;   
         int mondayPlus = this.getMondayPlus(); 
      ////   System.out.println(mondayPlus+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         GregorianCalendar currentDate = new GregorianCalendar();
       ////  System.out.println(GregorianCalendar.DATE+"!!!!!!!!!!!!!!!!!");
         currentDate.add(GregorianCalendar.DATE, mondayPlus);   
         Date monday = currentDate.getTime();   
       ////  System.out.println(monday+"!!!!!!!!!!!!!!!!!");
         /////DateFormat df = DateFormat.getDateInstance();
         SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
         String preMonday = df.format(monday);   
         return preMonday;   
    }
    
    /**
     * 锟斤拷帽锟斤拷锟街革拷锟斤拷锟斤拷诩锟斤拷锟斤拷锟斤拷锟�
     */   
    public String getDateOFWeek(int weekday){   
         weeks = 0;   
         int mondayPlus = this.getMondayPlus(); 
      ////   System.out.println(mondayPlus+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         GregorianCalendar currentDate = new GregorianCalendar();
       ////  System.out.println(GregorianCalendar.DATE+"!!!!!!!!!!!!!!!!!");
        
         currentDate.add(GregorianCalendar.DATE, mondayPlus+weekday-1);
        
         Date monday = currentDate.getTime();   
       ////  System.out.println(monday+"!!!!!!!!!!!!!!!!!");
         /////DateFormat df = DateFormat.getDateInstance();
         SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
         String preMonday = df.format(monday);   
         return preMonday;   
    }
    
  /**
   * 锟斤拷帽锟斤拷锟街革拷锟斤拷锟斤拷诩锟斤拷锟斤拷锟斤拷锟�--指锟斤拷锟斤拷锟斤拷式
   * weekday     锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷  锟斤拷锟斤拷锟斤拷 1
   * dateFormat  锟斤拷: "yyyy-MM-dd" 锟斤拷 "MM-dd" 锟斤拷 "dd"
   */   
    public String getDateOFWeek(int weekday,String dateFormat){   
         weeks = 0;   
         int mondayPlus = this.getMondayPlus(); 
      ////   System.out.println(mondayPlus+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         GregorianCalendar currentDate = new GregorianCalendar();
       ////  System.out.println(GregorianCalendar.DATE+"!!!!!!!!!!!!!!!!!");
        
         currentDate.add(GregorianCalendar.DATE, mondayPlus+weekday-1);
        
         Date monday = currentDate.getTime();   
       ////  System.out.println(monday+"!!!!!!!!!!!!!!!!!");
         /////DateFormat df = DateFormat.getDateInstance();
         SimpleDateFormat df=new SimpleDateFormat(dateFormat);
         String preMonday = df.format(monday);   
         return preMonday;   
    }
       
  //锟斤拷锟斤拷锟接︼拷艿锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷   
    public String getSaturday() {   
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);   
        Date monday = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    }   
       
 // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷盏锟斤拷锟斤拷锟�  
    public String getPreviousWeekSunday() {   
        weeks=0;   
        weeks--;   
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus+weeks);   
        Date monday = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    }   
  
 // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�  
    public String getPreviousWeekday() {   
        weeks--;   
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);   
        Date monday = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    }   
       
    // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�  
    public String getNextMonday() {   
        weeks++;   
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);   
        Date monday = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    }   
       
 // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷盏锟斤拷锟斤拷锟�  
    public String getNextSunday() {   
           
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7+6);   
        Date monday = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    }   
       
       
       
    private int getMonthPlus(){   
        Calendar cd = Calendar.getInstance();   
        int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);   
        cd.set(Calendar.DATE, 1);//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟铰碉拷一锟斤拷    
        cd.roll(Calendar.DATE, -1);//锟斤拷锟节回癸拷一锟届，也锟斤拷锟斤拷锟斤拷锟揭伙拷锟�   
        MaxDate=cd.get(Calendar.DATE);    
        if(monthOfNumber == 1){   
            return -MaxDate;   
        }else{   
            return 1-monthOfNumber;   
        }   
    }   
       
  //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�  
    public String getPreviousMonthEnd(){   
        String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
       Calendar lastDate = Calendar.getInstance();   
      lastDate.add(Calendar.MONTH,-1);//锟斤拷一锟斤拷锟斤拷   
      lastDate.set(Calendar.DATE, 1);//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟铰碉拷一锟斤拷    
      lastDate.roll(Calendar.DATE, -1);//锟斤拷锟节回癸拷一锟届，也锟斤拷锟角憋拷锟斤拷锟斤拷锟揭伙拷锟�   
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
  //锟斤拷锟斤拷赂锟斤拷碌锟揭伙拷锟斤拷锟斤拷锟斤拷   
    public String getNextMonthFirst(){   
        String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
       Calendar lastDate = Calendar.getInstance();   
      lastDate.add(Calendar.MONTH,1);//锟斤拷一锟斤拷锟斤拷   
      lastDate.set(Calendar.DATE, 1);//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟铰碉拷一锟斤拷    
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
  //锟斤拷锟斤拷赂锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�  
    public String getNextMonthEnd(){   
        String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
       Calendar lastDate = Calendar.getInstance();   
      lastDate.add(Calendar.MONTH,1);//锟斤拷一锟斤拷锟斤拷   
      lastDate.set(Calendar.DATE, 1);//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟铰碉拷一锟斤拷    
      lastDate.roll(Calendar.DATE, -1);//锟斤拷锟节回癸拷一锟届，也锟斤拷锟角憋拷锟斤拷锟斤拷锟揭伙拷锟�   
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
    //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�  
    public String getNextYearEnd(){   
        String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
      Calendar lastDate = Calendar.getInstance();   
      lastDate.add(Calendar.YEAR,1);//锟斤拷一锟斤拷锟斤拷   
      lastDate.set(Calendar.DAY_OF_YEAR, 1);   
     lastDate.roll(Calendar.DAY_OF_YEAR, -1);   
       str=sdf.format(lastDate.getTime());   
       return str;     
    }   
       
  //锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�  
    public String getNextYearFirst(){   
        String str = "";   
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");       
  
      Calendar lastDate = Calendar.getInstance();   
      lastDate.add(Calendar.YEAR,1);//锟斤拷一锟斤拷锟斤拷   
      lastDate.set(Calendar.DAY_OF_YEAR, 1);   
           str=sdf.format(lastDate.getTime());   
      return str;     
           
    }   
       
  //锟斤拷帽锟斤拷锟斤拷卸锟斤拷锟斤拷锟�  
    private int getMaxYear(){   
        Calendar cd = Calendar.getInstance();   
        cd.set(Calendar.DAY_OF_YEAR,1);//锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟斤拷锟揭伙拷锟�  
        cd.roll(Calendar.DAY_OF_YEAR,-1);//锟斤拷锟斤拷锟节回癸拷一锟届。   
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);    
        return MaxYear;   
    }   
       
    private int getYearPlus(){   
        Calendar cd = Calendar.getInstance();   
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);//锟斤拷玫锟斤拷锟斤拷锟揭伙拷锟斤拷械牡诩锟斤拷锟�  
        cd.set(Calendar.DAY_OF_YEAR,1);//锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟斤拷锟揭伙拷锟�  
        cd.roll(Calendar.DAY_OF_YEAR,-1);//锟斤拷锟斤拷锟节回癸拷一锟届。   
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);   
        if(yearOfNumber == 1){   
            return -MaxYear;   
        }else{   
            return 1-yearOfNumber;   
        }   
    }   
  //锟斤拷帽锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�  
    public String getCurrentYearFirst(){   
        int yearPlus = this.getYearPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE,yearPlus);   
        Date yearDay = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preYearDay = df.format(yearDay);   
        return preYearDay;   
    }   
       
       
  //锟斤拷帽锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�*   
    public String getCurrentYearEnd(){   
        Date date = new Date();   
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�     
        String  years  = dateFormat.format(date);      
        return years+"-12-31";   
    }   
       
       
  //锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�*   
    public String getPreviousYearFirst(){   
        Date date = new Date();   
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�     
        String  years  = dateFormat.format(date); int years_value = Integer.parseInt(years);     
        years_value--;   
        return years_value+"-1-1";   
    }   
       
  //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟�  
    public String getPreviousYearEnd(){   
        weeks--;   
        int yearPlus = this.getYearPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE,yearPlus+MaxYear*weeks+(MaxYear-1));   
        Date yearDay = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preYearDay = df.format(yearDay);   
        getThisSeasonTime(11);   
        return preYearDay;   
    }   
       
  //锟斤拷帽锟斤拷锟斤拷锟�  
    public String getThisSeasonTime(int month){   
        int array[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};   
        int season = 1;   
        if(month>=1&&month<=3){   
            season = 1;   
        }   
        if(month>=4&&month<=6){   
            season = 2;   
        }   
        if(month>=7&&month<=9){   
            season = 3;   
        }   
        if(month>=10&&month<=12){   
            season = 4;   
        }   
        int start_month = array[season-1][0];   
        int end_month = array[season-1][2];   
           
        Date date = new Date();   
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�     
        String  years  = dateFormat.format(date);      
        int years_value = Integer.parseInt(years);   
           
        int start_days =1;//years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);   
        int end_days = getLastDayOfMonth(years_value,end_month);   
        String seasonDate = years_value+"-"+start_month+"-"+start_days+";"+years_value+"-"+end_month+"-"+end_days;   
        return seasonDate;   
           
    }   
       
    /**  
     * 锟斤拷取某锟斤拷某锟铰碉拷锟斤拷锟揭伙拷锟� 
     * @param year 锟斤拷  
     * @param month 锟斤拷  
     * @return 锟斤拷锟揭伙拷锟� 
     */  
   private int getLastDayOfMonth(int year, int month) {   
         if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8  
                   || month == 10 || month == 12) {   
               return 31;   
         }   
         if (month == 4 || month == 6 || month == 9 || month == 11) {   
               return 30;   
         }   
         if (month == 2) {   
               if (isLeapYear(year)) {   
                   return 29;   
               } else {   
                   return 28;   
               }   
         }   
         return 0;   
   }   
   /**  
    * 锟角凤拷锟斤拷锟斤拷  
    * @param year 锟斤拷  
    * @return   
    */  
  public boolean isLeapYear(int year) {   
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);   
  }   
  
  /***
   * 锟斤拷锟绞憋拷浯︼拷锟�
   */
  public static String formatshijian(String shijian){
	  SimpleDateFormat myFormatter = new SimpleDateFormat("MM-dd HH:mm:ss");
	  long date=new Date().parse(shijian);
	  return myFormatter.format(date);
  }
  
  public static String getCurrentTime(){
	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	  return df.format(new Date());
  }
  
  public static String TimetmpConvetDateTime(String shijian){
	  return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(shijian) * 1000));
  }
	
  
  public static String formatshijian2(String shijian){
	  int month=0;
	  int day=0;
	  int hour=0;
	  int minute=0;
	  int second=0;
	  StringBuilder sbf=new StringBuilder("");
	  try{
		  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date date=myFormatter.parse(shijian);
		  month=date.getMonth()+1;
		  day=date.getDate();
		  hour=date.getHours();
		  minute=date.getMinutes();
		  second=date.getSeconds();
		  if(month<10){
			  sbf.append("0"+String.valueOf(month));
		  }else{
			  sbf.append(String.valueOf(month));
		  }
		  if(day<10){
			  sbf.append("-"+"0"+String.valueOf(day));
		  }else{
			  sbf.append("-"+String.valueOf(day));
		  }
		  if(hour<10){
			  sbf.append(" "+"0"+String.valueOf(hour));
		  }else{
			  sbf.append(" "+String.valueOf(hour));
		  }
		  if(minute<10){
			  sbf.append(":"+"0"+String.valueOf(minute));
		  }else{
			  sbf.append(":"+String.valueOf(minute));
		  }
		  if(second<10){
			  sbf.append(":"+"0"+String.valueOf(second));
		  }else{
			  sbf.append(":"+String.valueOf(second));
		  }
	  }catch(ParseException ex){}
	  return sbf.toString();
  }
  
}  

