package com.mss.shtoone.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {
	//从sjgl1、sjgl2、sjgl3、sjgl4、sjgl5、sjfl1、sjfl2这几档材料中出现重复料仓，就合并数据
	public static String[] findSameNum(String[] arr,String[] xx) {  
		String[] str=null;
        Map<String, String> map = new HashMap<String, String>(); 
        Map<String, Integer> mapNum = new HashMap<String, Integer>(); 
        for (int i = 0; i < arr.length; i++) {  
        	/* 
             * map.containsKey(Object findKey)  
             * 方法介绍： 如果此映射包含指定键的映射关系，则返回 true。 
             * 说明：map已经包含了findKey的映射关系 则返回true 否则返回false 
             */ 
            if (map.containsKey(arr[i])) {  
                map.put(arr[i], xx[i]+","+map.get(arr[i]));  
                int tempCount = mapNum.get(arr[i]); 
                mapNum.put(arr[i],++tempCount);
            } else { 
            	mapNum.put(arr[i],1);
                map.put(arr[i], xx[i]);  
            }  
        }  
        /* 
         * Map的遍历操作 map.entrySet().iterator(); map.getKey() map.getValue() 
         */  
        Iterator<Entry<String, Integer>> itNum = mapNum.entrySet().iterator();  
        Iterator<Entry<String, String>> it = map.entrySet().iterator();  
        int count = 0; // 全局记录某数字出现的最多的次数  
        String maxCountWord = arr[0]; // 默认出现最多的字符是第一个  
        String TempStr="";
        while (itNum.hasNext()) {  
            Entry<String, Integer> enNum = itNum.next();  
            Entry<String, String> en = it.next();  
            int tempCount = enNum.getValue();  
            if (tempCount > count) {  
                count = tempCount;  
                maxCountWord = enNum.getKey();  
                //在这里才将第一个假设默认为出现最多的字符给踢掉
                if(count>1){
                	TempStr=en.getValue();
                }
            }  
        }
        //这里表示有字段在这里出现过两次
        if(count>1){
        	str=TempStr.split(",");
        }
        return str;
    }  
  
	//从15个筛孔中，如果有小的筛孔通过率=100，那么大的筛孔的通过率也就复制为100
	
  
    public static void main(String[] args) {  
//        String[] arr = { "1-3碎石", "1-2碎石", "5-10mm", "0-5石粉", "0-5石粉"};  
        String[] arr1 = {  "0-5石粉", "0-5石粉","1-3碎石", "1-2碎石", "5-10mm"};  
//        String[] arr2 = {"1-3碎石", "0-5石粉", "0-5石粉","1-2碎石", "5-10mm"};  
//        String[] arr3 = {"1-3碎石", "0-5石粉", "1-5石粉","1-2碎石", "5-10mm"};  
        String[] xx={"sjgl1","sjgl2","sjgl3","sjgl4","sjgl5"};
        String[] str=MapUtil.findSameNum(arr1,xx);  
        if(str!=null){
	    	for(int i=0;i<str.length;i++){
	    		System.out.println("重复出现的字段:"+(str[i]));
	    	}
        }else{
        	System.out.println("未出现重复字段！！");
        }
        String [] floatArr={"1.6","","","","","2.7","11.5","95.4","","100.0","","","","",""};
        int temp=0;
        for(int i=0;i<floatArr.length;i++){
        	if(Float.parseFloat(StringUtil.Null2Zero(floatArr[i]))==100){
        		temp=i;
        	}
        	if(temp!=0 && i>temp){
        		floatArr[i]="100.0";
        	}
        }
        System.out.println(temp);
        for(int i=0;i<floatArr.length;i++){
        	System.out.print(floatArr[i]+"  ");
        }
    }  
}
