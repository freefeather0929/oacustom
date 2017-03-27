/*   1:    */ package dinghan.workflow.test;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.action.ChangeReDelStatueAction;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.List;
/*   7:    */ import java.util.Map;
/*   8:    */ import org.apache.commons.logging.Log;
/*   9:    */ import org.apache.commons.logging.LogFactory;
/*  10:    */ import weaver.conn.RecordSet;
/*  11:    */ import weaver.general.BaseBean;
/*  12:    */ 
/*  13:    */ public class MailTest
/*  14:    */   extends BaseBean
/*  15:    */ {
/*  16: 17 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/*  17: 19 */   private RecordSet hrrs = new RecordSet();
/*  18:    */   
/*  19:    */   public List<Map<String, Object>> getUserIdsInAccount(String accountId)
/*  20:    */   {
/*  21: 24 */     List<Map<String, Object>> userList = new ArrayList();
/*  22:    */     
/*  23: 26 */     String hrSql = "select * from uf_acounts where id = " + accountId;
/*  24:    */     
/*  25: 28 */     this.hrrs.executeSql(hrSql);
/*  26: 30 */     while (this.hrrs.next())
/*  27:    */     {
/*  28: 31 */       Map<String, Object> userMap = new HashMap();
/*  29:    */       
/*  30: 33 */       userMap.put("", "");
/*  31:    */       
/*  32: 35 */       userList.add(userMap);
/*  33:    */     }
/*  34: 38 */     return userList;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public String getSendContent(String userCode, String month)
/*  38:    */   {
/*  39: 43 */     String _userCode = userCode;
/*  40: 44 */     String _month = month;
/*  41:    */     
/*  42: 46 */     String Sql = "select h.Code,h.NameCN,h.Mail,h.Company,h.KaoQinType,h.DeptOneNameText,h.DeptTwoNameText,h.DeptThreeNameText,h.InCompany,h.Post,k.hzyf,k.ycqts,k.jbgs,k.jbztx,k.sj,k.bj,k.hj,k.sangj,k.cj,k.cjj,k.lcj,k.jyj,k.jsj,k.grgs,k.kg,k.trgs,k.wdk,k.brj,k.nxj,k.txj,k.synx,k.sytx from uf_kqhz k,uf_hr_userinfo1 h where h.Code = k.gh and h.Code = '" + 
/*  43:    */     
/*  44: 48 */       _userCode + "' and k.hzyf = '" + _month + "'";
/*  45:    */     
/*  46: 50 */     this.log.error("hrsql::" + Sql);
/*  47:    */     
/*  48: 52 */     this.hrrs.executeSql(Sql);
/*  49:    */     
/*  50: 54 */     String mailContent = "<table align=\"center\" border=\"1\" cellpadding=\"5\" style=\"width:600px;border-collapse:collapse;font-size:12px;\">";
/*  51: 56 */     while (this.hrrs.next())
/*  52:    */     {
/*  53: 57 */       mailContent = mailContent + "<tr>";
/*  54: 58 */       mailContent = mailContent + "<td colspan=\"4\" style=\"background-color: #1e90ff\"><span style=\"display: block; border-width: 0;font-size:14px;font-weight:bold;text-align: center\">月考勤汇总统计表</span></td>";
/*  55:    */       
/*  56:    */ 
/*  57: 61 */       mailContent = mailContent + "</tr>";
/*  58:    */       
/*  59: 63 */       mailContent = mailContent + "<tr>";
/*  60: 64 */       mailContent = mailContent + "<td style=\"width:20%;\">工号：</td>";
/*  61: 65 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getString("Code") + "</td>";
/*  62: 66 */       mailContent = mailContent + "<td style=\"width:20%;\">姓名：</td>";
/*  63: 67 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getString("NameCN") + "</td>";
/*  64: 68 */       mailContent = mailContent + "</tr>";
/*  65:    */       
/*  66: 70 */       mailContent = mailContent + "<tr>";
/*  67: 71 */       mailContent = mailContent + "<td style=\"width:20%;\">所属帐套：</td>";
/*  68: 72 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getString("Company") + "</td>";
/*  69: 73 */       mailContent = mailContent + "<td style=\"width:20%;\">考勤类型：</td>";
/*  70: 74 */       mailContent = mailContent + "<td style=\"width:30%;\">" + getAccountName(this.hrrs.getString("KaoQinType")) + "</td>";
/*  71: 75 */       mailContent = mailContent + "</tr>";
/*  72:    */       
/*  73: 77 */       mailContent = mailContent + "<tr>";
/*  74: 78 */       mailContent = mailContent + "<td style=\"width:20%;\">任职状态：</td>";
/*  75: 80 */       if (this.hrrs.getString("InCompany") == "0") {
/*  76: 81 */         mailContent = mailContent + "<td style=\"width:30%;\">在职</td>";
/*  77:    */       } else {
/*  78: 83 */         mailContent = mailContent + "<td style=\"width:30%;\">离职</td>";
/*  79:    */       }
/*  80: 86 */       mailContent = mailContent + "<td style=\"width:20%;\">岗位：</td>";
/*  81: 87 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getString("Post") + "</td>";
/*  82: 88 */       mailContent = mailContent + "</tr>";
/*  83:    */       
/*  84: 90 */       mailContent = mailContent + "<tr>";
/*  85: 91 */       mailContent = mailContent + "<td style=\"width:20%;\">考勤月份：</td>";
/*  86: 92 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getString("hzyf") + "</td>";
/*  87: 93 */       mailContent = mailContent + "<td style=\"width:20%;\">应出勤天数：</td>";
/*  88: 94 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("ycqts") + "</td>";
/*  89: 95 */       mailContent = mailContent + "</tr>";
/*  90:    */       
/*  91: 97 */       mailContent = mailContent + "<tr>";
/*  92: 98 */       mailContent = mailContent + "<td style=\"width:20%;\">加班工时：</td>";
/*  93: 99 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("jbgs") + "</td>";
/*  94:100 */       mailContent = mailContent + "<td style=\"width:20%;\">加班转调休：</td>";
/*  95:101 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("jbztx") + "</td>";
/*  96:102 */       mailContent = mailContent + "</tr>";
/*  97:    */       
/*  98:104 */       mailContent = mailContent + "<tr>";
/*  99:105 */       mailContent = mailContent + "<td style=\"width:20%;\">事假工时：</td>";
/* 100:106 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("sj") + "</td>";
/* 101:107 */       mailContent = mailContent + "<td style=\"width:20%;\">病假工时：</td>";
/* 102:108 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("bj") + "</td>";
/* 103:109 */       mailContent = mailContent + "</tr>";
/* 104:    */       
/* 105:111 */       mailContent = mailContent + "<tr>";
/* 106:112 */       mailContent = mailContent + "<td style=\"width:20%;\">婚假工时：</td>";
/* 107:113 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("hj") + "</td>";
/* 108:114 */       mailContent = mailContent + "<td style=\"width:20%;\">丧假工时：</td>";
/* 109:115 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("sangj") + "</td>";
/* 110:116 */       mailContent = mailContent + "</tr>";
/* 111:    */       
/* 112:118 */       mailContent = mailContent + "<tr>";
/* 113:119 */       mailContent = mailContent + "<td style=\"width:20%;\">产假工时：</td>";
/* 114:120 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("cj") + "</td>";
/* 115:121 */       mailContent = mailContent + "<td style=\"width:20%;\">产检假工时：</td>";
/* 116:122 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("cjj") + "</td>";
/* 117:123 */       mailContent = mailContent + "</tr>";
/* 118:    */       
/* 119:125 */       mailContent = mailContent + "<tr>";
/* 120:126 */       mailContent = mailContent + "<td style=\"width:20%;\">流产假工时：</td>";
/* 121:127 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("lcj") + "</td>";
/* 122:128 */       mailContent = mailContent + "<td style=\"width:20%;\">节育假工时：</td>";
/* 123:129 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("jyj") + "</td>";
/* 124:130 */       mailContent = mailContent + "</tr>";
/* 125:    */       
/* 126:132 */       mailContent = mailContent + "<tr>";
/* 127:133 */       mailContent = mailContent + "<td style=\"width:20%;\">计划生育假工时：</td>";
/* 128:134 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("jsj") + "</td>";
/* 129:135 */       mailContent = mailContent + "<td style=\"width:20%;\">个人工伤假工时：</td>";
/* 130:136 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("grgs") + "</td>";
/* 131:137 */       mailContent = mailContent + "</tr>";
/* 132:    */       
/* 133:139 */       mailContent = mailContent + "<tr>";
/* 134:140 */       mailContent = mailContent + "<td style=\"width:20%;\">旷工工时：</td>";
/* 135:141 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("kg") + "</td>";
/* 136:142 */       mailContent = mailContent + "<td style=\"width:20%;\">他人工伤假工时：</td>";
/* 137:143 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("trgs") + "</td>";
/* 138:144 */       mailContent = mailContent + "</tr>";
/* 139:    */       
/* 140:146 */       mailContent = mailContent + "<tr>";
/* 141:147 */       mailContent = mailContent + "<td style=\"width:20%;\">忘打卡次数：</td>";
/* 142:148 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("wdk") + "</td>";
/* 143:149 */       mailContent = mailContent + "<td style=\"width:20%;\">迟到次数：</td>";
/* 144:150 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getString("cd") + "</td>";
/* 145:151 */       mailContent = mailContent + "</tr>";
/* 146:    */       
/* 147:153 */       mailContent = mailContent + "<tr>";
/* 148:154 */       mailContent = mailContent + "<td style=\"width:20%;\">早退次数：</td>";
/* 149:155 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("wdk") + "</td>";
/* 150:156 */       mailContent = mailContent + "<td style=\"width:20%;\">哺乳假工时：</td>";
/* 151:157 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("brj") + "</td>";
/* 152:158 */       mailContent = mailContent + "</tr>";
/* 153:    */       
/* 154:160 */       mailContent = mailContent + "<tr>";
/* 155:161 */       mailContent = mailContent + "<td style=\"width:20%;\">年休假工时：</td>";
/* 156:162 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("nxj") + "</td>";
/* 157:163 */       mailContent = mailContent + "<td style=\"width:20%;\">调休假工时：</td>";
/* 158:164 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("txj") + "</td>";
/* 159:165 */       mailContent = mailContent + "</tr>";
/* 160:    */       
/* 161:167 */       mailContent = mailContent + "<tr>";
/* 162:168 */       mailContent = mailContent + "<td style=\"width:20%;\">剩余年休假：</td>";
/* 163:169 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("synx") + "</td>";
/* 164:170 */       mailContent = mailContent + "<td style=\"width:20%;\">剩余调休假：</td>";
/* 165:171 */       mailContent = mailContent + "<td style=\"width:30%;\">" + this.hrrs.getDouble("sytx") + "</td>";
/* 166:172 */       mailContent = mailContent + "</tr>";
/* 167:    */       
/* 168:174 */       mailContent = mailContent + "<tr>";
/* 169:175 */       mailContent = mailContent + "<td colspan=\"4\" style=\"text-align:center\">";
/* 170:176 */       mailContent = mailContent + "<a style=\"color:#ff0000\" href=\"http://oa.dinghantech.com/login/Login.jsp?gopage=/formmode/search/CustomSearchBySimple.jsp?customid=79\">";
/* 171:177 */       mailContent = mailContent + "点击查看详细考勤明细</a>";
/* 172:178 */       mailContent = mailContent + "</td>";
/* 173:179 */       mailContent = mailContent + "</tr>";
/* 174:    */     }
/* 175:182 */     mailContent = mailContent + "</table>";
/* 176:    */     
/* 177:184 */     return mailContent;
/* 178:    */   }
/* 179:    */   
/* 180:    */   private String getAccountName(String id)
/* 181:    */   {
/* 182:188 */     RecordSet rs = new RecordSet();
/* 183:189 */     String sql = "select company from uf_hr_company where id = " + id;
/* 184:190 */     rs.executeSql(sql);
/* 185:191 */     String companyName = "";
/* 186:192 */     while (rs.next()) {
/* 187:193 */       companyName = rs.getString("company");
/* 188:    */     }
/* 189:195 */     return companyName;
/* 190:    */   }
/* 191:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.test.MailTest
 * JD-Core Version:    0.7.0.1
 */