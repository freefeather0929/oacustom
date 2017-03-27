/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.Date;
/*   4:    */ import org.apache.commons.logging.Log;
/*   5:    */ import org.apache.commons.logging.LogFactory;
/*   6:    */ import weaver.conn.RecordSet;
/*   7:    */ 
/*   8:    */ public class WaiChu
/*   9:    */ {
/*  10: 11 */   private static Log log = LogFactory.getLog(WaiChu.class.getName());
/*  11:    */   private int id;
/*  12:    */   private int requestId;
/*  13:    */   private int proposer;
/*  14:    */   private int stdept1;
/*  15:    */   private int stdept2;
/*  16:    */   private int wcrq;
/*  17:    */   private int stdept3;
/*  18:    */   private String appnom;
/*  19:    */   private String setdate;
/*  20:    */   private String lxdh;
/*  21:    */   private String kssj;
/*  22:    */   private String jssj;
/*  23:    */   private String xq;
/*  24:    */   private String zw1;
/*  25:    */   private String ggsy;
/*  26:    */   private int hdzt;
/*  27:    */   
/*  28:    */   public Log getLog()
/*  29:    */   {
/*  30: 14 */     return log;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void setLog(Log log)
/*  34:    */   {
/*  35: 18 */     log = log;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static void checkList(int id)
/*  39:    */     throws Exception
/*  40:    */   {
/*  41: 45 */     log.error("外出明细核定：");
/*  42: 46 */     String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
/*  43: 47 */     String sql = "";
/*  44: 48 */     if (id == 0)
/*  45:    */     {
/*  46: 49 */       sql = "SELECT * FROM formtable_main_95 WHERE wcrq<'" + nowDate;
/*  47: 50 */       sql = sql + "' and hdzt is null ORDER BY id";
/*  48:    */     }
/*  49:    */     else
/*  50:    */     {
/*  51: 52 */       sql = "SELECT * FROM formtable_main_95 WHERE id=" + id;
/*  52:    */     }
/*  53: 55 */     RecordSet wcrs = new RecordSet();
/*  54: 56 */     RecordSet rs = new RecordSet();
/*  55: 57 */     rs.executeSql(sql);
/*  56: 58 */     while (rs.next())
/*  57:    */     {
/*  58: 59 */       UserInfo userInfo = new UserInfo(rs.getInt("proposer"));
/*  59: 60 */       Wctemp wctemp = new Wctemp();
/*  60: 61 */       wctemp.setFlowno(rs.getString("appnom"));
/*  61: 62 */       wctemp.setHdjssj(rs.getString("jssj"));
/*  62: 63 */       wctemp.setHdkssj(rs.getString("kssj"));
/*  63: 64 */       wctemp.setHrmid(rs.getInt("proposer"));
/*  64: 65 */       wctemp.setHrmno(userInfo.getCode());
/*  65:    */       
/*  66: 67 */       wctemp.setMainid(String.valueOf(rs.getInputStream("id")));
/*  67: 68 */       wctemp.setWcrq(rs.getString("wcrq"));
/*  68: 69 */       wctemp.setXq(rs.getString("xq"));
/*  69: 70 */       wctemp.setDataid(rs.getString("requestId"));
/*  70: 71 */       wctemp.setMainid(String.valueOf(rs.getInt("id")));
/*  71:    */       
/*  72: 73 */       Wctemp.delete(rs.getInt("id"), rs.getString("wcrq"));
/*  73:    */       
/*  74: 75 */       wctemp.insert();
/*  75:    */       
/*  76: 77 */       String wcsql = "update formtable_main_95 set hdzt=1 where id=" + 
/*  77: 78 */         rs.getInt("id");
/*  78: 79 */       wcrs.executeSql(wcsql);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getWcrq()
/*  83:    */   {
/*  84: 84 */     return this.wcrq;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void setWcrq(int wcrq)
/*  88:    */   {
/*  89: 88 */     this.wcrq = wcrq;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getId()
/*  93:    */   {
/*  94: 92 */     return this.id;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void setId(int id)
/*  98:    */   {
/*  99: 96 */     this.id = id;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public int getRequestId()
/* 103:    */   {
/* 104:100 */     return this.requestId;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setRequestId(int requestId)
/* 108:    */   {
/* 109:104 */     this.requestId = requestId;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public int getProposer()
/* 113:    */   {
/* 114:108 */     return this.proposer;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setProposer(int proposer)
/* 118:    */   {
/* 119:112 */     this.proposer = proposer;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public int getStdept1()
/* 123:    */   {
/* 124:116 */     return this.stdept1;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setStdept1(int stdept1)
/* 128:    */   {
/* 129:120 */     this.stdept1 = stdept1;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public int getStdept2()
/* 133:    */   {
/* 134:124 */     return this.stdept2;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void setStdept2(int stdept2)
/* 138:    */   {
/* 139:128 */     this.stdept2 = stdept2;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public int getStdept3()
/* 143:    */   {
/* 144:132 */     return this.stdept3;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public void setStdept3(int stdept3)
/* 148:    */   {
/* 149:136 */     this.stdept3 = stdept3;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public String getAppnom()
/* 153:    */   {
/* 154:140 */     return this.appnom;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void setAppnom(String appnom)
/* 158:    */   {
/* 159:144 */     this.appnom = appnom;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public String getSetdate()
/* 163:    */   {
/* 164:148 */     return this.setdate;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public void setSetdate(String setdate)
/* 168:    */   {
/* 169:152 */     this.setdate = setdate;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public String getLxdh()
/* 173:    */   {
/* 174:156 */     return this.lxdh;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public void setLxdh(String lxdh)
/* 178:    */   {
/* 179:160 */     this.lxdh = lxdh;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public String getKssj()
/* 183:    */   {
/* 184:164 */     return this.kssj;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setKssj(String kssj)
/* 188:    */   {
/* 189:168 */     this.kssj = kssj;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public String getJssj()
/* 193:    */   {
/* 194:172 */     return this.jssj;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void setJssj(String jssj)
/* 198:    */   {
/* 199:176 */     this.jssj = jssj;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public String getXq()
/* 203:    */   {
/* 204:180 */     return this.xq;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void setXq(String xq)
/* 208:    */   {
/* 209:184 */     this.xq = xq;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public String getZw1()
/* 213:    */   {
/* 214:188 */     return this.zw1;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public void setZw1(String zw1)
/* 218:    */   {
/* 219:192 */     this.zw1 = zw1;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public String getGgsy()
/* 223:    */   {
/* 224:196 */     return this.ggsy;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void setGgsy(String ggsy)
/* 228:    */   {
/* 229:200 */     this.ggsy = ggsy;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public int getHdzt()
/* 233:    */   {
/* 234:204 */     return this.hdzt;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public void setHdzt(int hdzt)
/* 238:    */   {
/* 239:208 */     this.hdzt = hdzt;
/* 240:    */   }
/* 241:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.WaiChu
 * JD-Core Version:    0.7.0.1
 */