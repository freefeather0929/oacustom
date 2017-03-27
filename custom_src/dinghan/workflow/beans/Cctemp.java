/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import org.apache.commons.logging.Log;
/*   5:    */ import org.apache.commons.logging.LogFactory;
/*   6:    */ import weaver.conn.RecordSet;
/*   7:    */ 
/*   8:    */ public class Cctemp
/*   9:    */ {
/*  10: 11 */   private static Log log = LogFactory.getLog(Cctemp.class.getName());
/*  11:    */   private int id;
/*  12:    */   private int requestId;
/*  13:    */   private int hrmid;
/*  14:    */   private int kqr;
/*  15:    */   private String flowno;
/*  16:    */   private String hrmno;
/*  17:    */   private String ccrq;
/*  18:    */   private String hdkssj;
/*  19:    */   private String hdjssj;
/*  20:    */   private String dataid;
/*  21:    */   private String xq;
/*  22:    */   private String mainid;
/*  23:    */   
/*  24:    */   public Log getLog()
/*  25:    */   {
/*  26: 14 */     return log;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void setLog(Log log)
/*  30:    */   {
/*  31: 18 */     log = log;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void insert()
/*  35:    */     throws Exception
/*  36:    */   {
/*  37:    */     try
/*  38:    */     {
/*  39: 40 */       String sql = "INSERT INTO uf__cc_temp (hrmid,kqr,flowno,hrmno,ccrq,hdkssj,hdjssj,dataid,xq,mainid)";
/*  40: 41 */       sql = sql + " VALUES  (";
/*  41: 42 */       sql = sql + "'" + this.hrmid + "',";
/*  42: 43 */       sql = sql + "'" + this.kqr + "',";
/*  43: 44 */       sql = sql + "'" + this.flowno + "',";
/*  44: 45 */       sql = sql + "'" + this.hrmno + "',";
/*  45: 46 */       sql = sql + "'" + this.ccrq + "',";
/*  46: 47 */       sql = sql + "'" + this.hdkssj + "',";
/*  47: 48 */       sql = sql + "'" + this.hdjssj + "',";
/*  48: 49 */       sql = sql + "'" + this.dataid + "',";
/*  49: 50 */       sql = sql + "'" + this.xq + "',";
/*  50: 51 */       sql = sql + "'" + this.mainid + "'";
/*  51: 52 */       sql = sql + ")";
/*  52: 53 */       RecordSet rs = new RecordSet();
/*  53: 54 */       rs.executeSql(sql);
/*  54:    */     }
/*  55:    */     catch (Exception e)
/*  56:    */     {
/*  57: 57 */       log.error("插入出差中间表：" + e);
/*  58: 58 */       throw e;
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static void delete(int mainid, String ccrq)
/*  63:    */     throws Exception
/*  64:    */   {
/*  65:    */     try
/*  66:    */     {
/*  67: 68 */       String sql = "delete from uf__cc_temp where ccrq='" + ccrq + 
/*  68: 69 */         "' and mainid=" + mainid;
/*  69: 70 */       RecordSet rs = new RecordSet();
/*  70: 71 */       rs.executeSql(sql);
/*  71:    */     }
/*  72:    */     catch (Exception e)
/*  73:    */     {
/*  74: 74 */       log.error("删除出差中间表信息：" + e);
/*  75: 75 */       throw e;
/*  76:    */     }
/*  77:    */   }
/*  78:    */   
/*  79:    */   public static void delete(int mainid)
/*  80:    */     throws Exception
/*  81:    */   {
/*  82:    */     try
/*  83:    */     {
/*  84: 85 */       String sql = "delete from uf__cc_temp where  mainid=" + mainid;
/*  85: 86 */       RecordSet rs = new RecordSet();
/*  86: 87 */       rs.executeSql(sql);
/*  87:    */     }
/*  88:    */     catch (Exception e)
/*  89:    */     {
/*  90: 90 */       log.error("删除出差中间表信息：" + e);
/*  91: 91 */       throw e;
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   public String toString()
/*  96:    */   {
/*  97:103 */     return 
/*  98:    */     
/*  99:    */ 
/* 100:    */ 
/* 101:107 */       "Cctemp [id=" + this.id + ", requestId=" + this.requestId + ", hrmid=" + this.hrmid + ", kqr=" + this.kqr + ", flowno=" + this.flowno + ", hrmno=" + this.hrmno + ", ccrq=" + this.ccrq + ", hdkssj=" + this.hdkssj + ", hdjssj=" + this.hdjssj + ", dataid=" + this.dataid + ", xq=" + this.xq + ", mainid=" + this.mainid + "]";
/* 102:    */   }
/* 103:    */   
/* 104:    */   public static ArrayList<Cctemp> queryList(int hrmid, String ccrq)
/* 105:    */     throws Exception
/* 106:    */   {
/* 107:112 */     ArrayList<Cctemp> aCC = new ArrayList();
/* 108:    */     try
/* 109:    */     {
/* 110:114 */       String sql = "SELECT * FROM uf__cc_temp Where hrmid='" + hrmid + 
/* 111:115 */         "' and ccrq='" + ccrq + "'";
/* 112:    */       
/* 113:117 */       RecordSet rs = new RecordSet();
/* 114:118 */       rs.executeSql(sql);
/* 115:120 */       while (rs.next())
/* 116:    */       {
/* 117:121 */         Cctemp cctemp = new Cctemp();
/* 118:122 */         cctemp.setHdkssj(rs.getString("hdkssj"));
/* 119:123 */         cctemp.setHdjssj(rs.getString("hdjssj"));
/* 120:124 */         aCC.add(cctemp);
/* 121:    */       }
/* 122:    */     }
/* 123:    */     catch (Exception e)
/* 124:    */     {
/* 125:128 */       log.error("得到外出中间表信息出错：" + e);
/* 126:129 */       throw e;
/* 127:    */     }
/* 128:131 */     return aCC;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public int getId()
/* 132:    */   {
/* 133:135 */     return this.id;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void setId(int id)
/* 137:    */   {
/* 138:139 */     this.id = id;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public int getRequestId()
/* 142:    */   {
/* 143:143 */     return this.requestId;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void setRequestId(int requestId)
/* 147:    */   {
/* 148:147 */     this.requestId = requestId;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public int getHrmid()
/* 152:    */   {
/* 153:151 */     return this.hrmid;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void setHrmid(int hrmid)
/* 157:    */   {
/* 158:155 */     this.hrmid = hrmid;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public int getKqr()
/* 162:    */   {
/* 163:159 */     return this.kqr;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setKqr(int kqr)
/* 167:    */   {
/* 168:163 */     this.kqr = kqr;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public String getFlowno()
/* 172:    */   {
/* 173:167 */     return this.flowno;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void setFlowno(String flowno)
/* 177:    */   {
/* 178:171 */     this.flowno = flowno;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public String getHrmno()
/* 182:    */   {
/* 183:175 */     return this.hrmno;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public void setHrmno(String hrmno)
/* 187:    */   {
/* 188:179 */     this.hrmno = hrmno;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public String getCcrq()
/* 192:    */   {
/* 193:183 */     return this.ccrq;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setCcrq(String ccrq)
/* 197:    */   {
/* 198:187 */     this.ccrq = ccrq;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public String getHdkssj()
/* 202:    */   {
/* 203:191 */     return this.hdkssj;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public void setHdkssj(String hdkssj)
/* 207:    */   {
/* 208:195 */     this.hdkssj = hdkssj;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public String getHdjssj()
/* 212:    */   {
/* 213:199 */     return this.hdjssj;
/* 214:    */   }
/* 215:    */   
/* 216:    */   public void setHdjssj(String hdjssj)
/* 217:    */   {
/* 218:203 */     this.hdjssj = hdjssj;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public String getDataid()
/* 222:    */   {
/* 223:207 */     return this.dataid;
/* 224:    */   }
/* 225:    */   
/* 226:    */   public void setDataid(String dataid)
/* 227:    */   {
/* 228:211 */     this.dataid = dataid;
/* 229:    */   }
/* 230:    */   
/* 231:    */   public String getXq()
/* 232:    */   {
/* 233:215 */     return this.xq;
/* 234:    */   }
/* 235:    */   
/* 236:    */   public void setXq(String xq)
/* 237:    */   {
/* 238:219 */     this.xq = xq;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public String getMainid()
/* 242:    */   {
/* 243:223 */     return this.mainid;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public void setMainid(String mainid)
/* 247:    */   {
/* 248:227 */     this.mainid = mainid;
/* 249:    */   }
/* 250:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.Cctemp
 * JD-Core Version:    0.7.0.1
 */