/*  1:   */ package dinghan.workflow.action;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import org.apache.commons.logging.Log;
/*  6:   */ import org.apache.commons.logging.LogFactory;
/*  7:   */ import weaver.conn.RecordSet;
/*  8:   */ import weaver.interfaces.workflow.action.Action;
/*  9:   */ import weaver.soa.workflow.request.RequestInfo;
/* 10:   */ import weaver.workflow.request.RequestManager;
/* 11:   */ 
/* 12:   */ public class AutoChangeDocToUnBrowAbleAction
/* 13:   */   implements Action
/* 14:   */ {
/* 15:   */   private RequestManager requestManager;
/* 16:16 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/* 17:   */   
/* 18:   */   public String execute(RequestInfo request)
/* 19:   */   {
/* 20:20 */     this.requestManager = request.getRequestManager();
/* 21:21 */     String requestid = request.getRequestid();
/* 22:22 */     String workflowId = request.getWorkflowid();
/* 23:23 */     this.log.error("workflowId = " + workflowId);
/* 24:24 */     int formid = this.requestManager.getFormid();
/* 25:25 */     String src = this.requestManager.getSrc();
/* 26:26 */     this.log.error("页面请求类型 = " + src);
/* 27:   */     
/* 28:28 */     int cNodeId = this.requestManager.getNodeid();
/* 29:   */     
/* 30:30 */     AutoChangeDocStatueAction(requestid, formid, src, cNodeId);
/* 31:31 */     return "1";
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void AutoChangeDocStatueAction(String requestId, int formid, String requestType, int cNodeId)
/* 35:   */   {
/* 36:37 */     System.out.println("当前节点ID ： " + cNodeId);
/* 37:38 */     System.out.println("页面请求类型：" + this.requestManager.getSrc());
/* 38:   */     
/* 39:40 */     RecordSet rs = new RecordSet();
/* 40:   */     
/* 41:42 */     ArrayList<String> docIdList = new ArrayList();
/* 42:   */     
/* 43:44 */     String formName = "formtable_main_" + Math.abs(formid);
/* 44:45 */     String dtFormName = "formtable_main_" + Math.abs(formid) + "_dt1";
/* 45:   */     
/* 46:47 */     String sql = "select id from " + formName + " where requestId = " + requestId;
/* 47:   */     
/* 48:49 */     int mainId = -1;
/* 49:   */     
/* 50:51 */     rs.executeSql(sql);
/* 51:53 */     while (rs.next()) {
/* 52:54 */       mainId = rs.getInt("id");
/* 53:   */     }
/* 54:57 */     sql = "select * from " + dtFormName + " where mainid = " + mainId + " and d_scan = '0'";
/* 55:   */     
/* 56:59 */     rs.executeSql(sql);
/* 57:61 */     while (rs.next()) {
/* 58:62 */       docIdList.add(rs.getString("d_docname"));
/* 59:   */     }
/* 60:65 */     int borwStatue = 1;
/* 61:67 */     if (docIdList.size() > 0) {
/* 62:68 */       for (String docId : docIdList)
/* 63:   */       {
/* 64:69 */         sql = "update uf_documentlist set docbrowstatue = '" + borwStatue + "' where id = " + docId;
/* 65:70 */         rs.executeSql(sql);
/* 66:   */       }
/* 67:   */     }
/* 68:   */   }
/* 69:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.AutoChangeDocToUnBrowAbleAction
 * JD-Core Version:    0.7.0.1
 */