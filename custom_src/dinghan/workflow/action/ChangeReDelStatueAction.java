/*  1:   */ package dinghan.workflow.action;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import org.apache.commons.logging.Log;
/*  5:   */ import org.apache.commons.logging.LogFactory;
/*  6:   */ import weaver.conn.RecordSet;
/*  7:   */ import weaver.interfaces.workflow.action.Action;
/*  8:   */ import weaver.soa.workflow.request.RequestInfo;
/*  9:   */ import weaver.workflow.request.RequestManager;
/* 10:   */ 
/* 11:   */ public class ChangeReDelStatueAction
/* 12:   */   implements Action
/* 13:   */ {
/* 14:   */   private RequestManager requestManager;
/* 15:21 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/* 16:   */   
/* 17:   */   public String execute(RequestInfo request)
/* 18:   */   {
/* 19:24 */     this.requestManager = request.getRequestManager();
/* 20:25 */     String requestid = request.getRequestid();
/* 21:26 */     String workflowId = request.getWorkflowid();
/* 22:27 */     this.log.error("workflowId = " + workflowId);
/* 23:28 */     int formid = this.requestManager.getFormid();
/* 24:29 */     changeReDelStatue(requestid, formid);
/* 25:30 */     return "1";
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void changeReDelStatue(String requestid, int formid)
/* 29:   */   {
/* 30:33 */     this.log.error("开始执行欠料状态变更Action:请求ID -- " + requestid);
/* 31:34 */     ArrayList<String> redelListIds = new ArrayList();
/* 32:35 */     ArrayList<String> markList = new ArrayList();
/* 33:36 */     RecordSet rs = new RecordSet();
/* 34:37 */     RecordSet rs1 = new RecordSet();
/* 35:38 */     RecordSet rs2 = new RecordSet();
/* 36:39 */     String sql = "select top 1 id,qlflowlink from formtable_main_99 where requestId = " + requestid;
/* 37:40 */     this.log.error(sql);
/* 38:41 */     rs.executeSql(sql);
/* 39:42 */     int wfId = -1;
/* 40:43 */     String qlWfId = "";
/* 41:45 */     while (rs.next())
/* 42:   */     {
/* 43:46 */       wfId = rs.getInt("id");
/* 44:47 */       qlWfId = rs.getString("qlflowlink").trim();
/* 45:   */     }
/* 46:49 */     this.log.error("查询到流程主表记录的ID为：" + wfId);
/* 47:50 */     this.log.error("查询到欠发流程主表记录的ID为：" + qlWfId);
/* 48:51 */     if (qlWfId.trim() != "")
/* 49:   */     {
/* 50:53 */       sql = "select owndtbtn,mark from formtable_main_99_dt1 where mainid = " + wfId;
/* 51:54 */       this.log.error("开始执行查询：" + sql);
/* 52:55 */       rs1.executeSql(sql);
/* 53:56 */       String qlListRowId = "";
/* 54:57 */       String mark = "";
/* 55:58 */       while (rs1.next())
/* 56:   */       {
/* 57:59 */         qlListRowId = rs1.getString("owndtbtn").trim();
/* 58:60 */         mark = rs1.getString("mark");
/* 59:61 */         this.log.error("查询到欠料明细行的id为：" + qlListRowId);
/* 60:62 */         redelListIds.add(qlListRowId);
/* 61:63 */         markList.add(mark);
/* 62:   */       }
/* 63:65 */       for (int i = 0; i < redelListIds.size(); i++)
/* 64:   */       {
/* 65:66 */         sql = "update formtable_main_99_dt1 SET ownsoluted = '1',mark='" + (String)markList.get(i) + ";<br/>欠料已补发',redelwf=" + wfId + " where id= " + qlListRowId;
/* 66:67 */         this.log.error("开始执行更新：" + sql);
/* 67:68 */         if (rs2.executeSql(sql)) {
/* 68:69 */           this.log.error("第 " + (i + 1) + " 条明细数据更新完毕");
/* 69:   */         }
/* 70:   */       }
/* 71:73 */       sql = "select count(id) from formtable_main_99_dt1 where isown = '1' and ownsoluted <> '1' and mainid = " + qlWfId;
/* 72:74 */       rs2.executeSql(sql);
/* 73:75 */       int qldtnum = 0;
/* 74:76 */       while (rs2.next()) {
/* 75:77 */         qldtnum = rs2.getInt(1);
/* 76:   */       }
/* 77:79 */       if (qldtnum == 0)
/* 78:   */       {
/* 79:80 */         this.log.error("所有欠料明细都已经处理完成，现在更新主表欠料处理结果字段....");
/* 80:81 */         sql = "update formtable_main_99 set redeled = '1' where id = " + qlWfId;
/* 81:82 */         rs2.executeSql(sql);
/* 82:   */       }
/* 83:84 */       this.log.error("接口执行完成....");
/* 84:   */     }
/* 85:   */   }
/* 86:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.ChangeReDelStatueAction
 * JD-Core Version:    0.7.0.1
 */