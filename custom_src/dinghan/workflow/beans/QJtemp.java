/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import org.apache.commons.logging.Log;
/*   4:    */ import org.apache.commons.logging.LogFactory;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class QJtemp
/*   8:    */ {
/*   9:  9 */   private static Log log = LogFactory.getLog(QJtemp.class.getName());
/*  10:    */   private int id;
/*  11:    */   private int hrmid;
/*  12:    */   private int requestId;
/*  13:    */   private int qjlx;
/*  14:    */   private int kqr;
/*  15:    */   private String flowno;
/*  16:    */   private String hrmno;
/*  17:    */   private String qjrq;
/*  18:    */   private String xq;
/*  19:    */   private String hdkssj;
/*  20:    */   private String hdjssj;
/*  21:    */   private String hdgs;
/*  22:    */   private String hdyf;
/*  23:    */   private String dataid;
/*  24:    */   private String mainid;
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
/*  36:    */   public void insert()
/*  37:    */     throws Exception
/*  38:    */   {
/*  39:    */     try
/*  40:    */     {
/*  41: 41 */       String sql = "INSERT INTO uf__qj_temp (hrmid,qjlx,kqr,flowno,hrmno,qjrq,xq,hdkssj,hdjssj,hdgs,hdyf,dataid,mainid)";
/*  42: 42 */       sql = sql + " VALUES  (";
/*  43: 43 */       sql = sql + "'" + this.hrmid + "',";
/*  44: 44 */       sql = sql + "'" + this.qjlx + "',";
/*  45: 45 */       sql = sql + "'" + this.kqr + "',";
/*  46: 46 */       sql = sql + "'" + this.flowno + "',";
/*  47: 47 */       sql = sql + "'" + this.hrmno + "',";
/*  48: 48 */       sql = sql + "'" + this.qjrq + "',";
/*  49: 49 */       sql = sql + "'" + this.xq + "',";
/*  50: 50 */       sql = sql + "'" + this.hdkssj + "',";
/*  51: 51 */       sql = sql + "'" + this.hdjssj + "',";
/*  52: 52 */       sql = sql + "'" + this.hdgs + "',";
/*  53: 53 */       sql = sql + "'" + this.hdyf + "',";
/*  54: 54 */       sql = sql + "'" + this.dataid + "',";
/*  55: 55 */       sql = sql + "'" + this.mainid + "'";
/*  56: 56 */       sql = sql + ")";
/*  57: 57 */       RecordSet rs = new RecordSet();
/*  58: 58 */       rs.executeSql(sql);
/*  59:    */     }
/*  60:    */     catch (Exception e)
/*  61:    */     {
/*  62: 61 */       log.error("插入请假中间表：" + e);
/*  63: 62 */       throw e;
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67:    */   public static void delete(int mainid, String qjrq)
/*  68:    */     throws Exception
/*  69:    */   {
/*  70:    */     try
/*  71:    */     {
/*  72: 72 */       String sql = "delete from uf__qj_temp where qjrq='" + qjrq + 
/*  73: 73 */         "' and mainid=" + mainid;
/*  74: 74 */       RecordSet rs = new RecordSet();
/*  75: 75 */       rs.executeSql(sql);
/*  76:    */     }
/*  77:    */     catch (Exception e)
/*  78:    */     {
/*  79: 78 */       log.error("删除请假中间表信息：" + e);
/*  80: 79 */       throw e;
/*  81:    */     }
/*  82:    */   }
/*  83:    */   
/*  84:    */   public int getId()
/*  85:    */   {
/*  86: 84 */     return this.id;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void setId(int id)
/*  90:    */   {
/*  91: 88 */     this.id = id;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public int getHrmid()
/*  95:    */   {
/*  96: 92 */     return this.hrmid;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void setHrmid(int hrmid)
/* 100:    */   {
/* 101: 96 */     this.hrmid = hrmid;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public int getRequestId()
/* 105:    */   {
/* 106:100 */     return this.requestId;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void setRequestId(int requestId)
/* 110:    */   {
/* 111:104 */     this.requestId = requestId;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public int getQjlx()
/* 115:    */   {
/* 116:108 */     return this.qjlx;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void setQjlx(int qjlx)
/* 120:    */   {
/* 121:112 */     this.qjlx = qjlx;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public int getKqr()
/* 125:    */   {
/* 126:116 */     return this.kqr;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void setKqr(int kqr)
/* 130:    */   {
/* 131:120 */     this.kqr = kqr;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public String getFlowno()
/* 135:    */   {
/* 136:124 */     return this.flowno;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void setFlowno(String flowno)
/* 140:    */   {
/* 141:128 */     this.flowno = flowno;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public String getHrmno()
/* 145:    */   {
/* 146:132 */     return this.hrmno;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void setHrmno(String hrmno)
/* 150:    */   {
/* 151:136 */     this.hrmno = hrmno;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public String getQjrq()
/* 155:    */   {
/* 156:140 */     return this.qjrq;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void setQjrq(String qjrq)
/* 160:    */   {
/* 161:144 */     this.qjrq = qjrq;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public String getXq()
/* 165:    */   {
/* 166:148 */     return this.xq;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void setXq(String xq)
/* 170:    */   {
/* 171:152 */     this.xq = xq;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public String getHdkssj()
/* 175:    */   {
/* 176:156 */     return this.hdkssj;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void setHdkssj(String hdkssj)
/* 180:    */   {
/* 181:160 */     this.hdkssj = hdkssj;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public String getHdjssj()
/* 185:    */   {
/* 186:164 */     return this.hdjssj;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void setHdjssj(String hdjssj)
/* 190:    */   {
/* 191:168 */     this.hdjssj = hdjssj;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public String getHdgs()
/* 195:    */   {
/* 196:172 */     return this.hdgs;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void setHdgs(String hdgs)
/* 200:    */   {
/* 201:176 */     this.hdgs = hdgs;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public String getHdyf()
/* 205:    */   {
/* 206:180 */     return this.hdyf;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void setHdyf(String hdyf)
/* 210:    */   {
/* 211:184 */     this.hdyf = hdyf;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public String getDataid()
/* 215:    */   {
/* 216:188 */     return this.dataid;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public void setDataid(String dataid)
/* 220:    */   {
/* 221:192 */     this.dataid = dataid;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public String getMainid()
/* 225:    */   {
/* 226:196 */     return this.mainid;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public void setMainid(String mainid)
/* 230:    */   {
/* 231:200 */     this.mainid = mainid;
/* 232:    */   }
/* 233:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.QJtemp
 * JD-Core Version:    0.7.0.1
 */