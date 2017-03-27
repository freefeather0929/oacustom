/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import org.apache.commons.logging.Log;
/*   4:    */ import org.apache.commons.logging.LogFactory;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class Jbtemp
/*   8:    */ {
/*   9:  9 */   private static Log log = LogFactory.getLog(Jbtemp.class.getName());
/*  10:    */   private int id;
/*  11:    */   private int requestId;
/*  12:    */   private int hrmid;
/*  13:    */   private int sfztx;
/*  14:    */   private int kqr;
/*  15:    */   private String flowno;
/*  16:    */   private String hrmno;
/*  17:    */   private String jbdd;
/*  18:    */   private String jbrq;
/*  19:    */   private String xq;
/*  20:    */   private String dataid;
/*  21:    */   private String mainid;
/*  22:    */   private double yxgs;
/*  23:    */   private double jbxs;
/*  24:    */   private double hdgs;
/*  25:    */   private double xxsj;
/*  26:    */   
/*  27:    */   public Log getLog()
/*  28:    */   {
/*  29: 12 */     return log;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void setLog(Log log)
/*  33:    */   {
/*  34: 16 */     log = log;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void insert()
/*  38:    */     throws Exception
/*  39:    */   {
/*  40:    */     try
/*  41:    */     {
/*  42: 42 */       String sql = "INSERT INTO uf__jb_temp (hrmid,sfztx,kqr,flowno,hrmno,jbdd,jbrq,xq,dataid,mainid,yxgs,jbxs,hdgs,xxsj)";
/*  43: 43 */       sql = sql + " VALUES  (";
/*  44: 44 */       sql = sql + "'" + this.hrmid + "',";
/*  45: 45 */       sql = sql + "'" + this.sfztx + "',";
/*  46: 46 */       sql = sql + "'" + this.kqr + "',";
/*  47: 47 */       sql = sql + "'" + this.flowno + "',";
/*  48: 48 */       sql = sql + "'" + this.hrmno + "',";
/*  49: 49 */       sql = sql + "'" + this.jbdd + "',";
/*  50: 50 */       sql = sql + "'" + this.jbrq + "',";
/*  51: 51 */       sql = sql + "'" + this.xq + "',";
/*  52: 52 */       sql = sql + "'" + this.dataid + "',";
/*  53: 53 */       sql = sql + "'" + this.mainid + "',";
/*  54: 54 */       sql = sql + "'" + this.yxgs + "',";
/*  55: 55 */       sql = sql + "'" + this.jbxs + "',";
/*  56: 56 */       sql = sql + "'" + this.hdgs + "',";
/*  57: 57 */       sql = sql + "'" + this.xxsj + "'";
/*  58: 58 */       sql = sql + ")";
/*  59: 59 */       RecordSet rs = new RecordSet();
/*  60: 60 */       rs.executeSql(sql);
/*  61:    */     }
/*  62:    */     catch (Exception e)
/*  63:    */     {
/*  64: 63 */       log.error("插入j加班中间表：" + e);
/*  65: 64 */       throw e;
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public static void delete(int mainid, String jbrq)
/*  70:    */     throws Exception
/*  71:    */   {
/*  72:    */     try
/*  73:    */     {
/*  74: 74 */       String sql = "delete from uf__jb_temp where jbrq='" + jbrq + 
/*  75: 75 */         "' and mainid=" + mainid;
/*  76: 76 */       RecordSet rs = new RecordSet();
/*  77: 77 */       rs.executeSql(sql);
/*  78:    */     }
/*  79:    */     catch (Exception e)
/*  80:    */     {
/*  81: 80 */       log.error("删除加班中间表信息：" + e);
/*  82: 81 */       throw e;
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   public static void insertJb()
/*  87:    */     throws Exception
/*  88:    */   {
/*  89:    */     try
/*  90:    */     {
/*  91: 92 */       String sql = "select f.requestId,f.sqdh,f.proposer,f.jbgzdd, k.id,k.mainid,k.jbrq,k.sfztx,k.hdkssj,k.hdjssj,k.xxsj,k.yxgs,k.jbxs,k.hdgs,k.yjkssj,k.yjjssj,k.xq,k.hdzt,k.dkjl from workflow_requestbase w, formtable_main_94 f, formtable_main_94_dt1 k where w.requestid=f.requestId and w.currentnodetype=3 and k.mainid=f.id and k.hdzt=1";
/*  92: 93 */       RecordSet rs = new RecordSet();
/*  93: 94 */       RecordSet rs1 = new RecordSet();
/*  94: 95 */       rs.executeSql(sql);
/*  95: 96 */       while (rs.next())
/*  96:    */       {
/*  97: 97 */         UserInfo userInfo = new UserInfo(rs.getInt("proposer"));
/*  98:    */         
/*  99: 99 */         Jbtemp jbtemp = new Jbtemp();
/* 100:100 */         jbtemp.setDataid(String.valueOf(rs.getInt("requestId")));
/* 101:101 */         jbtemp.setFlowno(rs.getString("sqdh"));
/* 102:102 */         jbtemp.setHdgs(rs.getDouble("hdgs"));
/* 103:103 */         jbtemp.setHrmid(rs.getInt("proposer"));
/* 104:104 */         jbtemp.setHrmno(userInfo.getCode());
/* 105:105 */         log.error("加班地点：" + rs.getInt("jbgzdd"));
/* 106:106 */         int jbdd = rs.getInt("jbgzdd");
/* 107:107 */         if (jbdd == 0) {
/* 108:108 */           jbtemp.setJbdd("北京总部");
/* 109:109 */         } else if (jbdd == 1) {
/* 110:110 */           jbtemp.setJbdd("深圳分部");
/* 111:111 */         } else if (jbdd == 2) {
/* 112:112 */           jbtemp.setJbdd("东莞生产基地");
/* 113:113 */         } else if (jbdd == 3) {
/* 114:114 */           jbtemp.setJbdd("检测公司");
/* 115:    */         } else {
/* 116:116 */           jbtemp.setJbdd("其他");
/* 117:    */         }
/* 118:119 */         int requestid = rs.getInt("requestId");
/* 119:120 */         String sqlString = "select lastoperatedate from workflow_requestbase where requestid=" + 
/* 120:121 */           requestid;
/* 121:122 */         rs1.executeSql(sqlString);
/* 122:123 */         if (rs1.next()) {
/* 123:124 */           jbtemp.setJbrq(rs1.getString("lastoperatedate"));
/* 124:    */         }
/* 125:127 */         jbtemp.setJbxs(rs.getDouble("jbxs"));
/* 126:128 */         jbtemp.setMainid(String.valueOf(rs.getInt("mainid")));
/* 127:129 */         jbtemp.setSfztx(rs.getInt("sfztx"));
/* 128:130 */         jbtemp.setXq(rs.getString("xq"));
/* 129:131 */         jbtemp.setXxsj(rs.getDouble("xxsj"));
/* 130:132 */         jbtemp.setYxgs(rs.getDouble("yxgs"));
/* 131:    */         
/* 132:134 */         delete(rs.getInt("mainid"), 
/* 133:135 */           rs1.getString("lastoperatedate"));
/* 134:    */         
/* 135:137 */         jbtemp.insert();
/* 136:    */         
/* 137:139 */         JiaBan1.update(rs.getInt("mainid"), 3);
/* 138:    */       }
/* 139:    */     }
/* 140:    */     catch (Exception e)
/* 141:    */     {
/* 142:144 */       log.error("插入加班中间表信息：" + e);
/* 143:145 */       throw e;
/* 144:    */     }
/* 145:    */   }
/* 146:    */   
/* 147:    */   public int getId()
/* 148:    */   {
/* 149:150 */     return this.id;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void setId(int id)
/* 153:    */   {
/* 154:154 */     this.id = id;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public int getRequestId()
/* 158:    */   {
/* 159:158 */     return this.requestId;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setRequestId(int requestId)
/* 163:    */   {
/* 164:162 */     this.requestId = requestId;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public int getHrmid()
/* 168:    */   {
/* 169:166 */     return this.hrmid;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void setHrmid(int hrmid)
/* 173:    */   {
/* 174:170 */     this.hrmid = hrmid;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public int getSfztx()
/* 178:    */   {
/* 179:174 */     return this.sfztx;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public void setSfztx(int sfztx)
/* 183:    */   {
/* 184:178 */     this.sfztx = sfztx;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public int getKqr()
/* 188:    */   {
/* 189:182 */     return this.kqr;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public void setKqr(int kqr)
/* 193:    */   {
/* 194:186 */     this.kqr = kqr;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public String getFlowno()
/* 198:    */   {
/* 199:190 */     return this.flowno;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void setFlowno(String flowno)
/* 203:    */   {
/* 204:194 */     this.flowno = flowno;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public String getHrmno()
/* 208:    */   {
/* 209:198 */     return this.hrmno;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void setHrmno(String hrmno)
/* 213:    */   {
/* 214:202 */     this.hrmno = hrmno;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public String getJbdd()
/* 218:    */   {
/* 219:206 */     return this.jbdd;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void setJbdd(String jbdd)
/* 223:    */   {
/* 224:210 */     this.jbdd = jbdd;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public String getJbrq()
/* 228:    */   {
/* 229:214 */     return this.jbrq;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void setJbrq(String jbrq)
/* 233:    */   {
/* 234:218 */     this.jbrq = jbrq;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public String getXq()
/* 238:    */   {
/* 239:222 */     return this.xq;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void setXq(String xq)
/* 243:    */   {
/* 244:226 */     this.xq = xq;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public String getDataid()
/* 248:    */   {
/* 249:230 */     return this.dataid;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void setDataid(String dataid)
/* 253:    */   {
/* 254:234 */     this.dataid = dataid;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public String getMainid()
/* 258:    */   {
/* 259:238 */     return this.mainid;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void setMainid(String mainid)
/* 263:    */   {
/* 264:242 */     this.mainid = mainid;
/* 265:    */   }
/* 266:    */   
/* 267:    */   public double getYxgs()
/* 268:    */   {
/* 269:246 */     return this.yxgs;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public void setYxgs(double yxgs)
/* 273:    */   {
/* 274:250 */     this.yxgs = yxgs;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public double getJbxs()
/* 278:    */   {
/* 279:254 */     return this.jbxs;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void setJbxs(double jbxs)
/* 283:    */   {
/* 284:258 */     this.jbxs = jbxs;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public double getHdgs()
/* 288:    */   {
/* 289:262 */     return this.hdgs;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void setHdgs(double hdgs)
/* 293:    */   {
/* 294:266 */     this.hdgs = hdgs;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public double getXxsj()
/* 298:    */   {
/* 299:270 */     return this.xxsj;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public void setXxsj(double xxsj)
/* 303:    */   {
/* 304:274 */     this.xxsj = xxsj;
/* 305:    */   }
/* 306:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.Jbtemp
 * JD-Core Version:    0.7.0.1
 */