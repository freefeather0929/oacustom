/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import org.apache.commons.logging.Log;
/*   4:    */ import org.apache.commons.logging.LogFactory;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class JiaBan
/*   8:    */ {
/*   9:  9 */   private static Log log = LogFactory.getLog(JiaBan.class.getName());
/*  10:    */   private int id;
/*  11:    */   private int requestId;
/*  12:    */   private int proposer;
/*  13:    */   private int stdept2;
/*  14:    */   private int stdept1;
/*  15:    */   private int jbgzdd;
/*  16:    */   private int zjzg;
/*  17:    */   private int sjzg;
/*  18:    */   private int bzgzsj;
/*  19:    */   private int kqhdyj;
/*  20:    */   private String setdate;
/*  21:    */   private String jbsy;
/*  22:    */   private String kqlx;
/*  23:    */   private String sqdh;
/*  24:    */   private String zw;
/*  25:    */   
/*  26:    */   public Log getLog()
/*  27:    */   {
/*  28: 12 */     return log;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void setLog(Log log)
/*  32:    */   {
/*  33: 16 */     log = log;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public JiaBan(String requestid)
/*  37:    */   {
/*  38:    */     try
/*  39:    */     {
/*  40: 37 */       String sql = "select * from formtable_main_94 where requestid= " + 
/*  41: 38 */         requestid;
/*  42: 39 */       RecordSet rs = new RecordSet();
/*  43: 40 */       rs.executeSql(sql);
/*  44: 41 */       if (rs.next())
/*  45:    */       {
/*  46: 42 */         this.kqhdyj = rs.getInt("kqhdyj");
/*  47: 43 */         this.jbgzdd = rs.getInt("jbgzdd");
/*  48: 44 */         this.zjzg = rs.getInt("zjzg");
/*  49: 45 */         this.id = rs.getInt("id");
/*  50: 46 */         this.bzgzsj = rs.getInt("bzgzsj");
/*  51: 47 */         this.proposer = rs.getInt("proposer");
/*  52: 48 */         this.requestId = rs.getInt("requestId");
/*  53: 49 */         this.sqdh = rs.getString("sqdh");
/*  54: 50 */         this.setdate = rs.getString("setdate");
/*  55: 51 */         this.sjzg = rs.getInt("sjzg");
/*  56: 52 */         this.stdept1 = rs.getInt("stdept1");
/*  57: 53 */         this.stdept2 = rs.getInt("stdept2");
/*  58: 54 */         this.jbsy = rs.getString("jbsy");
/*  59: 55 */         this.kqlx = rs.getString("kqlx");
/*  60:    */       }
/*  61:    */     }
/*  62:    */     catch (Exception e)
/*  63:    */     {
/*  64: 59 */       log.error("requestid取主表信息：" + e);
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public JiaBan(int mainid)
/*  69:    */   {
/*  70:    */     try
/*  71:    */     {
/*  72: 66 */       String sql = "select * from formtable_main_94 where id= " + mainid;
/*  73: 67 */       RecordSet rs = new RecordSet();
/*  74: 68 */       rs.executeSql(sql);
/*  75: 69 */       if (rs.next())
/*  76:    */       {
/*  77: 70 */         this.kqhdyj = rs.getInt("kqhdyj");
/*  78: 71 */         this.jbgzdd = rs.getInt("jbgzdd");
/*  79: 72 */         this.zjzg = rs.getInt("zjzg");
/*  80: 73 */         this.id = rs.getInt("id");
/*  81: 74 */         this.bzgzsj = rs.getInt("bzgzsj");
/*  82: 75 */         this.proposer = rs.getInt("proposer");
/*  83: 76 */         this.requestId = rs.getInt("requestId");
/*  84: 77 */         this.sqdh = rs.getString("sqdh");
/*  85: 78 */         this.setdate = rs.getString("setdate");
/*  86: 79 */         this.sjzg = rs.getInt("sjzg");
/*  87: 80 */         this.stdept1 = rs.getInt("stdept1");
/*  88: 81 */         this.stdept2 = rs.getInt("stdept2");
/*  89: 82 */         this.jbsy = rs.getString("jbsy");
/*  90: 83 */         this.kqlx = rs.getString("kqlx");
/*  91:    */       }
/*  92:    */     }
/*  93:    */     catch (Exception e)
/*  94:    */     {
/*  95: 87 */       log.error("requestid取主表信息：" + e);
/*  96:    */     }
/*  97:    */   }
/*  98:    */   
/*  99:    */   public int getId()
/* 100:    */   {
/* 101: 92 */     return this.id;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setId(int id)
/* 105:    */   {
/* 106: 96 */     this.id = id;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public int getRequestId()
/* 110:    */   {
/* 111:100 */     return this.requestId;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setRequestId(int requestId)
/* 115:    */   {
/* 116:104 */     this.requestId = requestId;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public int getProposer()
/* 120:    */   {
/* 121:108 */     return this.proposer;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setProposer(int proposer)
/* 125:    */   {
/* 126:112 */     this.proposer = proposer;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public int getStdept2()
/* 130:    */   {
/* 131:116 */     return this.stdept2;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void setStdept2(int stdept2)
/* 135:    */   {
/* 136:120 */     this.stdept2 = stdept2;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int getStdept1()
/* 140:    */   {
/* 141:124 */     return this.stdept1;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setStdept1(int stdept1)
/* 145:    */   {
/* 146:128 */     this.stdept1 = stdept1;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public int getJbgzdd()
/* 150:    */   {
/* 151:132 */     return this.jbgzdd;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setJbgzdd(int jbgzdd)
/* 155:    */   {
/* 156:136 */     this.jbgzdd = jbgzdd;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public int getZjzg()
/* 160:    */   {
/* 161:140 */     return this.zjzg;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setZjzg(int zjzg)
/* 165:    */   {
/* 166:144 */     this.zjzg = zjzg;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public int getSjzg()
/* 170:    */   {
/* 171:148 */     return this.sjzg;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setSjzg(int sjzg)
/* 175:    */   {
/* 176:152 */     this.sjzg = sjzg;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public int getBzgzsj()
/* 180:    */   {
/* 181:156 */     return this.bzgzsj;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void setBzgzsj(int bzgzsj)
/* 185:    */   {
/* 186:160 */     this.bzgzsj = bzgzsj;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public int getKqhdyj()
/* 190:    */   {
/* 191:164 */     return this.kqhdyj;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setKqhdyj(int kqhdyj)
/* 195:    */   {
/* 196:168 */     this.kqhdyj = kqhdyj;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public String getSetdate()
/* 200:    */   {
/* 201:172 */     return this.setdate;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setSetdate(String setdate)
/* 205:    */   {
/* 206:176 */     this.setdate = setdate;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public String getJbsy()
/* 210:    */   {
/* 211:180 */     return this.jbsy;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setJbsy(String jbsy)
/* 215:    */   {
/* 216:184 */     this.jbsy = jbsy;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public String getKqlx()
/* 220:    */   {
/* 221:188 */     return this.kqlx;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setKqlx(String kqlx)
/* 225:    */   {
/* 226:192 */     this.kqlx = kqlx;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public String getSqdh()
/* 230:    */   {
/* 231:196 */     return this.sqdh;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void setSqdh(String sqdh)
/* 235:    */   {
/* 236:200 */     this.sqdh = sqdh;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public String getZw()
/* 240:    */   {
/* 241:204 */     return this.zw;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setZw(String zw)
/* 245:    */   {
/* 246:208 */     this.zw = zw;
/* 247:    */   }
/* 248:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.JiaBan
 * JD-Core Version:    0.7.0.1
 */