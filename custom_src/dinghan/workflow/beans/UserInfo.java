/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import org.apache.commons.logging.Log;
/*   4:    */ import org.apache.commons.logging.LogFactory;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class UserInfo
/*   8:    */ {
/*   9:  9 */   private Log log = LogFactory.getLog(UserInfo.class.getName());
/*  10:    */   private int id;
/*  11:    */   private int requestId;
/*  12:    */   private String Code;
/*  13:    */   private int Name;
/*  14:    */   private int KaoQinType;
/*  15:    */   private int DeptOneName;
/*  16:    */   private int DeptTwoName;
/*  17:    */   private int DeptThreeName;
/*  18:    */   private String Post;
/*  19:    */   private int InCompany;
/*  20:    */   private String JoinDate;
/*  21:    */   private String LeaveDate;
/*  22:    */   private String Mail;
/*  23:    */   private String StartWorkTime;
/*  24:    */   private String EndWorkTime;
/*  25:    */   private double SYNianXiuJia;
/*  26:    */   private double SYTiaoXiuJia;
/*  27:    */   private String LeaveInvest;
/*  28:    */   private String LeaveReason;
/*  29:    */   private String OtherReason;
/*  30:    */   private String Company;
/*  31:    */   private int formmodeid;
/*  32:    */   private int modedatacreater;
/*  33:    */   private int modedatacreatertype;
/*  34:    */   private String modedatacreatedate;
/*  35:    */   private String modedatacreatetime;
/*  36:    */   private String NameCN;
/*  37:    */   private String AmStartWorkTime;
/*  38:    */   private String PmEndWorkTime;
/*  39:    */   private int MobileAtten;
/*  40:    */   private String Obode;
/*  41:    */   private double rest;
/*  42:    */   
/*  43:    */   public Log getLog()
/*  44:    */   {
/*  45: 12 */     return this.log;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void setLog(Log log)
/*  49:    */   {
/*  50: 16 */     this.log = log;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public UserInfo() {}
/*  54:    */   
/*  55:    */   public UserInfo(int userid)
/*  56:    */   {
/*  57:    */     try
/*  58:    */     {
/*  59: 61 */       String sql = "select * from uf_hr_userinfo1 where Name= " + userid;
/*  60: 62 */       RecordSet rs = new RecordSet();
/*  61: 63 */       rs.executeSql(sql);
/*  62: 64 */       if (rs.next())
/*  63:    */       {
/*  64: 65 */         this.Code = rs.getString("Code");
/*  65: 66 */         this.Company = rs.getString("Company");
/*  66: 67 */         this.DeptOneName = rs.getInt("DeptOneName");
/*  67: 68 */         this.DeptThreeName = rs.getInt("DeptThreeName");
/*  68: 69 */         this.DeptTwoName = rs.getInt("DeptTwoName");
/*  69: 70 */         this.EndWorkTime = rs.getString("EndWorkTime");
/*  70: 71 */         this.formmodeid = rs.getInt("formmodeid");
/*  71: 72 */         this.id = rs.getInt("id");
/*  72: 73 */         this.InCompany = rs.getInt("InCompany");
/*  73: 74 */         this.JoinDate = rs.getString("JoinDate");
/*  74: 75 */         this.KaoQinType = rs.getInt("KaoQinType");
/*  75: 76 */         this.LeaveDate = rs.getString("LeaveDate");
/*  76: 77 */         this.LeaveInvest = rs.getString("LeaveInvest");
/*  77: 78 */         this.LeaveReason = rs.getString("LeaveReason");
/*  78: 79 */         this.Mail = rs.getString("Mail");
/*  79: 80 */         this.modedatacreatedate = rs.getString("modedatacreatedate");
/*  80: 81 */         this.modedatacreater = rs.getInt("modedatacreater");
/*  81: 82 */         this.modedatacreatertype = rs.getInt("modedatacreatertype");
/*  82: 83 */         this.modedatacreatetime = rs.getString("modedatacreatetime");
/*  83: 84 */         this.Name = rs.getInt("Name");
/*  84: 85 */         this.OtherReason = rs.getString("OtherReason");
/*  85: 86 */         this.Post = rs.getString("Post");
/*  86: 87 */         this.requestId = rs.getInt("requestId");
/*  87: 88 */         this.StartWorkTime = rs.getString("StartWorkTime");
/*  88: 89 */         this.SYNianXiuJia = rs.getDouble("SYNianXiuJia");
/*  89: 90 */         this.SYTiaoXiuJia = rs.getDouble("SYTiaoXiuJia");
/*  90: 91 */         this.NameCN = rs.getString("NameCN");
/*  91: 92 */         this.AmStartWorkTime = rs.getString("AmStartWorkTime");
/*  92: 93 */         this.PmEndWorkTime = rs.getString("PmEndWorkTime");
/*  93: 94 */         this.Obode = rs.getString("MobileAtten");
/*  94: 95 */         this.MobileAtten = rs.getInt("Obode");
/*  95: 96 */         this.rest = 1.0D;
/*  96: 97 */         if ("13:30:00".equals(this.PmEndWorkTime)) {
/*  97: 98 */           this.rest = 1.5D;
/*  98:    */         }
/*  99:    */       }
/* 100:    */     }
/* 101:    */     catch (Exception e)
/* 102:    */     {
/* 103:103 */       this.log.error("个人信息查询：" + e);
/* 104:    */     }
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void updateholiday(int userid, double num, int type)
/* 108:    */   {
/* 109:    */     try
/* 110:    */     {
/* 111:112 */       String sql = "";
/* 112:113 */       if (type == 0) {
/* 113:114 */         sql = 
/* 114:115 */           "update uf_hr_userinfo1 set SYNianXiuJia= " + num + " where Name=" + userid;
/* 115:116 */       } else if (type == 1) {
/* 116:117 */         sql = 
/* 117:118 */           "update uf_hr_userinfo1 set SYTiaoXiuJia= " + num + " where Name=" + userid;
/* 118:    */       }
/* 119:120 */       RecordSet rs = new RecordSet();
/* 120:121 */       rs.executeSql(sql);
/* 121:    */     }
/* 122:    */     catch (Exception e)
/* 123:    */     {
/* 124:125 */       this.log.error("修改年休调休假：" + e);
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   public int getId()
/* 129:    */   {
/* 130:130 */     return this.id;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void setId(int id)
/* 134:    */   {
/* 135:134 */     this.id = id;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public int getRequestId()
/* 139:    */   {
/* 140:138 */     return this.requestId;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void setRequestId(int requestId)
/* 144:    */   {
/* 145:142 */     this.requestId = requestId;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public String getCode()
/* 149:    */   {
/* 150:146 */     return this.Code;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void setCode(String code)
/* 154:    */   {
/* 155:150 */     this.Code = code;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public int getName()
/* 159:    */   {
/* 160:154 */     return this.Name;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void setName(int name)
/* 164:    */   {
/* 165:158 */     this.Name = name;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public int getKaoQinType()
/* 169:    */   {
/* 170:162 */     return this.KaoQinType;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void setKaoQinType(int kaoQinType)
/* 174:    */   {
/* 175:166 */     this.KaoQinType = kaoQinType;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public int getDeptOneName()
/* 179:    */   {
/* 180:170 */     return this.DeptOneName;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void setDeptOneName(int deptOneName)
/* 184:    */   {
/* 185:174 */     this.DeptOneName = deptOneName;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public int getDeptTwoName()
/* 189:    */   {
/* 190:178 */     return this.DeptTwoName;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void setDeptTwoName(int deptTwoName)
/* 194:    */   {
/* 195:182 */     this.DeptTwoName = deptTwoName;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public int getDeptThreeName()
/* 199:    */   {
/* 200:186 */     return this.DeptThreeName;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setDeptThreeName(int deptThreeName)
/* 204:    */   {
/* 205:190 */     this.DeptThreeName = deptThreeName;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public String getPost()
/* 209:    */   {
/* 210:194 */     return this.Post;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public void setPost(String post)
/* 214:    */   {
/* 215:198 */     this.Post = post;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public int getInCompany()
/* 219:    */   {
/* 220:202 */     return this.InCompany;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void setInCompany(int inCompany)
/* 224:    */   {
/* 225:206 */     this.InCompany = inCompany;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public String getJoinDate()
/* 229:    */   {
/* 230:210 */     return this.JoinDate;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public void setJoinDate(String joinDate)
/* 234:    */   {
/* 235:214 */     this.JoinDate = joinDate;
/* 236:    */   }
/* 237:    */   
/* 238:    */   public String getLeaveDate()
/* 239:    */   {
/* 240:218 */     return this.LeaveDate;
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void setLeaveDate(String leaveDate)
/* 244:    */   {
/* 245:222 */     this.LeaveDate = leaveDate;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public String getMail()
/* 249:    */   {
/* 250:226 */     return this.Mail;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public void setMail(String mail)
/* 254:    */   {
/* 255:230 */     this.Mail = mail;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public String getStartWorkTime()
/* 259:    */   {
/* 260:234 */     return this.StartWorkTime;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void setStartWorkTime(String startWorkTime)
/* 264:    */   {
/* 265:238 */     this.StartWorkTime = startWorkTime;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public String getEndWorkTime()
/* 269:    */   {
/* 270:242 */     return this.EndWorkTime;
/* 271:    */   }
/* 272:    */   
/* 273:    */   public void setEndWorkTime(String endWorkTime)
/* 274:    */   {
/* 275:246 */     this.EndWorkTime = endWorkTime;
/* 276:    */   }
/* 277:    */   
/* 278:    */   public double getSYNianXiuJia()
/* 279:    */   {
/* 280:250 */     return this.SYNianXiuJia;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public void setSYNianXiuJia(double sYNianXiuJia)
/* 284:    */   {
/* 285:254 */     this.SYNianXiuJia = sYNianXiuJia;
/* 286:    */   }
/* 287:    */   
/* 288:    */   public double getSYTiaoXiuJia()
/* 289:    */   {
/* 290:258 */     return this.SYTiaoXiuJia;
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void setSYTiaoXiuJia(double sYTiaoXiuJia)
/* 294:    */   {
/* 295:262 */     this.SYTiaoXiuJia = sYTiaoXiuJia;
/* 296:    */   }
/* 297:    */   
/* 298:    */   public String getLeaveInvest()
/* 299:    */   {
/* 300:266 */     return this.LeaveInvest;
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void setLeaveInvest(String leaveInvest)
/* 304:    */   {
/* 305:270 */     this.LeaveInvest = leaveInvest;
/* 306:    */   }
/* 307:    */   
/* 308:    */   public String getLeaveReason()
/* 309:    */   {
/* 310:274 */     return this.LeaveReason;
/* 311:    */   }
/* 312:    */   
/* 313:    */   public void setLeaveReason(String leaveReason)
/* 314:    */   {
/* 315:278 */     this.LeaveReason = leaveReason;
/* 316:    */   }
/* 317:    */   
/* 318:    */   public String getOtherReason()
/* 319:    */   {
/* 320:282 */     return this.OtherReason;
/* 321:    */   }
/* 322:    */   
/* 323:    */   public void setOtherReason(String otherReason)
/* 324:    */   {
/* 325:286 */     this.OtherReason = otherReason;
/* 326:    */   }
/* 327:    */   
/* 328:    */   public String getCompany()
/* 329:    */   {
/* 330:290 */     return this.Company;
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void setCompany(String company)
/* 334:    */   {
/* 335:294 */     this.Company = company;
/* 336:    */   }
/* 337:    */   
/* 338:    */   public int getFormmodeid()
/* 339:    */   {
/* 340:298 */     return this.formmodeid;
/* 341:    */   }
/* 342:    */   
/* 343:    */   public void setFormmodeid(int formmodeid)
/* 344:    */   {
/* 345:302 */     this.formmodeid = formmodeid;
/* 346:    */   }
/* 347:    */   
/* 348:    */   public int getModedatacreater()
/* 349:    */   {
/* 350:306 */     return this.modedatacreater;
/* 351:    */   }
/* 352:    */   
/* 353:    */   public void setModedatacreater(int modedatacreater)
/* 354:    */   {
/* 355:310 */     this.modedatacreater = modedatacreater;
/* 356:    */   }
/* 357:    */   
/* 358:    */   public int getModedatacreatertype()
/* 359:    */   {
/* 360:314 */     return this.modedatacreatertype;
/* 361:    */   }
/* 362:    */   
/* 363:    */   public void setModedatacreatertype(int modedatacreatertype)
/* 364:    */   {
/* 365:318 */     this.modedatacreatertype = modedatacreatertype;
/* 366:    */   }
/* 367:    */   
/* 368:    */   public String getModedatacreatedate()
/* 369:    */   {
/* 370:322 */     return this.modedatacreatedate;
/* 371:    */   }
/* 372:    */   
/* 373:    */   public void setModedatacreatedate(String modedatacreatedate)
/* 374:    */   {
/* 375:326 */     this.modedatacreatedate = modedatacreatedate;
/* 376:    */   }
/* 377:    */   
/* 378:    */   public String getModedatacreatetime()
/* 379:    */   {
/* 380:330 */     return this.modedatacreatetime;
/* 381:    */   }
/* 382:    */   
/* 383:    */   public void setModedatacreatetime(String modedatacreatetime)
/* 384:    */   {
/* 385:334 */     this.modedatacreatetime = modedatacreatetime;
/* 386:    */   }
/* 387:    */   
/* 388:    */   public String getNameCN()
/* 389:    */   {
/* 390:338 */     return this.NameCN;
/* 391:    */   }
/* 392:    */   
/* 393:    */   public void setNameCN(String nameCN)
/* 394:    */   {
/* 395:342 */     this.NameCN = nameCN;
/* 396:    */   }
/* 397:    */   
/* 398:    */   public String getAmStartWorkTime()
/* 399:    */   {
/* 400:346 */     return this.AmStartWorkTime;
/* 401:    */   }
/* 402:    */   
/* 403:    */   public void setAmStartWorkTime(String amStartWorkTime)
/* 404:    */   {
/* 405:350 */     this.AmStartWorkTime = amStartWorkTime;
/* 406:    */   }
/* 407:    */   
/* 408:    */   public String getPmEndWorkTime()
/* 409:    */   {
/* 410:354 */     return this.PmEndWorkTime;
/* 411:    */   }
/* 412:    */   
/* 413:    */   public void setPmEndWorkTime(String pmEndWorkTime)
/* 414:    */   {
/* 415:358 */     this.PmEndWorkTime = pmEndWorkTime;
/* 416:    */   }
/* 417:    */   
/* 418:    */   public int getMobileAtten()
/* 419:    */   {
/* 420:362 */     return this.MobileAtten;
/* 421:    */   }
/* 422:    */   
/* 423:    */   public void setMobileAtten(int mobileAtten)
/* 424:    */   {
/* 425:366 */     this.MobileAtten = mobileAtten;
/* 426:    */   }
/* 427:    */   
/* 428:    */   public String getObode()
/* 429:    */   {
/* 430:370 */     return this.Obode;
/* 431:    */   }
/* 432:    */   
/* 433:    */   public void setObode(String obode)
/* 434:    */   {
/* 435:374 */     this.Obode = obode;
/* 436:    */   }
/* 437:    */   
/* 438:    */   public double getRest()
/* 439:    */   {
/* 440:378 */     return this.rest;
/* 441:    */   }
/* 442:    */   
/* 443:    */   public void setRest(double rest)
/* 444:    */   {
/* 445:382 */     this.rest = rest;
/* 446:    */   }
/* 447:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.UserInfo
 * JD-Core Version:    0.7.0.1
 */