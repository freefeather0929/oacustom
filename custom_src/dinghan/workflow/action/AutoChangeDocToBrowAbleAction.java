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
/* 12:   */ public class AutoChangeDocToBrowAbleAction
/* 13:   */   implements Action
/* 14:   */ {
/* 15:   */   private RequestManager requestManager;
/* 16:16 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/* 17:   */   
/* 18:   */   public String execute(RequestInfo request)
/* 19:   */   {
/* 20:20 */     this.requestManager = request.getRequestManager();
/* 21:21 */     String requestid = request.getRequestid();
/* 22:22 */     int formid = this.requestManager.getFormid();
/* 23:23 */     String src = this.requestManager.getSrc();
/* 24:   */     
/* 25:25 */     int cNodeId = this.requestManager.getNodeid();
/* 26:   */     
/* 27:27 */     AutoChangeDocStatueAction(requestid, formid, src, cNodeId);
/* 28:28 */     return "1";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void AutoChangeDocStatueAction(String requestId, int formid, String requestType, int cNodeId)
/* 32:   */   {
/* 33:34 */     System.out.println("当前节点ID ： " + cNodeId);
/* 34:35 */     System.out.println("页面请求类型：" + this.requestManager.getSrc());
/* 35:   */     
/* 36:37 */     RecordSet rs = new RecordSet();
/* 37:   */     
/* 38:39 */     ArrayList<String> docIdList = new ArrayList();
/* 39:   */     
/* 40:41 */     String formName = "formtable_main_" + Math.abs(formid);
/* 41:42 */     String dtFormName = "formtable_main_" + Math.abs(formid) + "_dt1";
/* 42:   */     
/* 43:44 */     String sql = "select id from " + formName + " where requestId = " + requestId;
/* 44:   */     
/* 45:46 */     int mainId = -1;
/* 46:   */     
/* 47:48 */     rs.executeSql(sql);
/* 48:50 */     while (rs.next()) {
/* 49:51 */       mainId = rs.getInt("id");
/* 50:   */     }
/* 51:54 */     sql = "select * from " + dtFormName + " where mainid = " + mainId + " and d_scan = '0'";
/* 52:   */     
/* 53:56 */     rs.executeSql(sql);
/* 54:58 */     while (rs.next()) {
/* 55:59 */       docIdList.add(rs.getString("d_docname"));
/* 56:   */     }
/* 57:62 */     int borwStatue = 0;
/* 58:64 */     if (docIdList.size() > 0) {
/* 59:65 */       for (String docId : docIdList)
/* 60:   */       {
/* 61:66 */         sql = "update uf_documentlist set docbrowstatue = '" + borwStatue + "' where id = " + docId;
/* 62:67 */         rs.executeSql(sql);
/* 63:   */       }
/* 64:   */     }
/* 65:   */   }
/* 66:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.AutoChangeDocToBrowAbleAction
 * JD-Core Version:    0.7.0.1
 */