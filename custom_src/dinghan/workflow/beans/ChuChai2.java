/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Date;
/*   5:    */ import org.apache.commons.logging.Log;
/*   6:    */ import org.apache.commons.logging.LogFactory;
/*   7:    */ import weaver.conn.RecordSet;
/*   8:    */ 
/*   9:    */ public class ChuChai2
/*  10:    */ {
/*  11: 12 */   private static Log log = LogFactory.getLog(ChuChai2.class.getName());
/*  12:    */   private int id;
/*  13:    */   private int mainid;
/*  14:    */   private int userid;
/*  15:    */   private int kqy;
/*  16:    */   private int hdzt;
/*  17:    */   private String ccrq;
/*  18:    */   private String yjkssj;
/*  19:    */   private String yjjssj;
/*  20:    */   private String hdkssj;
/*  21:    */   private String hdjssj;
/*  22:    */   private String appnom;
/*  23:    */   private String xm;
/*  24:    */   private String gh;
/*  25:    */   private String sqrq;
/*  26:    */   private String lcjssj;
/*  27:    */   private int row_id;
/*  28:    */   
/*  29:    */   public Log getLog()
/*  30:    */   {
/*  31: 15 */     return log;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void setLog(Log log)
/*  35:    */   {
/*  36: 19 */     log = log;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void insert()
/*  40:    */     throws Exception
/*  41:    */   {
/*  42:    */     try
/*  43:    */     {
/*  44: 44 */       String sql = "INSERT INTO formtable_main_92_dt2 (mainid,userid,kqy,hdzt,ccrq,yjkssj,yjjssj,hdkssj,hdjssj,appnom,xm,gh,sqrq,lcjssj,row_id)  VALUES    (";
/*  45: 45 */       sql = sql + "'" + this.mainid + "',";
/*  46: 46 */       sql = sql + "'" + this.userid + "',";
/*  47: 47 */       sql = sql + "'" + this.kqy + "',";
/*  48: 48 */       sql = sql + "'" + this.hdzt + "',";
/*  49: 49 */       sql = sql + "'" + this.ccrq + "',";
/*  50: 50 */       sql = sql + "'" + this.yjkssj + "',";
/*  51: 51 */       sql = sql + "'" + this.yjjssj + "',";
/*  52: 52 */       sql = sql + "'" + this.hdkssj + "',";
/*  53: 53 */       sql = sql + "'" + this.hdjssj + "',";
/*  54: 54 */       sql = sql + "'" + this.appnom + "',";
/*  55: 55 */       sql = sql + "'" + this.xm + "',";
/*  56: 56 */       sql = sql + "'" + this.gh + "',";
/*  57: 57 */       sql = sql + "'" + this.sqrq + "',";
/*  58: 58 */       sql = sql + "'" + this.lcjssj + "',";
/*  59: 59 */       sql = sql + "'" + this.row_id + "'";
/*  60: 60 */       sql = sql + ")";
/*  61: 61 */       RecordSet recordSet = new RecordSet();
/*  62: 62 */       recordSet.execute(sql);
/*  63:    */     }
/*  64:    */     catch (Exception e)
/*  65:    */     {
/*  66: 65 */       log.error("插入出差明细表2信息：" + e);
/*  67: 66 */       throw e;
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public static ArrayList<ChuChai2> queryList(String userid, String ksrq, String jsrq)
/*  72:    */     throws Exception
/*  73:    */   {
/*  74: 75 */     ArrayList<ChuChai2> ccList = new ArrayList();
/*  75:    */     try
/*  76:    */     {
/*  77: 77 */       String sql = "select * from formtable_main_92_dt2  where userid='";
/*  78: 78 */       sql = sql + userid + "' and ccrq BETWEEN '" + ksrq + "' and '" + jsrq + 
/*  79: 79 */         "'";
/*  80: 80 */       RecordSet rs = new RecordSet();
/*  81: 81 */       rs.executeSql(sql);
/*  82: 82 */       while (rs.next())
/*  83:    */       {
/*  84: 83 */         ChuChai2 occ = new ChuChai2();
/*  85: 84 */         occ.setAppnom(rs.getString("appnom"));
/*  86: 85 */         occ.setCcrq(rs.getString("ccrq"));
/*  87: 86 */         occ.setGh(rs.getString("gh"));
/*  88: 87 */         occ.setHdjssj(rs.getString("hdjssj"));
/*  89: 88 */         occ.setHdkssj(rs.getString("hdkssj"));
/*  90: 89 */         occ.setHdzt(rs.getInt("hdzt"));
/*  91: 90 */         occ.setId(rs.getInt("id"));
/*  92: 91 */         occ.setKqy(rs.getInt("kqy"));
/*  93: 92 */         occ.setLcjssj(rs.getString("lcjssj"));
/*  94: 93 */         occ.setMainid(rs.getInt("mainid"));
/*  95: 94 */         occ.setSqrq(rs.getString("sqrq"));
/*  96: 95 */         occ.setUserid(rs.getInt("userid"));
/*  97: 96 */         occ.setXm(rs.getString("xm"));
/*  98: 97 */         occ.setYjjssj(rs.getString("yjjssj"));
/*  99: 98 */         occ.setYjkssj(rs.getString("yjkssj"));
/* 100: 99 */         ccList.add(occ);
/* 101:    */       }
/* 102:    */     }
/* 103:    */     catch (Exception e)
/* 104:    */     {
/* 105:103 */       log.error("得到出差明细表二数据失败：" + e);
/* 106:104 */       throw e;
/* 107:    */     }
/* 108:106 */     return ccList;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public static void delete(int mainid, int row_id)
/* 112:    */     throws Exception
/* 113:    */   {
/* 114:114 */     String sql = "";
/* 115:    */     try
/* 116:    */     {
/* 117:116 */       if (row_id <= 0) {
/* 118:117 */         sql = 
/* 119:118 */           "delete from formtable_main_92_dt2 where mainid=" + mainid;
/* 120:    */       } else {
/* 121:120 */         sql = 
/* 122:121 */           "delete from formtable_main_92_dt2 where mainid=" + mainid + " and row_id=" + row_id;
/* 123:    */       }
/* 124:123 */       RecordSet rs = new RecordSet();
/* 125:124 */       rs.executeSql(sql);
/* 126:    */     }
/* 127:    */     catch (Exception e)
/* 128:    */     {
/* 129:127 */       log.error("删除请假明细表三信息：" + e);
/* 130:128 */       throw e;
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   public static void checkList(int mainid)
/* 135:    */     throws Exception
/* 136:    */   {
/* 137:140 */     log.error("出差明细核定：");
/* 138:    */     try
/* 139:    */     {
/* 140:142 */       String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
/* 141:143 */       String sql = "";
/* 142:144 */       if (mainid == 0)
/* 143:    */       {
/* 144:145 */         sql = 
/* 145:146 */           "SELECT * FROM formtable_main_92_dt2 WHERE ccrq<'" + nowDate + "'";
/* 146:147 */         sql = sql + " AND hdzt=0 ORDER BY mainid,row_id";
/* 147:    */       }
/* 148:    */       else
/* 149:    */       {
/* 150:149 */         sql = 
/* 151:150 */           "SELECT * FROM formtable_main_92_dt2 WHERE mainid=" + mainid + " ORDER BY row_id";
/* 152:    */       }
/* 153:152 */       RecordSet rs = new RecordSet();
/* 154:153 */       rs.executeSql(sql);
/* 155:154 */       log.error("sql=" + sql);
/* 156:155 */       while (rs.next())
/* 157:    */       {
/* 158:156 */         ChuChai2 oCC = new ChuChai2();
/* 159:157 */         oCC.setMainid(rs.getInt("mainid"));
/* 160:158 */         oCC.setRow_id(rs.getInt("row_id"));
/* 161:159 */         oCC.setUserid(rs.getInt("userid"));
/* 162:160 */         oCC.setCcrq(rs.getString("ccrq"));
/* 163:161 */         oCC.setYjkssj(rs.getString("yjkssj"));
/* 164:162 */         oCC.setYjjssj(rs.getString("yjjssj"));
/* 165:163 */         oCC.setAppnom(rs.getString(""));
/* 166:164 */         oCC.setGh(rs.getString(""));
/* 167:165 */         oCC.setKqy(rs.getInt(""));
/* 168:166 */         ChuChai cc_main = new ChuChai(oCC.getMainid());
/* 169:167 */         if ((!cc_main.getLsh2().equals("")) || 
/* 170:168 */           (!cc_main.getAppnom().equals("")))
/* 171:    */         {
/* 172:170 */           UserInfo userInfo = new UserInfo(cc_main.getProposer());
/* 173:172 */           if (oCC.getCcrq().compareTo(nowDate) < 0)
/* 174:    */           {
/* 175:173 */             oCC.setHdkssj(oCC.getYjkssj());
/* 176:174 */             oCC.setHdjssj(oCC.getYjjssj());
/* 177:175 */             oCC.setHdzt(1);
/* 178:176 */             delete(oCC.getMainid(), oCC.getRow_id());
/* 179:177 */             oCC.insert();
/* 180:    */           }
/* 181:179 */           Cctemp cctemp = new Cctemp();
/* 182:180 */           cctemp.setCcrq(oCC.getCcrq());
/* 183:181 */           cctemp.setDataid(String.valueOf(cc_main.getRequestId()));
/* 184:182 */           cctemp.setFlowno(cc_main.getAppnom());
/* 185:183 */           cctemp.setHdjssj(oCC.getHdjssj());
/* 186:184 */           cctemp.setHdkssj(oCC.getHdkssj());
/* 187:185 */           cctemp.setHrmid(cc_main.getProposer());
/* 188:186 */           cctemp.setHrmno(userInfo.getCode());
/* 189:    */           
/* 190:188 */           cctemp.setMainid(String.valueOf(oCC.getMainid()));
/* 191:189 */           cctemp.setXq("");
/* 192:190 */           cctemp.toString();
/* 193:    */           
/* 194:192 */           Cctemp.delete(oCC.getMainid(), oCC.getCcrq());
/* 195:    */           
/* 196:194 */           cctemp.insert();
/* 197:    */         }
/* 198:    */       }
/* 199:    */     }
/* 200:    */     catch (Exception e)
/* 201:    */     {
/* 202:201 */       log.error("核定出差明细：" + e);
/* 203:202 */       throw e;
/* 204:    */     }
/* 205:    */   }
/* 206:    */   
/* 207:    */   public int getId()
/* 208:    */   {
/* 209:207 */     return this.id;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void setId(int id)
/* 213:    */   {
/* 214:211 */     this.id = id;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public int getMainid()
/* 218:    */   {
/* 219:215 */     return this.mainid;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void setMainid(int mainid)
/* 223:    */   {
/* 224:219 */     this.mainid = mainid;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public int getUserid()
/* 228:    */   {
/* 229:223 */     return this.userid;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void setUserid(int userid)
/* 233:    */   {
/* 234:227 */     this.userid = userid;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public int getKqy()
/* 238:    */   {
/* 239:231 */     return this.kqy;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void setKqy(int kqy)
/* 243:    */   {
/* 244:235 */     this.kqy = kqy;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public int getHdzt()
/* 248:    */   {
/* 249:239 */     return this.hdzt;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void setHdzt(int hdzt)
/* 253:    */   {
/* 254:243 */     this.hdzt = hdzt;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public String getCcrq()
/* 258:    */   {
/* 259:247 */     return this.ccrq;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void setCcrq(String ccrq)
/* 263:    */   {
/* 264:251 */     this.ccrq = ccrq;
/* 265:    */   }
/* 266:    */   
/* 267:    */   public String getYjkssj()
/* 268:    */   {
/* 269:255 */     return this.yjkssj;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public void setYjkssj(String yjkssj)
/* 273:    */   {
/* 274:259 */     this.yjkssj = yjkssj;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public String getYjjssj()
/* 278:    */   {
/* 279:263 */     return this.yjjssj;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void setYjjssj(String yjjssj)
/* 283:    */   {
/* 284:267 */     this.yjjssj = yjjssj;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public String getHdkssj()
/* 288:    */   {
/* 289:271 */     return this.hdkssj;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void setHdkssj(String hdkssj)
/* 293:    */   {
/* 294:275 */     this.hdkssj = hdkssj;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public String getHdjssj()
/* 298:    */   {
/* 299:279 */     return this.hdjssj;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public void setHdjssj(String hdjssj)
/* 303:    */   {
/* 304:283 */     this.hdjssj = hdjssj;
/* 305:    */   }
/* 306:    */   
/* 307:    */   public String getAppnom()
/* 308:    */   {
/* 309:287 */     return this.appnom;
/* 310:    */   }
/* 311:    */   
/* 312:    */   public void setAppnom(String appnom)
/* 313:    */   {
/* 314:291 */     this.appnom = appnom;
/* 315:    */   }
/* 316:    */   
/* 317:    */   public String getXm()
/* 318:    */   {
/* 319:295 */     return this.xm;
/* 320:    */   }
/* 321:    */   
/* 322:    */   public void setXm(String xm)
/* 323:    */   {
/* 324:299 */     this.xm = xm;
/* 325:    */   }
/* 326:    */   
/* 327:    */   public String getGh()
/* 328:    */   {
/* 329:303 */     return this.gh;
/* 330:    */   }
/* 331:    */   
/* 332:    */   public void setGh(String gh)
/* 333:    */   {
/* 334:307 */     this.gh = gh;
/* 335:    */   }
/* 336:    */   
/* 337:    */   public String getSqrq()
/* 338:    */   {
/* 339:311 */     return this.sqrq;
/* 340:    */   }
/* 341:    */   
/* 342:    */   public void setSqrq(String sqrq)
/* 343:    */   {
/* 344:315 */     this.sqrq = sqrq;
/* 345:    */   }
/* 346:    */   
/* 347:    */   public String getLcjssj()
/* 348:    */   {
/* 349:319 */     return this.lcjssj;
/* 350:    */   }
/* 351:    */   
/* 352:    */   public void setLcjssj(String lcjssj)
/* 353:    */   {
/* 354:323 */     this.lcjssj = lcjssj;
/* 355:    */   }
/* 356:    */   
/* 357:    */   public int getRow_id()
/* 358:    */   {
/* 359:327 */     return this.row_id;
/* 360:    */   }
/* 361:    */   
/* 362:    */   public void setRow_id(int row_id)
/* 363:    */   {
/* 364:331 */     this.row_id = row_id;
/* 365:    */   }
/* 366:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.ChuChai2
 * JD-Core Version:    0.7.0.1
 */