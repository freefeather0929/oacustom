/*  1:   */ package dinghan.workflow.action;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.beans.Abnormal;
/*  4:   */ import org.apache.commons.logging.Log;
/*  5:   */ import org.apache.commons.logging.LogFactory;
/*  6:   */ import weaver.interfaces.workflow.action.Action;
/*  7:   */ import weaver.soa.workflow.request.RequestInfo;
/*  8:   */ 
/*  9:   */ public class YcGenerateDetail
/* 10:   */   implements Action
/* 11:   */ {
/* 12:11 */   private Log log = LogFactory.getLog(YcGenerateDetail.class.getName());
/* 13:   */   
/* 14:   */   public Log getLog()
/* 15:   */   {
/* 16:14 */     return this.log;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setLog(Log log)
/* 20:   */   {
/* 21:18 */     this.log = log;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String execute(RequestInfo request)
/* 25:   */   {
/* 26:24 */     String returninfo = "0";
/* 27:   */     try
/* 28:   */     {
/* 29:27 */       String requestid = request.getRequestid();
/* 30:28 */       int requestId = Integer.parseInt(requestid);
/* 31:29 */       Abnormal.update(requestId, 1);
/* 32:30 */       returninfo = "1";
/* 33:   */     }
/* 34:   */     catch (Exception e)
/* 35:   */     {
/* 36:33 */       this.log.error("修改异常核定状态出错：" + e);
/* 37:   */     }
/* 38:   */     finally
/* 39:   */     {
/* 40:35 */       return returninfo;
/* 41:   */     }
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.YcGenerateDetail
 * JD-Core Version:    0.7.0.1
 */