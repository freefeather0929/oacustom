/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
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
/*  14:    */ public class QingJia
/*  15:    */ {
/*  16: 16 */   private static Log log = LogFactory.getLog(QingJia.class.getName());
/*  17:    */   private int userid;
/*  18:    */   private int mainid;
/*  19:    */   private String rq;
/*  20:    */   private String qjlb;
/*  21:    */   private String kssj;
/*  22:    */   private String jssj;
/*  23:    */   private int hdzt;
/*  24:    */   private String xq;
/*  25:    */   private int id;
/*  26:    */   private String hdkssj;
/*  27:    */   private String hdjssj;
/*  28:    */   private String dkjl;
/*  29:    */   private String appnom;
/*  30:    */   private String gh;
/*  31:    */   private double hdgs;
/*  32:    */   private String jdid;
/*  33:    */   private String bzgzsj;
/*  34:    */   private String kqhdyf;
/*  35:    */   private String lcspjsrq;
/*  36:    */   private int kqy;
/*  37:    */   private int row_id;
/*  38:    */   
/*  39:    */   public Log getLog()
/*  40:    */   {
/*  41: 19 */     return log;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setLog(Log log)
/*  45:    */   {
/*  46: 23 */     log = log;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public QingJia() {}
/*  50:    */   
/*  51:    */   public void insert()
/*  52:    */     throws Exception
/*  53:    */   {
/*  54:    */     try
/*  55:    */     {
/*  56: 57 */       String sql = "INSERT INTO formtable_main_89_dt3 (userid,mainid,rq,qjlb,kssj,jssj,hdzt,xq,hdkssj,hdjssj,dkjl,appnom,gh,hdgs,jdid,bzgzsj,kqhdyf,lcspjsrq,kqy,row_id)";
/*  57: 58 */       sql = sql + " VALUES  (";
/*  58: 59 */       sql = sql + "'" + this.userid + "',";
/*  59: 60 */       sql = sql + "'" + this.mainid + "',";
/*  60: 61 */       sql = sql + "'" + this.rq + "',";
/*  61: 62 */       sql = sql + "'" + this.qjlb + "',";
/*  62: 63 */       sql = sql + "'" + this.kssj + "',";
/*  63: 64 */       sql = sql + "'" + this.jssj + "',";
/*  64: 65 */       sql = sql + "'" + this.hdzt + "',";
/*  65: 66 */       sql = sql + "'" + this.xq + "',";
/*  66: 67 */       sql = sql + "'" + this.hdkssj + "',";
/*  67: 68 */       sql = sql + "'" + this.hdjssj + "',";
/*  68: 69 */       sql = sql + "'" + this.dkjl + "',";
/*  69: 70 */       sql = sql + "'" + this.appnom + "',";
/*  70: 71 */       sql = sql + "'" + this.gh + "',";
/*  71: 72 */       sql = sql + "'" + this.hdgs + "',";
/*  72: 73 */       sql = sql + "'" + this.jdid + "',";
/*  73: 74 */       sql = sql + "'" + this.bzgzsj + "',";
/*  74: 75 */       sql = sql + "'" + this.kqhdyf + "',";
/*  75: 76 */       sql = sql + "'" + this.lcspjsrq + "',";
/*  76: 77 */       sql = sql + "'" + this.kqy + "',";
/*  77: 78 */       sql = sql + "'" + this.row_id + "'";
/*  78: 79 */       sql = sql + ")";
/*  79: 80 */       RecordSet rs = new RecordSet();
/*  80: 81 */       rs.executeSql(sql);
/*  81:    */     }
/*  82:    */     catch (Exception e)
/*  83:    */     {
/*  84: 84 */       log.error("插入请假明细表三：" + e);
/*  85: 85 */       throw e;
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   public QingJia(int mainid, int row_id)
/*  90:    */     throws Exception
/*  91:    */   {
/*  92: 94 */     String sql = "select * from formtable_main_89_dt3  where mainid=" + 
/*  93: 95 */       mainid;
/*  94: 96 */     sql = sql + " and row_id = " + row_id;
/*  95:    */     try
/*  96:    */     {
/*  97: 99 */       RecordSet rs = new RecordSet();
/*  98:100 */       rs.executeSql(sql);
/*  99:101 */       this.appnom = rs.getString("appnom");
/* 100:102 */       this.bzgzsj = rs.getString("bzgzsj");
/* 101:103 */       this.dkjl = rs.getString("dkjl");
/* 102:104 */       this.gh = rs.getString("gh");
/* 103:105 */       this.hdgs = rs.getDouble("hdgs");
/* 104:106 */       this.hdjssj = rs.getString("hdjssj");
/* 105:107 */       this.hdkssj = rs.getString("hdkssj");
/* 106:108 */       this.hdzt = rs.getInt("hdzt");
/* 107:109 */       this.id = rs.getInt("id");
/* 108:110 */       this.jdid = rs.getString("jdid");
/* 109:111 */       this.jssj = rs.getString("jssj");
/* 110:112 */       this.kqhdyf = rs.getString("kqhdyf");
/* 111:113 */       this.kqy = rs.getInt("kqy");
/* 112:114 */       this.kssj = rs.getString("kssj");
/* 113:115 */       this.lcspjsrq = rs.getString("lcspjsrq");
/* 114:116 */       this.mainid = rs.getInt("mainid");
/* 115:117 */       this.qjlb = rs.getString("qjlb");
/* 116:118 */       this.row_id = rs.getInt("row_id");
/* 117:119 */       this.rq = rs.getString("rq");
/* 118:120 */       this.userid = rs.getInt("userid");
/* 119:121 */       this.xq = rs.getString("xq");
/* 120:    */     }
/* 121:    */     catch (Exception e)
/* 122:    */     {
/* 123:124 */       log.error("得到请假明细表三：" + e);
/* 124:125 */       throw e;
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   public static ArrayList<QingJia> queryList(int mainid)
/* 129:    */     throws Exception
/* 130:    */   {
/* 131:133 */     ArrayList<QingJia> aQJ = new ArrayList();
/* 132:134 */     String sql = "select * from formtable_main_89_dt3  where mainid=" + 
/* 133:135 */       mainid;
/* 134:    */     try
/* 135:    */     {
/* 136:137 */       RecordSet rs = new RecordSet();
/* 137:138 */       rs.executeSql(sql);
/* 138:139 */       while (rs.next())
/* 139:    */       {
/* 140:140 */         QingJia oQj = new QingJia();
/* 141:141 */         oQj.setAppnom(rs.getString("appnom"));
/* 142:142 */         oQj.setBzgzsj(rs.getString("bzgzsj"));
/* 143:143 */         oQj.setDkjl(rs.getString("dkjl"));
/* 144:144 */         oQj.setGh(rs.getString("gh"));
/* 145:145 */         oQj.setHdgs(rs.getDouble("hdgs"));
/* 146:146 */         oQj.setHdjssj(rs.getString("hdjssj"));
/* 147:147 */         oQj.setHdkssj(rs.getString("hdkssj"));
/* 148:148 */         oQj.setHdzt(rs.getInt("hdzt"));
/* 149:149 */         oQj.setId(rs.getInt("id"));
/* 150:150 */         oQj.setJdid(rs.getString("jdid"));
/* 151:151 */         oQj.setJssj(rs.getString("jssj"));
/* 152:152 */         oQj.setKqhdyf(rs.getString("kqhdyf"));
/* 153:153 */         oQj.setKqy(rs.getInt("kqy"));
/* 154:154 */         oQj.setKssj(rs.getString("kssj"));
/* 155:155 */         oQj.setLcspjsrq(rs.getString("lcspjsrq"));
/* 156:156 */         oQj.setMainid(rs.getInt("mainid"));
/* 157:157 */         oQj.setQjlb(rs.getString("qjlb"));
/* 158:158 */         oQj.setRow_id(rs.getInt("row_id"));
/* 159:159 */         oQj.setRq(rs.getString("rq"));
/* 160:160 */         oQj.setUserid(rs.getInt("userid"));
/* 161:161 */         oQj.setXq(rs.getString("xq"));
/* 162:162 */         aQJ.add(oQj);
/* 163:    */       }
/* 164:    */     }
/* 165:    */     catch (Exception e)
/* 166:    */     {
/* 167:166 */       log.error("根据mainid得到请假明细三：" + e);
/* 168:167 */       throw e;
/* 169:    */     }
/* 170:169 */     return aQJ;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public static ArrayList<QingJia> queryList(String userid, String ksrq, String jsrq, String requestid)
/* 174:    */     throws Exception
/* 175:    */   {
/* 176:177 */     ArrayList<QingJia> aQJ = new ArrayList();
/* 177:178 */     String sql = "select * from formtable_main_89 left JOIN formtable_main_89_dt3 on formtable_main_89.id=formtable_main_89_dt3.mainid  where userid=";
/* 178:179 */     sql = sql + userid + " and rq BETWEEN '" + ksrq + "' and '" + jsrq + 
/* 179:180 */       "'  and requestId!='" + requestid + "'";
/* 180:    */     try
/* 181:    */     {
/* 182:182 */       RecordSet rs = new RecordSet();
/* 183:183 */       rs.executeSql(sql);
/* 184:184 */       while (rs.next())
/* 185:    */       {
/* 186:185 */         QingJia oQj = new QingJia();
/* 187:186 */         oQj.setUserid(rs.getInt("userid"));
/* 188:187 */         oQj.setRq(rs.getString("rq"));
/* 189:188 */         oQj.setHdzt(rs.getInt("hdzt"));
/* 190:189 */         oQj.setJssj(rs.getString("jssj"));
/* 191:190 */         oQj.setKssj(rs.getString("kssj"));
/* 192:191 */         oQj.setMainid(rs.getInt("mainid"));
/* 193:192 */         oQj.setQjlb(rs.getString("qjlb"));
/* 194:193 */         oQj.setXq(rs.getString("xq"));
/* 195:194 */         aQJ.add(oQj);
/* 196:    */       }
/* 197:    */     }
/* 198:    */     catch (Exception e)
/* 199:    */     {
/* 200:198 */       throw e;
/* 201:    */     }
/* 202:200 */     return aQJ;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public static void delete(int mainid, int row_id)
/* 206:    */     throws Exception
/* 207:    */   {
/* 208:208 */     String sql = "";
/* 209:    */     try
/* 210:    */     {
/* 211:210 */       sql = "delete from formtable_main_89_dt3 where mainid=" + mainid;
/* 212:211 */       if (row_id > 0) {
/* 213:212 */         sql = sql + " and row_id=" + row_id;
/* 214:    */       }
/* 215:214 */       RecordSet rs = new RecordSet();
/* 216:215 */       rs.executeSql(sql);
/* 217:    */     }
/* 218:    */     catch (Exception e)
/* 219:    */     {
/* 220:218 */       log.error("删除请假明细表三信息：" + e);
/* 221:219 */       throw e;
/* 222:    */     }
/* 223:    */   }
/* 224:    */   
/* 225:    */   public static void delete(int id)
/* 226:    */     throws Exception
/* 227:    */   {
/* 228:228 */     String sql = "";
/* 229:    */     try
/* 230:    */     {
/* 231:230 */       sql = "delete from formtable_main_89_dt3 where id=" + id;
/* 232:    */       
/* 233:232 */       RecordSet rs = new RecordSet();
/* 234:233 */       rs.executeSql(sql);
/* 235:    */     }
/* 236:    */     catch (Exception e)
/* 237:    */     {
/* 238:236 */       log.error("删除请假明细表三信息：" + e);
/* 239:237 */       throw e;
/* 240:    */     }
/* 241:    */   }
/* 242:    */   
/* 243:    */   public static void update(int mainid, int type)
/* 244:    */     throws Exception
/* 245:    */   {
/* 246:    */     try
/* 247:    */     {
/* 248:246 */       String sql = "update formtable_main_89_dt3 set hdzt=" + type;
/* 249:247 */       sql = sql + " where mainid=" + mainid;
/* 250:248 */       RecordSet rs = new RecordSet();
/* 251:249 */       rs.executeSql(sql);
/* 252:    */     }
/* 253:    */     catch (Exception e)
/* 254:    */     {
/* 255:252 */       log.error("更新请假明细表三hdzt出错：" + e);
/* 256:253 */       throw e;
/* 257:    */     }
/* 258:    */   }
/* 259:    */   
/* 260:    */   public static void check(int mainid)
/* 261:    */     throws Exception
/* 262:    */   {
/* 263:258 */     String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
/* 264:259 */     String sql = "";
/* 265:260 */     if (mainid == 0)
/* 266:    */     {
/* 267:261 */       sql = "SELECT * FROM formtable_main_89_dt3 WHERE rq<'" + nowDate;
/* 268:262 */       sql = sql + "' AND hdzt=0 ORDER BY mainid,row_id";
/* 269:    */     }
/* 270:    */     else
/* 271:    */     {
/* 272:264 */       sql = 
/* 273:265 */         "SELECT * FROM formtable_main_89_dt3 WHERE mainid=" + mainid + " ORDER BY row_id";
/* 274:    */     }
/* 275:267 */     RecordSet rs = new RecordSet();
/* 276:268 */     rs.executeSql(sql);
/* 277:269 */     System.out.println(nowDate);
/* 278:    */   }
/* 279:    */   
/* 280:    */   public static void checkList(int mainid)
/* 281:    */     throws Exception
/* 282:    */   {
/* 283:278 */     log.error("请假明细核定：");
/* 284:279 */     String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
/* 285:280 */     String sql = "";
/* 286:281 */     if (mainid == 0)
/* 287:    */     {
/* 288:282 */       sql = "SELECT * FROM formtable_main_89_dt3 WHERE rq<'" + nowDate;
/* 289:283 */       sql = sql + "' AND hdzt=0 ORDER BY mainid,row_id";
/* 290:    */     }
/* 291:    */     else
/* 292:    */     {
/* 293:285 */       sql = 
/* 294:286 */         "SELECT * FROM formtable_main_89_dt3 WHERE mainid=" + mainid + " ORDER BY row_id";
/* 295:    */     }
/* 296:288 */     RecordSet rs = new RecordSet();
/* 297:289 */     rs.executeSql(sql);
/* 298:290 */     rs.getDouble("");
/* 299:    */     label636:
/* 300:    */     label639:
/* 301:291 */     while (rs.next())
/* 302:    */     {
/* 303:294 */       QingJia qj_three = new QingJia();
/* 304:295 */       qj_three.setId(rs.getInt("id"));
/* 305:296 */       qj_three.setMainid(rs.getInt("mainid"));
/* 306:297 */       qj_three.setRq(rs.getString("rq"));
/* 307:298 */       qj_three.setKssj(rs.getString("kssj"));
/* 308:299 */       qj_three.setJssj(rs.getString("jssj"));
/* 309:300 */       qj_three.setQjlb(rs.getString("qjlb"));
/* 310:301 */       qj_three.setUserid(rs.getInt("userid"));
/* 311:302 */       qj_three.setHdzt(rs.getInt("hdzt"));
/* 312:303 */       qj_three.setXq(rs.getString("xq"));
/* 313:304 */       qj_three.setRow_id(rs.getInt("row_id"));
/* 314:305 */       QingJia0 qj_main = new QingJia0(qj_three.getMainid());
/* 315:306 */       if ((qj_main.getAppnom() != null) && (!qj_main.getAppnom().equals(" ")))
/* 316:    */       {
/* 317:309 */         UserInfo userInfo = new UserInfo(rs.getInt("userid"));
/* 318:310 */         qj_three.setGh(userInfo.getCode());
/* 319:311 */         if (qj_three.getRq().compareTo(nowDate) < 0)
/* 320:    */         {
/* 321:312 */           boolean flag = false;
/* 322:313 */           DaKaRecord oDK = new DaKaRecord(userInfo.getCode(), 
/* 323:314 */             rs.getString("rq"));
/* 324:315 */           log.error("打卡记录：" + oDK.getDate() + oDK.getDk_list());
/* 325:318 */           for (int i = 0; i < oDK.getDk_list().size(); i++) {
/* 326:319 */             if ((((String)oDK.getDk_list().get(i)).equals("")) || 
/* 327:    */             
/* 328:321 */               (((String)oDK.getDk_list().get(i)).equalsIgnoreCase("null")))
/* 329:    */             {
/* 330:322 */               oDK.getDk_list().remove(i);
/* 331:323 */               i--;
/* 332:    */             }
/* 333:    */           }
/* 334:328 */           Collections.sort(oDK.getDk_list());
/* 335:329 */           if (oDK.getDk_list().size() == 0)
/* 336:    */           {
/* 337:330 */             oDK.setFirsttime("");
/* 338:331 */             oDK.setLasttime("");
/* 339:332 */             qj_three.setDkjl("");
/* 340:    */           }
/* 341:    */           else
/* 342:    */           {
/* 343:334 */             oDK.setFirsttime((String)oDK.getDk_list().get(0));
/* 344:335 */             oDK.setLasttime((String)oDK.getDk_list().get(
/* 345:336 */               oDK.getDk_list().size() - 1));
/* 346:337 */             qj_three.setDkjl(oDK.getDk_list().toString());
/* 347:    */           }
/* 348:339 */           switch ((i = qj_three.getXq()).hashCode())
/* 349:    */           {
/* 350:    */           case 25962637: 
/* 351:339 */             if (i.equals("星期六")) {
/* 352:    */               break;
/* 353:    */             }
/* 354:    */             break;
/* 355:    */           case 25967877: 
/* 356:339 */             if (!i.equals("星期日"))
/* 357:    */             {
/* 358:    */               break label636;
/* 359:341 */               flag = false;
/* 360:    */               break label639;
/* 361:    */             }
/* 362:    */             else
/* 363:    */             {
/* 364:344 */               flag = false;
/* 365:    */             }
/* 366:345 */             break;
/* 367:    */           }
/* 368:347 */           flag = true;
/* 369:    */           
/* 370:    */ 
/* 371:350 */           String bz_Stime = userInfo.getStartWorkTime();
/* 372:351 */           String bz_Etime = userInfo.getEndWorkTime();
/* 373:    */           
/* 374:353 */           dingHanTools dht = new dingHanTools();
/* 375:354 */           HashMap<String, String> map = dht.getJJR(
/* 376:355 */             String.valueOf(userInfo.getName()), 
/* 377:356 */             qj_three.getRq());
/* 378:357 */           log.error("name:" + userInfo.getName() + "   rq:" + 
/* 379:358 */             qj_three.getRq());
/* 380:359 */           log.error("1212" + map.toString() + "   " + map.size());
/* 381:    */           String jrlx;
/* 382:360 */           if ((map != null) && (map.size() > 0))
/* 383:    */           {
/* 384:361 */             jrlx = Util.null2String((String)map.get(userInfo
/* 385:362 */               .getName() + "_jrlx"));
/* 386:363 */             if ("8".equals(jrlx))
/* 387:    */             {
/* 388:364 */               String kssj = Util.null2String((String)map.get(userInfo
/* 389:365 */                 .getName() + "_kssj"));
/* 390:366 */               if (kssj.compareTo(bz_Stime) > 0) {
/* 391:367 */                 bz_Stime = Util.null2String((String)map.get(userInfo
/* 392:368 */                   .getName() + "_kssj"));
/* 393:    */               }
/* 394:370 */               bz_Etime = Util.null2String((String)map.get(userInfo
/* 395:371 */                 .getName() + "_jssj"));
/* 396:372 */               flag = true;
/* 397:    */             }
/* 398:    */             else
/* 399:    */             {
/* 400:374 */               flag = false;
/* 401:    */             }
/* 402:    */           }
/* 403:377 */           log.error("上下班时间：" + bz_Etime + bz_Stime);
/* 404:379 */           switch ((jrlx = qj_three.getQjlb()).hashCode())
/* 405:    */           {
/* 406:    */           case 52: 
/* 407:379 */             if (jrlx.equals("4")) {
/* 408:    */               break;
/* 409:    */             }
/* 410:    */             break;
/* 411:    */           case 53: 
/* 412:379 */             if (jrlx.equals("5")) {
/* 413:    */               break;
/* 414:    */             }
/* 415:    */             break;
/* 416:    */           case 54: 
/* 417:    */           case 1571: 
/* 418:379 */             if ((jrlx.equals("6")) || ((goto 1106) && (jrlx.equals("14")))) {
/* 419:384 */               flag = false;
/* 420:    */             }
/* 421:385 */             break;
/* 422:    */           }
/* 423:    */           String hdjssj;
/* 424:389 */           if (flag)
/* 425:    */           {
/* 426:391 */             qj_three.setHdkssj(qj_three.getKssj());
/* 427:392 */             if (qj_three.getKssj().compareTo(bz_Stime) < 0) {
/* 428:393 */               qj_three.setHdkssj(bz_Stime);
/* 429:394 */             } else if (qj_three.getHdkssj().compareTo(
/* 430:395 */               userInfo.getAmStartWorkTime()) >= 0) {
/* 431:396 */               if (qj_three.getHdkssj().compareTo(
/* 432:397 */                 userInfo.getPmEndWorkTime()) < 0) {
/* 433:398 */                 qj_three.setHdkssj(userInfo.getPmEndWorkTime());
/* 434:    */               }
/* 435:    */             }
/* 436:400 */             qj_three.setHdjssj(qj_three.getJssj());
/* 437:401 */             hdjssj = nowDate + " " + qj_three.getJssj();
/* 438:403 */             for (int i = 0; i < oDK.getDk_list().size(); i++)
/* 439:    */             {
/* 440:404 */               String dksj = nowDate + " " + 
/* 441:405 */                 (String)oDK.getDk_list().get(i);
/* 442:406 */               double hd = 0.0D;
/* 443:407 */               hd = PublicVariant.getTimeDifference(dksj, hdjssj);
/* 444:408 */               hd = Arith.div(hd, 3600000.0D, 2) + 0.5D;
/* 445:409 */               if ((0.0D <= hd) && (hd <= 1.0D)) {
/* 446:    */                 break;
/* 447:    */               }
/* 448:411 */               if (dksj.compareTo(hdjssj) > 0)
/* 449:    */               {
/* 450:412 */                 qj_three.setHdjssj((String)oDK.getDk_list().get(i));
/* 451:413 */                 break;
/* 452:    */               }
/* 453:    */             }
/* 454:417 */             if (qj_three.getHdjssj().compareTo(bz_Etime) > 0) {
/* 455:418 */               qj_three.setHdjssj(bz_Etime);
/* 456:419 */             } else if (userInfo.getPmEndWorkTime().compareTo(
/* 457:420 */               qj_three.getHdjssj()) < 0) {
/* 458:421 */               if (userInfo.getPmEndWorkTime().compareTo(
/* 459:422 */                 qj_three.getHdjssj()) >= 0) {
/* 460:423 */                 qj_three.setHdjssj(userInfo.getAmStartWorkTime());
/* 461:    */               }
/* 462:    */             }
/* 463:426 */             String startTime = nowDate + " " + qj_three.getHdkssj();
/* 464:427 */             String endTime = nowDate + " 00:00";
/* 465:428 */             double t1 = 0.0D;
/* 466:429 */             t1 = 
/* 467:430 */               PublicVariant.getTimeDifference(startTime, endTime);
/* 468:431 */             t1 = Arith.div(t1, 3600000.0D, 2);
/* 469:432 */             double t2 = 12.0D;
/* 470:433 */             double t3 = 0.0D;
/* 471:434 */             startTime = nowDate + " " + qj_three.getHdjssj();
/* 472:435 */             t3 = 
/* 473:436 */               PublicVariant.getTimeDifference(startTime, endTime);
/* 474:437 */             t3 = Arith.div(t3, 3600000.0D, 2);
/* 475:438 */             double t4 = 13.0D;
/* 476:439 */             if (userInfo.getPmEndWorkTime().equals("13:30")) {
/* 477:440 */               t4 = 13.5D;
/* 478:    */             }
/* 479:442 */             double h = 0.0D;
/* 480:443 */             if (t3 < t2) {
/* 481:444 */               h = Arith.sub(t3, t1);
/* 482:445 */             } else if (t3 < t4) {
/* 483:446 */               h = Arith.sub(t2, t1);
/* 484:447 */             } else if (t1 < t2) {
/* 485:448 */               h = Arith.sub(t3, t1) - userInfo.getRest();
/* 486:449 */             } else if (t1 < t4) {
/* 487:450 */               h = Arith.sub(t3, t4);
/* 488:    */             } else {
/* 489:452 */               h = Arith.sub(t3, t1);
/* 490:    */             }
/* 491:455 */             int h1 = (int)h;
/* 492:456 */             if (h < 0.0D) {
/* 493:457 */               h = 0.0D;
/* 494:458 */             } else if (h < h1 + 0.25D) {
/* 495:459 */               h = h1;
/* 496:460 */             } else if (h < h1 + 0.75D) {
/* 497:461 */               h = h1 + 0.5D;
/* 498:    */             } else {
/* 499:463 */               h = h1 + 1;
/* 500:    */             }
/* 501:466 */             h = Arith.round(h, 1);
/* 502:467 */             qj_three.setHdgs((float)h);
/* 503:    */           }
/* 504:    */           else
/* 505:    */           {
/* 506:470 */             qj_three.setHdkssj("");
/* 507:471 */             qj_three.setHdjssj("");
/* 508:472 */             qj_three.setHdgs(0.0D);
/* 509:473 */             switch ((hdjssj = qj_three.getQjlb()).hashCode())
/* 510:    */             {
/* 511:    */             case 52: 
/* 512:473 */               if (hdjssj.equals("4")) {
/* 513:    */                 break;
/* 514:    */               }
/* 515:    */               break;
/* 516:    */             case 53: 
/* 517:473 */               if (hdjssj.equals("5")) {
/* 518:    */                 break;
/* 519:    */               }
/* 520:    */               break;
/* 521:    */             case 54: 
/* 522:    */             case 1571: 
/* 523:473 */               if ((hdjssj.equals("6")) || ((goto 1933) && (hdjssj.equals("14"))))
/* 524:    */               {
/* 525:478 */                 qj_three.setHdgs(8.0D);
/* 526:479 */                 qj_three.setHdkssj(bz_Etime);
/* 527:480 */                 qj_three.setHdjssj(bz_Etime);
/* 528:    */               }
/* 529:481 */               break;
/* 530:    */             }
/* 531:    */           }
/* 532:487 */           qj_three.setHdzt(1);
/* 533:    */           
/* 534:    */ 
/* 535:490 */           delete(qj_three.getId());
/* 536:491 */           log.error("明细表：" + qj_three.toString());
/* 537:492 */           qj_three.insert();
/* 538:    */         }
/* 539:498 */         String nowMon = nowDate.substring(5, 7);
/* 540:499 */         QJtemp qJtemp = new QJtemp();
/* 541:    */         
/* 542:501 */         qJtemp.setFlowno(qj_main.getAppnom());
/* 543:502 */         qJtemp.setHdgs(String.valueOf(qj_three.getHdgs()));
/* 544:503 */         qJtemp.setHdjssj(qj_three.getHdjssj());
/* 545:504 */         qJtemp.setHdkssj(qj_three.getHdkssj());
/* 546:505 */         qJtemp.setHdyf(nowMon);
/* 547:506 */         qJtemp.setHrmid(qj_three.getUserid());
/* 548:507 */         qJtemp.setHrmno(qj_three.getGh());
/* 549:508 */         qJtemp.setKqr(qj_three.getKqy());
/* 550:509 */         qJtemp.setMainid(String.valueOf(qj_three.getMainid()));
/* 551:510 */         qJtemp.setQjlx(Integer.parseInt(qj_three.getQjlb()));
/* 552:511 */         qJtemp.setQjrq(qj_three.getRq());
/* 553:512 */         qJtemp.setXq(qj_three.getXq());
/* 554:513 */         qJtemp.setDataid(String.valueOf(qj_main.getRequestId()));
/* 555:    */         
/* 556:515 */         QJtemp.delete(qj_three.getMainid(), qj_three.getRq());
/* 557:    */         
/* 558:517 */         qJtemp.insert();
/* 559:    */       }
/* 560:    */     }
/* 561:    */   }
/* 562:    */   
/* 563:    */   public String toString()
/* 564:    */   {
/* 565:527 */     return 
/* 566:    */     
/* 567:    */ 
/* 568:    */ 
/* 569:    */ 
/* 570:    */ 
/* 571:    */ 
/* 572:534 */       "QingJia [userid=" + this.userid + ", mainid=" + this.mainid + ", rq=" + this.rq + ", qjlb=" + this.qjlb + ", kssj=" + this.kssj + ", jssj=" + this.jssj + ", hdzt=" + this.hdzt + ", xq=" + this.xq + ", id=" + this.id + ", hdkssj=" + this.hdkssj + ", hdjssj=" + this.hdjssj + ", dkjl=" + this.dkjl + ", appnom=" + this.appnom + ", gh=" + this.gh + ", hdgs=" + this.hdgs + ", jdid=" + this.jdid + ", bzgzsj=" + this.bzgzsj + ", kqhdyf=" + this.kqhdyf + ", lcspjsrq=" + this.lcspjsrq + ", kqy=" + this.kqy + ", row_id=" + this.row_id + "]";
/* 573:    */   }
/* 574:    */   
/* 575:    */   public int getUserid()
/* 576:    */   {
/* 577:538 */     return this.userid;
/* 578:    */   }
/* 579:    */   
/* 580:    */   public void setUserid(int userid)
/* 581:    */   {
/* 582:542 */     this.userid = userid;
/* 583:    */   }
/* 584:    */   
/* 585:    */   public int getMainid()
/* 586:    */   {
/* 587:546 */     return this.mainid;
/* 588:    */   }
/* 589:    */   
/* 590:    */   public void setMainid(int mainid)
/* 591:    */   {
/* 592:550 */     this.mainid = mainid;
/* 593:    */   }
/* 594:    */   
/* 595:    */   public String getRq()
/* 596:    */   {
/* 597:554 */     return this.rq;
/* 598:    */   }
/* 599:    */   
/* 600:    */   public void setRq(String rq)
/* 601:    */   {
/* 602:558 */     this.rq = rq;
/* 603:    */   }
/* 604:    */   
/* 605:    */   public String getQjlb()
/* 606:    */   {
/* 607:562 */     return this.qjlb;
/* 608:    */   }
/* 609:    */   
/* 610:    */   public void setQjlb(String qjlb)
/* 611:    */   {
/* 612:566 */     this.qjlb = qjlb;
/* 613:    */   }
/* 614:    */   
/* 615:    */   public String getKssj()
/* 616:    */   {
/* 617:570 */     return this.kssj;
/* 618:    */   }
/* 619:    */   
/* 620:    */   public void setKssj(String kssj)
/* 621:    */   {
/* 622:574 */     this.kssj = kssj;
/* 623:    */   }
/* 624:    */   
/* 625:    */   public String getJssj()
/* 626:    */   {
/* 627:578 */     return this.jssj;
/* 628:    */   }
/* 629:    */   
/* 630:    */   public void setJssj(String jssj)
/* 631:    */   {
/* 632:582 */     this.jssj = jssj;
/* 633:    */   }
/* 634:    */   
/* 635:    */   public String getXq()
/* 636:    */   {
/* 637:586 */     return this.xq;
/* 638:    */   }
/* 639:    */   
/* 640:    */   public void setXq(String xq)
/* 641:    */   {
/* 642:590 */     this.xq = xq;
/* 643:    */   }
/* 644:    */   
/* 645:    */   public int getId()
/* 646:    */   {
/* 647:594 */     return this.id;
/* 648:    */   }
/* 649:    */   
/* 650:    */   public void setId(int id)
/* 651:    */   {
/* 652:598 */     this.id = id;
/* 653:    */   }
/* 654:    */   
/* 655:    */   public String getHdkssj()
/* 656:    */   {
/* 657:602 */     return this.hdkssj;
/* 658:    */   }
/* 659:    */   
/* 660:    */   public void setHdkssj(String hdkssj)
/* 661:    */   {
/* 662:606 */     this.hdkssj = hdkssj;
/* 663:    */   }
/* 664:    */   
/* 665:    */   public String getHdjssj()
/* 666:    */   {
/* 667:610 */     return this.hdjssj;
/* 668:    */   }
/* 669:    */   
/* 670:    */   public void setHdjssj(String hdjssj)
/* 671:    */   {
/* 672:614 */     this.hdjssj = hdjssj;
/* 673:    */   }
/* 674:    */   
/* 675:    */   public String getDkjl()
/* 676:    */   {
/* 677:618 */     return this.dkjl;
/* 678:    */   }
/* 679:    */   
/* 680:    */   public void setDkjl(String dkjl)
/* 681:    */   {
/* 682:622 */     this.dkjl = dkjl;
/* 683:    */   }
/* 684:    */   
/* 685:    */   public String getAppnom()
/* 686:    */   {
/* 687:626 */     return this.appnom;
/* 688:    */   }
/* 689:    */   
/* 690:    */   public void setAppnom(String appnom)
/* 691:    */   {
/* 692:630 */     this.appnom = appnom;
/* 693:    */   }
/* 694:    */   
/* 695:    */   public String getGh()
/* 696:    */   {
/* 697:634 */     return this.gh;
/* 698:    */   }
/* 699:    */   
/* 700:    */   public void setGh(String gh)
/* 701:    */   {
/* 702:638 */     this.gh = gh;
/* 703:    */   }
/* 704:    */   
/* 705:    */   public double getHdgs()
/* 706:    */   {
/* 707:642 */     return this.hdgs;
/* 708:    */   }
/* 709:    */   
/* 710:    */   public void setHdgs(double hdgs)
/* 711:    */   {
/* 712:646 */     this.hdgs = hdgs;
/* 713:    */   }
/* 714:    */   
/* 715:    */   public String getJdid()
/* 716:    */   {
/* 717:650 */     return this.jdid;
/* 718:    */   }
/* 719:    */   
/* 720:    */   public void setJdid(String jdid)
/* 721:    */   {
/* 722:654 */     this.jdid = jdid;
/* 723:    */   }
/* 724:    */   
/* 725:    */   public String getBzgzsj()
/* 726:    */   {
/* 727:658 */     return this.bzgzsj;
/* 728:    */   }
/* 729:    */   
/* 730:    */   public void setBzgzsj(String bzgzsj)
/* 731:    */   {
/* 732:662 */     this.bzgzsj = bzgzsj;
/* 733:    */   }
/* 734:    */   
/* 735:    */   public String getKqhdyf()
/* 736:    */   {
/* 737:666 */     return this.kqhdyf;
/* 738:    */   }
/* 739:    */   
/* 740:    */   public void setKqhdyf(String kqhdyf)
/* 741:    */   {
/* 742:670 */     this.kqhdyf = kqhdyf;
/* 743:    */   }
/* 744:    */   
/* 745:    */   public String getLcspjsrq()
/* 746:    */   {
/* 747:674 */     return this.lcspjsrq;
/* 748:    */   }
/* 749:    */   
/* 750:    */   public void setLcspjsrq(String lcspjsrq)
/* 751:    */   {
/* 752:678 */     this.lcspjsrq = lcspjsrq;
/* 753:    */   }
/* 754:    */   
/* 755:    */   public int getKqy()
/* 756:    */   {
/* 757:682 */     return this.kqy;
/* 758:    */   }
/* 759:    */   
/* 760:    */   public void setKqy(int kqy)
/* 761:    */   {
/* 762:686 */     this.kqy = kqy;
/* 763:    */   }
/* 764:    */   
/* 765:    */   public int getRow_id()
/* 766:    */   {
/* 767:690 */     return this.row_id;
/* 768:    */   }
/* 769:    */   
/* 770:    */   public void setRow_id(int row_id)
/* 771:    */   {
/* 772:694 */     this.row_id = row_id;
/* 773:    */   }
/* 774:    */   
/* 775:    */   public int getHdzt()
/* 776:    */   {
/* 777:698 */     return this.hdzt;
/* 778:    */   }
/* 779:    */   
/* 780:    */   public void setHdzt(int hdzt)
/* 781:    */   {
/* 782:702 */     this.hdzt = hdzt;
/* 783:    */   }
/* 784:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.QingJia
 * JD-Core Version:    0.7.0.1
 */