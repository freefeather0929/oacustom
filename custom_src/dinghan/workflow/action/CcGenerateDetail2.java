/*  1:   */ package dinghan.workflow.action;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.beans.Cctemp;
/*  4:   */ import dinghan.workflow.beans.ChuChai;
/*  5:   */ import dinghan.workflow.beans.ChuChai2;
/*  6:   */ import dinghan.workflow.beans.UserInfo;
/*  7:   */ import java.text.SimpleDateFormat;
/*  8:   */ import java.util.Calendar;
/*  9:   */ import java.util.Date;
/* 10:   */ import java.util.GregorianCalendar;
/* 11:   */ import org.apache.commons.logging.Log;
/* 12:   */ import org.apache.commons.logging.LogFactory;
/* 13:   */ import weaver.interfaces.workflow.action.Action;
/* 14:   */ import weaver.soa.workflow.request.RequestInfo;
/* 15:   */ import weaver.workflow.request.RequestManager;
/* 16:   */ 
/* 17:   */ public class CcGenerateDetail2
/* 18:   */   implements Action
/* 19:   */ {
/* 20:19 */   private Log log = LogFactory.getLog(CcGenerateDetail.class.getName());
/* 21:   */   
/* 22:   */   public Log getLog()
/* 23:   */   {
/* 24:22 */     return this.log;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setLog(Log log)
/* 28:   */   {
/* 29:26 */     this.log = log;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String execute(RequestInfo request)
/* 33:   */   {
/* 34:32 */     String returninfo = "0";
/* 35:33 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 36:   */     try
/* 37:   */     {
/* 38:35 */       this.log.error("hahahahah");
/* 39:36 */       String requestid = request.getRequestid();
/* 40:   */       
/* 41:38 */       int nodeid = request.getRequestManager().getNodeid();
/* 42:   */       
/* 43:40 */       ChuChai cc_main = new ChuChai(requestid);
/* 44:41 */       int userid = cc_main.getProposer();
/* 45:42 */       int mainid = cc_main.getId();
/* 46:   */       
/* 47:44 */       UserInfo userInfo = new UserInfo(userid);
/* 48:   */       
/* 49:46 */       ChuChai2.delete(mainid, 0);
/* 50:   */       
/* 51:48 */       Date dsDate = sdf.parse(cc_main.getSjkssj());
/* 52:49 */       Date deDate = sdf.parse(cc_main.getSjjssj());
/* 53:   */       
/* 54:51 */       long dNum = (deDate.getTime() - dsDate.getTime()) / 
/* 55:52 */         86400000L + 1L;
/* 56:54 */       for (int j = 0; j < dNum; j++)
/* 57:   */       {
/* 58:55 */         Calendar calendar = new GregorianCalendar();
/* 59:56 */         calendar.setTime(dsDate);
/* 60:57 */         calendar.add(5, j);
/* 61:58 */         String dsDateString = sdf.format(calendar.getTime());
/* 62:   */         
/* 63:60 */         ChuChai2 cc_two = new ChuChai2();
/* 64:61 */         cc_two.setCcrq(dsDateString);
/* 65:62 */         cc_two.setMainid(mainid);
/* 66:63 */         cc_two.setUserid(userid);
/* 67:64 */         cc_two.setHdjssj("");
/* 68:65 */         cc_two.setHdkssj("");
/* 69:66 */         cc_two.setRow_id(j + 1);
/* 70:68 */         if (dsDate.equals(deDate))
/* 71:   */         {
/* 72:69 */           cc_two.setYjkssj(cc_main.getKssj1());
/* 73:70 */           cc_two.setYjjssj(cc_main.getKssj3());
/* 74:   */         }
/* 75:72 */         else if (j == 0)
/* 76:   */         {
/* 77:73 */           cc_two.setYjkssj(cc_main.getKssj1());
/* 78:74 */           cc_two.setYjjssj(userInfo.getEndWorkTime());
/* 79:   */         }
/* 80:75 */         else if (j + 1 == dNum)
/* 81:   */         {
/* 82:76 */           cc_two.setYjkssj(userInfo.getStartWorkTime());
/* 83:77 */           cc_two.setYjjssj(cc_main.getKssj3());
/* 84:   */         }
/* 85:   */         else
/* 86:   */         {
/* 87:79 */           cc_two.setYjkssj(userInfo.getStartWorkTime());
/* 88:80 */           cc_two.setYjjssj(userInfo.getEndWorkTime());
/* 89:   */         }
/* 90:84 */         cc_two.insert();
/* 91:   */       }
/* 92:87 */       Cctemp.delete(cc_main.getId());
/* 93:88 */       ChuChai2.checkList(cc_main.getId());
/* 94:89 */       returninfo = "1";
/* 95:   */     }
/* 96:   */     catch (Exception e)
/* 97:   */     {
/* 98:92 */       this.log.error("生成出差明细失败：" + e);
/* 99:   */     }
/* :0:   */     finally
/* :1:   */     {
/* :2:94 */       return returninfo;
/* :3:   */     }
/* :4:   */   }
/* :5:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.CcGenerateDetail2
 * JD-Core Version:    0.7.0.1
 */