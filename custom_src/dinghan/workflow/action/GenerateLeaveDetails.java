/*   1:    */ package dinghan.workflow.action;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.beans.Arith;
/*   4:    */ import dinghan.workflow.beans.PublicVariant;
/*   5:    */ import dinghan.workflow.beans.QJtemp;
/*   6:    */ import dinghan.workflow.beans.QingJia;
/*   7:    */ import dinghan.workflow.beans.QingJia0;
/*   8:    */ import dinghan.workflow.beans.QingJia1;
/*   9:    */ import dinghan.workflow.beans.UserInfo;
/*  10:    */ import java.text.SimpleDateFormat;
/*  11:    */ import java.util.ArrayList;
/*  12:    */ import java.util.Calendar;
/*  13:    */ import java.util.Date;
/*  14:    */ import java.util.GregorianCalendar;
/*  15:    */ import org.apache.commons.logging.Log;
/*  16:    */ import org.apache.commons.logging.LogFactory;
/*  17:    */ import weaver.interfaces.workflow.action.Action;
/*  18:    */ import weaver.soa.workflow.request.RequestInfo;
/*  19:    */ import weaver.workflow.request.RequestManager;
/*  20:    */ 
/*  21:    */ public class GenerateLeaveDetails
/*  22:    */   implements Action
/*  23:    */ {
/*  24: 23 */   private Log log = LogFactory.getLog(GenerateLeaveDetails.class.getName());
/*  25:    */   
/*  26:    */   public Log getLog()
/*  27:    */   {
/*  28: 26 */     return this.log;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void setLog(Log log)
/*  32:    */   {
/*  33: 30 */     this.log = log;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public String execute(RequestInfo request)
/*  37:    */   {
/*  38: 39 */     String returninfo = "0";
/*  39: 40 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  40: 41 */     SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
/*  41:    */     try
/*  42:    */     {
/*  43: 43 */       String requestid = request.getRequestid();
/*  44:    */       
/*  45: 45 */       int nodeid = request.getRequestManager().getNodeid();
/*  46:    */       
/*  47: 47 */       QingJia0 qj_main = new QingJia0(requestid);
/*  48: 48 */       int userid = qj_main.getProposer();
/*  49: 49 */       int mainid = qj_main.getId();
/*  50:    */       
/*  51: 51 */       int type = 0;
/*  52: 52 */       if (580 == nodeid)
/*  53:    */       {
/*  54: 54 */         UserInfo userInfo = new UserInfo(userid);
/*  55:    */         
/*  56: 56 */         QingJia.delete(mainid, 0);
/*  57:    */         
/*  58: 58 */         ArrayList<QingJia1> qj_oneList = QingJia1.queryList(mainid);
/*  59: 60 */         for (int i = 0; i < qj_oneList.size(); i++)
/*  60:    */         {
/*  61: 61 */           QingJia1 qj_one = (QingJia1)qj_oneList.get(i);
/*  62:    */           
/*  63: 63 */           Date deDate = sdf.parse(qj_one.getJsrq());
/*  64: 64 */           Date dsDate = sdf.parse(qj_one.getKsrq());
/*  65:    */           
/*  66: 66 */           double num = 0.0D;
/*  67: 67 */           String startTime = qj_one.getKsrq() + " " + 
/*  68: 68 */             qj_one.getKssj();
/*  69: 69 */           String endTime = qj_one.getJsrq() + " " + qj_one.getJssj();
/*  70:    */           
/*  71: 71 */           String se = qj_one.getKsrq() + " " + 
/*  72: 72 */             userInfo.getEndWorkTime();
/*  73: 73 */           String ee = qj_one.getJsrq() + " " + 
/*  74: 74 */             userInfo.getEndWorkTime();
/*  75: 75 */           String es = qj_one.getJsrq() + " " + 
/*  76: 76 */             userInfo.getStartWorkTime();
/*  77: 77 */           double dnum = PublicVariant.getTimeDifference(se, ee);
/*  78: 78 */           dnum = Arith.div(dnum, 86400000.0D, 2);
/*  79: 80 */           if (dnum > 0.0D)
/*  80:    */           {
/*  81: 81 */             if ((qj_one.getKssj().equals("08:30")) || 
/*  82: 82 */               (qj_one.getKssj().equals("09:00")))
/*  83:    */             {
/*  84: 83 */               num = 8.0D + (dnum - 1.0D) * 8.0D;
/*  85:    */             }
/*  86:    */             else
/*  87:    */             {
/*  88: 85 */               double q = PublicVariant.getTimeDifference(
/*  89: 86 */                 startTime, se);
/*  90: 87 */               q = Arith.div(q, 3600000.0D, 2);
/*  91: 88 */               num = num + q + (dnum - 1.0D) * 8.0D;
/*  92:    */             }
/*  93: 90 */             if (qj_one.getJssj().equals("12:00"))
/*  94:    */             {
/*  95: 91 */               double q = PublicVariant.getTimeDifference(es, 
/*  96: 92 */                 endTime);
/*  97: 93 */               q = Arith.div(q, 3600000.0D, 2);
/*  98: 94 */               num += q;
/*  99:    */             }
/* 100:    */             else
/* 101:    */             {
/* 102: 96 */               num += 8.0D;
/* 103:    */             }
/* 104:    */           }
/* 105: 99 */           else if (((qj_one.getKssj().equals("08:30")) || 
/* 106:100 */             (qj_one.getKssj().equals("09:00"))) && (
/* 107:101 */             (qj_one.getJssj().equals("17:30")) || 
/* 108:102 */             (qj_one.getJssj().equals("18:00"))))
/* 109:    */           {
/* 110:103 */             num = 8.0D;
/* 111:    */           }
/* 112:    */           else
/* 113:    */           {
/* 114:105 */             num = PublicVariant.getTimeDifference(startTime, 
/* 115:106 */               endTime);
/* 116:107 */             num = Arith.div(num, 3600000.0D, 2);
/* 117:    */           }
/* 118:111 */           if (qj_one.getQjlb() == 2)
/* 119:    */           {
/* 120:113 */             double synx = userInfo.getSYNianXiuJia() - num;
/* 121:114 */             userInfo.updateholiday(userid, synx, 0);
/* 122:    */           }
/* 123:115 */           else if (qj_one.getQjlb() == 3)
/* 124:    */           {
/* 125:117 */             double sytx = userInfo.getSYTiaoXiuJia() - num;
/* 126:118 */             userInfo.updateholiday(userid, sytx, 1);
/* 127:    */           }
/* 128:121 */           long qjNum = (deDate.getTime() - dsDate.getTime()) / 
/* 129:122 */             86400000L + 1L;
/* 130:124 */           for (int j = 0; j < qjNum; j++)
/* 131:    */           {
/* 132:126 */             Calendar calendar = new GregorianCalendar();
/* 133:127 */             calendar.setTime(dsDate);
/* 134:128 */             calendar.add(5, j);
/* 135:129 */             Date dsDate1 = calendar.getTime();
/* 136:130 */             String dsDateString = sdf.format(dsDate1);
/* 137:131 */             String Week = sdf1.format(dsDate1);
/* 138:    */             
/* 139:    */ 
/* 140:134 */             QingJia qj_three = new QingJia();
/* 141:135 */             qj_three.setHdzt(type);
/* 142:136 */             qj_three.setRq(dsDateString);
/* 143:137 */             qj_three.setQjlb(String.valueOf(qj_one.getQjlb()));
/* 144:138 */             qj_three.setXq(Week);
/* 145:139 */             qj_three.setMainid(mainid);
/* 146:140 */             qj_three.setUserid(userid);
/* 147:141 */             qj_three.setRow_id(i + 1);
/* 148:142 */             qj_three.setAppnom("");
/* 149:143 */             qj_three.setDkjl("");
/* 150:144 */             qj_three.setHdgs(0.0D);
/* 151:145 */             qj_three.setHdjssj("");
/* 152:146 */             qj_three.setHdkssj("");
/* 153:147 */             if (dsDate.equals(deDate))
/* 154:    */             {
/* 155:148 */               qj_three.setKssj(qj_one.getKssj());
/* 156:149 */               qj_three.setJssj(qj_one.getJssj());
/* 157:    */             }
/* 158:151 */             else if (j == 0)
/* 159:    */             {
/* 160:152 */               qj_three.setKssj(qj_one.getKssj());
/* 161:153 */               qj_three.setJssj(userInfo.getEndWorkTime());
/* 162:    */             }
/* 163:154 */             else if (j + 1 == qjNum)
/* 164:    */             {
/* 165:155 */               qj_three.setKssj(userInfo.getStartWorkTime());
/* 166:156 */               qj_three.setJssj(qj_one.getJssj());
/* 167:    */             }
/* 168:    */             else
/* 169:    */             {
/* 170:158 */               qj_three.setKssj(userInfo.getStartWorkTime());
/* 171:159 */               qj_three.setJssj(userInfo.getEndWorkTime());
/* 172:    */             }
/* 173:162 */             qj_three.insert();
/* 174:    */           }
/* 175:    */         }
/* 176:    */       }
/* 177:    */       else
/* 178:    */       {
/* 179:167 */         if (587 != nodeid) {
/* 180:    */           return returninfo;
/* 181:    */         }
/* 182:168 */         type = 2;
/* 183:169 */         QingJia.update(mainid, type);
/* 184:    */         
/* 185:171 */         ArrayList<QingJia> qj_threeList = QingJia.queryList(mainid);
/* 186:    */         
/* 187:173 */         String nowDate = PublicVariant.DateToStr(new Date(), 
/* 188:174 */           "YYYY-MM-dd");
/* 189:    */         
/* 190:176 */         String nowMon = nowDate.substring(5, 7);
/* 191:178 */         for (int i = 0; i < qj_threeList.size(); i++)
/* 192:    */         {
/* 193:180 */           QingJia qj_three = (QingJia)qj_threeList.get(i);
/* 194:181 */           QJtemp qJtemp = new QJtemp();
/* 195:182 */           qJtemp.setFlowno(qj_main.getAppnom());
/* 196:183 */           qJtemp.setHdgs(String.valueOf(qj_three.getHdgs()));
/* 197:184 */           qJtemp.setHdjssj(qj_three.getHdjssj());
/* 198:185 */           qJtemp.setHdkssj(qj_three.getHdkssj());
/* 199:186 */           qJtemp.setHdyf(nowMon);
/* 200:187 */           qJtemp.setHrmid(qj_three.getUserid());
/* 201:188 */           qJtemp.setHrmno(qj_three.getGh());
/* 202:189 */           qJtemp.setKqr(qj_three.getKqy());
/* 203:190 */           qJtemp.setMainid(String.valueOf(qj_three.getMainid()));
/* 204:191 */           qJtemp.setQjlx(Integer.parseInt(qj_three.getQjlb()) + 1);
/* 205:192 */           qJtemp.setQjrq(qj_three.getRq());
/* 206:193 */           qJtemp.setXq(qj_three.getXq());
/* 207:194 */           qJtemp.setDataid("");
/* 208:    */           
/* 209:196 */           QJtemp.delete(mainid, qj_three.getRq());
/* 210:    */           
/* 211:198 */           qJtemp.insert();
/* 212:    */         }
/* 213:    */       }
/* 214:204 */       returninfo = "1";
/* 215:    */     }
/* 216:    */     catch (Exception e)
/* 217:    */     {
/* 218:208 */       this.log.error("生成明细失败" + e);
/* 219:    */     }
/* 220:    */     finally
/* 221:    */     {
/* 222:211 */       return returninfo;
/* 223:    */     }
/* 224:    */   }
/* 225:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.GenerateLeaveDetails
 * JD-Core Version:    0.7.0.1
 */