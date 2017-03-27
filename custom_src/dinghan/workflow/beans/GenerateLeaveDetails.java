/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.text.SimpleDateFormat;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.Calendar;
/*   6:    */ import java.util.Date;
/*   7:    */ import java.util.GregorianCalendar;
/*   8:    */ import org.apache.commons.logging.Log;
/*   9:    */ import org.apache.commons.logging.LogFactory;
/*  10:    */ import weaver.interfaces.workflow.action.Action;
/*  11:    */ import weaver.soa.workflow.request.RequestInfo;
/*  12:    */ import weaver.workflow.request.RequestManager;
/*  13:    */ 
/*  14:    */ public class GenerateLeaveDetails
/*  15:    */   implements Action
/*  16:    */ {
/*  17: 22 */   private Log log = LogFactory.getLog(GenerateLeaveDetails.class.getName());
/*  18:    */   
/*  19:    */   public Log getLog()
/*  20:    */   {
/*  21: 25 */     return this.log;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void setLog(Log log)
/*  25:    */   {
/*  26: 29 */     this.log = log;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public String execute(RequestInfo request)
/*  30:    */   {
/*  31: 38 */     String returninfo = "0";
/*  32: 39 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  33: 40 */     SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
/*  34:    */     try
/*  35:    */     {
/*  36: 42 */       String requestid = request.getRequestid();
/*  37:    */       
/*  38: 44 */       int nodeid = request.getRequestManager().getNodeid();
/*  39:    */       
/*  40: 46 */       QingJia0 qj_main = new QingJia0(requestid);
/*  41: 47 */       int userid = qj_main.getProposer();
/*  42: 48 */       int mainid = qj_main.getId();
/*  43:    */       
/*  44: 50 */       int type = 0;
/*  45: 51 */       if (580 == nodeid)
/*  46:    */       {
/*  47: 53 */         UserInfo userInfo = new UserInfo(userid);
/*  48:    */         
/*  49: 55 */         QingJia.delete(mainid, 0);
/*  50:    */         
/*  51: 57 */         ArrayList<QingJia1> qj_oneList = QingJia1.queryList(mainid);
/*  52: 59 */         for (int i = 0; i < qj_oneList.size(); i++)
/*  53:    */         {
/*  54: 60 */           QingJia1 qj_one = (QingJia1)qj_oneList.get(i);
/*  55:    */           
/*  56: 62 */           Date deDate = sdf.parse(qj_one.getJsrq());
/*  57: 63 */           Date dsDate = sdf.parse(qj_one.getKsrq());
/*  58:    */           
/*  59: 65 */           long qjNum = (deDate.getTime() - dsDate.getTime()) / 
/*  60: 66 */             86400000L + 1L;
/*  61: 68 */           for (int j = 0; j < qjNum; j++)
/*  62:    */           {
/*  63: 70 */             Calendar calendar = new GregorianCalendar();
/*  64: 71 */             calendar.setTime(dsDate);
/*  65: 72 */             calendar.add(5, j);
/*  66: 73 */             Date dsDate1 = calendar.getTime();
/*  67: 74 */             String dsDateString = sdf.format(dsDate1);
/*  68: 75 */             String Week = sdf1.format(dsDate1);
/*  69:    */             
/*  70:    */ 
/*  71: 78 */             QingJia qj_three = new QingJia();
/*  72: 79 */             qj_three.setHdzt(type);
/*  73: 80 */             qj_three.setRq(dsDateString);
/*  74: 81 */             qj_three.setQjlb(String.valueOf(qj_one.getQjlb()));
/*  75: 82 */             qj_three.setXq(Week);
/*  76: 83 */             qj_three.setMainid(mainid);
/*  77: 84 */             qj_three.setUserid(userid);
/*  78: 85 */             qj_three.setRow_id(j + 1);
/*  79: 86 */             qj_three.setAppnom("");
/*  80: 87 */             qj_three.setDkjl("");
/*  81: 88 */             qj_three.setHdgs(0.0D);
/*  82: 89 */             qj_three.setHdjssj("");
/*  83: 90 */             qj_three.setHdkssj("");
/*  84: 91 */             if (dsDate.equals(deDate))
/*  85:    */             {
/*  86: 92 */               qj_three.setKssj(qj_one.getKssj());
/*  87: 93 */               qj_three.setJssj(qj_one.getJssj());
/*  88:    */             }
/*  89: 95 */             else if (j == 0)
/*  90:    */             {
/*  91: 96 */               qj_three.setKssj(qj_one.getKssj());
/*  92: 97 */               qj_three.setJssj(userInfo.getEndWorkTime());
/*  93:    */             }
/*  94: 98 */             else if (j + 1 == qjNum)
/*  95:    */             {
/*  96: 99 */               qj_three.setKssj(userInfo.getStartWorkTime());
/*  97:100 */               qj_three.setJssj(qj_one.getJssj());
/*  98:    */             }
/*  99:    */             else
/* 100:    */             {
/* 101:102 */               qj_three.setKssj(userInfo.getStartWorkTime());
/* 102:103 */               qj_three.setJssj(userInfo.getEndWorkTime());
/* 103:    */             }
/* 104:106 */             qj_three.insert();
/* 105:    */           }
/* 106:    */         }
/* 107:    */       }
/* 108:    */       else
/* 109:    */       {
/* 110:110 */         if (587 != nodeid) {
/* 111:    */           return returninfo;
/* 112:    */         }
/* 113:111 */         type = 2;
/* 114:112 */         QingJia.update(mainid, type);
/* 115:    */         
/* 116:114 */         QJtemp.delete(mainid);
/* 117:    */         
/* 118:116 */         ArrayList<QingJia> qj_threeList = QingJia.queryList(mainid);
/* 119:    */         
/* 120:118 */         String nowDate = PublicVariant.DateToStr(new Date(), 
/* 121:119 */           "YYYY-MM-dd");
/* 122:    */         
/* 123:121 */         String nowMon = nowDate.substring(5, 7);
/* 124:123 */         for (int i = 0; i < qj_threeList.size(); i++)
/* 125:    */         {
/* 126:125 */           QingJia qj_three = (QingJia)qj_threeList.get(i);
/* 127:126 */           QJtemp qJtemp = new QJtemp();
/* 128:127 */           qJtemp.setFlowno(qj_three.getAppnom());
/* 129:128 */           qJtemp.setHdgs(String.valueOf(qj_three.getHdgs()));
/* 130:129 */           qJtemp.setHdjssj(qj_three.getHdjssj());
/* 131:130 */           qJtemp.setHdkssj(qj_three.getHdkssj());
/* 132:131 */           qJtemp.setHdyf(nowMon);
/* 133:132 */           qJtemp.setHrmid(qj_three.getUserid());
/* 134:133 */           qJtemp.setHrmno(qj_three.getGh());
/* 135:134 */           qJtemp.setKqr(qj_three.getKqy());
/* 136:135 */           qJtemp.setMainid(String.valueOf(qj_three.getMainid()));
/* 137:136 */           qJtemp.setQjlx(Integer.parseInt(qj_three.getQjlb()) + 1);
/* 138:137 */           qJtemp.setQjrq(qj_three.getRq());
/* 139:138 */           qJtemp.setXq(qj_three.getXq());
/* 140:139 */           qJtemp.setDataid("");
/* 141:140 */           qJtemp.insert();
/* 142:    */         }
/* 143:    */       }
/* 144:146 */       returninfo = "1";
/* 145:    */     }
/* 146:    */     catch (Exception e)
/* 147:    */     {
/* 148:150 */       this.log.error("生成明细失败" + e);
/* 149:    */     }
/* 150:    */     finally
/* 151:    */     {
/* 152:153 */       return returninfo;
/* 153:    */     }
/* 154:    */   }
/* 155:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.GenerateLeaveDetails
 * JD-Core Version:    0.7.0.1
 */