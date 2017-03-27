/*   1:    */ package weaver.dh.interfaces;
/*   2:    */ 
/*   3:    */ import com.weaver.formmodel.util.DateHelper;
/*   4:    */ import dinghan.workflow.action.ChangeReDelStatueAction;
/*   5:    */ import java.util.Date;
/*   6:    */ import java.util.HashMap;
/*   7:    */ import org.apache.commons.logging.Log;
/*   8:    */ import org.apache.commons.logging.LogFactory;
/*   9:    */ import org.json.JSONException;
/*  10:    */ import org.json.JSONObject;
/*  11:    */ import weaver.conn.RecordSet;
/*  12:    */ import weaver.general.Util;
/*  13:    */ import weaver.interfaces.schedule.BaseCronJob;
/*  14:    */ 
/*  15:    */ public class toU9
/*  16:    */   extends BaseCronJob
/*  17:    */ {
/*  18: 22 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/*  19:    */   private String month;
/*  20:    */   private String year;
/*  21: 25 */   private String mon = "";
/*  22: 26 */   private String account = "";
/*  23: 27 */   private int operatorid = 0;
/*  24: 28 */   int userId = 0;
/*  25: 29 */   int flag = 0;
/*  26: 30 */   JSONObject json = new JSONObject();
/*  27: 31 */   String userCode = "";
/*  28: 32 */   String userNameCN = "";
/*  29: 33 */   String resInfo = "";
/*  30: 35 */   HashMap<String, String> cus_Map = new HashMap();
/*  31: 36 */   HashMap<String, String> hz_Map = new HashMap();
/*  32: 37 */   HashMap<String, String> Map = new HashMap();
/*  33: 38 */   HashMap<String, String> curDataMap = new HashMap();
/*  34: 39 */   RecordSet rsA = new RecordSet();
/*  35: 40 */   RecordSet rs = new RecordSet();
/*  36:    */   
/*  37:    */   public String getJump()
/*  38:    */   {
/*  39: 44 */     this.log.error("U9!" + new Date() + " *** Start transport Checkout Result to ERP ***");
/*  40: 45 */     this.month = (this.year + "-" + this.month);
/*  41:    */     
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45: 50 */     execute();
/*  46:    */     try
/*  47:    */     {
/*  48: 53 */       this.json.put("flag", this.flag);
/*  49: 54 */       this.json.put("info", this.resInfo);
/*  50:    */     }
/*  51:    */     catch (JSONException e)
/*  52:    */     {
/*  53: 56 */       e.printStackTrace();
/*  54:    */     }
/*  55: 58 */     return this.json.toString();
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void execute()
/*  59:    */   {
/*  60: 63 */     this.log.error("U9! " + new Date() + " *** Execute transport Checkout Result for date in -- " + this.year + "-" + this.month + " *** ");
/*  61:    */     
/*  62: 65 */     HrmData(this.operatorid);
/*  63: 66 */     String operaCode = (String)this.Map.get(this.operatorid + "_Code");
/*  64:    */     
/*  65: 68 */     String operaU9OId = (String)this.Map.get(operaCode + "_ORG");
/*  66: 69 */     String operaNameCN = (String)this.Map.get(operaCode + "_NameCN");
/*  67: 70 */     this.Map.clear();
/*  68:    */     
/*  69: 72 */     HrmData(this.userId);
/*  70: 74 */     if (this.Map.get(this.userCode + "_NameCN") == null)
/*  71:    */     {
/*  72: 75 */       this.resInfo = (Util.null2String((String)this.Map.get(new StringBuilder(String.valueOf(this.userCode)).append("_NameCN").toString())) + "null");
/*  73: 76 */       return;
/*  74:    */     }
/*  75: 79 */     if (("".equals(this.Map.get(this.userCode + "_NameCN"))) || (!((String)this.Map.get(this.userCode + "_NameCN")).equals(this.userNameCN)))
/*  76:    */     {
/*  77: 80 */       this.resInfo = 
/*  78: 81 */         ("当前用户，工号：" + this.userCode + "，" + "在OA系统与ERP中姓名为空不一致[oa：" + this.userNameCN + "，erp：" + (String)this.Map.get(new StringBuilder(String.valueOf(this.userCode)).append("_NameCN").toString()) + "]");
/*  79: 82 */       return;
/*  80:    */     }
/*  81: 85 */     String sql = "select * from uf_kqhz where hzyf = '" + this.month + "' and gh = '" + this.userCode + "'";
/*  82:    */     
/*  83: 87 */     this.rsA.executeSql(sql);
/*  84: 88 */     while (this.rsA.next())
/*  85:    */     {
/*  86: 89 */       getData();
/*  87:    */       
/*  88:    */ 
/*  89: 92 */       String gh = (String)this.Map.get(this.userId + "_Code");
/*  90: 93 */       String U9id = Util.null2String((String)this.Map.get(gh + "_ID"));
/*  91:    */       
/*  92: 95 */       String U9orgId = Util.null2String((String)this.Map.get(gh + "_ORG"));
/*  93: 96 */       String U9Personid = Util.null2o((String)this.Map.get(gh + "_Person"));
/*  94: 97 */       String exsisid = Util.null2String((String)this.hz_Map.get(U9id));
/*  95: 98 */       this.log.error("是否已经存在：" + exsisid);
/*  96:    */       
/*  97:    */ 
/*  98:101 */       String trgs = Util.null2o(String.valueOf(this.rsA.getDouble("trgs")));
/*  99:102 */       String brj = Util.null2o(String.valueOf(this.rsA.getDouble("brj")));
/* 100:    */       
/* 101:104 */       String kgsj = Util.null2o(String.valueOf(this.rsA.getDouble("kg")));
/* 102:    */       
/* 103:106 */       String cd = Util.null2o(String.valueOf((int)this.rsA.getDouble("cd")));
/* 104:    */       
/* 105:108 */       String zaot = Util.null2o(String.valueOf((int)this.rsA.getDouble("zt")));
/* 106:    */       
/* 107:110 */       String jbgs = Util.null2o(String.valueOf(this.rsA.getDouble("jbgs")));
/* 108:    */       
/* 109:112 */       String sj = Util.null2o(String.valueOf(this.rsA.getDouble("sj")));
/* 110:113 */       String bj = Util.null2o(String.valueOf(this.rsA.getDouble("bj")));
/* 111:114 */       String nxj = Util.null2o(String.valueOf(this.rsA.getDouble("nxj")));
/* 112:115 */       String txj = Util.null2o(String.valueOf(this.rsA.getDouble("txj")));
/* 113:116 */       String hj = Util.null2o(String.valueOf(this.rsA.getDouble("hj")));
/* 114:117 */       String sangj = Util.null2o(String.valueOf(this.rsA.getDouble("sangj")));
/* 115:118 */       String cj = Util.null2o(String.valueOf(this.rsA.getDouble("cj")));
/* 116:119 */       String cjj = Util.null2o(String.valueOf(this.rsA.getDouble("cjj")));
/* 117:120 */       String lcj = Util.null2o(String.valueOf(this.rsA.getDouble("lcj")));
/* 118:121 */       String jyj = Util.null2o(String.valueOf(this.rsA.getDouble("jyj")));
/* 119:122 */       String jsj = Util.null2o(String.valueOf(this.rsA.getDouble("jsj")));
/* 120:123 */       String pcj = Util.null2o(String.valueOf(this.rsA.getDouble("pcj")));
/* 121:124 */       this.log.error("实际应出勤天数： " + this.rsA.getDouble("ycqts"));
/* 122:125 */       String ActualAttendHours = Util.null2o(String.valueOf(this.rsA.getDouble("ycqts")));
/* 123:    */       
/* 124:127 */       String grgs = Util.null2o(String.valueOf(this.rsA.getDouble("grgs")));
/* 125:    */       
/* 126:129 */       String wdkcs = Util.null2o(String.valueOf(this.rsA.getDouble("wdk")));
/* 127:    */       
/* 128:131 */       this.log.error("WWW: : " + wdkcs);
/* 129:    */       
/* 130:133 */       String exesql = "";
/* 131:134 */       String curentTime = DateHelper.getCurDateTime();
/* 132:135 */       if ("".equals(exsisid))
/* 133:    */       {
/* 134:136 */         String id = "";
/* 135:    */         
/* 136:    */ 
/* 137:139 */         RecordSet rcs = new RecordSet();
/* 138:140 */         rcs.executeSql("SELECT NEXT VALUE FOR U9idseq");
/* 139:141 */         while (rcs.next())
/* 140:    */         {
/* 141:142 */           int seq = rcs.getInt(1);
/* 142:143 */           this.log.error("序列 ：： " + seq);
/* 143:144 */           id = ((String)this.Map.get(this.userCode + "_Person")).substring(0, 3);
/* 144:145 */           id = id + this.year + this.mon + seq;
/* 145:    */         }
/* 146:147 */         this.log.error("当前日期：：" + curentTime);
/* 147:    */         
/* 148:    */ 
/* 149:    */ 
/* 150:    */ 
/* 151:    */ 
/* 152:153 */         exesql = "insert into HI_AttendResult(ID,CreatedBy,CreatedOn,ModifiedBy,ModifiedOn,SysVersion,AbsentHours,ActualAttendHours,BornLeaveHours,CreateOrg,DeathLeaveHours,EarlyLeaveTimes,Employee,OvertimeHours,OwnerOrg,PeriodNum,PeriodType,Person,PrivateLeaveHours,LateTimes,SickLeaveHours,WeddingLeaveHours,Status,WorkOrg,Year,YearLeaveHours,DescFlexField_PrivateDescSeg1,DescFlexField_PrivateDescSeg2,DescFlexField_PrivateDescSeg3,DescFlexField_PrivateDescSeg4,DescFlexField_PrivateDescSeg5,DescFlexField_PrivateDescSeg6,DescFlexField_PrivateDescSeg7,DescFlexField_PrivateDescSeg8,DescFlexField_PrivateDescSeg9,DescFlexField_PrivateDescSeg10,DescFlexField_PrivateDescSeg11,DescFlexField_PrivateDescSeg12) values('" + 
/* 153:    */         
/* 154:    */ 
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:168 */           id + "','" + operaNameCN + "','" + curentTime + "','" + operaNameCN + "','" + curentTime + "',0," + kgsj + "," + 
/* 168:169 */           ActualAttendHours + "," + cj + "," + operaU9OId + "," + sangj + "," + Integer.valueOf(zaot) + "," + U9id + "," + 
/* 169:170 */           jbgs + "," + U9orgId + "," + Integer.valueOf(this.mon) + ",0," + U9Personid + "," + sj + "," + 
/* 170:171 */           Integer.valueOf(cd) + "," + bj + "," + hj + ",2," + 
/* 171:172 */           U9orgId + "," + this.year + "," + nxj + ",'" + grgs + "','0'," + 
/* 172:173 */           "'" + wdkcs + "','" + trgs + "','" + lcj + "','0'," + 
/* 173:174 */           "'" + jyj + "','" + jsj + "','" + txj + "','" + cjj + "'," + 
/* 174:175 */           "'" + pcj + "','" + brj + "')";
/* 175:    */       }
/* 176:    */       else
/* 177:    */       {
/* 178:180 */         exesql = 
/* 179:    */         
/* 180:    */ 
/* 181:    */ 
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:    */ 
/* 188:    */ 
/* 189:    */ 
/* 190:    */ 
/* 191:    */ 
/* 192:    */ 
/* 193:195 */           "update HI_AttendResult set AbsentHours=" + kgsj + "," + "ModifiedBy='" + operaNameCN + "',ModifiedOn='" + curentTime + "'," + "ActualAttendHours=" + ActualAttendHours + "," + "BornLeaveHours=" + cj + ",CreateOrg=" + operaU9OId + ",DeathLeaveHours=" + sangj + "," + "EarlyLeaveTimes=" + Integer.valueOf(zaot) + ",Employee=" + U9id + "," + "LateTimes=" + Integer.valueOf(cd) + "," + "OvertimeHours=" + jbgs + ",OwnerOrg=" + U9orgId + ",PeriodNum=" + Integer.parseInt(this.mon) + ",PeriodType=0," + "Person=" + U9Personid + ",PrivateLeaveHours=" + sj + "," + "SickLeaveHours=" + bj + ",WeddingLeaveHours=" + hj + "," + "WorkOrg=" + U9orgId + ",Year=" + this.year + ",YearLeaveHours=" + nxj + "," + "DescFlexField_PrivateDescSeg1='" + grgs + "',DescFlexField_PrivateDescSeg2='0'," + "DescFlexField_PrivateDescSeg3='" + wdkcs + "',DescFlexField_PrivateDescSeg4='" + trgs + "',DescFlexField_PrivateDescSeg5='" + lcj + "'," + "DescFlexField_PrivateDescSeg6='0'," + "DescFlexField_PrivateDescSeg7='" + jyj + "',DescFlexField_PrivateDescSeg8='" + jsj + "',DescFlexField_PrivateDescSeg9='" + txj + "'," + "DescFlexField_PrivateDescSeg10='" + cjj + "'," + "DescFlexField_PrivateDescSeg11='" + pcj + "',DescFlexField_PrivateDescSeg12='" + brj + "' where id = '" + exsisid + "'";
/* 194:    */       }
/* 195:198 */       this.log.error("执行sql :: " + exesql);
/* 196:    */       
/* 197:200 */       this.rs.executeSql(exesql, "U9");
/* 198:    */       
/* 199:202 */       this.flag = getTransPortedInfo();
/* 200:204 */       if (this.flag > 0) {
/* 201:205 */         this.resInfo = "传输成功！";
/* 202:    */       } else {
/* 203:207 */         this.resInfo = "传输未成功！";
/* 204:    */       }
/* 205:    */     }
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void HrmData(int userId)
/* 209:    */   {
/* 210:216 */     String sqlHrm = "select Code,Name,NameCN from uf_hr_userinfo1 where Name = " + userId;
/* 211:    */     
/* 212:218 */     this.rsA.executeSql(sqlHrm);
/* 213:219 */     while (this.rsA.next())
/* 214:    */     {
/* 215:220 */       this.userCode = Util.null2String(this.rsA.getString("Code"));
/* 216:221 */       this.userNameCN = Util.null2String(this.rsA.getString("NameCN"));
/* 217:    */     }
/* 218:224 */     String sql = "select e.ID,e.EmployeeCode,e.OwnerOrg,e.Person,u.Name from CBO_EmployeeArchive e,Base_User u where e.EmployeeCode = u.Code and u.Code = '" + 
/* 219:225 */       this.userCode + "'";
/* 220:226 */     this.rsA.executeSql(sql, "U9");
/* 221:    */     
/* 222:228 */     String U9code = "";
/* 223:229 */     String U9id = "";
/* 224:230 */     String OwnerOrg = "";
/* 225:231 */     String U9NameCN = "";
/* 226:232 */     String U9PersonId = "";
/* 227:234 */     while (this.rsA.next())
/* 228:    */     {
/* 229:235 */       U9code = Util.null2String(this.rsA.getString("EmployeeCode"));
/* 230:236 */       U9id = Util.null2String(this.rsA.getString("ID"));
/* 231:237 */       this.log.error("ID,ID :: " + U9id);
/* 232:238 */       OwnerOrg = Util.null2String(this.rsA.getString("OwnerOrg"));
/* 233:239 */       U9NameCN = Util.null2String(this.rsA.getString("Name"));
/* 234:240 */       U9PersonId = Util.null2String(this.rsA.getString("Person"));
/* 235:241 */       this.Map.put(userId + "_Code", U9code);
/* 236:242 */       this.Map.put(U9code + "_ID", U9id);
/* 237:243 */       this.Map.put(U9code + "_ORG", OwnerOrg);
/* 238:244 */       this.Map.put(U9code + "_NameCN", U9NameCN);
/* 239:245 */       this.Map.put(U9code + "_Person", U9PersonId);
/* 240:    */     }
/* 241:248 */     this.log.error("U9! " + new Date() + " *** get user info : Code -- " + U9code + 
/* 242:249 */       " U9NameCN -- " + U9NameCN + 
/* 243:250 */       " OwnerOrg -- " + OwnerOrg + 
/* 244:251 */       " U9id -- " + U9id + " ***");
/* 245:    */   }
/* 246:    */   
/* 247:    */   private int getTransPortedInfo()
/* 248:    */   {
/* 249:257 */     int num = 0;
/* 250:258 */     String sql = "select h.ID,e.Name,e.EmployeeCode from HI_AttendResult h,CBO_EmployeeArchive e where h.PeriodNum = " + Integer.parseInt(this.month.substring(5, 7)) + " and h.Year = '" + this.year + "'" + 
/* 251:259 */       " and h.Person = e.Person" + 
/* 252:260 */       " and e.EmployeeCode = '" + (String)this.Map.get(new StringBuilder(String.valueOf(this.userId)).append("_Code").toString()) + "'";
/* 253:    */     
/* 254:262 */     RecordSet rs = new RecordSet();
/* 255:    */     
/* 256:264 */     rs.executeSql(sql, "U9");
/* 257:266 */     while (rs.next()) {
/* 258:267 */       num++;
/* 259:    */     }
/* 260:270 */     return num;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void getData()
/* 264:    */   {
/* 265:277 */     if (this.month.length() >= 5) {
/* 266:278 */       this.mon = this.month.substring(5, 7);
/* 267:    */     }
/* 268:280 */     String Code = (String)this.Map.get(this.userId + "_Code");
/* 269:281 */     String sql = "select * from HI_AttendResult where PeriodNum = '" + this.mon + "' and Year = '" + this.year + "'" + 
/* 270:282 */       " and Person = '" + (String)this.Map.get(new StringBuilder(String.valueOf(Code)).append("_Person").toString()) + "'";
/* 271:283 */     RecordSet rs = new RecordSet();
/* 272:284 */     rs.executeSql(sql, "U9");
/* 273:285 */     while (rs.next())
/* 274:    */     {
/* 275:286 */       String u9hrid = Util.null2String(rs.getString("Employee"));
/* 276:287 */       String id = Util.null2String(rs.getString("id"));
/* 277:288 */       this.hz_Map.put(u9hrid, id);
/* 278:    */     }
/* 279:    */   }
/* 280:    */   
/* 281:    */   public String getMonth()
/* 282:    */   {
/* 283:293 */     return this.month;
/* 284:    */   }
/* 285:    */   
/* 286:    */   public void setMonth(String month)
/* 287:    */   {
/* 288:297 */     this.month = month;
/* 289:    */   }
/* 290:    */   
/* 291:    */   public String getYear()
/* 292:    */   {
/* 293:301 */     return this.year;
/* 294:    */   }
/* 295:    */   
/* 296:    */   public void setYear(String year)
/* 297:    */   {
/* 298:305 */     this.year = year;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public String getAccount()
/* 302:    */   {
/* 303:309 */     return this.account;
/* 304:    */   }
/* 305:    */   
/* 306:    */   public void setAccount(String account)
/* 307:    */   {
/* 308:313 */     this.account = account;
/* 309:    */   }
/* 310:    */   
/* 311:    */   public int getUserId()
/* 312:    */   {
/* 313:317 */     return this.userId;
/* 314:    */   }
/* 315:    */   
/* 316:    */   public void setUserId(int userId)
/* 317:    */   {
/* 318:321 */     this.userId = userId;
/* 319:    */   }
/* 320:    */   
/* 321:    */   public int getOperatorid()
/* 322:    */   {
/* 323:325 */     return this.operatorid;
/* 324:    */   }
/* 325:    */   
/* 326:    */   public void setOperatorid(int operatorid)
/* 327:    */   {
/* 328:329 */     this.operatorid = operatorid;
/* 329:    */   }
/* 330:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     weaver.dh.interfaces.toU9
 * JD-Core Version:    0.7.0.1
 */