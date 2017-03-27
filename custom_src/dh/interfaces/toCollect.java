/*    1:     */ package weaver.dh.interfaces;
/*    2:     */ 
/*    3:     */ import com.weaver.formmodel.util.DateHelper;
/*    4:     */ import java.math.BigDecimal;
/*    5:     */ import java.math.RoundingMode;
/*    6:     */ import java.text.SimpleDateFormat;
/*    7:     */ import java.util.ArrayList;
/*    8:     */ import java.util.Date;
/*    9:     */ import java.util.HashMap;
/*   10:     */ import java.util.List;
/*   11:     */ import java.util.Map;
/*   12:     */ import org.apache.commons.logging.Log;
/*   13:     */ import org.apache.commons.logging.LogFactory;
/*   14:     */ import weaver.conn.RecordSet;
/*   15:     */ import weaver.general.Util;
/*   16:     */ import weaver.interfaces.schedule.BaseCronJob;
/*   17:     */ 
/*   18:     */ public class toCollect
/*   19:     */   extends BaseCronJob
/*   20:     */ {
/*   21:  28 */   private Log log = LogFactory.getLog(toCollect.class.getName());
/*   22:     */   private String Sdays;
/*   23:     */   private String Edays;
/*   24:     */   private String type;
/*   25:     */   
/*   26:     */   public Log getLog()
/*   27:     */   {
/*   28:  31 */     return this.log;
/*   29:     */   }
/*   30:     */   
/*   31:     */   public void setLog(Log log)
/*   32:     */   {
/*   33:  35 */     this.log = log;
/*   34:     */   }
/*   35:     */   
/*   36:  41 */   private String resourceid = "";
/*   37:  42 */   private String flag = "0";
/*   38:  43 */   private String kqjSource = "kqj";
/*   39:  44 */   private String currentDay = "";
/*   40:  45 */   private String currentTime = DateHelper.getCurrentTime();
/*   41:  46 */   private Map<String, String> DkMap = new HashMap();
/*   42:  47 */   HashMap<String, String> cus_Map = new HashMap();
/*   43:  48 */   HashMap<String, String> waiChu_Map = new HashMap();
/*   44:  49 */   HashMap<String, String> qingJia_Map = new HashMap();
/*   45:  50 */   HashMap<String, String> chuChai_Map = new HashMap();
/*   46:  51 */   HashMap<String, String> jiaBan_Map = new HashMap();
/*   47:  52 */   HashMap<String, String> mxhz_Map = new HashMap();
/*   48:  53 */   HashMap<String, String> Map = new HashMap();
/*   49:  54 */   RecordSet rsA = new RecordSet();
/*   50:  55 */   private dingHanTools dht = new dingHanTools();
/*   51:     */   
/*   52:     */   public void getJump(String zt, String hrid, String dayB, String dayE)
/*   53:     */   {
/*   54:  70 */     this.log.error("toCollect");
/*   55:  71 */     getHrmCusData();
/*   56:  72 */     this.flag = "1";
/*   57:  73 */     String[] hid = { "0" };
/*   58:  74 */     if (!"".equals(Util.null2String(hrid))) {
/*   59:  75 */       hid = hrid.split(",");
/*   60:     */     }
/*   61:  77 */     for (int n = 0; n < hid.length; n++) {
/*   62:  78 */       if (!"".equals(Util.null2String(hid[n]))) {
/*   63:  79 */         this.resourceid = (this.resourceid + hid[n] + ",");
/*   64:     */       }
/*   65:     */     }
/*   66:  82 */     String getPeople = "select name from uf_hr_userinfo1 where Company = '" + 
/*   67:  83 */       zt + "'";
/*   68:  84 */     RecordSet r = new RecordSet();
/*   69:  85 */     r.executeSql(getPeople);
/*   70:  86 */     while (r.next())
/*   71:     */     {
/*   72:  87 */       String name = Util.null2String(r.getString("name"));
/*   73:  88 */       if (!"".equals(name)) {
/*   74:  89 */         this.resourceid = (this.resourceid + name + ",");
/*   75:     */       }
/*   76:     */     }
/*   77:  92 */     if (this.resourceid.endsWith(",")) {
/*   78:  93 */       this.resourceid = this.resourceid.substring(0, this.resourceid.length() - 1);
/*   79:     */     }
/*   80:  96 */     int l = (int)DateHelper.getDisDays(dayB, dayE);
/*   81:  98 */     if (l > 0)
/*   82:     */     {
/*   83:  99 */       for (int i = 0; i < l; i++)
/*   84:     */       {
/*   85: 100 */         this.currentDay = DateHelper.dayMove(dayB, i);
/*   86: 101 */         execute();
/*   87:     */       }
/*   88:     */     }
/*   89: 103 */     else if (l == 0)
/*   90:     */     {
/*   91: 104 */       this.currentDay = DateHelper.getCurrentDate();
/*   92: 105 */       execute();
/*   93:     */     }
/*   94:     */   }
/*   95:     */   
/*   96:     */   public void execute()
/*   97:     */   {
/*   98: 112 */     if ("0".equals(this.flag))
/*   99:     */     {
/*  100: 113 */       this.currentDay = DateHelper.getCurrentDate();
/*  101: 114 */       getHrmCusData();
/*  102:     */     }
/*  103: 116 */     init();
/*  104: 117 */     RecordSet hrm = new RecordSet();
/*  105: 118 */     RecordSet ydkq = new RecordSet();
/*  106: 119 */     RecordSet delRs = new RecordSet();
/*  107: 120 */     RecordSet rs = new RecordSet();
/*  108:     */     
/*  109: 122 */     String sqlHrm = "select id,workcode,jobtitle from HrmResource where id in (" + 
/*  110: 123 */       this.resourceid + ")";
/*  111: 124 */     if ("0".equals(this.flag)) {
/*  112: 125 */       sqlHrm = "select id,workcode,jobtitle from HrmResource where status in (1,2,3,5)";
/*  113:     */     }
/*  114: 130 */     hrm.executeSql(sqlHrm);
/*  115: 131 */     while (hrm.next())
/*  116:     */     {
/*  117: 132 */       String excSql = "";
/*  118: 133 */       String hid = Util.null2String(hrm.getString("id"));
/*  119:     */       
/*  120:     */ 
/*  121:     */ 
/*  122: 137 */       String delSql = "delete from uf_kqhzmx where xm = '" + hid + 
/*  123: 138 */         "' and kqrq = '" + this.currentDay + "'";
/*  124: 139 */       delRs.executeSql(delSql);
/*  125:     */       
/*  126:     */ 
/*  127:     */ 
/*  128:     */ 
/*  129:     */ 
/*  130: 145 */       String joinDate = (String)this.cus_Map.get(hid + "_rzrq");
/*  131: 146 */       String leaveDate = (String)this.cus_Map.get(hid + "_lzrq");
/*  132: 148 */       if (("".equals(joinDate)) || 
/*  133: 149 */         ((int)DateHelper.getDisDays(this.currentDay, joinDate) - 1 <= 0)) {
/*  134: 153 */         if (("".equals(leaveDate)) || 
/*  135: 154 */           ((int)DateHelper.getDisDays(leaveDate, this.currentDay) - 1 <= 0))
/*  136:     */         {
/*  137: 159 */           getQingjia(hid);
/*  138: 160 */           getChuChai(hid);
/*  139: 161 */           getWaiChu(hid);
/*  140: 162 */           getKqyc(hid);
/*  141:     */           
/*  142: 164 */           String workcode = Util.null2String(hrm.getString("workcode"));
/*  143: 165 */           String bz_Stime = Util.null2String((String)this.cus_Map.get(hid + "_18"));
/*  144: 166 */           if ("1".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))) {
/*  145: 167 */             bz_Stime = "09:30";
/*  146:     */           }
/*  147: 170 */           String bz_Etime = Util.null2String((String)this.cus_Map.get(hid + "_19"));
/*  148:     */           
/*  149: 172 */           String amE = Util.null2String((String)this.cus_Map.get(hid + "_amE"));
/*  150: 173 */           String pmS = Util.null2String((String)this.cus_Map.get(hid + "_pmS"));
/*  151:     */           
/*  152: 175 */           float restTime = getMinutesBetween(pmS, amE);
/*  153: 176 */           float restHour = new BigDecimal(restTime).divide(
/*  154: 177 */             new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  155:     */           
/*  156:     */ 
/*  157:     */ 
/*  158:     */ 
/*  159:     */ 
/*  160:     */ 
/*  161: 184 */           float amMin = getMinutesBetween(amE, bz_Stime);
/*  162: 185 */           float pmMin = getMinutesBetween(bz_Etime, pmS);
/*  163: 186 */           String tzxxks = "";
/*  164: 187 */           String tzxxjs = "";
/*  165: 188 */           HashMap<String, String> map = this.dht.getJJR(hid, this.currentDay);
/*  166: 189 */           int mn = 99;
/*  167: 190 */           if (map.isEmpty())
/*  168:     */           {
/*  169: 191 */             mn = DateHelper.getDayOfWeek(DateHelper.parseDate(this.currentDay));
/*  170: 192 */             if ((mn == 1) || (mn == 7))
/*  171:     */             {
/*  172: 193 */               map.put(hid + "_kssj", bz_Stime);
/*  173: 194 */               map.put(hid + "_jssj", bz_Etime);
/*  174:     */             }
/*  175:     */           }
/*  176: 197 */           if ((map != null) && (!map.isEmpty()))
/*  177:     */           {
/*  178: 198 */             String jrlx = Util.null2String((String)map.get(hid + "_jrlx"));
/*  179: 199 */             if ("8".equals(jrlx))
/*  180:     */             {
/*  181: 200 */               bz_Stime = Util.null2String((String)map.get(hid + "_kssj"));
/*  182: 201 */               bz_Etime = Util.null2String((String)map.get(hid + "_jssj"));
/*  183: 203 */               if (("1".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))) && 
/*  184: 204 */                 (getMinutesBetween("09:30", bz_Stime) >= 0.0F)) {
/*  185: 207 */                 bz_Stime = "09:30";
/*  186:     */               }
/*  187: 211 */               if ("7".equals(this.cus_Map.get(hid + "_COM")))
/*  188:     */               {
/*  189: 212 */                 bz_Stime = "09:00";
/*  190: 213 */                 bz_Etime = "18:00";
/*  191:     */               }
/*  192:     */             }
/*  193: 216 */             else if ("9".equals(jrlx))
/*  194:     */             {
/*  195: 217 */               tzxxks = Util.null2String((String)map.get(hid + "_kssj"));
/*  196: 218 */               tzxxjs = Util.null2String((String)map.get(hid + "_jssj"));
/*  197:     */             }
/*  198:     */             else
/*  199:     */             {
/*  200: 220 */               tzxxks = Util.null2String((String)map.get(hid + "_kssj"));
/*  201: 221 */               tzxxjs = Util.null2String((String)map.get(hid + "_jssj"));
/*  202:     */             }
/*  203:     */           }
/*  204: 226 */           String scdksj = Util.null2String((String)this.DkMap.get(workcode + "_S"));
/*  205: 227 */           String mcdksj = Util.null2String((String)this.DkMap.get(workcode + "_E"));
/*  206: 228 */           String exists = Util.null2String((String)this.mxhz_Map.get(hid));
/*  207:     */           
/*  208: 230 */           String wckssj = Util.null2String((String)this.waiChu_Map.get(hid + "_WCKS"));
/*  209: 231 */           String wcjssj = Util.null2String((String)this.waiChu_Map.get(hid + "_WCJS"));
/*  210: 232 */           BigDecimal wcsj = new BigDecimal((String)this.waiChu_Map.get(hid + "_WCSJ"));
/*  211:     */           
/*  212:     */ 
/*  213: 235 */           String cckssj = Util.null2String((String)this.chuChai_Map.get(hid + "_CCKS"));
/*  214: 236 */           String ccjssj = Util.null2String((String)this.chuChai_Map.get(hid + "_CCJS"));
/*  215: 237 */           BigDecimal ccsj = new BigDecimal((String)this.chuChai_Map.get(hid + "_CCSJ"));
/*  216:     */           
/*  217: 239 */           List<String> adk = new ArrayList();
/*  218: 240 */           if (!"".equals(wckssj)) {
/*  219: 241 */             adk.add(wckssj);
/*  220:     */           }
/*  221: 243 */           if (!"".equals(cckssj)) {
/*  222: 244 */             adk.add(cckssj);
/*  223:     */           }
/*  224: 246 */           if (!"".equals(scdksj)) {
/*  225: 247 */             adk.add(scdksj);
/*  226:     */           }
/*  227: 250 */           if (adk.size() > 0) {
/*  228: 251 */             scdksj = sortTime(adk, "ASC");
/*  229:     */           }
/*  230: 253 */           List<String> adkmc = new ArrayList();
/*  231: 254 */           if (!"".equals(wcjssj)) {
/*  232: 255 */             adkmc.add(wcjssj);
/*  233:     */           }
/*  234: 257 */           if (!"".equals(mcdksj)) {
/*  235: 258 */             adkmc.add(mcdksj);
/*  236:     */           }
/*  237: 260 */           if (!"".equals(ccjssj)) {
/*  238: 261 */             adkmc.add(ccjssj);
/*  239:     */           }
/*  240: 264 */           if (adkmc.size() > 0) {
/*  241: 265 */             mcdksj = sortTime(adkmc, "DESC");
/*  242:     */           }
/*  243: 268 */           BigDecimal jbgs = new BigDecimal(Util.null2s(
/*  244: 269 */             (String)this.jiaBan_Map.get(hid + "_JBGS"), "0"));
/*  245:     */           
/*  246: 271 */           BigDecimal jbxs = new BigDecimal(Util.null2s(
/*  247: 272 */             (String)this.jiaBan_Map.get(hid + "_JBXS"), "0"));
/*  248:     */           
/*  249:     */ 
/*  250:     */ 
/*  251:     */ 
/*  252: 277 */           String yclx = Util.null2String((String)this.Map.get(hid + "_YCLX"));
/*  253: 278 */           String time1 = Util.null2String((String)this.Map.get(hid + "_time1"));
/*  254: 279 */           String time2 = Util.null2String((String)this.Map.get(hid + "_time2"));
/*  255: 280 */           String time3 = Util.null2String((String)this.Map.get(hid + "_time3"));
/*  256: 281 */           String time4 = Util.null2String((String)this.Map.get(hid + "_time4"));
/*  257:     */           
/*  258:     */ 
/*  259: 284 */           int wdk = 0;
/*  260: 286 */           if ((!"2".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))) && 
/*  261: 287 */             (!"3".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT"))))) {
/*  262: 290 */             if ("0".equals(yclx))
/*  263:     */             {
/*  264: 291 */               if (!"".equals(time1)) {
/*  265: 292 */                 wdk++;
/*  266:     */               }
/*  267: 294 */               if (!"".equals(time2)) {
/*  268: 295 */                 wdk++;
/*  269:     */               }
/*  270: 297 */               if (!"".equals(time3)) {
/*  271: 298 */                 wdk++;
/*  272:     */               }
/*  273: 300 */               if (!"".equals(time4)) {
/*  274: 301 */                 wdk++;
/*  275:     */               }
/*  276:     */             }
/*  277:     */           }
/*  278: 307 */           String qjlx = (String)this.qingJia_Map.get(hid + "_QJLX");
/*  279: 308 */           String qjkssj = Util.null2String((String)this.qingJia_Map.get(hid + "_KS"));
/*  280: 309 */           String qjjssj = Util.null2String((String)this.qingJia_Map.get(hid + "_JS"));
/*  281: 310 */           String qjgs = Util.null2s((String)this.qingJia_Map.get(hid + "_QJGS"), "0");
/*  282: 311 */           BigDecimal qjgs_no = new BigDecimal(qjgs);
/*  283: 312 */           String sj = Util.null2s((String)this.qingJia_Map.get(hid + "_0"), "0");
/*  284: 313 */           String bj = Util.null2s((String)this.qingJia_Map.get(hid + "_1"), "0");
/*  285: 314 */           String nxj = Util.null2s((String)this.qingJia_Map.get(hid + "_2"), "0");
/*  286: 315 */           String txj = Util.null2s((String)this.qingJia_Map.get(hid + "_3"), "0");
/*  287: 316 */           String hj = Util.null2s((String)this.qingJia_Map.get(hid + "_4"), "0");
/*  288: 317 */           String sangj = Util.null2s((String)this.qingJia_Map.get(hid + "_5"), "0");
/*  289: 318 */           String cj = Util.null2s((String)this.qingJia_Map.get(hid + "_6"), "0");
/*  290:     */           
/*  291: 320 */           String cjj = Util.null2s((String)this.qingJia_Map.get(hid + "_7"), "0");
/*  292: 321 */           String lcj = Util.null2s((String)this.qingJia_Map.get(hid + "_8"), "0");
/*  293: 322 */           String brj = Util.null2s((String)this.qingJia_Map.get(hid + "_9"), "0");
/*  294: 323 */           String jyj = Util.null2s((String)this.qingJia_Map.get(hid + "_10"), "0");
/*  295:     */           
/*  296: 325 */           String jsj = Util.null2s((String)this.qingJia_Map.get(hid + "_11"), "0");
/*  297:     */           
/*  298: 327 */           String grgs = Util.null2s((String)this.qingJia_Map.get(hid + "_12"), "0");
/*  299:     */           
/*  300: 329 */           String trgs = Util.null2s((String)this.qingJia_Map.get(hid + "_13"), "0");
/*  301:     */           
/*  302: 331 */           String pcj = Util.null2s((String)this.qingJia_Map.get(hid + "_14"), "0");
/*  303:     */           
/*  304:     */ 
/*  305:     */ 
/*  306: 335 */           float kg = 0.0F;
/*  307: 336 */           String xm = hid;
/*  308: 337 */           String kssj = "";
/*  309: 338 */           String jssj = "";
/*  310:     */           
/*  311: 340 */           float cdsj = 0.0F;
/*  312: 341 */           float ztsj = 0.0F;
/*  313: 342 */           float sjsjwc = 0.0F;
/*  314: 344 */           if ((!"2".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))) && 
/*  315: 345 */             (!"3".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))))
/*  316:     */           {
/*  317: 348 */             List<String> arr2 = new ArrayList();
/*  318: 349 */             if (!"".equals(wckssj)) {
/*  319: 350 */               arr2.add(wckssj);
/*  320:     */             }
/*  321: 352 */             if (!"".equals(qjkssj)) {
/*  322: 353 */               arr2.add(qjkssj);
/*  323:     */             }
/*  324: 355 */             if (!"".equals(cckssj)) {
/*  325: 356 */               arr2.add(cckssj);
/*  326:     */             }
/*  327: 358 */             if (!"".equals(tzxxks)) {
/*  328: 359 */               arr2.add(tzxxks);
/*  329:     */             }
/*  330: 362 */             if (arr2.size() > 0) {
/*  331: 363 */               kssj = sortTime(arr2, "ASC");
/*  332:     */             }
/*  333: 366 */             arr2.clear();
/*  334: 367 */             if (!"".equals(wcjssj)) {
/*  335: 368 */               arr2.add(wcjssj);
/*  336:     */             }
/*  337: 370 */             if (!"".equals(qjjssj)) {
/*  338: 371 */               arr2.add(qjjssj);
/*  339:     */             }
/*  340: 373 */             if (!"".equals(ccjssj)) {
/*  341: 374 */               arr2.add(ccjssj);
/*  342:     */             }
/*  343: 376 */             if (!"".equals(tzxxjs)) {
/*  344: 377 */               arr2.add(tzxxjs);
/*  345:     */             }
/*  346: 380 */             if (arr2.size() > 0) {
/*  347: 381 */               jssj = sortTime(arr2, "DESC");
/*  348:     */             }
/*  349: 384 */             if ((!"".equals(scdksj)) && ("".equals(mcdksj)))
/*  350:     */             {
/*  351: 387 */               if (!this.Map.isEmpty())
/*  352:     */               {
/*  353: 388 */                 if (("0".equals(yclx)) || ("1".equals(yclx)))
/*  354:     */                 {
/*  355: 389 */                   List<String> kq1 = new ArrayList();
/*  356: 390 */                   if (!"".equals(kssj)) {
/*  357: 391 */                     kq1.add(kssj);
/*  358:     */                   }
/*  359: 393 */                   if (!"".equals(time1)) {
/*  360: 394 */                     kq1.add(time1);
/*  361:     */                   }
/*  362: 396 */                   if (!"".equals(time2)) {
/*  363: 397 */                     kq1.add(time2);
/*  364:     */                   }
/*  365: 399 */                   if (!"".equals(scdksj)) {
/*  366: 400 */                     kq1.add(scdksj);
/*  367:     */                   }
/*  368: 402 */                   String kssj12 = sortTime(kq1, "ASC");
/*  369: 403 */                   String jssj12 = sortTime(kq1, "DESC");
/*  370: 404 */                   cdsj = getMinutesBetween(kssj12, bz_Stime);
/*  371: 405 */                   ztsj = getMinutesBetween(bz_Etime, jssj12);
/*  372:     */                 }
/*  373: 418 */                 else if (!"1".equals(yclx))
/*  374:     */                 {
/*  375: 420 */                   if ("2".equals(yclx))
/*  376:     */                   {
/*  377: 421 */                     float l = getMinutesBetween(time1, scdksj);
/*  378: 422 */                     float ll = getMinutesBetween(time2, scdksj);
/*  379: 423 */                     sjsjwc = getMinutesBetween(time2, time1);
/*  380: 424 */                     if ((l <= 0.0F) && (ll <= 0.0F))
/*  381:     */                     {
/*  382: 426 */                       cdsj = getMinutesBetween(time1, bz_Stime);
/*  383: 427 */                       ztsj = getMinutesBetween(scdksj, time2);
/*  384:     */                     }
/*  385: 428 */                     else if ((l >= 0.0F) && (ll >= 0.0F))
/*  386:     */                     {
/*  387: 430 */                       cdsj = getMinutesBetween(scdksj, bz_Stime);
/*  388: 431 */                       ztsj = getMinutesBetween(bz_Etime, time2);
/*  389:     */                     }
/*  390:     */                   }
/*  391:     */                 }
/*  392:     */               }
/*  393: 435 */               else if (("".equals(kssj)) && ("".equals(jssj)))
/*  394:     */               {
/*  395: 436 */                 kg = 8.0F;
/*  396:     */               }
/*  397: 440 */               else if ((mn == 1) || (mn == 7))
/*  398:     */               {
/*  399: 442 */                 cdsj = 0.0F;
/*  400: 443 */                 ztsj = 0.0F;
/*  401:     */               }
/*  402:     */               else
/*  403:     */               {
/*  404: 445 */                 List<String> aqq = new ArrayList();
/*  405: 446 */                 if (!"".equals(scdksj)) {
/*  406: 447 */                   aqq.add(scdksj);
/*  407:     */                 }
/*  408: 449 */                 if (!"".equals(kssj)) {
/*  409: 450 */                   aqq.add(kssj);
/*  410:     */                 }
/*  411: 452 */                 if (!"".equals(jssj)) {
/*  412: 453 */                   aqq.add(jssj);
/*  413:     */                 }
/*  414: 455 */                 String dkkssj = sortTime(aqq, "ASC");
/*  415: 456 */                 String dkjssj = sortTime(aqq, "DESC");
/*  416:     */                 
/*  417: 458 */                 cdsj = getMinutesBetween(dkkssj, bz_Stime);
/*  418: 459 */                 ztsj = getMinutesBetween(bz_Etime, dkjssj);
/*  419:     */               }
/*  420:     */             }
/*  421: 464 */             else if ((mcdksj.equals("")) && ("".equals(scdksj)))
/*  422:     */             {
/*  423: 466 */               if ((!this.Map.isEmpty()) && (!"".equals(time1)) && 
/*  424: 467 */                 (!"".equals(time2)))
/*  425:     */               {
/*  426: 468 */                 if (("0".equals(yclx)) || ("1".equals(yclx)))
/*  427:     */                 {
/*  428: 469 */                   List<String> bqq = new ArrayList();
/*  429: 470 */                   if (!"".equals(time1)) {
/*  430: 471 */                     bqq.add(time1);
/*  431:     */                   }
/*  432: 473 */                   if (!"".equals(time2)) {
/*  433: 474 */                     bqq.add(time2);
/*  434:     */                   }
/*  435: 476 */                   if (!"".equals(kssj)) {
/*  436: 477 */                     bqq.add(kssj);
/*  437:     */                   }
/*  438: 479 */                   if (!"".equals(jssj)) {
/*  439: 480 */                     bqq.add(jssj);
/*  440:     */                   }
/*  441: 482 */                   String dkkssj = sortTime(bqq, "ASC");
/*  442: 483 */                   String dkjssj = sortTime(bqq, "DESC");
/*  443:     */                   
/*  444: 485 */                   cdsj = getMinutesBetween(dkkssj, bz_Stime);
/*  445: 486 */                   ztsj = getMinutesBetween(bz_Etime, dkjssj);
/*  446:     */                 }
/*  447: 499 */                 else if ("2".equals(yclx))
/*  448:     */                 {
/*  449: 500 */                   float l = getMinutesBetween(time1, time2);
/*  450: 501 */                   sjsjwc = getMinutesBetween(time2, time1);
/*  451: 502 */                   if (l < 0.0F)
/*  452:     */                   {
/*  453: 504 */                     cdsj = getMinutesBetween(time1, bz_Stime);
/*  454: 505 */                     ztsj = getMinutesBetween(bz_Etime, time2);
/*  455:     */                   }
/*  456:     */                 }
/*  457:     */               }
/*  458: 508 */               else if (this.Map.isEmpty()) {
/*  459: 510 */                 if (("".equals(kssj)) && ("".equals(jssj)))
/*  460:     */                 {
/*  461: 511 */                   kg = 8.0F;
/*  462:     */                 }
/*  463: 513 */                 else if ((!"".equalsIgnoreCase(tzxxks)) && 
/*  464: 514 */                   (!"".equalsIgnoreCase(tzxxjs)))
/*  465:     */                 {
/*  466: 516 */                   cdsj = 0.0F;
/*  467: 517 */                   ztsj = 0.0F;
/*  468:     */                 }
/*  469:     */                 else
/*  470:     */                 {
/*  471: 520 */                   cdsj = getMinutesBetween(kssj, bz_Stime);
/*  472: 521 */                   ztsj = getMinutesBetween(bz_Etime, jssj);
/*  473:     */                 }
/*  474:     */               }
/*  475:     */             }
/*  476: 525 */             else if ((!"".equals(scdksj)) && (!"".equals(mcdksj)))
/*  477:     */             {
/*  478: 527 */               if (!this.Map.isEmpty())
/*  479:     */               {
/*  480: 528 */                 if (!"0".equals(yclx)) {
/*  481: 530 */                   if (!"1".equals(yclx)) {
/*  482: 532 */                     "2".equals(yclx);
/*  483:     */                   }
/*  484:     */                 }
/*  485:     */               }
/*  486: 535 */               else if (this.Map.isEmpty()) {
/*  487: 536 */                 if (("".equals(kssj)) && ("".equals(jssj)))
/*  488:     */                 {
/*  489: 537 */                   cdsj = getMinutesBetween(scdksj, bz_Stime);
/*  490: 538 */                   ztsj = getMinutesBetween(bz_Etime, mcdksj);
/*  491:     */                 }
/*  492:     */                 else
/*  493:     */                 {
/*  494: 541 */                   if (getMinutesBetween(kssj, scdksj) > 0.0F) {
/*  495: 542 */                     cdsj = getMinutesBetween(scdksj, bz_Stime);
/*  496:     */                   } else {
/*  497: 544 */                     cdsj = getMinutesBetween(kssj, bz_Stime);
/*  498:     */                   }
/*  499: 547 */                   if (getMinutesBetween(jssj, mcdksj) > 0.0F) {
/*  500: 548 */                     ztsj = getMinutesBetween(bz_Etime, jssj);
/*  501:     */                   } else {
/*  502: 550 */                     ztsj = getMinutesBetween(bz_Etime, mcdksj);
/*  503:     */                   }
/*  504:     */                 }
/*  505:     */               }
/*  506:     */             }
/*  507: 554 */             else if ("".equals(scdksj)) {
/*  508: 554 */               "".equals(mcdksj);
/*  509:     */             }
/*  510:     */           }
/*  511: 562 */           float cdtemp = 0.0F;
/*  512: 563 */           float zttemp = 0.0F;
/*  513: 564 */           float ssj = 0.0F;
/*  514: 566 */           if ((!"2".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))) && 
/*  515: 567 */             (!"3".equals(Util.null2String((String)this.cus_Map.get(hid + "_KQT")))))
/*  516:     */           {
/*  517: 571 */             if (cdsj > amMin) {
/*  518: 572 */               if (cdsj - amMin >= restTime) {
/*  519: 573 */                 cdtemp = cdsj - restTime;
/*  520: 574 */               } else if (cdsj - amMin < restTime) {
/*  521: 575 */                 cdtemp = amMin;
/*  522:     */               }
/*  523:     */             }
/*  524: 578 */             if (ztsj > pmMin) {
/*  525: 579 */               if (ztsj - pmMin >= restTime) {
/*  526: 580 */                 zttemp = ztsj - restTime;
/*  527: 581 */               } else if (ztsj - pmMin > restTime) {
/*  528: 582 */                 zttemp = pmMin;
/*  529:     */               }
/*  530:     */             }
/*  531: 587 */             if (kg != 8.0F)
/*  532:     */             {
/*  533: 588 */               if ((cdsj > 60.0F) && (ztsj < 60.0F))
/*  534:     */               {
/*  535: 589 */                 kg = 
/*  536: 590 */                   new BigDecimal(cdsj).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  537: 591 */                 if (cdtemp > 0.0F) {
/*  538: 592 */                   kg = 
/*  539:     */                   
/*  540: 594 */                     new BigDecimal(cdtemp).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  541:     */                 }
/*  542: 596 */                 cdsj = 0.0F;
/*  543:     */               }
/*  544: 598 */               if ((ztsj > 60.0F) && (cdsj < 60.0F))
/*  545:     */               {
/*  546: 599 */                 kg = 
/*  547: 600 */                   new BigDecimal(ztsj).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  548: 601 */                 if (zttemp > 0.0F) {
/*  549: 602 */                   kg = 
/*  550:     */                   
/*  551: 604 */                     new BigDecimal(zttemp).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  552:     */                 }
/*  553: 606 */                 ztsj = 0.0F;
/*  554:     */               }
/*  555: 608 */               if ((ztsj > 60.0F) && (cdsj > 60.0F))
/*  556:     */               {
/*  557: 609 */                 kg = 
/*  558: 610 */                   new BigDecimal(ztsj).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  559:     */                 
/*  560:     */ 
/*  561:     */ 
/*  562: 614 */                 kg = kg + new BigDecimal(cdsj).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  563: 615 */                 if ((cdtemp > 0.0F) && (zttemp > 0.0F))
/*  564:     */                 {
/*  565: 616 */                   kg = 
/*  566:     */                   
/*  567: 618 */                     new BigDecimal(cdtemp).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  568:     */                   
/*  569:     */ 
/*  570:     */ 
/*  571: 622 */                   kg = kg + new BigDecimal(zttemp).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  572:     */                 }
/*  573: 624 */                 if ((cdtemp > 0.0F) && (zttemp <= 0.0F))
/*  574:     */                 {
/*  575: 625 */                   kg = 
/*  576:     */                   
/*  577: 627 */                     new BigDecimal(cdtemp).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  578:     */                   
/*  579:     */ 
/*  580:     */ 
/*  581: 631 */                   kg = kg + new BigDecimal(ztsj).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  582:     */                 }
/*  583: 633 */                 if ((cdtemp <= 0.0F) && (zttemp > 0.0F))
/*  584:     */                 {
/*  585: 634 */                   kg = 
/*  586:     */                   
/*  587: 636 */                     new BigDecimal(cdsj).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  588:     */                   
/*  589:     */ 
/*  590:     */ 
/*  591: 640 */                   kg = kg + new BigDecimal(zttemp).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP).floatValue();
/*  592:     */                 }
/*  593: 642 */                 ztsj = 0.0F;
/*  594: 643 */                 cdsj = 0.0F;
/*  595:     */               }
/*  596:     */             }
/*  597: 648 */             if (cdsj < 0.0F) {
/*  598: 649 */               cdsj = 0.0F;
/*  599:     */             }
/*  600: 651 */             if (ztsj < 0.0F) {
/*  601: 652 */               ztsj = 0.0F;
/*  602:     */             }
/*  603:     */           }
/*  604: 656 */           String kqlb = Util.null2String((String)this.cus_Map.get(hid + "_KQT"));
/*  605: 657 */           String ssgs = Util.null2String((String)this.cus_Map.get(hid + "_COM"));
/*  606:     */           
/*  607: 659 */           String yjbm = Util.null2String((String)this.cus_Map.get(hid + "_DEPT1"));
/*  608: 660 */           String ejbm = Util.null2String((String)this.cus_Map.get(hid + "_DEPT2"));
/*  609: 661 */           String sjbm = Util.null2String((String)this.cus_Map.get(hid + "_DEPT3"));
/*  610: 662 */           String zt = Util.null2String((String)this.cus_Map.get(hid + "_RZZT"));
/*  611: 664 */           if ("0".equals(zt)) {
/*  612: 665 */             zt = "在职";
/*  613:     */           } else {
/*  614: 667 */             zt = "离职";
/*  615:     */           }
/*  616: 670 */           String gw = Util.null2String(hrm.getString("jobtitle"));
/*  617:     */           
/*  618: 672 */           ssj = sjsjwc + new BigDecimal(sj).floatValue();
/*  619: 673 */           sj = ssj;
/*  620: 676 */           if (cdsj < 1.0F) {
/*  621: 677 */             cdsj = 0.0F;
/*  622:     */           }
/*  623: 682 */           excSql = 
/*  624:     */           
/*  625:     */ 
/*  626:     */ 
/*  627:     */ 
/*  628:     */ 
/*  629:     */ 
/*  630:     */ 
/*  631:     */ 
/*  632:     */ 
/*  633:     */ 
/*  634:     */ 
/*  635:     */ 
/*  636:     */ 
/*  637:     */ 
/*  638:     */ 
/*  639:     */ 
/*  640:     */ 
/*  641:     */ 
/*  642:     */ 
/*  643:     */ 
/*  644:     */ 
/*  645:     */ 
/*  646:     */ 
/*  647:     */ 
/*  648:     */ 
/*  649:     */ 
/*  650:     */ 
/*  651:     */ 
/*  652:     */ 
/*  653:     */ 
/*  654:     */ 
/*  655:     */ 
/*  656:     */ 
/*  657:     */ 
/*  658:     */ 
/*  659:     */ 
/*  660:     */ 
/*  661:     */ 
/*  662:     */ 
/*  663:     */ 
/*  664:     */ 
/*  665:     */ 
/*  666:     */ 
/*  667:     */ 
/*  668:     */ 
/*  669:     */ 
/*  670:     */ 
/*  671:     */ 
/*  672:     */ 
/*  673:     */ 
/*  674:     */ 
/*  675:     */ 
/*  676:     */ 
/*  677:     */ 
/*  678:     */ 
/*  679:     */ 
/*  680:     */ 
/*  681:     */ 
/*  682:     */ 
/*  683:     */ 
/*  684:     */ 
/*  685:     */ 
/*  686:     */ 
/*  687:     */ 
/*  688:     */ 
/*  689:     */ 
/*  690:     */ 
/*  691:     */ 
/*  692: 751 */             "insert into uf_kqhzmx(kqrq,trgs,brj,gh,wdk,kg,xm,cd,kqlb_n,zaot,ssgs,yjbm_n,ejbm_n,zt,gw,scdksj,mcdksj,jbgs,sj,bj,nxj,txj,hj,sangj,cj,cjj,lcj,jyj,jsj,grgs,formmodeid,modedatacreater,modedatacreatedate,modedatacreatetime) values ('" + this.currentDay + "'," + "'" + trgs + "','" + brj + "','" + workcode + "','" + wdk + "','" + kg + "','" + xm + "','" + cdsj + "','" + kqlb + "','" + ztsj + "','" + ssgs + "','" + yjbm + "'," + "'" + ejbm + "','" + zt + "','" + gw + "','" + scdksj + "','" + mcdksj + "','" + jbgs + "','" + sj + "','" + bj + "','" + nxj + "','" + txj + "'," + "'" + hj + "','" + sangj + "','" + cj + "','" + cjj + "','" + lcj + "','" + jyj + "','" + jsj + "','" + grgs + "'," + "'60','1','" + DateHelper.getCurrentDate() + "','" + this.currentTime + "')";
/*  693:     */           
/*  694:     */ 
/*  695:     */ 
/*  696:     */ 
/*  697:     */ 
/*  698:     */ 
/*  699:     */ 
/*  700:     */ 
/*  701:     */ 
/*  702:     */ 
/*  703:     */ 
/*  704:     */ 
/*  705:     */ 
/*  706:     */ 
/*  707:     */ 
/*  708:     */ 
/*  709: 768 */           rs.executeSql(excSql);
/*  710:     */         }
/*  711:     */       }
/*  712:     */     }
/*  713:     */   }
/*  714:     */   
/*  715:     */   private void init()
/*  716:     */   {
/*  717: 774 */     this.DkMap.clear();
/*  718: 775 */     getydData();
/*  719: 776 */     getKqjData();
/*  720:     */     
/*  721:     */ 
/*  722: 779 */     getJiaBan();
/*  723:     */     
/*  724:     */ 
/*  725: 782 */     getKqhzmx();
/*  726:     */   }
/*  727:     */   
/*  728:     */   private void getKqyc(String hid)
/*  729:     */   {
/*  730: 789 */     this.Map.clear();
/*  731: 790 */     String sql = "select proposer,ycrq,yclx,time1,time2,time3,time4 from formtable_main_106 where ycrq = '" + 
/*  732: 791 */       this.currentDay + "' and proposer ='" + hid + "'";
/*  733: 792 */     this.rsA.executeSql(sql);
/*  734: 793 */     while (this.rsA.next())
/*  735:     */     {
/*  736: 794 */       String yclx = Util.null2String(this.rsA.getString("yclx"));
/*  737: 795 */       String time1 = Util.null2String(this.rsA.getString("time1"));
/*  738: 796 */       String time2 = Util.null2String(this.rsA.getString("time2"));
/*  739: 797 */       String time3 = Util.null2String(this.rsA.getString("time3"));
/*  740: 798 */       String time4 = Util.null2String(this.rsA.getString("time4"));
/*  741: 799 */       this.Map.put(hid + "_YCLX", yclx);
/*  742: 800 */       this.Map.put(hid + "_time1", time1);
/*  743: 801 */       this.Map.put(hid + "_time2", time2);
/*  744: 802 */       this.Map.put(hid + "_time3", time3);
/*  745: 803 */       this.Map.put(hid + "_time4", time4);
/*  746:     */     }
/*  747:     */   }
/*  748:     */   
/*  749:     */   private void getKqhzmx()
/*  750:     */   {
/*  751: 811 */     this.mxhz_Map.clear();
/*  752: 812 */     String sql = "select xm,kqrq,gh,scdksj,mcdksj from uf_kqhzmx where kqrq = '" + 
/*  753: 813 */       this.currentDay + "'";
/*  754: 814 */     this.rsA.executeSql(sql);
/*  755: 815 */     while (this.rsA.next())
/*  756:     */     {
/*  757: 816 */       String code = Util.null2String(this.rsA.getString("gh"));
/*  758: 817 */       String id = Util.null2String(this.rsA.getString("xm"));
/*  759: 818 */       String kqrq = Util.null2String(this.rsA.getString("kqrq"));
/*  760: 819 */       String hdkssj = Util.null2String(this.rsA.getString("hdkssj"));
/*  761: 820 */       String hdjssj = Util.null2String(this.rsA.getString("hdjssj"));
/*  762: 821 */       this.mxhz_Map.put(id, kqrq);
/*  763:     */     }
/*  764:     */   }
/*  765:     */   
/*  766:     */   private void getWaiChu(String hid)
/*  767:     */   {
/*  768: 830 */     this.waiChu_Map.clear();
/*  769: 831 */     String sql = "select hrmno,wcrq,xq,hdkssj,hdjssj from uf__wc_temp where hrmid='" + 
/*  770: 832 */       hid + "' and wcrq= '" + this.currentDay + "'";
/*  771: 833 */     this.rsA.executeSql(sql);
/*  772: 834 */     BigDecimal wcsj = new BigDecimal("0");
/*  773: 835 */     String tempS = "";
/*  774: 836 */     String tempE = "";
/*  775: 837 */     while (this.rsA.next())
/*  776:     */     {
/*  777: 838 */       String code = Util.null2String(this.rsA.getString("hrmno"));
/*  778:     */       
/*  779: 840 */       String hdkssj = Util.null2String(this.rsA.getString("hdkssj"));
/*  780: 841 */       String hdjssj = Util.null2String(this.rsA.getString("hdjssj"));
/*  781: 842 */       String hdgs = Util.null2s(this.rsA.getString("hdgs"), "0");
/*  782: 843 */       wcsj = wcsj.add(new BigDecimal(hdgs));
/*  783: 844 */       if ("".equals(tempS))
/*  784:     */       {
/*  785: 845 */         tempS = hdkssj;
/*  786: 846 */         this.waiChu_Map.put(hid + "_WCKS", tempS);
/*  787:     */       }
/*  788: 848 */       else if (getMinutesBetween(tempS, hdkssj) > 0.0F)
/*  789:     */       {
/*  790: 850 */         tempS = hdkssj;
/*  791: 851 */         this.waiChu_Map.put(hid + "_WCKS", tempS);
/*  792:     */       }
/*  793: 854 */       if ("".equals(tempE))
/*  794:     */       {
/*  795: 855 */         tempE = hdjssj;
/*  796: 856 */         this.waiChu_Map.put(hid + "_WCJS", tempE);
/*  797:     */       }
/*  798: 858 */       else if (getMinutesBetween(tempE, hdjssj) < 0.0F)
/*  799:     */       {
/*  800: 860 */         tempE = hdjssj;
/*  801: 861 */         this.waiChu_Map.put(hid + "_WCJS", tempE);
/*  802:     */       }
/*  803:     */     }
/*  804: 866 */     this.waiChu_Map.put(hid + "_WCSJ", wcsj);
/*  805:     */   }
/*  806:     */   
/*  807:     */   private void getChuChai(String hid)
/*  808:     */   {
/*  809: 873 */     this.chuChai_Map.clear();
/*  810: 874 */     String sql = "select hrmno,ccrq,xq,hdkssj,hdjssj,hdgs from uf__cc_temp where ccrq = '" + 
/*  811: 875 */       this.currentDay + "' and hrmid = '" + hid + "'";
/*  812: 876 */     this.rsA.executeSql(sql);
/*  813: 877 */     BigDecimal ccsj = new BigDecimal("0");
/*  814: 878 */     String tempS = "";
/*  815: 879 */     String tempE = "";
/*  816: 880 */     while (this.rsA.next())
/*  817:     */     {
/*  818: 881 */       String code = Util.null2String(this.rsA.getString("hrmno"));
/*  819: 882 */       String hdkssj = Util.null2String(this.rsA.getString("hdkssj"));
/*  820: 883 */       String hdjssj = Util.null2String(this.rsA.getString("hdjssj"));
/*  821: 884 */       String hdgs = Util.null2s(this.rsA.getString("hdgs"), "0");
/*  822: 885 */       ccsj = ccsj.add(new BigDecimal(hdgs));
/*  823: 886 */       if ("".equals(tempS))
/*  824:     */       {
/*  825: 887 */         tempS = hdkssj;
/*  826: 888 */         this.chuChai_Map.put(hid + "_CCKS", tempS);
/*  827:     */       }
/*  828: 890 */       else if (getMinutesBetween(tempS, hdkssj) > 0.0F)
/*  829:     */       {
/*  830: 892 */         tempS = hdkssj;
/*  831: 893 */         this.chuChai_Map.put(hid + "_CCKS", tempS);
/*  832:     */       }
/*  833: 896 */       if ("".equals(tempE))
/*  834:     */       {
/*  835: 897 */         tempE = hdjssj;
/*  836: 898 */         this.chuChai_Map.put(hid + "_CCJS", tempE);
/*  837:     */       }
/*  838: 900 */       else if (getMinutesBetween(tempE, hdjssj) < 0.0F)
/*  839:     */       {
/*  840: 902 */         tempE = hdjssj;
/*  841: 903 */         this.chuChai_Map.put(hid + "_CCJS", tempE);
/*  842:     */       }
/*  843:     */     }
/*  844: 909 */     this.chuChai_Map.put(hid + "_CCSJ", ccsj);
/*  845:     */   }
/*  846:     */   
/*  847:     */   private void getJiaBan()
/*  848:     */   {
/*  849: 916 */     this.jiaBan_Map.clear();
/*  850: 917 */     String sql = "select hrmno,hrmid,jbrq,xq,hdgs,jbxs from uf__jb_temp where jbrq = '" + 
/*  851: 918 */       this.currentDay + "'";
/*  852: 919 */     this.rsA.executeSql(sql);
/*  853: 920 */     while (this.rsA.next())
/*  854:     */     {
/*  855: 921 */       String code = Util.null2String(this.rsA.getString("hrmno"));
/*  856: 922 */       String id = Util.null2String(this.rsA.getString("hrmid"));
/*  857: 923 */       String hdgs = Util.null2String(this.rsA.getString("hdgs"));
/*  858: 924 */       String jbxs = Util.null2String(this.rsA.getString("jbxs"));
/*  859: 925 */       this.jiaBan_Map.put(id + "_JBGS", hdgs);
/*  860: 926 */       this.jiaBan_Map.put(id + "_JBXS", jbxs);
/*  861:     */     }
/*  862:     */   }
/*  863:     */   
/*  864:     */   private void getQingjia(String hid)
/*  865:     */   {
/*  866: 935 */     this.qingJia_Map.clear();
/*  867: 936 */     String sql = "select hrmno,hrmid,qjrq,xq,hdkssj,hdjssj,qjlx,hdgs from uf__qj_temp where qjrq='" + 
/*  868: 937 */       this.currentDay + "' and hrmid ='" + hid + "'";
/*  869: 938 */     this.rsA.executeSql(sql);
/*  870: 939 */     BigDecimal qjgs = new BigDecimal("0");
/*  871: 940 */     String tempS = "";
/*  872: 941 */     String tempE = "";
/*  873: 942 */     while (this.rsA.next())
/*  874:     */     {
/*  875: 943 */       String code = Util.null2String(this.rsA.getString("hrmno"));
/*  876: 944 */       String id = Util.null2String(this.rsA.getString("hrmid"));
/*  877: 945 */       String hdkssj = Util.null2String(this.rsA.getString("hdkssj"));
/*  878: 946 */       String hdjssj = Util.null2String(this.rsA.getString("hdjssj"));
/*  879: 947 */       String qjlx = Util.null2String(this.rsA.getString("qjlx"));
/*  880: 948 */       String hdgs = Util.null2s(this.rsA.getString("hdgs"), "0");
/*  881: 949 */       qjgs = qjgs.add(new BigDecimal(hdgs));
/*  882: 950 */       if ("".equals(tempS))
/*  883:     */       {
/*  884: 951 */         tempS = hdkssj;
/*  885: 952 */         this.qingJia_Map.put(hid + "_KS", tempS);
/*  886:     */       }
/*  887: 954 */       else if (getMinutesBetween(tempS, hdkssj) > 0.0F)
/*  888:     */       {
/*  889: 956 */         tempS = hdkssj;
/*  890: 957 */         this.qingJia_Map.put(hid + "_KS", tempS);
/*  891:     */       }
/*  892: 960 */       if ("".equals(tempE))
/*  893:     */       {
/*  894: 961 */         tempE = hdjssj;
/*  895: 962 */         this.qingJia_Map.put(hid + "_JS", tempE);
/*  896:     */       }
/*  897: 964 */       else if (getMinutesBetween(tempE, hdjssj) < 0.0F)
/*  898:     */       {
/*  899: 966 */         tempE = hdjssj;
/*  900: 967 */         this.qingJia_Map.put(hid + "_JS", tempE);
/*  901:     */       }
/*  902: 971 */       this.qingJia_Map.put(hid + "_" + qjlx, hdgs);
/*  903:     */     }
/*  904: 973 */     this.qingJia_Map.put(hid + "_QJGS", qjgs);
/*  905:     */   }
/*  906:     */   
/*  907:     */   private void getHrmCusData()
/*  908:     */   {
/*  909: 981 */     String sql = "select Name,StartWorkTime,EndWorkTime,AmStartWorkTime,PmEndWorkTime,KaoQinType,Company,DeptOneName,DeptTwoName,DeptThreeName,InCompany, DeptOneNameText,DeptTwoNameText,DeptThreeNameText,JoinDate,LeaveDate from uf_hr_userinfo1";
/*  910:     */     
/*  911: 983 */     RecordSet cusRs = new RecordSet();
/*  912: 984 */     cusRs.executeSql(sql);
/*  913: 985 */     while (cusRs.next())
/*  914:     */     {
/*  915: 986 */       String id = Util.null2String(cusRs.getString("Name"));
/*  916: 987 */       String StartWorkTime = Util.null2String(cusRs
/*  917: 988 */         .getString("StartWorkTime"));
/*  918: 989 */       String EndWorkTime = Util.null2String(cusRs
/*  919: 990 */         .getString("EndWorkTime"));
/*  920: 991 */       String amE = Util.null2String(cusRs.getString("AmStartWorkTime"));
/*  921: 992 */       String pmS = Util.null2String(cusRs.getString("PmEndWorkTime"));
/*  922: 993 */       String KaoQinType = Util.null2String(cusRs.getString("KaoQinType"));
/*  923: 994 */       String Company = Util.null2String(cusRs.getString("Company"));
/*  924: 995 */       String DeptOneName = Util.null2String(cusRs
/*  925: 996 */         .getString("DeptOneNameText"));
/*  926: 997 */       String DeptTwoName = Util.null2String(cusRs
/*  927: 998 */         .getString("DeptTwoNameText"));
/*  928: 999 */       String DeptThreeName = Util.null2String(cusRs
/*  929:1000 */         .getString("DeptThreeNameText"));
/*  930:1001 */       String InCompany = Util.null2String(cusRs.getString("InCompany"));
/*  931:1002 */       String join = Util.null2String(cusRs.getString("JoinDate"));
/*  932:1003 */       String leave = Util.null2String(cusRs.getString("LeaveDate"));
/*  933:     */       
/*  934:1005 */       this.cus_Map.put(id + "_18", StartWorkTime);
/*  935:1006 */       this.cus_Map.put(id + "_19", EndWorkTime);
/*  936:1007 */       this.cus_Map.put(id + "_amE", amE);
/*  937:1008 */       this.cus_Map.put(id + "_pmS", pmS);
/*  938:1009 */       this.cus_Map.put(id + "_KQT", KaoQinType);
/*  939:1010 */       this.cus_Map.put(id + "_COM", Company);
/*  940:1011 */       this.cus_Map.put(id + "_DEPT1", DeptOneName);
/*  941:1012 */       this.cus_Map.put(id + "_DEPT2", DeptTwoName);
/*  942:1013 */       this.cus_Map.put(id + "_DEPT3", DeptThreeName);
/*  943:1014 */       this.cus_Map.put(id + "_RZZT", InCompany);
/*  944:1015 */       this.cus_Map.put(id + "_rzrq", join);
/*  945:1016 */       this.cus_Map.put(id + "_lzrq", leave);
/*  946:     */     }
/*  947:     */   }
/*  948:     */   
/*  949:     */   public void getydData()
/*  950:     */   {
/*  951:1027 */     RecordSet rsyd = new RecordSet();
/*  952:1028 */     String sql = "select (select workcode from hrmresource where id =t.userId) code,userId,signDate,signTime from hrmschedulesign  t where signDate = '" + 
/*  953:     */     
/*  954:     */ 
/*  955:1031 */       this.currentDay + 
/*  956:1032 */       "' order by userid,signTime asc";
/*  957:1033 */     rsyd.executeSql(sql);
/*  958:1034 */     String before_code = "";
/*  959:1035 */     while (rsyd.next())
/*  960:     */     {
/*  961:1036 */       String code = Util.null2String(rsyd.getString("code"));
/*  962:1037 */       String signTime = Util.null2String(rsyd.getString("signTime"));
/*  963:1038 */       if (before_code.equals(code)) {
/*  964:1039 */         this.DkMap.put(code + "_E", signTime);
/*  965:     */       } else {
/*  966:1041 */         this.DkMap.put(code + "_S", signTime);
/*  967:     */       }
/*  968:1043 */       before_code = code;
/*  969:     */     }
/*  970:     */   }
/*  971:     */   
/*  972:     */   public void getKqjData()
/*  973:     */   {
/*  974:1051 */     String sql = "select code,date,time from vwCheckTime where date = '" + 
/*  975:1052 */       this.currentDay + "' order by code,time asc";
/*  976:1053 */     RecordSet rskqj = new RecordSet();
/*  977:1054 */     rskqj.executeSql(sql, "kqj");
/*  978:1055 */     String before_code = "";
/*  979:1056 */     while (rskqj.next())
/*  980:     */     {
/*  981:1057 */       String code = Util.null2String(rskqj.getString("code"));
/*  982:1058 */       String Time = Util.null2String(rskqj.getString("time"));
/*  983:1059 */       if (before_code.equals(code))
/*  984:     */       {
/*  985:1060 */         String signTimen = Util.null2String((String)this.DkMap.get(code + "_E"));
/*  986:1061 */         if (Time.compareTo(signTimen) > 0) {
/*  987:1062 */           this.DkMap.put(code + "_E", Time);
/*  988:     */         }
/*  989:     */       }
/*  990:     */       else
/*  991:     */       {
/*  992:1065 */         String signTimen = Util.null2String((String)this.DkMap.get(code + "_S"));
/*  993:1066 */         if ((Time.compareTo(signTimen) < 0) || ("".equals(signTimen))) {
/*  994:1067 */           this.DkMap.put(code + "_S", Time);
/*  995:     */         }
/*  996:     */       }
/*  997:1070 */       before_code = code;
/*  998:     */     }
/*  999:     */   }
/* 1000:     */   
/* 1001:     */   public static float getMinutesBetween(String s1, String s2)
/* 1002:     */   {
/* 1003:1084 */     if ((!"".equals(s1)) && (s1.length() == 8)) {
/* 1004:1085 */       s1 = s1.substring(0, 5);
/* 1005:     */     }
/* 1006:1087 */     if ((!"".equals(s2)) && (s2.length() == 8)) {
/* 1007:1088 */       s2 = s2.substring(0, 5);
/* 1008:     */     }
/* 1009:1090 */     s1 = "2016-01-01 " + s1;
/* 1010:1091 */     s2 = "2016-01-01 " + s2;
/* 1011:1092 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 1012:     */     try
/* 1013:     */     {
/* 1014:1094 */       Date dt1 = sdf.parse(s1);
/* 1015:1095 */       Date dt2 = sdf.parse(s2);
/* 1016:1096 */       BigDecimal c = new BigDecimal(dt1.getTime() - dt2.getTime());
/* 1017:1097 */       BigDecimal re = c.divide(new BigDecimal(60000), 2, 
/* 1018:1098 */         RoundingMode.HALF_UP);
/* 1019:1099 */       return re.floatValue();
/* 1020:     */     }
/* 1021:     */     catch (Exception e) {}
/* 1022:1101 */     return 0.0F;
/* 1023:     */   }
/* 1024:     */   
/* 1025:     */   public static String sortTime(List<String> arr, String asc)
/* 1026:     */   {
/* 1027:1112 */     String endTime = "";
/* 1028:1113 */     for (int i = 0; i < arr.size() - 1; i++) {
/* 1029:1114 */       for (int j = 0; j < arr.size() - 1 - i; j++) {
/* 1030:1115 */         if (getMinutesBetween((String)arr.get(j), (String)arr.get(j + 1)) > 0.0F)
/* 1031:     */         {
/* 1032:1116 */           String temp = (String)arr.get(j);
/* 1033:     */           
/* 1034:     */ 
/* 1035:     */ 
/* 1036:1120 */           arr.set(j, (String)arr.get(j + 1));
/* 1037:1121 */           arr.set(j + 1, temp);
/* 1038:     */         }
/* 1039:     */       }
/* 1040:     */     }
/* 1041:1125 */     if ("ASC".equalsIgnoreCase(asc)) {
/* 1042:1126 */       endTime = (String)arr.get(0);
/* 1043:     */     } else {
/* 1044:1128 */       endTime = (String)arr.get(arr.size() - 1);
/* 1045:     */     }
/* 1046:1132 */     return endTime;
/* 1047:     */   }
/* 1048:     */ }


/* Location:           F:\oa_back\oacustom\custom_class\dh\interfaces\
 * Qualified Name:     weaver.dh.interfaces.toCollect
 * JD-Core Version:    0.7.0.1
 */