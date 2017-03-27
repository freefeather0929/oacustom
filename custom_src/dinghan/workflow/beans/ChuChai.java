/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import org.apache.commons.logging.Log;
/*   5:    */ import org.apache.commons.logging.LogFactory;
/*   6:    */ import weaver.conn.RecordSet;
/*   7:    */ 
/*   8:    */ public class ChuChai
/*   9:    */ {
/*  10: 11 */   private static Log log = LogFactory.getLog(ChuChai.class.getName());
/*  11:    */   private int id;
/*  12:    */   private int requestId;
/*  13:    */   private int zjzg;
/*  14:    */   private int proposer;
/*  15:    */   private int sjzg;
/*  16:    */   private int stdept1;
/*  17:    */   private int stdept2;
/*  18:    */   private int stdept3;
/*  19:    */   private String appnom;
/*  20:    */   private String setdate;
/*  21:    */   private String yjccsj;
/*  22:    */   private String ccsj2;
/*  23:    */   private String ccdd;
/*  24:    */   private String txry;
/*  25:    */   private String lxdh;
/*  26:    */   private String ccsy;
/*  27:    */   private String jhxcap;
/*  28:    */   private String lsh2;
/*  29:    */   private String sjkssj;
/*  30:    */   private String sjjssj;
/*  31:    */   private String jtxcap;
/*  32:    */   private String bz;
/*  33:    */   private String fj;
/*  34:    */   private String ccsj1;
/*  35:    */   private String ccsj3;
/*  36:    */   private String kssj1;
/*  37:    */   private String kssj3;
/*  38:    */   private double ztfy;
/*  39:    */   private double zsf;
/*  40:    */   private double snjtf;
/*  41:    */   private double gwzf;
/*  42:    */   private double zdcf;
/*  43:    */   private double zdlp;
/*  44:    */   private double hjy;
/*  45:    */   private double zbqnzts;
/*  46:    */   private double fy1;
/*  47:    */   private double zbqwzts;
/*  48:    */   private double fy2;
/*  49:    */   private double fwxsyz;
/*  50:    */   private double fy3;
/*  51:    */   private double gcjfzy;
/*  52:    */   private double fy4;
/*  53:    */   private double qtfy;
/*  54:    */   private double yjccts;
/*  55:    */   
/*  56:    */   public Log getLog()
/*  57:    */   {
/*  58: 14 */     return log;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setLog(Log log)
/*  62:    */   {
/*  63: 18 */     log = log;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public ChuChai() {}
/*  67:    */   
/*  68:    */   public ChuChai(String requestid)
/*  69:    */   {
/*  70: 74 */     String sql = "select * from formtable_main_92 where requestid=" + 
/*  71: 75 */       requestid;
/*  72:    */     try
/*  73:    */     {
/*  74: 77 */       RecordSet rs = new RecordSet();
/*  75: 78 */       rs.executeSql(sql);
/*  76: 79 */       if (rs.next())
/*  77:    */       {
/*  78: 80 */         this.appnom = rs.getString("appnom");
/*  79: 81 */         this.bz = rs.getString("bz");
/*  80: 82 */         this.ccdd = rs.getString("ccdd");
/*  81: 83 */         this.ccsj1 = rs.getString("ccsj1");
/*  82: 84 */         this.ccsj2 = rs.getString("ccsj2");
/*  83: 85 */         this.ccsj3 = rs.getString("ccsj3");
/*  84: 86 */         this.ccsy = rs.getString("ccsy");
/*  85: 87 */         this.fj = rs.getString("fj");
/*  86: 88 */         this.fwxsyz = rs.getDouble("fwxsyz");
/*  87: 89 */         this.fy1 = rs.getDouble("fy1");
/*  88: 90 */         this.fy2 = rs.getDouble("fy2");
/*  89: 91 */         this.fy3 = rs.getDouble("fy3");
/*  90: 92 */         this.fy4 = rs.getDouble("fy4");
/*  91: 93 */         this.gcjfzy = rs.getDouble("gcjfzy");
/*  92: 94 */         this.gwzf = rs.getDouble("gwzf");
/*  93: 95 */         this.hjy = rs.getDouble("hjy");
/*  94: 96 */         this.id = rs.getInt("id");
/*  95: 97 */         this.jhxcap = rs.getString("jhxcap");
/*  96: 98 */         this.jtxcap = rs.getString("jtxcap");
/*  97: 99 */         this.kssj1 = rs.getString("kssj1");
/*  98:100 */         this.kssj3 = rs.getString("kssj3");
/*  99:101 */         this.lsh2 = rs.getString("lsh2");
/* 100:102 */         this.lxdh = rs.getString("lxdh");
/* 101:103 */         this.proposer = rs.getInt("proposer");
/* 102:104 */         this.qtfy = rs.getDouble("qtfy");
/* 103:105 */         this.requestId = rs.getInt("requestId");
/* 104:106 */         this.setdate = rs.getString("setdate");
/* 105:107 */         this.sjjssj = rs.getString("sjjssj");
/* 106:108 */         this.sjkssj = rs.getString("sjkssj");
/* 107:109 */         this.sjzg = rs.getInt("sjzg");
/* 108:110 */         this.snjtf = rs.getDouble("snjtf");
/* 109:111 */         this.stdept1 = rs.getInt("stdept1");
/* 110:112 */         this.stdept2 = rs.getInt("stdept2");
/* 111:113 */         this.stdept3 = rs.getInt("stdept3");
/* 112:114 */         this.txry = rs.getString("txry");
/* 113:115 */         this.yjccsj = rs.getString("yjccsj");
/* 114:116 */         this.yjccts = rs.getDouble("yjccts");
/* 115:117 */         this.zbqnzts = rs.getDouble("zbqnzts");
/* 116:118 */         this.zbqwzts = rs.getDouble("zbqwzts");
/* 117:119 */         this.zdcf = rs.getDouble("zdcf");
/* 118:120 */         this.zdlp = rs.getDouble("zdlp");
/* 119:121 */         this.zjzg = rs.getInt("zjzg");
/* 120:122 */         this.zsf = rs.getDouble("zsf");
/* 121:123 */         this.ztfy = rs.getDouble("ztfy");
/* 122:    */       }
/* 123:    */     }
/* 124:    */     catch (Exception e)
/* 125:    */     {
/* 126:127 */       log.error("获取出差主表信息:" + e);
/* 127:    */     }
/* 128:    */   }
/* 129:    */   
/* 130:    */   public ChuChai(int mainid)
/* 131:    */   {
/* 132:136 */     String sql = "select * from formtable_main_92 where id=" + mainid;
/* 133:    */     try
/* 134:    */     {
/* 135:138 */       RecordSet rs = new RecordSet();
/* 136:139 */       rs.executeSql(sql);
/* 137:140 */       if (rs.next())
/* 138:    */       {
/* 139:141 */         this.requestId = rs.getInt("requestId");
/* 140:142 */         this.proposer = rs.getInt("proposer");
/* 141:143 */         this.appnom = rs.getString("appnom");
/* 142:144 */         this.lsh2 = rs.getString("lsh2");
/* 143:    */       }
/* 144:    */     }
/* 145:    */     catch (Exception e)
/* 146:    */     {
/* 147:148 */       log.error("获取出差主表信息:" + e);
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   public static ArrayList<ChuChai> queryList(String userid, String ksrq, String jsrq, String requestid)
/* 152:    */     throws Exception
/* 153:    */   {
/* 154:157 */     ArrayList<ChuChai> ccList = new ArrayList();
/* 155:    */     try
/* 156:    */     {
/* 157:159 */       String sql = "select * from formtable_main_92 where (proposer='";
/* 158:160 */       sql = sql + userid + "' AND  yjccsj BETWEEN '" + ksrq + "' and '";
/* 159:161 */       sql = sql + jsrq + "' or  proposer='" + userid;
/* 160:162 */       sql = sql + "' AND ccsj2 BETWEEN '" + ksrq + "' and '" + jsrq + "'";
/* 161:163 */       sql = sql + " or proposer='" + userid + "' AND yjccsj<='" + ksrq;
/* 162:164 */       sql = sql + "' AND ccsj2>='" + jsrq + 
/* 163:165 */         "') and appnom!='' and requestId!='" + requestid + "'";
/* 164:166 */       RecordSet rs = new RecordSet();
/* 165:167 */       rs.executeSql(sql);
/* 166:168 */       while (rs.next())
/* 167:    */       {
/* 168:169 */         ChuChai occ = new ChuChai();
/* 169:170 */         occ.setYjccsj(rs.getString("yjccsj"));
/* 170:171 */         occ.setCcsj2(rs.getString("ccsj2"));
/* 171:172 */         occ.setCcsj1(rs.getString("ccsj1"));
/* 172:173 */         occ.setCcsj3(rs.getString("ccsj3"));
/* 173:    */         
/* 174:175 */         occ.setSjkssj(rs.getString("sjkssj"));
/* 175:176 */         occ.setSjjssj(rs.getString("sjjssj"));
/* 176:177 */         occ.setKssj1(rs.getString("kssj1"));
/* 177:178 */         occ.setKssj3(rs.getString("kssj3"));
/* 178:179 */         ccList.add(occ);
/* 179:    */       }
/* 180:    */     }
/* 181:    */     catch (Exception e)
/* 182:    */     {
/* 183:183 */       log.error("得到出差主表信息失败：" + e);
/* 184:184 */       throw e;
/* 185:    */     }
/* 186:186 */     return ccList;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public int getId()
/* 190:    */   {
/* 191:190 */     return this.id;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setId(int id)
/* 195:    */   {
/* 196:194 */     this.id = id;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public int getRequestId()
/* 200:    */   {
/* 201:198 */     return this.requestId;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setRequestId(int requestId)
/* 205:    */   {
/* 206:202 */     this.requestId = requestId;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public int getZjzg()
/* 210:    */   {
/* 211:206 */     return this.zjzg;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setZjzg(int zjzg)
/* 215:    */   {
/* 216:210 */     this.zjzg = zjzg;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public int getProposer()
/* 220:    */   {
/* 221:214 */     return this.proposer;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setProposer(int proposer)
/* 225:    */   {
/* 226:218 */     this.proposer = proposer;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public int getSjzg()
/* 230:    */   {
/* 231:222 */     return this.sjzg;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void setSjzg(int sjzg)
/* 235:    */   {
/* 236:226 */     this.sjzg = sjzg;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public int getStdept1()
/* 240:    */   {
/* 241:230 */     return this.stdept1;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setStdept1(int stdept1)
/* 245:    */   {
/* 246:234 */     this.stdept1 = stdept1;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public int getStdept2()
/* 250:    */   {
/* 251:238 */     return this.stdept2;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void setStdept2(int stdept2)
/* 255:    */   {
/* 256:242 */     this.stdept2 = stdept2;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public int getStdept3()
/* 260:    */   {
/* 261:246 */     return this.stdept3;
/* 262:    */   }
/* 263:    */   
/* 264:    */   public void setStdept3(int stdept3)
/* 265:    */   {
/* 266:250 */     this.stdept3 = stdept3;
/* 267:    */   }
/* 268:    */   
/* 269:    */   public String getAppnom()
/* 270:    */   {
/* 271:254 */     return this.appnom;
/* 272:    */   }
/* 273:    */   
/* 274:    */   public void setAppnom(String appnom)
/* 275:    */   {
/* 276:258 */     this.appnom = appnom;
/* 277:    */   }
/* 278:    */   
/* 279:    */   public String getSetdate()
/* 280:    */   {
/* 281:262 */     return this.setdate;
/* 282:    */   }
/* 283:    */   
/* 284:    */   public void setSetdate(String setdate)
/* 285:    */   {
/* 286:266 */     this.setdate = setdate;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public String getYjccsj()
/* 290:    */   {
/* 291:270 */     return this.yjccsj;
/* 292:    */   }
/* 293:    */   
/* 294:    */   public void setYjccsj(String yjccsj)
/* 295:    */   {
/* 296:274 */     this.yjccsj = yjccsj;
/* 297:    */   }
/* 298:    */   
/* 299:    */   public String getCcsj2()
/* 300:    */   {
/* 301:278 */     return this.ccsj2;
/* 302:    */   }
/* 303:    */   
/* 304:    */   public void setCcsj2(String ccsj2)
/* 305:    */   {
/* 306:282 */     this.ccsj2 = ccsj2;
/* 307:    */   }
/* 308:    */   
/* 309:    */   public String getCcdd()
/* 310:    */   {
/* 311:286 */     return this.ccdd;
/* 312:    */   }
/* 313:    */   
/* 314:    */   public void setCcdd(String ccdd)
/* 315:    */   {
/* 316:290 */     this.ccdd = ccdd;
/* 317:    */   }
/* 318:    */   
/* 319:    */   public String getTxry()
/* 320:    */   {
/* 321:294 */     return this.txry;
/* 322:    */   }
/* 323:    */   
/* 324:    */   public void setTxry(String txry)
/* 325:    */   {
/* 326:298 */     this.txry = txry;
/* 327:    */   }
/* 328:    */   
/* 329:    */   public String getLxdh()
/* 330:    */   {
/* 331:302 */     return this.lxdh;
/* 332:    */   }
/* 333:    */   
/* 334:    */   public void setLxdh(String lxdh)
/* 335:    */   {
/* 336:306 */     this.lxdh = lxdh;
/* 337:    */   }
/* 338:    */   
/* 339:    */   public String getCcsy()
/* 340:    */   {
/* 341:310 */     return this.ccsy;
/* 342:    */   }
/* 343:    */   
/* 344:    */   public void setCcsy(String ccsy)
/* 345:    */   {
/* 346:314 */     this.ccsy = ccsy;
/* 347:    */   }
/* 348:    */   
/* 349:    */   public String getJhxcap()
/* 350:    */   {
/* 351:318 */     return this.jhxcap;
/* 352:    */   }
/* 353:    */   
/* 354:    */   public void setJhxcap(String jhxcap)
/* 355:    */   {
/* 356:322 */     this.jhxcap = jhxcap;
/* 357:    */   }
/* 358:    */   
/* 359:    */   public String getLsh2()
/* 360:    */   {
/* 361:326 */     return this.lsh2;
/* 362:    */   }
/* 363:    */   
/* 364:    */   public void setLsh2(String lsh2)
/* 365:    */   {
/* 366:330 */     this.lsh2 = lsh2;
/* 367:    */   }
/* 368:    */   
/* 369:    */   public String getSjkssj()
/* 370:    */   {
/* 371:334 */     return this.sjkssj;
/* 372:    */   }
/* 373:    */   
/* 374:    */   public void setSjkssj(String sjkssj)
/* 375:    */   {
/* 376:338 */     this.sjkssj = sjkssj;
/* 377:    */   }
/* 378:    */   
/* 379:    */   public String getSjjssj()
/* 380:    */   {
/* 381:342 */     return this.sjjssj;
/* 382:    */   }
/* 383:    */   
/* 384:    */   public void setSjjssj(String sjjssj)
/* 385:    */   {
/* 386:346 */     this.sjjssj = sjjssj;
/* 387:    */   }
/* 388:    */   
/* 389:    */   public String getJtxcap()
/* 390:    */   {
/* 391:350 */     return this.jtxcap;
/* 392:    */   }
/* 393:    */   
/* 394:    */   public void setJtxcap(String jtxcap)
/* 395:    */   {
/* 396:354 */     this.jtxcap = jtxcap;
/* 397:    */   }
/* 398:    */   
/* 399:    */   public String getBz()
/* 400:    */   {
/* 401:358 */     return this.bz;
/* 402:    */   }
/* 403:    */   
/* 404:    */   public void setBz(String bz)
/* 405:    */   {
/* 406:362 */     this.bz = bz;
/* 407:    */   }
/* 408:    */   
/* 409:    */   public String getFj()
/* 410:    */   {
/* 411:366 */     return this.fj;
/* 412:    */   }
/* 413:    */   
/* 414:    */   public void setFj(String fj)
/* 415:    */   {
/* 416:370 */     this.fj = fj;
/* 417:    */   }
/* 418:    */   
/* 419:    */   public String getCcsj1()
/* 420:    */   {
/* 421:374 */     return this.ccsj1;
/* 422:    */   }
/* 423:    */   
/* 424:    */   public void setCcsj1(String ccsj1)
/* 425:    */   {
/* 426:378 */     this.ccsj1 = ccsj1;
/* 427:    */   }
/* 428:    */   
/* 429:    */   public String getCcsj3()
/* 430:    */   {
/* 431:382 */     return this.ccsj3;
/* 432:    */   }
/* 433:    */   
/* 434:    */   public void setCcsj3(String ccsj3)
/* 435:    */   {
/* 436:386 */     this.ccsj3 = ccsj3;
/* 437:    */   }
/* 438:    */   
/* 439:    */   public String getKssj1()
/* 440:    */   {
/* 441:390 */     return this.kssj1;
/* 442:    */   }
/* 443:    */   
/* 444:    */   public void setKssj1(String kssj1)
/* 445:    */   {
/* 446:394 */     this.kssj1 = kssj1;
/* 447:    */   }
/* 448:    */   
/* 449:    */   public String getKssj3()
/* 450:    */   {
/* 451:398 */     return this.kssj3;
/* 452:    */   }
/* 453:    */   
/* 454:    */   public void setKssj3(String kssj3)
/* 455:    */   {
/* 456:402 */     this.kssj3 = kssj3;
/* 457:    */   }
/* 458:    */   
/* 459:    */   public double getZtfy()
/* 460:    */   {
/* 461:406 */     return this.ztfy;
/* 462:    */   }
/* 463:    */   
/* 464:    */   public void setZtfy(double ztfy)
/* 465:    */   {
/* 466:410 */     this.ztfy = ztfy;
/* 467:    */   }
/* 468:    */   
/* 469:    */   public double getZsf()
/* 470:    */   {
/* 471:414 */     return this.zsf;
/* 472:    */   }
/* 473:    */   
/* 474:    */   public void setZsf(double zsf)
/* 475:    */   {
/* 476:418 */     this.zsf = zsf;
/* 477:    */   }
/* 478:    */   
/* 479:    */   public double getSnjtf()
/* 480:    */   {
/* 481:422 */     return this.snjtf;
/* 482:    */   }
/* 483:    */   
/* 484:    */   public void setSnjtf(double snjtf)
/* 485:    */   {
/* 486:426 */     this.snjtf = snjtf;
/* 487:    */   }
/* 488:    */   
/* 489:    */   public double getGwzf()
/* 490:    */   {
/* 491:430 */     return this.gwzf;
/* 492:    */   }
/* 493:    */   
/* 494:    */   public void setGwzf(double gwzf)
/* 495:    */   {
/* 496:434 */     this.gwzf = gwzf;
/* 497:    */   }
/* 498:    */   
/* 499:    */   public double getZdcf()
/* 500:    */   {
/* 501:438 */     return this.zdcf;
/* 502:    */   }
/* 503:    */   
/* 504:    */   public void setZdcf(double zdcf)
/* 505:    */   {
/* 506:442 */     this.zdcf = zdcf;
/* 507:    */   }
/* 508:    */   
/* 509:    */   public double getZdlp()
/* 510:    */   {
/* 511:446 */     return this.zdlp;
/* 512:    */   }
/* 513:    */   
/* 514:    */   public void setZdlp(double zdlp)
/* 515:    */   {
/* 516:450 */     this.zdlp = zdlp;
/* 517:    */   }
/* 518:    */   
/* 519:    */   public double getHjy()
/* 520:    */   {
/* 521:454 */     return this.hjy;
/* 522:    */   }
/* 523:    */   
/* 524:    */   public void setHjy(double hjy)
/* 525:    */   {
/* 526:458 */     this.hjy = hjy;
/* 527:    */   }
/* 528:    */   
/* 529:    */   public double getZbqnzts()
/* 530:    */   {
/* 531:462 */     return this.zbqnzts;
/* 532:    */   }
/* 533:    */   
/* 534:    */   public void setZbqnzts(double zbqnzts)
/* 535:    */   {
/* 536:466 */     this.zbqnzts = zbqnzts;
/* 537:    */   }
/* 538:    */   
/* 539:    */   public double getFy1()
/* 540:    */   {
/* 541:470 */     return this.fy1;
/* 542:    */   }
/* 543:    */   
/* 544:    */   public void setFy1(double fy1)
/* 545:    */   {
/* 546:474 */     this.fy1 = fy1;
/* 547:    */   }
/* 548:    */   
/* 549:    */   public double getZbqwzts()
/* 550:    */   {
/* 551:478 */     return this.zbqwzts;
/* 552:    */   }
/* 553:    */   
/* 554:    */   public void setZbqwzts(double zbqwzts)
/* 555:    */   {
/* 556:482 */     this.zbqwzts = zbqwzts;
/* 557:    */   }
/* 558:    */   
/* 559:    */   public double getFy2()
/* 560:    */   {
/* 561:486 */     return this.fy2;
/* 562:    */   }
/* 563:    */   
/* 564:    */   public void setFy2(double fy2)
/* 565:    */   {
/* 566:490 */     this.fy2 = fy2;
/* 567:    */   }
/* 568:    */   
/* 569:    */   public double getFwxsyz()
/* 570:    */   {
/* 571:494 */     return this.fwxsyz;
/* 572:    */   }
/* 573:    */   
/* 574:    */   public void setFwxsyz(double fwxsyz)
/* 575:    */   {
/* 576:498 */     this.fwxsyz = fwxsyz;
/* 577:    */   }
/* 578:    */   
/* 579:    */   public double getFy3()
/* 580:    */   {
/* 581:502 */     return this.fy3;
/* 582:    */   }
/* 583:    */   
/* 584:    */   public void setFy3(double fy3)
/* 585:    */   {
/* 586:506 */     this.fy3 = fy3;
/* 587:    */   }
/* 588:    */   
/* 589:    */   public double getGcjfzy()
/* 590:    */   {
/* 591:510 */     return this.gcjfzy;
/* 592:    */   }
/* 593:    */   
/* 594:    */   public void setGcjfzy(double gcjfzy)
/* 595:    */   {
/* 596:514 */     this.gcjfzy = gcjfzy;
/* 597:    */   }
/* 598:    */   
/* 599:    */   public double getFy4()
/* 600:    */   {
/* 601:518 */     return this.fy4;
/* 602:    */   }
/* 603:    */   
/* 604:    */   public void setFy4(double fy4)
/* 605:    */   {
/* 606:522 */     this.fy4 = fy4;
/* 607:    */   }
/* 608:    */   
/* 609:    */   public double getQtfy()
/* 610:    */   {
/* 611:526 */     return this.qtfy;
/* 612:    */   }
/* 613:    */   
/* 614:    */   public void setQtfy(double qtfy)
/* 615:    */   {
/* 616:530 */     this.qtfy = qtfy;
/* 617:    */   }
/* 618:    */   
/* 619:    */   public double getYjccts()
/* 620:    */   {
/* 621:534 */     return this.yjccts;
/* 622:    */   }
/* 623:    */   
/* 624:    */   public void setYjccts(double yjccts)
/* 625:    */   {
/* 626:538 */     this.yjccts = yjccts;
/* 627:    */   }
/* 628:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.ChuChai
 * JD-Core Version:    0.7.0.1
 */