package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return currDate;
	}
	
	public static int getSeparatedDay(Date dateOne,Date dateTwo){
    	String separateDay = ""; 
    	long quot = 0;
    	  try {
    	   if(dateOne != null && dateTwo != null){
    		   dateOne = stringToDate(dateToString(dateOne, "yyyy-MM-dd"), "yyyy-MM-dd");
    		   dateTwo = stringToDate(dateToString(dateTwo, "yyyy-MM-dd"), "yyyy-MM-dd");
    		   quot = dateOne.getTime() - dateTwo.getTime();
        	   quot = Math.abs(quot);
        	   quot = quot / 1000 / 60 / 60 / 24;
        	   separateDay = quot+"";
    	   }
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }
    	  return Integer.parseInt(separateDay);
    }
	
	public static String dateToString(Date date,String dateFormat){
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			String dateStr = format.format(date);
			return dateStr;
		}
		return "";
	}
	
	public static Date stringToDate(String dateStr,String dateFormat){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date _dateForamt(Date date,String dateFormate){
		if(dateFormate == null || "".equals(dateFormate)){
			dateFormate = "yyyy-MM-dd HH:mm:ss";
		}
		return stringToDate(dateToString(date, dateFormate), dateFormate);
	}
	
	public static Date _dateForamt(Date date){
		return _dateForamt(date, null);
	}
	
    public static Date getPreDate(Date date,int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,n);
        return calendar.getTime();
    }
    
    
	public static Date parseFormatLongToDate(long time, String format) {
		if (format == null || format.length() == 0) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatDateTime = new SimpleDateFormat(format);
		return parseDateString(long2DateStr(time, formatDateTime), format);
	}
	
	public static Date parseFormatLongToDate(Long time){
		return parseFormatLongToDate(time,null);
	}
	
	public static Date parseDateString(String str, String format) {
		if (str == null || str.equals("")) {
			return null;
		}
		Date dt = null;
		try {
			DateFormat df = new SimpleDateFormat(format);
			dt = df.parse(str);

		} catch (Exception pe) {
			System.out.println(pe);
		}

		return dt;
	}
	
	@SuppressWarnings("unused")
	public static String long2DateStr(long time, SimpleDateFormat dateFmt) {
		java.sql.Date date = new java.sql.Date(time);

		if (date != null) {
			return dateFmt.format(date);
		}
		return "";
	}

	public static void main(String[] args) {
		
		//Date date1 = stringToDate("2014-09-12 00:00:00", "yyyy-MM-dd");
		//Date date2 = stringToDate("2014-09-20 23:59:59", "yyyy-MM-dd");1413528908059l
		
		//System.out.println(_dateForamt(new Date(), null));
		Date date = parseFormatLongToDate(1413784538821l);
		System.out.println(dateToString(date, "yyyy-MM-dd HH:mm:ss"));
		
		System.out.println(new Date().getTime());
		
		String arch = System.getProperty("sun.arch.data.model");
		System.out.println(arch); 
	}

}
