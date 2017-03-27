/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import org.apache.commons.logging.Log;
/*   5:    */ import org.apache.commons.logging.LogFactory;
/*   6:    */ import weaver.conn.RecordSet;
/*   7:    */ 
/*   8:    */ public class Wctemp
/*   9:    */ {
/*  10: 11 */   private static Log log = LogFactory.getLog(Wctemp.class.getName());
/*  11:    */   private int id;
/*  12:    */   private int requestId;
/*  13:    */   private int hrmid;
/*  14:    */   private int kqr;
/*  15:    */   private String flowno;
/*  16:    */   private String hrmno;
/*  17:    */   private String wcrq;
/*  18:    */   private String xq;
/*  19:    */   private String hdkssj;
/*  20:    */   private String hdjssj;
/*  21:    */   private String dataid;
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
/*  39: 40 */       String sql = "INSERT INTO uf__wc_temp (hrmid,kqr,flowno,hrmno,wcrq,xq,hdkssj,hdjssj,dataid,mainid)";
/*  40: 41 */       sql = sql + " VALUES  (";
/*  41: 42 */       sql = sql + "'" + this.hrmid + "',";
/*  42: 43 */       sql = sql + "'" + this.kqr + "',";
/*  43: 44 */       sql = sql + "'" + this.flowno + "',";
/*  44: 45 */       sql = sql + "'" + this.hrmno + "',";
/*  45: 46 */       sql = sql + "'" + this.wcrq + "',";
/*  46: 47 */       sql = sql + "'" + this.xq + "',";
/*  47: 48 */       sql = sql + "'" + this.hdkssj + "',";
/*  48: 49 */       sql = sql + "'" + this.hdjssj + "',";
/*  49: 50 */       sql = sql + "'" + this.dataid + "',";
/*  50: 51 */       sql = sql + "'" + this.mainid + "'";
/*  51: 52 */       sql = sql + ")";
/*  52: 53 */       RecordSet rs = new RecordSet();
/*  53: 54 */       rs.executeSql(sql);
/*  54:    */     }
/*  55:    */     catch (Exception e)
/*  56:    */     {
/*  57: 57 */       log.error("插入外出中间表：" + e);
/*  58: 58 */       throw e;
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static void delete(int mainid, String wcrq)
/*  63:    */     throws Exception
/*  64:    */   {
/*  65:    */     try
/*  66:    */     {
/*  67: 68 */       String sql = "delete from uf__wc_temp where wcrq='" + wcrq + 
/*  68: 69 */         "' and mainid=" + mainid;
/*  69: 70 */       RecordSet rs = new RecordSet();
/*  70: 71 */       rs.executeSql(sql);
/*  71:    */     }
/*  72:    */     catch (Exception e)
/*  73:    */     {
/*  74: 74 */       log.error("删除请假中间表信息：" + e);
/*  75: 75 */       throw e;
/*  76:    */     }
/*  77:    */   }
/*  78:    */   
/*  79:    */   public static ArrayList<Wctemp> queryList(int hrmid, String wcrq)
/*  80:    */     throws Exception
/*  81:    */   {
/*  82: 87 */     ArrayList<Wctemp> aWC = new ArrayList();
/*  83:    */     try
/*  84:    */     {
/*  85: 89 */       String sql = "SELECT * FROM uf__wc_temp Where hrmid='" + hrmid + 
/*  86: 90 */         "' and wcrq='" + wcrq + "'";
/*  87:    */       
/*  88: 92 */       RecordSet rs = new RecordSet();
/*  89: 93 */       rs.executeSql(sql);
/*  90: 95 */       while (rs.next())
/*  91:    */       {
/*  92: 96 */         Wctemp wctemp = new Wctemp();
/*  93: 97 */         wctemp.setHdkssj(rs.getString("hdkssj"));
/*  94: 98 */         wctemp.setHdjssj(rs.getString("hdjssj"));
/*  95: 99 */         aWC.add(wctemp);
/*  96:    */       }
/*  97:    */     }
/*  98:    */     catch (Exception e)
/*  99:    */     {
/* 100:103 */       log.error("得到外出中间表信息出错：" + e);
/* 101:104 */       throw e;
/* 102:    */     }
/* 103:106 */     return aWC;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public int getId()
/* 107:    */   {
/* 108:110 */     return this.id;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void setId(int id)
/* 112:    */   {
/* 113:114 */     this.id = id;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public int getRequestId()
/* 117:    */   {
/* 118:118 */     return this.requestId;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void setRequestId(int requestId)
/* 122:    */   {
/* 123:122 */     this.requestId = requestId;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public int getHrmid()
/* 127:    */   {
/* 128:126 */     return this.hrmid;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void setHrmid(int hrmid)
/* 132:    */   {
/* 133:130 */     this.hrmid = hrmid;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public int getKqr()
/* 137:    */   {
/* 138:134 */     return this.kqr;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void setKqr(int kqr)
/* 142:    */   {
/* 143:138 */     this.kqr = kqr;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public String getFlowno()
/* 147:    */   {
/* 148:142 */     return this.flowno;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public void setFlowno(String flowno)
/* 152:    */   {
/* 153:146 */     this.flowno = flowno;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public String getHrmno()
/* 157:    */   {
/* 158:150 */     return this.hrmno;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void setHrmno(String hrmno)
/* 162:    */   {
/* 163:154 */     this.hrmno = hrmno;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public String getWcrq()
/* 167:    */   {
/* 168:158 */     return this.wcrq;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void setWcrq(String wcrq)
/* 172:    */   {
/* 173:162 */     this.wcrq = wcrq;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public String getXq()
/* 177:    */   {
/* 178:166 */     return this.xq;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void setXq(String xq)
/* 182:    */   {
/* 183:170 */     this.xq = xq;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public String getHdkssj()
/* 187:    */   {
/* 188:174 */     return this.hdkssj;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public void setHdkssj(String hdkssj)
/* 192:    */   {
/* 193:178 */     this.hdkssj = hdkssj;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public String getHdjssj()
/* 197:    */   {
/* 198:182 */     return this.hdjssj;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public void setHdjssj(String hdjssj)
/* 202:    */   {
/* 203:186 */     this.hdjssj = hdjssj;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public String getDataid()
/* 207:    */   {
/* 208:190 */     return this.dataid;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void setDataid(String dataid)
/* 212:    */   {
/* 213:194 */     this.dataid = dataid;
/* 214:    */   }
/* 215:    */   
/* 216:    */   public String getMainid()
/* 217:    */   {
/* 218:198 */     return this.mainid;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public void setMainid(String mainid)
/* 222:    */   {
/* 223:202 */     this.mainid = mainid;
/* 224:    */   }
/* 225:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.Wctemp
 * JD-Core Version:    0.7.0.1
 */