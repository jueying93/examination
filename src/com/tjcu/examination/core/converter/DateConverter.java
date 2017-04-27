/**
 * 
 */
package com.tjcu.examination.core.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @author Administrator
 *
 */
public class DateConverter extends StrutsTypeConverter {
 
	private static String DATE_TIME_FOMART = "yyyy-MM-dd HH:mm";

	private static String DATE_FOMART = "yyyy-MM-dd";
	/*@Override  
    public Object convertValue(Map<String, Object> context, Object value,  
            Class toType) {  
          
            value:被转换的数据，由于struts2需要接受所有的请求参数，比如复选框  
            toType:将要转换的类型  
           
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FOMART );  
        if(toType == Date.class) {  
              
             *  由于struts2需要接受所有的请求参数，比如复选框，这就导致一个参数名称对应多个值，  
             *  所以框架采用getParamterValues方法获取参数值，这就导致获取到的为字符串数组  
             *  所以value为字符串数组  
               
            String[] strs = (String[])value;  
            Date time = null;  
            try {  
                time = dateFormat.parse(strs[0]);  
            } catch (ParseException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
            return time;  
        } else if(toType == String.class){  
            Date date = (Date)value;  
            String time = dateFormat.format(date);  
            return time;  
        }  
        return null;  
    }  */
	 @Override
	 public Object convertFromString(Map context, String[] values, Class toClass) {
		 
		 
		 /*  
         value:被转换的数据，由于struts2需要接受所有的请求参数，比如复选框  
         toType:将要转换的类型  
      */  
		 System.out.println("转换"+values.toString());
     SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FOMART );  
     if(toClass == Date.class) {  
         /*  
          *  由于struts2需要接受所有的请求参数，比如复选框，这就导致一个参数名称对应多个值，  
          *  所以框架采用getParamterValues方法获取参数值，这就导致获取到的为字符串数组  
          *  所以value为字符串数组  
          */  
           
         Date time = null;  
         try {  
             time = dateFormat.parse(values[0]);  
         } catch (ParseException e) {  
             e.printStackTrace();  
             throw new RuntimeException(e);  
         }  
         return time;  
     } 
     return null; 

	  /*Date date = null;

	  String dateString = null;

	  if (values != null && values.length > 0) {
	   dateString = values[0];
	   if (dateString != null) {
	    if (date == null) {

	     SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FOMART);

	     try {

	      date = format.parse(dateString);

	     } catch (ParseException e) {

	      date = null;
	     }
	    }
	   }
	  }
	  return date;*/
	 }

	 @Override
	 public String convertToString(Map arg0, Object arg1) {

	  // TODO Auto-generated method stub

	  return null;

	 

	}




}
