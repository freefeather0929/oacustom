/*  1:   */ package dinghan.workflow.beans;
/*  2:   */ 
/*  3:   */ import java.text.SimpleDateFormat;
/*  4:   */ import java.util.Calendar;
/*  5:   */ import java.util.Date;
/*  6:   */ import java.util.GregorianCalendar;
/*  7:   */ 
/*  8:   */ public class PublicVariant
/*  9:   */ {
/* 10:   */   public static String DateToStr(Date date, String format)
/* 11:   */     throws Exception
/* 12:   */   {
/* 13:16 */     SimpleDateFormat df = new SimpleDateFormat(format);
/* 14:17 */     String str = df.format(date);
/* 15:18 */     return str;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static Date StrToDate(String str, String format)
/* 19:   */     throws Exception
/* 20:   */   {
/* 21:31 */     SimpleDateFormat df = new SimpleDateFormat(format);
/* 22:32 */     Date date = df.parse(str);
/* 23:33 */     return date;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static Date AdjustDateTime(Date date, int... args)
/* 27:   */     throws Exception
/* 28:   */   {
/* 29:44 */     Calendar calendar = new GregorianCalendar();
/* 30:45 */     calendar.setTime(date);
/* 31:46 */     switch (args.length)
/* 32:   */     {
/* 33:   */     case 6: 
/* 34:48 */       calendar.add(13, args[5]);
/* 35:   */     case 5: 
/* 36:50 */       calendar.add(12, args[4]);
/* 37:   */     case 4: 
/* 38:52 */       calendar.add(10, args[3]);
/* 39:   */     case 3: 
/* 40:54 */       calendar.add(5, args[2]);
/* 41:   */     case 2: 
/* 42:56 */       calendar.add(2, args[1]);
/* 43:   */     case 1: 
/* 44:58 */       calendar.add(1, args[0]);
/* 45:   */     }
/* 46:60 */     return calendar.getTime();
/* 47:   */   }
/* 48:   */   
/* 49:   */   public static String toUTF8(String ss)
/* 50:   */     throws Exception
/* 51:   */   {
/* 52:64 */     if (ss == null) {
/* 53:65 */       ss = "";
/* 54:   */     }
/* 55:66 */     ss = new String(ss.getBytes("ISO-8859-1"), "UTF8");
/* 56:67 */     return ss;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public static long getTimeDifference(String startTime, String endTime)
/* 60:   */     throws Exception
/* 61:   */   {
/* 62:81 */     long l = 0L;
/* 63:82 */     Date dt = StrToDate(startTime, "yyyy-MM-dd HH:mm");
/* 64:83 */     l = dt.getTime();
/* 65:84 */     dt = StrToDate(endTime, "yyyy-MM-dd HH:mm");
/* 66:85 */     l -= dt.getTime();
/* 67:86 */     return Math.abs(l);
/* 68:   */   }
/* 69:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.PublicVariant
 * JD-Core Version:    0.7.0.1
 */