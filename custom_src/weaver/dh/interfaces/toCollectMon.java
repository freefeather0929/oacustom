/*   1:    */ package weaver.dh.interfaces;
/*   2:    */ 
/*   3:    */ import com.weaver.formmodel.util.DateHelper;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.math.BigDecimal;
/*   6:    */ import java.text.ParseException;
/*   7:    */ import java.text.SimpleDateFormat;
/*   8:    */ import java.util.Date;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.Map;
/*  11:    */ import weaver.conn.RecordSet;
/*  12:    */ import weaver.general.Util;
/*  13:    */ import weaver.interfaces.schedule.BaseCronJob;
/*  14:    */ 
/*  15:    */ public class toCollectMon
/*  16:    */   extends BaseCronJob
/*  17:    */ {
/*  18: 20 */   private String resourceid = "";
/*  19: 21 */   private String flag = "0";
/*  20: 23 */   private String currentDay = DateHelper.getCurrentDate();
/*  21: 24 */   private String currentTime = DateHelper.getCurrentTime();
/*  22: 25 */   private String toCollectMon = DateHelper.getCurrentMonth();
/*  23: 26 */   private Map<String, String> DkMap = new HashMap();
/*  24: 27 */   HashMap<String, String> cus_Map = new HashMap();
/*  25: 28 */   HashMap<String, String> hz_Map = new HashMap();
/*  26: 29 */   HashMap<String, String> Map = new HashMap();
/*  27: 30 */   HashMap<String, String> jiaBan_Map = new HashMap();
/*  28: 31 */   RecordSet rsA = new RecordSet();
/*  29: 32 */   toCollect co = new toCollect();
/*  30:    */   
/*  31:    */   public void getJump(String zt, String hrid, String dayB)
/*  32:    */   {
/*  33: 36 */     this.flag = "1";
/*  34: 37 */     String[] hid = { "0" };
/*  35: 38 */     if (!"".equals(Util.null2String(hrid))) {
/*  36: 39 */       hid = hrid.split(",");
/*  37:    */     }
/*  38: 41 */     for (int n = 0; n < hid.length; n++) {
/*  39: 42 */       if (!"".equals(Util.null2String(hid[n]))) {
/*  40: 43 */         this.resourceid = (this.resourceid + hid[n] + ",");
/*  41:    */       }
/*  42:    */     }
/*  43: 46 */     String getPeople = "select name from uf_hr_userinfo1 where Company = '" + zt + "'";
/*  44: 47 */     RecordSet r = new RecordSet();
/*  45: 48 */     r.executeSql(getPeople);
/*  46: 49 */     while (r.next())
/*  47:    */     {
/*  48: 50 */       String name = Util.null2String(r.getString("name"));
/*  49: 51 */       if (!"".equals(name)) {
/*  50: 52 */         this.resourceid = (this.resourceid + name + ",");
/*  51:    */       }
/*  52:    */     }
/*  53: 55 */     if (this.resourceid.endsWith(",")) {
/*  54: 56 */       this.resourceid = this.resourceid.substring(0, this.resourceid.length() - 1);
/*  55:    */     }
/*  56: 58 */     this.toCollectMon = dayB;
/*  57: 59 */     execute();
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void execute()
/*  61:    */   {
/*  62: 63 */     getData();
/*  63: 64 */     getHrmCusData();
/*  64: 65 */     RecordSet hrm = new RecordSet();
/*  65: 66 */     RecordSet rsG = new RecordSet();
/*  66: 67 */     RecordSet rs = new RecordSet();
/*  67:    */     
/*  68:    */ 
/*  69:    */ 
/*  70: 71 */     String sqlHrm = "select id,workcode,jobtitle from HrmResource where id in (" + this.resourceid + ")";
/*  71: 72 */     if ("0".equals(this.flag)) {
/*  72: 73 */       sqlHrm = "select id,workcode,jobtitle from HrmResource where status in (1,2,3,5)";
/*  73:    */     }
/*  74: 75 */     hrm.executeSql(sqlHrm);
/*  75: 76 */     while (hrm.next())
/*  76:    */     {
/*  77: 77 */       String hid = Util.null2String(hrm.getString("id"));
/*  78: 78 */       getJiaBan(hid);
/*  79: 79 */       String workcode = Util.null2String(hrm.getString("workcode"));
/*  80: 80 */       String exists = Util.null2String((String)this.hz_Map.get(hid));
/*  81: 81 */       String getSql = "select * from uf_kqhzmx where kqrq like '%" + this.toCollectMon + "%' and xm = '" + hid + "'";
/*  82: 82 */       rsG.executeSql(getSql);
/*  83: 83 */       String excSql = "";
/*  84:    */       
/*  85: 85 */       String trgs = "0";
/*  86: 86 */       String brj = "0";
/*  87: 87 */       String wdk = "0";
/*  88: 88 */       BigDecimal kg = new BigDecimal("0");
/*  89: 89 */       String xm = hid;
/*  90: 90 */       BigDecimal cdsj = new BigDecimal("0");
/*  91: 91 */       BigDecimal ztsj = new BigDecimal("0");
/*  92: 92 */       String kqlb = Util.null2String((String)this.cus_Map.get(hid + "_KQT"));
/*  93: 93 */       String ssgs = Util.null2String((String)this.cus_Map.get(hid + "_COM"));
/*  94: 94 */       String yjbm = Util.null2String((String)this.cus_Map.get(hid + "_DEPT1"));
/*  95: 95 */       String ejbm = Util.null2String((String)this.cus_Map.get(hid + "_DEPT2"));
/*  96: 96 */       String sjbm = Util.null2String((String)this.cus_Map.get(hid + "_DEPT3"));
/*  97: 97 */       String zt = Util.null2String((String)this.cus_Map.get(hid + "_RZZT"));
/*  98: 98 */       String join = Util.null2String((String)this.cus_Map.get(hid + "_join"));
/*  99: 99 */       String leave = Util.null2String((String)this.cus_Map.get(hid + "_leave"));
/* 100:100 */       String synx = Util.null2String((String)this.cus_Map.get(hid + "_synx"));
/* 101:101 */       String sytx = Util.null2String((String)this.cus_Map.get(hid + "_sytx"));
/* 102:102 */       BigDecimal jb_ztx = new BigDecimal(null2o((String)this.jiaBan_Map.get(hid + "_ztx")));
/* 103:103 */       BigDecimal jb_bztx = new BigDecimal(null2o((String)this.jiaBan_Map.get(hid + "_bztx")));
/* 104:104 */       String dstr = this.toCollectMon + "-01";
/* 105:105 */       String lastDate = getLastDateOfMon(dstr);
/* 106:106 */       float ycqts = (float)DateHelper.getDaysBetween(lastDate, dstr, true);
/* 107:107 */       if (DateHelper.isWorkDay(DateHelper.parseDate(dstr))) {
/* 108:108 */         ycqts += 1.0F;
/* 109:    */       }
/* 110:110 */       if (!"".equals(join))
/* 111:    */       {
/* 112:111 */         String JoinMonth = join.substring(0, 7);
/* 113:112 */         if (this.toCollectMon.equals(JoinMonth))
/* 114:    */         {
/* 115:113 */           ycqts = (float)DateHelper.getDaysBetween(lastDate, join, true);
/* 116:114 */           if (DateHelper.isWorkDay(DateHelper.parseDate(join))) {
/* 117:115 */             ycqts += 1.0F;
/* 118:    */           }
/* 119:    */         }
/* 120:    */       }
/* 121:118 */       if (!"".equals(leave))
/* 122:    */       {
/* 123:119 */         String leaveMonth = leave.substring(0, 7);
/* 124:120 */         if (this.toCollectMon.equals(leaveMonth))
/* 125:    */         {
/* 126:121 */           ycqts = (float)DateHelper.getDaysBetween(leave, this.toCollectMon + "-01", true);
/* 127:122 */           if (DateHelper.isWorkDay(DateHelper.parseDate(this.toCollectMon + "-01"))) {
/* 128:123 */             ycqts += 1.0F;
/* 129:    */           }
/* 130:    */         }
/* 131:    */       }
/* 132:128 */       if ("0".equals(zt)) {
/* 133:129 */         zt = "在职";
/* 134:    */       } else {
/* 135:131 */         zt = "离职";
/* 136:    */       }
/* 137:133 */       String gw = Util.null2String(hrm.getString("jobtitle"));
/* 138:134 */       BigDecimal jbgs = jb_bztx;
/* 139:135 */       String sj = "0";
/* 140:136 */       String bj = "0";
/* 141:137 */       String nxj = "0";
/* 142:138 */       String txj = "0";
/* 143:139 */       String hj = "0";
/* 144:140 */       String sangj = "0";
/* 145:141 */       String cj = "0";
/* 146:142 */       String cjj = "0";
/* 147:143 */       String lcj = "0";
/* 148:144 */       String pcj = "0";
/* 149:145 */       String jyj = "0";
/* 150:146 */       String jsj = "0";
/* 151:147 */       String grgs = "0";
/* 152:148 */       int cdTims = 0;
/* 153:149 */       int if10 = 0;
/* 154:150 */       int zaoTims = 0;
/* 155:151 */       while (rsG.next())
/* 156:    */       {
/* 157:152 */         trgs = new BigDecimal(null2o(rsG.getString("trgs"))).add(new BigDecimal(trgs));
/* 158:153 */         brj = new BigDecimal(null2o(rsG.getString("brj"))).add(new BigDecimal(brj));
/* 159:154 */         wdk = new BigDecimal(null2o(rsG.getString("wdk"))).add(new BigDecimal(wdk));
/* 160:155 */         kg = new BigDecimal(null2o(rsG.getString("kg"))).add(kg);
/* 161:156 */         BigDecimal cdTemp = new BigDecimal(null2o(rsG.getString("cd")));
/* 162:157 */         cdsj = cdsj.add(cdTemp);
/* 163:158 */         if ((cdTemp.doubleValue() <= 10.0D) && (cdTemp.doubleValue() > 0.0D))
/* 164:    */         {
/* 165:159 */           cdTims++;
/* 166:160 */           if10++;
/* 167:    */         }
/* 168:161 */         else if ((cdTemp.doubleValue() > 10.0D) && (cdTemp.doubleValue() <= 30.0D))
/* 169:    */         {
/* 170:162 */           cdTims++;
/* 171:    */         }
/* 172:163 */         else if ((cdTemp.doubleValue() > 30.0D) && (cdTemp.doubleValue() <= 60.0D))
/* 173:    */         {
/* 174:164 */           cdTims += 2;
/* 175:    */         }
/* 176:165 */         else if (cdTemp.doubleValue() > 60.0D)
/* 177:    */         {
/* 178:166 */           kg = cdTemp.divide(new BigDecimal(60)).add(kg);
/* 179:    */         }
/* 180:168 */         BigDecimal zaotTemp = new BigDecimal(null2o(rsG.getString("zaot")));
/* 181:169 */         ztsj = ztsj.add(zaotTemp);
/* 182:170 */         if ((zaotTemp.doubleValue() <= 30.0D) && (zaotTemp.doubleValue() > 0.0D)) {
/* 183:171 */           zaoTims++;
/* 184:    */         }
/* 185:172 */         if ((zaotTemp.doubleValue() > 30.0D) && (zaotTemp.doubleValue() <= 60.0D)) {
/* 186:173 */           zaoTims += 2;
/* 187:    */         }
/* 188:174 */         if (zaotTemp.doubleValue() > 60.0D) {
/* 189:175 */           kg = zaotTemp.divide(new BigDecimal(60)).add(kg);
/* 190:    */         }
/* 191:178 */         sj = new BigDecimal(null2o(rsG.getString("sj"))).add(new BigDecimal(sj));
/* 192:179 */         bj = new BigDecimal(null2o(rsG.getString("bj"))).add(new BigDecimal(bj));
/* 193:180 */         nxj = new BigDecimal(null2o(rsG.getString("nxj"))).add(new BigDecimal(nxj));
/* 194:181 */         txj = new BigDecimal(null2o(rsG.getString("txj"))).add(new BigDecimal(txj));
/* 195:182 */         hj = new BigDecimal(null2o(rsG.getString("hj"))).add(new BigDecimal(hj));
/* 196:183 */         sangj = new BigDecimal(null2o(rsG.getString("sangj"))).add(new BigDecimal(sangj));
/* 197:184 */         cj = new BigDecimal(null2o(rsG.getString("cj"))).add(new BigDecimal(cj));
/* 198:185 */         cjj = new BigDecimal(null2o(rsG.getString("cjj"))).add(new BigDecimal(cjj));
/* 199:186 */         lcj = new BigDecimal(null2o(rsG.getString("lcj"))).add(new BigDecimal(lcj));
/* 200:187 */         jyj = new BigDecimal(null2o(rsG.getString("jyj"))).add(new BigDecimal(jyj));
/* 201:188 */         jsj = new BigDecimal(null2o(rsG.getString("jsj"))).add(new BigDecimal(jsj));
/* 202:189 */         grgs = new BigDecimal(null2o(rsG.getString("grgs"))).add(new BigDecimal(grgs));
/* 203:    */       }
/* 204:191 */       if (if10 >= 3) {
/* 205:192 */         cdTims -= 3;
/* 206:    */       } else {
/* 207:194 */         cdTims -= if10;
/* 208:    */       }
/* 209:197 */       kg = kg.multiply(new BigDecimal("1"));
/* 210:198 */       int h1 = kg.intValue();
/* 211:199 */       double kg1 = kg.doubleValue();
/* 212:200 */       if (kg1 < h1 + 0.25D) {
/* 213:201 */         kg1 = h1;
/* 214:202 */       } else if (kg1 < h1 + 0.75D) {
/* 215:203 */         kg1 = h1 + 0.5D;
/* 216:    */       } else {
/* 217:205 */         kg1 = h1 + 1;
/* 218:    */       }
/* 219:207 */       if ("".equals(exists)) {
/* 220:208 */         excSql = 
/* 221:    */         
/* 222:    */ 
/* 223:    */ 
/* 224:    */ 
/* 225:    */ 
/* 226:214 */           "insert into uf_kqhz(jbztx,synx,sytx,ycqts,hzyf,kqrq,trgs,brj,gh,wdk,kg,xm,cdTime,cd,kqlb,ztTime,zt,ssgs,yjbm,ejbm,rzzt,jbgs,sj,bj,nxj,txj,pcj,hj,sangj,cj,cjj,lcj,jyj,jsj,grgs,formmodeid,modedatacreater,modedatacreatedate,modedatacreatetime) values ('" + jb_ztx + "','" + synx + "','" + sytx + "','" + ycqts + "','" + this.toCollectMon + "','" + this.currentDay + "'," + "'" + trgs + "','" + brj + "','" + workcode + "','" + wdk + "','" + kg1 + "','" + xm + "','" + cdsj + "','" + cdTims + "','" + kqlb + "','" + ztsj + "','" + zaoTims + "','" + ssgs + "','" + yjbm + "'," + "'" + ejbm + "','" + zt + "','" + jbgs + "','" + sj + "','" + bj + "','" + nxj + "','" + txj + "','" + pcj + "'," + "'" + hj + "','" + sangj + "','" + cj + "','" + cjj + "','" + lcj + "','" + jyj + "','" + jsj + "','" + grgs + "'," + "'61','1','" + this.currentDay + "','" + this.currentTime + "')";
/* 227:    */       } else {
/* 228:216 */         excSql = 
/* 229:    */         
/* 230:    */ 
/* 231:    */ 
/* 232:    */ 
/* 233:221 */           "update uf_kqhz set trgs='" + trgs + "',brj='" + brj + "',wdk='" + wdk + "',kg='" + kg1 + "',cd='" + cdTims + "',cdTime='" + cdsj + "'," + "kqlb='" + kqlb + "',ztTime='" + ztsj + "',zt='" + zaoTims + "',ssgs='" + ssgs + "',yjbm='" + yjbm + "',ejbm='" + ejbm + "',rzzt='" + zt + "'," + "gw='" + gw + "',jbgs='" + jbgs + "',sj='" + sj + "',bj='" + bj + "',nxj='" + nxj + "',pcj='" + pcj + "'," + "txj='" + txj + "',hj='" + hj + "',sangj='" + sangj + "',cj='" + cj + "',cjj='" + cjj + "',lcj='" + lcj + "',jyj='" + jyj + "',jsj='" + jsj + "'," + "grgs='" + grgs + "',ycqts='" + ycqts + "',synx='" + synx + "',sytx='" + sytx + "',jbztx='" + jb_ztx + "'" + " where hzyf= '" + this.toCollectMon + "' and xm='" + xm + "'";
/* 234:    */       }
/* 235:223 */       rs.executeSql(excSql);
/* 236:    */     }
/* 237:    */   }
/* 238:    */   
/* 239:    */   private String getLastDateOfMon(String dstr)
/* 240:    */   {
/* 241:228 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 242:229 */     String lastdate = "";
/* 243:    */     try
/* 244:    */     {
/* 245:231 */       Date date = sdf.parse(dstr);
/* 246:232 */       lastdate = DateHelper.getLastDayOfMonthWeek(date);
/* 247:    */     }
/* 248:    */     catch (ParseException e)
/* 249:    */     {
/* 250:235 */       e.printStackTrace();
/* 251:    */     }
/* 252:237 */     return lastdate;
/* 253:    */   }
/* 254:    */   
/* 255:    */   public void getData()
/* 256:    */   {
/* 257:243 */     String sql = "select gh,xm from uf_kqhz where hzyf = '" + this.toCollectMon + "'";
/* 258:244 */     this.rsA.executeSql(sql);
/* 259:245 */     while (this.rsA.next())
/* 260:    */     {
/* 261:246 */       String code = Util.null2String(this.rsA.getString("gh"));
/* 262:247 */       String id = Util.null2String(this.rsA.getString("xm"));
/* 263:248 */       this.hz_Map.put(id, code);
/* 264:    */     }
/* 265:    */   }
/* 266:    */   
/* 267:    */   public String null2o(String str)
/* 268:    */   {
/* 269:257 */     String returnSt = str;
/* 270:258 */     if ((str == null) || ("".equals(str))) {
/* 271:259 */       returnSt = "0";
/* 272:    */     }
/* 273:261 */     return returnSt;
/* 274:    */   }
/* 275:    */   
/* 276:    */   private void getJiaBan(String hrmid)
/* 277:    */   {
/* 278:267 */     this.jiaBan_Map.clear();
/* 279:268 */     String sql = "select hrmno,jbrq,xq,hdgs,jbxs,sfztx from uf__jb_temp where jbrq like '%" + this.toCollectMon + "%' and hrmid = '" + hrmid + "'";
/* 280:269 */     this.rsA.executeSql(sql);
/* 281:270 */     BigDecimal ztx = new BigDecimal("0");
/* 282:271 */     BigDecimal bztx = new BigDecimal("0");
/* 283:272 */     while (this.rsA.next())
/* 284:    */     {
/* 285:273 */       String hdgs = Util.null2o(this.rsA.getString("hdgs"));
/* 286:274 */       String jbxs = Util.null2o(this.rsA.getString("jbxs"));
/* 287:275 */       String sfztx = Util.null2String(this.rsA.getString("sfztx"));
/* 288:276 */       if ("0".equals(sfztx)) {
/* 289:277 */         ztx = ztx.add(new BigDecimal(hdgs));
/* 290:278 */       } else if ("1".equals(sfztx)) {
/* 291:279 */         bztx = bztx.add(new BigDecimal(hdgs));
/* 292:    */       }
/* 293:    */     }
/* 294:282 */     this.jiaBan_Map.put(hrmid + "_ztx", ztx);
/* 295:283 */     this.jiaBan_Map.put(hrmid + "_bztx", bztx);
/* 296:    */   }
/* 297:    */   
/* 298:    */   private void getHrmCusData()
/* 299:    */   {
/* 300:290 */     String sql = "select Name,StartWorkTime,EndWorkTime,AmStartWorkTime,PmEndWorkTime,KaoQinType,Company,DeptOneName,DeptTwoName,DeptThreeName,InCompany, DeptOneNameText,DeptTwoNameText,DeptThreeNameText,JoinDate,LeaveDate,SYNianXiuJia,SYTiaoXiuJia from uf_hr_userinfo1";
/* 301:    */     
/* 302:292 */     RecordSet cusRs = new RecordSet();
/* 303:293 */     cusRs.executeSql(sql);
/* 304:294 */     while (cusRs.next())
/* 305:    */     {
/* 306:295 */       String id = Util.null2String(cusRs.getString("Name"));
/* 307:296 */       String StartWorkTime = Util.null2String(cusRs.getString("StartWorkTime"));
/* 308:297 */       String EndWorkTime = Util.null2String(cusRs.getString("EndWorkTime"));
/* 309:298 */       String amE = Util.null2String(cusRs.getString("AmStartWorkTime"));
/* 310:299 */       String pmS = Util.null2String(cusRs.getString("PmEndWorkTime"));
/* 311:300 */       String KaoQinType = Util.null2String(cusRs.getString("KaoQinType"));
/* 312:301 */       String Company = Util.null2String(cusRs.getString("Company"));
/* 313:302 */       String DeptOneName = Util.null2String(cusRs.getString("DeptOneNameText"));
/* 314:303 */       String DeptTwoName = Util.null2String(cusRs.getString("DeptTwoNameText"));
/* 315:304 */       String DeptThreeName = Util.null2String(cusRs.getString("DeptThreeNameText"));
/* 316:305 */       String InCompany = Util.null2String(cusRs.getString("InCompany"));
/* 317:306 */       String JoinDate = Util.null2String(cusRs.getString("JoinDate"));
/* 318:307 */       String LeaveDate = Util.null2String(cusRs.getString("LeaveDate"));
/* 319:308 */       String SYNianXiuJia = Util.null2String(cusRs.getString("SYNianXiuJia"));
/* 320:309 */       String SYTiaoXiuJia = Util.null2String(cusRs.getString("SYTiaoXiuJia"));
/* 321:310 */       this.cus_Map.put(id + "_18", StartWorkTime);
/* 322:311 */       this.cus_Map.put(id + "_19", EndWorkTime);
/* 323:312 */       this.cus_Map.put(id + "_amE", amE);
/* 324:313 */       this.cus_Map.put(id + "_pmS", pmS);
/* 325:314 */       this.cus_Map.put(id + "_KQT", KaoQinType);
/* 326:315 */       this.cus_Map.put(id + "_COM", Company);
/* 327:316 */       this.cus_Map.put(id + "_DEPT1", DeptOneName);
/* 328:317 */       this.cus_Map.put(id + "_DEPT2", DeptTwoName);
/* 329:318 */       this.cus_Map.put(id + "_DEPT3", DeptThreeName);
/* 330:319 */       this.cus_Map.put(id + "_RZZT", InCompany);
/* 331:320 */       this.cus_Map.put(id + "_join", JoinDate);
/* 332:321 */       this.cus_Map.put(id + "_leave", LeaveDate);
/* 333:322 */       this.cus_Map.put(id + "_synx", SYNianXiuJia);
/* 334:323 */       this.cus_Map.put(id + "_sytx", SYTiaoXiuJia);
/* 335:    */     }
/* 336:    */   }
/* 337:    */   
/* 338:    */   public static void main(String[] args)
/* 339:    */   {
/* 340:327 */     System.out.println(DateHelper.getDaysBetween("2017-01-31", "2017-01-01", true));
/* 341:328 */     System.out.println("11:11:00".compareTo(""));
/* 342:329 */     System.out.println("".compareTo("11:00:11"));
/* 343:330 */     System.out.println("13:00:00".compareTo("11:00:11"));
/* 344:    */   }
/* 345:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     weaver.dh.interfaces.toCollectMon
 * JD-Core Version:    0.7.0.1
 */