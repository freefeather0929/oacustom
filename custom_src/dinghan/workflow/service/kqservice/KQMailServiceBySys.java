/*   1:    */ package dinghan.workflow.service.kqservice;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.Map;
/*   6:    */ import weaver.conn.RecordSet;
/*   7:    */ import weaver.email.EmailWorkRunnable;
/*   8:    */ 
/*   9:    */ public class KQMailServiceBySys
/*  10:    */ {
/*  11:    */   private String tip;
/*  12:    */   private String userCode;
/*  13:    */   private String monthStr;
/*  14: 28 */   Map<String, String> mailInfoMap = new HashMap();
/*  15:    */   
/*  16:    */   public void executeMail()
/*  17:    */   {
/*  18: 36 */     createKQCollectMailInfo(this.userCode, this.monthStr);
/*  19:    */     
/*  20: 38 */     System.out.println("mail from address ：" + (String)this.mailInfoMap.get("mailto"));
/*  21: 39 */     System.out.println("mail from User Name ：" + (String)this.mailInfoMap.get("mailtoName"));
/*  22:    */     
/*  23: 41 */     String subject = (String)this.mailInfoMap.get("subject");
/*  24: 42 */     String content = "<br/>" + this.tip + "<br/><br/>" + (String)this.mailInfoMap.get("content");
/*  25:    */     
/*  26: 44 */     new Thread(new EmailWorkRunnable((String)this.mailInfoMap.get("mailto") + ",", subject, content)).start();
/*  27:    */   }
/*  28:    */   
/*  29:    */   private void createKQCollectMailInfo(String userCode, String month)
/*  30:    */   {
/*  31: 78 */     RecordSet hrrs = new RecordSet();
/*  32:    */     
/*  33: 80 */     String _userCode = userCode;
/*  34: 81 */     String _month = month;
/*  35:    */     
/*  36: 83 */     String mailto = "";
/*  37: 84 */     String mailtoName = "";
/*  38:    */     
/*  39: 86 */     String Sql = "select h.Code,h.NameCN,h.Mail,h.Company,h.KaoQinType,h.DeptOneNameText,h.DeptTwoNameText,h.DeptThreeNameText,h.InCompany,h.Post,k.hzyf,k.ycqts,k.jbgs,k.jbztx,k.sj,k.bj,k.hj,k.sangj,k.cj,k.cjj,k.lcj,k.jyj,k.jsj,k.grgs,k.kg,k.trgs,k.wdk,k.cd,k.zt,k.brj,k.nxj,k.txj,k.synx,k.sytx from uf_kqhz k,uf_hr_userinfo1 h where h.Code = k.gh and h.Code = '" + 
/*  40:    */     
/*  41: 88 */       _userCode + "' and k.hzyf = '" + _month + "'";
/*  42:    */     
/*  43: 90 */     System.out.println("Sql::" + Sql);
/*  44:    */     
/*  45: 92 */     hrrs.executeSql(Sql);
/*  46:    */     
/*  47: 94 */     this.mailInfoMap.clear();
/*  48:    */     
/*  49: 96 */     String mailContent = "<table align=\"center\" border=\"1\" cellpadding=\"5\" style=\"width:600px;border-collapse:collapse;font-size:12px;\">";
/*  50: 98 */     while (hrrs.next())
/*  51:    */     {
/*  52:100 */       mailto = hrrs.getString("Mail");
/*  53:101 */       System.out.println("mail address :: " + mailto);
/*  54:102 */       mailtoName = hrrs.getString("NameCN");
/*  55:    */       
/*  56:104 */       mailContent = mailContent + "<tr>";
/*  57:105 */       mailContent = mailContent + "<td colspan=\"4\" style=\"background-color: #1e90ff\"><span style=\"display: block; border-width: 0;font-size:14px;font-weight:bold;text-align:center;color:#ffffff\">月考勤汇总统计表（系统邮件，请勿回复！）</span></td>";
/*  58:    */       
/*  59:    */ 
/*  60:108 */       mailContent = mailContent + "</tr>";
/*  61:    */       
/*  62:110 */       mailContent = mailContent + "<tr>";
/*  63:111 */       mailContent = mailContent + "<td style=\"width:20%;\">工号：</td>";
/*  64:112 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("Code") + "</td>";
/*  65:113 */       mailContent = mailContent + "<td style=\"width:20%;\">姓名：</td>";
/*  66:114 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("NameCN") + "</td>";
/*  67:115 */       mailContent = mailContent + "</tr>";
/*  68:    */       
/*  69:117 */       mailContent = mailContent + "<tr>";
/*  70:118 */       mailContent = mailContent + "<td style=\"width:20%;\">所属帐套：</td>";
/*  71:119 */       mailContent = mailContent + "<td style=\"width:30%;\">" + getAccountName(hrrs.getString("Company")) + "</td>";
/*  72:120 */       mailContent = mailContent + "<td style=\"width:20%;\">考勤类型：</td>";
/*  73:121 */       mailContent = mailContent + "<td style=\"width:30%;\">" + getKaoQinType(Integer.parseInt(hrrs.getString("KaoQinType"))) + "</td>";
/*  74:122 */       mailContent = mailContent + "</tr>";
/*  75:    */       
/*  76:124 */       mailContent = mailContent + "<tr>";
/*  77:125 */       mailContent = mailContent + "<td style=\"width:20%;\">任职状态：</td>";
/*  78:127 */       if ("0".equals(hrrs.getString("InCompany"))) {
/*  79:128 */         mailContent = mailContent + "<td style=\"width:30%;\">在职</td>";
/*  80:    */       } else {
/*  81:130 */         mailContent = mailContent + "<td style=\"width:30%;\">离职</td>";
/*  82:    */       }
/*  83:133 */       mailContent = mailContent + "<td style=\"width:20%;\">岗位：</td>";
/*  84:134 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("Post") + "</td>";
/*  85:135 */       mailContent = mailContent + "</tr>";
/*  86:    */       
/*  87:137 */       mailContent = mailContent + "<tr>";
/*  88:138 */       mailContent = mailContent + "<td style=\"width:20%;\">考勤月份：</td>";
/*  89:139 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getString("hzyf") + "</td>";
/*  90:140 */       mailContent = mailContent + "<td style=\"width:20%;\">应出勤天数：</td>";
/*  91:141 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("ycqts") + "</td>";
/*  92:142 */       mailContent = mailContent + "</tr>";
/*  93:    */       
/*  94:144 */       mailContent = mailContent + "<tr>";
/*  95:145 */       mailContent = mailContent + "<td style=\"width:20%;\">加班工时：</td>";
/*  96:146 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jbgs") + "</td>";
/*  97:147 */       mailContent = mailContent + "<td style=\"width:20%;\">加班转调休：</td>";
/*  98:148 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jbztx") + "</td>";
/*  99:149 */       mailContent = mailContent + "</tr>";
/* 100:    */       
/* 101:151 */       mailContent = mailContent + "<tr>";
/* 102:152 */       mailContent = mailContent + "<td style=\"width:20%;\">事假工时：</td>";
/* 103:153 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("sj") + "</td>";
/* 104:154 */       mailContent = mailContent + "<td style=\"width:20%;\">病假工时：</td>";
/* 105:155 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("bj") + "</td>";
/* 106:156 */       mailContent = mailContent + "</tr>";
/* 107:    */       
/* 108:158 */       mailContent = mailContent + "<tr>";
/* 109:159 */       mailContent = mailContent + "<td style=\"width:20%;\">婚假工时：</td>";
/* 110:160 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("hj") + "</td>";
/* 111:161 */       mailContent = mailContent + "<td style=\"width:20%;\">丧假工时：</td>";
/* 112:162 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("sangj") + "</td>";
/* 113:163 */       mailContent = mailContent + "</tr>";
/* 114:    */       
/* 115:165 */       mailContent = mailContent + "<tr>";
/* 116:166 */       mailContent = mailContent + "<td style=\"width:20%;\">产假工时：</td>";
/* 117:167 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("cj") + "</td>";
/* 118:168 */       mailContent = mailContent + "<td style=\"width:20%;\">产检假工时：</td>";
/* 119:169 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("cjj") + "</td>";
/* 120:170 */       mailContent = mailContent + "</tr>";
/* 121:    */       
/* 122:172 */       mailContent = mailContent + "<tr>";
/* 123:173 */       mailContent = mailContent + "<td style=\"width:20%;\">流产假工时：</td>";
/* 124:174 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("lcj") + "</td>";
/* 125:175 */       mailContent = mailContent + "<td style=\"width:20%;\">节育假工时：</td>";
/* 126:176 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jyj") + "</td>";
/* 127:177 */       mailContent = mailContent + "</tr>";
/* 128:    */       
/* 129:179 */       mailContent = mailContent + "<tr>";
/* 130:180 */       mailContent = mailContent + "<td style=\"width:20%;\">计划生育假工时：</td>";
/* 131:181 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("jsj") + "</td>";
/* 132:182 */       mailContent = mailContent + "<td style=\"width:20%;\">个人工伤假工时：</td>";
/* 133:183 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("grgs") + "</td>";
/* 134:184 */       mailContent = mailContent + "</tr>";
/* 135:    */       
/* 136:186 */       mailContent = mailContent + "<tr>";
/* 137:187 */       mailContent = mailContent + "<td style=\"width:20%;\">旷工工时：</td>";
/* 138:188 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("kg") + "</td>";
/* 139:189 */       mailContent = mailContent + "<td style=\"width:20%;\">他人工伤假工时：</td>";
/* 140:190 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("trgs") + "</td>";
/* 141:191 */       mailContent = mailContent + "</tr>";
/* 142:    */       
/* 143:193 */       mailContent = mailContent + "<tr>";
/* 144:194 */       mailContent = mailContent + "<td style=\"width:20%;\">忘打卡次数：</td>";
/* 145:195 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("wdk") + "</td>";
/* 146:196 */       mailContent = mailContent + "<td style=\"width:20%;\">迟到次数：</td>";
/* 147:197 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("cd") + "</td>";
/* 148:198 */       mailContent = mailContent + "</tr>";
/* 149:    */       
/* 150:200 */       mailContent = mailContent + "<tr>";
/* 151:201 */       mailContent = mailContent + "<td style=\"width:20%;\">早退次数：</td>";
/* 152:202 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("zt") + "</td>";
/* 153:203 */       mailContent = mailContent + "<td style=\"width:20%;\">哺乳假工时：</td>";
/* 154:204 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("brj") + "</td>";
/* 155:205 */       mailContent = mailContent + "</tr>";
/* 156:    */       
/* 157:207 */       mailContent = mailContent + "<tr>";
/* 158:208 */       mailContent = mailContent + "<td style=\"width:20%;\">年休假工时：</td>";
/* 159:209 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("nxj") + "</td>";
/* 160:210 */       mailContent = mailContent + "<td style=\"width:20%;\">调休假工时：</td>";
/* 161:211 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("txj") + "</td>";
/* 162:212 */       mailContent = mailContent + "</tr>";
/* 163:    */       
/* 164:214 */       mailContent = mailContent + "<tr>";
/* 165:215 */       mailContent = mailContent + "<td style=\"width:20%;\">剩余年休假：</td>";
/* 166:216 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("synx") + "</td>";
/* 167:217 */       mailContent = mailContent + "<td style=\"width:20%;\">剩余调休假：</td>";
/* 168:218 */       mailContent = mailContent + "<td style=\"width:30%;\">" + hrrs.getDouble("sytx") + "</td>";
/* 169:219 */       mailContent = mailContent + "</tr>";
/* 170:    */       
/* 171:221 */       mailContent = mailContent + "<tr>";
/* 172:222 */       mailContent = mailContent + "<td colspan=\"4\" style=\"text-align:center\">";
/* 173:223 */       mailContent = mailContent + "<a target=\"_blank\" style=\"color:#ff0000\" href=\"http://oa.dinghantech.com/login/Login.jsp?gopage=/formmode/search/CustomSearchBySimple.jsp?customid=79\">";
/* 174:224 */       mailContent = mailContent + "点击查看详细考勤明细</a>";
/* 175:225 */       mailContent = mailContent + "</td>";
/* 176:226 */       mailContent = mailContent + "</tr>";
/* 177:    */     }
/* 178:230 */     mailContent = mailContent + "</table>";
/* 179:    */     
/* 180:232 */     this.mailInfoMap.put("mailto", mailto);
/* 181:233 */     this.mailInfoMap.put("mailtoName", mailtoName);
/* 182:234 */     this.mailInfoMap.put("content", mailContent);
/* 183:235 */     this.mailInfoMap.put("subject", _month + "月度考勤汇总，请核对！如果异常，请及时反馈给考勤员。");
/* 184:    */   }
/* 185:    */   
/* 186:    */   private String getAccountName(String id)
/* 187:    */   {
/* 188:240 */     RecordSet rs = new RecordSet();
/* 189:241 */     String sql = "select company from uf_hr_company where id = " + id;
/* 190:242 */     rs.executeSql(sql);
/* 191:243 */     String companyName = "";
/* 192:244 */     while (rs.next()) {
/* 193:245 */       companyName = rs.getString("company");
/* 194:    */     }
/* 195:247 */     return companyName;
/* 196:    */   }
/* 197:    */   
/* 198:    */   private String getKaoQinType(int id)
/* 199:    */   {
/* 200:252 */     String type = "";
/* 201:253 */     switch (id)
/* 202:    */     {
/* 203:    */     case 0: 
/* 204:255 */       type = "非弹性制";
/* 205:256 */       break;
/* 206:    */     case 1: 
/* 207:258 */       type = "弹性制";
/* 208:259 */       break;
/* 209:    */     case 2: 
/* 210:261 */       type = "免打卡";
/* 211:262 */       break;
/* 212:    */     case 3: 
/* 213:264 */       type = "长期驻外";
/* 214:    */     }
/* 215:268 */     return type;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public String getTip()
/* 219:    */   {
/* 220:298 */     return this.tip;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void setTip(String tip)
/* 224:    */   {
/* 225:302 */     this.tip = tip;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public String getUserCode()
/* 229:    */   {
/* 230:306 */     return this.userCode;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public void setUserCode(String userCode)
/* 234:    */   {
/* 235:310 */     this.userCode = userCode;
/* 236:    */   }
/* 237:    */   
/* 238:    */   public String getMonthStr()
/* 239:    */   {
/* 240:314 */     return this.monthStr;
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void setMonthStr(String monthStr)
/* 244:    */   {
/* 245:318 */     this.monthStr = monthStr;
/* 246:    */   }
/* 247:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.service.kqservice.KQMailServiceBySys
 * JD-Core Version:    0.7.0.1
 */