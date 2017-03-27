/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import org.apache.commons.logging.Log;
/*   4:    */ import org.apache.commons.logging.LogFactory;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class QingJia0
/*   8:    */ {
/*   9:  9 */   private Log log = LogFactory.getLog(QingJia0.class.getName());
/*  10:    */   private int id;
/*  11:    */   private int requestId;
/*  12:    */   private String appnom;
/*  13:    */   private int proposer;
/*  14:    */   private String setdate;
/*  15:    */   private int stdept1;
/*  16:    */   private int stdept2;
/*  17:    */   private int bzgzsj;
/*  18:    */   private String lxdh;
/*  19:    */   private int zw;
/*  20:    */   private String yjqjts;
/*  21:    */   private int zjzg;
/*  22:    */   private int sjzg;
/*  23:    */   private int zgfz;
/*  24:    */   private int rlzyzj;
/*  25:    */   private String hdyj;
/*  26:    */   private String gh;
/*  27:    */   
/*  28:    */   public Log getLog()
/*  29:    */   {
/*  30: 12 */     return this.log;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void setLog(Log log)
/*  34:    */   {
/*  35: 16 */     this.log = log;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public QingJia0() {}
/*  39:    */   
/*  40:    */   public QingJia0(String requestid)
/*  41:    */   {
/*  42:    */     try
/*  43:    */     {
/*  44: 43 */       String sql = "select * from formtable_main_89 where requestid= " + 
/*  45: 44 */         requestid;
/*  46: 45 */       RecordSet rs = new RecordSet();
/*  47: 46 */       rs.executeSql(sql);
/*  48: 47 */       if (rs.next())
/*  49:    */       {
/*  50: 48 */         this.appnom = rs.getString("appnom");
/*  51: 49 */         this.bzgzsj = rs.getInt("bzgzsj");
/*  52: 50 */         this.gh = rs.getString("gh");
/*  53: 51 */         this.hdyj = rs.getString("hdyj");
/*  54: 52 */         this.id = rs.getInt("id");
/*  55: 53 */         this.lxdh = rs.getString("lxdh");
/*  56: 54 */         this.proposer = rs.getInt("proposer");
/*  57: 55 */         this.requestId = rs.getInt("requestId");
/*  58: 56 */         this.rlzyzj = rs.getInt("rlzyzj");
/*  59: 57 */         this.setdate = rs.getString("setdate");
/*  60: 58 */         this.sjzg = rs.getInt("sjzg");
/*  61: 59 */         this.stdept1 = rs.getInt("stdept1");
/*  62: 60 */         this.stdept2 = rs.getInt("stdept2");
/*  63: 61 */         this.yjqjts = rs.getString("yjqjts");
/*  64: 62 */         this.zgfz = rs.getInt("zgfz");
/*  65: 63 */         this.zjzg = rs.getInt("zjzg");
/*  66: 64 */         this.zw = rs.getInt("zw");
/*  67:    */       }
/*  68:    */     }
/*  69:    */     catch (Exception e)
/*  70:    */     {
/*  71: 68 */       this.log.error("requestid取主表信息：" + e);
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public QingJia0(int mainid)
/*  76:    */   {
/*  77:    */     try
/*  78:    */     {
/*  79: 75 */       String sql = "select * from formtable_main_89 where id= " + mainid;
/*  80: 76 */       RecordSet rs = new RecordSet();
/*  81: 77 */       rs.executeSql(sql);
/*  82: 78 */       if (rs.next())
/*  83:    */       {
/*  84: 79 */         this.appnom = rs.getString("appnom");
/*  85: 80 */         this.bzgzsj = rs.getInt("bzgzsj");
/*  86: 81 */         this.gh = rs.getString("gh");
/*  87: 82 */         this.hdyj = rs.getString("hdyj");
/*  88: 83 */         this.id = rs.getInt("id");
/*  89: 84 */         this.lxdh = rs.getString("lxdh");
/*  90: 85 */         this.proposer = rs.getInt("proposer");
/*  91: 86 */         this.requestId = rs.getInt("requestId");
/*  92: 87 */         this.rlzyzj = rs.getInt("rlzyzj");
/*  93: 88 */         this.setdate = rs.getString("setdate");
/*  94: 89 */         this.sjzg = rs.getInt("sjzg");
/*  95: 90 */         this.stdept1 = rs.getInt("stdept1");
/*  96: 91 */         this.stdept2 = rs.getInt("stdept2");
/*  97: 92 */         this.yjqjts = rs.getString("yjqjts");
/*  98: 93 */         this.zgfz = rs.getInt("zgfz");
/*  99: 94 */         this.zjzg = rs.getInt("zjzg");
/* 100: 95 */         this.zw = rs.getInt("zw");
/* 101:    */       }
/* 102:    */     }
/* 103:    */     catch (Exception e)
/* 104:    */     {
/* 105: 99 */       this.log.error("requestid取主表信息：" + e);
/* 106:    */     }
/* 107:    */   }
/* 108:    */   
/* 109:    */   public int getId()
/* 110:    */   {
/* 111:104 */     return this.id;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setId(int id)
/* 115:    */   {
/* 116:108 */     this.id = id;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public int getRequestId()
/* 120:    */   {
/* 121:112 */     return this.requestId;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setRequestId(int requestId)
/* 125:    */   {
/* 126:116 */     this.requestId = requestId;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public String getAppnom()
/* 130:    */   {
/* 131:120 */     return this.appnom;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void setAppnom(String appnom)
/* 135:    */   {
/* 136:124 */     this.appnom = appnom;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int getProposer()
/* 140:    */   {
/* 141:128 */     return this.proposer;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setProposer(int proposer)
/* 145:    */   {
/* 146:132 */     this.proposer = proposer;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public String getSetdate()
/* 150:    */   {
/* 151:136 */     return this.setdate;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setSetdate(String setdate)
/* 155:    */   {
/* 156:140 */     this.setdate = setdate;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public int getStdept1()
/* 160:    */   {
/* 161:144 */     return this.stdept1;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setStdept1(int stdept1)
/* 165:    */   {
/* 166:148 */     this.stdept1 = stdept1;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public int getStdept2()
/* 170:    */   {
/* 171:152 */     return this.stdept2;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setStdept2(int stdept2)
/* 175:    */   {
/* 176:156 */     this.stdept2 = stdept2;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public int getBzgzsj()
/* 180:    */   {
/* 181:160 */     return this.bzgzsj;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void setBzgzsj(int bzgzsj)
/* 185:    */   {
/* 186:164 */     this.bzgzsj = bzgzsj;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public String getLxdh()
/* 190:    */   {
/* 191:168 */     return this.lxdh;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setLxdh(String lxdh)
/* 195:    */   {
/* 196:172 */     this.lxdh = lxdh;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public int getZw()
/* 200:    */   {
/* 201:176 */     return this.zw;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setZw(int zw)
/* 205:    */   {
/* 206:180 */     this.zw = zw;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public String getYjqjts()
/* 210:    */   {
/* 211:184 */     return this.yjqjts;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setYjqjts(String yjqjts)
/* 215:    */   {
/* 216:188 */     this.yjqjts = yjqjts;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public int getZjzg()
/* 220:    */   {
/* 221:192 */     return this.zjzg;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setZjzg(int zjzg)
/* 225:    */   {
/* 226:196 */     this.zjzg = zjzg;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public int getSjzg()
/* 230:    */   {
/* 231:200 */     return this.sjzg;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void setSjzg(int sjzg)
/* 235:    */   {
/* 236:204 */     this.sjzg = sjzg;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public int getZgfz()
/* 240:    */   {
/* 241:208 */     return this.zgfz;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setZgfz(int zgfz)
/* 245:    */   {
/* 246:212 */     this.zgfz = zgfz;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public int getRlzyzj()
/* 250:    */   {
/* 251:216 */     return this.rlzyzj;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void setRlzyzj(int rlzyzj)
/* 255:    */   {
/* 256:220 */     this.rlzyzj = rlzyzj;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public String getHdyj()
/* 260:    */   {
/* 261:224 */     return this.hdyj;
/* 262:    */   }
/* 263:    */   
/* 264:    */   public void setHdyj(String hdyj)
/* 265:    */   {
/* 266:228 */     this.hdyj = hdyj;
/* 267:    */   }
/* 268:    */   
/* 269:    */   public String getGh()
/* 270:    */   {
/* 271:232 */     return this.gh;
/* 272:    */   }
/* 273:    */   
/* 274:    */   public void setGh(String gh)
/* 275:    */   {
/* 276:236 */     this.gh = gh;
/* 277:    */   }
/* 278:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.QingJia0
 * JD-Core Version:    0.7.0.1
 */