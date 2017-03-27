/*  1:   */ package dinghan.workflow.action;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.beans.ChuChai;
/*  4:   */ import dinghan.workflow.beans.ChuChai2;
/*  5:   */ import dinghan.workflow.beans.UserInfo;
/*  6:   */ import java.text.SimpleDateFormat;
/*  7:   */ import java.util.Calendar;
/*  8:   */ import java.util.Date;
/*  9:   */ import java.util.GregorianCalendar;
/* 10:   */ import org.apache.commons.logging.Log;
/* 11:   */ import org.apache.commons.logging.LogFactory;
/* 12:   */ import weaver.interfaces.workflow.action.Action;
/* 13:   */ import weaver.soa.workflow.request.RequestInfo;
/* 14:   */ import weaver.workflow.request.RequestManager;
/* 15:   */ 
/* 16:   */ public class CcGenerateDetail
/* 17:   */   implements Action
/* 18:   */ {
/* 19:18 */   private Log log = LogFactory.getLog(CcGenerateDetail.class.getName());
/* 20:   */   
/* 21:   */   public Log getLog()
/* 22:   */   {
/* 23:21 */     return this.log;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void setLog(Log log)
/* 27:   */   {
/* 28:25 */     this.log = log;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public String execute(RequestInfo request)
/* 32:   */   {
/* 33:31 */     String returninfo = "0";
/* 34:32 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 35:   */     try
/* 36:   */     {
/* 37:34 */       String requestid = request.getRequestid();
/* 38:   */       
/* 39:36 */       int nodeid = request.getRequestManager().getNodeid();
/* 40:37 */       int type = 0;
/* 41:38 */       if (589 == nodeid)
/* 42:   */       {
/* 43:41 */         ChuChai cc_main = new ChuChai(requestid);
/* 44:42 */         int userid = cc_main.getProposer();
/* 45:43 */         int mainid = cc_main.getId();
/* 46:   */         
/* 47:45 */         UserInfo userInfo = new UserInfo(userid);
/* 48:   */         
/* 49:47 */         ChuChai2.delete(mainid, 0);
/* 50:   */         
/* 51:49 */         Date dsDate = sdf.parse(cc_main.getYjccsj());
/* 52:50 */         Date deDate = sdf.parse(cc_main.getCcsj2());
/* 53:   */         
/* 54:52 */         long dNum = (deDate.getTime() - dsDate.getTime()) / 
/* 55:53 */           86400000L + 1L;
/* 56:55 */         for (int j = 0; j < dNum; j++)
/* 57:   */         {
/* 58:56 */           Calendar calendar = new GregorianCalendar();
/* 59:57 */           calendar.setTime(dsDate);
/* 60:58 */           calendar.add(5, j);
/* 61:59 */           String dsDateString = sdf.format(calendar.getTime());
/* 62:   */           
/* 63:61 */           ChuChai2 cc_two = new ChuChai2();
/* 64:62 */           cc_two.setCcrq(dsDateString);
/* 65:63 */           cc_two.setMainid(mainid);
/* 66:64 */           cc_two.setHdzt(type);
/* 67:65 */           cc_two.setUserid(userid);
/* 68:66 */           cc_two.setHdjssj("");
/* 69:67 */           cc_two.setHdkssj("");
/* 70:68 */           cc_two.setHdzt(0);
/* 71:69 */           cc_two.setRow_id(j + 1);
/* 72:71 */           if (dsDate.equals(deDate))
/* 73:   */           {
/* 74:72 */             cc_two.setYjkssj(cc_main.getCcsj1());
/* 75:73 */             cc_two.setYjjssj(cc_main.getCcsj3());
/* 76:   */           }
/* 77:75 */           else if (j == 0)
/* 78:   */           {
/* 79:76 */             cc_two.setYjkssj(cc_main.getCcsj1());
/* 80:77 */             cc_two.setYjjssj(userInfo.getEndWorkTime());
/* 81:   */           }
/* 82:78 */           else if (j + 1 == dNum)
/* 83:   */           {
/* 84:79 */             cc_two.setYjkssj(userInfo.getStartWorkTime());
/* 85:80 */             cc_two.setYjjssj(cc_main.getCcsj3());
/* 86:   */           }
/* 87:   */           else
/* 88:   */           {
/* 89:82 */             cc_two.setYjkssj(userInfo.getStartWorkTime());
/* 90:83 */             cc_two.setYjjssj(userInfo.getEndWorkTime());
/* 91:   */           }
/* 92:87 */           cc_two.insert();
/* 93:   */         }
/* 94:90 */         returninfo = "1";
/* 95:   */       }
/* 96:   */     }
/* 97:   */     catch (Exception e)
/* 98:   */     {
/* 99:93 */       this.log.error("生成出差明细失败：" + e);
/* :0:   */     }
/* :1:   */     finally
/* :2:   */     {
/* :3:95 */       return returninfo;
/* :4:   */     }
/* :5:   */   }
/* :6:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.CcGenerateDetail
 * JD-Core Version:    0.7.0.1
 */