/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import org.apache.commons.logging.Log;
/*   4:    */ import org.apache.commons.logging.LogFactory;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class Abnormal
/*   8:    */ {
/*   9:  9 */   private static Log log = LogFactory.getLog(Abnormal.class.getName());
/*  10:    */   private int id;
/*  11:    */   private int requestId;
/*  12:    */   private int proposer;
/*  13:    */   private int yclx;
/*  14:    */   private String sqrq;
/*  15:    */   private String ycrq;
/*  16:    */   private String time1;
/*  17:    */   private String time2;
/*  18:    */   private String time3;
/*  19:    */   private String time4;
/*  20:    */   private String fj;
/*  21:    */   private int hdzt;
/*  22:    */   
/*  23:    */   public Log getLog()
/*  24:    */   {
/*  25: 12 */     return log;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void setLog(Log log)
/*  29:    */   {
/*  30: 16 */     log = log;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public Abnormal(int userid, String date, int type)
/*  34:    */   {
/*  35: 39 */     String sql = "select * from formtable_main_106 where proposer=" + 
/*  36: 40 */       userid + " and ycrq='" + date + "'";
/*  37: 41 */     if (type >= 0) {
/*  38: 42 */       sql = sql + " and type=" + type;
/*  39:    */     }
/*  40:    */     try
/*  41:    */     {
/*  42: 45 */       RecordSet rs = new RecordSet();
/*  43: 46 */       rs.executeSql(sql);
/*  44: 47 */       if (rs.next())
/*  45:    */       {
/*  46: 48 */         this.fj = rs.getString("fj");
/*  47: 49 */         this.id = rs.getInt("id");
/*  48: 50 */         this.proposer = rs.getInt("proposer");
/*  49: 51 */         this.requestId = rs.getInt("requestId");
/*  50: 52 */         this.sqrq = rs.getString("sqrq");
/*  51: 53 */         this.time1 = rs.getString("time1");
/*  52: 54 */         this.time2 = rs.getString("time2");
/*  53: 55 */         this.time3 = rs.getString("time3");
/*  54: 56 */         this.time4 = rs.getString("time4");
/*  55: 57 */         this.yclx = rs.getInt("yclx");
/*  56: 58 */         this.ycrq = rs.getString("ycrq");
/*  57: 59 */         this.hdzt = rs.getInt("hdzt");
/*  58:    */       }
/*  59:    */     }
/*  60:    */     catch (Exception e)
/*  61:    */     {
/*  62: 63 */       log.error("得到异常信息出错：" + e);
/*  63:    */     }
/*  64:    */   }
/*  65:    */   
/*  66:    */   public Abnormal(String requestid)
/*  67:    */   {
/*  68: 73 */     String sql = "select * from formtable_main_106 where requestid=" + 
/*  69: 74 */       requestid;
/*  70:    */     try
/*  71:    */     {
/*  72: 76 */       RecordSet rs = new RecordSet();
/*  73: 77 */       rs.executeSql(sql);
/*  74: 78 */       if (rs.next())
/*  75:    */       {
/*  76: 79 */         this.fj = rs.getString("fj");
/*  77: 80 */         this.id = rs.getInt("id");
/*  78: 81 */         this.proposer = rs.getInt("proposer");
/*  79: 82 */         this.requestId = rs.getInt("requestId");
/*  80: 83 */         this.sqrq = rs.getString("sqrq");
/*  81: 84 */         this.time1 = rs.getString("time1");
/*  82: 85 */         this.time2 = rs.getString("time2");
/*  83: 86 */         this.time3 = rs.getString("time3");
/*  84: 87 */         this.time4 = rs.getString("time4");
/*  85: 88 */         this.yclx = rs.getInt("yclx");
/*  86: 89 */         this.ycrq = rs.getString("ycrq");
/*  87: 90 */         this.hdzt = rs.getInt("hdzt");
/*  88:    */       }
/*  89:    */     }
/*  90:    */     catch (Exception e)
/*  91:    */     {
/*  92: 94 */       log.error("获取出差主表信息:" + e);
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   public static void update(int requestId, int type)
/*  97:    */     throws Exception
/*  98:    */   {
/*  99:    */     try
/* 100:    */     {
/* 101:107 */       String sql = "update formtable_main_106 set hdzt=" + type;
/* 102:108 */       sql = sql + " where requestId=" + requestId;
/* 103:109 */       RecordSet rs = new RecordSet();
/* 104:110 */       rs.executeSql(sql);
/* 105:    */     }
/* 106:    */     catch (Exception e)
/* 107:    */     {
/* 108:113 */       log.error("更新考勤异常出错：" + e);
/* 109:114 */       throw e;
/* 110:    */     }
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int getId()
/* 114:    */   {
/* 115:119 */     return this.id;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void setId(int id)
/* 119:    */   {
/* 120:123 */     this.id = id;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int getRequestId()
/* 124:    */   {
/* 125:127 */     return this.requestId;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void setRequestId(int requestId)
/* 129:    */   {
/* 130:131 */     this.requestId = requestId;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public int getProposer()
/* 134:    */   {
/* 135:135 */     return this.proposer;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public void setProposer(int proposer)
/* 139:    */   {
/* 140:139 */     this.proposer = proposer;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public int getYclx()
/* 144:    */   {
/* 145:143 */     return this.yclx;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void setYclx(int yclx)
/* 149:    */   {
/* 150:147 */     this.yclx = yclx;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public String getSqrq()
/* 154:    */   {
/* 155:151 */     return this.sqrq;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void setSqrq(String sqrq)
/* 159:    */   {
/* 160:155 */     this.sqrq = sqrq;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public String getYcrq()
/* 164:    */   {
/* 165:159 */     return this.ycrq;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void setYcrq(String ycrq)
/* 169:    */   {
/* 170:163 */     this.ycrq = ycrq;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public String getTime1()
/* 174:    */   {
/* 175:167 */     return this.time1;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void setTime1(String time1)
/* 179:    */   {
/* 180:171 */     this.time1 = time1;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public String getTime2()
/* 184:    */   {
/* 185:175 */     return this.time2;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void setTime2(String time2)
/* 189:    */   {
/* 190:179 */     this.time2 = time2;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public String getTime3()
/* 194:    */   {
/* 195:183 */     return this.time3;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void setTime3(String time3)
/* 199:    */   {
/* 200:187 */     this.time3 = time3;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public String getTime4()
/* 204:    */   {
/* 205:191 */     return this.time4;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void setTime4(String time4)
/* 209:    */   {
/* 210:195 */     this.time4 = time4;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public String getFj()
/* 214:    */   {
/* 215:199 */     return this.fj;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public void setFj(String fj)
/* 219:    */   {
/* 220:203 */     this.fj = fj;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public int getHdzt()
/* 224:    */   {
/* 225:207 */     return this.hdzt;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public void setHdzt(int hdzt)
/* 229:    */   {
/* 230:211 */     this.hdzt = hdzt;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public Abnormal() {}
/* 234:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.Abnormal
 * JD-Core Version:    0.7.0.1
 */