/*  1:   */ package dinghan.workflow.action.seasonkpiaction;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.action.ChangeReDelStatueAction;
/*  4:   */ import org.apache.commons.logging.Log;
/*  5:   */ import org.apache.commons.logging.LogFactory;
/*  6:   */ import weaver.conn.RecordSet;
/*  7:   */ import weaver.interfaces.workflow.action.Action;
/*  8:   */ import weaver.soa.workflow.request.RequestInfo;
/*  9:   */ import weaver.workflow.request.RequestManager;
/* 10:   */ 
/* 11:   */ public class AutoFillSammaryDateAndUserAction
/* 12:   */   implements Action
/* 13:   */ {
/* 14:   */   private RequestManager requestManager;
/* 15:20 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/* 16:   */   
/* 17:   */   public String execute(RequestInfo request)
/* 18:   */   {
/* 19:22 */     this.requestManager = request.getRequestManager();
/* 20:23 */     String requestid = request.getRequestid();
/* 21:24 */     int formid = this.requestManager.getFormid();
/* 22:25 */     int userid = this.requestManager.getUserId();
/* 23:   */     
/* 24:27 */     FillSammaryDateAndUser(requestid, formid, userid);
/* 25:   */     
/* 26:29 */     return "1";
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void FillSammaryDateAndUser(String requestId, int formid, int userid)
/* 30:   */   {
/* 31:33 */     this.log.error("开始自动填写总结日期和人员...");
/* 32:   */     
/* 33:35 */     String sql = "select top 1 id,monthchecked from formtable_main_" + Math.abs(formid) + " where requestId = " + requestId;
/* 34:36 */     RecordSet rs = new RecordSet();
/* 35:   */     
/* 36:38 */     rs.executeSql(sql);
/* 37:   */     
/* 38:40 */     int mainId = -1;
/* 39:41 */     int mmmark = -1;
/* 40:43 */     while (rs.next())
/* 41:   */     {
/* 42:44 */       mainId = rs.getInt("id");
/* 43:45 */       mmmark = rs.getInt("monthchecked");
/* 44:   */     }
/* 45:47 */     String curDate = this.requestManager.getCurrentDate();
/* 46:   */     
/* 47:49 */     this.log.error("当前日期：" + curDate);
/* 48:51 */     if (mmmark == 2)
/* 49:   */     {
/* 50:52 */       sql = "update formtable_main_" + Math.abs(formid) + "_dt3 set d_m1_sumpsn = '" + userid + "', d_m1_sumdate = '" + curDate + "' where mainid = " + mainId;
/* 51:53 */       this.log.error("开始执行更新：" + sql);
/* 52:54 */       rs.executeSql(sql);
/* 53:   */     }
/* 54:   */     else
/* 55:   */     {
/* 56:57 */       sql = "select count(id) from formtable_main_" + Math.abs(formid) + "_dt3 where d_m1_sumpsn <> ''";
/* 57:58 */       if (rs.getCounts() > 0)
/* 58:   */       {
/* 59:59 */         sql = "update formtable_main_" + Math.abs(formid) + "_dt4 set d_m2_sumpsn = '" + userid + "', d_m2_sumdate = '" + curDate + "' where mainid = " + mainId;
/* 60:60 */         this.log.error("开始执行更新：" + sql);
/* 61:61 */         rs.executeSql(sql);
/* 62:   */       }
/* 63:   */       else
/* 64:   */       {
/* 65:63 */         sql = "update formtable_main_" + Math.abs(formid) + "_dt3 set d_m1_sumpsn = '" + userid + "', d_m1_sumdate = '" + curDate + "' where mainid = " + mainId;
/* 66:64 */         this.log.error("开始执行更新：" + sql);
/* 67:65 */         rs.executeSql(sql);
/* 68:66 */         sql = "update formtable_main_" + Math.abs(formid) + "_dt4 set d_m2_sumpsn = '" + userid + "', d_m2_sumdate = '" + curDate + "' where mainid = " + mainId;
/* 69:67 */         this.log.error("开始执行更新：" + sql);
/* 70:68 */         rs.executeSql(sql);
/* 71:   */       }
/* 72:   */     }
/* 73:72 */     this.log.error("自动填写总结日期和人员结束。");
/* 74:   */   }
/* 75:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.seasonkpiaction.AutoFillSammaryDateAndUserAction
 * JD-Core Version:    0.7.0.1
 */