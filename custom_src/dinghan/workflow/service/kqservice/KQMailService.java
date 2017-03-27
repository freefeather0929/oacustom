/*   1:    */ package dinghan.workflow.service.kqservice;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.beans.KQMail;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.Map;
/*   7:    */ import javax.mail.MessagingException;
/*   8:    */ import weaver.conn.RecordSet;
/*   9:    */ 
/*  10:    */ public class KQMailService
/*  11:    */ {
/*  12:    */   private String formAdress;
/*  13:    */   private String formName;
/*  14:    */   private String fromPwd;
/*  15:    */   private String tip;
/*  16:    */   private String userCode;
/*  17:    */   private String monthStr;
/*  18: 24 */   Map<String, String> mailInfoMap = new HashMap();
/*  19:    */   
/*  20:    */   public void executeMail()
/*  21:    */   {
/*  22: 32 */     createKQCollectMailInfo(this.userCode, this.monthStr);
/*  23:    */     
/*  24: 34 */     KQMail mail = new KQMail();
/*  25:    */     
/*  26: 36 */     System.out.println("mail from address ：" + (String)this.mailInfoMap.get("mailto"));
/*  27: 37 */     System.out.println("mail from User Name ：" + (String)this.mailInfoMap.get("mailtoName"));
/*  28:    */     
/*  29:    */ 
/*  30:    */ 
/*  31: 41 */     mail.setHost("10.10.66.222");
/*  32: 42 */     mail.setContent(this.tip + "<br/><br/>" + (String)this.mailInfoMap.get("content"));
/*  33: 43 */     mail.setContentType("html");
/*  34: 44 */     mail.setFrom(this.formAdress);
/*  35: 45 */     mail.setFromName(this.formName);
/*  36: 46 */     mail.setFromPwd(this.fromPwd);
/*  37:    */     
/*  38: 48 */     mail.setValidate(false);
/*  39: 49 */     mail.setRecipients(((String)this.mailInfoMap.get("mailto")).split(","));
/*  40: 50 */     mail.setRecipientsName(((String)this.mailInfoMap.get("mailtoName")).split(","));
/*  41: 51 */     mail.setSubject((String)this.mailInfoMap.get("subject"));
/*  42:    */     try
/*  43:    */     {
/*  44: 55 */       mail.sendMail();
/*  45:    */     }
/*  46:    */     catch (MessagingException e)
/*  47:    */     {
/*  48: 57 */       e.printStackTrace();
/*  49:    */     }
/*  50:    */     catch (Exception e)
/*  51:    */     {
/*  52: 59 */       e.printStackTrace();
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   private void createKQCollectMailInfo(String userCode, String month)
/*  57:    */   {
/*  58: 71 */     RecordSet hrrs = new RecordSet();
/*  59:    */     
/*  60: 73 */     String _userCode = userCode;
/*  61: 74 */     String _month = month;
/*  62:    */     
/*  63: 76 */     String mailto = "";
/*  64: 77 */     String mailtoName = "";
/*  65:    */     
/*  66: 79 */     String Sql = "select h.Code,h.NameCN,h.Mail,h.Company,h.KaoQinType,h.DeptOneNameText,h.DeptTwoNameText,h.DeptThreeNameText,h.InCompany,h.Post,k.hzyf,k.ycqts,k.jbgs,k.jbztx,k.sj,k.bj,k.hj,k.sangj,k.cj,k.cjj,k.lcj,k.jyj,k.jsj,k.grgs,k.kg,k.trgs,k.wdk,k.brj,k.nxj,k.txj,k.synx,k.sytx from uf_kqhz k,uf_hr_userinfo1 h where h.Code = k.gh and h.Code = '" + 
/*  67:    */     
/*  68: 81 */       _userCode + "' and k.hzyf = '" + _month + "'";
/*  69:    */     
/*  70: 83 */     System.out.println("Sql::" + Sql);
/*  71:    */     
/*  72: 85 */     hrrs.executeSql(Sql);
/*  73:    */     
/*  74: 87 */     this.mailInfoMap.clear();
/*  75:    */     
/*  76: 89 */     String mailContent = "<table align=\"center\" border=\"1\" cellpadding=\"5\" style=\"width:600px;border-collapse:collapse;font-size:12px;\">";
/*  77: 91 */     while (hrrs.next())
/*  78:    */     {
/*  79: 93 */       mailto = hrrs.getString("Mail");
/*  80: 94 */       System.out.println("mail address :: " + mailto);
/*  81: 95 */       mailtoName = hrrs.getString("NameCN");
/*  82:    */       
/*  83: 97 */       mailContent = mailContent + "<tr>";
/*  84: 98 */       mailContent = mailContent + "<td colspan=\"4\" style=\"background-color: #1e90ff\"><span style=\"display: block; border-width: 0;font-size:14px;font-weight:bold;text-align: center\">月考勤汇总统计表</span></td>";
/*  85:    */       
/*  86:    */ 
/*  87:101 */       mailContent = mailContent + "</tr>";
/*  88:    */       
/*  89:103 */       mailContent = mailContent + "<tr>";
/*  90:104 */       mailContent = mailContent + "<td style=\"width:20%;\">工号：</td>";
/*  91:105 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("Code") + "</td>";
/*  92:106 */       mailContent = mailContent + "<td style=\"width:20%;\">姓名：</td>";
/*  93:107 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("NameCN") + "</td>";
/*  94:108 */       mailContent = mailContent + "</tr>";
/*  95:    */       
/*  96:110 */       mailContent = mailContent + "<tr>";
/*  97:111 */       mailContent = mailContent + "<td style=\"width:20%;\">所属帐套：</td>";
/*  98:112 */       mailContent = mailContent + "<td style=\"width:30%;\">" + getAccountName(hrrs.getString("Company")) + "</td>";
/*  99:113 */       mailContent = mailContent + "<td style=\"width:20%;\">考勤类型：</td>";
/* 100:114 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("") + "</td>";
/* 101:115 */       mailContent = mailContent + "</tr>";
/* 102:    */       
/* 103:117 */       mailContent = mailContent + "<tr>";
/* 104:118 */       mailContent = mailContent + "<td style=\"width:20%;\">任职状态：</td>";
/* 105:120 */       if (hrrs.getString("InCompany") == "0") {
/* 106:121 */         mailContent = mailContent + "<td style=\"width:30%;\">在职</td>";
/* 107:    */       } else {
/* 108:123 */         mailContent = mailContent + "<td style=\"width:30%;\">离职</td>";
/* 109:    */       }
/* 110:126 */       mailContent = mailContent + "<td style=\"width:20%;\">岗位：</td>";
/* 111:127 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("Post") + "</td>";
/* 112:128 */       mailContent = mailContent + "</tr>";
/* 113:    */       
/* 114:130 */       mailContent = mailContent + "<tr>";
/* 115:131 */       mailContent = mailContent + "<td style=\"width:20%;\">考勤月份：</td>";
/* 116:132 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("hzyf") + "</td>";
/* 117:133 */       mailContent = mailContent + "<td style=\"width:20%;\">应出勤天数：</td>";
/* 118:134 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("ycqts") + "</td>";
/* 119:135 */       mailContent = mailContent + "</tr>";
/* 120:    */       
/* 121:137 */       mailContent = mailContent + "<tr>";
/* 122:138 */       mailContent = mailContent + "<td style=\"width:20%;\">加班工时：</td>";
/* 123:139 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jbgs") + "</td>";
/* 124:140 */       mailContent = mailContent + "<td style=\"width:20%;\">加班转调休：</td>";
/* 125:141 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jbztx") + "</td>";
/* 126:142 */       mailContent = mailContent + "</tr>";
/* 127:    */       
/* 128:144 */       mailContent = mailContent + "<tr>";
/* 129:145 */       mailContent = mailContent + "<td style=\"width:20%;\">事假工时：</td>";
/* 130:146 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("sj") + "</td>";
/* 131:147 */       mailContent = mailContent + "<td style=\"width:20%;\">病假工时：</td>";
/* 132:148 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("bj") + "</td>";
/* 133:149 */       mailContent = mailContent + "</tr>";
/* 134:    */       
/* 135:151 */       mailContent = mailContent + "<tr>";
/* 136:152 */       mailContent = mailContent + "<td style=\"width:20%;\">婚假工时：</td>";
/* 137:153 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("hj") + "</td>";
/* 138:154 */       mailContent = mailContent + "<td style=\"width:20%;\">丧假工时：</td>";
/* 139:155 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("sangj") + "</td>";
/* 140:156 */       mailContent = mailContent + "</tr>";
/* 141:    */       
/* 142:158 */       mailContent = mailContent + "<tr>";
/* 143:159 */       mailContent = mailContent + "<td style=\"width:20%;\">产假工时：</td>";
/* 144:160 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("cj") + "</td>";
/* 145:161 */       mailContent = mailContent + "<td style=\"width:20%;\">产检假工时：</td>";
/* 146:162 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("cjj") + "</td>";
/* 147:163 */       mailContent = mailContent + "</tr>";
/* 148:    */       
/* 149:165 */       mailContent = mailContent + "<tr>";
/* 150:166 */       mailContent = mailContent + "<td style=\"width:20%;\">流产假工时：</td>";
/* 151:167 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("lcj") + "</td>";
/* 152:168 */       mailContent = mailContent + "<td style=\"width:20%;\">节育假工时：</td>";
/* 153:169 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jyj") + "</td>";
/* 154:170 */       mailContent = mailContent + "</tr>";
/* 155:    */       
/* 156:172 */       mailContent = mailContent + "<tr>";
/* 157:173 */       mailContent = mailContent + "<td style=\"width:20%;\">计划生育假工时：</td>";
/* 158:174 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jsj") + "</td>";
/* 159:175 */       mailContent = mailContent + "<td style=\"width:20%;\">个人工伤假工时：</td>";
/* 160:176 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("grgs") + "</td>";
/* 161:177 */       mailContent = mailContent + "</tr>";
/* 162:    */       
/* 163:179 */       mailContent = mailContent + "<tr>";
/* 164:180 */       mailContent = mailContent + "<td style=\"width:20%;\">旷工工时：</td>";
/* 165:181 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("kg") + "</td>";
/* 166:182 */       mailContent = mailContent + "<td style=\"width:20%;\">他人工伤假工时：</td>";
/* 167:183 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("trgs") + "</td>";
/* 168:184 */       mailContent = mailContent + "</tr>";
/* 169:    */       
/* 170:186 */       mailContent = mailContent + "<tr>";
/* 171:187 */       mailContent = mailContent + "<td style=\"width:20%;\">忘打卡次数：</td>";
/* 172:188 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("wdk") + "</td>";
/* 173:189 */       mailContent = mailContent + "<td style=\"width:20%;\">迟到次数：</td>";
/* 174:190 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("cd") + "</td>";
/* 175:191 */       mailContent = mailContent + "</tr>";
/* 176:    */       
/* 177:193 */       mailContent = mailContent + "<tr>";
/* 178:194 */       mailContent = mailContent + "<td style=\"width:20%;\">早退次数：</td>";
/* 179:195 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("wdk") + "</td>";
/* 180:196 */       mailContent = mailContent + "<td style=\"width:20%;\">哺乳假工时：</td>";
/* 181:197 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("brj") + "</td>";
/* 182:198 */       mailContent = mailContent + "</tr>";
/* 183:    */       
/* 184:200 */       mailContent = mailContent + "<tr>";
/* 185:201 */       mailContent = mailContent + "<td style=\"width:20%;\">年休假工时：</td>";
/* 186:202 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("nxj") + "</td>";
/* 187:203 */       mailContent = mailContent + "<td style=\"width:20%;\">调休假工时：</td>";
/* 188:204 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("txj") + "</td>";
/* 189:205 */       mailContent = mailContent + "</tr>";
/* 190:    */       
/* 191:207 */       mailContent = mailContent + "<tr>";
/* 192:208 */       mailContent = mailContent + "<td style=\"width:20%;\">剩余年休假：</td>";
/* 193:209 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("synx") + "</td>";
/* 194:210 */       mailContent = mailContent + "<td style=\"width:20%;\">剩余调休假：</td>";
/* 195:211 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("sytx") + "</td>";
/* 196:212 */       mailContent = mailContent + "</tr>";
/* 197:    */       
/* 198:214 */       mailContent = mailContent + "<tr>";
/* 199:215 */       mailContent = mailContent + "<td colspan=\"4\" style=\"text-align:center\">";
/* 200:216 */       mailContent = mailContent + "<a target=\"_blank\" style=\"color:#ff0000\" href=\"http://oa.dinghantech.com/formmode/search/CustomSearchBySimple.jsp?customid=79\">";
/* 201:217 */       mailContent = mailContent + "点击查看详细考勤明细</a>";
/* 202:218 */       mailContent = mailContent + "</td>";
/* 203:219 */       mailContent = mailContent + "</tr>";
/* 204:    */     }
/* 205:222 */     mailContent = mailContent + "</table>";
/* 206:    */     
/* 207:224 */     this.mailInfoMap.put("mailto", mailto);
/* 208:225 */     this.mailInfoMap.put("mailtoName", mailtoName);
/* 209:226 */     this.mailInfoMap.put("content", mailContent);
/* 210:227 */     this.mailInfoMap.put("subject", _month + "月度考勤汇总，请核对！如果异常，请及时反馈给考勤员。");
/* 211:    */   }
/* 212:    */   
/* 213:    */   private String getAccountName(String id)
/* 214:    */   {
/* 215:232 */     RecordSet rs = new RecordSet();
/* 216:233 */     String sql = "select company from uf_hr_company where id = " + id;
/* 217:234 */     rs.executeSql(sql);
/* 218:235 */     String companyName = "";
/* 219:236 */     while (rs.next()) {
/* 220:237 */       companyName = rs.getString("company");
/* 221:    */     }
/* 222:239 */     return companyName;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public String getFormAdress()
/* 226:    */   {
/* 227:243 */     return this.formAdress;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public void setFormAdress(String formAdress)
/* 231:    */   {
/* 232:247 */     this.formAdress = formAdress;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public String getFormName()
/* 236:    */   {
/* 237:251 */     return this.formName;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public void setFormName(String formName)
/* 241:    */   {
/* 242:255 */     this.formName = formName;
/* 243:    */   }
/* 244:    */   
/* 245:    */   public String getFromPwd()
/* 246:    */   {
/* 247:259 */     return this.fromPwd;
/* 248:    */   }
/* 249:    */   
/* 250:    */   public void setFromPwd(String fromPwd)
/* 251:    */   {
/* 252:263 */     this.fromPwd = fromPwd;
/* 253:    */   }
/* 254:    */   
/* 255:    */   public String getTip()
/* 256:    */   {
/* 257:267 */     return this.tip;
/* 258:    */   }
/* 259:    */   
/* 260:    */   public void setTip(String tip)
/* 261:    */   {
/* 262:271 */     this.tip = tip;
/* 263:    */   }
/* 264:    */   
/* 265:    */   public String getUserCode()
/* 266:    */   {
/* 267:275 */     return this.userCode;
/* 268:    */   }
/* 269:    */   
/* 270:    */   public void setUserCode(String userCode)
/* 271:    */   {
/* 272:279 */     this.userCode = userCode;
/* 273:    */   }
/* 274:    */   
/* 275:    */   public String getMonthStr()
/* 276:    */   {
/* 277:283 */     return this.monthStr;
/* 278:    */   }
/* 279:    */   
/* 280:    */   public void setMonthStr(String monthStr)
/* 281:    */   {
/* 282:287 */     this.monthStr = monthStr;
/* 283:    */   }
/* 284:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.service.kqservice.KQMailService
 * JD-Core Version:    0.7.0.1
 */