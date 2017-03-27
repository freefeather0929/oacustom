/*   1:    */ package dinghan.workflow.service;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.beans.DaKaRecord;
/*   4:    */ import dinghan.workflow.beans.JiaBan1;
/*   5:    */ import dinghan.workflow.beans.QingJia;
/*   6:    */ import dinghan.workflow.beans.WanCanBuZhu;
/*   7:    */ import java.text.SimpleDateFormat;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.Calendar;
/*  10:    */ import java.util.GregorianCalendar;
/*  11:    */ import java.util.HashMap;
/*  12:    */ import org.apache.commons.logging.Log;
/*  13:    */ import org.apache.commons.logging.LogFactory;
/*  14:    */ import weaver.conn.RecordSet;
/*  15:    */ import weaver.dh.interfaces.dingHanTools;
/*  16:    */ 
/*  17:    */ public class WanCanBuZhuService
/*  18:    */ {
/*  19: 20 */   private static Log log = LogFactory.getLog(QingJia.class.getName());
/*  20: 21 */   private dingHanTools dhTools = new dingHanTools();
/*  21: 22 */   private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  22: 23 */   private SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/*  23:    */   private DaKaRecord dakaRecord;
/*  24:    */   private static final int buzhuMenoy = 20;
/*  25:    */   private static final String dateTmp = "2016-01-01";
/*  26: 27 */   private Calendar calendarTmp = new GregorianCalendar();
/*  27:    */   
/*  28:    */   public boolean isFilledApp(int buzhuYear, int curMonth, int userId, String requestId)
/*  29:    */   {
/*  30: 32 */     int wfNum = 0;
/*  31: 33 */     boolean filled = false;
/*  32:    */     
/*  33: 35 */     RecordSet rs = new RecordSet();
/*  34:    */     
/*  35: 37 */     String _monthStr = "0" + curMonth;
/*  36:    */     
/*  37: 39 */     String sql = "select count(id) from formtable_main_82 where apppsn = " + userId + " and appmonth = '" + buzhuYear + "-" + _monthStr + "'";
/*  38: 40 */     sql = sql + " and requestId <> '" + requestId + "'";
/*  39:    */     
/*  40:    */ 
/*  41:    */ 
/*  42: 44 */     rs.executeSql(sql);
/*  43: 46 */     while (rs.next()) {
/*  44: 47 */       wfNum = rs.getInt(1);
/*  45:    */     }
/*  46: 50 */     if (wfNum > 0) {
/*  47: 51 */       filled = true;
/*  48:    */     }
/*  49: 54 */     return filled;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public ArrayList<WanCanBuZhu> getAllWanCanBuZhuRecord(int buzhuYear, int curMonth, int userId, String userCode)
/*  53:    */     throws Exception
/*  54:    */   {
/*  55: 67 */     log.error("======================== 生成晚餐补助： 用户ID :: " + userId + "工号 :: " + userCode + " ==============================");
/*  56:    */     
/*  57: 69 */     boolean flag = false;
/*  58: 70 */     String weekDay = "";
/*  59: 71 */     int buzhuTimes = 1;
/*  60:    */     
/*  61: 73 */     ArrayList<WanCanBuZhu> wanCanBuZhuList = new ArrayList();
/*  62:    */     
/*  63: 75 */     String endDateStr = buzhuYear + "-";
/*  64: 76 */     endDateStr = endDateStr + (curMonth > 10 ? Integer.valueOf(curMonth) : new StringBuilder("0").append(curMonth).toString());
/*  65:    */     
/*  66: 78 */     endDateStr = endDateStr + "-21";
/*  67:    */     
/*  68: 80 */     Calendar endCalendar = new GregorianCalendar();
/*  69: 81 */     endCalendar.setTime(this.sdf.parse(endDateStr));
/*  70:    */     
/*  71: 83 */     String startDateStr = buzhuYear + "-";
/*  72: 84 */     startDateStr = startDateStr + (curMonth - 1 > 10 ? Integer.valueOf(curMonth - 1) : new StringBuilder("0").append(curMonth - 1).toString());
/*  73: 85 */     startDateStr = startDateStr + "-21";
/*  74:    */     
/*  75: 87 */     Calendar startCalendar = new GregorianCalendar();
/*  76: 88 */     startCalendar.setTime(this.sdf.parse(startDateStr));
/*  77:    */     
/*  78: 90 */     HashMap<String, ArrayList<JiaBan1>> jiabanMap = new JiaBanService().getAllJiaBanRecordByUserCode(startDateStr, endDateStr, userCode);
/*  79:    */     label1317:
/*  80: 92 */     while (startCalendar.compareTo(endCalendar) < 0)
/*  81:    */     {
/*  82: 93 */       flag = false;
/*  83: 94 */       buzhuTimes = 1;
/*  84: 95 */       Calendar lastTime = new GregorianCalendar();
/*  85: 96 */       Calendar startTime = new GregorianCalendar();
/*  86: 97 */       if (jiabanMap.get(this.sdf.format(startCalendar.getTime())) != null)
/*  87:    */       {
/*  88: 98 */         startCalendar.add(5, 1);
/*  89:    */       }
/*  90:    */       else
/*  91:    */       {
/*  92:102 */         HashMap<String, String> jjrMap = this.dhTools.getJJR(String.valueOf(userId), this.sdf.format(startCalendar.getTime()));
/*  93:103 */         this.dakaRecord = new DaKaRecord(userCode, this.sdf.format(startCalendar.getTime()));
/*  94:105 */         if (this.dakaRecord.getLasttime() == null)
/*  95:    */         {
/*  96:106 */           startCalendar.add(5, 1);
/*  97:    */         }
/*  98:    */         else
/*  99:    */         {
/* 100:110 */           lastTime.setTime(this.sdfTime.parse("2016-01-01 " + this.dakaRecord.getLasttime()));
/* 101:111 */           startTime.setTime(this.sdfTime.parse("2016-01-01 " + this.dakaRecord.getFirsttime()));
/* 102:113 */           if (!jjrMap.isEmpty())
/* 103:    */           {
/* 104:114 */             if ("8".equals(jjrMap.get(userId + "_jrlx")))
/* 105:    */             {
/* 106:115 */               this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 19:59"));
/* 107:116 */               if (lastTime.compareTo(this.calendarTmp) > 0) {
/* 108:117 */                 flag = true;
/* 109:    */               }
/* 110:    */             }
/* 111:    */             else
/* 112:    */             {
/* 113:120 */               this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 12:59"));
/* 114:121 */               if (lastTime.compareTo(this.calendarTmp) > 0)
/* 115:    */               {
/* 116:122 */                 this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 19:59"));
/* 117:123 */                 if (lastTime.compareTo(this.calendarTmp) > 0)
/* 118:    */                 {
/* 119:124 */                   flag = true;
/* 120:125 */                   this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 10:00"));
/* 121:126 */                   if (startTime.compareTo(this.calendarTmp) < 0) {
/* 122:127 */                     buzhuTimes = 2;
/* 123:129 */                   } else if (startTime.compareTo(this.calendarTmp) > 0) {
/* 124:130 */                     flag = false;
/* 125:    */                   } else {
/* 126:132 */                     flag = true;
/* 127:    */                   }
/* 128:    */                 }
/* 129:    */               }
/* 130:    */             }
/* 131:    */             String str1;
/* 132:139 */             switch ((str1 = (String)jjrMap.get(userId + "_jrlx")).hashCode())
/* 133:    */             {
/* 134:    */             case 48: 
/* 135:139 */               if (str1.equals("0")) {
/* 136:    */                 break;
/* 137:    */               }
/* 138:    */               break;
/* 139:    */             case 49: 
/* 140:139 */               if (str1.equals("1")) {}
/* 141:    */               break;
/* 142:    */             case 50: 
/* 143:139 */               if (str1.equals("2")) {}
/* 144:    */               break;
/* 145:    */             case 51: 
/* 146:139 */               if (str1.equals("3")) {}
/* 147:    */               break;
/* 148:    */             case 52: 
/* 149:139 */               if (str1.equals("4")) {}
/* 150:    */               break;
/* 151:    */             case 53: 
/* 152:139 */               if (str1.equals("5")) {}
/* 153:    */               break;
/* 154:    */             case 54: 
/* 155:139 */               if (str1.equals("6")) {}
/* 156:    */               break;
/* 157:    */             case 55: 
/* 158:139 */               if (str1.equals("7")) {}
/* 159:    */               break;
/* 160:    */             case 56: 
/* 161:139 */               if (str1.equals("8")) {}
/* 162:    */               break;
/* 163:    */             case 57: 
/* 164:139 */               if (!str1.equals("9"))
/* 165:    */               {
/* 166:    */                 break label1317;
/* 167:141 */                 weekDay = "元旦";
/* 168:    */                 break label1317;
/* 169:144 */                 weekDay = "清明节";
/* 170:    */                 break label1317;
/* 171:147 */                 weekDay = "劳动节";
/* 172:    */                 break label1317;
/* 173:150 */                 weekDay = "端午节";
/* 174:    */                 break label1317;
/* 175:153 */                 weekDay = "中秋节";
/* 176:    */                 break label1317;
/* 177:156 */                 weekDay = "国庆节";
/* 178:    */                 break label1317;
/* 179:159 */                 weekDay = "春节";
/* 180:    */                 break label1317;
/* 181:162 */                 weekDay = "抗战胜利日";
/* 182:    */                 break label1317;
/* 183:165 */                 weekDay = "调整上班";
/* 184:    */               }
/* 185:    */               else
/* 186:    */               {
/* 187:168 */                 weekDay = "调整休息";
/* 188:    */               }
/* 189:    */               break;
/* 190:    */             }
/* 191:    */           }
/* 192:    */           else
/* 193:    */           {
/* 194:173 */             int week_day = startCalendar.get(7);
/* 195:174 */             switch (week_day)
/* 196:    */             {
/* 197:    */             case 1: 
/* 198:    */             case 7: 
/* 199:177 */               this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 12:59"));
/* 200:178 */               if (lastTime.compareTo(this.calendarTmp) > 0)
/* 201:    */               {
/* 202:179 */                 this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 19:59"));
/* 203:180 */                 if (lastTime.compareTo(this.calendarTmp) > 0)
/* 204:    */                 {
/* 205:181 */                   flag = true;
/* 206:182 */                   this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 10:00"));
/* 207:183 */                   if (startTime.compareTo(this.calendarTmp) < 0) {
/* 208:184 */                     buzhuTimes = 2;
/* 209:186 */                   } else if (startTime.compareTo(this.calendarTmp) > 0) {
/* 210:187 */                     flag = false;
/* 211:    */                   } else {
/* 212:189 */                     flag = true;
/* 213:    */                   }
/* 214:    */                 }
/* 215:    */               }
/* 216:194 */               break;
/* 217:    */             default: 
/* 218:197 */               this.calendarTmp.setTime(this.sdfTime.parse("2016-01-01 19:59"));
/* 219:198 */               if (lastTime.compareTo(this.calendarTmp) > 0) {
/* 220:199 */                 flag = true;
/* 221:    */               }
/* 222:    */               break;
/* 223:    */             }
/* 224:203 */             switch (week_day)
/* 225:    */             {
/* 226:    */             case 1: 
/* 227:205 */               weekDay = "星期日";
/* 228:206 */               break;
/* 229:    */             case 2: 
/* 230:208 */               weekDay = "星期一";
/* 231:209 */               break;
/* 232:    */             case 3: 
/* 233:211 */               weekDay = "星期二";
/* 234:212 */               break;
/* 235:    */             case 4: 
/* 236:214 */               weekDay = "星期三";
/* 237:215 */               break;
/* 238:    */             case 5: 
/* 239:217 */               weekDay = "星期四";
/* 240:218 */               break;
/* 241:    */             case 6: 
/* 242:220 */               weekDay = "星期五";
/* 243:221 */               break;
/* 244:    */             case 7: 
/* 245:223 */               weekDay = "星期六";
/* 246:    */             }
/* 247:    */           }
/* 248:228 */           if (flag)
/* 249:    */           {
/* 250:229 */             WanCanBuZhu wcBuZhu = createWanCanBuZhu(
/* 251:230 */               this.dakaRecord.getFirsttime(), 
/* 252:231 */               this.dakaRecord.getLasttime(), 
/* 253:232 */               this.sdf.format(startCalendar.getTime()), 
/* 254:233 */               20 * buzhuTimes, 
/* 255:234 */               weekDay);
/* 256:    */             
/* 257:236 */             wanCanBuZhuList.add(wcBuZhu);
/* 258:    */           }
/* 259:239 */           startCalendar.add(5, 1);
/* 260:    */         }
/* 261:    */       }
/* 262:    */     }
/* 263:241 */     log.error("======================== 生成晚餐补助： 用户ID :: " + userId + "工号 :: " + userCode + "生成完毕  ==============================");
/* 264:242 */     return wanCanBuZhuList;
/* 265:    */   }
/* 266:    */   
/* 267:    */   private WanCanBuZhu createWanCanBuZhu(String firstTime, String lastTime, String dateStr, double buzhuMenoy, String weekDay)
/* 268:    */   {
/* 269:248 */     WanCanBuZhu wcBuZhu = new WanCanBuZhu();
/* 270:    */     
/* 271:250 */     wcBuZhu.setDakaRecord(firstTime + "--" + lastTime);
/* 272:251 */     wcBuZhu.setDate(dateStr);
/* 273:252 */     wcBuZhu.setMenoy(buzhuMenoy);
/* 274:253 */     wcBuZhu.setWeekDay(weekDay);
/* 275:    */     
/* 276:255 */     return wcBuZhu;
/* 277:    */   }
/* 278:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.service.WanCanBuZhuService
 * JD-Core Version:    0.7.0.1
 */