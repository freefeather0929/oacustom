/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.text.SimpleDateFormat;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.Collections;
/*   6:    */ import java.util.Date;
/*   7:    */ import java.util.HashMap;
/*   8:    */ import org.apache.commons.logging.Log;
/*   9:    */ import org.apache.commons.logging.LogFactory;
/*  10:    */ import weaver.conn.RecordSet;
/*  11:    */ import weaver.dh.interfaces.dingHanTools;
/*  12:    */ import weaver.general.Util;
/*  13:    */ 
/*  14:    */ public class JiaBan1
/*  15:    */ {
/*  16: 17 */   private static Log log = LogFactory.getLog(JiaBan1.class.getName());
/*  17:    */   private int id;
/*  18:    */   private int mainid;
/*  19:    */   private String jbrq;
/*  20:    */   private int sfztx;
/*  21:    */   private String hdkssj;
/*  22:    */   private String hdjssj;
/*  23:    */   private double xxsj;
/*  24:    */   private double yxgs;
/*  25:    */   private double jbxs;
/*  26:    */   private double hdgs;
/*  27:    */   private String yjkssj;
/*  28:    */   private String yjjssj;
/*  29:    */   private String xq;
/*  30:    */   private int hdzt;
/*  31:    */   private String dkjl;
/*  32:    */   
/*  33:    */   public Log getLog()
/*  34:    */   {
/*  35: 20 */     return log;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void setLog(Log log)
/*  39:    */   {
/*  40: 24 */     log = log;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public String getDkjl()
/*  44:    */   {
/*  45: 44 */     return this.dkjl;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void setDkjl(String dkjl)
/*  49:    */   {
/*  50: 48 */     this.dkjl = dkjl;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void insert()
/*  54:    */     throws Exception
/*  55:    */   {
/*  56:    */     try
/*  57:    */     {
/*  58: 57 */       String sql = "INSERT INTO formtable_main_94_dt1 (mainid,jbrq,sfztx,hdkssj,hdjssj,xxsj,yxgs,jbxs,hdgs,yjkssj,yjjssj,xq,dkjl,hdzt)";
/*  59: 58 */       sql = sql + " VALUES  (";
/*  60: 59 */       sql = sql + "'" + this.mainid + "',";
/*  61: 60 */       sql = sql + "'" + this.jbrq + "',";
/*  62: 61 */       sql = sql + "'" + this.sfztx + "',";
/*  63: 62 */       sql = sql + "'" + this.hdkssj + "',";
/*  64: 63 */       sql = sql + "'" + this.hdjssj + "',";
/*  65: 64 */       sql = sql + "'" + this.xxsj + "',";
/*  66: 65 */       sql = sql + "'" + this.yxgs + "',";
/*  67: 66 */       sql = sql + "'" + this.jbxs + "',";
/*  68: 67 */       sql = sql + "'" + this.hdgs + "',";
/*  69: 68 */       sql = sql + "'" + this.yjkssj + "',";
/*  70: 69 */       sql = sql + "'" + this.yjjssj + "',";
/*  71: 70 */       sql = sql + "'" + this.xq + "',";
/*  72: 71 */       sql = sql + "'" + this.dkjl + "',";
/*  73: 72 */       sql = sql + "'" + this.hdzt + "'";
/*  74: 73 */       sql = sql + ")";
/*  75: 74 */       RecordSet rs = new RecordSet();
/*  76: 75 */       rs.executeSql(sql);
/*  77:    */     }
/*  78:    */     catch (Exception e)
/*  79:    */     {
/*  80: 78 */       log.error("插入加班明细表一：" + e);
/*  81: 79 */       throw e;
/*  82:    */     }
/*  83:    */   }
/*  84:    */   
/*  85:    */   public static ArrayList<JiaBan1> queryList(String userid, String jbrq, String requestid)
/*  86:    */     throws Exception
/*  87:    */   {
/*  88: 91 */     ArrayList<JiaBan1> aJB = new ArrayList();
/*  89:    */     try
/*  90:    */     {
/*  91: 93 */       String sql = "select * from formtable_main_94 left JOIN formtable_main_94_dt1 on formtable_main_94.id=formtable_main_94_dt1.mainid";
/*  92: 94 */       sql = sql + "  where formtable_main_94.proposer='" + userid;
/*  93: 95 */       sql = sql + "' and formtable_main_94_dt1.jbrq='" + jbrq + 
/*  94: 96 */         "' and  formtable_main_94.requestId!='" + requestid + "'";
/*  95:    */       
/*  96: 98 */       RecordSet rs = new RecordSet();
/*  97: 99 */       rs.executeSql(sql);
/*  98:101 */       while (rs.next())
/*  99:    */       {
/* 100:102 */         JiaBan1 oJB = new JiaBan1();
/* 101:103 */         oJB.setHdgs(rs.getDouble("hdgs"));
/* 102:104 */         oJB.setHdjssj(rs.getString("hdjssj"));
/* 103:105 */         oJB.setHdkssj(rs.getString("hdkssj"));
/* 104:106 */         oJB.setJbrq(rs.getString("jbrq"));
/* 105:107 */         oJB.setJbxs(rs.getDouble("jbxs"));
/* 106:108 */         oJB.setMainid(rs.getInt("mainid"));
/* 107:109 */         oJB.setSfztx(rs.getInt("sfztx"));
/* 108:110 */         oJB.setXq(rs.getString("xq"));
/* 109:111 */         oJB.setXxsj(rs.getDouble("xxsj"));
/* 110:112 */         oJB.setYjjssj(rs.getString("yjjssj"));
/* 111:113 */         oJB.setYxgs(rs.getDouble("yxgs"));
/* 112:114 */         oJB.setYjkssj(rs.getString("yjkssj"));
/* 113:115 */         oJB.setId(rs.getInt("id1"));
/* 114:116 */         oJB.setHdzt(rs.getInt("hdzt"));
/* 115:117 */         aJB.add(oJB);
/* 116:    */       }
/* 117:    */     }
/* 118:    */     catch (Exception e)
/* 119:    */     {
/* 120:121 */       log.error("得到加班明细2信息出错：" + e);
/* 121:122 */       throw e;
/* 122:    */     }
/* 123:124 */     return aJB;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public static ArrayList<JiaBan1> queryList(int mainid)
/* 127:    */     throws Exception
/* 128:    */   {
/* 129:134 */     ArrayList<JiaBan1> aJB = new ArrayList();
/* 130:    */     try
/* 131:    */     {
/* 132:136 */       String sql = "select * from formtable_main_94_dt1 where mainid=" + 
/* 133:137 */         mainid;
/* 134:    */       
/* 135:139 */       RecordSet rs = new RecordSet();
/* 136:140 */       rs.executeSql(sql);
/* 137:142 */       while (rs.next())
/* 138:    */       {
/* 139:143 */         JiaBan1 oJB = new JiaBan1();
/* 140:144 */         oJB.setHdgs(rs.getDouble("hdgs"));
/* 141:145 */         oJB.setHdjssj(rs.getString("hdjssj"));
/* 142:146 */         oJB.setHdkssj(rs.getString("hdkssj"));
/* 143:147 */         oJB.setJbrq(rs.getString("jbrq"));
/* 144:148 */         oJB.setJbxs(rs.getDouble("jbxs"));
/* 145:149 */         oJB.setMainid(rs.getInt("mainid"));
/* 146:150 */         oJB.setSfztx(rs.getInt("sfztx"));
/* 147:151 */         oJB.setXq(rs.getString("xq"));
/* 148:152 */         oJB.setXxsj(rs.getDouble("xxsj"));
/* 149:153 */         oJB.setYjjssj(rs.getString("yjjssj"));
/* 150:154 */         oJB.setYxgs(rs.getDouble("yxgs"));
/* 151:155 */         oJB.setYjkssj(rs.getString("yjkssj"));
/* 152:156 */         oJB.setId(rs.getInt("id"));
/* 153:157 */         oJB.setHdzt(rs.getInt("hdzt"));
/* 154:158 */         aJB.add(oJB);
/* 155:    */       }
/* 156:    */     }
/* 157:    */     catch (Exception e)
/* 158:    */     {
/* 159:162 */       log.error("得到加班明细2信息出错：" + e);
/* 160:163 */       throw e;
/* 161:    */     }
/* 162:165 */     return aJB;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public static void delete(int id, int type)
/* 166:    */     throws Exception
/* 167:    */   {
/* 168:173 */     String sql = "";
/* 169:    */     try
/* 170:    */     {
/* 171:175 */       if (type == 0) {
/* 172:176 */         sql = "delete from formtable_main_94_dt1 where mainid=" + id;
/* 173:    */       } else {
/* 174:178 */         sql = "delete from formtable_main_94_dt1 where id=" + id;
/* 175:    */       }
/* 176:180 */       RecordSet rs = new RecordSet();
/* 177:181 */       rs.executeSql(sql);
/* 178:    */     }
/* 179:    */     catch (Exception e)
/* 180:    */     {
/* 181:184 */       log.error("删除加班明细表1信息：" + e);
/* 182:185 */       throw e;
/* 183:    */     }
/* 184:    */   }
/* 185:    */   
/* 186:    */   public static void update(int mainid, int type)
/* 187:    */     throws Exception
/* 188:    */   {
/* 189:    */     try
/* 190:    */     {
/* 191:198 */       String sql = "update formtable_main_94_dt1 set hdzt=" + type;
/* 192:199 */       sql = sql + " where mainid=" + mainid;
/* 193:200 */       RecordSet rs = new RecordSet();
/* 194:201 */       rs.executeSql(sql);
/* 195:    */     }
/* 196:    */     catch (Exception e)
/* 197:    */     {
/* 198:204 */       log.error("更新考勤异常出错：" + e);
/* 199:205 */       throw e;
/* 200:    */     }
/* 201:    */   }
/* 202:    */   
/* 203:    */   public static void checkList(int mainid)
/* 204:    */     throws Exception
/* 205:    */   {
/* 206:215 */     log.error("加班明细核定：");
/* 207:    */     try
/* 208:    */     {
/* 209:217 */       RecordSet rs = new RecordSet();
/* 210:218 */       String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
/* 211:219 */       String sql = "";
/* 212:220 */       if (mainid == 0)
/* 213:    */       {
/* 214:221 */         sql = 
/* 215:222 */           "SELECT * FROM formtable_main_94_dt1 WHERE jbrq<'" + nowDate + "'";
/* 216:223 */         sql = sql + " AND hdzt=0 ORDER BY mainid,id";
/* 217:    */       }
/* 218:    */       else
/* 219:    */       {
/* 220:225 */         sql = 
/* 221:226 */           "SELECT * FROM formtable_main_94_dt1 WHERE mainid=" + mainid + " ORDER BY id";
/* 222:    */       }
/* 223:229 */       rs.executeSql(sql);
/* 224:234 */       while (rs.next())
/* 225:    */       {
/* 226:235 */         JiaBan1 oJB = new JiaBan1();
/* 227:236 */         oJB.setHdgs(rs.getDouble("hdgs"));
/* 228:237 */         oJB.setHdjssj(rs.getString("hdjssj"));
/* 229:238 */         oJB.setHdkssj(rs.getString("hdkssj"));
/* 230:239 */         oJB.setId(rs.getInt("id"));
/* 231:240 */         oJB.setJbrq(rs.getString("jbrq"));
/* 232:241 */         oJB.setJbxs(rs.getDouble("jbxs"));
/* 233:242 */         oJB.setMainid(rs.getInt("mainid"));
/* 234:243 */         oJB.setSfztx(rs.getInt("sfztx"));
/* 235:244 */         oJB.setXq(rs.getString("xq"));
/* 236:245 */         oJB.setXxsj(rs.getDouble("xxsj"));
/* 237:246 */         oJB.setYjjssj(rs.getString("yjjssj"));
/* 238:247 */         oJB.setYjkssj(rs.getString("yjkssj"));
/* 239:248 */         oJB.setYxgs(rs.getDouble("yxgs"));
/* 240:249 */         oJB.setDkjl(rs.getString("dkjl"));
/* 241:250 */         JiaBan jb_main = new JiaBan(rs.getInt("mainid"));
/* 242:251 */         if (!jb_main.getSqdh().equals(""))
/* 243:    */         {
/* 244:254 */           UserInfo userInfo = new UserInfo(jb_main.getProposer());
/* 245:259 */           if (oJB.getJbrq().compareTo(nowDate) < 0)
/* 246:    */           {
/* 247:261 */             DaKaRecord oDK = new DaKaRecord(userInfo.getCode(), 
/* 248:262 */               oJB.getJbrq());
/* 249:263 */             ArrayList<Wctemp> list_wc = Wctemp.queryList(userInfo.getName(), 
/* 250:264 */               oJB.getJbrq());
/* 251:265 */             for (int i = 0; i < list_wc.size(); i++)
/* 252:    */             {
/* 253:266 */               oDK.getDk_list().add(((Wctemp)list_wc.get(i)).getHdkssj());
/* 254:267 */               oDK.getDk_list().add(((Wctemp)list_wc.get(i)).getHdjssj());
/* 255:    */             }
/* 256:269 */             ArrayList<Cctemp> list_cc = Cctemp.queryList(userInfo.getName(), 
/* 257:270 */               oJB.getJbrq());
/* 258:271 */             for (int i = 0; i < list_cc.size(); i++)
/* 259:    */             {
/* 260:272 */               oDK.getDk_list().add(((Cctemp)list_cc.get(i)).getHdkssj());
/* 261:273 */               oDK.getDk_list().add(((Cctemp)list_cc.get(i)).getHdjssj());
/* 262:    */             }
/* 263:276 */             for (int i = 0; i < oDK.getDk_list().size(); i++) {
/* 264:277 */               if ((((String)oDK.getDk_list().get(i)).equals("")) || 
/* 265:    */               
/* 266:279 */                 (((String)oDK.getDk_list().get(i)).equalsIgnoreCase("null")))
/* 267:    */               {
/* 268:280 */                 oDK.getDk_list().remove(i);
/* 269:281 */                 i--;
/* 270:    */               }
/* 271:    */             }
/* 272:284 */             Collections.sort(oDK.getDk_list());
/* 273:285 */             log.error("打卡记录：" + oDK.getDk_list().toString());
/* 274:287 */             if (oDK.getDk_list().size() == 0)
/* 275:    */             {
/* 276:288 */               oDK.setFirsttime("");
/* 277:289 */               oDK.setLasttime("");
/* 278:    */             }
/* 279:    */             else
/* 280:    */             {
/* 281:291 */               oDK.setFirsttime((String)oDK.getDk_list().get(0));
/* 282:292 */               oDK.setLasttime((String)oDK.getDk_list().get(
/* 283:293 */                 oDK.getDk_list().size() - 1));
/* 284:294 */               oJB.setDkjl(oDK.getDk_list().toString());
/* 285:295 */               log.error("打卡记录：" + oJB.getDkjl());
/* 286:    */             }
/* 287:298 */             SimpleDateFormat format = new SimpleDateFormat(
/* 288:299 */               "yyyy-MM-dd");
/* 289:300 */             Date date = format.parse(oJB.getJbrq());
/* 290:301 */             SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
/* 291:302 */             String a = dateFm.format(date);
/* 292:    */             String str1;
/* 293:303 */             switch ((str1 = a).hashCode())
/* 294:    */             {
/* 295:    */             case 25962637: 
/* 296:    */             case 25967877: 
/* 297:303 */               if ((str1.equals("星期六")) || ((goto 863) && (str1.equals("星期日")))) {
/* 298:306 */                 oJB.setJbxs(2.0D);
/* 299:    */               }
/* 300:307 */               break;
/* 301:    */             }
/* 302:309 */             oJB.setJbxs(1.5D);
/* 303:    */             
/* 304:    */ 
/* 305:    */ 
/* 306:313 */             String bz_Stime = userInfo.getStartWorkTime();
/* 307:314 */             String bz_Etime = userInfo.getEndWorkTime();
/* 308:    */             
/* 309:316 */             dingHanTools dht = new dingHanTools();
/* 310:317 */             HashMap<String, String> map = dht.getJJR(
/* 311:318 */               String.valueOf(userInfo.getName()), 
/* 312:319 */               oJB.getJbrq());
/* 313:320 */             if ((map != null) && (map.size() > 0))
/* 314:    */             {
/* 315:321 */               Double jbxs = Double.valueOf(Double.parseDouble((String)map.get(userInfo
/* 316:322 */                 .getName() + "_jbxs")));
/* 317:323 */               String jrlx = Util.null2String((String)map.get(userInfo
/* 318:324 */                 .getName() + "_jrlx"));
/* 319:325 */               if ("8".equals(jrlx))
/* 320:    */               {
/* 321:326 */                 oJB.setJbxs(jbxs.doubleValue());
/* 322:327 */                 oJB.setXq("上班调整");
/* 323:328 */                 bz_Stime = Util.null2String((String)map.get(userInfo
/* 324:329 */                   .getName() + "__kssj"));
/* 325:330 */                 bz_Etime = Util.null2String((String)map.get(userInfo
/* 326:331 */                   .getName() + "_jssj"));
/* 327:    */               }
/* 328:    */               else
/* 329:    */               {
/* 330:333 */                 if ("0".equals(jrlx)) {
/* 331:334 */                   oJB.setXq("元旦");
/* 332:335 */                 } else if ("1".equals(jrlx)) {
/* 333:336 */                   oJB.setXq("清明节");
/* 334:337 */                 } else if ("2".equals(jrlx)) {
/* 335:338 */                   oJB.setXq("劳动节");
/* 336:339 */                 } else if ("3".equals(jrlx)) {
/* 337:340 */                   oJB.setXq("端午节");
/* 338:341 */                 } else if ("4".equals(jrlx)) {
/* 339:342 */                   oJB.setXq("中秋节");
/* 340:343 */                 } else if ("5".equals(jrlx)) {
/* 341:344 */                   oJB.setXq("国庆节");
/* 342:345 */                 } else if ("6".equals(jrlx)) {
/* 343:346 */                   oJB.setXq("春节");
/* 344:347 */                 } else if ("7".equals(jrlx)) {
/* 345:348 */                   oJB.setXq("抗战胜利日");
/* 346:    */                 }
/* 347:350 */                 oJB.setJbxs(jbxs.doubleValue());
/* 348:    */               }
/* 349:    */             }
/* 350:355 */             if (oJB.getSfztx() == 0) {
/* 351:357 */               oJB.setJbxs(1.0D);
/* 352:    */             }
/* 353:360 */             if (oDK.getFirsttime().compareTo(oJB.getYjkssj()) < 0) {
/* 354:361 */               oJB.setHdkssj(oJB.getYjkssj());
/* 355:    */             } else {
/* 356:363 */               oJB.setHdkssj(oDK.getFirsttime());
/* 357:    */             }
/* 358:366 */             if (oDK.getLasttime().compareTo(oJB.getYjjssj()) < 0) {
/* 359:367 */               oJB.setHdjssj(oDK.getLasttime());
/* 360:    */             } else {
/* 361:369 */               oJB.setHdjssj(oJB.getYjjssj());
/* 362:    */             }
/* 363:372 */             if (oJB.getHdkssj().compareTo(oJB.getHdjssj()) > 0) {
/* 364:373 */               oJB.setHdjssj(oJB.getHdkssj());
/* 365:    */             }
/* 366:378 */             if ((oJB.getHdkssj().equals("")) || 
/* 367:379 */               (oJB.getHdjssj().equals("")) || 
/* 368:380 */               (oJB.getHdkssj().equals(oJB.getHdjssj())))
/* 369:    */             {
/* 370:381 */               oJB.setYxgs(0.0D);
/* 371:382 */               oJB.setHdgs(0.0D);
/* 372:383 */               oJB.setXxsj(0.0D);
/* 373:    */             }
/* 374:    */             else
/* 375:    */             {
/* 376:386 */               boolean type = false;
/* 377:387 */               if (("星期一".endsWith(a)) || ("星期二".endsWith(a)) || 
/* 378:388 */                 ("星期三".endsWith(a)) || ("星期四".endsWith(a)) || 
/* 379:389 */                 ("星期五".endsWith(a)) || ("上班调整".endsWith(a))) {
/* 380:390 */                 if (oJB.getHdkssj().compareTo(bz_Stime) < 0)
/* 381:    */                 {
/* 382:391 */                   if (oJB.getHdjssj().compareTo(bz_Stime) >= 0) {
/* 383:392 */                     if (oJB.getHdjssj().compareTo(
/* 384:393 */                       bz_Etime) <= 0) {
/* 385:394 */                       oJB.setHdjssj(bz_Stime);
/* 386:    */                     } else {
/* 387:396 */                       type = true;
/* 388:    */                     }
/* 389:    */                   }
/* 390:    */                 }
/* 391:398 */                 else if (oJB.getHdkssj().compareTo(bz_Etime) <= 0) {
/* 392:399 */                   if (oJB.getHdjssj().compareTo(bz_Etime) <= 0) {
/* 393:400 */                     oJB.setHdjssj(oJB.getHdkssj());
/* 394:    */                   } else {
/* 395:402 */                     oJB.setHdkssj(bz_Etime);
/* 396:    */                   }
/* 397:    */                 }
/* 398:    */               }
/* 399:406 */               String startTime = nowDate + " " + oJB.getHdkssj();
/* 400:407 */               String endTime = nowDate + " 00:00";
/* 401:408 */               double t1 = 0.0D;
/* 402:409 */               t1 = PublicVariant.getTimeDifference(startTime, 
/* 403:410 */                 endTime);
/* 404:411 */               t1 = Arith.div(t1, 3600000.0D, 2);
/* 405:    */               
/* 406:413 */               double t3 = 0.0D;
/* 407:414 */               startTime = nowDate + " " + oJB.getHdjssj();
/* 408:415 */               t3 = PublicVariant.getTimeDifference(startTime, 
/* 409:416 */                 endTime);
/* 410:417 */               t3 = Arith.div(t3, 3600000.0D, 2);
/* 411:    */               
/* 412:419 */               double t4 = 13.0D;
/* 413:420 */               if (userInfo.getPmEndWorkTime().equals("13:30")) {
/* 414:421 */                 t4 = 13.5D;
/* 415:    */               }
/* 416:424 */               double h = t3 - t1;
/* 417:432 */               if (userInfo.getRest() == 1.0D)
/* 418:    */               {
/* 419:433 */                 if (t1 < 10.5D)
/* 420:    */                 {
/* 421:435 */                   if (t3 > 20.0D) {
/* 422:437 */                     oJB.setXxsj(2.0D);
/* 423:438 */                   } else if (t3 > 13.0D) {
/* 424:440 */                     oJB.setXxsj(1.0D);
/* 425:    */                   } else {
/* 426:443 */                     oJB.setXxsj(0.0D);
/* 427:    */                   }
/* 428:    */                 }
/* 429:447 */                 else if (t3 > 20.0D) {
/* 430:449 */                   oJB.setXxsj(1.0D);
/* 431:    */                 } else {
/* 432:452 */                   oJB.setXxsj(0.0D);
/* 433:    */                 }
/* 434:    */               }
/* 435:456 */               else if (t1 < 10.5D)
/* 436:    */               {
/* 437:457 */                 if (t3 > 19.0D) {
/* 438:458 */                   oJB.setXxsj(2.5D);
/* 439:459 */                 } else if (t3 > 13.0D) {
/* 440:460 */                   oJB.setXxsj(1.5D);
/* 441:    */                 } else {
/* 442:462 */                   oJB.setXxsj(0.0D);
/* 443:    */                 }
/* 444:    */               }
/* 445:464 */               else if (t1 < 13.5D)
/* 446:    */               {
/* 447:465 */                 if (t3 > 19.0D) {
/* 448:466 */                   oJB.setXxsj(2.5D);
/* 449:    */                 } else {
/* 450:468 */                   oJB.setXxsj(1.5D);
/* 451:    */                 }
/* 452:    */               }
/* 453:470 */               else if (t1 < 19.0D)
/* 454:    */               {
/* 455:471 */                 if (t3 > 19.0D) {
/* 456:472 */                   oJB.setXxsj(1.0D);
/* 457:    */                 } else {
/* 458:474 */                   oJB.setXxsj(0.0D);
/* 459:    */                 }
/* 460:    */               }
/* 461:    */               else {
/* 462:477 */                 oJB.setXxsj(0.0D);
/* 463:    */               }
/* 464:482 */               h = Arith.sub(h, oJB.getXxsj());
/* 465:484 */               if (h < 1.0D)
/* 466:    */               {
/* 467:485 */                 oJB.setYxgs(0.0D);
/* 468:486 */                 oJB.setHdgs(0.0D);
/* 469:    */               }
/* 470:    */               else
/* 471:    */               {
/* 472:489 */                 int h1 = (int)h;
/* 473:490 */                 if (h < h1 + 0.25D) {
/* 474:491 */                   h = h1;
/* 475:492 */                 } else if (h < h1 + 0.75D) {
/* 476:493 */                   h = h1 + 0.5D;
/* 477:    */                 } else {
/* 478:495 */                   h = h1 + 1;
/* 479:    */                 }
/* 480:497 */                 if (type) {
/* 481:498 */                   h -= 8.0D;
/* 482:    */                 }
/* 483:500 */                 oJB.setYxgs((float)Arith.round(h, 1));
/* 484:501 */                 h = Arith.mul(h, oJB.getJbxs());
/* 485:502 */                 oJB.setHdgs((float)Arith.round(h, 2));
/* 486:503 */                 if (oJB.getSfztx() == 0)
/* 487:    */                 {
/* 488:505 */                   double sytx = userInfo.getSYTiaoXiuJia();
/* 489:506 */                   sytx += h;
/* 490:507 */                   userInfo.updateholiday(
/* 491:508 */                     jb_main.getProposer(), sytx, 1);
/* 492:    */                 }
/* 493:    */               }
/* 494:    */             }
/* 495:515 */             oJB.setHdzt(1);
/* 496:516 */             delete(oJB.getId(), 1);
/* 497:517 */             oJB.insert();
/* 498:    */           }
/* 499:    */         }
/* 500:    */       }
/* 501:    */     }
/* 502:    */     catch (Exception e)
/* 503:    */     {
/* 504:552 */       log.error("加班自动检测：" + e);
/* 505:553 */       throw e;
/* 506:    */     }
/* 507:    */   }
/* 508:    */   
/* 509:    */   public int getId()
/* 510:    */   {
/* 511:559 */     return this.id;
/* 512:    */   }
/* 513:    */   
/* 514:    */   public void setId(int id)
/* 515:    */   {
/* 516:563 */     this.id = id;
/* 517:    */   }
/* 518:    */   
/* 519:    */   public int getMainid()
/* 520:    */   {
/* 521:567 */     return this.mainid;
/* 522:    */   }
/* 523:    */   
/* 524:    */   public void setMainid(int mainid)
/* 525:    */   {
/* 526:571 */     this.mainid = mainid;
/* 527:    */   }
/* 528:    */   
/* 529:    */   public String getJbrq()
/* 530:    */   {
/* 531:575 */     return this.jbrq;
/* 532:    */   }
/* 533:    */   
/* 534:    */   public void setJbrq(String jbrq)
/* 535:    */   {
/* 536:579 */     this.jbrq = jbrq;
/* 537:    */   }
/* 538:    */   
/* 539:    */   public int getSfztx()
/* 540:    */   {
/* 541:583 */     return this.sfztx;
/* 542:    */   }
/* 543:    */   
/* 544:    */   public void setSfztx(int sfztx)
/* 545:    */   {
/* 546:587 */     this.sfztx = sfztx;
/* 547:    */   }
/* 548:    */   
/* 549:    */   public String getHdkssj()
/* 550:    */   {
/* 551:591 */     return this.hdkssj;
/* 552:    */   }
/* 553:    */   
/* 554:    */   public void setHdkssj(String hdkssj)
/* 555:    */   {
/* 556:595 */     this.hdkssj = hdkssj;
/* 557:    */   }
/* 558:    */   
/* 559:    */   public String getHdjssj()
/* 560:    */   {
/* 561:599 */     return this.hdjssj;
/* 562:    */   }
/* 563:    */   
/* 564:    */   public void setHdjssj(String hdjssj)
/* 565:    */   {
/* 566:603 */     this.hdjssj = hdjssj;
/* 567:    */   }
/* 568:    */   
/* 569:    */   public double getXxsj()
/* 570:    */   {
/* 571:607 */     return this.xxsj;
/* 572:    */   }
/* 573:    */   
/* 574:    */   public void setXxsj(double xxsj)
/* 575:    */   {
/* 576:611 */     this.xxsj = xxsj;
/* 577:    */   }
/* 578:    */   
/* 579:    */   public double getYxgs()
/* 580:    */   {
/* 581:615 */     return this.yxgs;
/* 582:    */   }
/* 583:    */   
/* 584:    */   public void setYxgs(double yxgs)
/* 585:    */   {
/* 586:619 */     this.yxgs = yxgs;
/* 587:    */   }
/* 588:    */   
/* 589:    */   public double getJbxs()
/* 590:    */   {
/* 591:623 */     return this.jbxs;
/* 592:    */   }
/* 593:    */   
/* 594:    */   public void setJbxs(double jbxs)
/* 595:    */   {
/* 596:627 */     this.jbxs = jbxs;
/* 597:    */   }
/* 598:    */   
/* 599:    */   public double getHdgs()
/* 600:    */   {
/* 601:631 */     return this.hdgs;
/* 602:    */   }
/* 603:    */   
/* 604:    */   public void setHdgs(double hdgs)
/* 605:    */   {
/* 606:635 */     this.hdgs = hdgs;
/* 607:    */   }
/* 608:    */   
/* 609:    */   public String getYjkssj()
/* 610:    */   {
/* 611:639 */     return this.yjkssj;
/* 612:    */   }
/* 613:    */   
/* 614:    */   public void setYjkssj(String yjkssj)
/* 615:    */   {
/* 616:643 */     this.yjkssj = yjkssj;
/* 617:    */   }
/* 618:    */   
/* 619:    */   public String getYjjssj()
/* 620:    */   {
/* 621:647 */     return this.yjjssj;
/* 622:    */   }
/* 623:    */   
/* 624:    */   public void setYjjssj(String yjjssj)
/* 625:    */   {
/* 626:651 */     this.yjjssj = yjjssj;
/* 627:    */   }
/* 628:    */   
/* 629:    */   public String getXq()
/* 630:    */   {
/* 631:655 */     return this.xq;
/* 632:    */   }
/* 633:    */   
/* 634:    */   public void setXq(String xq)
/* 635:    */   {
/* 636:659 */     this.xq = xq;
/* 637:    */   }
/* 638:    */   
/* 639:    */   public int getHdzt()
/* 640:    */   {
/* 641:663 */     return this.hdzt;
/* 642:    */   }
/* 643:    */   
/* 644:    */   public void setHdzt(int hdzt)
/* 645:    */   {
/* 646:667 */     this.hdzt = hdzt;
/* 647:    */   }
/* 648:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.JiaBan1
 * JD-Core Version:    0.7.0.1
 */