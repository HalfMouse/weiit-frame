package com.weiit.resource.common.utils;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


 
public class DateUtil
{
  public static final SimpleDateFormat DF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
  public static final String patternA = "yyyy-MM-dd";
  public static final String patternB = "yyyyMMdd";
  public static final String patternC = "yyyy-MM-dd HH-mm-ss";
  public static final String patternD = "yyyy-MM-dd HH:mm:ss";
  public static final String patternE = "yyyy-MM-dd HH:mm";
  public static final String patternF = "yyyyMMddHHmmss";
  public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static int WEEK_RULE_CHINA = 1;
  public static int WEEK_RULE_FOREIGN = 2;
  private static Calendar calendar = Calendar.getInstance();

  public static String formateDate(Date date)
  {
    return dateToString(date, "yyyy-MM-dd");
  }

  public static String dateToString(Timestamp date, String format)
  {
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    Date utilDate = null;
    if (date == null)
    {
      utilDate = new Date();
    }
    else {
      utilDate = new Date(date.getTime());
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(utilDate);
  }

  public static String dateToString(Date date, String format)
  {
    if (date == null) {
      return "";
    }
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  public static String dateToString(Date date, String format, Date defaultDate)
  {
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    if (date == null)
    {
      if (defaultDate == null) {
        date = new Date();
      }
      else {
        date = defaultDate;
      }
    }

    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  public static Date stringToDate(String str)
  {
    if (str == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(
      DEFAULT_DATE_FORMAT);
    try {
      return sdf.parse(str);
    } catch (ParseException ex) {
    }
    return null;
  }

  public static Date stringToDate(String str, String format)
  {
    if (str == null) {
      return stringToDate(str);
    }
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(str);
    }
    catch (ParseException ex) {
    }
    return null;
  }

  public static String getCurrentDate(String format)
  {
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    Date utilDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(utilDate);
  }

  public static String getOldDateStr(int days, String format)
  {
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    Calendar cal = Calendar.getInstance();
    int today = cal.get(5);

    cal.set(5, today + days);
    return dateToString(cal.getTime(), format);
  }

  public static ArrayList getWeekListByMonth(int year, int month)
  {
    Calendar cal = Calendar.getInstance();

    ArrayList list = new ArrayList();
    month--;
    cal.set(year, month, 1, 0, 0);

    int weekInt = cal.get(7);
    if (weekInt == 1) {
      weekInt = 7;
    }
    weekInt--;
    cal.set(5, cal.get(5) - (weekInt - 1));
    String dateString = "";
    for (int i = 0; i < 5; i++) {
      dateString = getDay_Of_Week(cal);
      dateString = dateString + "~" + getLastDay_Of_Week(cal);
      list.add(dateString);

      cal.set(5, cal.get(5) + 1);
    }
    return list;
  }

  private static String getDay_Of_Week(Calendar cal)
  {
    int fYear = cal.get(1);
    int fMonth = 1 + cal.get(2);
    int fDay = cal.get(5);

    String zeroM = "";
    if (fMonth < 10) {
      zeroM = "0";
    }
    String zeroD = "";
    if (fDay < 10) {
      zeroD = "0";
    }

    return fYear + "-" + zeroM + fMonth + "-" + zeroD + fDay;
  }

  private static String getLastDay_Of_Week(Calendar cal)
  {
    cal.set(5, cal.get(5) + 6);
    return getDay_Of_Week(cal);
  }

  public static void nextDay()
  {
    calendar.set(5, getDay() + 1);
  }

  public static void previousDay()
  {
    calendar.set(5, getDay() - 1);
  }

  public static void changeDay(int num)
  {
    calendar.set(5, getDay() + num);
  }

  public static String getDate()
  {
    String zeroM = "";
    if (getMonth() < 10) {
      zeroM = "0";
    }
    String zeroD = "";
    if (getDay() < 10) {
      zeroD = "0";
    }
    int fYear = getYear();
    int fMonth = getMonth();
    int fDay = getDay();

    return fYear + "-" + zeroM + fMonth + "-" + zeroD + fDay;
  }

  public static String getFirstDayOfWeek()
  {
    Calendar fristCal = Calendar.getInstance();

    int weekInt = fristCal.get(7);
    if (weekInt == 1) {
      weekInt = 7;
    }
    weekInt--;

    fristCal.set(5, 
      fristCal.get(5) - (weekInt - 1));

    int fYear = fristCal.get(1);
    int fMonth = 1 + fristCal.get(2);
    int fDay = fristCal.get(5);

    String zeroM = "";
    if (fMonth < 10) {
      zeroM = "0";
    }
    String zeroD = "";
    if (fDay < 10) {
      zeroD = "0";
    }
    return fYear + "-" + zeroM + fMonth + "-" + zeroD + fDay;
  }

  public static String getLastDayOfWeek()
  {
    Calendar lastCal = Calendar.getInstance();

    int weekInt = lastCal.get(7);
    if (weekInt == 1) {
      weekInt = 7;
    }
    weekInt--;

    lastCal.set(5, 
      lastCal.get(5) + (7 - weekInt));

    int fYear = lastCal.get(1);
    int fMonth = 1 + lastCal.get(2);
    int fDay = lastCal.get(5);

    String zeroM = "";
    if (fMonth < 10) {
      zeroM = "0";
    }
    String zeroD = "";
    if (fDay < 10) {
      zeroD = "0";
    }

    return fYear + "-" + zeroM + fMonth + "-" + zeroD + fDay;
  }

  public static int getWeekByChina()
  {
    int x = calendar.get(7);
    if (x == 1) {
      x = 7;
    }
    return x - 1;
  }

  public static int getWeekByForeign()
  {
    int x = calendar.get(7);
    return x;
  }

  public static String getWeekInt2Hz(int weekInt, int rule)
  {
    if ((rule != WEEK_RULE_CHINA) && (rule != WEEK_RULE_FOREIGN)) {
      rule = WEEK_RULE_CHINA;
    }
    String hzWeek = null;
    switch (weekInt) {
    case 1:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期一" : "星期日";
      break;
    case 2:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期二" : "星期一";
      break;
    case 3:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期三" : "星期二";
      break;
    case 4:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期四" : "星期三";
      break;
    case 5:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期五" : "星期四";
      break;
    case 6:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期六" : "星期五";
      break;
    case 7:
      hzWeek = rule == WEEK_RULE_CHINA ? "星期日" : "星期六";
      break;
    default:
      hzWeek = "";
    }

    return hzWeek;
  }

  public static int getDay()
  {
    return calendar.get(5);
  }

  public static int getMonth()
  {
    return 1 + calendar.get(2);
  }

  public static int getYear()
  {
    return calendar.get(1);
  }

  public static boolean isLeapYear(int year)
  {
    if (((year % 4 == 0) && (year % 400 != 0)) || (year % 400 == 0)) {
      return true;
    }

    return false;
  }

  public static int days(int year, int month)
  {
    int returndays = 30;
    if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || 
      (month == 10) || (month == 12)) {
      returndays = 31;
    }
    else if (month == 2) {
      if (isLeapYear(year)) {
        returndays = 29;
      }
      else {
        returndays = 28;
      }
    }

    return returndays;
  }

  public static String add_months(int addMonth, String[] date)
  {
    SimpleDateFormat from_sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    if ((date != null) && (date.length > 0) && (date[0] != null))
      try {
        Date tempDate = from_sdf.parse(date[0]);
        cal.setTime(tempDate);
      }
      catch (ParseException localParseException)
      {
      }
    cal.add(2, addMonth);
    String resultDate = from_sdf.format(cal.getTime());
    return resultDate;
  }

  public static String add_days(int day, String[] date)
  {
    SimpleDateFormat from_sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    if ((date != null) && (date.length > 0) && (date[0] != null))
      try {
        Date tempDate = from_sdf.parse(date[0]);
        cal.setTime(tempDate);
      }
      catch (ParseException localParseException)
      {
      }
    cal.add(6, day);
    String resultDate = from_sdf.format(cal.getTime());
    return resultDate;
  }
  
  public static String addDay(int day, String date,String patten)
  {
    SimpleDateFormat from_sdf = new SimpleDateFormat(patten);
    Calendar cal = Calendar.getInstance();
    if (date != null &&date !="")
      try {
        Date tempDate = from_sdf.parse(date);
        cal.setTime(tempDate);
      }
      catch (ParseException localParseException)
      {
      }
    cal.add(6, day);
    String resultDate = from_sdf.format(cal.getTime());
    return resultDate;
  }
  

  public static String addMin(int min, String date2)
  {
    try
    {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = format.parse(getCurrentDate(null));
      if ((date2 != null) && (date2.length() > 0)) {
        date = format.parse(date2);
      }

      Calendar c = Calendar.getInstance();
      c.setTime(date);
      c.add(12, min);

      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
        c.getTime());
    }
    catch (ParseException ex) {
      ex.printStackTrace();
      System.out.println("Parse DATE String ERROR!" + ex.getMessage());
    }
    return date2;
  }

  public static String addHour(int hour, String date2)
  {
    try
    {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = format.parse(getCurrentDate(null));
      if ((date2 != null) && (date2.length() > 0)) {
        date = format.parse(date2);
      }

      Calendar c = Calendar.getInstance();
      c.setTime(date);
      c.add(11, hour);

      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
        c.getTime());
    }
    catch (ParseException ex) {
      ex.printStackTrace();
      System.out.println("Parse DATE String ERROR!" + ex.getMessage());
    }
    return date2;
  }

  public static long minusDate(String date1, String date2)
  {
    if ((date1 == null) || (date2 == null) || (date1.length() == 0) || (date2.length() == 0))
      return 99999999L;
    if (date1.length() == 10) date1 = date1 + " 00:00:00";
    if (date2.length() == 10) date2 = date2 + " 00:00:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long l1 = 0L;
    long l2 = 0L;
    try {
      l1 = sdf.parse(date1).getTime();
      l2 = sdf.parse(date2).getTime();
    }
    catch (ParseException ex) {
      ex.printStackTrace();
      System.out.println("Parse DATE String ERROR!" + ex.getMessage());
    }

    return (l2 - l1) / 1000L;
  }

  public static String getDayOfMonth(int day, String[] date)
  {
    SimpleDateFormat from_sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    if ((date != null) && (date.length > 0) && (date[0] != null))
      try {
        Date tempDate = from_sdf.parse(date[0]);
        cal.setTime(tempDate);
      }
      catch (ParseException localParseException)
      {
      }
    cal.set(5, day);
    String resultDate = from_sdf.format(cal.getTime());
    return resultDate;
  }

  public static String getDateForChina(String date)
  {
    if ((date == null) || (date.length() == 0)) return "";
    String str = "";
    String[] dateStr = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九", "三十", "三十一" };
    String year = date.substring(0, 4);

    year = dateStr[java.lang.Integer.valueOf(year.substring(0, 1)).intValue()] + dateStr[java.lang.Integer.valueOf(year.substring(1, 2)).intValue()] + dateStr[java.lang.Integer.valueOf(year.substring(2, 3)).intValue()] + dateStr[java.lang.Integer.valueOf(year.substring(3)).intValue()];
    str = year + "年";
    if (date.length() <= 5) return str;

    String month = date.substring(5, 7);
    str = str + dateStr[java.lang.Integer.valueOf(month).intValue()] + "月";
    if (date.length() <= 8) return str;

    String day = date.substring(8);
    str = str + dateStr[java.lang.Integer.valueOf(day).intValue()] + "日";

    return str;
  }

  public static Date parseDate(String date)
    throws ParseException
  {
    if (StringUtils.isEmpty(date)) {
      return DF_YYYY_MM_DD.parse(date);
    }
    return null;
  }
  
  /** 
   * 时间戳转换成日期格式字符串 
   * @param seconds 精确到秒的字符串 
   * @param formatStr 
   * @return 
   */  
  public static String timeStampDate(String seconds,String format) {  
      if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
          return "";  
      }  
      if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
      SimpleDateFormat sdf = new SimpleDateFormat(format);  
      return sdf.format(new Date(Long.valueOf(seconds+"000")));  
  }  
  /** 
   * 日期格式字符串转换成时间戳 
   * @param date 字符串日期 
   * @param format 如：yyyy-MM-dd HH:mm:ss 
   * @return 
   */  
  public static String dateTimeStamp(String date_str,String format){  
      try {
      	if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
          SimpleDateFormat sdf = new SimpleDateFormat(format);  
          return String.valueOf(sdf.parse(date_str).getTime()/1000);  
      } catch (Exception e) {  
          e.printStackTrace();  
          return null;
      }  
  }

  public static void main(String[] args)
  {
    System.out.println(getCurrentDate(DateUtil.patternF));
  }
}